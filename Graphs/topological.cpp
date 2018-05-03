// jessica Krynitsky jmk8vr 4/24/18 topological.cpp

#include <iostream>
#include <fstream>
#include <stdlib.h>
#include <string>
#include <vector>
#include <queue>
using namespace std;

class node {
public:
    /** @brief Constructor of the node class.
 *
 * @param s The name of the node.
 */
    node(string s) {
        name = s;
        indegree = 0;
    }
    vector<node*> dependents;
    string name;
    int indegree;
};

// global variable
vector<node*> adjList;

/** @brief Computes the topological sort of a graph creted from pairs of vertices.
 *
 * @return 0
 * @param argc The number of command-line parameters.
 * @param y The command-line parameter string.
 */
int main (int argc, char **argv) {

    // verify the correct number of parameters
    if ( argc != 2 ) {
        cout << "Must supply the input file name as the one and only parameter" << endl;
        exit(1);
    }
    // attempt to open the supplied file
    ifstream file(argv[1], ifstream::binary);
    // report any problems opening the file and then exit
    if ( !file.is_open() ) {
        cout << "Unable to open file '" << argv[1] << "'." << endl;
        exit(2);
    }
    // read in two strings
    string s1, s2;
    while (true) {

        file >> s1;
        file >> s2;

        int s1index = -1;
        int s2index = -1;

        if (s1 == "0" && s2 == "0")
            break;

        for (int i=0; i<adjList.size(); i++) {
            if (adjList[i]->name == s1)
                s1index = i;
            if (adjList[i]->name == s2)
                s2index = i;
        }
        if (s1index == -1) {
            node* temp = new node(s1);
            s1index = adjList.size();
            adjList.push_back(temp);
        }
        if (s2index == -1) {
            node* temp2 = new node(s2);
            s2index = adjList.size();
            adjList.push_back(temp2);  
        }

        adjList[s2index]->indegree++;
        adjList[s1index]->dependents.push_back(adjList[s2index]);
    }

    queue<node*> q;
    node *zero, *n;

    for (int j=0; j<adjList.size(); j++) {
        zero = adjList[j];
        if (zero->indegree == 0)
            q.push(zero);
    }

    while(q.empty() == false) {
        n = q.front();
        q.pop();
        cout << n->name << " ";
        for (int k=0; k < n->dependents.size(); k++) {
            n->dependents[k]->indegree--;
            if (n->dependents[k]->indegree == 0)
                q.push(n->dependents[k]);
        }
    }
    cout << endl;
    // close the file before exiting
    file.close();

    return 0;
}

