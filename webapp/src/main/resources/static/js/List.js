import React, {Component} from "react";
import Topic from "./Topic";
import {connect} from "react-redux";
import TopicModal from "./TopicModal"

class List extends Component {

    constructor(props) {
        super(props);
        this.state = {
          showModal: false,
          topic: {}};
    }

    renderTopics() {
        let topicList = this.topicList();
        return (
            <div className="row text-center">
                {topicList}
                <TopicModal showModal={this.state.showModal} onHide={this.close.bind(this)} onClick={this.close.bind(this)} topic={this.state.topic}/>
            </div>
        )
    }

    close() {
      this.setState({
        showModal: false
       });
    }

    open(topic) {
      this.setState({
        showModal: true,
        topic: topic });
    }

    topicList() {
        return this.props.topics.map((el,i)=>
            <Topic name={el.name} status={el.status} id={el.id} score={el.score} author={el.author} description={el.description} key={i} onClickTopic={this.open.bind(this)}/>
        )
    }

    render() {
        return this.renderTopics();
    }
}

export default connect()(List);
