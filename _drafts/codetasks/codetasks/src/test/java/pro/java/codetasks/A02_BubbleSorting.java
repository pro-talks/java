package pro.java.codetasks;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * @author Serzh Nosov created on 18.01.2020.
 */
public class A02_BubbleSorting {

    @Test
    public void main() {
        Integer[] array = getUnsortedSequence(100);
        sort(array);
        log(array);
    }

    private void sort(Integer[] array) {
        for (int y = 0; y < array.length; y++) {
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] < array[i + 1]) {
                    int tmp = array[i + 1];
                    array[i + 1] = array[i];
                    array[i] = tmp;
                }
            }
        }
    }

    private Integer[] getUnsortedSequence(int length) {
        List<Integer> list = IntStream.iterate(0, i -> i + 1).limit(length).boxed().collect(Collectors.toList());
        Collections.shuffle(list);
        Integer[] array = list.toArray(new Integer[length]);
        log(array);
        return array;
    }

    private void log(Integer[] array) {
        System.out.println(Arrays.toString(array));
    }

}
