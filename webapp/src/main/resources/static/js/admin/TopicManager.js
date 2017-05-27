import {connect} from "react-redux";
import React, {Component} from "react";
import * as axios from "axios";

let createHandlers = function (dispatch) {
    let closeTopic = function (id, e) {
        e.preventDefault();
        e.stopPropagation();


        //fixme no possible API endpoint to close issue
        // let newIssue = {
        //     title: this.inputs.title,
        //     description: this.inputs.description
        // };


        // axios.post('/api/v1/issues', newIssue)
        //     .then(function (response) {
        //         this.setState({success: true});
        //         this.setState({error: false});
        //     }.bind(this))
        //     .catch(function (error) {
        //         this.setState({error: true});
        //         this.setState({success: false});
        //     }.bind(this))

    };
    return {
        closeTopic
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
                    <td>
                        <a href="#"
                            onClick={this.handlers.closeTopic.bind(el.id, this)}
                           className="btn btn-danger">CLOSE TOPIC</a>
                    </td>
                </tr>
        )
    }

}

function mapStateToProps(state) {
    return {topics: state.topics}
}

export default connect(mapStateToProps)(TopicManager);
