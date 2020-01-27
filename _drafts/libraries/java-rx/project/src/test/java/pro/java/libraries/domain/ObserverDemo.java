package pro.java.libraries.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Serzh Nosov (Serzh.Nosov@lanit-tercom.com) created on 27.01.20.
 */
public class ObserverDemo {

    public static void main(String[] args) {
        EventSource eventSource = new EventSource();

        eventSource.addObserver(event -> System.out.println("Observer 1: Receive number: " + event));
        eventSource.addObserver(event -> System.out.println("Observer 2: Receive number: " + event));

        eventSource.scanSystemIn();
    }
}

class EventSource {

    @FunctionalInterface
    interface Observer {
        void action(String s);
    }

    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer action) {
        observers.add(action);
    }

    private void notifyObservers(String value) {
        observers.forEach(o -> o.action(value));
    }

    public void scanSystemIn() {
        System.out.println("Enter message:");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String value = scanner.next();
            notifyObservers(value);
        }
    }
}

