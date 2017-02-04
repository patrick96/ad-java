package patrick96.ad_java.tree;

/**
 * Base class for binary trees
 * This class serves as both a tree node and a tree itself 
 * since a tree can be represented by its root node object
 */
public class BinaryTree {

    /**
     * The left and right children and the parent of this node
     */
    protected BinaryTree left, right, parent;
    
    /**
     * The value that this node represents
     */
    protected int key;

    public BinaryTree(int key) {
        this(key, null);
    }

    public BinaryTree(int key, BinaryTree parent) {
        this.key = key;
        this.parent = parent;
    }

    /**
     * Calculates the height of the binary tree with this node as the root node recursively
     *
     * @return
     */
    public int getHeight() {
        int left = this.left == null? 0 : this.left.getHeight();
        int right = this.right == null? 0 : this.right.getHeight();

        return Math.max(left, right) + 1;
    }
}
