#!/usr/bin/env bash
# Change the current directory to the directory of this script
pushd "$(dirname "$0")" >/dev/null

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

# compile the code into the bin folder, terminates if error occurred
if ! F:/Misc/jdk-11.0.17/bin/javac.exe -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/duke/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
F:/Misc/jdk-11.0.17/bin/java.exe -classpath ../bin Duke < input.txt > ACTUAL.TXT

# copy generated data and delete the generated data folder
mv ./data/duke.txt ./OUT-DATA.txt
rm -r ./data

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    # Change back to the original directory
    popd > /dev/null
    exit 0
else
    echo "Test result: FAILED"
    # Change back to the original directory
    popd > /dev/null
    exit 1
fi

