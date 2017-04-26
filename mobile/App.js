import React from 'react';
import { StyleSheet, Text, View, Button, Image, ListView, StatusBar, TouchableHighlight } from 'react-native';
import Expo from 'expo';
import { FontAwesome } from '@expo/vector-icons';
import { StackNavigator, TabNavigator } from 'react-navigation';
import store from 'react-native-simple-store';

const iconSize = 24;

const styles = StyleSheet.create({
  container: {
    marginTop: Expo.Constants.statusBarHeight,
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
      <View style={styles.container}>
        {this.props.children}
      </View>
    );
  }
}

class IssueView extends React.Component {
  static navigationOptions = ({navigation}) => ({
      title: `${navigation.state.params.title}`,
  });

  render() {
    const { params } = this.props.navigation.state;

    return (
      <View style={{padding: 10}}>
        <Text>Test - {params.title}</Text>
      </View>
    );
  }
}

class IssuesList extends React.Component {
  static navigationOptions = {
    title: 'Issues'
  };

  constructor() {
    super();
    const ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
    this.state = {
      dataSource: ds.cloneWithRows([
        {
          title: 'Nie ma ciepłej wody na III piętrze',
          votes: 321
        },
        {
          title: 'Teścik',
          votes: 123
        }
      ]),
    };
  }

  render() {
    return (
        <ListView
          dataSource={this.state.dataSource}
          renderRow={(rowData) => <TouchableHighlight onPress={() => this.props.navigation.navigate('Single', rowData)}>
            <View style={styles.row}>
              <View style={{flex:5}}><Text>{rowData.title}</Text></View>
              <View style={styles.votes}>
                <Text style={{textAlign: 'right'}}>{rowData.votes}</Text>
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
  render() {
    return (
      <DefaultContainer>
        <View style={{alignItems: 'center', justifyContent: 'center', flexDirection: 'row', padding: 80}}>
          <View style={{padding: 20}}><FontAwesome name="user-o" size={100} color="#999" /></View>
          <View style={{padding: 20}}><FontAwesome name="ticket" size={100} color="#999" /></View>
        </View>
        <Button onPress={this.props.login} title="Log in" />
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
    showLabel: false,
    activeTintColor: '#e91e63',
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
            this.setState({loggedIn: true})
          } else {
            this.setState({loggedIn: false})
          }
        });
  }

  saveNewToken = (token) => {
    store.save('loginToken', token);
    this.setState({loggedIn: !!token});
  }

  login = () => {
    this.saveNewToken("test");
  }

  logout = () => {
    this.saveNewToken(null);
  }

  render() {
    if(this.state.loggedIn) {
      return <LoggedinNav screenProps={{logout: this.logout}} />;
    } else {
      return <LoginView login={this.login} />;
    }
  }
}
