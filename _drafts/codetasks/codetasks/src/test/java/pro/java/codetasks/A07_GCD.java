package pro.java.codetasks;

import org.junit.Test;

/**
 * @author Serzh Nosov created on 19.01.2020.
 */
public class A07_GCD {

    @Test
    public void main() throws InterruptedException {

        int number1 = 54, number2 = 24;

        Math.sqrt(64.0);

        System.out.println("GCD of two numbers " + number1 +" and "
                + number2 +" is :" + findGCD(number1,number2));
    }

    private static int findGCD(int number1, int number2) {
        //base case
        if(number2 == 0){
            return number1;
        }
        return findGCD(number2, number1%number2);
    }


}
