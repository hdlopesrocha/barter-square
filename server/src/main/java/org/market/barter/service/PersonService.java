package org.market.barter.service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;

import org.market.barter.command.AuthenticatePersonCommand;
import org.market.barter.command.RegisterPersonCommand;
import org.market.barter.exception.BarterSquareException;
import org.market.barter.exception.InvalidTokenException;
import org.market.barter.exception.PersonAlreadyRegisteredException;
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

  public void registerPerson(RegisterPersonCommand command) throws BarterSquareException {
    String token = computeEmailVerification(command.getEmail());
    if(!token.equals(command.getToken())) {
      throw new InvalidTokenException();
    }
    Optional<Person> existingPerson = personRepository.findById(command.getUsername());
    if(existingPerson.isPresent()) {
      throw new PersonAlreadyRegisteredException();
    }

    UUID salt = UUID.randomUUID();
    String hashStr = salt.toString() + command.getPassword();
    String hash = securityService.getHash(hashStr);

    Person person = new Person(command.getUsername(), command.getEmail(), salt.toString(), hash);
    personRepository.save(person);
  }

  public boolean authenticatePerson(AuthenticatePersonCommand command) {
    Optional<Person> existingPerson = personRepository.findById(command.getUsername());
    if(existingPerson.isEmpty()) {
      return false;
    }
    Person person = existingPerson.get();
    String hashStr = securityService.getHash(person.getSalt() + command.getPassword());

    return hashStr.equals(person.getHash());
  }

  public String computeEmailVerification(String email) {
    String hashStr = signatureSecret + email;
    return securityService.getHash(hashStr);
  }

}
