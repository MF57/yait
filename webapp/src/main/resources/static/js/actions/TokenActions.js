import {REGISTER_TOKEN_IN_STORE} from "./types/TokenActionTypes";

export function registerTokenInStore(token) {
    return {
        type: REGISTER_TOKEN_IN_STORE,
        token: token
    };
}