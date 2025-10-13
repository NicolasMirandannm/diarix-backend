package nicksolutions.core.crud;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.context.SecurityContextHolder;

@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class BaseEntityMultiTenancy extends BaseEntity {

  public abstract String getContractManagerId();

  public abstract void setContractManagerId(String id);

  @Override
  @PrePersist
  public void prePersist() {
    super.prePersist();
    this.setContractManagerId(SecurityContextHolder.getContext().getAuthentication().getName());
  }

  @Override
  @PreUpdate
  public void preUpdate() {
    super.preUpdate();
  }
}
