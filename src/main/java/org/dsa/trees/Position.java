package org.dsa.trees;

public interface Position<T> {
    /**
     * Returns the element stored at this position
     *
     * @return the stored element
     * @throws IllegalStateException
     * */
    T getElement() throws IllegalStateException;
}
