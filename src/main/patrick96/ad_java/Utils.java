package patrick96.ad_java;

public class Utils {

    /**
     * Swaps the elements at pos1 and pos2 in the given array
     * 
     * If pos1 or pos2 are not in the array, this will throw an
     * ArrayIndexOutOfBoundsException
     *
     * @param array The array to swap in
     * @param pos1
     * @param pos2
     */
    public static void swap(int[] array, int pos1, int pos2) {
        int old1 = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = old1;
    }

}
