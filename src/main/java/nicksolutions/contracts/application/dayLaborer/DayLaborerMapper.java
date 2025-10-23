package nicksolutions.contracts.application.dayLaborer;

import nicksolutions.contracts.application.dayLaborer.dto.DayLaborerDto;
import nicksolutions.contracts.domain.daylaborer.DayLaborer;
import nicksolutions.core.crud.ApplicationMapper;
import org.springframework.stereotype.Component;

@Component
public class DayLaborerMapper implements ApplicationMapper<DayLaborerDto, DayLaborer> {

    @Override
    public DayLaborerDto toDto(DayLaborer entity) {
        if (entity == null) {
            return null;
        }
        var dto = DayLaborerDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .cpf(entity.getCpf())
                .phoneNumber(entity.getPhoneNumber())
                .status(entity.getStatus())
                .pixKey(entity.getPixKey())
                .build();
        dto.setVersion(entity.getVersion());
        return dto;
    }

    @Override
    public DayLaborer toEntity(DayLaborerDto dayLaborerDto) {
        if (dayLaborerDto == null) {
            return null;
        }
        var entity = DayLaborer.builder()
                .id(dayLaborerDto.getId())
                .name(dayLaborerDto.getName())
                .cpf(dayLaborerDto.getCpf())
                .phoneNumber(dayLaborerDto.getPhoneNumber())
                .status(dayLaborerDto.getStatus())
                .pixKey(dayLaborerDto.getPixKey())
                .build();
        entity.setVersion(dayLaborerDto.getVersion());
        return entity;
    }
}
