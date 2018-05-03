/*
 * Jessica Krynitsky
 * jmk8vr
 * Sources: BigJava textbook, stackoverflow, codeproject.com, java2blog, 2 TA's whose names I don't know
 */

public class BinaryTreeNode<T> {
	
	private BinaryTreeNode<T> left;
	private BinaryTreeNode<T> right;
	private T data;
	
	public BinaryTreeNode(){
		this(null,null,null);
	}
	
	public BinaryTreeNode(T theData){
		this(theData,null,null);
	}
	
	public BinaryTreeNode(T theData, BinaryTreeNode<T> leftChild, BinaryTreeNode<T> rightChild){
		data = theData;
		left = leftChild;
		right = rightChild;
	}
	
	public int size(){
		int size = 0; //the size of the tree
		
		//The size of the tree rooted at this node is one more than the
		//sum of the sizes of its children.
		if(left != null){
			size = size + left.size();
		}
		if(right != null){
			size = size + right.size();
		}
		return size + 1; //add one to account for the current node
	}

	public BinaryTreeNode<T> getLeft() {
		return left;
	}

	public void setLeft(BinaryTreeNode<T> left) {
		this.left = left;
	}

	public BinaryTreeNode<T> getRight() {
		return right;
	}

	public void setRight(BinaryTreeNode<T> right) {
		this.right = right;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
	public BinaryTreeNode<T> deepCopy(){
		// create new left and right nodes that are initially null
		BinaryTreeNode<T> left = null;
		BinaryTreeNode<T> right = null;
		// if the object being copied has a left or right node, create a deep copy of that node
		if ( this.left != null) {
			left = this.left.deepCopy();
		}
		if ( this.right != null) {
			right = this.right.deepCopy();
		}
		// return the deep copy once there are no further left or right nodes
		return new BinaryTreeNode<T>(this.data, left, right);		
	}
	
	@Override
	public boolean equals(Object o){
		// first check if the reference values are equal
		if (this == o) {
			return true;
		}
		// check that the object does not point to null and that it is an instance of BinaryTreeNode
		if (o == null || !(o instanceof BinaryTreeNode<?>)) {
			return false;
		}
		// cast the object to a BinaryTreeNode type
		BinaryTreeNode<?> that = (BinaryTreeNode<?>) o;
		
		// compare the data of the current nodes
		if (!this.data.equals(that.getData())) {
			return false;
		}
		// compares the children of the node recursively - checks all of the possible false cases
		if (this.left == null && that.getLeft() != null) {
			return false;
		}
		if (this.left != null) {
			if (!this.left.equals(that.getLeft())) {
				return false;
			}
		}
		if (this.right == null && that.getRight() != null) {
			return false;
		}
		if (this.right != null) {
			if (!this.right.equals(that.getRight())) {
				return false;
			}
		}
		// all false cases have been checked, so they must be equal
		return true;
	}
	
	public int height(){
		if (this.left != null) {
			if (this.right != null) {
				// when both left and right are not null
				return 1 + Math.max(this.left.height(), this.right.height());
			}
			// when right is null but left is not
			return 1 + this.left.height();
		}
		if (this.right != null) {
			// when left is null but right is not
			return 1 + this.right.height();
		}
		//when both left and right are null
		return 1;	
	}

	public boolean full(){
		// check all possible true cases
		if (this.left != null && this.right != null) {
			return (this.left.full() && this.right.full());
		} else if (this.left == null && this.right == null) {
			return true;
		} else {
			return false;
		}
	}
	
	// switch the left and right child nodes by setting them to a deep copy of the opposite node
	public void mirror(){
		// if only the left child is null
		if (this.left == null && this.right != null) {
			this.setLeft(this.right.deepCopy());
			this.setRight(null);
			this.left.mirror();
		// if only the right child is null
		} else if (this.left != null && this.right == null) {
			this.setRight(this.left.deepCopy());
			this.setLeft(null);
			this.right.mirror();
		// if both are not null
		} else if (this.left != null && this.right != null) {
			BinaryTreeNode<T> temp = this.left.deepCopy();
			this.setLeft(this.right.deepCopy());
			this.setRight(temp);
			this.right.mirror();
			this.left.mirror();
		}
		// if left and right are both null do nothing
	}
	
	public String inOrder(){
		//traverse the tree in order of left, root, right
		String str = new String();
		if (this.left != null) {
			str += this.left.inOrder();
		}
			str += "(" + this.getData() + ")";
		if (this.right != null) {
			str += this.right.inOrder();
		}
		return str;
	}

}
