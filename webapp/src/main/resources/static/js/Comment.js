import React, {Component} from "react";
import {connect} from "react-redux";

class Comment extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
          <div className="media">
            <p className="pull-right">
              <small>5 days ago</small>
            </p>
            <a className="media-left" href="#">
              <img src="http://lorempixel.com/40/40/people/1/"/>
            </a>
            <div className="media-body">
              <h4 className="media-heading user_name">{this.props.author}</h4>
              {this.props.description}
            </div>
          </div>
        )
    }
}

export default connect()(Comment);
