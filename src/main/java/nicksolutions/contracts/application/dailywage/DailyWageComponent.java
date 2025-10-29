package nicksolutions.contracts.application.dailywage;

import nicksolutions.contracts.application.dailywage.dto.DailyWageDto;
import nicksolutions.contracts.application.dailywage.dto.DailyWorkRegisterDto;
import nicksolutions.core.crud.ApplicationComponent;

import java.util.List;

public interface DailyWageComponent extends ApplicationComponent<DailyWageDto> {

  List<DailyWageDto> registerDailyWork(DailyWorkRegisterDto dailyWorkRegisterDto);

}
