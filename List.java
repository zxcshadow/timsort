package linked;

import java.util.Collection;

public interface List<T> {

    //Возвращает количество элементов в этом списке.
    int size();

    //Возвращает true, если этот список не содержит элементов.
    boolean isEmpty();

    //Возвращает значение true, если этот список содержит указанный элемент
    boolean contains(Object o);

    //Возвращает индекс первого вхождения указанного элемента в этом списке или -1, если этот список не содержит элемента
    int indexOf(Object o);

    //Заменяет элемент в указанной позиции в этом списке указанным элементом
    T set(int index, T element);

    //Удаляет элемент в указанной позиции в этом списке
    T remove(int index);

    //Удаляет первое вхождение указанного элемента из этого списка, если он присутствует
    boolean remove(Object o);

    // Удаляет все элементы из этого списка
    void clear();

    //Возвращает элемент в указанной позиции в этом списке.
    T get(int index);

    //Добавляет указанный элемент в конец этого списка
    boolean add(T t);

    void printList();
}
