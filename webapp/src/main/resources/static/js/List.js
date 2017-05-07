import React,{Component} from 'react';
import Topic from './Topic';
import {connect} from "react-redux";

class List extends Component {

    constructor(props) {
        super(props)
    }

    renderTopics() {
        let topicList = this.topicList();
        return (
            <div className="container">
            <div className="row text-center">
                {topicList}
            </div>
            </div>

        )
    }

    topicList() {
        return this.props.topics.map((el,i)=>
            <Topic name={el.name} status={el.status} id={el.id} score={el.score} key={i}/>
        )
    }

    render() {
        return this.renderTopics();
    }
}

export default connect()(List);
