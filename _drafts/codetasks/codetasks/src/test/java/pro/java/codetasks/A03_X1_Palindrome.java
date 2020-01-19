package pro.java.codetasks;

import org.junit.Test;

import java.util.Objects;

/**
 *
 * @author Serzh Nosov created on 18.01.2020.
 */
public class A03_X1_Palindrome {

    @Test
    public void main() {
        if(isPalindrome("Hello world"))
            throw new IllegalArgumentException();
        if(isPalindrome("march"))
            throw new IllegalArgumentException();
        if(isPalindrome("" + 1234))
            throw new IllegalArgumentException();


        if(!isPalindrome("шалаш"))
            throw new IllegalArgumentException();
        if(!isPalindrome("bob"))
            throw new IllegalArgumentException();
        if(!isPalindrome("" + 1001))
            throw new IllegalArgumentException();
    }

    private boolean isPalindrome(String str) {
        String reversed = reverseString(str);
        return Objects.equals(reversed, str);

    }

    private String reverseString(String str) {
        char[] chars = str.toCharArray();
        for (int left = 0, right = chars.length - 1; left < right; left++, right--) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
        }
        return new String(chars);
    }
}
