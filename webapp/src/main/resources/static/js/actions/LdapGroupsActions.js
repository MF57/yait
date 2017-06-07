import {REPLACE_LDAP_GROUPS} from "./types/LdapGroupsActionTypes";

export function replaceLdapGroups(newLdapGroups) {
    return {
        type: REPLACE_LDAP_GROUPS,
        ldapGroups: newLdapGroups
    }
}
