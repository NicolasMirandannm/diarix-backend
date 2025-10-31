package nicksolutions.contracts.domain;

import nicksolutions.contracts.domain.enterprise.Enterprise;
import org.springframework.stereotype.Component;

@Component
public class MockFactory {

  public Enterprise createEnterprise() {
    return Enterprise.builder()
        .managerId("contract-manager-id")
        .name("enterprise")
        .cnpj("12.345.678/0001-90")
        .ownerName("Owner Name")
        .ownerEmail("owner@gmail.com")
        .ownerPhone("(11) 91234-5678")
        .address("123 Main St, City, Country")
        .build();
  }
}
