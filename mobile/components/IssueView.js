import React from 'react';
import axios from 'axios';
import {ActivityIndicator, StyleSheet, Text, View, ListView, Button} from 'react-native';

export default class IssueView extends React.Component {
  static navigationOptions = ({navigation}) => ({
      title: `${navigation.state.params.title}`,
  });

  constructor(props) {
    super(props);
    this.ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
    this.state = {
      data: {},
      loading: true
    };
  }

  componentDidMount() {
    const { params } = this.props.navigation.state;
    axios.get('/issues/' + params.issueId)
    .then((response) => {
      this.setState({
        data: response.data,
        dataSource: this.ds.cloneWithRows(response.data.comments),
        loading: false
      })
    })
    .catch((error) => {
      console.log("error")
      console.log(error)
    });
  }

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
        </View>
        <ListView
          dataSource={this.state.dataSource}
          renderRow={(comment) =>
            {
              return (
            <View style={styles.row}>
              <Text>Comment by: {comment.author.first_name} {comment.author.last_name}</Text>
              <Text>{comment.text}</Text>
            </View>);
            }
          }
          renderSeparator={(sectionId, rowId) => <View key={rowId} style={styles.separator} />}
        />
        <Button onPress={() => this.props.navigation.navigate('AddComment', {issueId: this.state.data.id, title: this.state.data.title})} title="Comment" />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    padding: 10
  },
});
