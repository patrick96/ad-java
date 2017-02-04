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

    /**
     * Performs a sort by selection on the given array
     * 
     * Selectionsort assumes that the first k elements of the given array contains the
     * k smallest elements of the array in the right oder, it will then search the remaining
     * array for the smallest element and swap that element with the (k + 1)th element and thus
     * expands the sorted array.
     * 
     * Time Complexity:
     * Key comparisons: O(n^2)
     * Key swaps      : O(n)
     *
     * @param array
     * @return
     */
    public static int[] selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            /*
             * Invariant: The first i elements of the array are sorted and contain the
             * first i smallest elements of the array
             */

            /*
             * Search for the smallest element in the remaining array
             */
            int min = array[i];
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if(array[j] < min) {
                    minIndex = j;
                    min = array[j];
                }
            }

            /*
             * Since the smallest element in the remaining array is still greater or equals
             * to any of the elements in the sorted subarray, it can just be put at the end of
             * the sorted array thus increasing the size of the sorted array by one
             */
            Utils.swap(array, i, minIndex);
        }

        return array;
    }

    /**
     * Merges the two sorted subarrays into a single sorted array
     * The two subarrays are the ones from left to middle (inclusive) and from
     * middle + 1 to right (inclusive)
     * 
     * Time Complexity:
     * Key Comparisons: O(right - left)
     * Key wap        : O(right - left)
     *
     * @param array
     * @param left
     * @param middle
     * @param right
     */
    private static void merge(int[] array, int left, int middle, int right) {
        int[] merged = new int[right - left + 1];
        int i = left, j = middle + 1, k = 0;
        while(i <= middle && j <= right) {
            if(array[i] <= array[j]) {
                merged[k] = array[i];
                i++;
            }
            else {
                merged[k] = array[j];
                j++;
            }
            
            k++;
        }

        /*
         * The merging will finish as soon as all elements of one array was completely
         * added to the merged array
         * Here we just attach the remaining part of the array that was not fully merged
         */
        while(i <= middle) {
            merged[k] = array[i];
            i++;
            k++;
        }
        while(j <= right) {
            merged[k] = array[j];
            j++;
            k++;
        }

        /*
         * Reinsert the merged subarray into the array
         */
        for (i = left; i <= right; i++) {
            array[i] = merged[i - left];
        }
    }

    /**
     * Performs mergesort recursively on the given array 
     * 
     * Mergesort is a Divide-And-Conquer approach to sorting, it splits the array in half
     * and sorts each half recursively and then merges these two arrays into a signle sorted
     * array
     * 
     * The problem with the recursive approach is that large arrays will lead to a 
     * StackOverflow
     * 
     * Time Complexity:
     * Key comparisons: O(n * log(n))
     * Key swaps      : O(n * log(n))
     * 
     * Space Complextiy O(n)
     *
     * @param array
     * @param left
     * @param right
     * @return The given array with the subarray from index left to right (inclusive) sorted
     */
    public static int[] mergeSortRecursive(int[] array, int left, int right) {
        if(left == right) {
            return array;
        }

        int middle = (left + right) / 2;
        mergeSortRecursive(array, left, middle);
        mergeSortRecursive(array, middle + 1, right);

        merge(array, left, middle, right);
        return array;
    }

    /**
     * Performs a bottom-up non-recursive mergersort on the given array
     * 
     * The bottom-up approach works by dividing up dividing up the array in subarrays
     * of length 1, then merging adjacent subarrays. Now we have sorted subarrays of length 2
     * which we can merge and so on. After each iteration the length of the sorted subarrays 
     * doubles. If there is an uneven amount of sorted subarrays, the last subarray will not be 
     * merged, but might be in the next iteration and at the very least will be merged on the 
     * last iteration (if n = 2^k + 1).
     * Since this approach does not use recursion, the size of the array does not impact the
     * functionality
     * 
     * Time Complexity:
     * Key comparisons: O(n * log(n))
     * Key swaps      : O(n * log(n))
     * 
     * Space Complextiy O(n)
     *
     * @param array
     * @return
     */
    public static int[] mergeSortStraight(int[] array) {
        int n = array.length;
        // Length of the subarrays
        int len = 1;
        while(len < n) {
            // The first index of the two subarrays to be merged
            int left = 0;
            while(left + len < n) {
                // The middle is the index of the last element in the first subarray
                int middle = left + len - 1;
                merge(array, left, middle, Math.min(middle + len, n - 1));
                // Left is now one after the last element of the second subarray
                left = middle + len + 1;
            }

            len *= 2;
        }
        return array;
    }
    
}
