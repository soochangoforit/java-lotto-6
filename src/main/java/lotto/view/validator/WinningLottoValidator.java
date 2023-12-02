package lotto.view.validator;

import java.util.regex.Pattern;

public final class WinningLottoValidator {
    private static final Pattern DIGITS_WITH_COMMA_SEPARATED = Pattern.compile("[0-9]+(,[0-9]+)*");
    private static final String FORMAT_EXCEPTION = "숫자(양의 정수)와 ',' 형식만 입력 가능합니다.";

    private WinningLottoValidator() {
    }

    public static void validate(String input) {
        if (!matchesPattern(input, DIGITS_WITH_COMMA_SEPARATED)) {
            throw new IllegalArgumentException(FORMAT_EXCEPTION);
        }
    }

    private static boolean matchesPattern(String input, Pattern pattern) {
        return pattern.matcher(input).matches();
    }
}
