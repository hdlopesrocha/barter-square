package org.market.barter.model;

import java.util.Date;
import org.springframework.data.annotation.Id;

public class Person {

  @Id
  private String username;

  private String email;
  private byte [] salt;
  private byte [] hash;
  private Date createdDate;
  private Date lastActivity;
  private PersonStatus status;

  public Person(String username, String email, byte [] salt, byte [] hash) {
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

  public byte[] getSalt() {
    return salt;
  }

  public byte[] getHash() {
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
