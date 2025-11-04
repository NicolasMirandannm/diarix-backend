package nicksolutions.contracts.domain.payment;

import nicksolutions.core.crud.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface PaymentRepository extends BaseRepository<Payment> {
  Page<Payment> findByDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
}
