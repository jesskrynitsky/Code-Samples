/*
 * Jessica Krynitsky
 * jmk8vr
 * Sources: BigJava textbook, stackoverflow, codeproject.com, java2blog, 2 TA's whose names I don't know
 */
public class BinaryTree<T> {

	private BinaryTreeNode<T> root;

	public BinaryTree() {
		this(null);
	}

	public BinaryTree(BinaryTreeNode<T> newRoot) {
		this.root = newRoot;
	}
	
	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	public void setRoot(BinaryTreeNode<T> root) {
		this.root = root;
	}

	@Override
	public boolean equals(Object o) {
		// first check that the object being compared is a binary tree, and cast it
		if (!(o instanceof BinaryTree)) { return false; }
		BinaryTree<?> that = (BinaryTree<?>) o;
		// call the BinaryTreeNode equals method on the roots of the trees
		return this.root.equals(that.getRoot());
	}

	public BinaryTree<T> deepCopy() {
		// return a new binary tree in which the root is a deep copy of the calling root
		return new BinaryTree<T>(root.deepCopy());
	}

	public BinaryTree<T> combine(BinaryTreeNode<T> newRoot, BinaryTree<T> t,
			boolean left) {
		// initialize the new tree to be returned
		BinaryTree<T> retTree = new BinaryTree<T>();
		retTree.setRoot(newRoot.deepCopy());
		if (left == true) {
			// set the left side to a deep copy of the calling tree 
			retTree.root.setLeft(this.getRoot().deepCopy());
			// set the right side to a deep copy of the passed tree
			retTree.root.setRight(t.getRoot().deepCopy());
		}
		else if (left == false) {
			// set the left side to a deep copy of the passed tree
			retTree.root.setLeft(t.getRoot().deepCopy());
			// set the right side to a deep copy of the calling tree
			retTree.root.setRight(this.getRoot().deepCopy());
		}
		return retTree;
	}
	
	public int size(){
		// check if the root is null
		if (this.root == null) {
			return 0;
		}
		return root.size();
	}
	
	public int height(){
		// check for the case of a null root
        if (this.root == null) {
            return 0;
        }
        // call the height method for a node on the root of the tree
        return this.root.height();
    }
	
	public boolean full(){
		// this is assuming the tree is "full" even if it is empty
		if (this.root == null) {
			return true;
		}
		return this.root.full();
	}
	
	public void mirror(){
		// check that the root is not null
		if (this.root != null) {
			this.root.mirror();
		}
	}
	
	public String inOrder(){
		// check if the root is null
		if (this.root == null) {
			return null;	
		}
		return this.root.inOrder();
	}
}
