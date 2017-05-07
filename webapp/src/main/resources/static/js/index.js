import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import {topicReducer} from "./reducers/TopicReducer";
import {createStore} from "redux";
import {Provider} from "react-redux";

const preloadState = {
    topics: [
        {name: "TOPIC1", status: "DONE", id: 1, score: 0},
        {name: "TOPIC2", status: "DONE", id: 2, score: 0}
    ]
};

const store = createStore(topicReducer, preloadState);

ReactDOM.render(
    <Provider store={store}>
    <App/></Provider>,
    document.getElementById('root')
);