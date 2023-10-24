package linked;



public class ArrayList <T> implements List<T> {

    private Object[] data;
    private int size;
    public static final int DEFAULT_CAPACITY = 1000000;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int size){

        this.data =  new Object[size];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }


    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }



    @Override
    public int indexOf(Object o) {
        for (int i=0; i<data.length; i++) {
            if (data[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public Object set(int index, Object element) {
        Object original = data[index];
        data[index] = element;
        return original;
    }

    @Override
    public T remove(int index) {
        Object out = data[index];
        for (int i=index; i<data.length-1; i++) {
            data[i] = data[i+1];
        }
        this.size--;
        return (T) out;
    }

    @Override
    public boolean remove(Object o) {
        for (int i=0; i<data.length; i++) {
            if (data[i].equals(o)) {
                this.remove(i);
                return true;
            }
        }
        this.size--;
        return false;
    }




    @Override
    public void clear() {
        for (int i=0; i<data.length; i++) {
            data[i] = null;
        }
        this.data = new Object[DEFAULT_CAPACITY];
        this.size =0;
    }

    @Override
    public T get(int index) {
        return (T) data[index];
    }



    @Override
    public boolean add(Object t) {
        if (size+1 > data.length) {
            Object[] copy =  new Object[data.length*2];
            for (int i=0; i<data.length; i++) {
                copy[i] = data[i];
            }
            data = copy;
        }
        data[size++] = t;
        return false;
    }
    @Override
    public String toString() {
        if (size == 0)
            return "empty";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(data[i]).append(", ");
        }
        return sb.append(data[size - 1]).append("]").toString();
    }


    @Override
    public void printList() {
        for (Object element : data) {
            if (element != null) {
                System.out.print(element + "--->");
            }
        }
    }

}
