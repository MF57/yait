import React from 'react';
import { Platform, ActivityIndicator, StyleSheet, Text, View, Button, Image, FlatList, StatusBar, TouchableHighlight, TextInput, KeyboardAvoidingView, ScrollView, Linking, Alert } from 'react-native';
import { KeyboardAwareScrollView } from 'react-native-keyboard-aware-scroll-view'
import Expo from 'expo';
import { FontAwesome } from '@expo/vector-icons';
import { StackNavigator, TabNavigator, NavigationActions } from 'react-navigation';
import store from 'react-native-simple-store';
import axios from 'axios';
import jwtDecode from 'jwt-decode';
const PropTypes = require('prop-types');


import IssuesList from './components/IssuesList';
import IssueView from './components/IssueView';
import AddComment from './components/AddComment';


const iconSize = 24;
const prefix = Platform.OS == 'android' ? 'yait://yait/' : 'yait://';
axios.defaults.baseURL = 'http://176.9.152.5:8080/api/v1/';

const styles = StyleSheet.create({
  container: {
    marginTop: Expo.Constants.statusBarHeight,
    paddingHorizontal: 20,
    paddingTop: 20,
  },
});



export class DefaultContainer extends React.Component {
  render() {
    return (
      <KeyboardAwareScrollView style={styles.container} extraScrollHeight={50}>
        {this.props.children}
      </KeyboardAwareScrollView>
    );
  }
}

class IssueSubmit extends React.Component {
    constructor(props) {
        super(props);
        this.state = {};
    }

    submit = () => {
        axios.post('/issues', {
            title: this.state.title,
            description: this.state.description
        })
        .then((response) => {
           console.log(response);
           this.props.navigation.navigate('Single', {issueId:response.data.id, title: response.data.title})
        })
        .catch((error) => {
            console.log(error)
        })
        ;
    };

    render() {
        return (
            <DefaultContainer>
                 <TextInput
                     ref="1"
                     style={{height: 40, borderColor: 'gray', borderWidth: 1}}
                     onChangeText={(title) => this.setState({title})}
                     value={this.state.title}
                     returnKeyType="next"
                     onSubmitEditing={() => this.focusNextField('2')}
                     keyboardType="default"
                 />
                 <TextInput
                     ref="2"
                     style={{height: 250, borderColor: 'gray', borderWidth: 1}}
                     onChangeText={(description) => this.setState({description})}
                     value={this.state.description}
                     returnKeyType="default"
                     keyboardType="default"
                 />
                 <Button title="Submit" onPress={this.submit} />
             </DefaultContainer>
        );
    }
}

const IssuesTab = StackNavigator({
  List: {
    screen: IssuesList,
  },
  Single: { 
    screen: IssueView
  },
  AddComment: {
    screen: AddComment
  },
  Submit: {
  screen: IssueSubmit
  }
}, {
    navigationOptions: {
      headerStyle: {marginTop: Expo.Constants.statusBarHeight},
      tabBarLabel: 'Issues',
      tabBarIcon: ({ tintColor }) => (
      <FontAwesome name="th-list" size={iconSize} color={tintColor} />
    )
  }
});

const prevGetStateForActionIssuesTab = IssuesTab.router.getStateForAction;
IssuesTab.router = {
  ...IssuesTab.router,
  getStateForAction(action, state) {
    if (state && action.type === 'ReplaceCurrentScreen') {
      const routes = state.routes.slice(0, state.routes.length - 1);
      routes.push(action);
      return {
        ...state,
        routes,
        index: routes.length - 1,
      };
    }
    return prevGetStateForActionIssuesTab(action, state);
  },
};





class Ticket extends React.Component {
  static navigationOptions = {
    title: 'single ticket'
  };
  constructor(props) {
    super(props);
    this.state = {
        data: {},
        loading: true
    };
  }

  componentDidMount() {
    const { params } = this.props.navigation.state;
    // axios.get('/token', {
    //     headers: {'Authorization': params.token},
    // })
    //   .then((response) => {
    //       this.setState({
    //           data: response.data,
    //           loading: false
    //       })
    //   })
    //   .catch((error) => {
    //       console.log("error")
    //       console.log(error)
    //   });
  }

  render () {
    if(this.state.loading) {
        return <View>
          <Text>token: {this.props.navigation.state.params.token}</Text>
          <ActivityIndicator />
        </View>;
    }

    return (
        <View style={styles.container}>
          <Text>Votes available: {this.state.data.points} </Text>
        </View>
    );
  }
}

class Tickets extends React.Component {
  static navigationOptions = {
    title: 'voting tickets'
  };
  constructor(props) {
    super(props)
    this.state = {
        loading: true
    };
  }
  componentDidMount = () => {
    const { params } = this.props.navigation.state;

    if(params && params.token) {
      console.log(params)
      this.props.navigation.navigate('TicketSingle', {token: params.token})
    }
  }

  static navigationOptions = {
    tabBarLabel: 'Tickets',
    tabBarIcon: ({ tintColor }) => (
      <FontAwesome name="ticket" size={iconSize} color={tintColor} />
    ),
  };

  componentDidMount = () => {
      let tokens = [
          {'token': 'sampleVotingToken'}
      ];
      this.setState({
          data: tokens
      });
  }

  _keyExtractor = (item, index) => item.token;

  render() {
    return (
        <DefaultContainer>
          <FlatList
              data={this.state.data}
              keyExtractor={this._keyExtractor}
              renderItem={(rowData) =>{ 
                console.log(rowData)
                return <TouchableHighlight onPress={() => this.props.navigation.navigate('TicketSingle', {token: rowData.item.token})}>
                <View style={styles.row}>
                  <View style={{flex:5}}><Text>{rowData.item.token}</Text></View>
                </View>
              </TouchableHighlight>}}
          />
        </DefaultContainer>
    );
  }
}

const TicketsTab = StackNavigator({
    TicketsList: {
      screen: Tickets,
    },
    TicketSingle: {
      screen: Ticket,
      path: 'ticket/:token'
    }
}, {
    navigationOptions: {
        title: 'voting tickets',
        headerStyle: {marginTop: Expo.Constants.statusBarHeight},
        tabBarLabel: 'Tickets',
        tabBarIcon: ({ tintColor }) => (
            <FontAwesome name="th-list" size={iconSize} color={tintColor} />
        ),
    }
});



class Settings extends React.Component {
  static navigationOptions = {
    tabBarLabel: 'Settings',
    tabBarIcon: ({ tintColor }) => (
      <FontAwesome name="cog" size={iconSize} color={tintColor} />
    ),
  };

  // componentDidMount() {
  //   Linking.getInitialURL().then((urla) => {
  //     const url = 'yait://yait/ticket/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ';
  //     if (url) {
  //       Alert.alert(
  //         'external url',
  //         url);
  //       const matches = url.match(/yait\/ticket\/([A-Za-z0-9-_=]+\.[A-Za-z0-9-_=]+\.?[A-Za-z0-9-_.+\/=]*)/);
  //       if(matches) {
  //         const token = matches[1];
  //         this.props.navigation.navigate('Tickets', {}, NavigationActions.navigate({
  //           routeName: 'TicketSingle',
  //           params: {token}
  //         }))
  //         console.log(matches[1]);
  //       }
  //       // console.log('Initial url is: ' + url);
  //     }
  //   }).catch(err => console.error('An error occurred', err));
  // }

  render() {
    return (
      <DefaultContainer>
        <Button onPress={this.props.screenProps.logout} title="Log out" />
      </DefaultContainer>
    );
  }
}

class LoginView extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      login: 'pracow1',
      password: 'Haslo1234!',
      wrongCredentials: false
    };
  }

  focusNextField = (nextField) => {
    this.refs[nextField].focus();
  };

  login = () => {
    axios.post('/login', {
      login: this.state.login, // TODO
      password: this.state.password
    })
    .then((response) => {
      this.props.saveNewToken(response.data.authenticationToken);
    })
    .catch((error) => {
      this.setState({
        wrongCredentials: true,
        password: ''
      })
    });
  }

  render() {
    return (
      <DefaultContainer>
        <View style={{alignItems: 'center', justifyContent: 'center', flexDirection: 'row', paddingTop: 80}}>
          <View style={{padding: 20}}><FontAwesome name="user-o" size={100} color="#999" /></View>
          <View style={{padding: 20}}><FontAwesome name="ticket" size={100} color="#999" /></View>
        </View>
          {this.state.wrongCredentials && <Text> Wrong credentials. Please try again </Text>}
          <TextInput
            ref="1"
            style={{height: 40, borderColor: 'gray', borderWidth: 1}}
            onChangeText={(login) => this.setState({login})}
            value={this.state.login}
            returnKeyType="next"
            onSubmitEditing={() => this.focusNextField('2')}
            keyboardType="email-address"
          />
          <TextInput
            ref="2"
            style={{height: 40, borderColor: 'gray', borderWidth: 1}}
            onChangeText={(password) => this.setState({password})}
            value={this.state.password}
            secureTextEntry={true}
            returnKeyType="done"
          />
          <Button title="log in" onPress={this.login} />
      </DefaultContainer>
    );
  }
}

const LoggedinNav = TabNavigator({
  Issues: {
    screen: IssuesTab
  },
  Tickets: {
    screen: TicketsTab
  },
  Settings: {
    screen: Settings
  }
}, {
  tabBarPosition: 'bottom',
  tabBarOptions: {
    showIcon: true,
    showLabel: false
  },
});

export default class NavWrapper extends React.Component {
  getChildContext() {
    return {user: this.state.user};
  }

  constructor(props) {
    super(props);
    this.state = {
      loading: true,
      user: {}
    };
  }

  componentWillMount() {
    store
      .get('loginToken')
      .then(
        (loginToken) => {
          if(loginToken) {
            axios.defaults.headers.common['Authorization'] = loginToken;
            this.setState({loggedIn: true, loading: false, user: jwtDecode(loginToken)})
          } else {
            this.setState({loggedIn: false, loading: false})
          }
        });
  }

  saveNewToken = (token) => {
    store.save('loginToken', token);
    axios.defaults.headers.common['Authorization'] = token;
    this.setState({loggedIn: !!token});
    if(token) {
      this.setState({user: jwtDecode(token)})
    }
  }

  logout = () => {
    this.saveNewToken(null);
  }

  render() {
    if(this.state.loading) {
      return <DefaultContainer><ActivityIndicator /></DefaultContainer>;
    }
    if(this.state.loggedIn) {
      return <LoggedinNav uriPrefix={prefix} screenProps={{logout: this.logout}} />;
    } else {
      return <LoginView saveNewToken={this.saveNewToken} />;
    }
  }
}

NavWrapper.childContextTypes = {
  user: PropTypes.object
};
