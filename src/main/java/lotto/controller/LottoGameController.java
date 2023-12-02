package lotto.controller;

import java.util.List;
import java.util.function.Supplier;
import lotto.model.Lotto;
import lotto.model.LottoCount;
import lotto.model.LottoGroup;
import lotto.model.LottoNumber;
import lotto.model.NumberGenerator;
import lotto.model.PurchaseAmount;
import lotto.model.WinningCombination;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGameController {
    private final InputView inputView;
    private final OutputView outputView;
    private final NumberGenerator numberGenerator;

    public LottoGameController(InputView inputView, OutputView outputView, NumberGenerator numberGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.numberGenerator = numberGenerator;
    }

    public void run() {
        PurchaseAmount purchaseAmount = fetch(this::readPurchaseAmount);
        LottoCount lottoCount = purchaseAmount.calculateLottoCount();
        LottoGroup lottoGroup = LottoGroup.create(lottoCount, numberGenerator);
        outputView.printLottoGroup(lottoGroup);
        Lotto winningLotto = fetch(this::readWinningLotto);
        WinningCombination winningCombination = fetch(() -> createWinningCombination(winningLotto));

    }

    private WinningCombination createWinningCombination(Lotto winningLotto) {
        int rawBonusNumber = inputView.readBonusNumber();
        LottoNumber bonusNumber = LottoNumber.from(rawBonusNumber);
        return WinningCombination.of(winningLotto, bonusNumber);
    }

    private Lotto readWinningLotto() {
        List<Integer> winningLottoNumbers = inputView.readWinningLotto();
        return Lotto.from(winningLottoNumbers);
    }

    private PurchaseAmount readPurchaseAmount() {
        int rawPurchaseAmount = inputView.readPurchaseAmount();
        return PurchaseAmount.from(rawPurchaseAmount);
    }

    private <T> T fetch(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return fetch(supplier);
        }
    }
}
