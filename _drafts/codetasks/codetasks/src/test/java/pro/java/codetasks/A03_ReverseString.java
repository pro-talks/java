package pro.java.codetasks;

import org.junit.Test;

/**
 * This problem is similar to the String Palindrome problem we have discussed above.
 * If you can solve that problem, you can solve this as well.
 * You can use indexOf() or substring() to reverse a String or alternatively,
 * convert the problem to reverse an array by operating on character array instead of String.
 *
 * @author Serzh Nosov created on 18.01.2020.
 */
public class A03_ReverseString {

    @Test
    public void main() {
        char[] chars = "Hello world1".toCharArray();

        for (int left = 0, right = chars.length - 1; left < right; left++, right--) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
        }

        if (!"1dlrow olleH".equals(new String(chars)))
            throw new IllegalArgumentException();
    }

    /*
    str = new StringBuilder(str).reverse().toString();
    System.out.println(str);
    */
}
