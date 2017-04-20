import React from 'react';
import { StyleSheet, Text, View, Button, Image, ListView } from 'react-native';
import Expo from 'expo';
import { FontAwesome } from '@expo/vector-icons';
import { DrawerNavigator, DrawerView, TabNavigator } from 'react-navigation';

class Issues extends React.Component {
  static navigationOptions = {
    tabBarLabel: 'Issues',
    tabBarIcon: ({ tintColor }) => (
      <FontAwesome name="th-list" size={24} color={tintColor} />
    ),
  };
   constructor() {
    super();
    const ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
    this.state = {
      dataSource: ds.cloneWithRows(['row 1', 'row 2']),
    };
  }


  render() {
    return (
      <ListView
             dataSource={this.state.dataSource}
             renderRow={(rowData) => <Text>{rowData}</Text>}
      />
    );
  }
}

class Tickets extends React.Component {
  static navigationOptions = {
    tabBarLabel: 'Tickets',
    tabBarIcon: ({ tintColor }) => (
      <FontAwesome name="ticket" size={24} color={tintColor} />

    ),
  };

  render() {
    return (
      <Button
        onPress={() => this.props.navigation.goBack()}
        title="Go back home"
      />
    );
  }
}

class Settings extends React.Component {
  static navigationOptions = {
    tabBarLabel: 'Settings',
    tabBarIcon: ({ tintColor }) => (
      <FontAwesome name="cog" size={24} color={tintColor} />

    ),
  };

  render() {
    return (
      <Button
        onPress={() => this.props.navigation.goBack()}
        title="Go back home"
      />
    );
  }
}

const styles = StyleSheet.create({
  icon: {
    width: 26,
    height: 26,
  },
});

export default TabNavigator({
  Home: {
    screen: Issues,
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
