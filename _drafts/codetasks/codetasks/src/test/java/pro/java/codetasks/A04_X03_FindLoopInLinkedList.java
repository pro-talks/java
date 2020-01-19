package pro.java.codetasks;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @author Serzh Nosov created on 19.01.2020.
 */
public class A04_X03_FindLoopInLinkedList {

    @Test
    public void main() {
        SinglyLinkedListX03<Integer> list = new SinglyLinkedListX03<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        list.print();

    }

}


class SinglyLinkedListX03<E> {

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
    private Node<E> tail;

    public void add(E data) {
        Node<E> node = new Node<>(data);
        if (head == null) {
            head = node;
            tail = head;
        } else {
            tail.next = node;
            tail = tail.next;
        }
    }

    public void print() {
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}