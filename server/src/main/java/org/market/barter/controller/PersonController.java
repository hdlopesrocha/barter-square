package org.market.barter.controller;

import javax.mail.MessagingException;
import org.market.barter.command.SendEmailCommand;
import org.market.barter.command.VerifyEmailCommand;
import org.market.barter.service.EmailService;
import org.market.barter.service.PersonService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {


  final PersonService personService;
  final EmailService emailService;

  public PersonController(PersonService personService,
      EmailService emailService) {
    this.personService = personService;
    this.emailService = emailService;
  }

  @PostMapping(value = "/verifyEmail")
  public void verifyEmail(@RequestBody VerifyEmailCommand command) throws MessagingException {
    String verificationToken = personService.computeEmailVerification(command);
    emailService.sendEmail(new SendEmailCommand(command.getEmail(), "[Barter Square] confirm your e-mail", verificationToken));
  }

}
