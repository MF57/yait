import React, {Component} from "react";
import {connect} from "react-redux";
import {Button, Modal} from "react-bootstrap";
import Comment from "./Comment";
import CommentForm from "./CommentForm";

class TopicModal extends Component {

    constructor(props) {
        super(props);
        this.state = {
            comments: [
                {author: "Jan Nowak", text: "Sample comment"},
                {author: "Jan Kowalski", text: "Sample comment 2"}
            ]
        }
    }

    commentList() {
        return this.state.comments.map((el, i) =>
            <Comment author={el.author} description={el.text} key={i}/>
        )
    }


    render() {
        let commentList = this.commentList();
        return (
            <Modal show={this.props.showModal} onHide={this.props.onHide}>
                <Modal.Header closeButton>
                    <Modal.Title> by {this.props.topic.author}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <h2>[{this.props.topic.status}]{this.props.topic.name}</h2>
                    <h4>Topic description: {this.props.topic.description} </h4>
                    <p>Score: {this.props.topic.score}</p>
                    <div className="comments-list">
                        {commentList}
                    </div>
                    <CommentForm />

                </Modal.Body>
                <Modal.Footer>
                    <Button onClick={this.props.onClick}>Close</Button>
                </Modal.Footer>
            </Modal>
        )
    }
}


export default connect()(TopicModal);
