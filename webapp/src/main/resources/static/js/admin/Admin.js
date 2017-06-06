import {connect} from "react-redux";
import React, {Component} from "react";
import * as axios from "axios";
import TopicManager from "./TopicManager";
import TokenGenerator from "./TokenGenerator";
import {replaceLdapGroups} from "../actions/LdapGroupsActions"

let createHandlers = function (dispatch, authorizationToken) {
    let createTopic = function (e) {
        e.preventDefault();
        e.stopPropagation();

        let exampleIssue = {
            title: "Title",
            description: "Description"
        };

        axios.post('/api/v1/issues', exampleIssue, {
            headers: {'Authorization': authorizationToken}
        })
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

    componentWillMount() {
        let dispatch = this.props.dispatch;
        axios.get('/api/v1/admin/ldapGroups')
            .then(function (response) {
                dispatch(replaceLdapGroups(response.data));
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    constructor(props) {
        super(props);
        this.handlers = createHandlers(this.props.dispatch, this.props.login.authorizationToken);
    }

    render() {
        return (
            <div className="container">
                <div className="row">
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
                            <div className="panel-heading">Generate tokens</div>
                            <div className="panel-body">
                                <TokenGenerator/>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-12">
                        <div className="panel panel-default">
                            <div className="panel-heading">Manage topics</div>
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

function mapStateToProps(state) {
    return {login: state.login}
}

export default connect(mapStateToProps)(Admin);
