import {connect} from "react-redux";
import React, {Component} from "react";

class Admin extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="container">
                ADMIN PAGE
            </div>
        )
    }
}

export default connect()(Admin);
