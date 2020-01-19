package pro.java.codetasks;

import org.junit.Test;

/**
 * @author Serzh Nosov created on 19.01.2020.
 */
public class A05_PrimeNumber {

    @Test
    public void main() {
        // Primes
        if (!isPrime(4))
            throw new IllegalStateException();
        if (!isPrime(6))
            throw new IllegalStateException();
        if (!isPrime(15))
            throw new IllegalStateException();

        // Not Primes
        if (isPrime(1))
            throw new IllegalStateException();
        if (isPrime(2))
            throw new IllegalStateException();
        if (isPrime(3))
            throw new IllegalStateException();
        if (isPrime(13))
            throw new IllegalStateException();
        if (isPrime(17))
            throw new IllegalStateException();
        if (isPrime(127))
            throw new IllegalStateException();
    }

    private boolean isPrime(int n) {
        if(n == 1 || n == 2 || n == 3)
            return false;

        if(n % 2 == 0 || n % 3 == 0)
            return true;

        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return true;

        return false;
    }


}
