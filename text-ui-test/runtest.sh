#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

echo -n > ../data/jerrybot.txt

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

cd ..

echo "Current working directory: $(pwd)"

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ./bin jerrybot/JerryBot < text-ui-test/input.txt > text-ui-test/ACTUAL.TXT

# convert to UNIX format
cp text-ui-test/EXPECTED.TXT text-ui-test/EXPECTED-UNIX.TXT
dos2unix text-ui-test/ACTUAL.TXT text-ui-test/EXPECTED-UNIX.TXT

# compare the output to the expected output
diff text-ui-test/ACTUAL.TXT text-ui-test/EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi