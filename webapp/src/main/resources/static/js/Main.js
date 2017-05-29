import InfoPanel from "./InfoPanel";
import List from "./List";
import React, {Component} from "react";
import {connect} from "react-redux";
import {registerTokenInStore} from "./actions/TokenActions";
import {replaceTopics} from "./actions/TopicActions";
import * as axios from "axios";

class Main extends Component {

  componentWillMount() {
        let dispatch = this.props.dispatch;

        let token = this.props.match.params.token;
        if (token !== undefined) {
            dispatch(registerTokenInStore(token))
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
                <InfoPanel/>
                <List topics={this.props.topics}/>
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {topics: state.topics}
}

export default connect(mapStateToProps)(Main);
