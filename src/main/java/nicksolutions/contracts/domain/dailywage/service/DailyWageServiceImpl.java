package nicksolutions.contracts.domain.dailywage.service;

import nicksolutions.contracts.domain.dailywage.DailyWage;
import nicksolutions.contracts.domain.dailywage.DailyWageRepository;
import nicksolutions.core.crud.BaseAbstractServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DailyWageServiceImpl extends BaseAbstractServiceImpl<DailyWage, DailyWageRepository> implements DailyWageService {

  public DailyWageServiceImpl(DailyWageRepository repository) {
    super(repository);
  }
}
