import React from 'react';
import List from './List';
import NavBar from './NavBar'
import {connect} from "react-redux";

class App extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                <NavBar/>
                <List topics={this.props.topics}/>
            </div>
        )
    }
};

function mapStateToProps(state) {
    return {topics: state.topics}
};

export default connect(mapStateToProps)(App);