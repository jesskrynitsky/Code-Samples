// Jessica Krynitsky jmk8vr 05/15/18 heap.h

#ifndef HEAP_H
#define HEAP_H

#include <iostream>
#include <vector>
#include "huffNode.h"
using namespace std;

class heap{
public:
	heap();
	~heap();
	void insert(huffNode *n);
	void percolateUp(int hole);
	huffNode* deleteMin();
	huffNode* findMin();
	void percolateDown(int hole);
	vector<huffNode*> getVect();
	unsigned int size();

private:
	int heap_size;
	vector<huffNode*> list;
};

#endif