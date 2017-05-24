import React, {Component} from "react";

class NavBar extends Component {

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
                </div>
            </nav>
        )
    }
}

export default NavBar;