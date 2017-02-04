package patrick96.ad_java.sort;

import java.util.Arrays;

import patrick96.ad_java.Utils;

/**
 * Implementations of elementary search algorithms
 * The algorithms implemented here are in-situi and sort in ascending order unless noted otherwise
 * 
 */
public class Sort {

    /**
     * Performs an in-situi bubblesort on the given array
     * 
     * The algorithm iterates through the array and always checks two adjacent
     * elements, if the two elements are not in sorted order it will swap them.
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

    /**
     * Performs an in-situi insertionsort on the given array
     * 
     * Insertion sort assumes that the first k elements of the array are already sorted, 
     * it will then expand that assumption by one, by calculating the would-be position of the
     * (k+1)th element (using binary search).
     * This way, when k=n the array will be sorted.
     *
     * Runtime:
     * Key comparisons: O(n * log(n))
     * Key swaps      : O(n^2)
     *
     * @param array
     * @return
     */
    public static int[] insertionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            /*
             * Invariant: All the elements up to and including index i are sorted
             */

            int pivot = array[i + 1];

            /*
             * Search for the would-be position of the the array at index i in the sorted array
             */
            int left = 0, right = i;
            int newPos = -1;
            while(left <= right) {
                int middle = (left + right) / 2;
                int midVal = array[middle];
                if(pivot < midVal) {
                    right = middle - 1;
                }
                else if(pivot > midVal) {
                    left = middle + 1;
                }
                else {
                    newPos = middle;
                    break;
                }
            }
            
            if(newPos < 0) {
                newPos = left;
            }

            /*
             * Shift all elements starting from newPos one to the right
             */
            for (int j = i; j >= newPos; j--) {
                array[j + 1] = array[j];
            }
            array[newPos] = pivot;
        }

        return array;
    }
    
}
