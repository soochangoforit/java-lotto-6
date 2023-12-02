package lotto.model;

public class LottoCount {
    private final int count;

    private LottoCount(int count) {
        validate(count);
        this.count = count;
    }

    private void validate(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("음수 값 또는 0은 로또 개수가 될 수 없습니다.");
        }
    }

    public static LottoCount from(int count) {
        return new LottoCount(count);
    }

    public int getCount() {
        return count;
    }
}
