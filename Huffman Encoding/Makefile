# Jessica Krynitsky jmk8vr 05/15/18 Makefile

# Defines the C++ compiler we'll be using
CXX	= clang++ 

# Defines the flags we'll be passing to the C++ compiler
CXXFLAGSE = -Wall -g -o encoder
CXXFLAGSD	= -Wall -g -o decoder
# All of the .o files for our program
EFILES = huffmanenc.o heap.o huffNode.o
DFILES	= huffNode.o huffmandec.o

# This tells make to create a .o file from a .cpp file, using the
# defaults above (i.e. the CXX and CXXFLAGS macros)
.SUFFIXES: .o .cpp

# How to compile our final program.  Note that we do NOT specify an
# output executable name -- in order for this to work with the grading
# system, the file needs to be a.out (a.exe in Cygwin).
main: encode decode 

encode:	$(EFILES)
	$(CXX) $(CXXFLAGSE) $(EFILES)

decode:	$(DFILES)
	$(CXX) $(CXXFLAGSD) $(DFILES)
# This will clean up (remove) all our object files.  The -f option
# tells rm to forcily remove the files (i.e. don't ask if they should
# be removed or not).  This removes object files (*.o) and Emacs
# backup files (*~)
clean:
	/bin/rm -f *.o *~