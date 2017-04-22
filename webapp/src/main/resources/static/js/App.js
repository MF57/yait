import React from 'react';
import List from './List';
import NavBar from './NavBar'

class App extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                <NavBar/>
                <List/>
            </div>
        )
    }
};

export default App;