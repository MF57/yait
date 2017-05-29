import React, {Component} from "react";
import {connect} from "react-redux";
import {Button, Modal} from "react-bootstrap";
import Comment from "./Comment";
import CommentForm from "./CommentForm";
import Infinite from 'react-infinite';
import {upvoteTopic} from "./actions/TopicActions";
import {addCOmment} from "./actions/CommentActions";

class TopicModal extends Component {

    constructor(props) {
        super(props);

    };

    commentList() {
        return this.props.comments.map((el, i) =>
            <Comment author={el.author} description={el.comment} date={el.creationDate} key={i}/>
        )
    };


    render() {
          let commentList = this.commentList();
        return (
            <Modal show={this.props.showModal} onHide={this.props.onHide}>
                <Modal.Body>
                    <h2>[{this.props.topic.points}] {this.props.topic.title}</h2>
                    <p style={{color: "grey"}}>wysłane  {new Date(this.props.topic.date).toLocaleString()} przez {this.props.topic.author}</p>
                    <p>{this.props.topic.description} </p>
                          <Infinite containerHeight={400} elementHeight={45}>
                          {commentList}
                        </Infinite>
                      <div className="row">
                        <CommentForm id={this.props.topic.id}/>
                      </div>

                </Modal.Body>
                <Modal.Footer>
                    <Button onClick={this.props.onClick}>Close</Button>
                </Modal.Footer>
            </Modal>
        )
      }
}

export default connect()(TopicModal);
