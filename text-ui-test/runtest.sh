#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
for i in {1,2,3,4,5}; do
    if [ -e "./ACTUAL$i.TXT" ]; then
        rm ACTUAL$i.TXT
    fi
done

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input1.txt file and redirect the output to the ACTUAL.TXT
for i in {1,2,3,4,5}; do
    java -classpath ../bin Duke < input$i.txt > ACTUAL$i.TXT
done

# convert to UNIX format
for i in {1,2,3,4,5}; do
    cp EXPECTED$i.TXT EXPECTED-UNIX$i.TXT
    dos2unix ACTUAL$i.TXT EXPECTED-UNIX$i.TXT
done


# compare the output to the expected output
diff ACTUAL1.TXT EXPECTED-UNIX1.TXT
if [ $? -eq 0 ]
then
    echo "Test 1 result: PASSED"
else
    echo "Test 1 result: FAILED, Check your mark command!"
fi

diff ACTUAL2.TXT EXPECTED-UNIX2.TXT
if [ $? -eq 0 ]
then
    echo "Test 2 result: PASSED"
else
    echo "Test 2 result: FAILED, Check your deadline creation exceptions!"
fi

diff ACTUAL3.TXT EXPECTED-UNIX3.TXT
if [ $? -eq 0 ]
then
    echo "Test 3 result: PASSED"
else
    echo "Test 3 result: FAILED, Check your event creation exceptions!"
fi

diff ACTUAL4.TXT EXPECTED-UNIX4.TXT
if [ $? -eq 0 ]
then
    echo "Test 4 result: PASSED"
else
    echo "Test 4 result: FAILED, Check your todo creation exceptions!"
fi

diff ACTUAL5.TXT EXPECTED-UNIX5.TXT
if [ $? -eq 0 ]
then
    echo "Test 5 result: PASSED"
else
    echo "Test 5 result: FAILED, Check your delete function"
fi
exit 0