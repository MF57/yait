import React, {Component} from "react";
import {connect} from "react-redux";
import {upvoteTopic} from "./actions/TopicActions";
import * as axios from "axios";

let createHandlers = function (dispatch) {
    let onClickUpvote = function (id, e) {
        e.preventDefault();
        e.stopPropagation();
        let points = this.inputs.points;
        axios.post('http://localhost:8090/api/v1/issues/' + id + '/vote', {"points": points})
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
            <div className="col-md-12 hero-feature" onClick={this.props.onClickTopic.bind(this, topic, this.props.dispatch)}>
              <div className="panel panel-primary">
                <div className="panel-heading">
                  <h3 className="panel-title">{this.props.title}</h3>
                </div>
                <div className="panel-body">
                  <div className="col-md-2"> Score: {this.props.points} </div>
                  <div className="col-md-2"> Status: {this.props.status} </div>
                  <div className="col-md-2"> Author: {this.props.author} </div>
                  <div className="col-md-4"> Created: {new Date(this.props.date).toLocaleString()} </div>
                  <div className="col-md-2 pull-right input-group">
                    <input type="text form-control" onChange={this.handlePointsChange} onClick={this.handleInputClick} className="form-control" placeholder="1"
                           aria-describedby="basic-addon1"/>
                    <span onClick={this.handlers.onClickUpvote.bind(this, this.props.id)}
                       className="input-group-addon btn btn-primary">UPVOTE</span>
                  </div>
                </div>
            </div>
          </div>
        )
    }
}

export default connect()(Topic);
