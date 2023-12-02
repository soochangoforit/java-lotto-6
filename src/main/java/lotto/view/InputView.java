package lotto.view;

import java.util.List;
import java.util.stream.Stream;
import camp.nextstep.edu.missionutils.Console;
import lotto.view.validator.BlankValidator;
import lotto.view.validator.DigitsOnlyValidator;
import lotto.view.validator.WinningLottoValidator;

public class InputView {

    public int readPurchaseAmount() {
        println("구입금액을 입력해 주세요.");
        String rawPurchaseAmount = readLine();
        printEmptyLine();
        validatePurchaseAmount(rawPurchaseAmount);
        return convertToInt(rawPurchaseAmount);
    }

    private void validatePurchaseAmount(String rawPurchaseAmount) {
        BlankValidator.validate(rawPurchaseAmount);
        DigitsOnlyValidator.validate(rawPurchaseAmount);
    }

    private String readLine() {
        return Console.readLine().trim();
    }

    private int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자로 변환할 수 없는 문자입니다.");
        }
    }

    private void println(String message) {
        System.out.println(message);
    }

    private void printEmptyLine() {
        System.out.println();
    }

    public int readBonusNumber() {
        println("보너스 번호를 입력해 주세요.");
        String rawBonusNumber = readLine();
        printEmptyLine();
        validateBonusNumber(rawBonusNumber);
        return convertToInt(rawBonusNumber);
    }

    private void validateBonusNumber(String rawBonusNumber) {
        BlankValidator.validate(rawBonusNumber);
        DigitsOnlyValidator.validate(rawBonusNumber);
    }

    public List<Integer> readWinningLotto() {
        println("당첨 번호를 입력해 주세요.");
        String rawWinningLotto = readLine();
        printEmptyLine();
        validateWinningLotto(rawWinningLotto);
        return splitToInt(",", rawWinningLotto);
    }

    private void validateWinningLotto(String rawWinningLotto) {
        BlankValidator.validate(rawWinningLotto);
        WinningLottoValidator.validate(rawWinningLotto);
    }

    private List<Integer> splitToInt(String delimiter, String input) {
        return Stream.of(input.split(delimiter))
                .map(Integer::parseInt)
                .toList();
    }

    private void print(String message) {
        System.out.print(message);
    }

    private List<String> split(String format, String input) {
        return List.of(input.split(format));
    }

}
