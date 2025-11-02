package nicksolutions.contracts.rest;

import nicksolutions.contracts.application.payment.PaymentComponent;
import nicksolutions.contracts.application.payment.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

  private final PaymentComponent paymentComponent;

  @Autowired
  public PaymentController(PaymentComponent paymentComponent) {
    this.paymentComponent = paymentComponent;
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
