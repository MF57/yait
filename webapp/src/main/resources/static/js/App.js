import React from "react";
import NavBar from "./NavBar";
import {connect} from "react-redux";
import {Route, Switch} from "react-router";
import Login from "./Login";
import Main from "./Main";
import {withRouter} from "react-router-dom";
import Admin from "./admin/Admin";

class App extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                <NavBar/>
                <Switch>
                    <Route path='/login' component={Login}/>
                    <Route path='/admin' component={Admin}/>
                    <Route path='/:token?' component={Main}/>
                </Switch>
            </div>
        )
    }
}

export default withRouter(connect()(App));