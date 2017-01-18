package patrick96.ad_java.search;

public class Search {

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
     * @return A non-negative integer representing the index where the needle was found
     *         -1 if the element could not be found
     */
    public static <T extends Comparable<T>> int binarySearch(T[] array, T needle) {
        // Lower and upper bounds of the search space
        int left = 0;
        int right = array.length - 1;

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

        return -1;
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
     * @return A non-negative integer representing the index where the needle was found
     *         -1 if the element could not be found
     */
    public static <T extends Comparable<T>> int linearSearch(T[] array, T needle) {
        for (int i = 0; i < array.length; i++) {
            if(needle.compareTo(array[i]) == 0) {
                return i;
            }
        }

        return -1;
    }

}
