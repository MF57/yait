import {connect} from "react-redux";
import React, {Component} from "react";
import * as axios from "axios";
import DatePicker from "react-bootstrap-date-picker";
import * as EmailValidator from "email-validator";
import LdapGroups from '../LdapGroups'

let createHandlers = function (dispatch) {
    let generateTokens = function (e) {
        e.preventDefault();
        e.stopPropagation();

        let unparsedEmails = this.state.mails.split('\n');
        let parsedEmails = [];
        let misspelledEmails = [];
        for (let i = 0; i < unparsedEmails.length; i++) {
            if(EmailValidator.validate(unparsedEmails[i])){
                parsedEmails.push(unparsedEmails[i]);
            } else {
                misspelledEmails.push(unparsedEmails[i]);
            }
        }
        let xyx = misspelledEmails.join().replace(",", "\n");
        this.setState({mails: xyx});
        if(xyx.length > 0){
            this.setState({error: true});
        } else {
            this.setState({error: false});
        }

        let tokenData = {
            emails: parsedEmails,
            ldapGroups: this.inputs.ldapGroups,
            tokenPoints: this.inputs.tokens
        };

        if(this.inputs.date){
            tokenData.expires_at = this.inputs.date.split(".")[0]+'Z'
        }

        console.log(tokenData);
        if(tokenData.expires_at !== undefined && tokenData.tokenPoints !== undefined){
            axios.post('/api/v1/admin/generate_tokens', tokenData)
                .then(function (response) {
                    this.setState({success: true});
                    this.setState({error: false});
                }.bind(this))
                .catch(function (error) {
                    this.setState({error: true});
                    this.setState({success: false});
                }.bind(this))
        }
    };
    return {
        generateTokens
    };
};

class TokenGenerator extends Component {

    constructor(props) {
        super(props);
        this.handlers = createHandlers(this.props.dispatch);
        this.inputs = {ldapGroups: []};
        this.state = {mails: ''};
        this.handleMailsChange = this.handleMailsChange.bind(this);
        this.handleDateChange = this.handleDateChange.bind(this);
        this.handleTokensChange = this.handleTokensChange.bind(this);
    }

    handleMailsChange(event) {
        this.setState({mails: event.target.value});
    }

    handleDateChange(value) {
        this.inputs.date = value;
    }

    handleTokensChange(event) {
        this.inputs.tokens = event.target.value
    }

    handleLdapGroupsChange(selectedCheckboxes) {
        this.inputs.ldapGroups = selectedCheckboxes;
        console.log(selectedCheckboxes);
    }

    render() {
        return (
            <div>
                {
                    this.state.error === true ?
                        <div className="alert alert-danger" id="errorTopicAdd">
                            <strong>Error!</strong> There was a problem while parsing emails
                        </div> : null

                }
                {
                    this.state.success === true ?
                        <div className="alert alert-success" id="successTopicAdd">
                            <strong>Success!</strong> Tokens generated successfully
                        </div> : null
                }

                <div className="col-md-6">
                    <div className="row form-group col-md-12">
                        <textarea className="form-control" onChange={this.handleMailsChange} placeholder="List of email addresses"
                                  rows="5" id="description" value={this.state.mails}/>
                    </div>
                    <div className="col-md-6">
                        <div className="row form-group">
                            <div className="col-md-4">
                                Expiring:
                            </div>
                            <div className="col-md-8">
                                <DatePicker className="form-control" onChange={this.handleDateChange}/>
                            </div>
                        </div>
                        <div className="row form-group">
                            <div className="col-md-4">
                                Tokens:
                            </div>
                            <div className="col-md-8">
                                <input type="number" onChange={this.handleTokensChange} className="form-control" placeholder="1"
                                   aria-describedby="basic-addon1" min={1}/>
                            </div>
                        </div>
                        <div className="row form-group">
                            <div className="col-md-12">
                                <a href="#" onClick={this.handlers.generateTokens.bind(this)} className="btn btn-primary">GENERATE</a>

                            </div>
                        </div>
                    </div>
                </div>
                <div className="col-md-6">
                    <LdapGroups ldapGroups={this.props.ldapGroups} handleCheckboxChange={this.handleLdapGroupsChange.bind(this)}/>
                </div>
            </div>
        )
    }
}


function mapStateToProps(state) {
    return { ldapGroups: state.ldapGroups}
}


export default connect(mapStateToProps)(TokenGenerator);
