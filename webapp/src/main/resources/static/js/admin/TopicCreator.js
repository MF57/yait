import {connect} from "react-redux";
import React, {Component} from "react";
import * as axios from "axios";

let createHandlers = function (dispatch, authorizationToken) {
    let createTopic = function (e) {
        e.preventDefault();
        e.stopPropagation();

        let newIssue = {
            title: this.inputs.title,
            description: this.inputs.description
        };


        axios.post('/api/v1/issues', newIssue, {
            headers: {'Authorization': authorizationToken}
        })
            .then(function (response) {
                this.setState({success: true});
                this.setState({error: false});
            }.bind(this))
            .catch(function (error) {
                this.setState({error: true});
                this.setState({success: false});
            }.bind(this))

    };
    return {
        createTopic
    };
};

class TopicCreator extends Component {

    constructor(props) {
        super(props);
        this.handlers = createHandlers(this.props.dispatch, this.props.login.authorizationToken);
        this.inputs = {};
        this.state = {};
        this.handleDescriptionChange = this.handleDescriptionChange.bind(this);
        this.handleTitleChange = this.handleTitleChange.bind(this)
    }

    handleDescriptionChange(event) {
        this.inputs.description = event.target.value
    }

    handleTitleChange(event) {
        this.inputs.title = event.target.value
    }

    render() {
        return (
            <div>
                {
                    this.state.error === true ?
                        <div className="alert alert-danger" id="errorTopicAdd">
                            <strong>Error!</strong> There was a problem while adding topic
                        </div> : null

                }
                {
                    this.state.success === true ?
                        <div className="alert alert-success" id="successTopicAdd">
                            <strong>Success!</strong> Topic added successfully
                        </div> : null
                }

                <div className="row form-group">
                    <div className="col-md-10 col-md-offset-1">
                        <input type="text" onChange={this.handleTitleChange} className="form-control" placeholder="Title"
                               aria-describedby="basic-addon1"/>
                    </div>
                </div>
                <div className="row form-group">
                    <div className="col-md-10 col-md-offset-1">
                        <textarea className="form-control" onChange={this.handleDescriptionChange} placeholder="Description"
                                  rows="5" id="description"/>
                    </div>
                </div>
                <div className="row form-group">
                    <div className="col-md-10 col-md-offset-1">
                        <a href="#" onClick={this.handlers.createTopic.bind(this)} className="btn btn-primary">CREATE</a>
                    </div>
                </div>
            </div>
        )
    }
}


function mapStateToProps(state) {
    return {login: state.login}
}


export default connect(mapStateToProps)(TopicCreator);
