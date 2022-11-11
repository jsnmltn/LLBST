import java.io.*;

public class LinkedBSTree {
    private class Node {
        private float data;
        private String name;
        private Node leftChild;
        private Node rightChild;

        public Node(String aname, float aData) {
            name = aname;
            data = aData;
            leftChild = null;
            rightChild = null;
        }
    }

    private Node root;

    public LinkedBSTree() {
        root = null;
    }

    public void insert(String name, float data) {
        if (root == null) {
            root = new Node(name, data);
        } else {
            insert(root, name, data);
        }
    }

    private Node insert(Node aNode, String name, float data) {
        if (aNode == null) 
        {
            aNode = new Node(name, data);
        } else if (data < aNode.data) //less than so traverse left
        {
            aNode.leftChild = insert(aNode.leftChild, name, data);
        } else if (data >= aNode.data) //greater than so go right
        {
            aNode.rightChild = insert(aNode.rightChild, name, data);
        }
        return aNode;
    }

    //method to delete a node
    public void delete(String name, float data) {
        delete(root, name, data);
    }

    private Node delete(Node aNode, String name, float data) {
       
        if (aNode == null) 
            return null;
        if (data < aNode.data) //Left
            aNode.leftChild = delete(aNode.leftChild, name, data);
        else if (data > aNode.data) //Right
            aNode.rightChild = delete(aNode.rightChild, name, data);
        else //Find and delete
        {
            if (aNode.rightChild == null) // either no children or just left
                return aNode.leftChild;
            if (aNode.leftChild == null) // right child only
                return aNode.rightChild;

           
            Node temp = aNode; //get rid of the duplicate
            aNode = findMinInTree(aNode.rightChild);
            aNode.rightChild = deleteMinFromTree(aNode.rightChild);
            aNode.leftChild = temp.leftChild;
        }
        return aNode;
    }

    private Node findMinInTree(Node aNode) {
        if (aNode == null)
            return null;
        if (aNode.leftChild == null)
            return aNode;
        else
            return findMinInTree(aNode.leftChild);
    }

    private Node deleteMinFromTree(Node aNode) {
        if (aNode == null)
            return null;
        if (aNode.leftChild == null)
            return aNode;
        aNode.leftChild = deleteMinFromTree(aNode.leftChild);
        return aNode;
    }

    public void printPreOrder() //Pre order
    {
        printPreOrder(root);
    }
//Process,left, right
    private void printPreOrder(Node aNode) {
        if (aNode == null)
            return;
        
        System.out.println(aNode.data);
        if (aNode.leftChild != null)
            printPreOrder(aNode.leftChild);
        if (aNode.rightChild != null)
            printPreOrder(aNode.rightChild);
        return;
    }

    public void printInOrder() 
    {
        printInOrder(root);
    }

    private void printInOrder(Node aNode) {
        if (aNode == null)
            return;
        if (aNode.leftChild != null)
            printInOrder(aNode.leftChild);

        System.out.println(aNode.name + "\t" + aNode.data); // process the node
        if (aNode.rightChild != null)
            printInOrder(aNode.rightChild);
        return;
    }
/////Main Method for entry point to test the program
    public static void main(String args[]) {
        LinkedBSTree l = new LinkedBSTree();
        String fileName = "input.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] item = line.split(" ");
                l.insert(item[0], Float.parseFloat(item[1]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        l.printInOrder();

    }
    
 
}
