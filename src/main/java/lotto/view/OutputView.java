package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.model.Lotto;
import lotto.model.LottoGroup;
import lotto.model.LottoMatchingResult;
import lotto.model.LottoNumber;
import lotto.model.LottoResult;

public class OutputView {
    private static final String EXCEPTION_FORMAT = "[ERROR] %s";

    public void printExceptionMessage(String message) {
        println(String.format(EXCEPTION_FORMAT, message));
    }

    private void println(String message) {
        System.out.println(message);
    }

    public void printProfitRate(double profitRate) {
        String formattedProfitRate = String.format("%,.1f", profitRate);
        println(String.format("총 수익률은 %s%%입니다.", formattedProfitRate));
    }

    public void printLottoResult(LottoMatchingResult lottoResult) {
        Map<LottoResult, Integer> statistics = lottoResult.getStatistics();
        println("당첨 통계");
        println("---");
        for (LottoResult lottoResultKey : LottoResult.values()) {
            if (lottoResultKey == LottoResult.NONE) {
                continue;
            }
            printResult(lottoResultKey, statistics);
        }
    }

    private void printResult(LottoResult lottoResultKey, Map<LottoResult, Integer> statistics) {
        int requiredMatchCount = lottoResultKey.getRequiredMatchCount();
        int winningMoney = lottoResultKey.getWinningMoney();
        String formattedWinningMoney = String.format("%,d", winningMoney);
        int matchedCount = statistics.getOrDefault(lottoResultKey, 0);

        String message = String.format("%d개 일치 (%s원) - %d개", requiredMatchCount, formattedWinningMoney,
                matchedCount);
        if (lottoResultKey == LottoResult.SECOND) {
            message = String.format("%d개 일치, 보너스 볼 일치 (%s원) - %d개", requiredMatchCount, formattedWinningMoney,
                    matchedCount);
        }

        println(message);
    }

    public void printLottoGroup(LottoGroup lottoGroup) {
        List<Lotto> lottos = lottoGroup.getLottos();
        println(String.format("%d개를 구매했습니다.", lottos.size()));
        lottos.forEach(this::printLotto);
        printEmptyLine();
    }

    private void printLotto(Lotto lotto) {
        List<Integer> numbers = lotto.getNumbers()
                .stream()
                .map(LottoNumber::getNumber)
                .sorted()
                .toList();
        println(numbers.toString());
    }

    private void printEmptyLine() {
        System.out.println();
    }

}
