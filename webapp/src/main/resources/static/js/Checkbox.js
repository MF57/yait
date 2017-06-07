import React, { Component } from 'react';
import {connect} from "react-redux";

class Checkbox extends Component {

    constructor(props){
        super(props);
        this.state = {
            isChecked: false
        };
    }

    toggleCheckboxChange() {
        const { handleCheckboxChange, label } = this.props;
        this.setState({isChecked: !this.state.isChecked});

        handleCheckboxChange(label);
    };

    render() {
        const { label } = this.props;
        const { isChecked } = this.state;

        return (
            <div className="checkbox">
                <label>
                    <input
                        type="checkbox"
                        value={label}
                        checked={isChecked}
                        onChange={this.toggleCheckboxChange.bind(this)}
                    />

                    {label}
                </label>
            </div>
        );
    }
}


export default connect()(Checkbox);