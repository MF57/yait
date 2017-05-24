import React from "react";
import ReactDOM from "react-dom";
import App from "./App";
import {topicReducer} from "./reducers/TopicReducer";
import {combineReducers, createStore} from "redux";
import {HashRouter, Route} from "react-router-dom";
import {tokenReducer} from "./reducers/TokenReducer";
import {Provider} from "react-redux";

export const preloadState = {
    topics: [
        {name: "TOPIC1", status: "DONE", id: 1, score: 0, author: "123", description: "Topic 1 description"},
        {name: "TOPIC2", status: "DONE", id: 2, score: 0, author: "123", description: "Topic 2 description"},
        {name: "TOPIC3", status: "DONE", id: 3, score: 0, author: "123", description: "Topic 3 description"},
        {name: "TOPIC4", status: "DONE", id: 4, score: 0, author: "123", description: "Topic 4 description"}
    ],
    token: {
        number: "TOKEN_TEST",
        tokenPoints: 10
    },
};

const rootReducer = combineReducers({
    topics: topicReducer,
    token: tokenReducer
});
const store = createStore(rootReducer, preloadState);

ReactDOM.render(
    <Provider store={store}>
        <HashRouter>
            <Route path="/:token?" component={App}/>
        </HashRouter>
    </Provider>,
    document.getElementById('root')
);
