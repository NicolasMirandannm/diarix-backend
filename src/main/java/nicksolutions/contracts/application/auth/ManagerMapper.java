package nicksolutions.contracts.application.auth;

import nicksolutions.contracts.application.auth.dto.ManagerDto;
import nicksolutions.contracts.domain.auth.Manager;
import nicksolutions.core.crud.ApplicationMapper;
import org.springframework.stereotype.Component;

@Component
public class ManagerMapper implements ApplicationMapper<ManagerDto, Manager> {

    public Manager toEntity(ManagerDto dto) {
        if (dto == null) {
            return null;
        }
        var manager = Manager.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .phoneNumber(dto.getPhoneNumber())
                .build();
        manager.setVersion(dto.getVersion());
        return manager;
    }

  public ManagerDto toDto(Manager entity) {
    if (entity == null) {
      return null;
    }
    return ManagerDto.builder()
            .id(entity.getId())
            .name(entity.getName())
            .email(entity.getEmail())
            .phoneNumber(entity.getPhoneNumber())
            .version(entity.getVersion())
            .build();
  }
}
