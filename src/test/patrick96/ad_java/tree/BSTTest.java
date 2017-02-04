package patrick96.ad_java.tree;

import static org.junit.Assert.*;

import org.junit.*;

public class BSTTest {
    
    BST root;

    @Before
    public void buildUp() {
        root = new BST(-1, null);
        System.setOut(System.err);
    }

    public void assertStructure(BST root) {
        if(root.left != null) {
            assertEquals(root, root.left.parent);
            assertStructure(root.left);

            assertTrue("left is smaller", root.left.key < root.key);
        }

        if(root.right != null) {
            assertEquals(root, root.right.parent);
            assertStructure(root.right);
            assertTrue("right is greater", root.right.key > root.key);
        }
    }

    @Test
    public void insert() {
        root.insert(5);
        assertStructure(root);
        root.insert(4);
        assertStructure(root);
        root.insert(3);
        assertStructure(root);
        root.insert(9);
        assertStructure(root);
        root.insert(1);
        assertStructure(root);
        root.insert(-11);
        assertStructure(root);

        assertEquals(-11, root.left.key);
        assertEquals(5, root.right.key);
        assertEquals(9, root.right.right.key);
        assertEquals(4, root.right.left.key);
        assertEquals(3, root.right.left.left.key);
        assertEquals(1, root.right.left.left.left.key);
    }

    @Test
    public void contains() {
        root.insert(5);
        root.insert(8);
        root.insert(9);
        root.insert(1);
        root.insert(2);
        root.insert(3);

        assertTrue(root.contains(5));
        assertTrue(root.contains(8));
        assertTrue(root.contains(9));
        assertTrue(root.contains(1));
        assertTrue(root.contains(2));
        assertTrue(root.contains(3));
        assertTrue(root.contains(-1));
        assertFalse(root.contains(0));
    }

    @Test
    public void delete() {
        root.insert(5);
        root.insert(-10);
        root.insert(8);
        root.insert(100);
        root.insert(90);
        root.insert(6);
        root.insert(7);
        root.insert(3);
        root.insert(4);
        root.insert(1);
        root.insert(0);

        // Has no children
        root.delete(-10);
        assertFalse(root.contains(-10));
        assertStructure(root);

        assertEquals(5, root.right.key);
        assertEquals(3, root.right.left.key);
        assertEquals(4, root.right.left.right.key);
        assertEquals(1, root.right.left.left.key);
        assertEquals(0, root.right.left.left.left.key);
        assertEquals(8, root.right.right.key);
        assertEquals(6, root.right.right.left.key);
        assertEquals(7, root.right.right.left.right.key);
        assertEquals(100, root.right.right.right.key);
        assertEquals(90, root.right.right.right.left.key);

        // Has two children
        root.delete(5);
        assertFalse(root.contains(5));
        assertStructure(root);

        assertEquals(6, root.right.key);
        assertEquals(3, root.right.left.key);
        assertEquals(4, root.right.left.right.key);
        assertEquals(1, root.right.left.left.key);
        assertEquals(0, root.right.left.left.left.key);
        assertEquals(8, root.right.right.key);
        assertEquals(7, root.right.right.left.key);
        assertEquals(100, root.right.right.right.key);
        assertEquals(90, root.right.right.right.left.key);

        // Has one child
        root.delete(1);
        assertFalse(root.contains(1));
        assertStructure(root);

        assertEquals(6, root.right.key);
        assertEquals(3, root.right.left.key);
        assertEquals(4, root.right.left.right.key);
        assertEquals(0, root.right.left.left.key);
        assertEquals(8, root.right.right.key);
        assertEquals(7, root.right.right.left.key);
        assertEquals(100, root.right.right.right.key);
        assertEquals(90, root.right.right.right.left.key);
    }
}
