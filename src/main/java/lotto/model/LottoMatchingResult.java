package lotto.model;

import java.util.EnumMap;
import java.util.Map;

public class LottoMatchingResult {
    private final Map<LottoResult, Integer> statistics;

    private LottoMatchingResult(Map<LottoResult, Integer> statistics) {
        this.statistics = new EnumMap<>(statistics);
    }

    public static LottoMatchingResult from(Map<LottoResult, Integer> statistics) {
        return new LottoMatchingResult(statistics);
    }

    public double calculateProfitRate(PurchaseAmount purchaseAmount) {
        long totalPrize = statistics.entrySet().stream()
                .mapToLong(entry -> entry.getKey().calculateWinningMoney(entry.getValue()))
                .sum();

        return ((double) totalPrize / purchaseAmount.getAmount()) * 100;
    }

    public Map<LottoResult, Integer> getStatistics() {
        return statistics;
    }
}
