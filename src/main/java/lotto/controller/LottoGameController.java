package lotto.controller;

import java.util.function.Supplier;
import lotto.model.PurchaseAmount;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGameController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        PurchaseAmount purchaseAmount = fetch(this::readPurchaseAmount);

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
