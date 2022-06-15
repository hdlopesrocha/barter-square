package org.market.barter.controller;

import org.market.barter.command.RegisterPersonCommand;
import org.market.barter.exception.BarterSquareException;
import org.market.barter.repository.PersonRepository;
import org.market.barter.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SetupController {

  private final PersonService personService;
  private final PersonRepository personRepository;

  public SetupController(PersonService personService, PersonRepository personRepository) {
    this.personService = personService;
    this.personRepository = personRepository;
  }

  @GetMapping(value = "/setup")
  public void setup() throws BarterSquareException {
    personRepository.deleteAll();
    String email = "hdlopesrocha91@gmail.com";

    RegisterPersonCommand registerPersonCommand = new RegisterPersonCommand("admin",email, "password",
        personService.computeEmailVerification(email));
    personService.registerPerson(registerPersonCommand);
  }

}
