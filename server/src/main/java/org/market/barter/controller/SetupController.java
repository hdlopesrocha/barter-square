package org.market.barter.controller;

import org.market.barter.command.RegisterPersonCommand;
import org.market.barter.exception.BarterSquareException;
import org.market.barter.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SetupController {

  private final PersonService personService;

  public SetupController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping(value = "/setup")
  public void setup() throws BarterSquareException {
    String email = "hdlopesrocha91@gmail.com";

    RegisterPersonCommand registerPersonCommand = new RegisterPersonCommand("admin",email, "password",
        personService.computeEmailVerification(email));
    personService.registerPerson(registerPersonCommand);
  }

}
