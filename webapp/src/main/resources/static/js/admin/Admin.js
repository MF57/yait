import {connect} from "react-redux";
import React, {Component} from "react";
import * as axios from "axios";
import TopicCreator from "./TopicCreator";
import TopicManager from "./TopicManager";

let createHandlers = function (dispatch) {
    let createTopic = function (e) {
        e.preventDefault();
        e.stopPropagation();

        let exampleIssue = {
            title: "Title",
            description: "Description"
        };

        axios.post('/api/v1/issues', exampleIssue,)
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    };
    return {
        createTopic
    };
};


class Admin
    extends Component {


    constructor(props) {
        super(props);
        this.handlers = createHandlers(this.props.dispatch);
    }

    render() {
        return (
            <div className="container">
                <div className="row">
                    <div className="col-md-6">
                        <div className="panel panel-default">
                            <div className="panel-heading">New topic</div>
                            <div className="panel-body">
                                <TopicCreator />
                            </div>
                        </div>
                    </div>
                    <div className="col-md-6">
                        <div className="panel panel-default">
                            <div className="panel-body">Create test topic</div>
                            <div className="panel-footer">
                                <a href="#" onClick={this.handlers.createTopic.bind(this)} className="btn btn-primary">CREATE
                                    TEST
                                    TOPIC</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-12">
                        <div className="panel panel-default">
                            <div className="panel-heading">Topic list</div>
                            <div className="panel-body">
                                <TopicManager />
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        )
    }
}

export default connect()(Admin);
