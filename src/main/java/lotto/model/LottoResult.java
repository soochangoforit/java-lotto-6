package lotto.model;

import java.util.stream.Stream;

public enum LottoResult {
    NONE(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000);

    private final int requiredMatchCount;
    private final int winningMoney;

    LottoResult(int requiredMatchCount, int winningMoney) {
        this.requiredMatchCount = requiredMatchCount;
        this.winningMoney = winningMoney;
    }

    // TODO : 리펙토링 필요 (깔끔하게 할 수 있는 방법)
    public static LottoResult of(int countOfMatch, boolean isBonusNumberMatch) {
        LottoResult result = Stream.of(values())
                .filter(lottoResult -> lottoResult.requiredMatchCount == countOfMatch)
                .findFirst()
                .orElse(NONE);

        if (result == THIRD && isBonusNumberMatch) {
            return SECOND;
        }
        return result;
    }

    public long calculateWinningMoney(int matchedCount) {
        return (long) winningMoney * matchedCount;
    }

    public int getRequiredMatchCount() {
        return requiredMatchCount;
    }

    public int getWinningMoney() {
        return winningMoney;
    }
}
