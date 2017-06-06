import React, {Component} from "react";
import {connect} from "react-redux";

class InfoPanel extends Component {

    constructor(props) {
        super(props)
    }

    render() {
        return (
            <div >
                    Token: {this.props.token.number} <br/>
                    Points left: {this.props.token.tokenPoints}
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        token: state.votingToken
    }
}

export default connect(mapStateToProps)(InfoPanel);