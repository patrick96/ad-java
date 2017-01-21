package patrick96.ad_java;

import java.util.Random;

public class TestUtils {

    private static Random rand = new Random();
    
    public static int[] getRandomArray(int size) {
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(10000) - 5000;
        }

        return array;
    }
}
