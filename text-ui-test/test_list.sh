# Test listing tasks
echo "Testing listing tasks..."

  java -classpath ../bin Main < ./in/list-01.txt  > ./out/list-01.txt
cp ./expect/list-01.txt ./expect/list-01-UNIX.txt
dos2unix ./out/list-01.txt ./expect/list-01-UNIX.txt
diff -w ./out/list-01.txt ./expect/list-01-UNIX.txt
if [ $? -eq 0 ]
then
    echo "Test listing tasks result: PASSED"
else
    echo "Test listing tasks result: FAILED"
    exit 1
fi

# Test random permutation of commands
echo "Testing random permutation of commands..."

java -classpath ../bin Main < ./in/list-02.txt  > ./out/list-02.txt
cp ./expect/list-02.txt ./expect/list-02-UNIX.txt
dos2unix ./out/list-02.txt ./expect/list-02-UNIX.txt
diff -w ./out/list-02.txt ./expect/list-02-UNIX.txt
if [ $? -eq 0 ]
then
    echo "Test random permutation of commands result: PASSED"
else
    echo "Test random permutation of commands result: FAILED"
    exit 1
fi

java -classpath ../bin Main < ./in/list-03.txt  > ./out/list-03.txt
cp ./expect/list-03.txt ./expect/list-03-UNIX.txt
dos2unix ./out/list-03.txt ./expect/list-03-UNIX.txt
diff -w ./out/list-03.txt ./expect/list-03-UNIX.txt
if [ $? -eq 0 ]
then
    echo "Test random permutation of commands result: PASSED"
else
    echo "Test random permutation of commands result: FAILED"
    exit 1
fi

java -classpath ../bin Main < ./in/list-04.txt  > ./out/list-04.txt
cp ./expect/list-04.txt ./expect/list-04-UNIX.txt
dos2unix ./out/list-04.txt ./expect/list-04-UNIX.txt
diff -w ./out/list-04.txt ./expect/list-04-UNIX.txt
if [ $? -eq 0 ]
then
    echo "Test random permutation of commands result: PASSED"
else
    echo "Test random permutation of commands result: FAILED"
    exit 1
fi

exit 0
