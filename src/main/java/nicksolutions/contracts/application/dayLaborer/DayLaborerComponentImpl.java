package nicksolutions.contracts.application.dayLaborer;

import nicksolutions.contracts.application.dayLaborer.dto.DayLaborerDto;
import nicksolutions.contracts.domain.daylaborer.DayLaborer;
import nicksolutions.contracts.domain.daylaborer.service.DayLaborerService;
import nicksolutions.core.crud.AbstractApplicationComponent;
import nicksolutions.core.crud.ApplicationMapper;
import org.springframework.stereotype.Component;

@Component
public class DayLaborerComponentImpl extends AbstractApplicationComponent<DayLaborerDto, DayLaborer, DayLaborerService> implements DayLaborerComponent {

    protected DayLaborerComponentImpl(ApplicationMapper<DayLaborerDto, DayLaborer> mapper, DayLaborerService service) {
        super(mapper, service);
    }
}
