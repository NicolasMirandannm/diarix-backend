package nicksolutions.contracts.rest;

import nicksolutions.contracts.application.dayLaborer.DayLaborerComponent;
import nicksolutions.contracts.application.dayLaborer.dto.DayLaborerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/day-laborer")
public class DayLaborerController {

    private final DayLaborerComponent dayLaborerComponent;

    public DayLaborerController(DayLaborerComponent dayLaborerComponent) {
        this.dayLaborerComponent = dayLaborerComponent;
    }

    @GetMapping
    public Page<DayLaborerDto> findAll(@PageableDefault Pageable pageable) {
        return dayLaborerComponent.list(pageable.getPageNumber(), pageable.getPageSize());
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
}
