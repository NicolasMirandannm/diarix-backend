package nicksolutions.core.crud;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

  public abstract String getId();
  public abstract void setId(String id);

  @CreatedDate
  @Column(name = "created_date", updatable = false)
  private LocalDateTime createdDate;

  @CreatedBy
  @Column(name = "created_by", updatable = false)
  private String createdBy;

  @LastModifiedDate
  @Column(name = "modified_date")
  private LocalDateTime modifiedDate;

  @LastModifiedBy
  @Column(name = "modified_by")
  private String modifiedBy;

  @Version
  @Column(name = "version")
  private Long version;

  @PrePersist
  public void prePersist() {
    this.setId(UUID.randomUUID().toString());
  }
}
