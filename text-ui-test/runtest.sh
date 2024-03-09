#!/usr/bin/env bash

# IMPORTANT: Deprecated as of Level-8 onwards

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# Remove any existing test files
if [ -e "./out" ]; then
    # If it exists, delete it
    rm -rf "./out"
fi

# Create ./actual
mkdir "./out"

# compile the code into the bin folder, terminates if error occurred
CLASSPATH="../src/main/java"
if ! javac -cp "$CLASSPATH" -Xlint:none -d ../bin ../src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

failure_count=0

run_test() {
    "$@"
    if [ $? -ne 0 ]; then
        echo "Test failed: $@"
        failure_count=$((failure_count + 1))
    fi
}

# Run various tests
run_test ./test_welcome_exit.sh
run_test ./test_add_task.sh
run_test ./test_list.sh
run_test ./test_mark.sh
run_test ./test_error_handling.sh
run_test ./test_delete.sh

if [ $failure_count -ne 0 ]; then
    echo "Number of tests failed: $failure_count"
    exit 1
else
    echo "All tests passed"
    exit 0
fi