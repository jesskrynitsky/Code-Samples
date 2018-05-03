# Jessica Krynitsky jmk8vr 05/15/18 Makefile

CXX	= clang++ 

CXXFLAGS = -Wall -g 

OFILES = traveling.o middleearth.o

.SUFFIXES: .o .cpp

main: $(OFILES)
	$(CXX) $(CXXFLAGS) $(OFILES)
	doxygen

clean:
	/bin/rm -f *.o *~