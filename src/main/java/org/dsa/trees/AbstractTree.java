package org.dsa.trees;

import java.util.*;

/**
 * An abstract base class providing some functionality of the Tree interface
 * */
public abstract class AbstractTree<T> implements Tree<T> {
    public boolean isInternal(Position<T> p) { return numChildren(p) > 0; }
    public boolean isExternal(Position<T> p) { return numChildren(p) == 0; }
    public boolean isRoot(Position<T> p) { return p == root(); }
    public boolean isEmpty() { return size() == 0; }
    /**
     * Returns the number of levels separating Position p from the root
     * */
    public int depth(Position<T> p) {
        if (isRoot(p)) {
            return 0;
        } else {
            return 1 + depth(parent(p));
        }
    }
    /**
     * Returns the height of the tree
     * Height = maximum of the depths of its positions
     * */
    public int height(Position<T> p) {
        int h = 0;

        for(Position<T> c : children(p)) {
            h = Math.max(h, 1 + height(c));
        }

        return h;
    }
    /** nested ElementIterator class
     *  adapts the iteration produced by positions() to return elements
     */
    private class ElementIterator implements Iterator<T> {
        Iterator<Position<T>> posIterator = positions().iterator();
        public boolean hasNext() { return posIterator.hasNext(); }
        public T next() { return posIterator.next().getElement(); }
        public void remove() { posIterator.remove(); }
    }

    public Iterator<T> iterator() { return new ElementIterator(); }
    
    public Iterable<Position<T>> positions() {
        return preorder();
    }

    private void preorderSubtree(Position<T> p, List<Position<T>> snapshot) {
        snapshot.add(p);
        for(Position<T> c : children(p)) {
            preorderSubtree(c, snapshot);
        }
    }

    public Iterable<Position<T>> preorder() {
        List<Position<T>> snapshot = new ArrayList<>();

        if(!isEmpty()) {
            preorderSubtree(root(), snapshot);
        }

        return snapshot;
    }
    
}
