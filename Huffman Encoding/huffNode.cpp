// Jessica Krynitsky jmk8vr 05/15/18 huffNode.cpp

#include <iostream>
#include <string>
#include "huffNode.h"
using namespace std;

huffNode :: huffNode(char ch, int frequency) {
	freq = frequency;
	character = ch;
	left = NULL;
	right = NULL;
}
huffNode :: ~huffNode() {
	delete left;
	delete right;
}

int huffNode :: getFreq(){
	return freq;
}
int huffNode :: getChar() {
	return character;
}
// bool huffNode :: operator<(const huffNode &n) const {
// 	return(this->getFreq() < n.getFreq());
// }
