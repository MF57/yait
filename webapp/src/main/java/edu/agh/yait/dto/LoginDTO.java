package edu.agh.yait.dto;

public class LoginDTO {

    private String user;
    private String password;

    public LoginDTO(){
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
