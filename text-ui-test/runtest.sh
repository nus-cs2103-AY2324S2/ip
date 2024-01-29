#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "/Users/cedrictan/git/ip/bin" ]
then
    mkdir /Users/cedrictan/git/ip/bin
fi

# delete output from previous run
if [ -e "/Users/cedrictan/git/ip/text-ui-test/ACTUAL.TXT" ]
then
    rm /Users/cedrictan/git/ip/text-ui-test/ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp /Users/cedrictan/git/ip/src/main/java -Xlint:none -d /Users/cedrictan/git/ip/bin /Users/cedrictan/git/ip/src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath /Users/cedrictan/git/ip/bin Duke < input.txt > ACTUAL.TXT

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