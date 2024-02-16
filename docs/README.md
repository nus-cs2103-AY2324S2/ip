# WindBro User Guide

![Ui.png.png](Ui.png)

WindBro is a desktop app that allows you to manage your tasks conveniently.
It has a GUI while still allowing you to
use Command to navigate through the apps.

## Quick Start 
1. Ensure that Java 11 is installed in your computer
2. Download the latest `windbro.jar` from [here](https://github.com/Windofbitter/ip/releases/tag/v0.2)
3. Copy the file to the folder where you want to use as the _home folder_ for your WindBro
4. Open a command line and use `cd` command to navigate to the home folder containing WindBro
5. Run `java -jar windbro.jar` command to run the application


## Features

## Adding deadlines: `deadline`

Adds a deadline to your task list. A deadline is a task
that has a due date.

Format: `deadline DESCRIPTION /by DUE_DATE`

- The due date must be entered in the correct format `yyyy-mm-dd HH:MM`

Example: `deadline Finish CS2103T assignment /by 2024-02-06 18:00`

Expected output:

```
Got it. I've added this task: 
[D][] Finish CS2103T assignment(by: 02 06 2024 18:00)
Now you have 1 takss in the list
```

## Adding Todo `todo`

Adds a todo to your task list. Todo task does not
have due dates.

Format: `todo DESCRIPTION`

Example: `todo Finish CS2103T Assignemnt`

Expected output:

```
Got it. I've added this task: 
[T][] Finish CS2103T assignment
Now you have 1 taks in the list
```

## Adding Event `event`

Adds an event to your task list. An event is a task that
has both start date and end date.

Format: `event DESCRIPTION /from START_DATE /to END_DATE`

- Both start date should be entered in the correct format `yyyy-mm-dd HH:MM`
- The end date of the event must be later than the start date of the event

example: `event CS2103T exam /from 2024-05-01 18:00 /to 2024-05-01: 20:00`

Expected output:

```
Got it. I've added this task: 
[E][] CS2103 T exam(from: 01 05 2024 18:00 to： 01 05 2024 20：00）
Now you have 1 tasks in the list.
```  

## Listing Task: `list`

lists all your tasks.

Format: `list`

Example:

Suppose you have two todos in the list. You can view them by:

`list`

Expected output:

```
Here are the tasks in yoyr list: 
1.[T][] todo1 
2.[T][] todo2
```

The first bracket indicates the type of the task, T for todo, D for deadline
and E for event. The second bracket indicates whether the task is done,
if it is done, it would be marked by a cross.

## Deleting a task: `delete`

Deletes a task by the index on the task list.

Format: `delete INDEX`

- The index must be valid
- The index is the index of the task you want to delete in the task list

Example:  
Suppose you have two tasks on the list, and you view them using `list` command

```
Here are the tasks in your list: 
1.[T][] Finish CS2103T assignment
2.[T][] Finish CS2101 assignment
```

Then, if we want to delete the first task, we can call

`delete 1`

Expected output:

```
Noted. I've removed this task: 
[T][] Finish CS2103T assignment
Now you have 1 tasks in the list
```

## Marking a task as done: `mark`

Marks a task as done.

Format: `mark INDEX`

- The index is the index of the task in the list you want to mark
- The index must be valid
- If the task is not done, it would be marked as done
- If the task is already done, nothing will be changed

Example:  
Suppose you have an undone todo, and is at index 1. You can mark it as done
using

`mark 1`

Expected output:

```
Nice!. I've marked this task as done: 
[T][X] Finish CS2103T assignment
```

## Unmarking a task: `unmark`

Unmarks a task to not done.

Format: `unmark INDEX`

- The index is the index of the task you want to unmark in the list
- The index must be valid

Example:  
Suppose you have one todo at index 1 that is marked as done. We can unmark it by

`unmark 1`

Expected output:

```
OK, I've marked this task as not done yet:  
[T][] Finish CS2103T assignment 
```

## Checking tasks to be due: `check`

Checks what tasks are to be due on a specific date.

Format: `check DUE_DATE`

- The due date must be in correct format `yyyy-mm-dd`

Example:
Suppose you have no task due on 2022-01-01, and you entered the command:

`check 2022-01-01`

Expected output:

```
You have no task due on 01 01 22
```

Finding tasks: `find`  
Finds the tasks that matched the description

Format: `find DESCRIPTION`

- Tasks that consist of DESCRIPTION will be matched
- It will match all types of tasks in the list

Example:  
Suppose you have a todo that starts with CS, and you can look it up by:

`find CS`

Expected output:

```
Here are the matching tasks in your list:   
1.[T][] Finish CS2103T assignment
```  

## Exiting program: `bye`

Exits the program

Format: `bye`

- It takes several seconds for the program to exit
- It will save all the tasks in the task list
- Wait for the program to exit naturally, else the tasks might not be saved

example: `bye`

Expected output:

```
Bye. Hope to see you again soon!
WindBro left the chat room
```

## FAQ

**Q**: How do I transfer data to another Computer?  
**A**: Install the app in the other computer, and then perform start and exit. It would crete a data file,
then, paste the content of your original data file to the data file in the new computer. 


