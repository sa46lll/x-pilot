package org.xangle.xpilot.scheduler.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionFeeCalculatorService {

    private static final long WEI_UNIT = 1_000_000_000L;

    private TransactionFeeCalculatorService() {
    }

    public static BigDecimal calculate(Long gasPriceWei, Long gasUsed) {
        return getPriceGwei(gasPriceWei)
                .divide(BigDecimal.valueOf(WEI_UNIT))
                .multiply(BigDecimal.valueOf(gasUsed));
    }

    private static BigDecimal getPriceGwei(Long gasPriceWei) {
        return BigDecimal.valueOf(gasPriceWei).divide(BigDecimal.valueOf(WEI_UNIT));
    }
}
