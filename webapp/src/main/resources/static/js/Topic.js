import React, {Component} from "react";
import {connect} from "react-redux";
import {upvoteTopic} from "./actions/TopicActions";
import * as axios from "axios";

let createHandlers = function (dispatch, votingToken) {
    let onClickUpvote = function (id, e) {
        e.preventDefault();
        e.stopPropagation();
        let score = this.inputs.score;
        axios.post('/api/v1/issues/' + id + '/vote', {"points": score}, {
            headers: {'Authorization': votingToken}
        })
            .then(function (response) {
                console.log(response);
                dispatch(upvoteTopic(id, score));
            })
            .catch(function (error) {
                console.log(error);
            });
    };
    return {
        onClickUpvote
    };
};

class Topic extends Component {

    constructor(props) {
        super(props);
        this.handlers = createHandlers(this.props.dispatch, this.props.votingToken.number);
        this.inputs = {};
        this.handleScoreChange = this.handleScoreChange.bind(this);
        this.handleInputClick = this.handleInputClick.bind(this);
    };

    handleScoreChange(event) {
        this.inputs.score = event.target.value
    }

    handleInputClick(event) {
        event.preventDefault();
        event.stopPropagation();
    }

    render() {
        let topic = {
            id: this.props.id,
            title: this.props.title,
            status: this.props.status,
            score: this.props.score,
            author: this.props.author,
            description: this.props.description,
            date: this.props.date
        };
        return (
            <div>
                <div className="row" onClick={this.props.onClickTopic.bind(this, topic, this.props.dispatch)}>
                    <div className="col-xs-1 media-middle text-center">
                        <a href="#">
                            <img src="http://placehold.it/100x100" alt=""/>
                        </a>
                    </div>
                    <div className="col-xs-9 col-xs-offset-1">
                        <h4>{this.props.title}</h4>
                        <p> Author: {this.props.author}</p>
                        <p> Created: {new Date(this.props.date).toLocaleString()} </p>
                    </div>
                    <div className="col-xs-1 text-center media-middle">
                        <p> Score: {this.props.score} </p>
                        {
                        this.props.login.isTokenBeingUsed === true ?
                            <div>
                                <input type="number" onChange={this.handleScoreChange} onClick={this.handleInputClick}
                                       className="form-control" placeholder="1"
                                       aria-describedby="basic-addon1" min={1}/>
                                <button onClick={this.handlers.onClickUpvote.bind(this, this.props.id)}
                                        className="btn btn-primary glyphicon glyphicon-arrow-up col-xs-12"><span>VOTE</span>
                                </button>
                            </div> : null
                        }
                    </div>
                </div>
                <hr/>
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        login: state.login,
        votingToken: state.votingToken
    }
}

export default connect(mapStateToProps)(Topic);
