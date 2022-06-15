package org.market.barter.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

  private MessageDigest digest;

  public SecurityService() throws NoSuchAlgorithmException {
    digest = MessageDigest.getInstance("SHA-256");
  }

  public String getHash(String data) {
    return toBase64(digest.digest(data.getBytes(StandardCharsets.UTF_8)));
  }

  public String toBase64(byte [] data){
    return Base64.getEncoder().encodeToString(data);
  }

}
