import React from "react";
import ReactDOM from "react-dom";
import App from "./App";
import {combineReducers, createStore} from "redux";
import {topicReducer} from "./reducers/TopicReducer";
import {tokenReducer} from "./reducers/TokenReducer";
import {commentReducer} from "./reducers/CommentReducer";
import {ldapGroupsReducer} from "./reducers/LdapGroupsReducer";
import {loginReducer} from "./reducers/LoginReducer";
import {Provider} from "react-redux";
import {HashRouter} from "react-router-dom";

export const preloadState = {
    topics: [
        {title: "TOPIC1", status: "DONE", id: 1, score: 0, author: {login: "user1",firstName: "firstName1",lastName: "lastName1"}, description: "Topic 1 description", created_at: "2017-06-06T14:43:40+0000", statusChangeDate: "2017-06-06T14:43:40+0000"},
        {title: "TOPIC2", status: "DONE", id: 2, score: 2, author: {login: "user2",firstName: "firstName2",lastName: "lastName2"}, description: "Topic 2 description", created_at: "2017-06-06T14:43:40+0000", statusChangeDate: "2017-06-06T14:43:40+0000"},
        {title: "TOPIC3", status: "DONE", id: 3, score: 0, author: {login: "user3",firstName: "firstName3",lastName: "lastName3"}, description: "Topic 3 description", created_at: "2017-06-06T14:43:40+0000", statusChangeDate: "2017-06-06T14:43:40+0000"},
        {title: "TOPIC4", status: "DONE", id: 4, score: 0, author: {login: "user4",firstName: "firstName4",lastName: "lastName4"}, description: "Topic 4 description", created_at: "2017-06-06T14:43:40+0000", statusChangeDate: "2017-06-06T14:43:40+0000"}
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
        authorizationToken: "",
        username: ""
    },
    ldapGroups: []

};

const rootReducer = combineReducers({
    topics: topicReducer,
    votingToken: tokenReducer,
    comments: commentReducer,
    login: loginReducer,
    ldapGroups: ldapGroupsReducer
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
