package pro.java.libraries.domain;

import io.reactivex.rxjava3.core.Flowable;

import java.util.Arrays;
import java.util.List;

/**
 * @author Serzh Nosov (Serzh.Nosov@lanit-tercom.com) created on 27.01.20.
 */
public class HelloWorld {

    public static void main(String[] args) {
        /*Flowable.just("Hello world")
                .subscribe(System.out::println);*/
        Integer[] objects = (Integer[]) Arrays.asList(1, 3, 5, 7, 99).toArray();
        Flowable.fromArray(objects)
                .subscribe(s -> System.out.println("Hello " + s + "!"));
    }

    /*public static void hello(String... args) {

    }*/

}
