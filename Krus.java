package linked;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Krus{
    
    static ArrayList<Edge> edges;
    ArrayList<SetElement> vertices;

    //со второй лабы
    static void sortEdges(int begin, int end){
        if (begin >= end){
            return;
        }
        int mid = begin + (end - begin) / 2;
        sortEdges(begin, mid);
        sortEdges(mid + 1, end);
        merge(begin, mid + 1, end - mid );

    }

    //берем со второй лабы мердж и меняем просто аргумент в эрэйлисте
    static void merge(int firstIndex, int secondIndex, int secondSize) {
        int firstSize = secondIndex - firstIndex;
        int end = secondIndex + secondSize - 1;

        ArrayList<Edge> left = new ArrayList<>();
        ArrayList<Edge> right = new ArrayList<>();

        for (int i = 0; i < firstSize; i++) {
            left.add(edges.get(i + firstIndex));
        }
        for (int j = 0; j < secondSize; j++) {
            right.add(edges.get(secondIndex + j));
        }

        int indexOfFirst = 0;
        int indexOfSecond = 0;
        int indexOfMerged = firstIndex;

        while (indexOfFirst < firstSize && indexOfSecond < secondSize) {
            if (left.get(indexOfFirst).weight <= right.get(indexOfSecond).weight) {
                edges.set(indexOfMerged, left.get(indexOfFirst));
                indexOfMerged++;
                indexOfFirst++;
            } else {
                edges.set(indexOfMerged, right.get(indexOfSecond));
                indexOfMerged++;
                indexOfSecond++;
            }
        }
        while (indexOfFirst < firstSize){
            edges.set(indexOfMerged, left.get(indexOfFirst));
            indexOfFirst++;
            indexOfMerged++;
        }
        while (indexOfSecond < secondSize){
            edges.set(indexOfMerged, right.get(indexOfSecond));
            indexOfSecond++;
            indexOfMerged++;
        }
    }
    Krus (String fileName) {
        try {
            FileReader reader = new FileReader(fileName);
            Scanner scanner = new Scanner(reader);
            int k = -1;

            edges = new ArrayList<>();
            int currentIndex = 0;
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] words = line.split(" ");
                if (k == -1) {
                    vertices = new ArrayList<>();
                    DisjointSetUnion dsu = new DisjointSetUnion();
                    for (String word : words) {
                        vertices.add(dsu.makeSet(word));
                    }
                } else {
                    ArrayList<Integer> nums = new ArrayList<>();
                    for (String num : words) {
                        nums.add(Integer.parseInt(num));
                    }
                    for (int i = currentIndex; i < nums.size(); i++) {
                        if (nums.get(i) > 0) {
                            Edge edge = new Edge();
                            edge.vertex1 = vertices.get(k);
                            edge.vertex2 = vertices.get(i);
                            edge.weight = nums.get(i);
                            edges.add(edge);
                        }
                    }
                    currentIndex++;
                }
                k++;

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

     static void algKruskala(){
        SetElement First, Second;
        int sizeOf = edges.size() - 1;
        sortEdges(0, sizeOf);
        ArrayList<Edge> spanningTree = new ArrayList<>();

        DisjointSetUnion dsu = new DisjointSetUnion();
        for (int i = 0; i < edges.size(); i++){
            First = dsu.find(edges.get(i).vertex1);
            Second = dsu.find(edges.get(i).vertex2);

            if (First != Second){  //если вершины не равны
                spanningTree.add(edges.get(i)); //   Добавляем  ребро  в  дерево,  если  не  образует  цикла
                dsu.uniteSets(First, Second);
            }
        }
        stringSort(spanningTree);
        int sum = 0;
        for (int i = 0; i < spanningTree.size(); i++){
            sum += spanningTree.get(i).weight;
            System.out.println(spanningTree.get(i).vertex1.value + " " + spanningTree.get(i).vertex2.value);
        }
        System.out.println(sum);
    }


    public static void main(String args[]) throws FileNotFoundException {
        Krus samp = new Krus("C:\\Programming\\lessons\\lad1_al\\src\\linked\\test.txt");

        samp.algKruskala();
    }
    static void stringSort(ArrayList<Edge> list){
        int indexOfArray = 0;
        int sizeOfArray = list.size();
        for (int i = indexOfArray + 1; i < sizeOfArray + indexOfArray; i++){
            Edge currentValue = list.get(i);
            if (currentValue.vertex1.value.compareTo(currentValue.vertex2.value) > 0){
                SetElement buffer = currentValue.vertex1;
                currentValue.vertex1 = currentValue.vertex2;
                currentValue.vertex2 = buffer;
            }
            int left = indexOfArray;
            int right = i ;
            while (left < right){
                int middle = left + (right - left) / 2;
                if (currentValue.vertex1.value.compareTo(list.get(middle).vertex1.value) < 0){
                    right = middle;
                }
                else{
                    left = middle + 1;
                }
            }
            for (int j = i; j > left ; j--){
                list.set(j, list.get(j - 1));
            }
            list.set(left, currentValue);

        }
    }

    static class Edge {
        private SetElement vertex1;    // ребро имеет 2 вершины и вес
        private SetElement vertex2;
        private int weight;

        public int compareTo(Edge edge) {
            if (weight != edge.weight) return weight < edge.weight ? -1 : 1;
            return 0;
        }

    }
    //класс для представления непересекающегося множества
    public static class DisjointSetUnion {


        SetElement makeSet(String value){
            SetElement element = new SetElement();
            element.value = value;
            element.parent = element;
            element.next = null;
            return element;
        }
        SetElement find(SetElement i){ //возвращает, в каком множестве находится указанный элемент
            if(i == i.parent)
            return i;
            return find(i.parent);
        }


        /*Чтобы объединить два множества union_sets(f,s),  сначала найдём лидеров множества, в котором находится f,
            и множества, в котором находится s. Если лидеры совпали, то ничего не делаем —
            это значит, что множества и так уже были объединены.
            В противном случае можно просто указать, что предок вершины s равен f (или наоборот) —
            тем самым присоединив одно дерево к другому.*/
        void uniteSets(SetElement first, SetElement second){ //объединяет два указанных множества
            first = find(first);
            second = find(second);
            if(first != second){
                second.parent = first;
            }
        }
    }
    static class SetElement {
        String value;
        SetElement parent;
        SetElement next;
    }
}
