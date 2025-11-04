package nicksolutions.contracts.rest;

import nicksolutions.contracts.application.payment.PaymentComponent;
import nicksolutions.contracts.application.payment.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/payments")
public class PaymentController {

  private final PaymentComponent paymentComponent;

  @Autowired
  public PaymentController(PaymentComponent paymentComponent) {
    this.paymentComponent = paymentComponent;
  }

  @GetMapping
  public Page<PaymentDto> findAll(
      @RequestParam(required = false) String paymentId,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
      @PageableDefault Pageable pageable) {

    return paymentComponent.findWithFilters(paymentId, startDate, endDate, pageable);
  }

  @GetMapping("/{id}")
  public PaymentDto findById(@PathVariable String id) {
    return paymentComponent.getById(id);
  }

  @PostMapping
  public PaymentDto create(@RequestBody PaymentDto paymentDto) {
    return paymentComponent.create(paymentDto);
  }

  @PutMapping("/{id}")
  public PaymentDto update(@PathVariable String id, @RequestBody PaymentDto paymentDto) {
    return paymentComponent.update(id, paymentDto);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable String id) {
    paymentComponent.delete(id);
  }
}
