import {LOGIN, VOTING_TOKEN_IN_USE, LOGOUT} from "./types/LoginActionTypes";

export function login(token) {
    return {
        type: LOGIN,
        token: token
    };
}

export function logout() {
    return {
        type: LOGOUT
    };
}


export function votingTokenInUse() {
    return {
        type: VOTING_TOKEN_IN_USE
    }
}