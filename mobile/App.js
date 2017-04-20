import React from 'react';
import { StyleSheet, Text, View, Button, Image, ListView, StatusBar, TouchableHighlight } from 'react-native';
import Expo from 'expo';
import { FontAwesome } from '@expo/vector-icons';
import { StackNavigator, TabNavigator } from 'react-navigation';

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
  render() {
    const { params } = this.props.navigation.state;

    return (
      <DefaultContainer>
        <Text>Test - {params.title}</Text>
      </DefaultContainer>
    );
  }
}

class IssuesList extends React.Component {
   constructor() {
    super();
    const ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
    this.state = {
      dataSource: ds.cloneWithRows([
        {
          title: 'Nie ma ciepłej wody na III piętrze',
          votes: 123
        },
        {
          title: 'Teścik',
          votes: 321
        }
      ]),
    };
  }

  render() {
    return (
      <DefaultContainer>
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
      </DefaultContainer>
    );
  }
}

const IssuesTab = StackNavigator({
  List: {
    screen: IssuesList,
    navigationOptions: {headerVisible: false}
  },
  Single: { 
    screen: IssueView,
    headerVisible: true
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
      </DefaultContainer>
    );
  }
}

export default TabNavigator({
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