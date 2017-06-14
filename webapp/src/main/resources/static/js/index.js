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
    topics: [],
    votingToken: {
        number: "TOKEN_TEST",
        tokenPoints: 0
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
