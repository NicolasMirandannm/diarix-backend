package nicksolutions.core.crud;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class AbstractApplicationComponent<Dto, Entity extends BaseEntity, Service extends BaseService<Entity>> implements ApplicationComponent<Dto> {

  protected final ApplicationMapper<Dto, Entity> mapper;
  protected final Service service;

  protected AbstractApplicationComponent(ApplicationMapper<Dto, Entity> mapper, Service service) {
    this.mapper = mapper;
    this.service = service;
  }

  @Override
  public Dto getById(String id) {
    Entity entity = service.findByIdOrThrow(id);
    return mapper.toDto(entity);
  }

  @Override
  public Dto create(Dto dto) {
    Entity entity = mapper.toEntity(dto);
    Entity savedEntity = service.save(entity);
    return mapper.toDto(savedEntity);
  }

  @Override
  public Dto update(String id, Dto dto) {
    Entity existingEntity = service.findByIdOrThrow(id);
    Entity entityToUpdate = mapper.toEntity(dto);
    entityToUpdate.setId(existingEntity.getId());

    return mapper.toDto(service.save(entityToUpdate));
  }

  @Override
  public void delete(String id) {
    service.deleteById(id);
  }

  @Override
  public Page<Dto> list(int page, int size) {
    Pageable pageable = Pageable.ofSize(size).withPage(page);
    Page<Entity> entities = service.findAll(pageable);
    return entities.map(mapper::toDto);
  }
}
