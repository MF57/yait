import React from 'react';
import axios from 'axios';
import {ActivityIndicator, StyleSheet, Text, View, TextInput, Button} from 'react-native';
import { NavigationActions } from 'react-navigation'

export default class AddComment extends React.Component {
  static navigationOptions = ({navigation}) => ({
      title: `${navigation.state.params.title}`,
  });

  constructor(props) {
    super(props);
    this.state = {
      body: '',
      loading: false
    };
  }

  addComment = () => {
    const {issueId,title, goBack} = this.props.navigation.state.params
    this.setState({loading: true})
    axios.post('/issues/' + issueId + '/comments', {
      text: this.state.body
    })
    .then((response) => {
      goBack();
      this.props.navigation.goBack()
    })
    .catch((error) => {
      console.log(error)
    });
  }

  render() {
    if(this.state.loading) {
      return <ActivityIndicator />;
    }
    return (
      <View style={styles.container}>
        <TextInput
          multiline={true}
          numberOfLines={10}
          onChangeText={(body) => this.setState({body})}
          value={this.state.body}
          style={styles.input}
          returnKeyType="done"
        />
        <Button onPress={() => this.addComment()} title="Submit comment" />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    padding: 10
  },
});
