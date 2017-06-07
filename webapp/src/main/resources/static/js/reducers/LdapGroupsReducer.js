import {REPLACE_LDAP_GROUPS} from "../actions/types/LdapGroupsActionTypes";
import {preloadState} from "../index";


export function ldapGroupsReducer(state, action) {
    if (state === undefined) {
        return preloadState
    }
    switch (action.type) {
        case REPLACE_LDAP_GROUPS: {
            return replaceLdapGroups(action.ldapGroups);
        }
        default:
            return [];
    }
    return state;
}

let replaceLdapGroups = function (newLdapGroups) {
    console.log(newLdapGroups);
    console.log("Replace ldapGroups");
    return newLdapGroups
};
