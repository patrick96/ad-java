package patrick96.ad_java.sort;

import patrick96.ad_java.Utils;

public class Sort {

    /**
     * Performs an in-situi bubblesort on the given array
     * 
     * The algorithm iterates through the array and always checks two adjacent
     * elements, if the two elements are not in sorted order (ascending 
     * or descending) it will swap them.
     * It will perform this iteration n times
     * This will cause the largest array element to "bubble" to the end, in
     * other words during each iteration at least one element will be placed
     * in the right position at the end of the unsorted elements (all the 
     * elements after it will have been bubbled up in a previous iteration)
     * 
     * O(n^2)
     *
     * @param array
     * @return The array sorted in ascending order. Since the algorithm is
     *         in-situi the returned Object and the array parameter will be
     *         the same
     */
    public static int[] bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            // Flag to store, whether or not at least one element was swapped
            boolean didSwap = false;
            for (int j = 1; j < n - i; j++) {
                if(array[j - 1] > array[j]) {
                    didSwap = true;
                    Utils.swap(array, j, j - 1);
                }
            }

            if(!didSwap) {
                /*
                 * If no element was swapped during this iteration, then the
                 * array is now sorted, meaning we can finish early
                 *
                 * This does not impact the worst case but lowers the best
                 * case key comparisons to (n - 1)
                 */
                break;
            }
        }

        return array;
    }
    
}