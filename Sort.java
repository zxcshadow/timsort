package linked;

import java.util.Random;

public class Sort{

    //определение минимального размера подмассива
    public static int getMinrun(int n){
        int r = 0;
        while(n >= 64){
            r |= n & 1;
            n >>=1;
        }
        return n+r;
    }

    //сортировка вставками
    public static ArrayList<Integer> insertionSortImperative(ArrayList<Integer> array, int left, int right) {

        for (int i = left+1; i <= right; i++) {

            int j;
            j = i;
            for (; j>left && array.get(j).compareTo(array.get(j-1)) < 0; j--) {
                Integer tmp = array.get(j);
                array.set(j, array.get(j-1));
                array.set(j-1, tmp);
            }
        }
        return array;
    }

    //сортировка слиянием
    public static void merge(ArrayList<Integer> array, int left1, int mid, int right1){
        int len1 = mid - left1;
        int len2 = mid + right1 -1;

        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        //ArrayList<Integer> sum = new ArrayList<>();


        for (int i = 0; i < len1; i++) {
            left.set(i, array.get(left1+i));
        }
        for (int j = 0; j < right1; j++) {
            right.set(j, array.get(mid + j));
        }
        int i = 0;
        int j = 0;
        int k = left1;
        int gallop = 0;
        boolean gallopingFirst;
        int firstNum = 0;
        int secondNum = 0;


        while (i < len1 && j < right1) {
            if (left.get(i) <= right.get(j)) {
                gallopingFirst = true;
                firstNum++;
                array.set(k++, left.get(i++));
            } else {
                gallopingFirst = false;
                secondNum++;
                array.set(k++, right.get(j++));
            }
            if (firstNum >= 7 && gallop == 0 && gallopingFirst) {
                int variable = 0;
                int lIndex = i;
                while (lIndex < len1) {
                    if (left.get(lIndex) <= right.get(j)) {
                        lIndex += Math.pow(2, variable++);
                    } else break;
                }
                if (lIndex >= len1) {
                    for (; i < len1; i++) {
                        array.set(k++, left.get(i));
                    }
                    break;
                } else {
                    gallop = -1;
                }
            } else if (secondNum >= 7 && gallop == 0 && !gallopingFirst) {
                int variable = 0;
                int rIndex = j;
                while (rIndex < right1) {
                    if (right.get(rIndex) <= left.get(i)) {
                        rIndex += Math.pow(2, variable++);
                    } else break;
                }
                if (rIndex >= right1) {
                    for (; j < right1; j++) {
                        array.set(k++, right.get(j));
                    }
                    break;
                } else {
                    gallop = -1;
                }
            }
        }



        while (i < len1) {
            array.set(k++, left.get(i++));
        }
        while (j < right1) {
            array.set(k++, right.get(j++));
        }
        left.clear();
        right.clear();
        }
        public static ArrayList<Integer> mergeSort(ArrayList<Integer> array, int l, int end){
        if(l<end){
            int m = l+(end-l)/2;
            mergeSort(array, l, m);
            mergeSort(array, m+1, end);
            merge(array, l, m+1, end-m);
        }
            return array;
        }


    //Сортировка TimSort
    public static ArrayList<Integer> timSort(ArrayList<Integer> array, int n){
        int minRun = getMinrun(n);

        LinkedList<Range> minrunStack = new LinkedList<>();

          int currentIndex = 0;
        while (currentIndex < n) {


            int min = Math.min(currentIndex + minRun - 1, n - 1);

            int currentRunIndex = currentIndex;
            int nextIndex;
            boolean goNext = false;

            for (nextIndex = currentIndex + 1; nextIndex < min + 1; nextIndex++) {
                if (array.get(nextIndex).compareTo(array.get(nextIndex - 1)) < 0 && !goNext) {
                    currentRunIndex = nextIndex - 1;
                    goNext = true;
                } else if (array.get(nextIndex).compareTo(array.get(nextIndex - 1)) > 0 && goNext) {
                    goNext = false;
                    exch(array, currentRunIndex, nextIndex);
                }
            }
            while (min < n - 1 && array.get(min).compareTo(array.get(min + 1)) < 0) {
                min++;
            }
            insertionSortImperative(array, currentIndex, min);

            minrunStack.push(new Range(currentIndex, min - currentIndex + 1));
            currentIndex = min + 1;



            while (minrunStack.size() > 1) {
                Range x = minrunStack.pop();
                Range y = minrunStack.pop();


                if (minrunStack.size() > 0) {
                    Range z = minrunStack.pop();
                    if (z.length <= x.length + y.length) {
                        if (x.length > z.length) {
                            //mergeSort(array, y.start, z.length);
                            merge(array, y.start, z.start, z.length);
                            minrunStack.push(new Range(z.start, y.length + z.length));
                            minrunStack.push(x);
                        } else {
                            merge(array, y.start, x.start, x.length);
                            //mergeSort(array,y.start, x.length);
                            minrunStack.push(z);
                            minrunStack.push(new Range(y.start, y.length + x.length));
                        }
                    }
                } else if (y.length <= x.length) {
                    //mergeSort(array, x.start, y.length);
                    merge(array, y.start, x.start, x.length);
                    minrunStack.push(new Range(y.start, y.length + x.length));

                } else if(y.length > x.length) {
                    merge(array, y.start, x.start, x.length);
                    minrunStack.push(new Range(y.start, y.length + x.length));
                }
            }
        }
        return array;
    }

    public static ArrayList<Integer> RandomArray(int n)
    {
        ArrayList<Integer> arrayRandom = new ArrayList<Integer>(n);
        int i;
        for (i=0; i<n; i++)
        {
            Random rand = new Random();
            rand.setSeed(System.currentTimeMillis());
            for ( i=0; i<n; i++)
            {
                Integer r = rand.nextInt() % 255;
                arrayRandom.add(r);
            }
        }
        return arrayRandom;
    }


    static class Range {
        int start;
        int length;
        public Range(int start, int length) {
            this.start = start;
            this.length = length;
        }
    }
     static void exch(ArrayList<Integer> array, int i, int min) {
        min--;
        while(i < min){
            Integer swap = array.get(i);
            array.set(i,array.get(min));
            array.set(min, swap);
            i++;
            min--;
        }
    }
}





