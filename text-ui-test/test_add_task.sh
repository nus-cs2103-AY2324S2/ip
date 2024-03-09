# Test adding a task
echo "Testing add task..."

java -classpath ../bin Main < ./in/task-01.txt  > ./out/task-01.txt
cp ./expect/task-01.txt ./expect/task-01-UNIX.txt
dos2unix ./out/task-01.txt ./expect/task-01-UNIX.txt
diff -w ./out/task-01.txt ./expect/task-01-UNIX.txt
if [ $? -eq 0 ]
then
    echo "Test add task result: PASSED"
else
    echo "Test add task result: FAILED"
    exit 1
fi

exit 0