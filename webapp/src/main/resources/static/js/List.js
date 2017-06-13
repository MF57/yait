import React, {Component} from "react";
import Topic from "./Topic";
import {connect} from "react-redux";
import TopicModal from "./TopicModal";
import {replaceComments} from "./actions/CommentActions";
import * as axios from "axios";

class List extends Component {

    constructor(props) {
        super(props);
        this.state = {
          showModal: false,
          topic: {}
        };

    }

    renderTopics() {
        let topicList = this.topicList();
        return (
            <div className="row">
                {topicList}
                <TopicModal showModal={this.state.showModal} onHide={this.close.bind(this)} onClick={this.close.bind(this)} topic={this.state.topic} comments={this.props.comments}/>
            </div>
        )
    }

    close() {
      this.setState({showModal: false});
    }

    open(topic, dispatch) {
      axios.get('/api/v1/issues/' + topic.id + '/comments')
          .then(function (response) {
              dispatch(replaceComments(response.data));
              console.log(response);
          })
          .catch(function (error) {
              console.log(error);
          });
          this.setState({
            showModal: true,
            topic: topic
          });
    }

    topicList() {
        return this.props.topics.map((el,i)=>
            <Topic title={el.title} status={el.status} id={el.id} score={el.score} author={el.author.first_name + ' ' + el.author.last_name}
                   description={el.description} date={el.created_at} key={i} onClickTopic={this.open.bind(this)}/>
        )
    }

    render() {
        return this.renderTopics();
    }
}

function mapStateToProps(state) {
    return {
        comments: state.comments
    }
}

export default connect(mapStateToProps)(List);
