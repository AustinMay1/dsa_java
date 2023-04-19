package org.dsa.trees;

/** Binary Tree using a node-based, linked structure */
public class LinkedBinaryTree<T> extends AbstractBinaryTree<T> {
    
    // nested node class
    protected static class Node<T> implements Position<T> {
        private T element;
        private Node<T> parent;
        private Node<T> left;
        private Node<T> right;

        public Node(T element, Node<T> parent, Node<T> leftChild, Node<T> rightChild) {
            this.element = element;
            this.parent = parent;
            this.left = leftChild;
            this.right = rightChild;
        }

        // access methods
        public T getElement() { return element; }
        public Node<T> getParent() { return parent; }
        public Node<T> getLeftChild() { return left; }
        public Node<T> getRightChild() { return right; }

        // update methods
        public void setElement(T element) { this.element = element; }
        public void setParent(Node<T> parent) { this.parent = parent; }
        public void setLeftChild(Node<T> leftChild) { this.left = leftChild; }
        public void setRightChild(Node<T> rightChild) { this.right = rightChild; }

    } // end nested node class

    /** Factory func to create a new node */
    protected Node<T> createNode(T element, Node<T> parent, Node<T> leftChild, Node<T> rightChild) {
        return new Node<T>(element, parent, leftChild, rightChild);
    }

    // LinkedBinaryTree members
    protected Node<T> root = null;
    private int size = 0;

    public LinkedBinaryTree() { }

    protected Node<T> validate(Position<T> p) throws IllegalArgumentException {
        if(!(p instanceof Node)) {
            throw new IllegalArgumentException("Invalid position type.");
        }

        Node<T> node = (Node<T>) p;

        if(node.getParent() == node) {
            throw new IllegalArgumentException("p is no longer in the tree.");
        }

        return node;
    }

    // access methods
    public int size() { return this.size; }

    public Position<T> root() { return this.root; }

    public Position<T> parent(Position<T> p) throws IllegalArgumentException {
        Node<T> node = validate(p);

        return node.getParent();
    }

    public Position<T> left(Position<T> p) throws IllegalArgumentException {
        Node<T> node = validate(p);

        return node.getLeftChild();
    }

    public Position<T> right(Position<T> p) throws IllegalArgumentException {
        Node<T> node = validate(p);

        return node.getRightChild();
    }

    // update methods
    public Position<T> addRoot(T element) throws IllegalStateException {
        if(!isEmpty()) throw new IllegalStateException("Tree is not empty.");

        this.root = createNode(element, null, null, null);
        this.size++;

        return root;
    }

    public Position<T> addLeft(Position<T> p, T element) throws IllegalArgumentException {
        Node<T> parent = validate(p);

        if(parent.getLeftChild() != null) throw new IllegalArgumentException("p already has a left child.");

        Node<T> child = createNode(element, parent, null, null);

        parent.setLeftChild(child);
        this.size++;

        return child;
    }

    public Position<T> addRight(Position<T> p, T element) throws IllegalArgumentException {
        Node<T> parent = validate(p);

        if(parent.getRightChild() != null) throw new IllegalArgumentException("p already has a right child.");

        Node<T> child = createNode(element, parent, null, null);

        parent.setRightChild(child);
        this.size++;

        return child;
    }

    /** Replaces the element at Position p with e and returns the replaced element */
    public T set(Position<T> p, T element) throws IllegalArgumentException {
        Node<T> node = validate(p);

        T temp = node.getElement();

        node.setElement(element);

        return temp;
    }

    public void attach(Position<T> p, LinkedBinaryTree<T> t1, LinkedBinaryTree<T> t2) throws IllegalArgumentException {
        Node<T> node = validate(p);

        if(isInternal(p)) throw new IllegalArgumentException("p must be a leaf.");

        this.size += t1.size() + t2.size();

        if(!t1.isEmpty()) {
            t1.root.setParent(node);
            node.setLeftChild(t1.root);
            t1.root = null;
            t1.size = 0;
        }

        if(!t2.isEmpty()) {
            t2.root.setParent(node);
            node.setRightChild(t2.root);
            t2.root = null;
            t2.size = 0;
        }
    }

    public T remove(Position<T> p) throws IllegalArgumentException {
        Node<T> node = validate(p);

        if(numChildren(p) == 2) throw new IllegalArgumentException("p has 2 children.");

        Node<T> child = (node.getLeftChild() != null ? node.getLeftChild() : node.getRightChild());

        if(child != null) child.setParent(node.getParent());

        if(node == root) {
            this.root = child;
        } else {
            Node<T> parent = node.getParent();

            if(node == parent.getLeftChild()) {
                parent.setLeftChild(child);
            } else {
                parent.setRightChild(child);
            }
        }

        this.size--;
        T temp = node.getElement();
        node.setElement(null);
        node.setLeftChild(null);
        node.setRightChild(null);
        node.setParent(node);

        return temp;
    }

}
