package nicksolutions.core.crud;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<Entity extends BaseEntity> extends JpaRepository<Entity, String> {
}
