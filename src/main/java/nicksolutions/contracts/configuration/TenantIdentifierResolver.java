package nicksolutions.contracts.configuration;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver<String> {

  @Override
  public String resolveCurrentTenantIdentifier() {
    var context = SecurityContextHolder.getContext();
    var auth = context != null ? context.getAuthentication() : null;

    if (auth == null || !auth.isAuthenticated() || auth.getName() == null) {
      return "default";
    }

    return auth.getName();
  }

  @Override
  public boolean validateExistingCurrentSessions() {
    return true;
  }
}
