package edu.agh.yait.utils;

import edu.agh.yait.LdapFascade;
import edu.agh.yait.persistence.model.User;
import edu.agh.yait.userData.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Krzysztof Podsiadło on 07.06.17.
 */
@Component
public class UserUtils {
    @Autowired
    private LdapFascade ldapFascade;

    public void fetchInformation(User user) {
        Optional<UserData> creator = null;
        if (user.getLdapId() != null) {
            creator = ldapFascade.getUserDataById(user.getLdapId());
        } else if (user.getLogin() != null) {
            creator = ldapFascade.getUserDataByLogin(user.getLogin());
        }
        if (creator != null && creator.isPresent()) {
            if (user.getLdapId() != null) {
                user.setLogin(creator.get().getLogin());
            } else if (user.getLogin() != null) {
                user.setLdapId(creator.get().getId());
            }
            user.setFirstName(creator.get().getName().orElse(null));
            user.setLastName(creator.get().getSurname().orElse(null));
        } else {
            throw new IllegalStateException("Cannot find such a user.");
        }
    }
}
