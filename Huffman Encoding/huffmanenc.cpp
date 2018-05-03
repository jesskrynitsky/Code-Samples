// Jessica Krynitsky jmk8vr 05/15/18 huffmanenc.cpp

#include <iostream>
#include <vector>
#include <algorithm>
#include "huffNode.h"
#include "heap.h"
#include <map>
#include <stdlib.h>
#include <stdio.h>

using namespace std;

// sets and prints the huffman encodings
void printPrefixes(huffNode* root, string code, map<char, string> &decode) {
	if (root->right == NULL && root->left ==NULL) {
		decode[root->character] = code;
		if (root->character == ' ') {
			cout << "space" << " " << code << endl;
		}
		else {
			cout << root->character << " " << code << endl;
		}
	}
	if (root->left) {
		printPrefixes(root->left, code + "0", decode);
	}
	if (root->right) {
		printPrefixes(root->right, code + "1", decode);
	}
}

int main(int argc, char **argv) {

	// variables
	int total = 0;
	int distinct = 0; 
	// array to hold frequencies of each char in file based on ASCII values
	int counts[128];
	for (int i=0; i<128; i++) {
		counts[i] = 0;
	}
	// read in the characters of the file
	FILE *theFile = fopen(argv[1], "r");
	char temp; 

	while((temp = fgetc(theFile)) != EOF) {
		int asc=(int) temp;
		// only readable characters so...
		if (asc>31 && asc<128) {
			//cout << counts[asc] << endl;
			counts[asc] += 1;
			total++;
		}
	}

	// put each character and its frequency on a heap
	heap *h = new heap();
	for (int i=31; i<128; i++) {
		if (counts[i] > 0) {
			int frequency = counts[i];
			//cout << counts[i] << endl;
			huffNode* n = new huffNode((char) i, frequency);
			h->insert(n);
			distinct++;
		}
	}

	// create a huffman tree from the heap
	while (h->size() > 1) {
		// combine the two smallest frequencies into a new node
		huffNode* a = h->deleteMin();
		huffNode* b = h->deleteMin();
		int freq = a->freq + b->freq;
		// inner character = '+'
		huffNode* tree = new huffNode('+', freq);
		tree->left = a;
		tree->right = b;
		h->insert(tree);
	}

	// for (int i=0; i<128; i++) {
	// 	codes[i] = "---";
	// }
	// print and set the characters
	map<char, string> decode;
	printPrefixes(h->findMin(), "", decode);

	// separator 
	cout << "----------------------------------------" << endl;

	rewind(theFile);

	int original=total*8;
	int compressed=0;
	//vector<huffNode*> v = h->getVect();
	//cout << v[2]->prefix << endl;

	while((temp = fgetc(theFile)) != EOF) {
		cout << decode[temp] << " ";
		compressed += decode[temp].size();	
	}
	cout << endl;

	cout << "----------------------------------------" << endl;

	double ratio = (double) original / compressed;
	double cost = (double) compressed / total;

	cout << "There are a total of " << total << " symbols that are encoded." << endl;
	cout << "There are " << distinct << " distinct symbols used." << endl;
	cout << "There were " << original << " bits in the original file." << endl;
	cout << "There were " << compressed << " bits in the compressed file." << endl;
	cout << "The compression ratio is " << ratio <<  "." << endl;
	cout << "The cost of the Huffman tree is " << cost << " bits per character." << endl;


	return 0;
}