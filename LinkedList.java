package linked;

import java.util.Collection;

public class LinkedList<T> implements List<T>, Stack<T> {

    ListItem<T> head;
    private int size;
    public LinkedList() {
        this.size = 0;
    }

    @Override
    public T peek() {
        //TODO возвращение элемента с начала списка
        return head != null ? head.data : null;

    }

    @Override
    public T pop() {
        //TODO удаление элемента в начале списка
        if(head == null)
            return null;
        T data = head.data;
        head = head.next;
        size--;
        return data;
    }

    @Override
    public T push(T e) {
        //TODO добавление элемента в начало списка
        head = new ListItem<>(e,head);
        size++;
        return e;
    }


    private static class ListItem<T> {
        T data;
        ListItem<T> next;
        ListItem(T data, ListItem<T> item){
            this.data = data;
            this.next=item;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    public boolean isEmpty() {

        return head == null;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public boolean add(T item) {
        if(isEmpty()){
            head=new ListItem<T>(item, null);
        }else{
            ListItem<T> p=head;
            ListItem<T> node=new ListItem<T>(item,null);
            while(p.next!=null){
                p=p.next;
            }
            p.next=node;
            node.next=null;
        }

        ++size;
        return true;
    }



    @Override
    public boolean remove(Object o) {
        ListItem<T> p = head, p1 = null;
        boolean have = false;
        if (isEmpty()) {
            return false;
        }
        while (p != null) {
            if (p.data.equals(o)) {
                if (p1 == null) {
                    head = head.next;
                } else {
                    p1.next = p.next;
                }
                have = true;
            }
            p1 = p;
            p = p.next;
        }
        return have;
    }


    @Override
    public void clear() {
        //TODO tail нужно убрать и пройтись по всему списку и занулить все ноды списка
        head=null;

        this.size = 0;
    }

    @Override
    public T get(int index) {

        int i = -1;
        if (isEmpty()) {
            return null;
        }
        if (index < 0 || index > size()) {
            return null;
        }
        ListItem<T> p = head;
        while (p != null) {
            i++;
            if (i == index) {
                return p.data;
            }
            p = p.next;
        }
        return null;
    }

    @Override
    public T set(int index, T element) {

        int i = -1;
        if (isEmpty()) {
            add(element);
            return null;
        }
        if (index < 0 || index > size()) {
            return null;
        }
        ListItem<T> p = head;
        T o = null;
        while (p != null) {
            i++;
            if (i == index) {
                o = p.data;
                p.data = element;
                return o;
            }
            p = p.next;
        }
        return null;
    }

    @Override
    public T remove(int index) {
        if (isEmpty()) {
            return null;
        }
        if (index < 0 || index > size()) {
            return null;
        }
        ListItem<T> p = head, p1 = null;
        int i = -1;
        while (p != null) {
            i++;
            if (i == index) {
                if (p1 == null) {
                    head = head.next;
                } else {
                    p1.next = p.next;
                }
                return p.data;
            }
            p1 = p;
            p = p.next;
        }
        return null;
    }

    @Override
    public int indexOf(Object o) {
        int i = -1;
        if (isEmpty()) {
            return -1;
        }
        ListItem<T> p = head;
        while (p != null) {
            i++;
            if (p.data.equals(o)) {
                return i;
            }
            p = p.next;
        }
        return -1;
    }

    public void printList() {
        ListItem<T> p;
        for (p = head; p != null; p = p.next) {
            System.out.print(p.data + "--->");
        }
        System.out.println();
    }

    public void printStack() {
        ListItem<T> p;
        for (p = head; p != null; p = p.next) {
            System.out.print(p.data + "--->");
        }
        System.out.println();
    }
}


