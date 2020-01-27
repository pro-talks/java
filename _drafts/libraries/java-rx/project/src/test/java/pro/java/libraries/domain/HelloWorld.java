package pro.java.libraries.domain;

import io.reactivex.rxjava3.core.Flowable;

/**
 * @author Serzh Nosov (Serzh.Nosov@lanit-tercom.com) created on 27.01.20.
 */
public class HelloWorld {

    public static void main(String[] args) {
        Flowable.just("Hello world")
                .subscribe(System.out::println);
    }

}
