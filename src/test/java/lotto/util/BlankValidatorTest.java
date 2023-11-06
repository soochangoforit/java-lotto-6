package lotto.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BlankValidatorTest {
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void validateBlankToFail(String input) {
        assertThatThrownBy(() -> BlankValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "가", "5000"})
    void validateBlankToSuccess(String input) {
        assertDoesNotThrow(() -> BlankValidator.validate(input));
    }
}
