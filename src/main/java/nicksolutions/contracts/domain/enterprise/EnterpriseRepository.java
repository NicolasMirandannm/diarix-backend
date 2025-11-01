package nicksolutions.contracts.domain.enterprise;

import nicksolutions.core.crud.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EnterpriseRepository extends BaseRepository<Enterprise>, JpaSpecificationExecutor<Enterprise> {
}
