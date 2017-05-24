import InfoPanel from "./InfoPanel";
import List from "./List";
import React, {Component} from "react";
import {connect} from "react-redux";
import {registerTokenInStore} from "./actions/TokenActions";

class Main extends Component {

    componentWillMount() {
        let token = this.props.match.params.token;
        if (token !== undefined) {
            this.props.dispatch(registerTokenInStore(token))
        }
    }

    render() {
        return (
            <div className="container">
                <InfoPanel/>
                <List topics={this.props.topics}/>
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {topics: state.topics}
}

export default connect(mapStateToProps)(Main);