// Jessica Krynitsky jmk8vr 05/15/18 heap.cpp

#include <iostream>
#include <vector>
#include "heap.h"
#include "huffNode.h"
using namespace std;

// default constructor
heap::heap() : heap_size(0) {
    list.reserve(256);
    list.push_back(new huffNode(0,0));
}

// the destructor doesn't need to do much
heap::~heap() {
    heap_size = 0;
    list.clear();
}

void heap::insert(huffNode* n) {
    // a vector push_back will resize as necessary
    list.push_back(n);
    // move it up into the right position
    percolateUp(++heap_size);
}

void heap::percolateUp(int hole) {
    // get the value just inserted
    huffNode *x = list[hole];
    // cout << x->getFreq() << endl;
    // while we haven't run off the top and while the
    // value is less than the parent...
    for ( ; (hole > 1) && (x->getFreq() < list[hole/2]->getFreq()); hole /= 2 )
        list[hole] = list[hole/2]; // move the parent down
    // correct position found!  insert at that spot
    list[hole] = x;
}

huffNode* heap::deleteMin() {
    // make sure the heap is not empty
    if ( heap_size == 0 )
        throw "deleteMin() called on empty heap";
    // save the value to be returned
    huffNode* ret = list[1];
    // move the last inserted position into the root
    list[1] = list[heap_size--];
    // make sure the vector knows that there is one less element
    list.pop_back();
    // percolate the root down to the proper position
    percolateDown(1);
    // return the old root node
    return ret;
}

void heap::percolateDown(int hole) {
    // get the value to percolate down
    huffNode* x = list[hole];
    // while there is a left child...
    while ( hole*2 <= heap_size ) {
        int child = hole*2; // the left child
        // is there a right child?  if so, is it lesser?
        if ( (child+1 <= heap_size) && (list[child+1]->freq < list[child]->freq) )
            child++;
        // if the child is greater than the node...
        if ( list[child]->freq < x->freq ) {
            list[hole] = list[child]; // move child up
            hole = child;             // move hole down
        } else
            break;
    }
    // correct position found!  insert at that spot
    list[hole] = x;
}

huffNode* heap::findMin() {
    if ( heap_size == 0 )
        throw "findMin() called on empty heap";
    return list[1];
}

unsigned int heap::size() {
    return heap_size;
}

vector<huffNode*> heap :: getVect(){
    return list;
}
