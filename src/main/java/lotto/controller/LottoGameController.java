package lotto.controller;

import lotto.dto.request.BonusNumberDto;
import lotto.dto.request.InvestMoneyDto;
import lotto.dto.request.WinningNumbersDto;
import lotto.dto.response.LottoGroupDto;
import lotto.dto.response.TotalProfitRateDto;
import lotto.model.InvestMoney;
import lotto.model.Lotto;
import lotto.model.LottoGroup;
import lotto.model.LottoMachine;
import lotto.model.LottoNumber;
import lotto.model.LottoPrice;
import lotto.model.NumberGenerator;
import lotto.model.PurchasableLottoCount;
import lotto.model.TotalPrize;
import lotto.model.TotalProfitRate;
import lotto.model.WinningCombination;
import lotto.util.RetryUtil;
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
        InvestMoney investMoney = RetryUtil.retryOnFail(this::createInvestMoney);

        LottoGroup lottoGroup = RetryUtil.retryOnFail(this::createLottoGroup, investMoney);
        printLottoGroup(lottoGroup);

        TotalPrize totalPrize = calculateTotalPrizes(lottoGroup);
        printTotalPrize(totalPrize);
        printTotalProfitRate(totalPrize, investMoney);
    }

    private void printTotalProfitRate(TotalPrize totalPrize, InvestMoney investMoney) {
        TotalProfitRate totalProfitRate = totalPrize.calculateTotalProfitRate(investMoney);
        TotalProfitRateDto totalProfitRateDto = TotalProfitRateDto.from(totalProfitRate);
        outputView.printTotalProfit(totalProfitRateDto);
    }

    private TotalPrize calculateTotalPrizes(LottoGroup lottoGroup) {
        LottoMachine lottoMachine = createLottoMachine(lottoGroup);
        return lottoMachine.calculateTotalPrizes();
    }

    private LottoMachine createLottoMachine(LottoGroup lottoGroup) {
        Lotto winningLotto = RetryUtil.retryOnFail(this::createWinningLotto);
        WinningCombination winningCombination = RetryUtil.retryOnFail(this::createWinningCombination, winningLotto);
        return LottoMachine.of(lottoGroup, winningCombination);
    }

    private Lotto createWinningLotto() {
        WinningNumbersDto winningNumbersDto = RetryUtil.retryOnFail(inputView::readWinningNumbers);
        return Lotto.from(winningNumbersDto.getWinningNumbers());
    }

    private WinningCombination createWinningCombination(Lotto winningLotto) {
        LottoNumber bonusNumber = RetryUtil.retryOnFail(this::createBonusNumber);
        return WinningCombination.of(winningLotto, bonusNumber);
    }

    private LottoNumber createBonusNumber() {
        BonusNumberDto bonusNumberDto = RetryUtil.retryOnFail(inputView::readBonusNumber);
        return LottoNumber.from(bonusNumberDto.getBonusNumber());
    }

    private LottoGroup createLottoGroup(InvestMoney investMoney) {
        PurchasableLottoCount lottoCount = RetryUtil.retryOnFail(this::createLottoCount, investMoney);
        return LottoGroup.create(lottoCount, numberGenerator);
    }

    private PurchasableLottoCount createLottoCount(InvestMoney investMoney) {
        return investMoney.calculatePurchasableLottoCount(LottoPrice.STANDARD_PRICE);
    }

    private void printTotalPrize(TotalPrize totalPrize) {
        outputView.printTotalPrize(totalPrize);
    }

    private InvestMoney createInvestMoney() {
        InvestMoneyDto investMoneyDto = RetryUtil.retryOnFail(inputView::readInvestMoney);
        int investMoney = investMoneyDto.getInvestMoney();
        return InvestMoney.from(investMoney);
    }

    private void printLottoGroup(LottoGroup lottoGroup) {
        LottoGroupDto lottoGroupDto = LottoGroupDto.from(lottoGroup);
        outputView.printLottoGroup(lottoGroupDto);
    }
}
