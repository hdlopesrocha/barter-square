package org.market.barter.controller;

import javax.mail.MessagingException;

import org.market.barter.command.AuthenticatePersonCommand;
import org.market.barter.command.RegisterPersonCommand;
import org.market.barter.command.SendEmailCommand;
import org.market.barter.command.VerifyEmailCommand;
import org.market.barter.exception.BarterSquareException;
import org.market.barter.service.EmailService;
import org.market.barter.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@RestController
public class PersonController {


  final PersonService personService;
  final EmailService emailService;
  private SpringTemplateEngine springTemplateEngine;

  public PersonController(PersonService personService,
                          EmailService emailService, SpringTemplateEngine springTemplateEngine) {
    this.personService = personService;
    this.emailService = emailService;
    this.springTemplateEngine = springTemplateEngine;
  }

  @PostMapping(value = "/verifyEmail")
  public void verifyEmail(@RequestBody VerifyEmailCommand command) throws MessagingException {
    String token = personService.computeEmailVerification(command.getEmail());
    Context context = new Context();
    context.setVariable("token", token);
    context.setVariable("email", command.getEmail());
    context.setVariable("url", "http://localhost:3000/register");
    String html = springTemplateEngine.process("verifyEmail", context);
    emailService.sendEmail(new SendEmailCommand(command.getEmail(), "[Barter Square] confirm your e-mail", html));
  }

  @PostMapping(value = "/register")
  public void register(@RequestBody RegisterPersonCommand command) throws BarterSquareException {
    personService.registerPerson(command);
  }

  @PostMapping(value = "/auth")
  public ResponseEntity<?> authenticate(@RequestBody AuthenticatePersonCommand command) throws BarterSquareException {
    boolean auth = personService.authenticatePerson(command);
    return ResponseEntity.status(auth ? HttpStatus.OK : HttpStatus.UNAUTHORIZED).body(null);
  }
}
