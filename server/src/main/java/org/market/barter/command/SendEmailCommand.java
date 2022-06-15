package org.market.barter.command;

public class SendEmailCommand {

  private String receiver;
  private String subject;
  private String content;


  public SendEmailCommand(String receiver, String subject, String content) {
    this.receiver = receiver;
    this.subject = subject;
    this.content = content;
  }

  public String getReceiver() {
    return receiver;
  }

  public String getSubject() {
    return subject;
  }

  public String getContent() {
    return content;
  }
}
