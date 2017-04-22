import React,{Component, PropTypes} from 'react';

class Topic extends Component {

    componentWillMount() {
        this.setState({
            name  : this.props.name,
            status: this.props.status
        });
    }

    render(){
        return (
            <div className="col-md-3 col-sm-6 hero-feature">
                <div className="thumbnail">
                    <img src="http://placehold.it/800x500" alt=""/>
                        <div className="caption">
                            <h3>{this.state.name}</h3>
                            <p>{this.state.status}</p>
                            <p>
                                <a href="#" className="btn btn-primary">UPVOTE</a>
                            </p>
                        </div>
                </div>
            </div>
        )
    }
}

Topic.propTypes = {
    name: PropTypes.string.isRequired,
    status: PropTypes.string.isRequired
}

export default Topic;
