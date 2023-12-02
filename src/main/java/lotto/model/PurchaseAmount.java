package lotto.model;

public class PurchaseAmount {
    private final int amount;

    private PurchaseAmount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("음수 값은 구매 금액이 될 수 없습니다.");
        }
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException("구매 금액은 1,000원 단위가 되어야 합니다.");
        }
    }

    public static PurchaseAmount from(int amount) {
        return new PurchaseAmount(amount);
    }

}
