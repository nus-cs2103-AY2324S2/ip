#!/usr/bin/env bash

classpath="src/main/java"

# delete output from previous run
if [ -e "./data/tasks.csv" ]
then
    rm "./data/tasks.csv"
fi

# delete output from previous run
if [ -e "./text-ui-test/ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# compile the code 
if ! javac -cp src/main/java src/main/java/chatbot/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -cp "$classpath" "chatbot/Main" < ./text-ui-test/input.txt > ./text-ui-test/ACTUAL.TXT

# convert to UNIX format
cp ./text-ui-test/EXPECTED.TXT ./text-ui-test/EXPECTED-UNIX.TXT
dos2unix ./text-ui-test/ACTUAL.TXT ./text-ui-test/EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ./text-ui-test/ACTUAL.TXT ./text-ui-test/EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi