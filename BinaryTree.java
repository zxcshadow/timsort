package linked;
public class BinaryTree {
    Node root = null;


    public Node getRoot() {

        return root;
    }


    ArrayList<Integer> arrayTree = new ArrayList<>();
    public BinaryTree(Node root){
        this.root = root;
    }

    //Рекурсивный обход в глубину (любое направление)
    public void preOrder(Node node)
    {
        if (node == null)
            return;
        arrayTree.add(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    public ArrayList<Integer> getTree(){
        preOrder(root);
        //System.out.println(arrayTree.toString());
        return arrayTree;
    }
}
