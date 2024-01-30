#!/usr/bin/env bash

## create bin directory if it doesn't exist
#if [ ! -d "../bin" ]
#then
#    mkdir ../bin
#fi
#
## delete output from previous run
#if [ -e "./ACTUAL.TXT" ]
#then
#    rm ACTUAL.TXT
#fi
#
## compile the code into the bin folder, terminates if error occurred
#if ! javac -cp ../src/main/java/chatbot/* -Xlint:none -d ../bin ../src/main/java/chatbot/*.java
#then
#    echo "********** BUILD FAILURE **********"
#    exit 1
#fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
~/Documents/jdk-11.0.17.jdk/Contents/Home/bin/java -classpath ../out/production/ip chatbot.Duke < input.txt > ACTUAL.TXT

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