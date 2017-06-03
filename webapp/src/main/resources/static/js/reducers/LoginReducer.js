import {VOTING_TOKEN_IN_USE} from "../actions/types/LoginActionTypes";

export function loginReducer(state, action) {
    switch (action.type) {
        case VOTING_TOKEN_IN_USE: {
            return Object.assign({}, state, {isTokenBeingUsed: true})
        }
    }

    return {
        login: {
            isUserLogged: false,
            isTokenBeingUsed: false,
            isAdmin: false,
            authorizationToken: ""
        }
    };
}
