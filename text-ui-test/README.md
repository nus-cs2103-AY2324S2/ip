# How to generate tests

To update test expected output, do:

```bash
cd ./text-ui-test
rm EXPECTED.TXT
cd ../src/main/java
java Duke < ../../../text-ui-test/input.txt > ../../../text-ui-test/EXPECTED.txt
```
