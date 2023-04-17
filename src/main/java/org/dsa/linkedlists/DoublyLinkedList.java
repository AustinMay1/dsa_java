package org.dsa.linkedlists;

public class DoublyLinkedList<T> {

    private class Node<T> {
        private T element;
        private Node<T> next;
        private Node<T> prev;

        public Node(T element, Node<T> next, Node<T> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        public T getElement() {
            return this.element;
        }

        public Node<T> getPrev() {
            return this.prev;
        }

        public Node<T> getNext() {
            return this.next;
        }

        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    } // end of Node class

    private Node<T> header; // header sentinel
    private Node<T> trailer; // trailer sentinel
    private int size = 0;

    public DoublyLinkedList() {
        this.header = new Node<>(null, null, null);
        this.trailer = new Node<>(null, null, header); // trailer is preceded by header
        header.setNext(trailer); // header is followed by trailer
    } // upon init, header & trailer sentinel nodes are created

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public T first() {
        if (isEmpty())
            return null;

        return header.getNext().getElement(); // first element is beyond header sentinel node
    }

    public T last() {
        if (isEmpty())
            return null;

        return trailer.getPrev().getElement(); // last element precedes trailer sentinel node
    }

    /* Private update-utility methods */

    private void addBetween(T element, Node<T> next, Node<T> prev) {
        Node<T> newest = new Node<>(element, next, prev);

        prev.setNext(newest);
        next.setPrev(newest);

        size++;
    } // adds element to list in-between the given nodes

    private T remove(Node<T> node) {
        Node<T> prev = node.getPrev();
        Node<T> next = node.getNext();

        prev.setNext(next);
        next.setPrev(prev);

        size--;

        return node.getElement();
    } // removes the given node from the list

    /* Public update methods */

    public void addFirst(T element) {
        addBetween(element, header.getNext(), header); // place just after the header sentinel node
    }

    public void addLast(T element) {
        addBetween(element, trailer, trailer.getPrev()); // place just before the trailer sentinel node
    }

    public T removeFirst() {
        if (isEmpty())
            return null;

        return remove(header.getNext()); // first element is after the header sentinel node
    }

    public T removeLast() {
        if (isEmpty())
            return null;

        return remove(trailer.getPrev()); // last element is before the trailer sentinel node
    }

    /* TODO: implement insertAt(position), deleteAt(position) methods */
}
