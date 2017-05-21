import React from "react";
import List from "./List";
import NavBar from "./NavBar";
import {connect} from "react-redux";
import InfoPanel from "./InfoPanel";
import {registerTokenInStore} from "./actions/TokenActions";

class App extends React.Component {

    componentWillMount() {
        let token = this.props.match.params.token;
        if (token !== undefined) {
            this.props.dispatch(registerTokenInStore(token))
        }
    }

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                <NavBar/>
                <div className="container">
                    <InfoPanel/>
                    <List topics={this.props.topics}/>
                </div>
            </div>
        )
    }
}
;

function mapStateToProps(state) {
    return {topics: state.topics}
};

export default connect(mapStateToProps)(App);