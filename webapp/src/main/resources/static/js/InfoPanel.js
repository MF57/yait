import React, {Component} from "react";
import {connect} from "react-redux";

class InfoPanel extends Component {

    constructor(props) {
        super(props)
    }

    render() {
        return (
            <div className="panel panel-default">
                <div className="panel-body">
                    Token: {this.props.token.number} <br/>
                    Points left: {this.props.token.tokenPoints}
                </div>
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        token: state.token,
    }
};

export default connect(mapStateToProps)(InfoPanel);