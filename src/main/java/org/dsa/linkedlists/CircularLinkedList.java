package org.dsa.linkedlists;

public class CircularLinkedList<T> {

    private class Node<T> {
        private T element;
        private Node<T> next;

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
    } // end of Node class

    private Node<T> tail = null;
    private int size = 0;

    public CircularLinkedList() {
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

        return tail.getNext().getElement();
    }

    public T last() {
        if (isEmpty())
            return null;

        return tail.getElement();
    }

    public void rotate() {
        if (tail != null) {
            tail = tail.getNext(); // the old head becomes the new tail
        }
    }

    public void addFirst(T element) {
        if (size == 0) {
            tail = new Node<>(element, null); // adds element to the front of the list
            tail.setNext(tail); // link to itself circularly
        } else {
            Node<T> newest = new Node<>(element, tail.getNext());
            tail.setNext(newest);
        }

        size++;
    }

    public void addLast(T element) {
        addFirst(element); // insert new element at the front of the list
        tail = tail.getNext(); // now the new element becomes the tail
    }

    public T removeFirst() {
        if (isEmpty())
            return null;

        Node<T> head = tail.getNext();

        if (head == tail) {
            tail = null;
        } else {
            tail.setNext(head.getNext());
        }

        size--;

        return head.getElement();
    }
}
