package lotto.model;

import java.util.List;
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
}
