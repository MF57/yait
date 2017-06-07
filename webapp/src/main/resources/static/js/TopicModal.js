import React, {Component} from "react";
import {connect} from "react-redux";
import {Button, Modal} from "react-bootstrap";
import Comment from "./Comment";
import CommentForm from "./CommentForm";
import Infinite from 'react-infinite';

class TopicModal extends Component {

    constructor(props) {
        super(props);

    };

    commentList() {
        return this.props.comments.map((el, i) =>
            <Comment author={el.author} text={el.text} date={el.created_at} key={i}/>
        )
    };
    //TO DO
    hasUserCommented() {
        return (this.props.comments.map((el, i) => el.author.login).indexOf(this.props.login.username) != -1)
    }


    render() {
          let commentList = this.commentList();
        return (
            <Modal show={this.props.showModal} onHide={this.props.onHide}>
                <Modal.Body>
                    <h2>[{this.props.topic.score}] {this.props.topic.title}</h2>
                    <p style={{color: "grey"}}>created  {new Date(this.props.topic.date).toLocaleString()} by {this.props.topic.author}</p>
                    <p>{this.props.topic.description} </p>
                          <Infinite containerHeight={400} elementHeight={45}>
                          {commentList}
                        </Infinite>
                      <div className="row">
                          {
                              this.hasUserCommented() ?
                                  <div className="alert alert-success">
                                      You have already commented this issue!
                                  </div>
                                  : this.props.login.isUserLogged ?
                                    <CommentForm id={this.props.topic.id}/>
                                      :
                                      <div className="alert alert-warning">
                                          You are not logged in!
                                      </div>
                          }
                      </div>
                </Modal.Body>
                <Modal.Footer>
                    <Button onClick={this.props.onClick}>Close</Button>
                </Modal.Footer>
            </Modal>
        )
      }
}
function mapStateToProps(state) {
    return {
        login: state.login
    }
}
export default connect(mapStateToProps)(TopicModal);
