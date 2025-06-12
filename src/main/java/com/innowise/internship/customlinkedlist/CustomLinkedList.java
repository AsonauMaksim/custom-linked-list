package com.innowise.internship.customlinkedlist;

import java.util.NoSuchElementException;

public class CustomLinkedList<E> implements CustomList<E> {

    private Node<E> first;
    private Node<E> last;
    private int size = 0;

    public CustomLinkedList() {
        first = null;
        last = null;
    }

    private static class Node<E> {
        E value;
        Node<E> next;
        Node<E> prev;

        public Node(E value) {
            this.value = value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(E element) {

        Node<E> newNode = new Node<>(element);

        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        }
        size++;
    }

    @Override
    public void addLast(E element) {

        Node<E> newNode = new Node<>(element);

        if (last == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, E element) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        if (index == 0) {
            addFirst(element);
            return;
        }
        if (index == size) {
            addLast(element);
            return;
        }

        Node<E> current = getNode(index);
        Node<E> newNode = new Node<>(element);

        Node<E> prev = current.prev;

        newNode.prev = prev;
        newNode.next = current;

        prev.next = newNode;
        current.prev = newNode;

        size++;
    }

    @Override
    public E getFirst() {

        if (first == null) {
            throw new NoSuchElementException("List is empty");
        }
        return first.value;
    }

    @Override
    public E getLast() {

        if (last == null) {
            throw new NoSuchElementException("List is empty");
        }
        return last.value;
    }

    @Override
    public E get(int index) {

        Node<E> current = getNode(index);
        return current.value;
    }

    @Override
    public E removeFirst() {

        if (first == null) {
            throw new NoSuchElementException("List is empty");
        }

        E value = first.value;

        if (first == last) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        size--;
        return value;
    }

    @Override
    public E removeLast() {
        if (last == null) {
            throw new NoSuchElementException("List is empty");
        }

        E value = last.value;

        if (first == last) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        size--;
        return value;
    }

    @Override
    public E remove(int index) {

        Node<E> nodeToRemove = getNode(index);
        E value = nodeToRemove.value;

        if (nodeToRemove == first) {
            return removeFirst();
        } else if (nodeToRemove == last) {
            return removeLast();
        } else {
            Node<E> prev = nodeToRemove.prev;
            Node<E> next = nodeToRemove.next;

            prev.next = next;
            next.prev = prev;

            size--;
            return value;
        }
    }

    private Node<E> getNode(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        Node<E> current;
        if (index < size / 2) {
            current = first;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = last;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }
}
