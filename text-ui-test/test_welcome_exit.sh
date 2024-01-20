echo "Testing Welcome and Exit Messages..."

java -classpath ../bin Duke <<< 'bye' > ./out/welcome-exit.txt

cp ./expect/welcome-exit.txt ./expect/welcome-exit-UNIX.txt
dos2unix ./out/welcome-exit.txt ./expect/welcome-exit-UNIX.txt

diff -w ./out/welcome-exit.txt ./expect/welcome-exit-UNIX.txt
if [ $? -eq 0 ]
then
    echo "Test welcome and exit message result: PASSED"
else
    echo "Test welcome and exit message result: FAILED"
    exit 1
fi

exit 0