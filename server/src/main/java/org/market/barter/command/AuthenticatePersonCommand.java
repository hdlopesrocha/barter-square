package org.market.barter.command;

public class AuthenticatePersonCommand {

    private String username;
    private String password;

    public AuthenticatePersonCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
