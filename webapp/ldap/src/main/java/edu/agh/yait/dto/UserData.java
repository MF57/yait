package edu.agh.yait.dto;

/**
 * Created by mf57 on 06.05.2017.
 */
public class UserData {
    private final String name;
    private final String surname;
    private final String mail;

    public UserData(String name, String surname, String mail) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMail() {
        return mail;
    }
}
