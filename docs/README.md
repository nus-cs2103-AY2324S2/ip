# someBOTy User Guide

![someBOTy Ui.png](https://ChillinRage.github.io/ip/Ui.png)

*"Don't waste your memory cache on trivial tasks. Just use it."*

---
<br>

## Get Started
1. Ensure that you have java 11 installed on your computer.
2. Download the latest `someBOTy.jar` from here.
3. Open the command terminal and navigate to the path the jar file is in.
4. Run the application by entering `java -jar someBOTy.jar`.
5. Play around with it.
<br>

## Features

### Get list of accepted commands
Enter `help` to get the list of commands that the bot understands.

<br>

### Get list of accepted date formats
Enter `dateFormats` to get the list of accepted date formats.

<br>

### Add 'ToDo' task
Add a new task with no deadlines nor time period. Use the following syntax:
```
todo <task description>
```

<br>

### Add 'Deadline'task
Add a new task with a fixed deadline. Use the following syntax:
```
deadline <task description> /by <date>
```
Note: Only some date formats are accepted by the program, to see all acceptable date formats, type `dateFormats`.

<br>

### Add 'Event' task
Add a new task with a start and end dates. Use the following syntax:
```
event <task description> /from <start date> /to <end date>
```
Note: Only some date formats are accepted by the program, to see all acceptable date formats, type `dateFormats`.

<br>

### List tasks and expenses
List all of the tasks or expenses you currently have using `list task` and `list expense`.

<br>

### Marking, Unmarking, and Deleting a task
Marks, unmarks or deletes a task according to its list number. Use the following syntax:
```
mark <list number>
unmark <list number>
delete <list number>
```
Note: Each command can mark/unmark/delete only 1 task.

<br>

### Finding a task
Display all tasks that matches the given word/phrase. Use the following syntax:
```
find <phrase>
```
Note: the given `phrase` is NOT case sensitive.

<br>

### Saving the lists
Updates the data files with your current task and expense lists using `save`.
Note: This command saves both your task and expense lists.

<br>

### Clearing a task or expense list
Removes all existing tasks/expenses from the list using `clear task` and `clear expense`.

<br>

### Exiting the program
You can exit the program by using `ALT`+`F4` or clicking the `x` button on the top right corner of the window,
but the proper way to exit is by typing `bye` to ensure that your existing lists are updated and saved.

---
