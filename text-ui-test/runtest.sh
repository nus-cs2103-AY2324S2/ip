#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "C:/Users/xylee/code/CS2103T/ip/bin" ]
then
    mkdir C:/Users/xylee/code/CS2103T/ip/bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp C:/Users/xylee/code/CS2103T/ip/src/main/java -Xlint:none -d C:/Users/xylee/code/CS2103T/ip/bin C:/Users/xylee/code/CS2103T/ip/src/main/java/src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath C:/Users/xylee/code/CS2103T/ip/bin Duke < input.txt > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi