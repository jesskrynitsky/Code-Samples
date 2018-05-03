#include "BinarySearchTree.h"
#include "AVLTree.h"
#include <iostream>

using namespace std;

int main() {
	BinarySearchTree bst;
	AVLTree avl;
	bst.insert("h");
	bst.insert("i");
	bst.insert("a");
	bst.insert("y");
	bst.insert("g");
	bst.insert("s");
	avl.insert("h");
	avl.insert("i");
	avl.insert("a");
	avl.insert("y");
	avl.insert("g");
	avl.insert("s");
	if (bst.find("y")) {
		cout << "yes!" << endl;
	}
	cout << bst.pathTo("s") << endl;
	cout << avl.pathTo("s") << endl;

	return 0;
}