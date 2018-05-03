#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <algorithm>

using namespace std;

#include "middleearth.h"

float computeDistance (MiddleEarth &me, string start, vector<string> dests);
void printRoute (string start, vector<string> dests);

/** @brief Finds the solution to the traveling salesperson problem by printing the path of cities with the minimum distance.
 *
 * @param argc the number of command-line parameters (should be 6)
 * @param argv an array of strings which are the width, height, number of cities, random seed, and number of cities to visit.
 */
int main (int argc, char **argv) {
    // check the number of parameters
    if ( argc != 6 ) {
        cout << "Usage: " << argv[0] << " <world_height> <world_width> "
             << "<num_cities> <random_seed> <cities_to_visit>" << endl;
        exit(0);
    }
    // we'll assume the parameters are all well-formed
    int width, height, num_cities, rand_seed, cities_to_visit;
    sscanf (argv[1], "%d", &width);
    sscanf (argv[2], "%d", &height);
    sscanf (argv[3], "%d", &num_cities);
    sscanf (argv[4], "%d", &rand_seed);
    sscanf (argv[5], "%d", &cities_to_visit);
    // Create the world, and select your itinerary
    MiddleEarth me(width, height, num_cities, rand_seed);
    vector<string> dests = me.getItinerary(cities_to_visit);
    // YOUR CODE HERE
    //cout << dests.back() << endl;
    //cout << dests[0] << endl;
    //printRoute(dests[0], dests);
    //me.printTable();
    string start = dests[0];
    vector<string> minPath = dests;
    float minDist = computeDistance(me, start, dests);
    float currDist = 0.0;

    sort(dests.begin()+1, dests.end());
    while ( next_permutation(dests.begin()+1, dests.end())) {
        currDist = computeDistance(me, start, dests);
        if (currDist < minDist) {
            minDist = currDist;
            minPath = dests;
        }
    }

    cout << "Minimum path has distance " << minDist << ": ";
    printRoute(start, minPath);

    //cout << "Distance: " << computeDistance(me, dests[0], dests) << endl;
    return 0;
}

// This method will compute the full distance of the cycle that starts
// at the 'start' parameter, goes to each of the cities in the dests
// vector IN ORDER, and ends back at the 'start' parameter.
/** @brief Computes the distance for an entire path of cities.
 *
 * @param me the world to be used.
 * @param start the first city, which is the start and end point.
 * @param dests the vector of cities in the path.
 */
float computeDistance (MiddleEarth &me, string start, vector<string> dests) {
    // YOUR CODE HERE
    float distance = 0.0;
    for (int i=0; i < dests.size()-1; i++) {
        distance += me.getDistance(dests[i], dests[i+1]);
    }
    distance += me.getDistance(dests.back(), start);
    return distance;
}

// This method will print the entire route, starting and ending at the
// 'start' parameter.  The output should be of the form:
// Erebor -> Khazad-dum -> Michel Delving -> Bree -> Cirith Ungol -> Erebor
/** @brief Prints the route of cities from start to end point.
 *
 * @param start the first city, which is the start and end point.
 * @param dests the vector of cities in the path.
 */
void printRoute (string start, vector<string> dests) {
    if (start != dests[0])
        cout << start << " -> ";
    for (int i=0; i<dests.size(); i++)
        cout << dests[i] << " -> ";
    cout << start << endl;
}
