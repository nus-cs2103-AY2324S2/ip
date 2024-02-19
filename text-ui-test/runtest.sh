#!/usr/bin/env bash
# Reference: https://stackoverflow.com/questions/59895/how-do-i-get-the-directory-where-a-bash-script-is-located-from-within-the-script
SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
echo $SCRIPT_DIR
# create bin directory if it doesn't exist
if [ ! -d "$SCRIPT_DIR/../bin" ]
then
    mkdir $SCRIPT_DIR/../bin
fi

# delete output from previous run
if [ -e "$SCRIPT_DIR/ACTUAL.TXT" ]
then
    rm $SCRIPT_DIR/ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp $SCRIPT_DIR/../src/main/java -Xlint:none -d $SCRIPT_DIR/../bin $SCRIPT_DIR/../src/main/java/Duke.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath $SCRIPT_DIR/../bin Duke < $SCRIPT_DIR/input.txt > $SCRIPT_DIR/ACTUAL.TXT

# compare the output to the expected output
diff $SCRIPT_DIR/ACTUAL.TXT $SCRIPT_DIR/EXPECTED.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
