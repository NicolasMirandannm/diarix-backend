package nicksolutions.contracts.domain.dailywage;

import nicksolutions.core.crud.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DailyWageRepository extends BaseRepository<DailyWage>, JpaSpecificationExecutor<DailyWage> {

  List<DailyWage> findAllByIdIn(List<String> ids);

}
