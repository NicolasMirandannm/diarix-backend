package nicksolutions.core.crud;

public interface ApplicationMapper <Dto, Entity extends BaseEntity> {
  Dto toDto(Entity entity);
  Entity toEntity(Dto dto);
}
