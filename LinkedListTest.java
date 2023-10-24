package linked;

import static org.junit.Assert.*;

public class LinkedListTest {

    @org.junit.Test
    public void peek() {
        Stack<String> stack = new LinkedList<>();
        stack.push("5");
        stack.push("54");
        assertEquals("54", stack.peek());
    }

    @org.junit.Test
    public void pop() {
        Stack<Integer> stack = new LinkedList<>();
        stack.push(5);
        stack.pop();
        assertEquals(true, stack.isEmpty());
    }

    @org.junit.Test
    public void push() {
        Stack<String> stack = new LinkedList<>();
        stack.push("5");
        stack.push("54");
        assertEquals(2, ((LinkedList<String>) stack).size());
    }

    @org.junit.Test
    public void size() {
        LinkedList<Object> list = new LinkedList<>();
        list.add(5);
        list.add(6);
        assertEquals(2,list.size());
    }

    @org.junit.Test
    public void isEmpty() {
        LinkedList<Object> list = new LinkedList<>();
        list.add(5);
        list.add(6);
        list.remove(0);
        list.remove(1);
        assertEquals(false,list.isEmpty());

    }

    @org.junit.Test
    public void contains() {
        LinkedList<Object> list = new LinkedList<>();
        list.add(5);
        list.add(6);
        list.add(7);
        assertEquals(false, list.contains(10));
    }

    @org.junit.Test
    public void add() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(5);
        list.add(8);
        list.add(1);
        list.clear();;
        list.add(2);
        list.add(3);
        list.add(4);
        assertEquals(true,list.contains(2));
    }

    @org.junit.Test
    public void remove() {
        LinkedList<Object> list = new LinkedList<>();
        list.add(5);
        list.add(6);
        list.add(7);
        list.remove(0);
        assertEquals(false, list.contains(5));
    }

    @org.junit.Test
    public void clear() {
        LinkedList<Object> list = new LinkedList<>();
        list.add(5);
        list.add(8);
        list.clear();
        assertEquals(0, list.size());
    }

    @org.junit.Test
    public void get() {
        LinkedList<Object> list = new LinkedList<>();
        list.add(5);
        list.add(6);
        list.add(7);
        assertEquals(6, list.get(1));
    }

    @org.junit.Test
    public void set() {
        LinkedList<Object> list = new LinkedList<>();
        list.add(5);
        list.add(6);
        list.add(7);
        list.set(2, 9);
        assertEquals(9, list.get(2));
    }

    @org.junit.Test
    public void testRemove() {
    }

    @org.junit.Test
    public void indexOf() {
    }
}