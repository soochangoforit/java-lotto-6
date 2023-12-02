package lotto.model;

public class LottoNumber {
    public static final int LOTTO_NUMBER_MIN = 1;
    public static final int LOTTO_NUMBER_MAX = 45;

    private final int number;

    private LottoNumber(int number) {
        validate(number);
        this.number = number;
    }

    private void validate(int number) {
        if (number < LOTTO_NUMBER_MIN || number > LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException("로또 번호는 1 ~ 45 사이의 값이어야 합니다.");
        }
    }

    public static LottoNumber from(int number) {
        return new LottoNumber(number);
    }

    public int getNumber() {
        return number;
    }
}
