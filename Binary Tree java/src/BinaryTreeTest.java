/*
 * Jessica Krynitsky
 * jmk8vr
 * Sources: BigJava textbook, stackoverflow, codeproject.com, java2blog, 2 TA's whose names I don't know
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BinaryTreeTest {
	// first tree
	BinaryTreeNode<Integer> a;
	BinaryTreeNode<Integer> b;
	BinaryTreeNode<Integer> c;
	BinaryTreeNode<Integer> d;
	BinaryTreeNode<Integer> e;
	BinaryTreeNode<Integer> root;
	BinaryTree<Integer> tree;
	
	// second tree that is equal to the first tree
	BinaryTreeNode<Integer> c2;
	BinaryTreeNode<Integer> d2;
	BinaryTreeNode<Integer> e2;
	BinaryTreeNode<Integer> root2;
	BinaryTree<Integer> tree2;
	
	// third tree that is not equal
	BinaryTreeNode<Integer> root3;
	BinaryTree<Integer> tree3;
	
	// fourth tree that is full
	BinaryTreeNode<Integer> f;
	BinaryTree<Integer> tree4;
	
	@Before
	public void setup() {
		a = new BinaryTreeNode<Integer>(3);
		b = new BinaryTreeNode<Integer>(5);
		c = new BinaryTreeNode<Integer>(7);
		d = new BinaryTreeNode<Integer>(9, a, b);
		e = new BinaryTreeNode<Integer>(11, c, null);
		root = new BinaryTreeNode<Integer>(1, d, e);
		tree = new BinaryTree<Integer>(root);
		
		c2 = new BinaryTreeNode<Integer>(7);
		d2 = new BinaryTreeNode<Integer>(9, a, b);
		e2 = new BinaryTreeNode<Integer>(11, c2, null);
		root2 = new BinaryTreeNode<Integer>(1, d2, e2);
		tree2 = new BinaryTree<Integer>(root2);
		root3 = new BinaryTreeNode<Integer>(13, d, c);
		tree3 = new BinaryTree<Integer>(root3);
		
		
	}
	
	@Test
	//compare two trees with different reference objects but same data
	public void testEquals() {
		assertTrue(tree.equals(tree2));
	}
	
	
	@Test
	// compare two trees that have some same values but are not equal
	public void testEquals2() {
		assertFalse(tree.equals(tree3));
	}
	
	@Test
	// copy trees and check if they are equal to the original
	public void testDeepCopy() {
		BinaryTree<Integer> tree1Copy = tree.deepCopy();
		assertTrue(tree.equals(tree1Copy));
		BinaryTree<Integer> tree3Copy = tree3.deepCopy();
		assertTrue(tree3.equals(tree3Copy));
	}
	
	@Test
	// copy trees and check that different copies are not equal
	// also check that a tree and its copy are not the same reference object
	public void testDeepCopy2() {
		BinaryTree<Integer> treeCopy = tree.deepCopy();
		BinaryTree<Integer> tree3Copy = tree3.deepCopy();
		assertFalse(tree3Copy.equals(treeCopy));
		assertFalse(tree3 == tree3Copy);
	}
	
	@Test
	public void testCombine() {
		BinaryTree<Integer> combined = tree.combine(a, tree3, true);
		// check that the left node in the combined tree is the root of the calling tree
		assertTrue(combined.getRoot().getLeft().equals(tree.getRoot()));
		// check that the right node in the combined tree is the root of the passed tree
		assertTrue(combined.getRoot().getRight().equals(tree3.getRoot()));
	}
	
	@Test
	public void testCombine2() {
		BinaryTree<Integer> combined = tree.combine(a, tree3, false);
		// check that the left node in the combined tree is the root of the calling tree
		assertTrue(combined.getRoot().getLeft().equals(tree3.getRoot()));
		// check that the right node in the combined tree is the root of the passed tree
		assertTrue(combined.getRoot().getRight().equals(tree.getRoot()));
	}
	
	// test size for trees
	@Test
	public void testSize() {
		assertEquals(tree.size(), 6);
	}
	
	@Test
	public void testSize2() {
		assertEquals(tree3.size(), 5);
	}
	
	// test size for nodes
	@Test
	public void testSize3() {
		assertEquals(a.size(), 1);
	}
	
	@Test
	public void testSize4() {
		assertEquals(e.size(), 2);
	}
	
	// test height for trees
	@Test
	public void testHeight() {
		assertEquals(tree.height(), 3);
	}
	
	@Test
	public void testHeight2() {
		assertEquals(tree3.height(), 3);
	}
	
	// test height for nodes
	@Test
	public void testHeight3() {
		assertEquals(a.height(), 1);
	}
	
	@Test
	public void testHeight4() {
		assertEquals(d.height(), 2);
	}
	
	@Test
	public void testFull() {
		assertTrue(d.full());
	}
	
	@Test
	public void testFull2() {
		assertFalse(tree.full());
	}
	
	@Test
	public void testMirror() {
		tree2.mirror();
		assertFalse(tree2.getRoot().getRight().equals(tree.getRoot().getRight()));
	}
	
	@Test
	public void testMirror2() {
		d.mirror();
		assertTrue(d.getLeft().equals(b));
		assertTrue(d.getRight().equals(a));
	}
	
	@Test
	public void testInOrder() {
		String treeOrder = "(3)(9)(5)(1)(7)(11)";
		assertEquals(tree.inOrder(), treeOrder);
	}
	
	@Test
	public void testInOrder2() {
		String treeOrder = "(3)(9)(5)";
		assertEquals(d.inOrder(), treeOrder);
	}
}
