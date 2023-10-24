package linked;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;

public class Parsing {

    /*private static final String delimiters = "()";

    private static boolean isDelimiter(String token) {
        if (token.length() != 1) return false;
        for (int i = 0; i < delimiters.length(); i++) {
            if (token.charAt(0) == delimiters.charAt(i)) return true;
        }
        return false;
    }

     */

    //Регулярные выражения + Pattern.compile()
    //* ( ) \\d+

    public void whenReadWithStreamTokenizer_thenCorrectTokens()
            throws Exception {

        String file = "C:\\Programming\\lessons\\lad1_al\\src\\linked\\test.txt";
        int curIndChar = 0;
        int curChar;
        int sizeFile = file.length();
        FileReader reader = new FileReader(file);
        StreamTokenizer tokenizer = new StreamTokenizer(reader);
        Stack<Node> stackNode = new LinkedList<>();
        Stack<String> stackBrack = new LinkedList<>();
        BinaryTree tree = null;

        while (curIndChar < sizeFile) {
            curChar = tokenizer.nextToken();
            if (curChar == '(') {
                stackBrack.push(String.valueOf(curChar));
            } else if (curChar == ')') {
                stackBrack.pop();
                stackNode.pop();
            }
            else{
                //input num
                char nextChar = file.charAt(curIndChar + 1);
                int num = 0;
                while (nextChar >= '0' && nextChar <= '9') {
                    num *= 10;
                    num += (curChar - '0');
                    curChar = nextChar;
                    curIndChar++;
                    nextChar = file.charAt(curIndChar + 1);
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

        System.out.println("Input:\t\t\t\t" + file);
        System.out.println("Tree:\t\t\t\t" + treeArray.toString());
        }
    }

