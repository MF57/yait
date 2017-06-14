import InfoPanel from "./InfoPanel";
import List from "./List";
import React, {Component} from "react";
import {connect} from "react-redux";
import {registerTokenInStore} from "./actions/TokenActions";
import {replaceTopics} from "./actions/TopicActions";
import * as axios from "axios";
import TopicCreator from "./admin/TopicCreator";
import {votingTokenInUse} from "./actions/LoginActions";

class Main extends Component {

    componentWillMount() {
        let dispatch = this.props.dispatch;

        let token = this.props.match.params.token;
        if (token !== undefined) {
            dispatch(registerTokenInStore(token));
            dispatch(votingTokenInUse())
        }

        axios.get('/api/v1/issues')
            .then(function (response) {
                dispatch(replaceTopics(response.data))
            })
            .catch(function (error) {
                console.log(error);
            });

    }

    render() {
        return (
            <div className="container">
                <div className="row">
                    <div className="col-md-6">
                        {
                            this.props.login.isUserLogged === true ?
                                <div className="panel panel-default">
                                    <div className="panel-heading">New topic
                                        <button className="btn btn-xs pull-right" data-toggle="collapse"
                                                data-target="#newTopic">+
                                        </button>
                                    </div>
                                    <div id="newTopic" className="panel-body collapse">
                                        <TopicCreator />
                                    </div>
                                </div>
                                : null
                        }
                    </div>
                    <div className="col-md-6">
                        {
                            this.props.login.isUserLogged === true ?
                                <div className="panel panel-default">
                                    <div className="panel-heading">Token
                                        <button className="btn btn-xs pull-right" data-toggle="collapse"
                                                data-target="#token">+
                                        </button>
                                    </div>
                                    <div className="panel-body collapse" id="token">
                                        <InfoPanel/>
                                    </div>
                                </div>
                                : null
                        }
                    </div>
                </div>
                <div className="row">
                    <List topics={this.props.topics}/>
                </div>
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        topics: state.topics,
        login: state.login
    }
}

export default connect(mapStateToProps)(Main);
