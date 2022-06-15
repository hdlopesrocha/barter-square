package org.market.barter.service;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import org.market.barter.command.RegisterPersonCommand;
import org.market.barter.command.VerifyEmailCommand;
import org.market.barter.model.Person;
import org.market.barter.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  @Value( "${signatureSecret}" )
  private String signatureSecret;

  final SecurityService securityService;
  final PersonRepository personRepository;

  public PersonService(SecurityService securityService,
      PersonRepository personRepository) {
    this.securityService = securityService;
    this.personRepository = personRepository;
  }

  public void registerPerson(RegisterPersonCommand command) {
    UUID salt = UUID.randomUUID();
    String hashStr = salt.toString() + command.getPassword();
    byte [] hash = securityService.getHash(hashStr.getBytes(StandardCharsets.UTF_8));

    Person person = new Person(command.getUsername(), command.getEmail(), salt.toString().getBytes(), hash);
    personRepository.save(person);
  }


  public String computeEmailVerification(VerifyEmailCommand command) {
    String hashStr = signatureSecret + command.getEmail();
    byte [] hash = securityService.getHash(hashStr.getBytes(StandardCharsets.UTF_8));
    return securityService.toBase64(hash);
  }

}
