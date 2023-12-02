package lotto.view;

import java.util.List;
import lotto.model.Lotto;
import lotto.model.LottoGroup;
import lotto.model.LottoNumber;

public class OutputView {
    private static final String EXCEPTION_FORMAT = "[ERROR] %s";

    public void printExceptionMessage(String message) {
        println(String.format(EXCEPTION_FORMAT, message));
    }

    private void println(String message) {
        System.out.println(message);
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
