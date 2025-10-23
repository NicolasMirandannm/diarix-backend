package nicksolutions.contracts.rest;

import nicksolutions.contracts.application.dailywage.DailyWageComponent;
import nicksolutions.contracts.application.dailywage.dto.DailyWageDto;
import nicksolutions.contracts.application.dayLaborer.dto.DayLaborerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/daily-wages")
public class DailyWageController {

    private final DailyWageComponent dailyWageComponent;

    public DailyWageController(DailyWageComponent dailyWageComponent) {
        this.dailyWageComponent = dailyWageComponent;
    }

    @GetMapping
    public Page<DailyWageDto> findAll(@PageableDefault Pageable pageable) {
        return dailyWageComponent.list(pageable.getPageNumber(), pageable.getPageSize());
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
