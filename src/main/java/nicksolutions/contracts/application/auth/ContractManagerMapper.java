package nicksolutions.contracts.application.auth;

import nicksolutions.contracts.application.auth.dto.ContractManagerDto;
import nicksolutions.contracts.domain.auth.ContractManager;
import nicksolutions.core.crud.ApplicationMapper;
import org.springframework.stereotype.Component;

@Component
public class ContractManagerMapper implements ApplicationMapper<ContractManagerDto, ContractManager> {

  public ContractManager toEntity(ContractManagerDto dto) {
    if (dto == null) {
      return null;
    }
    var contractManager = ContractManager.builder()
            .id(dto.getId())
            .name(dto.getName())
            .email(dto.getEmail())
            .password(dto.getPassword())
            .phoneNumber(dto.getPhoneNumber())
            .build();
    contractManager.setVersion(dto.getVersion());
    return contractManager;
  }

  public ContractManagerDto toDto(ContractManager entity) {
    if (entity == null) {
      return null;
    }
    return ContractManagerDto.builder()
            .id(entity.getId())
            .name(entity.getName())
            .email(entity.getEmail())
            .phoneNumber(entity.getPhoneNumber())
            .version(entity.getVersion())
            .build();
  }
}
