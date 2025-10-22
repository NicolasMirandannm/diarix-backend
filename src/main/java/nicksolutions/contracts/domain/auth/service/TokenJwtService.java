package nicksolutions.contracts.domain.auth.service;

import nicksolutions.contracts.domain.auth.ContractManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenJwtService {

  private final JwtEncoder jwtEncoder;
  private final JwtDecoder jwtDecoder;

  @Autowired
  public TokenJwtService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
    this.jwtEncoder = jwtEncoder;
    this.jwtDecoder = jwtDecoder;
  }

  public String generateCustomerJwtToken(ContractManager contractManager) {
    var claims = JwtClaimsSet.builder()
        .issuer("diarixpro")
        .subject(contractManager.getId())
        .issuedAt(Instant.now())
        .build();

    return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }

  public String getContractManagerIdFromToken(String token) {
    return jwtDecoder.decode(token.replace("Bearer ", "")).getSubject();
  }
}
