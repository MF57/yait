import React from 'react';
import { StyleSheet, Text, View, Button, Image, ListView, StatusBar, TouchableHighlight, TextInput, KeyboardAvoidingView, ScrollView } from 'react-native';
import { KeyboardAwareScrollView } from 'react-native-keyboard-aware-scroll-view'
import Expo from 'expo';
import { FontAwesome } from '@expo/vector-icons';
import { StackNavigator, TabNavigator } from 'react-navigation';
import store from 'react-native-simple-store';
import axios from 'axios';

const iconSize = 24;
axios.defaults.baseURL = 'http://yait.lagiewka.pl';

const styles = StyleSheet.create({
  container: {
    marginTop: Expo.Constants.statusBarHeight,
    paddingHorizontal: 20,
    paddingTop: 20,
  },
  row: {
    flex: 1,
    padding: 12,
    flexDirection: 'row',
    alignItems: 'center',
  },
  separator: {
     flex: 1,
     height: StyleSheet.hairlineWidth,
     backgroundColor: '#8E8E8E',
  },
  votes: {
    flex: 1,
    alignSelf: 'flex-end'
  }
});

class DefaultContainer extends React.Component {
  render() {
    return (
      <KeyboardAwareScrollView style={styles.container} extraScrollHeight={50}>
        {this.props.children}
      </KeyboardAwareScrollView>
    );
  }
}

class IssueView extends React.Component {
  static navigationOptions = ({navigation}) => ({
      title: `${navigation.state.params.title}`,
  });

  constructor(props) {
    super(props);
    this.state = {
      loaded: false,
      data: {}
    };
  }

  componentDidMount() {
    const { params } = this.props.navigation.state;
    axios.get('/issues/' + params.issueId)
    .then((response) => {
      this.setState({
        data: response.data
      })
    })
    .catch((error) => {
      console.log("error")
      console.log(error)
    });
  }

  render() {
    return (
      <View style={{padding: 10}}>
        <Text>Test - {this.state.data.description} </Text>
      </View>
    );
  }
}

class IssuesList extends React.Component {
  static navigationOptions = {
    title: 'Issues'
  };

  constructor(props) {
    super(props);
    this.ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
    this.state = {
      dataSource: this.ds.cloneWithRows([]),
      loading: true
    };
  }

  componentDidMount() {
    axios.get('/issues')
    .then((response) => {
      this.setState({
        dataSource: this.ds.cloneWithRows(response.data)
      })
    })
    .catch((error) => {
      console.log("error")
      console.log(error)
    });
  }

  render() {
    return (
        <ListView
          dataSource={this.state.dataSource}
          renderRow={(rowData) => <TouchableHighlight onPress={() => this.props.navigation.navigate('Single', {issueId: rowData.id, title: rowData.title})}>
            <View style={styles.row}>
              <View style={{flex:5}}><Text>{rowData.title}</Text></View>
              <View style={styles.votes}>
                <Text style={{textAlign: 'right'}}>{rowData.score}</Text>
              </View>
            </View>
          </TouchableHighlight>}
          renderSeparator={(sectionId, rowId) => <View key={rowId} style={styles.separator} />}
        />
    );
  }
}

const IssuesTab = StackNavigator({
  List: {
    screen: IssuesList,
  },
  Single: { 
    screen: IssueView
  }
}, {
  navigationOptions: {
    headerStyle: {marginTop: Expo.Constants.statusBarHeight},
    tabBarLabel: 'Issues',
    tabBarIcon: ({ tintColor }) => (
      <FontAwesome name="th-list" size={iconSize} color={tintColor} />
    ),
  }
});

class Tickets extends React.Component {
  static navigationOptions = {
    tabBarLabel: 'Tickets',
    tabBarIcon: ({ tintColor }) => (
      <FontAwesome name="ticket" size={iconSize} color={tintColor} />
    ),
  };

  render() {
    return (
      <DefaultContainer>
        <View style={{alignItems: 'center', padding: 12}}>
          <View style={{paddingTop: 50, paddingBottom: 30}}>
            <FontAwesome name="ticket" size={200} color="#999" />
          </View>
          <View>
            <Text style={{fontSize: 18, textAlign: 'center'}}>No active voting ticket. Please click on the voting link on this device.</Text>
          </View>
        </View>
      </DefaultContainer>
    );
  }
}

class Settings extends React.Component {
  static navigationOptions = {
    tabBarLabel: 'Settings',
    tabBarIcon: ({ tintColor }) => (
      <FontAwesome name="cog" size={iconSize} color={tintColor} />
    ),
  };

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
      login: 'tester',
      password: 'password123',
      wrongCredentials: false
    };
  }

  focusNextField = (nextField) => {
    this.refs[nextField].focus();
  };

  login = () => {
    axios.post('/login', {
      user: this.state.login, // TODO
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
    screen: IssuesTab,
  },
  Notifications: {
    screen: Tickets,
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
  constructor(props) {
    super(props);
    this.state = {};
  }

  componentWillMount() {
    store
      .get('loginToken')
      .then(
        (loginToken) => {
          if(loginToken) {
            axios.defaults.headers.common['Authorization'] = loginToken;
            this.setState({loggedIn: true})
          } else {
            this.setState({loggedIn: false})
          }
        });
  }

  saveNewToken = (token) => {
    store.save('loginToken', token);
    axios.defaults.headers.common['Authorization'] = token;
    this.setState({loggedIn: !!token});
  }

  logout = () => {
    this.saveNewToken(null);
  }

  render() {
    if(this.state.loggedIn) {
      return <LoggedinNav screenProps={{logout: this.logout}} />;
    } else {
      return <LoginView saveNewToken={this.saveNewToken} />;
    }
  }
}
