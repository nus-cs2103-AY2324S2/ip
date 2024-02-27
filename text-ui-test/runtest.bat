@ECHO OFF

REM create bin directory if it doesn't exist
if not exist .\bin mkdir .\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
for %%i in (.\src\main\java\*.java) do (
    javac -cp .\src\main\java -Xlint:none -d .\bin %%i
)

IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath .\bin Panda < .\text-ui-test\input.txt > .\text-ui-test\ACTUAL.TXT

REM compare the output to the expected output
FC .\text-ui-test\ACTUAL.TXT .\text-ui-test\EXPECTED.TXT