package nicksolutions.contracts.rest;

import nicksolutions.contracts.application.dailywage.DailyWageComponent;
import nicksolutions.contracts.application.dailywage.dto.DailyWageDto;
import nicksolutions.core.shared.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/daily-wage")
public class DailyWageController {

    private final DailyWageComponent dailyWageComponent;

    public DailyWageController(DailyWageComponent dailyWageComponent) {
        this.dailyWageComponent = dailyWageComponent;
    }

  @GetMapping
  public Page<DailyWageDto> findAll(
      @RequestParam(required = false) String dayLaborerName,
      @RequestParam(required = false) String enterpriseName,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate workDate,
      @RequestParam(required = false) PaymentStatus status,
      @PageableDefault Pageable pageable) {

    return dailyWageComponent.findWithFilters(dayLaborerName, enterpriseName, workDate, status, pageable);
  }

    @GetMapping("/{id}")
    public DailyWageDto findById(@PathVariable String id) {
        return dailyWageComponent.getById(id);
    }

    @PostMapping
    public DailyWageDto create(@RequestBody DailyWageDto dailyWageDto) {
        return dailyWageComponent.create(dailyWageDto);
    }

    @PutMapping("/{id}")
    public DailyWageDto update(@PathVariable String id, @RequestBody DailyWageDto dailyWageDto) {
        return dailyWageComponent.update(id, dailyWageDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        dailyWageComponent.delete(id);
    }
}
