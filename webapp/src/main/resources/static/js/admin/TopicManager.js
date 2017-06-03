import {connect} from "react-redux";
import React, {Component} from "react";
import * as axios from "axios";
import {replaceTopics} from "../actions/TopicActions";

let createHandlers = function (dispatch) {
    let changeTopicStatus = function (status, id, e) {
        e.preventDefault();
        e.stopPropagation();

        axios.patch('/api/v1/admin/issues/' + id + '/status?status=' + status)
            .then(function (response) {
                axios.get('/api/v1/issues')
                    .then(function (response) {
                        dispatch(replaceTopics(response.data))
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            })

    };
    return {
        changeTopicStatus
    };
};

class TopicManager extends Component {

    constructor(props) {
        super(props);
        this.handlers = createHandlers(this.props.dispatch);
    }

    render() {
        let topicList = this.topicList();
        return (
            <div>
                <table className="table table-striped table-inverse">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Status</th>
                    </tr>
                    </thead>
                    <tbody>
                    {topicList}
                    </tbody>
                </table>
            </div>
        )
    }

    topicList() {
        return this.props.topics.map((el, i) =>
            <tr key={i}>
                <th scope="row">{el.id}</th>
                <td>{el.title}</td>
                <td>{el.description}</td>
                <td>{el.status}</td>
                <td>
                    <a href="#"
                       onClick={(event) => this.handlers.changeTopicStatus("resolved", el.id, event)}
                       className="btn btn-primary">RESOLVE</a>
                </td>
                <td>
                    <a href="#"
                       onClick={(event) => this.handlers.changeTopicStatus("declined", el.id, event)}
                       className="btn btn-danger">DECLINE</a>
                </td>
            </tr>
        )
    }

}

function mapStateToProps(state) {
    return {topics: state.topics}
}

export default connect(mapStateToProps)(TopicManager);
