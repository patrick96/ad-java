package patrick96.ad_java.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Implementation of a binary search tree
 * For any given node the following (called the searchtree condition) holds:
 * All the elements in the left subtree are smaller
 * All the elements in the right subtree are greater
 * 
 * A binary search tree can be seen as a map, though here
 * only keys are stored which would make this a set of ints
 * Theoretically non-primitive data types can be stored
 * as long as they have a notion of order (e.g. Comparable)
 *
 * Since the searchtree condition is required for this tree all the public operations
 * insert, contains, delete run in O(h) where h is the height of the tree. This is because
 * any element has a unique position in a tree so all of these operations only need to
 * traverse the tree a constant number of times to get to the position where they need to operate
 */
public class BST extends BinaryTree {

    protected BST left, right, parent;

    public BST(int key, BST parent) {
        super(key, parent);
        this.parent = parent;
    }

    /**
     * Insert the given key into the tree
     * 
     * If the key is already in the tree, nothing happens
     *
     * @param key
     */
    public void insert(int key) {
        BST parent = find(key);

        if(parent.key != key) {
            if(key < parent.key) {
                parent.left = new BST(key, parent);
            }
            else {
                parent.right = new BST(key, parent);
            }
        }
    }

    /**
     * Determines if the given key is in the tree
     *
     * @param key
     * @return
     */
    public boolean contains(int key) {
        BST found = find(key);

        return found.key == key;
    }

    /**
     * Removes the node with the given key from the array if it exists
     *
     * @param key
     * @return false, if key is not in tree and true otherwise
     */
    public boolean delete(int key) {
        BST node = find(key);

        if(node.key != key) {
            return false;
        }

        return node.delete();
    }

    /**
     * Deleted this node from the tree
     *
     * @return
     */
    public boolean delete() {
        /*
         * When deleting, there are three cases:
         * 1. The node has no children (trivial)
         * 2. The node has exactly one child (attach the child to the node's parent)
         * 3. The node has exactly two children:
         *  We find the symmetric successor of the node and put it in place of the
         *  removed node, this does not violate the searchtree condition, since
         *  the symmetric successor is smaller than all the elements in the right subtree
         *  and obviously greater than all the elements in the left subtree
         *  Removing the symmetric successor can be done using case 1 or 2 since it cannot
         *  have two children
         */

        if(this.left == null && this.right == null) {
            // Case 1
            
            if(isLeft()) {
                this.parent.left = null;
            }
            else {
                if(this.parent != null) {
                    this.parent.right = null;
                }
            }

            this.parent = null;
        }
        else if((this.left == null) != (this.right == null)) {
            // Case 2

            BST parent = this.parent;
            BST child = this.left == null? this.right : this.left;

            if(isLeft()) {
                parent.left = child;
            }
            else {
                // The parent could be null, if the node is the root
                if(parent != null) {
                    parent.right = child;
                }
            }

            child.parent = parent;
            this.parent = null;
            this.left = null;
            this.right = null;
        }
        else {
            // Case 3
            BST symmetricSuccessor = symmetricSuccessor(this);
            int val = symmetricSuccessor.key;
            symmetricSuccessor.delete();
            this.key = val;
        }


        return true;
    }

    /**
     * Determines if this node is the left child of its parent
     *
     * @param node
     * @return
     */
    protected boolean isLeft() {
        return this.parent != null && this.parent.left == this;
    }

    /**
     * Finds the symmetric successor of the given node
     * 
     * The symmetric successor is the smallest element in the right subtree
     * or in other words the element that is greater than the node and smaller
     * than any other elements that are greated than the node
     *
     * @param node
     * @return
     */
    protected BST symmetricSuccessor(BST node) {
        BST iterator = node.right;
        BST parent = node;

        while(iterator != null) {
            parent = iterator;
            iterator = iterator.left;
        }

        return parent;
    }

    /**
     * Searches for the given key in the tree
     *
     * @param key
     * @return The node associated with this key if the key is in the tree
     *         Otherwise the parent node of the would-be position of the key
     *         Never returns null
     */
    protected BST find(int key) {
        BST iterator = this;
        while(true) {
            if(key < iterator.key) {
                if(iterator.left == null) {
                    break;
                }
                else {
                    iterator = iterator.left;
                }
            }
            else if(key > iterator.key) {
                if(iterator.right == null) {
                    break;
                }
                else {
                    iterator = iterator.right;
                }
            }
            else {
                break;
            }
        }

        return iterator;
    }

    /**
     * Traverses the tree in preorder (non-recursive)
     * 
     * The preorder traversation saves the key of the current element
     * then performs a preorder traversation on its left subtree recursively
     * and then on its right subtree
     *
     * @return
     */
    public int[] preorder() {
        List<Integer> order = new ArrayList<>();

        Stack<BST> stack = new Stack<>();
        stack.push(this);

        while(!stack.isEmpty()) {
            BST c = stack.pop();

            order.add(c.key);

            if(c.right != null) {
                stack.push(c.right);
            }

            if(c.left != null) {
                stack.push(c.left);
            }
        }

        return order.stream().mapToInt(Integer::valueOf).toArray();
    }

    /**
     * Traverses the tree in postorder (non-recursive)
     * 
     * The postorder traversal first traverses the left and then the
     * right subtree in postorder and only then the current node
     *
     * @return
     */
    public int[] postorder() {
        Stack<BST> S1 = new Stack<>();
        Stack<BST> S2 = new Stack<>();

        S1.push(this);

        while(!S1.isEmpty()) {
            BST c = S1.pop();
            S2.push(c);
            if(c.left != null) {
                S1.push(c.left);
            }

            if(c.right != null) {
                S1.push(c.right);
            }
        }

        int[] order = new int[S2.size()];

        int i = 0;
        while(!S2.empty()) {
            order[i] = S2.pop().key;
            i++;
        }

        return order;
    }


    /**
     * Traverses the tree in inordre (non-recursive)
     * 
     * Inorder traverses first the left subtree in inorder then the current node
     * and then the right subtree
     * 
     * Because this is a searchtree the inorder traversal is at the same time
     * also a traversal in ascending key value
     *
     * @return
     */
    public int[] inorder() {
        List<Integer> order = new ArrayList<>();

        Stack<BST> stack = new Stack<>();

        BST root = this;

        while(root != null) {
            stack.push(root);
            root = root.left;
        }

        while(!stack.isEmpty()) {
            BST c = stack.pop();
            order.add(c.key);

            if(c.right != null) {
                c = c.right;

                // The next node we need to visit is on the far left
                while(c != null) {
                    stack.push(c);
                    c = c.left;
                }
            }
        }

        return order.stream().mapToInt(Integer::valueOf).toArray();
    }
}
