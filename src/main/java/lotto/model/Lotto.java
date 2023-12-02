package lotto.model;

import java.util.List;

public class Lotto {
    private static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = convertFrom(numbers);
    }

    private List<LottoNumber> convertFrom(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::from)
                .toList();
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("하나의 로또에는 로또 번호는 6개여야 합니다.");
        }
        if (numbers.stream().distinct().count() != LOTTO_SIZE) {
            throw new IllegalArgumentException("하나의 로또에는 로또 번호가 중복되지 않아야 합니다.");
        }
    }

    public static Lotto create(NumberGenerator numberGenerator) {
        return new Lotto(
                numberGenerator.generateUniqueNumbers(LottoNumber.LOTTO_NUMBER_MIN, LottoNumber.LOTTO_NUMBER_MAX,
                        LOTTO_SIZE));
    }

    public static Lotto from(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }
}
