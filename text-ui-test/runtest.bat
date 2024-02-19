@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous runs
if exist ACTUAL.TXT del ACTUAL.TXT
if exist ACTUAL2.TXT del ACTUAL2.TXT

REM compile the code into the bin folder
javac -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\duke\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM run the first test
java -classpath ..\bin Duke < input.txt > ACTUAL.TXT
REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT

REM run the second test
java -classpath ..\bin Duke < input2.txt > ACTUAL2.TXT
REM compare the output to the expected output
FC ACTUAL2.TXT EXPECTED2.TXT
