import React from 'react';
import axios from 'axios';
import { StyleSheet, Text, View } from 'react-native';

export default class IssueView extends React.Component {
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
      <View style={styles.container}>
        <Text>Test - {this.state.data.description} </Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    padding: 10
  },
});
