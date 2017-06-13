import React, {Component} from "react";
import {Link} from "react-router-dom";
import {connect} from "react-redux";
import {logout} from "./actions/LoginActions";

let createHandlers = function (dispatch) {
    let logoutAction = function (e) {
        e.preventDefault();
        e.stopPropagation();

        dispatch(logout());
    };
    return {
        logoutAction
    };
};


class NavBar extends Component {
    constructor(props) {
        super(props);
        this.handlers = createHandlers(this.props.dispatch);
    }
    render() {
        return (
            <nav className="navbar navbar-inverse navbar-static-top" role="navigation">
                <div className="container">
                    <div className="navbar-header">
                        <button type="button" className="navbar-toggle" data-toggle="collapse"
                                data-target="#bs-example-navbar-collapse-1">
                            <span className="sr-only">Toggle navigation</span>
                        </button>
                        <a className="navbar-brand" href="#">Yet Another Issue Tracker</a>
                    </div>
                    <div className="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul className="nav navbar-nav">
                            {
                                this.props.login.isAdmin ?
                                    <li>
                                        <Link to={'/admin'}>Admin</Link>
                                    </li>
                                    : null
                            }
                        </ul>
                        <ul className="nav navbar-nav pull-right">
                            {
                                this.props.login.isUserLogged ?
                                    <li>
                                        <p className="navbar-text">
                                        Logged as {this.props.login.username}
                                        </p>
                                    </li>
                                    : null
                            }
                            {
                                this.props.login.isUserLogged ?
                                    <li>
                                        <a href="#" className="navbar-nav" onClick={this.handlers.logoutAction.bind(this)}>Logout</a>
                                    </li>
                                :
                                    <li>
                                        <Link to={'/login'}>Login</Link>
                                    </li>
                            }
                        </ul>
                    </div>
                </div>
            </nav>
        )
    }
}
function mapStateToProps(state) {
    return {
        login: state.login
    }
}
export default connect(mapStateToProps)(NavBar);