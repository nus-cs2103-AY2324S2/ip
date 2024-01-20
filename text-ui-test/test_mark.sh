# Test marking tasks
echo "Testing marking tasks..."

java -classpath ../bin Duke < ./in/mark-01.txt  > ./out/mark-01.txt

cp ./expect/mark-01.txt ./expect/mark-01-UNIX.txt
dos2unix ./out/mark-01.txt ./expect/mark-01-UNIX.txt
diff -w ./out/mark-01.txt ./expect/mark-01-UNIX.txt
if [ $? -eq 0 ]
then
    echo "Test marking tasks result: PASSED"
else
    echo "Test marking tasks result: FAILED"
    exit 1
fi

# Test unmarking tasks
echo "Testing unmarking tasks..."

java -classpath ../bin Duke < ./in/mark-02.txt  > ./out/mark-02.txt

cp ./expect/mark-02.txt ./expect/mark-02-UNIX.txt
dos2unix ./out/mark-02.txt ./expect/mark-02-UNIX.txt
diff -w ./out/mark-02.txt ./expect/mark-02-UNIX.txt
if [ $? -eq 0 ]
then
    echo "Test unmarking tasks result: PASSED"
else
    echo "Test unmarking tasks result: FAILED"
    exit 1
fi

exit 0
