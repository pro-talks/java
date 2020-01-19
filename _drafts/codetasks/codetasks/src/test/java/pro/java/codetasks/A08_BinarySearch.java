package pro.java.codetasks;

import org.junit.Test;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author Serzh Nosov created on 19.01.2020.
 */
public class A08_BinarySearch {

    @Test
    public void main() {
        int[] array = IntStream.iterate(0, i -> new Random().nextInt(44))
                .limit(20)
                .distinct()
                .sorted()
                .toArray();

        System.out.println(Arrays.toString(array));

        int value = 22;
        int index = binarySearch(array, value);
        int jdkIndex = Arrays.binarySearch(array, value);

        if (jdkIndex > 0 && !Objects.equals(jdkIndex, index))
            throw new IllegalStateException();
    }

    private int binarySearch(int[] array, int value) {
        if (array == null || array.length == 0) return -1;

        int low = 0, high = array.length - 1;
        return findIndex(array, value, low, high);
    }

    private int findIndex(int[] array, int value, int low, int high) {
        if(low > high) return -1;

        int middle = (low + high) / 2;
        if (array[middle] == value)
            return middle;

        if (array[middle] > value) {
            return findIndex(array, value, low, middle - 1);
        } else {
            return findIndex(array, value, middle + 1, high);
        }
    }
}
