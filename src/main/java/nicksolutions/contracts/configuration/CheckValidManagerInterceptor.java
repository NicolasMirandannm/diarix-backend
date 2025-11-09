package nicksolutions.contracts.configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nicksolutions.contracts.domain.auth.service.ManagerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CheckValidManagerInterceptor implements HandlerInterceptor {

  private final ManagerService managerService;

  public CheckValidManagerInterceptor(ManagerService managerService) {
    this.managerService = managerService;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication.isAuthenticated()) {
      String managerId = authentication.getName();
      if (!managerService.existsById(managerId)) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Id do gestor não existe. Acesso negado.");
        return false;
      }
    }

    return true;
  }
}
