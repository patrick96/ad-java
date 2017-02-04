package patrick96.ad_java.sort;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import patrick96.ad_java.TestUtils;

public class SortTest {

    public void assertSorted(int[] array) {
        for(int i = 1; i < array.length; i++) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

    public void assertCorrectSort(int[] original, int[] sorted) {
        // Check if array was sorted
        assertSorted(sorted);

        // Check if the elements of the array were not otherwise modified
        assertEquals("Arrays have the same size", original.length, sorted.length);
        Set<Integer> originalSet = Arrays.stream(original).boxed().collect(Collectors.toSet());;
        Set<Integer> sortedSet = Arrays.stream(sorted).boxed().collect(Collectors.toSet());;
        assertEquals("Arrays contain the same elements", originalSet, sortedSet);
    }

    @Test
    public void bubbleSortTest() throws Exception {
        for (int i = 0; i < 1000; i++) {
            int[] array = TestUtils.getRandomArray(1000);
            int[] original = array.clone();

            int[] sorted = Sort.bubbleSort(array);

            assertEquals(sorted, array);
            assertCorrectSort(original, array);
        }
    }

    @Test
    public void insertionSortTest() throws Exception {
        for (int i = 0; i < 1000; i++) {
            int[] array = TestUtils.getRandomArray(1000);
            int[] original = array.clone();

            int[] sorted = Sort.insertionSort(array);
            
            assertEquals(sorted, array);
            assertCorrectSort(original, array);
        }
    }

    @Test
    public void selectionSortTest() throws Exception {
        for (int i = 0; i < 1000; i++) {
            int[] array = TestUtils.getRandomArray(1000);
            int[] original = array.clone();

            int[] sorted = Sort.insertionSort(array);
            
            assertEquals(sorted, array);
            assertCorrectSort(original, array);
        }
    }

    @Test
    public void mergeSortRecursiveTest() throws Exception {
        for (int i = 0; i < 1000; i++) {
            int[] array = TestUtils.getRandomArray(1000);
            int[] original = array.clone();

            int[] sorted = Sort.insertionSort(array);
            
            assertEquals(sorted, array);
            assertCorrectSort(original, array);
        }
    }

}
