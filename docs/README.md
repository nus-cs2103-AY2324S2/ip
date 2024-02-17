# Miss-A User Guide

![image of miss-a](../src/main/resources/images/missa.png)

Miss-A stands out as **an desktop chatbot, specially crafted for seamless task management.** 
It combines the efficiency of a Command Line Interface (CLI) 
with the delightful features of a Graphical User Interface (GUI). 
It is also capable of **locally storing all the essential task information**, 
ensuring a dynamic and enthusiastic user experience!

## Quick Start
1. Ensure you have **Java 11** installed in your Computer.

2. Download the latest missa.jar from [here](https://github.com/linnn-7/ip/releases).

3. Copy the file to the folder you want to use as the home folder for your chatbot.

4. Open a command terminal, cd into the folder you put the jar file in, and use the **java -jar missa.jar** command to run the application.

## Features
### Adding 4 Types of Tasks

* add a **TODO** task: `todo (your task)`
* add a **DEADLINE** task: `deadline (your task) /by (yyyy-M-d HH)`
* add a **EVENT** task: `event (your task) /from (yyyy-M-d HH) /to (yyyy-M-d HH)`
* add a **DO-AFTER** task:`(your task) /after (condition)`

### List All Tasks: `list`
Expected Output
```
"Here are your tasks:..."
```

### Mark/Unmark Tasks: `mark/unmark (task number)`
Expected Output
```
mark:
(task number) [X] (task content)

unmark:
(task number) [ ] (task content)
```

### Delete Tasks: `delete (task number)`
Expected Output
```
"Noted. I will remove this task..."
```

### Search Tasks: `find (keywords)`
Expected Output
```
"Yes! Here are the matching tasks in your list:..."
```

### Exit the Program: `bye`
Expected Output
```
"Bye. Have a nice day!"

Program will exit 1 second later after displaying the above message.
```

## END
Wishing you an enjoyable and delightful experience 
as you embark on your note-taking journey with Miss-A! 