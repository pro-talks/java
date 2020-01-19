package pro.java.codetasks;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Serzh Nosov created on 19.01.2020.
 */
public class A04_X02_FindMiddleInOnPass {

    @Test
    public void main() {
        // creating a singly linked list
        /*SinglyLinkedListX02<Integer> list = new SinglyLinkedListX02<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        list.print();

        Integer middle = list.getMiddleElement();

        System.out.println("Middle -> " + middle);*/


        List<Integer> list2 = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list2.add(i);
        }
        list2.forEach(System.out::println);

        int middleLength = list2.size() / 2;
        int length = 0;
        Iterator<Integer> it = list2.iterator();
        Integer middle = null;
        while (it.hasNext()) {
            length++;
            middle = it.next();
            if (length == middleLength)
                break;
        }

        System.out.println("Middle -> " + middle);



        //while (list.hasNext())
            //System.out.println(list.next());

        //list.add(1);
        //list.add(2);
        //list.add(3);

        //list.print();
        //list.reverse();
        //list.print();
    }

}

class SinglyLinkedListX02<E> {

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
        Node<E> eNode = new Node<>(data);
        if (head == null) {
            head = eNode;
            tail = head;
        } else {
            tail.next = eNode;
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

    /**
     * В первый раз ты опереируешь данными которые ты знаешь.
     * И не видишьш решения этой задачи.
     * Значить нужно расширить структура данных и
     * ввести дополнительные сосотояния!!!
     * @return
     */
    public E getMiddleElement() {
        Node<E> current = head;
        Node<E> middle = head;
        int length = 0;
        while (current != null) {
            length++;
            if (length % 2 == 0)
                middle = middle.next;
            current = current.next;
        }
        return (middle == null) ? null : middle.data;
    }
}