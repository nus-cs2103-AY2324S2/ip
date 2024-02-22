@ECHO OFF

REM create bin directory if it doesn't exist
if not exist C:\Users\xylee\code\CS2103T\ip\bin mkdir C:\Users\xylee\code\CS2103T\ip\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp C:\Users\xylee\code\CS2103T\ip\src\main\java -Xlint:none -d C:\Users\xylee\code\CS2103T\ip\bin C:\Users\xylee\code\CS2103T\ip\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath C:\Users\xylee\code\CS2103T\ip\bin seedu.chrisPBacon.seedu.chrisPBacon < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
