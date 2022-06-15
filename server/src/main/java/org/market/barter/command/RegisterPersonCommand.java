package org.market.barter.command;

public class RegisterPersonCommand {

  private String username;
  private String password;
  private String email;
  private String token;

  public RegisterPersonCommand(String username, String email, String password,
      String token) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.token = token;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  public String getToken() {
    return token;
  }
}
