// Jessica Krynitsky / 03/02/18 / jmk8vr / BinarySearchTree.h

#ifndef BST_H
#define BST_H
#include <string>

using namespace std;

class BinaryNode {
  BinaryNode();

  string value;
  BinaryNode* left;
  BinaryNode* right;

  friend class BinarySearchTree;
};

class BinarySearchTree {
 public:
  BinarySearchTree();
  ~BinarySearchTree();

  // insert finds a position for x in the tree and places it there.
  void insert(const string& x);

  // remove finds x's position in the tree and removes it.
  void remove(const string& x);

  // pathTo finds x in the tree and returns a string representing the path it
  // took to get there.
  string pathTo(const string& x) const;
  // find determines whether or not x exists in the tree.
  bool find(const string& x) const;
  // numNodes returns the total number of nodes in the tree.
  int numNodes() const;

 private:
  // Declare a root node
  BinaryNode* root;

  // Any other methods you need...
   void insertN(const string& x, BinaryNode *& node);
   BinaryNode* remove(BinaryNode *& curnode, const string& x);
   string pathToN(const string& x, BinaryNode * const &curnode) const;
   bool find(const string& x, BinaryNode * const &curnode) const;
   BinaryNode* findMin(BinaryNode *& node);
   int numNodes(BinaryNode * const &node) const;
};

#endif