import React from 'react';
import axios from 'axios';
import { StyleSheet, Text, View, FlatList, TouchableHighlight, ActivityIndicator, Button } from 'react-native';
import {DefaultContainer} from "../App";

export default class IssuesList extends React.Component {
  static navigationOptions = {
    title: 'Issues'
  };

  constructor(props) {
    super(props);
    this.state = {
      loading: true
    };
  }

  componentDidMount() {
    this._refresh()
  }
  _refresh = () => {
    axios.get('/issues')
    .then((response) => {
      this.setState({
        loading: false,
        refreshing: false,
        data: response.data,
      })
    })
    .catch((error) => {
      console.log("error")
      console.log(error)
    });
  }

  _onRefresh = () => {
    this.setState({refreshing: true});
    this._refresh()
  }

  _keyExtractor = (item, index) => item.id;

  render() {
    if(this.state.loading) {
      return <ActivityIndicator />;
    }
    return (
      <DefaultContainer>
        <FlatList
            onRefresh={this._onRefresh}
            refreshing={this.state.refreshing}
            keyExtractor={this._keyExtractor}
            data={this.state.data}
            renderItem={(item) => {
              const rowData = item.item;
              return <TouchableHighlight onPress={() => this.props.navigation.navigate('Single', {issueId: rowData.id, title: rowData.title})}>
              <View style={styles.row}>
                <View style={{flex:5}}><Text>{rowData.title}</Text></View>
                <View style={styles.votes}>
                  <Text style={{textAlign: 'right'}}>{rowData.score}</Text>
                </View>
              </View>
            </TouchableHighlight>}}
            renderSeparator={(sectionId, rowId) => <View key={rowId} style={styles.separator} />}
        />
        <Button title="Add issue" onPress={() => this.props.navigation.navigate('Submit')}/>
      </DefaultContainer>
  );
  }
}

const styles = StyleSheet.create({
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