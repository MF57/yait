import {connect} from "react-redux";
import React, {Component} from "react";

class Login extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="container">
               LOGIN PAGE
            </div>
        )
    }
}

export default connect()(Login);
