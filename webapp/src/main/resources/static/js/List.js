import React,{Component} from 'react';
import Topic from './Topic';

class List extends Component {

    constructor(props) {
        super(props)
    }

    renderTopics() {
        return (
            <div className="container">
            <div className="row text-center">
                <Topic name="TOPIC1" status="DONE"/>
                <Topic name="TOPIC2" status="DONE"/>
                <Topic name="TOPIC3" status="DONE"/>
                <Topic name="TOPIC4" status="DONE"/>
            </div>
            </div>

        )
    }

    render() {
        return this.renderTopics();
    }
}

export default List;
