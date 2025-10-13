package nicksolutions.core.crud;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class BaseEntityNonMultiTenancy extends BaseEntity {

  @PrePersist
  public void prePersist() {
    super.prePersist();
  }

  @PreUpdate
  public void preUpdate() {
    super.preUpdate();
  }
}
