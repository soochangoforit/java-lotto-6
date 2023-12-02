package lotto.model;

import java.util.List;

public class WinningCombination {
    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    private WinningCombination(Lotto winningLotto, LottoNumber bonusNumber) {
        validate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validate(Lotto winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public static WinningCombination of(Lotto winningLotto, LottoNumber bonusNumber) {
        return new WinningCombination(winningLotto, bonusNumber);
    }

    public boolean containsLottoNumber(LottoNumber number) {
        return winningLotto.contains(number);
    }

    public boolean containsBonusNumber(List<LottoNumber> numbers) {
        return numbers.contains(bonusNumber);
    }
}
