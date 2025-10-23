package nicksolutions.contracts.application.dailywage;

import nicksolutions.contracts.application.dailywage.dto.DailyWageDto;
import nicksolutions.contracts.domain.dailywage.DailyWage;
import nicksolutions.contracts.domain.dailywage.service.DailyWageService;
import nicksolutions.core.crud.AbstractApplicationComponent;
import nicksolutions.core.crud.ApplicationMapper;
import org.springframework.stereotype.Component;

@Component
public class DailyWageComponentImpl extends AbstractApplicationComponent<DailyWageDto, DailyWage, DailyWageService> implements DailyWageComponent {

  protected DailyWageComponentImpl(ApplicationMapper<DailyWageDto, DailyWage> mapper, DailyWageService service) {
    super(mapper, service);
  }
}
