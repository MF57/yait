import {REGISTER_TOKEN_IN_STORE, SPEND_POINTS} from "./types/TokenActionTypes";

export function registerTokenInStore(token) {
    return {
        type: REGISTER_TOKEN_IN_STORE,
        token: token
    };
}

export function spendPoints(points) {
    return {
        type: SPEND_POINTS,
        points: points
    };
}