public class BinaryTreeToDLL {
    // Binary Tree Node
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    // Head of the DLL
    Node head = null;

    // Previous node reference (for linking)
    Node prev = null;

    // Function to convert Binary Tree to Doubly Linked List
    public void convertToDLL(Node root) {
        if (root == null) return;

        // Step 1: Left subtree
        convertToDLL(root.left);

        // Step 2: Current node processing
        if (prev == null) {
            // First node (leftmost) will be head of DLL
            head = root;
        } else {
            // Link previous node with current node
            root.left = prev;
            prev.right = root;
        }

        // Move prev forward
        prev = root;

        // Step 3: Right subtree
        convertToDLL(root.right);
    }

    // Function to print the Doubly Linked List
    public void printDLL() {
        Node current = head;
        System.out.println("Doubly Linked List (Inorder Traversal):");
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.right;
        }
        System.out.println("null");
    }

    // Main Method
    public static void main(String[] args) {
        BinaryTreeToDLL tree = new BinaryTreeToDLL();

        // Creating Binary Tree manually
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(20);
        root.left.left = new Node(3);
        root.left.right = new Node(7);
        root.right.left = new Node(15);
        root.right.right = new Node(25);

        /*
                 10
               /    \
              5     20
             / \    / \
            3   7  15  25
        */

        tree.convertToDLL(root);
        tree.printDLL();
    }
}
