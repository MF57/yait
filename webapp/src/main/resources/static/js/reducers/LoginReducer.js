import {VOTING_TOKEN_IN_USE, LOGIN} from "../actions/types/LoginActionTypes";
import {preloadState} from "../index";

export function loginReducer(state, action) {
    if (state === undefined) {
        return preloadState
    }
    switch (action.type) {
        case VOTING_TOKEN_IN_USE: {
            return Object.assign({}, state, {isTokenBeingUsed: true})
        }
        case LOGIN: {
            return Object.assign({}, state, {isUserLogged: true, authorizationToken: action.token})
        }
    }

    return state;
}
