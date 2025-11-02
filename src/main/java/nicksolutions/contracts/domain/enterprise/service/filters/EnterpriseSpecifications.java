package nicksolutions.contracts.domain.enterprise.service.filters;

import jakarta.persistence.criteria.Predicate;
import nicksolutions.contracts.domain.enterprise.Enterprise;
import nicksolutions.core.shared.StatusRegister;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EnterpriseSpecifications {

  public static Specification<Enterprise> withFilters(
      String name,
      String cnpj,
      String ownerName,
      StatusRegister status) {

    return (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();

      if (StringUtils.hasText(name)) {
        predicates.add(criteriaBuilder.like(
            criteriaBuilder.lower(root.get("name")),
            "%" + name.toLowerCase() + "%"
        ));
      }

      if (StringUtils.hasText(cnpj)) {
        predicates.add(criteriaBuilder.like(
            criteriaBuilder.lower(root.get("cnpj")),
            "%" + cnpj.toLowerCase() + "%"
        ));
      }

      if (StringUtils.hasText(ownerName)) {
        predicates.add(criteriaBuilder.like(
            criteriaBuilder.lower(root.get("ownerName")),
            "%" + ownerName.toLowerCase() + "%"
        ));
      }

      if (status != null) {
        predicates.add(criteriaBuilder.equal(root.get("status"), status));
      }

      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }
}
