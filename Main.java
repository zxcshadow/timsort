package linked;

import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static linked.Sort.RandomArray;

public class Main {

    public static void main(String[] args) throws Exception{
        String path = "C:\\Programming\\lessons\\lad1_al\\src\\linked\\test.txt";
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        String input = scanner.nextLine();
        //String file = Files.lines(Paths.get("C:\\Programming\\lessons\\lad1_al\\src\\linked\\test.txt", String.valueOf(StandardCharsets.UTF_8))).toString();
        int curIndChar = 0;
        char curChar;
        int sizeFile = input.length();
       // FileReader reader = new FileReader(file);
        //StreamTokenizer tokenizer = new StreamTokenizer(reader);
        Stack<Node> stackNode = new LinkedList<>();
        Stack<Character> stackBrack = new LinkedList<>();
        BinaryTree tree = null;

        while (curIndChar < sizeFile) {
            curChar = input.charAt(curIndChar);
            if (curChar == '(') {
                stackBrack.push(curChar);
            } else if (curChar == ')') {
                stackBrack.pop();
                stackNode.pop();
            }
            else{
                //input num
                char nextChar = input.charAt(curIndChar + 1);
                int num = 0;
                while (nextChar >= '0' && nextChar <= '9') {
                    num *= 10;
                    num += (curChar - '0');
                    curChar = nextChar;
                    curIndChar++;
                    nextChar = input.charAt(curIndChar + 1);
                }
                num *= 10;
                num += (curChar - '0');
                Node curNode = new Node(num);
                if (stackNode.isEmpty()){
                    stackNode.push(curNode);
                    tree = new BinaryTree(curNode);
                }
                else{
                    Node last = stackNode.peek();
                    if (last.left == null) last.left = curNode;
                    else if (last.right == null) last.right = curNode;
                    else{
                        throw new Exception("It's not bin Tree");
                    }
                    stackNode.push(curNode);

                }

            }
            curIndChar++;


        }
        AVLtree treeAVL = new AVLtree();
        ArrayList<Integer> treeArray = tree.getTree();


        for (int i = 0; i < treeArray.size(); i++){
            treeAVL.insert(treeArray.get(i));
        }


        System.out.println("Input:\t\t\t\t" + input);
        System.out.println("Tree:\t\t\t\t" + treeArray.toString());
        System.out.println("AVL Tree:\t\t\t" + treeAVL.toPreOrder().toString());
        System.out.println("В ширину:\t\t\t" + treeAVL.widthOrder().toString());
        System.out.println("Прямой:\t\t\t\t" + treeAVL.preOrder().toString());
        System.out.println("Центрированный:\t\t" + treeAVL.inOrder().toString());
        System.out.println("Обратный:\t\t\t" + treeAVL.postOrder().toString());
    }




        // только методы стека
       /* Stack<Integer> stack = new LinkedList<>();
        stack.push(5);
        stack.push(3);
        stack.push(6);
        stack.pop();
        stack.printStack();

        System.out.println(stack.peek());
        System.out.println(((LinkedList<Integer>) stack).size());

        */
        //вторая лаба тим сорт
        /* ArrayList<Integer> arr = new ArrayList<>() ;
        Sort sort = new Sort();
        long start = System.nanoTime();
        //arr =  Sort.mergeSort(RandomArray(100000), 0, 99999);
        //arr = Sort.insertionSortImperative(RandomArray(30), 0, 29);
        arr = sort.timSort(RandomArray(1000000), 1000000);

        System.out.println(arr.size());
        long finish = System.nanoTime();
        long elapsed = finish - start;
        arr.printList();
        System.out.println();
        long convert = TimeUnit.SECONDS.convert(elapsed, TimeUnit.NANOSECONDS);
        System.out.println("Прошло времени, с: " + convert);

         */
         //
       // arr = Sort.insertionSortImperative(RandomArray(20), 0, 19);

       // arr.add(9);
        //arr.add(4);
        //arr.add(2);
       // arr.add(8);
        //arr.add(3);

       // Sort.quickSort(arr, 0, 4);
        //Sort.timSort(arr, 1000);
        //Sort.mergeSort(arr, 0, 5);
        //Sort.insertionSortImperative(arr, 0, 4);
        //arr.printList();

        /*
         ArrayList<Integer> arr = new ArrayList<>() ;
        arr.add(4);
        arr.add(7);
        arr.add(9);
        arr.add(3);
        arr.add(8);
        Sort.merge(arr, arr);
        arr.printList();
        System.out.println();
        Sort.insertionSortImperative(arr);
        arr.printList();


         */

        //так только методы листа
       // List<Integer> list = new LinkedList<>();
        /*list.add(4);
        list.printList();
        list.add(43);
        list.add(2);
        list.printList();
        list.remove(Integer.valueOf(43));
        list.printList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(5);
        list.remove(4);
        list.printList();
        System.out.println(list.contains(4));
        System.out.println(list.indexOf(43));

         */
        //так у тебя будут доступны все методы линкед листа, включая методы стека и листа
       // LinkedList<Integer> linkedList = new LinkedList<>();
       /* stack.push(5);
        stack.printStack();
        stack.push(3);
        stack.printStack();
        stack.pop();
        System.out.println(stack.pop());

        */

    }

