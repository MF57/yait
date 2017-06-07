import React, { Component } from 'react';
import Checkbox from './Checkbox';
import {connect} from "react-redux";


class LdapGroups extends Component {
    componentWillMount(){
        this.selectedCheckboxes = [];
    };

    toggleCheckbox(label) {
        let elementIndex = this.selectedCheckboxes.indexOf(label);
        if (elementIndex> -1) {
            this.selectedCheckboxes.splice(elementIndex, 1);
        } else {
            this.selectedCheckboxes.push(label);
        }
        this.props.handleCheckboxChange(this.selectedCheckboxes);
    };


    createCheckbox(label) {
        return (
            <Checkbox
                label={label}
                handleCheckboxChange={this.toggleCheckbox.bind(this)}
                key={label}
            />
        );
    }

    createCheckboxes() {
        return (this.props.ldapGroups.map(this.createCheckbox.bind(this)));
    }


    render() {
        return (
            <div className="container">
                <div className="row">
                    <div className="col-sm-12">
                            {this.createCheckboxes()}
                    </div>
                </div>
            </div>
        );
    }
}

export default connect()(LdapGroups);
