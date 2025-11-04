package nicksolutions.contracts.rest;

import nicksolutions.contracts.application.dayLaborer.DayLaborerComponent;
import nicksolutions.contracts.application.dayLaborer.dto.DayLaborerDto;
import nicksolutions.core.shared.StatusRegister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/day-laborer")
public class DayLaborerController {

  private final DayLaborerComponent dayLaborerComponent;

  public DayLaborerController(DayLaborerComponent dayLaborerComponent) {
    this.dayLaborerComponent = dayLaborerComponent;
  }

  @GetMapping
  public Page<DayLaborerDto> findAll(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String cpf,
      @RequestParam(required = false) String phoneNumber,
      @RequestParam(required = false) StatusRegister status,
      @PageableDefault Pageable pageable) {

    return dayLaborerComponent.findWithFilters(name, cpf, phoneNumber, status, pageable);
  }

  @GetMapping("/pending-payments")
  public List<DayLaborerDto> findAllWithPendingPayments() {
    return dayLaborerComponent.findAllWithPendingPayments();
  }

  @GetMapping("/{id}")
  public DayLaborerDto findById(@PathVariable String id) {
    return dayLaborerComponent.getById(id);
  }

  @PostMapping
  public DayLaborerDto create(@RequestBody DayLaborerDto dayLaborerDto) {
    return dayLaborerComponent.create(dayLaborerDto);
  }

  @PutMapping("/{id}")
  public DayLaborerDto update(@PathVariable String id, @RequestBody DayLaborerDto dayLaborerDto) {
    return dayLaborerComponent.update(id, dayLaborerDto);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable String id) {
    dayLaborerComponent.delete(id);
  }

  @GetMapping("/available")
  public Page<DayLaborerDto> availableLaborers(@RequestParam LocalDate date, @RequestParam LocalTime startHour, @RequestParam LocalTime endHour, @PageableDefault Pageable pageable) {
    return dayLaborerComponent.findAvailable(date, startHour, endHour, pageable);
  }
}
