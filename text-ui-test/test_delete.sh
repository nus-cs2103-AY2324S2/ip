# Test deleting tasks
echo "Test deleting tasks.."

java -classpath ../bin Main < ./in/delete-01.txt  > ./out/delete-01.txt
cp ./expect/delete-01.txt ./expect/delete-01-UNIX.txt
dos2unix ./out/delete-01.txt ./expect/delete-01-UNIX.txt
diff -w ./out/delete-01.txt ./expect/delete-01-UNIX.txt
if [ $? -eq 0 ]
then
    echo "Test delete task result: PASSED"
else
    echo "Test delete task result: FAILED"
    exit 1
fi

exit 0
