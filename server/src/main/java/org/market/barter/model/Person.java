package org.market.barter.model;

import java.util.Date;
import org.springframework.data.annotation.Id;

public class Person {

  @Id
  private String username;

  private String email;
  String salt;
  private String hash;
  private Date createdDate;
  private Date lastActivity;
  private PersonStatus status;

  public Person(String username, String email, String salt, String hash) {
    this.username = username;
    this.email = email;
    this.salt = salt;
    this.hash = hash;
    this.createdDate = new Date();
    this.lastActivity = new Date();
    this.status = PersonStatus.ACTIVE;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getSalt() {
    return salt;
  }

  public String getHash() {
    return hash;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public Date getLastActivity() {
    return lastActivity;
  }

  public PersonStatus getStatus() {
    return status;
  }
}
