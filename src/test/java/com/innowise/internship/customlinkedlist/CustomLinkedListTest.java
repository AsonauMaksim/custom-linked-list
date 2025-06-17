package com.innowise.internship.customlinkedlist;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class CustomLinkedListTest {

    @Test
    @DisplayName("addFirst() должен добавить элемент String в начало списка")
    void testAddFirstSingleElementString() {
        CustomList<String> list = new CustomLinkedList<>();
        list.addFirst("Three hundred bucks");

        assertEquals(1, list.size());
        assertEquals("Three hundred bucks", list.getFirst());
    }

    @Test
    @DisplayName("addFirst() должен добавить элемент Integer в начало списка")
    void testAddFirstSingleElementInteger() {
        CustomList<Integer> list = new CustomLinkedList<>();
        list.addFirst(300);

        assertEquals(1, list.size());
        assertEquals(300, list.getFirst());
    }

    @Test
    @DisplayName("addFirst() должен добавить null в начало списка")
    void testAddFirstSingleElementNull() {
        CustomList<Integer> list = new CustomLinkedList<>();
        list.addFirst(null);

        assertEquals(1, list.size());
        assertNull(list.getFirst());
    }

    @Test
    @DisplayName("addFirst() при многократных вызовах должен сдвигать элементы вправо")
    void testAddFirstMultiple() {
        CustomList<String> list = new CustomLinkedList<>();
        list.addFirst("Three");
        list.addFirst("hundred");
        list.addFirst("bucks");

        assertEquals(3, list.size());
        assertEquals("bucks", list.getFirst());
        assertEquals("Three", list.getLast());
        assertEquals("hundred", list.get(1));
    }

    @Test
    @DisplayName("add(index) в середину списка работает корректно")
    void testAddAtIndex() {
        CustomList<String> list = new CustomLinkedList<>();
        list.addLast("Three");
        list.addLast("bucks");
        list.add(1, "hundred");

        assertEquals("Three", list.get(0));
        assertEquals("hundred", list.get(1));
        assertEquals("bucks", list.get(2));
    }

    @Test
    @DisplayName("add(index) в позицию 0 — работает как addFirst()")
    void testAddAtIndexZero() {
        CustomList<String> list = new CustomLinkedList<>();
        list.add(0, "Баклажаны рвут резинку, бэээээу");

        assertEquals("Баклажаны рвут резинку, бэээээу", list.getFirst());
    }

    @Test
    @DisplayName("add(index) в позицию size — работает как addLast()")
    void testAddAtIndexSize() {
        CustomList<String> list = new CustomLinkedList<>();
        list.addFirst("MEK");
        list.addFirst("KEK");
        list.add(list.size(),"ABOBA");

        assertEquals("ABOBA", list.getLast());
    }

    @Test
    @DisplayName("add(index) с некорректным индексом выбрасывает исключение")
    void testAddWithInvalidIndex() {
        CustomList<String> list = new CustomLinkedList<>();

        assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, "X"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "X"));
    }

    @Test
    @DisplayName("get(index) возвращает правильный элемент")
    void testGet() {
        CustomList<String> list = new CustomLinkedList<>();
        list.add(0,"A");
        list.add(1,"B");

        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
    }

    @Test
    @DisplayName("get(index) с неверным индексом выбрасывает исключение")
    void testGetInvalidIndex() {
        CustomList<String> list = new CustomLinkedList<>();
        list.addFirst("abc");

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }

    @Test
    @DisplayName("getFirst() возвращает первый элемент")
    void testGetFirst() {
        CustomList<String> list = new CustomLinkedList<>();
        list.addFirst("abc");
        list.addFirst("def");
        list.addFirst("xyz");

        assertEquals("xyz", list.getFirst());
    }

    @Test
    @DisplayName("getLast() возвращает последний элемент")
    void testGetLast() {
        CustomList<String> list = new CustomLinkedList<>();
        list.addFirst("abc");
        list.addFirst("def");
        list.addFirst("xyz");

        assertEquals("abc", list.getLast());
    }

    @Test
    @DisplayName("getFirst() на пустом списке выбрасывает NoSuchElementException")
    void testGetFirstEmpty() {
        CustomList<String> list = new CustomLinkedList<>();

        assertThrows(NoSuchElementException.class, list::getFirst);
    }

    @Test
    @DisplayName("getLast() на пустом списке выбрасывает NoSuchElementException")
    void testGetLastEmpty() {
        CustomList<String> list = new CustomLinkedList<>();

        assertThrows(NoSuchElementException.class, list::getLast);
    }

    @Test
    @DisplayName("removeFirst() удаляет первый элемент и возвращает его")
    void testRemoveFirst() {
        CustomList<String> list = new CustomLinkedList<>();
        list.addFirst("A");
        list.addLast("B");

        String removed = list.removeFirst();
        assertEquals("A", removed);
        assertEquals(1, list.size());
    }

    @Test
    @DisplayName("removeLast() удаляет последний элемент и возвращает его")
    void testRemoveLast() {
        CustomList<String> list = new CustomLinkedList<>();
        list.addFirst("X");
        list.addLast("Y");

        String removed = list.removeLast();
        assertEquals("Y", removed);
        assertEquals(1, list.size());
    }

    @Test
    @DisplayName("removeFirst() на пустом списке выбрасывает NoSuchElementException")
    void testRemoveFirstEmpty() {
        CustomList<String> list = new CustomLinkedList<>();
        assertThrows(NoSuchElementException.class, list::removeFirst);
    }

    @Test
    @DisplayName("removeLast() на пустом списке выбрасывает NoSuchElementException")
    void testRemoveLastEmpty() {
        CustomList<String> list = new CustomLinkedList<>();
        assertThrows(NoSuchElementException.class, list::removeLast);
    }

    @Test
    @DisplayName("remove(index) удаляет элемент по индексу")
    void testRemoveByIndex() {
        CustomList<String> list = new CustomLinkedList<>();
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");

        String removed = list.remove(1);
        assertEquals("B", removed);
        assertEquals(2, list.size());
        assertEquals("C", list.get(1));
    }

    @Test
    @DisplayName("remove(index) с неверным индексом выбрасывает исключение")
    void testRemoveInvalidIndex() {
        CustomList<String> list = new CustomLinkedList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }

    @Test
    @DisplayName("size() возвращает количество элементов в списке")
    void testSize() {
        CustomList<String> list = new CustomLinkedList<>();
        assertEquals(0, list.size());

        list.addLast("A");
        list.addLast("B");
        assertEquals(2, list.size());
    }
}
