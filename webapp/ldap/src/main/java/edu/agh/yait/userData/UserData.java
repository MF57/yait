package edu.agh.yait.userData;

import java.util.Optional;

/**
 * Created by mf57 on 06.05.2017.
 */
public class UserData {
    private final String id;
    private final Optional<String> name;
    private final Optional<String> surname;
    private final Optional<String> mail;

    public UserData(String id, Optional<String> name, Optional<String> surname, Optional<String> mail) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
    }

    public String getId() {
        return id;
    }

    public Optional<String> getName() {
        return name;
    }

    public Optional<String> getSurname() {
        return surname;
    }

    public Optional<String> getMail() {
        return mail;
    }
}
