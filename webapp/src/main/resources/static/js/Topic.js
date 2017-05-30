import React, {Component} from "react";
import {connect} from "react-redux";
import {upvoteTopic} from "./actions/TopicActions";
import * as axios from "axios";

let createHandlers = function (dispatch) {
    let onClickUpvote = function (id, e) {
        e.preventDefault();
        e.stopPropagation();
        let points = this.inputs.points;
        axios.post('/api/v1/issues/' + id + '/vote', {"points": points})
            .then(function (response) {
              console.log(response);
              dispatch(upvoteTopic(id, points));
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
        this.handlers = createHandlers(this.props.dispatch);
        this.inputs = {};
        this.handlePointsChange = this.handlePointsChange.bind(this);
        this.handleInputClick = this.handleInputClick.bind(this);
    };

    handlePointsChange(event) {
        this.inputs.points = event.target.value
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
            points: this.props.points,
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
                        <p> Score: {this.props.points} </p>
                        <input type="number" onChange={this.handlePointsChange} onClick={this.handleInputClick} className="form-control" placeholder="1"
                               aria-describedby="basic-addon1" min={1}/>
                        <button onClick={this.handlers.onClickUpvote.bind(this, this.props.id)}
                              className="btn btn-primary glyphicon glyphicon-arrow-up col-xs-12"><span>UP</span></button>
                    </div>
                </div>
                <hr/>
            </div>
        )
    }
}

export default connect()(Topic);
