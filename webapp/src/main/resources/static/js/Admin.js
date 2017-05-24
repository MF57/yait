import {connect} from "react-redux";
import React, {Component} from "react";
import * as axios from "axios";

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


class Admin extends Component {


    constructor(props) {
        super(props);
        this.handlers = createHandlers(this.props.dispatch);
    }

    render() {
        return (
            <div className="container">
                ADMIN PAGE <br/>
                <a href="#" onClick={this.handlers.createTopic.bind(this)} className="btn btn-primary">CREATE TOPIC</a>
            </div>
        )
    }
}

export default connect()(Admin);
