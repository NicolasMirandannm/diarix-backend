package nicksolutions.contracts.domain.daylaborer.service;

import nicksolutions.contracts.domain.daylaborer.DayLaborer;
import nicksolutions.contracts.domain.daylaborer.DayLaborerRepository;
import nicksolutions.core.crud.BaseAbstractServiceImpl;
import nicksolutions.core.shared.StatusRegister;
import org.springframework.stereotype.Service;

@Service
public class DayLaborerServiceImpl extends BaseAbstractServiceImpl<DayLaborer, DayLaborerRepository> implements DayLaborerService {

    public DayLaborerServiceImpl(DayLaborerRepository repository) {
        super(repository);
    }

    @Override
    public DayLaborer save(DayLaborer entity) {
        if (entity.isNew()) {
            entity.setStatus(StatusRegister.ATIVO);
        }
        return super.save(entity);
    }

}
