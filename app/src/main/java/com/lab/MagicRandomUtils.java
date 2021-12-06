package com.lab;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public final class MagicRandomUtils {

    private MagicRandomUtils() {}

    public static int[] generateRandomCombination(int from, int to, int size) {
        return Arrays.stream(
                ThreadLocalRandom
                        .current()
                        .ints(from, to)
                        .distinct()
                        .limit(size)
                        .sorted()
                        .boxed().toArray(Integer[]::new)
        ).mapToInt(Integer::intValue).toArray();
    }
}
