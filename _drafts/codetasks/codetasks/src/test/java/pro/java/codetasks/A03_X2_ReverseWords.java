package pro.java.codetasks;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Serzh Nosov created on 19.01.2020.
 */
public class A03_X2_ReverseWords {

    @Test
    public void main() {
        String str = "Hello world! I will change everything!";

        String[] words = str.split("\\s");
        StringBuilder reverseWords = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reverseWords.append(words[i]).append(" ");
        }
        /*List<String> strings = Arrays.asList(words);
        Collections.reverse(strings);
        strings.*/

        System.out.println(reverseWords);

        if(!"everything! change will I world! Hello ".equals(reverseWords.toString()))
            throw new IllegalStateException();
    }
}
