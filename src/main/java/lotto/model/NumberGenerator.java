package lotto.model;

import java.util.List;

public interface NumberGenerator {
    List<Integer> generateUniqueNumbers(int min, int max, int size);
}
