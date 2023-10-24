package linked;

public interface Stack<T> {
    T peek();

    T pop();

    T push(T e);

    boolean isEmpty();

    void printStack();
}
