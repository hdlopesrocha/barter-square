package org.market.barter.command;

public class VerifyEmailCommand {

  private String email;

  public VerifyEmailCommand(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }
}
