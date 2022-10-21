package ru.kpfu.itis.chemaev.net.model;

public class User {
    private int id;
    private final String firstname;
    private final String lastname;
    private final String login;
    private String password;

    public User(int id, String login, String firstName, String lastName, String password) {
        this.id = id;
        this.login = login;
        this.firstname = firstName;
        this.lastname = lastName;
        this.password = password;
    }

    public User(String login, String firstname, String lastname, String password) {
        this.login = login;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }
}
