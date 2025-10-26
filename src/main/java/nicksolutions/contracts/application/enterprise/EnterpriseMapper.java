package nicksolutions.contracts.application.enterprise;

import nicksolutions.contracts.application.enterprise.dto.EnterpriseDto;
import nicksolutions.contracts.domain.enterprise.Enterprise;
import nicksolutions.core.crud.ApplicationMapper;
import org.springframework.stereotype.Component;

@Component
public class EnterpriseMapper implements ApplicationMapper<EnterpriseDto, Enterprise> {

  @Override
  public EnterpriseDto toDto(Enterprise entity) {
    if (entity == null) {
      return null;
    }
    var dto = EnterpriseDto.builder()
        .id(entity.getId())
        .name(entity.getName())
        .cnpj(entity.getCnpj())
        .address(entity.getAddress())
        .ownerEmail(entity.getOwnerEmail())
        .ownerPhone(entity.getOwnerPhone())
        .ownerName(entity.getOwnerName())
        .baseDailyRate(entity.getBaseDailyRate())
        .status(entity.getStatus())
        .build();
    dto.setVersion(entity.getVersion());
    return dto;
  }

  @Override
  public Enterprise toEntity(EnterpriseDto enterpriseDto) {
    if (enterpriseDto == null) {
      return null;
    }
    var entity = Enterprise.builder()
        .id(enterpriseDto.getId())
        .name(enterpriseDto.getName())
        .cnpj(enterpriseDto.getCnpj())
        .address(enterpriseDto.getAddress())
        .ownerEmail(enterpriseDto.getOwnerEmail())
        .ownerPhone(enterpriseDto.getOwnerPhone())
        .ownerName(enterpriseDto.getOwnerName())
        .baseDailyRate(enterpriseDto.getBaseDailyRate())
        .status(enterpriseDto.getStatus())
        .build();
    entity.setVersion(enterpriseDto.getVersion());
    return entity;
  }
}
