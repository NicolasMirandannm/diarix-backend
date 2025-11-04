package nicksolutions.contracts.rest;

import nicksolutions.contracts.application.payment.PaymentComponent;
import nicksolutions.contracts.application.payment.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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

  @PostMapping("/{id}/statement/pdf")
  public ResponseEntity<Resource> downloadPaymentStatement(@PathVariable String id) throws IOException {
    File pdfFile = paymentComponent.generatePaymentStatementPdf(id);

    byte[] data = Files.readAllBytes(pdfFile.toPath());
    ByteArrayResource resource = new ByteArrayResource(data);
    pdfFile.delete();

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pdfFile.getName() + "\"")
        .contentType(MediaType.APPLICATION_PDF)
        .contentLength(data.length)
        .body(resource);
  }
}
