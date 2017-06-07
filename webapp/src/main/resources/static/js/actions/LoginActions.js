import {LOGIN, VOTING_TOKEN_IN_USE} from "./types/LoginActionTypes";

export function login(token) {
    return {
        type: LOGIN,
        token: token
    };
}

export function votingTokenInUse() {
    return {
        type: VOTING_TOKEN_IN_USE
    }

}