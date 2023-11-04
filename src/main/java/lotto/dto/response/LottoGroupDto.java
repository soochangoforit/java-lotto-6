package lotto.dto.response;

import java.util.List;
import lotto.model.Lotto;
import lotto.model.LottoGroup;

public class LottoGroupDto {
    private final int size;
    private final List<LottoDto> lottos;

    private LottoGroupDto(int size, List<LottoDto> lottos) {
        this.size = size;
        this.lottos = lottos;
    }

    public static LottoGroupDto from(LottoGroup lottoGroup) {
        List<Lotto> purchasedLottos = lottoGroup.getPurchasedLottos();
        List<LottoDto> lottoDtos = purchasedLottos.stream()
                .map(LottoDto::from)
                .toList();

        return new LottoGroupDto(purchasedLottos.size(), lottoDtos);
    }

    public int getSize() {
        return size;
    }

    public List<LottoDto> getLottos() {
        return lottos;
    }
}
