import {VOTING_TOKEN_IN_USE, LOGIN, LOGOUT} from "../actions/types/LoginActionTypes";
import {preloadState} from "../index";
import jwtDecode from 'jwt-decode';

export function loginReducer(state, action) {
    if (state === undefined) {
        return preloadState
    }
    switch (action.type) {
        case VOTING_TOKEN_IN_USE: {
            return Object.assign({}, state, {isTokenBeingUsed: true})
        }
        case LOGIN: {
            console.log(jwtDecode(action.token));
            return Object.assign({}, state, {isUserLogged: true, authorizationToken: action.token, isAdmin: true, username: jwtDecode(action.token).sub})
        }
        case LOGOUT:
            return Object.assign({}, state, {isUserLogged: false, authorizationToken: '', isAdmin: false, username: ''})
    }

    return state;
}
