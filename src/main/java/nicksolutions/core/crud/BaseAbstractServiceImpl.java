package nicksolutions.core.crud;

import nicksolutions.core.exceptions.CustomHttpException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public abstract class BaseAbstractServiceImpl<Entity extends BaseEntity, Repo extends BaseRepository<Entity>> implements BaseService<Entity> {

  protected final Repo repository;

  public BaseAbstractServiceImpl(Repo repository) {
    this.repository = repository;
  }

  @Override
  public Entity save(Entity entity) {
    return repository.save(entity);
  }

  @Override
  public void deleteById(String id) {
    repository.deleteById(id);
  }

  @Override
  public Optional<Entity> findById(String id) {
    return repository.findById(id);
  }

  @Override
  public Entity findByIdOrThrow(String id) {
    return findById(id).orElseThrow(() -> CustomHttpException.notFound("Entidade não encontrada com id: " + id));
  }

  @Override
  public Page<Entity> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }
}
