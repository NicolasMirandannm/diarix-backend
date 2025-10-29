package nicksolutions.contracts.domain.dailywage.service;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import nicksolutions.contracts.domain.dailywage.DailyWage;
import nicksolutions.contracts.domain.daylaborer.DayLaborer;
import nicksolutions.contracts.domain.enterprise.Enterprise;
import nicksolutions.core.shared.PaymentStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DailyWageSpecifications {

  public static Specification<DailyWage> withFilters(
      String dayLaborerName,
      String enterpriseName,
      LocalDate workDate,
      PaymentStatus status) {

    return (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();

      if (StringUtils.hasText(dayLaborerName)) {
        Join<DailyWage, DayLaborer> dayLaborerJoin = root.join("dayLaborer");
        predicates.add(criteriaBuilder.like(
            criteriaBuilder.lower(dayLaborerJoin.get("name")),
            "%" + dayLaborerName.toLowerCase() + "%"
        ));
      }

      if (StringUtils.hasText(enterpriseName)) {
        Join<DailyWage, Enterprise> enterpriseJoin = root.join("enterprise");
        predicates.add(criteriaBuilder.like(
            criteriaBuilder.lower(enterpriseJoin.get("name")),
            "%" + enterpriseName.toLowerCase() + "%"
        ));
      }

      if (workDate != null) {
        predicates.add(criteriaBuilder.equal(root.get("workDate"), workDate));
      }

      if (status != null) {
        predicates.add(criteriaBuilder.equal(root.get("paymentStatus"), status));
      }

      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }
}