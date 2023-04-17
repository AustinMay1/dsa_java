package org.dsa.linkedlists;

public class SinglyLinkedList<T> {

    private static class Node<T> {

        private T element; // reference to the element stored at this node
        private Node<T> next; // reference to the subsequent node in the list

        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }

        public T getElement() {
            return this.element;
        }

        public Node<T> getNext() {
            return this.next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

    } // End of Node class

    private Node<T> head = null; // head node of the list (or null if empty)
    private Node<T> tail = null; // tail node of the list (or null if empty)
    private int size = 0; // number of nodes in list

    public SinglyLinkedList() {
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public T first() {
        if (isEmpty())
            return null;

        return head.getElement();
    }

    public T last() {
        if (isEmpty())
            return null;

        return tail.getElement();
    }

    public void addFirst(T element) {
        head = new Node<>(element, head);

        if (size == 0)
            tail = head;

        size++;
    }

    public void addLast(T element) {
        Node<T> newest = new Node<>(element, null);

        if (isEmpty()) {
            head = newest;
        } else {
            tail.setNext(newest);
        }

        tail = newest;
        size++;
    }

    public T removeFirst() {
        if (isEmpty())
            return null;

        T element = head.getElement();
        head = head.getNext();

        size--;

        if (size == 0)
            tail = null;

        return element;
    }
}
