package nicksolutions.contracts;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@WithMockUser(username = "contract-manager-id", roles = {"ADMIN"}, value = "contract-manager-id")
public class CustomContractsServerTests {

	@Test
	void loadContext() {}

	@SuppressWarnings("resource")
	static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:14")
			.withInitScript("database/init.sql");

	@BeforeAll
	static void beforeAll() {
		postgreSQLContainer.start();
	}

	@AfterAll
	static void afterAll() {
		postgreSQLContainer.stop();
	}

	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
		registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
		registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
		registry.add("spring.flyway.enabled", () -> "true");
		registry.add("spring.flyway.schemas", () -> "contracts");
		registry.add("spring.flyway.locations", () -> "classpath:database/migrations");
		registry.add("spring.flyway.url", postgreSQLContainer::getJdbcUrl);
		registry.add("spring.flyway.user", postgreSQLContainer::getUsername);
		registry.add("spring.flyway.password", postgreSQLContainer::getPassword);
	}
}
