package patrick96.ad_java.search;

public class Search {

    public static <T extends Comparable<T>> int binarySearch(T[] array, T needle) {
        return binarySearch(array, needle, 0, array.length - 1);
    }

    /**
     * Performs a binary search on a sorted (in ascending order) array
     * 
     * The binary search operates by cutting the search space in half
     * in each iteration.
     * Since the array is sorted we know for any element in the array, 
     * if the needle is further right or further left of that element
     * (or actually is that element). If we only compare to the middle
     * element of the current search we can eliminate all the elements
     * to the left or to the right of it as possible candidates.
     *
     * O(log(n))
     *
     * @param array The array sorted in ascending order of distinct comparable objects to search in
     * @param needle The not null element to search for inside the array
     * @param left The leftmost array index that should still be included in the search
     * @param right The rightmost array index that should still be included in the search
     * @return A non-negative integer representing the index where the needle was found in the given range
     *         otherwise a negative integer k, where |k| - 1 is the index the given needle 
     *         would be inserted into the array
     */
    public static <T extends Comparable<T>> int binarySearch(T[] array, T needle, int left, int right) {

        while(left <= right) {
            /* 
             * The index of the middle element of the current search space
             * the search space is halfed each iteration
             */
            int pivotIndex = (right + left) / 2;
            int cmp = needle.compareTo(array[pivotIndex]);

            if(cmp > 0) {
                /*
                 * The needle is greater than the pivot
                 * It must be further right
                 */
                left = pivotIndex + 1;
            }
            else if(cmp < 0) {
                /*
                 * The needle is lesser than the pivot
                 * It must be further left
                 */
                right = pivotIndex - 1;
            }
            else {
                return pivotIndex;
            }
        }

        return -(left + 1);
    }


    public static <T extends Comparable<T>> int linearSearch(T[] array, T needle) {
        return binarySearch(array, needle, 0, array.length - 1);
    }

    /**
     * Performs a linear search on an unsorted array
     * 
     * The algorithm operates by simply iterating over all the array elements
     * and checking for equivalence.
     * 
     * O(n)
     *
     * @param array The array of distinct comparable objects to be searched
     * @param needle The not null element to search for inside the array
     * @param left The leftmost array index that should still be included in the search
     * @param right The rightmost array index that should still be included in the search
     * @return A non-negative integer representing the index where the needle was found
     *         -1 if the element could not be found
     */
    public static <T extends Comparable<T>> int linearSearch(T[] array, T needle, int left, int right) {
        for (int i = left; i <= right; i++) {
            if(needle.compareTo(array[i]) == 0) {
                return i;
            }
        }

        return -1;
    }

}
