package nicksolutions.contracts.domain.payment.usecase;

import nicksolutions.contracts.domain.dailywage.DailyWage;
import nicksolutions.contracts.domain.dailywage.service.DailyWageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class PaymentDailyWageCalculator {

  private final DailyWageService dailyWageService;

  @Autowired
  public PaymentDailyWageCalculator(DailyWageService dailyWageService) {
    this.dailyWageService = dailyWageService;
  }

  public BigDecimal compute(List<String> dailyWageIds) {
    return dailyWageService.findByIds(dailyWageIds).stream()
        .map(DailyWage::computePaymentValue)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
