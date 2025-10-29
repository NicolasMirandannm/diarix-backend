package nicksolutions.contracts.domain.dailywage;

import nicksolutions.core.crud.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DailyWageRepository extends BaseRepository<DailyWage>, JpaSpecificationExecutor<DailyWage> {
}
