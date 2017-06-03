import {connect} from "react-redux";
import React, {Component} from "react";
import * as axios from "axios";

let createHandlers = function (dispatch) {
    let login = function (e) {
        e.preventDefault();
        e.stopPropagation();

        let login = {
            login: this.inputs.login,
            password: this.inputs.password
        };

        axios.post('/api/v1/login', login)
            .then(function (response) {
                console.log(response)
            }.bind(this))
            .catch(function (error) {
                console.log(error)
            }.bind(this))

    };
    return {
        login
    };
};


class Login extends Component {

    constructor(props) {
        super(props);
        this.handlers = createHandlers();
        this.inputs = {};
    }

    handleUserNameChange(event) {
        this.inputs.login = event.target.value
    }

    handlePasswordChange(event) {
        this.inputs.password = event.target.value
    }

    render() {
        return (
            <div className="container">
                <div id="loginbox" style={{marginTop: '50px'}}
                     className="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                    <div className="panel panel-info">
                        <div className="panel-heading">
                            <div className="panel-title">Sign In</div>
                        </div>
                        <div style={{paddingTop: '30px'}} className="panel-body">
                            {/*<div style={{display: 'none'}} id="login-alert" className="alert alert-danger col-sm-12"></div>*/}
                            <form id="loginform" className="form-horizontal" role="form">
                                <div style={{marginBottom: '25px'}} className="input-group">
                                    <span className="input-group-addon"><i className="glyphicon glyphicon-user"/></span>
                                    <input onChange={this.handleUserNameChange.bind(this)} id="login-password" type="text"
                                           className="form-control" name="password"
                                           placeholder="username"/>
                                </div>
                                <div style={{marginBottom: '25px'}} className="input-group">
                                    <span className="input-group-addon"><i className="glyphicon glyphicon-lock"/></span>
                                    <input onChange={this.handlePasswordChange.bind(this)} id="login-password" type="password"
                                           className="form-control" name="password"
                                           placeholder="password"/>
                                </div>

                                <div style={{marginTop: '10px'}} className="form-group">
                                    <div className="col-sm-12 controls">
                                        <a onClick={this.handlers.login.bind(this)} id="btn-login" className="btn btn-success">Login </a>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default connect()(Login);
