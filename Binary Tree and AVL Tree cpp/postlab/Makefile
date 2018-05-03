# Jessica Krynitsky / 03/02/18 / jmk8vr / Makefile  

CXX=clang++ $(CXXFLAGS)
CXXFLAGS=-Wall -O2
DEBUG=-Wall -g
OFILES= BinarySearchTree.o AVLTree.o TreePathTest.o


.SUFFIXES: .o .cpp

main: $(OFILES)
	$(CXX) $(DEBUG) $(OFILES) 
	
clean:
	-rm -f *.o *~ outputfile

AVLTree.o: AVLTree.cpp AVLTree.h
BSTest.o: BSTest.cpp BinarySearchTree.h
BinarySearchTree.o: BinarySearchTree.cpp BinarySearchTree.h
TreePathTest.o: TreePathTest.cpp AVLTree.h BinarySearchTree.h
