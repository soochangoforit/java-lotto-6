package lotto.model;

public class LottoMachine {
    private final LottoGroup lottoGroup;
    private final WinningCombination winningCombination;

    private LottoMachine(LottoGroup lottoGroup, WinningCombination winningCombination) {
        this.lottoGroup = lottoGroup;
        this.winningCombination = winningCombination;
    }

    public static LottoMachine of(LottoGroup lottoGroup, WinningCombination winningCombination) {
        return new LottoMachine(lottoGroup, winningCombination);
    }

    public LottoMatchingResult calculateWinningStatistics() {
        return lottoGroup.calculateWinningStatistics(winningCombination);
    }
}
