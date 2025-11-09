package nicksolutions.contracts.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  private final CheckValidManagerInterceptor checkValidManagerInterceptor;

  public WebConfig(CheckValidManagerInterceptor checkValidManagerInterceptor) {
    this.checkValidManagerInterceptor = checkValidManagerInterceptor;
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOriginPatterns("*")
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
        .allowedHeaders("*")
        .exposedHeaders("Content-Disposition")
        .allowCredentials(true)
        .maxAge(3600);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(checkValidManagerInterceptor)
        .excludePathPatterns("/managers/sign-in")
        .excludePathPatterns("/managers/sign-up")
        .addPathPatterns("/**");
  }
}
