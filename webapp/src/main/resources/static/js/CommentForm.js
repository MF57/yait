import React, {Component} from "react";
import {connect} from "react-redux";

class CommentForm extends Component {

    constructor(props) {
        super(props);
        this.state = {comment: ''};
    }

      handleChange(event) {
        this.setState({comment: event.target.value});
      }

      handleSubmit(event) {
        alert("Comment submited: " + this.state.comment);
        event.preventDefault();
      }

    render() {
        return (
          <form onSubmit={this.handleSubmit.bind(this)}>
            <label>
              Comment:
              <input type="text" value={this.state.comment} onChange={this.handleChange.bind(this)} />
            </label>

            <input type="submit" value="Submit" />
          </form>
        )
    }
}

export default connect()(CommentForm);
