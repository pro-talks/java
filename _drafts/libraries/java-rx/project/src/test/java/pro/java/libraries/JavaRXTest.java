package pro.java.libraries;


import io.reactivex.rxjava3.core.Observable;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;

public class JavaRXTest {

    @Test
    public void javaRXTest1(){

        AtomicReference<String> result = new AtomicReference<>();
        Observable<String> observable = Observable.just("Hello");
        observable.subscribe(result::set);

        assertEquals("Hello", result.get());


        /*Observable<String> o = Observable.fromArray("a", "b", "c");

        def list = [5, 6, 7, 8]
        Observable<Integer> o = Observable.from(list);

        Observable<String> o = Observable.just("one object");*/
    }

    @Test
    public void javaRXTest2() {
        AtomicReference<String> result = new AtomicReference<>("");
        String[] letters = {"a", "b", "c", "d", "e", "f", "g"};
        Observable<String> observable = Observable.fromArray(letters);
        observable.subscribe(
                i -> result.set(result.get() + i),  //OnNext
                Throwable::printStackTrace, //OnError
                () -> result.set(result.get() + "_Completed") //OnCompleted
        );
        assertEquals("abcdefg_Completed", result.get());
    }


    @Test
    public void javaRXTest3() {
        AtomicReference<String> result = new AtomicReference<>("");
        String[] letters = {"a", "b", "c", "d", "e", "f", "g"};
        Observable.fromArray(letters)
                .map(String::toUpperCase)
                .subscribe(letter -> result.set(result.get() + letter));
        assertEquals("ABCDEFG", result.get());
    }

    @Test
    public void javaRXTest4() {
        AtomicReference<String> result = new AtomicReference<>("");
        String[] letters = {"a", "b", "c"};
        Observable.fromArray(letters)
                .scan(new StringBuilder(), StringBuilder::append)
                .subscribe(total -> result.set(result.get() + total.toString()));
        assertEquals("aababc", result.get());
    }

    @Test
    public void javaRXTest5() {
        AtomicReference<String> EVEN = new AtomicReference<>("");
        AtomicReference<String> ODD = new AtomicReference<>("");
        Observable.fromArray(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .groupBy(i -> 0 == (i % 2) ? "EVEN" : "ODD")
                .subscribe(group ->
                        group.subscribe(number -> {
                            if (number != null) {
                                if (group.getKey().equals("EVEN")) {
                                    EVEN.set(EVEN.get() + number);
                                } else {
                                    ODD.set(ODD.get() + number);
                                }
                            }
                        })
                );
        assertEquals("0246810", EVEN.get());
        assertEquals("13579", ODD.get());
    }


    Observable.from(numbers)
            .filter(i -> (i % 2 == 1))
            .subscribe(i -> result += i);

    assertTrue(result.equals("13579"));
}