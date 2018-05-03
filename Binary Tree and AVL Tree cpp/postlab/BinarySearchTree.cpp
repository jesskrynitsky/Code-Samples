// Jessica Krynitsky / 03/02/18 / jmk8vr / BinarySearchTree.cpp

#include "BinarySearchTree.h"
#include <string>

using namespace std;

BinaryNode :: BinaryNode() {
	value = "";
	left = NULL;
	right = NULL;
}

BinarySearchTree :: BinarySearchTree() {
	root = NULL;
}

BinarySearchTree :: ~BinarySearchTree() {
	delete root->left;
	delete root->right;
	delete root;
}

void BinarySearchTree :: insert(const string& x) {
	insertN(x,root);
}

void BinarySearchTree :: insertN(const string& x, BinaryNode *& curnode) {
	if (curnode == NULL) {
		curnode = new BinaryNode();
		curnode->value = x;
	}
	else if (x < curnode->value) {
		insertN(x, curnode->left);
	}
	else if (x > curnode->value) {
		insertN(x, curnode->right);
	}
	else {}
}

void BinarySearchTree::remove(const string& x) { root = remove(root, x); }

// private helper for remove to allow recursion over different nodes. returns
// an AVLNode* that is assigned to the original node.
BinaryNode* BinarySearchTree::remove(BinaryNode*& n, const string& x) {
  if (n == NULL) {
    return NULL;
  }
  // first look for x
  if (x == n->value) {
    // found
    // no children
    if (n->left == NULL && n->right == NULL) {
      return NULL;
    }
    // single child
    if (n->left == NULL) {
      return n->right;
    }
    if (n->right == NULL) {
      return n->left;
    }
    // two children -- tree may become unbalanced after deleting n
    string sr = findMin(n->right)->value;
    n->value = sr;
    n->right = remove(n->right, sr);
  } else if (x < n->value) {
    n->left = remove(n->left, x);
  } else {
    n->right = remove(n->right, x);
  }
  return n;
}


BinaryNode* BinarySearchTree :: findMin(BinaryNode *& node) {
	if (node->left == NULL) {
		return node;
	}
	else {
		return findMin(node->left);
	}
}
 
string BinarySearchTree :: pathTo(const string& x) const {
	if (find(x)) {
		return pathToN(x, root);
	}
	return "";
}

string BinarySearchTree :: pathToN(const string& x, BinaryNode * const &curnode) const {
	string ret = "";
	if (curnode == NULL) { // element doesn't exist 
		ret = "";
		return ret;
	}
	else if (x == curnode->value) {
		ret = curnode->value + ret;
		return ret;
	}
	else if (x < curnode->value) {
		ret = pathToN(x, curnode->left);
		ret = curnode->value + " " + ret;
		return ret;
	}
	else if (x > curnode->value) {
		ret = pathToN(x, curnode->right);
		ret = curnode->value + " " + ret;
		return ret;
	}
	return ret;
}

bool BinarySearchTree :: find(const string& x) const {
	return find(x, root);
}

bool BinarySearchTree :: find(const string& x, BinaryNode * const &curnode) const{
  if (x == curnode->value) {
    return true;
  }
  else if (x < curnode->value) {
    if (curnode->left == NULL) {
      return false;
    }
    else {
      return find(x, curnode->left);
    }
  }
  else {
    if (curnode->right == NULL) {
      return false;
    }
    else {
      return find(x, curnode->right);
    }
  }
}

int BinarySearchTree :: numNodes() const {
	return numNodes(root);
}

int BinarySearchTree :: numNodes(BinaryNode * const &node) const{
	if (node == NULL) {
		return 0;
	}
	else {
		return numNodes(node->left) + 1 + numNodes(node->right);
	}
}