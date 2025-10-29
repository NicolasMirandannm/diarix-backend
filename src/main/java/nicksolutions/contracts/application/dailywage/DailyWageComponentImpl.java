package nicksolutions.contracts.application.dailywage;

import nicksolutions.contracts.application.dailywage.dto.DailyWageDto;
import nicksolutions.contracts.application.dailywage.dto.DailyWorkRegisterDto;
import nicksolutions.contracts.domain.dailywage.DailyWage;
import nicksolutions.contracts.domain.dailywage.service.DailyWageService;
import nicksolutions.contracts.domain.dailywage.usecase.DailyWorkRegister;
import nicksolutions.core.crud.AbstractApplicationComponent;
import nicksolutions.core.crud.ApplicationMapper;
import nicksolutions.core.shared.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DailyWageComponentImpl extends AbstractApplicationComponent<DailyWageDto, DailyWage, DailyWageService> implements DailyWageComponent {

  private final DailyWorkRegister dailyWorkRegister;

  @Autowired
  protected DailyWageComponentImpl(ApplicationMapper<DailyWageDto, DailyWage> mapper, DailyWageService service, DailyWorkRegister dailyWorkRegister) {
    super(mapper, service);
    this.dailyWorkRegister = dailyWorkRegister;
  }

  @Override
  public Page<DailyWageDto> findWithFilters(String dayLaborerName, String enterpriseName,
                                            LocalDate workDate, PaymentStatus status, Pageable pageable) {
    return service.findWithFilters(dayLaborerName, enterpriseName, workDate, status, pageable)
        .map(mapper::toDto);
  }

  @Override
  public List<DailyWageDto> registerDailyWork(DailyWorkRegisterDto dailyWorkRegisterDto) {
    return dailyWorkRegister.execute(dailyWorkRegisterDto).stream().map(this.mapper::toDto).toList();
  }
}