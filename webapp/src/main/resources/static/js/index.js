import React from "react";
import ReactDOM from "react-dom";
import App from "./App";
import {topicReducer} from "./reducers/TopicReducer";
import {combineReducers, createStore} from "redux";
import {tokenReducer} from "./reducers/TokenReducer";
import {commentReducer} from "./reducers/CommentReducer";
import {Provider} from "react-redux";
import {HashRouter} from "react-router-dom";

export const preloadState = {
    topics: [
        {title: "TOPIC1", status: "DONE", id: 1, points: 0, author: "123", description: "Topic 1 description", creationDate: "1495942865"},
        {title: "TOPIC2", status: "DONE", id: 2, points: 2, author: "123", description: "Topic 2 description", creationDate: "1495942865"},
        {title: "TOPIC3", status: "DONE", id: 3, points: 0, author: "123", description: "Topic 3 description", creationDate: "1495942865"},
        {title: "TOPIC4", status: "DONE", id: 4, points: 0, author: "123", description: "Topic 4 description", creationDate: "1495942865"}
    ],
    votingToken: {
        number: "TOKEN_TEST",
        tokenPoints: 10
    },
    comments: [],
    login: {
        isUserLogged: false,
        isTokenBeingUsed: false,
        isAdmin: false,
        authorizationToken: ""
    }

};

const rootReducer = combineReducers({
    topics: topicReducer,
    votingToken: tokenReducer,
    comments: commentReducer
});
const store = createStore(rootReducer, preloadState);

ReactDOM.render(
    <Provider store={store}>
        <HashRouter>
            <App />
        </HashRouter>
    </Provider>,
    document.getElementById('root')
);
