import React, {Component} from "react";
import {connect} from "react-redux";
import {upvoteTopic} from "./actions/TopicActions";

let createHandlers = function (dispatch) {
    let onClickUpvote = function (id, e) {
        e.preventDefault();
        e.stopPropagation();

        dispatch(upvoteTopic(id));
    };
    return {
        onClickUpvote
    };
};

class Topic extends Component {

    constructor(props) {
        super(props);
        this.handlers = createHandlers(this.props.dispatch);
    };

    render() {
        let topic = {
            name: this.props.name,
            status: this.props.status,
            score: this.props.score,
            author: this.props.author,
            description: this.props.description,
        };
        return (
            <div className="col-md-3 col-sm-6 hero-feature" onClick={this.props.onClickTopic.bind(this, topic)}>
                <div className="thumbnail">
                    <img src="http://placehold.it/800x500" alt=""/>
                    <div className="caption">
                        <h3>{this.props.title}</h3>
                        <p>{this.props.status}</p>
                        <p>{this.props.score}</p>
                        <p>
                            <a href="#" onClick={this.handlers.onClickUpvote.bind(this, this.props.id)}
                               className="btn btn-primary">UPVOTE</a>
                        </p>
                    </div>
                </div>
            </div>
        )
    }
}

export default connect()(Topic);
