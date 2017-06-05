import React from 'react';
import axios from 'axios';
import {ActivityIndicator, StyleSheet, Text, View, FlatList, Button} from 'react-native';
import store from 'react-native-simple-store';
const PropTypes = require('prop-types');

export default class IssueView extends React.Component {
  static navigationOptions = ({navigation}) => ({
      title: `${navigation.state.params.title}`,
  });

  constructor(props) {
    super(props);
    this.state = {
      data: {},
      loading: true,
      refreshing: false
    };
  }

  componentDidMount() {
    this._refresh()
  }

  _refresh = () => {
    const { params } = this.props.navigation.state;
    axios.get('/issues/' + params.issueId)
    .then((response) => {
      console.log(response.data);
      this.setState({
        data: response.data,
        loading: false,
        refreshing: false
      })
    })
    .catch((error) => {
      console.log("error")
      console.log(error)
    });
  }

  vote = () => {
    axios.post('/issues/' + this.state.data.id + '/vote', {
        points: 1,
    }, {
      headers: {'Authorization': store.get('voting_token')}
    })
    .then((response) => {
      console.log(response);
    }).catch((error) => {
      console.log(error);
    })
  };

  _onRefresh = () => {
    this.setState({refreshing: true});
    this._refresh()
  }

  addCommentButton = () => {
    var foundCurrentUserComment = this.state.data.comments.find((comment) => comment.author.login === 0);
    if(foundCurrentUserComment) {
      return null;
    }
    return <Button
              onPress={() => this.props.navigation.navigate('AddComment', {issueId: this.state.data.id, title: this.state.data.title, goBack: this._refresh})}
              title="Comment"
            />;
  }
  _keyExtractor = (item, index) => item.id;

  render() {
    if(this.state.loading) {
      return <ActivityIndicator />;
    }
    return (
      <View>
        <View style={styles.container}>
          <Text>Description: {this.state.data.description} </Text>
          <Text>Status: {this.state.data.status}</Text>
          <Text>Score: {this.state.data.score}</Text>
          <Text>Submitted by: {this.state.data.author.first_name} {this.state.data.author.last_name}</Text>
          <Button title="Vote!" onPress={this.vote}/>
        </View>
        <FlatList
          onRefresh={this._onRefresh}
          refreshing={this.state.refreshing}
          keyExtractor={this._keyExtractor}
          data={this.state.data.comments}
          renderItem={(item) =>
            {
              const comment = item.item;
              return (
            <View style={styles.row}>
              <Text>Comment by: {comment.author.first_name} {comment.author.last_name}</Text>
              <Text>{comment.text}</Text>
            </View>);
            }
          }
        />
        {this.addCommentButton()}
      </View>
    );
  }
}

IssueView.contextTypes = {
  user: PropTypes.object
};

const styles = StyleSheet.create({
  container: {
    padding: 10
  },
});
