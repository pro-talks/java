package pro.java.codetasks;

import org.junit.Test;

/**
 * @author Serzh Nosov created on 19.01.2020.
 */
public class A06_DeadLock {

    private static final String LOCK_1 = new String("LOCK_1");
    private static final String LOCK_2 = new String("LOCK_2");

    @Test
    public void main() throws InterruptedException {
        Thread thread1 = new Thread(this::method1);
        Thread thread2 = new Thread(this::method2);

        thread1.start();
        thread2.start();

        System.out.println("Threads are started!");

        thread1.join();
        thread2.join();

        System.out.println("Threads are finished!");
    }

    public void method1() {
        synchronized (LOCK_2) {
            System.out.println("method1 -> " + LOCK_2);
            synchronized (LOCK_1) {
                System.out.println("method1 -> " + LOCK_1);
            }
        }
    }

    public void method2() {
        synchronized (LOCK_1) {
            System.out.println("method2 -> " + LOCK_1);
            synchronized (LOCK_2) {
                System.out.println("method2 -> " + LOCK_2);
            }
        }
    }
}
