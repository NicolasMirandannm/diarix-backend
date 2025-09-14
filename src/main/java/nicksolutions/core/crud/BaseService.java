package nicksolutions.core.crud;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BaseService<Entity extends BaseEntity> {

  Entity save(Entity entity);

  void deleteById(String id);

  Optional<Entity> findById(String id);

  Entity findByIdOrThrow(String id);

  Page<Entity> findAll(Pageable pageable);

}
