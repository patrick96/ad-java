package patrick96.ad_java.search;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class SearchTest {

    @Test
    public void binarySearchTest() throws Exception {
        /* 
         * Generates 10'000 random arrays and performs a binary search on each
         * array element as well as random elements not inside the array
         */
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            Integer[] array = new Integer[rand.nextInt(1000)];

            int oldVal = 0;
            // Generate a sorted array
            for(int j = 0; j < array.length; j++) {
                array[j] = oldVal + rand.nextInt(10000) + 1;
                oldVal = array[j];
            }

            /*
             * Make sure negative values are not found since the array does not contain them
             */
            for (int j = 0; j < 100; j++) {
                assertEquals(-1, Search.binarySearch(array, -rand.nextInt(10000)));
            }

            /*
             * Search for every single array element
             */
            for (int j = 0; j < array.length; j++) {
                int result = Search.binarySearch(array, array[j]);
                assertEquals(j, result);
            }

            /*
             * Search for random elements
             * The return value should be the same as the one given by Arrays.binarySearch
             */
            for (int j = 0; j < 10000; j++) {
                int val = rand.nextInt(1000000000);
                assertEquals(Arrays.binarySearch(array, 0, array.length, val), Search.binarySearch(array, val));
            }
        }
    }
}
