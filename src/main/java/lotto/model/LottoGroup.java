package lotto.model;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class LottoGroup {
    private final List<Lotto> lottos;

    private LottoGroup(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static LottoGroup create(LottoCount lottoCount, NumberGenerator numberGenerator) {
        List<Lotto> lottos = Stream.generate(
                        () -> Lotto.create(numberGenerator))
                .limit(lottoCount.getCount())
                .toList();

        return new LottoGroup(lottos);
    }

    public LottoMatchingResult calculateWinningStatistics(WinningCombination winningCombination) {
        Map<LottoResult, Integer> statistics = new EnumMap<>(LottoResult.class);
        // TODO : winningCombination.match 가 적절할까? lotto.match 가 적절할까?
        for (Lotto lotto : lottos) {
            LottoResult result = lotto.match(winningCombination);
            statistics.put(result, statistics.getOrDefault(result, 0) + 1);
        }
        return LottoMatchingResult.from(statistics);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
