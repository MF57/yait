package edu.agh.yait;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface LdapUserService {

    Optional<User> getUser(String login, String password);
    Map<UserId, Optional<User>> getUsers(List<UserId> ids);
    List<User> getUserByGroup(GroupId groups);


    class User {

    }
    class UserId {

    }
    class GroupId {

    }

}
