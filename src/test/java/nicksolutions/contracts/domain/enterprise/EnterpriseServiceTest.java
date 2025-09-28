package nicksolutions.contracts.domain.enterprise;

import nicksolutions.contracts.CustomContractsServerTests;
import nicksolutions.contracts.domain.MockFactory;
import nicksolutions.contracts.domain.enterprise.service.EnterpriseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EnterpriseServiceTest extends CustomContractsServerTests {

  @Autowired
  private EnterpriseService enterpriseService;

  @Autowired
  private MockFactory mockFactory;

  @Nested
  class Given_an_enterprise {
    private Enterprise enterprise;

    @BeforeEach
    void setup() {
      enterprise = mockFactory.createEnterprise();
    }

    @Nested
    class When_saving_the_enterprise {

      @BeforeEach
      void setup() {
        enterprise = enterpriseService.save(enterprise);
      }

      @Test
      void Then_it_should_be_persisted() {
        Assertions.assertNotNull(enterprise.getId());
      }
    }
  }
}
