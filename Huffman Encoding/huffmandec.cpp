// This program is the skeleton code for the lab 10 in-lab.  It uses
// C++ streams for the file input, and just prints out the data when
// read in from the file.

#include <iostream>
#include <fstream>
#include <sstream>
#include <stdlib.h>
#include "huffNode.h"
using namespace std;


void huffTree(huffNode* &n, char c, string prefix) {
        if (prefix.length() == 0) {
            n->character = c;
        }
        if (prefix[0] == '0') {
            if (n->left == NULL) {
                n->left = new huffNode(0, 0);
            }
            huffTree(n->left, c, prefix.substr(1, prefix.length()-1));
        }
        if (prefix[0] == '1') {
            if (n->right == NULL) {
                n->right = new huffNode(0, 0);
            }
            huffTree(n->right, c, prefix.substr(1, prefix.length()-1));
        }
}


// main(): we want to use parameters
int main (int argc, char **argv) {

    huffNode* temp = new huffNode(0,0);

    // verify the correct number of parameters
    if ( argc != 2 ) {
        cout << "Must supply the input file name as the only parameter" << endl;
        exit(1);
    }
    // attempt to open the supplied file; must be opened in binary
    // mode, as otherwise whitespace is discarded
    ifstream file(argv[1], ifstream::binary);
    // report any problems opening the file and then exit
    if ( !file.is_open() ) {
        cout << "Unable to open file '" << argv[1] << "'." << endl;
        exit(2);
    }
    // read in the first section of the file: the prefix codes
    while ( true ) {
        string character, prefix;
        // read in the first token on the line
        file >> character;
        // did we hit the separator?
        if ( (character[0] == '-') && (character.length() > 1) )
            break;
        // check for space
        if ( character == "space" )
            character = " ";
        // read in the prefix code
        file >> prefix;
        // do something with the prefix code
        // cout << "character '" << character << "' has prefix code '"
        //     << prefix << "'" << endl;
        // put character into tree
        huffTree(temp, character[0], prefix);
    }

    // read in the second section of the file: the encoded message
    stringstream sstm;
    while ( true ) {
        string bits;
        // read in the next set of 1's and 0's
        file >> bits;
        // check for the separator
        if ( bits[0] == '-' )
            break;
        // add it to the stringstream
        sstm << bits;
    }
    string allbits = sstm.str();
    // at this point, all the bits are in the 'allbits' string
    // cout << "All the bits: " << allbits << endl;

    // decode allbits
    int i = 0;
    huffNode *n = temp;

    while(i < allbits.size()) {
       // cout << "s" << endl;
        
        char bit = allbits[i];
        //cout << bit << endl;
        
        if(n->left != NULL && bit == '0') {
            n = n->left;
            i++;
        }
        if(n->right != NULL && bit == '1') {
            //cout << "going right" << endl;
            n = n->right;
            i++;
        }
        if(n->left == NULL && n->right == NULL) {
            cout << n->character;
            n = temp;
        }
        
    }
    cout << endl;
    // close the file before exiting
    file.close();

    return 0;
}