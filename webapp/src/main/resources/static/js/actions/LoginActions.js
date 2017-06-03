import {LOGIN} from "./types/LoginActionTypes";

export function login(token) {
    return {
        type: LOGIN,
        token: token
    };
}