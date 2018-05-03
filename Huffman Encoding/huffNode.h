// Jessica Krynitsky jmk8vr 05/15/18 huffNode.h

#ifndef HUFFNODE_H
#define HUFFNODE_H

#include <iostream>
#include <string>
using namespace std;

class huffNode {
public:
	huffNode(char ch, int frequency);
	~huffNode();
	int freq;
	char character;
	string prefix;
	huffNode *left, *right;
	int getFreq();
	int getChar();
	// bool operator<(const huffNode &n) const;
};

#endif