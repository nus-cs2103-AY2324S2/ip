#!/usr/bin/env bash

INPUT_FOLDER="./Input"
EXPECTED_FOLDER="./Expected"

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

for input_file in "$INPUT_FOLDER"/*; do
  expected_file="$EXPECTED_FOLDER/$(basename "$input_file")"

  # delete output from previous run
  if [ -e "./ACTUAL.TXT" ]
  then
      rm ACTUAL.TXT
  fi

  # compile the code into the bin folder, terminates if error occurred
  if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/*.java
  then
      echo "********** BUILD FAILURE **********"
      exit 1
  fi

  # run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
  java -classpath ../bin Duke < "$input_file" > ACTUAL.TXT

  # convert to UNIX format
  cp "$expected_file" EXPECTED-UNIX.TXT
#  dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

  # compare the output to the expected output
  # -w flag: Ignore whitespaces
  diff -w ACTUAL.TXT EXPECTED-UNIX.TXT
  if [ $? -eq 0 ]
  then
      echo "Test result for $(basename "$input_file"): PASSED"
  else
      echo "Test result for $(basename "$input_file"): FAILED"
  fi
done

exit 0