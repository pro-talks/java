package pro.java.codetasks;

import org.junit.Test;

/**
 * @author Serzh Nosov created on 18.01.2020.
 */
public class A04_X01_ReverseSinglyLinkedList {

    @Test
    public void main() {
        // creating a singly linked list
        SinglyLinkedListX01<Integer> list = new SinglyLinkedListX01<>();

        list.add(1);
        list.add(2);
        list.add(3);

        list.print();
        list.reverse();
        list.print();
    }
}

class SinglyLinkedListX01<E> {

    private static class Node<E> {

        private E data;
        private Node<E> next;

        public Node(E data) {
            this.data = data;
        }

        public E data() {
            return data;
        }

        public Node<E> next() {
            return next;
        }
    }

    private Node<E> head;
    private Node<E> current;

    public void add(E data) {
        Node<E> node = new Node<>(data);
        if (current == null) {
            current = node;
            head = current;
        } else {
            current.next = node;
            current = current.next;
        }
    }


    public void print() {
        current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    /**
     * сложно понять, что нужно делать 2е вещи
     * это менять ссылки
     * и по прежнему итерироваться в привильном направлении
     */
    public void reverse() {
        current = head;
        Node<E> previous = null;
        while (current != null) {
            Node<E> next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        head = previous;
    }
}
