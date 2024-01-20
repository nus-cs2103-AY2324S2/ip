# Test error tasks
echo "Test error handling.."

echo "Test unknown commands..."
java -classpath ../bin Duke < ./in/error-01.txt  > ./out/error-01.txt
cp ./expect/error-01.txt ./expect/error-01-UNIX.txt
dos2unix ./out/error-01.txt ./expect/error-01-UNIX.txt
diff -w ./out/error-01.txt ./expect/error-01-UNIX.txt
if [ $? -eq 0 ]
then
    echo "Test error tasks result: PASSED"
else
    echo "Test error tasks result: FAILED"
    exit 1
fi

echo "Test bad task inputs..."
java -classpath ../bin Duke < ./in/error-02.txt  > ./out/error-02.txt
cp ./expect/error-02.txt ./expect/error-02-UNIX.txt
dos2unix ./out/error-02.txt ./expect/error-02-UNIX.txt
diff -w ./out/error-02.txt ./expect/error-02-UNIX.txt
if [ $? -eq 0 ]
then
    echo "Test error tasks result: PASSED"
else
    echo "Test error tasks result: FAILED"
    exit 1
fi

echo "Test bad mark/unmark inputs..."
java -classpath ../bin Duke < ./in/error-03.txt  > ./out/error-03.txt
cp ./expect/error-03.txt ./expect/error-03-UNIX.txt
dos2unix ./out/error-03.txt ./expect/error-03-UNIX.txt
diff -w ./out/error-03.txt ./expect/error-03-UNIX.txt
if [ $? -eq 0 ]
then
    echo "Test error tasks result: PASSED"
else
    echo "Test error tasks result: FAILED"
    exit 1
fi

exit 0

