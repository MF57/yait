import React, {Component} from "react";
import {connect} from "react-redux";
import * as axios from "axios";
import {addComment} from "./actions/CommentActions";

let createHandlers = function (dispatch) {
    let submitComment = function (e) {
        e.preventDefault();
        e.stopPropagation();

        let newComment = {
            text: this.inputs.comment
        };

        axios.post('/api/v1/issues/' + this.props.id + '/comments', newComment)
            .then(function (response) {
                console.log(response);
                dispatch(addComment(response.data))
            }.bind(this))
            .catch(function (error) {
                console.log(error);
            }.bind(this))

    };
    return {
        submitComment
    };
};


class CommentForm extends Component {

    constructor(props) {
        super(props);
        this.handlers = createHandlers(this.props.dispatch);
        this.inputs = {};
        this.handleCommentChange = this.handleCommentChange.bind(this);
    }
      handleCommentChange(event) {
        this.inputs.comment = event.target.value
      }

    render() {
        return (
          <div className="col-md-10 col-md-offset-1 input-group">
                  <textarea className="form-control noresize" style={{resize: "none"}} onChange={this.handleCommentChange} placeholder="Comment"
                        rows="5" id="comment"/>
                  <span onClick={this.handlers.submitComment.bind(this)} className="input-group-addon btn btn-primary">SEND</span>

          </div>
        )
    }
}

export default connect()(CommentForm);
