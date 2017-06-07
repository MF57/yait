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
              <small>{new Date(this.props.date).toLocaleString()}</small>
            </p>
            <div className="media-body">
              <h4 className="media-heading user_name">{this.props.author.login}</h4>
              {this.props.text}
            </div>
          </div>
        )
    }
}

export default connect()(Comment);
