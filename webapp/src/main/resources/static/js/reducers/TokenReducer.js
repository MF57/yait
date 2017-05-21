import {REGISTER_TOKEN_IN_STORE} from "../actions/types/TokenActionTypes";
import {preloadState} from "../index";

export function tokenReducer(state, action) {
    if (state === undefined) {
        return preloadState
    }

    switch (action.type) {
        case REGISTER_TOKEN_IN_STORE: {
            let token = state;
            token.number = action.token;
            return Object.assign({}, state, {token: token});
        }
    }
    return state;
}