package nicksolutions.contracts.domain.daylaborer.service.filters;

import nicksolutions.contracts.domain.daylaborer.DayLaborer;
import nicksolutions.core.shared.StatusRegister;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class DayLaborerSpecifications {

  public static Specification<DayLaborer> withFilters(
      String name,
      String cpf,
      String phoneNumber,
      StatusRegister status) {

    return (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();

      if (StringUtils.hasText(name)) {
        predicates.add(criteriaBuilder.like(
            criteriaBuilder.lower(root.get("name")),
            "%" + name.toLowerCase() + "%"
        ));
      }

      if (StringUtils.hasText(cpf)) {
        predicates.add(criteriaBuilder.like(
            criteriaBuilder.lower(root.get("cpf")),
            "%" + cpf.toLowerCase() + "%"
        ));
      }

      if (StringUtils.hasText(phoneNumber)) {
        predicates.add(criteriaBuilder.like(
            criteriaBuilder.lower(root.get("phoneNumber")),
            "%" + phoneNumber.toLowerCase() + "%"
        ));
      }

      if (status != null) {
        predicates.add(criteriaBuilder.equal(root.get("status"), status));
      }

      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }
}
