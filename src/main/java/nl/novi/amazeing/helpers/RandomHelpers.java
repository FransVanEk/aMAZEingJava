package nl.novi.amazeing.helpers;

import java.util.Random;

public class RandomHelpers {
    private static final Random random = new Random(); // Creating a single Random instance for reuse

    public static int getRandomNumber(int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("Max must be greater than 0");
        }
        return random.nextInt(max);
    }
}
