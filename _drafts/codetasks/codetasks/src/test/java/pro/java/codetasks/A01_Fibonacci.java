package pro.java.codetasks;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Write a simple Java program which will print Fibonacci series, e.g. 1 1 2 3 5 8 13 ... up to a given number.
 * We prepare for cross questions like using iteration over recursion and how to optimize the solution using caching and memoization.
 */
public class A01_Fibonacci {

    private Map<String, Integer> cache = new HashMap<>();

    /**
     * Кешировать нужно по номеру числа Фибоначи
     *
     * public static int improvedFibo(int number){
     *         Integer fibonacci = cache.get(number);
     *         if(fibonacci != null){
     *             return fibonacci; //fibonacci number from cache
     *         }
     *         //fibonacci number not in cache, calculating it
     *         fibonacci = fibonacci2(number);
     *
     *         //putting fibonacci number in cache for future request
     *         cache.put(number, fibonacci);
     *         return fibonacci;
     *     }
    */

    @Test
    public void main() {

        int numbers = 12;

        for (int i = 1; i <= numbers; i++) {
            System.out.println(fib2(i));
        }

        /*for (int i = 1; i <= numbers; i++) {
            int number = getCachedNumberIfExists(i);
            System.out.println(number);
        }*/
    }

    private int fib2(int numbers) {
        return fibInner2(0, 1, numbers);
    }

    private int fibInner2(int first, int second, int number) {
        if (number <= 1)
            return second;
        else
            return fibInner2(second, (first + second), --number);
    }


    public static void main(String[] args) {
        System.out.println(fib(10));

    }

    private static int fib(int n) {

        return fibInternal(0,1,n);

    }

    //Recursion here :)
    private static int fibInternal(int a, int b, int n) {

        if (n<=1)
            return b;
        else
            return fibInternal(b, (a+b), n-1);
    }



    /*private int getCachedNumberIfExists(int number) {
        Integer value = cache.get(number);
        if (value != null) return value;

        value = getFibonacciNumber2(number);

        cache.put(number, value);
        return value;
    }*/

    /**
     * RECURSION
     *
     * @param i
     * @return
     */
    private int getFibonacciNumber(int i) {
        if (i == 1 || i == 2) return 1;

        return getFibonacciNumber(i - 1) + getFibonacciNumber(i - 2);
    }

    /**
     * ITERATE
     *
     * @param i
     * @return
     */
    private int getFibonacciNumber2(int i) {
        if (i == 1 || i == 2) return 1;

        int first = 1, second = 1, sum = 0;
        for (int j = 3; j <= i; j++) {
            sum = first + second;
            first = second;
            second = sum;
        }

        return sum;
    }

    /**
     * BORDER
     *
     * @param previous
     * @param next
     * @param border
     */
    private void printFibonacciNumbersTo(int previous, int next, int border) {
        if (border < next) return;

        System.out.println(next);
        int current = previous + next;
        printFibonacciNumbersTo(next, current, border);
    }
}
