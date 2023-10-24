package linked;

public class AVLtree {

    private Node root;

    public AVLtree() {
        root = null;
    }

    public static void makeEmpty(Node node) {
        if (node == null)
            return;
        makeEmpty(node.left);
        makeEmpty(node.right);
    }

    //добавление
    Node insertNode(int key, Node node) {
        if (node == null) {
            return new Node(key);

        } else if (key < node.data) {
            node.left = insertNode(key, node.left);
        } else if (key > node.data) {
            node.right = insertNode(key, node.right);
        }
        updateHeight(node);
        return imbalance(node);
    }

    public void insert(int key) {
        root = insertNode(key, root);
    }

        //удаление
    Node remove(int key, Node node) {
        Node temp;

        //поиск ключа
        if (node == null) {
            return null;
        } else if (key < node.data) {
            node.left = remove(key, node.left);
        } else if (key > node.data) {
            node.right = remove(key, node.right);
        } else if (node.left == null && node.right == null) {
            node = null;
        }

        // Node has only one child --> replace node by its single child
        else if (node.left == null) {
            node = node.right;
        } else if (node.right == null) {
            node = node.left;
        }

        // Node has two children
        else {
            deleteNodeWithTwoChildren(node);
        }
        if (node == null) {
            return null;
        }
        updateHeight(node);
        return imbalance(node);
    }


    public void delete(int key){
        root = remove(key, root);
    }

    // поиск узла
    Node find(Node node, int key) {
        if (node == null || key == node.data) {
            return node;
        }

        if (key < node.data) {
            return find(node.left, key);
        } else {
            return find(node.right, key);
        }
    }

    private void deleteNodeWithTwoChildren(Node node) {
        // Find minimum node of right subtree
        Node inOrderSuccessor = findMin(node.right);

        // Copy
        node.data = inOrderSuccessor.data;

        // Delete recursively
        node.right = remove(inOrderSuccessor.data, node.right);
    }

    Node findMin(Node node) //поиск минимального ключа
    {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


    //возвращает высоту поддерева или -1 для пустого поддерева
    private int height(Node node) {

        return node == null ? 0  : node.height;
    }

    //устанавливается node.height на максимальный рост детей плюс 1
    private void updateHeight(Node node) {
        int leftChildHeight = height(node.left);
        int rightChildHeight = height(node.right);
        node.height = Math.max(leftChildHeight, rightChildHeight) + 1;
    }

    //баланс узла
    private int balanceFactor(Node node) {

        return height(node.right) - height(node.left);
    }

    // rotations fix imbalance
    private Node imbalance(Node node) {
        int balanceFactor = balanceFactor(node);
// разница высот лев и правый
        // левое тяжелее
        if (balanceFactor < -1) {
            if (balanceFactor(node.left) <= 0) {
                // поворот right
                node = rotateRight(node);
            } else {
                // вращение left-right
                node.left = rotateLeft(node.left);
                node = rotateRight(node);
            }
        }

        // правое тяжелее
        if (balanceFactor > 1) {
            if (balanceFactor(node.right) >= 0) {
                // поворот лево
                node = rotateLeft(node);
            } else {
                // вращение right-left
                node.right = rotateRight(node.right);
                node = rotateLeft(node);
            }
        }

        return node;
    }

    //поворот вправо
    private Node rotateRight(Node node) {
        Node leftChild = node.left;

        node.left = leftChild.right;
        leftChild.right = node;

        updateHeight(node);
        updateHeight(leftChild);

        return leftChild;
    }

    //поворот влево
    private Node rotateLeft(Node node) {
        Node rightChild = node.right;

        node.right = rightChild.left;
        rightChild.left = node;

        updateHeight(node);
        updateHeight(rightChild);

        return rightChild;
    }
    //прямой обход ....первая вершина идем к левому дереву и до конца по левой стороне тд
    public ArrayList<Integer> preOrder(){
        Stack<Node> stack  = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        Node cur = root;
        while (!stack.isEmpty() || cur != null){
            while (cur != null){
                list.add(cur.data);
                if (cur.right != null) stack.push(cur.right);

                cur = cur.left;
            }
            if (!stack.isEmpty()){
                cur = stack.pop();
            }
        }

        return list;
    }
    //центрированный ..... вершина посередине
    public ArrayList<Integer> inOrder(){
        Stack<Node> stack  = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        Node cur = root;
        while (!stack.isEmpty() || cur != null){
            if (!stack.isEmpty()){
                cur = stack.pop();
                list.add(cur.data);
                if (cur.right != null) cur = cur.right;
                else cur = null;
            }
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
        }

        return list;
    }

    //обратный обход                            вершина в конце первый левый лист начинаем с конца
    public ArrayList<Integer> postOrder(){
        Stack<Node> stack  = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        Node cur = root;
        while (!stack.isEmpty() || cur != null){
            if (!stack.isEmpty()){
                cur = stack.pop();
                if (!stack.isEmpty() && cur.right == stack.peek()){
                    cur = stack.pop();
                }
                else {
                    list.add(cur.data);
                    cur = null;
                }
            }
            while (cur != null){
                stack.push(cur);
                if(cur.right != null){
                    stack.push(cur.right);
                    stack.push(cur);
                }
                cur = cur.left;
            }
        }

        return list;
    }
    private void doPreOrder(Node cur,  ArrayList<Integer> list) {
        if (cur == null)
            return;
        list.add(cur.data);
        doPreOrder(cur.left,  list);
        doPreOrder(cur.right,  list);
    }

    public ArrayList<Integer> toPreOrder() {
        ArrayList<Integer> list = new ArrayList<>();
        doPreOrder(root, list);
        return list;
    }

    public ArrayList<Integer> widthOrder() {
        ArrayList<Node> queue = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        Node cur = root;
        queue.add(cur);
        int proverka = 0;
        while (proverka < queue.size()) {


            cur = queue.get(proverka);
            list.add(cur.data);
           // System.out.println(cur.data + " ");

            if (cur.left != null)
                queue.add(cur.left);
            if (cur.right != null)
                queue.add(cur.right);

            proverka++;
        }
        //list.add(cur.data);
        return list;
    }
}
