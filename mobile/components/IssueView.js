import React from 'react';
import axios from 'axios';
import {ActivityIndicator, StyleSheet, Text, View} from 'react-native';

export default class IssueView extends React.Component {
  static navigationOptions = ({navigation}) => ({
      title: `${navigation.state.params.title}`,
  });

  constructor(props) {
    super(props);
    this.state = {
      data: {},
      loading: true
    };
  }

  componentDidMount() {
    const { params } = this.props.navigation.state;
    axios.get('/issues/' + params.issueId)
    .then((response) => {
      console.log(response);
      console.log(params.issueId);
      this.setState({
        data: response.data,
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
      <View style={styles.container}>
        <Text>Description: {this.state.data.description} </Text>
        <Text>Status: {this.state.data.status}</Text>
        <Text>Score: {this.state.data.score}</Text>
        <Text>Submitted by: {this.state.data.author.first_name} {this.state.data.author.last_name}</Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    padding: 10
  },
});
