# Plaudern User Guide

![Screenshot of Plaudern](Ui.png)

Plaudern is a task management application that allows users to record their upcoming tasks and update tasks' states. 
It is designed to be simple and easy to use, and is in the form of an interactive chatbot which makes the communication more interesting.

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest `plaudern.jar` from [here](https://github.com/Ella-e/ip/releases).
3. Copy the file to the folder you want to use as the home folder for Plaudern.
4. Run the file using the command `java -jar plaudern.jar` in the terminal.


## Features

### Adding a todo task: `todo <task description>`
Todo tasks are tasks without date to finish or start.<br>
Examples: 
```
todo read CS2103T textbook
```
Sample output:
```
Got it. I've added this task:
[T][ ] read CS2103T textbook
Now you have 1 tasks in the list.
```

### Adding a deadline task: `deadline <task description> /by <yyyy-mm-dd>`
Deadline tasks are tasks with a date to finish.<br>
Examples: 
```
deadline submit report /by 2023-03-10
```
Sample output:
```
Got it. I've added this task:
[D][ ] submit report (by: Mar 10 2023)
Now you have 2 tasks in the list.
```

### Adding an event task: `event <task description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>`
Event tasks are tasks with a date to start and end.<br>
Examples:
```
event finish math homework /from 2022-11-13 /to 2022-11-14
```
Sample output:
```
Got it. I've added this task:
[E][ ] finish math homework (from: Nov 13 2022 to: Nov 14 2022)
Now you have 3 tasks in the list.
```

### Marking a task as done: `mark <task number>`
Mark a task as done.<br>
Examples:
```
mark 1
```
Sample output:
```
Nice! I've marked this task as done:
[T][X] read CS2103T textbook
```

### Unmarking a task as done: `unmark <task number>`
Unmark a task as done.<br>
Examples:
```
unmark 1
```
Sample output:
```
Noted. I've marked this task as undone:
[T][ ] read CS2103T textbook
```

### Deleting a task: `delete <task number>`
Delete a task from the task list.<br>
Examples:
```
delete 1
```
Sample output:
```
Noted. I've removed this task:
[T][ ] read CS2103T textbook
Now you have 2 tasks in the list.
```

### Finding tasks by keyword: `find <keyword>`
Find tasks by keyword in the task content.<br>
Examples:
```
find homework
```
Sample output:
```
Here are the matching tasks in your list:
1. [E][ ] finish math homework (from: Nov 13 2022 to: Nov 14 2022)
```

### Listing all tasks: `list`
List all tasks in the task list.<br>
Sample output:
```
Here are the tasks in your list:
1. [T][ ] read CS2103T textbook
2. [D][ ] submit report (by: Mar 10 2023)
3. [E][ ] finish math homework (from: Nov 13 2022 to: Nov 14 2022)
```

### Exiting the application: `bye`
Exit the application after 2 seconds.<br>
Sample output:
```
Bye. Hope to see you again soon!
```

### Displaying most recent tasks upon entering the application
When user enters the application, the 2 unmarked tasks with the most recent due will be displayed.<br>
Sample output:
```
==Reminder==
Those tasks are due next:
1. [D][ ] submit report (by: Mar 10 2023)
2. [E][ ] finish math homework (from: Nov 13 2022 to: Nov 14 2022)
```

### Saving the data
The data will be saved in the hard disk in the form of a txt file automatically after any command that changes the data. There is no need to save manually.

### Loading the data
The data will be loaded from the txt file in the hard disk when the application starts up.
>[!NOTE]
> If it is the first time the application starts up, the application will automatically create the data file for storage.

>[!CAUTION]
> DO NOT CHANGE THE DATA FILE MANUALLY: If the format of data in the data file is invalid, Plaudern will not be able to load the data and will require the user to delete the data file manually.

## Additional notes about commands
- Usage of the special character `|` is strictly prohibited in the command.
- Commands are **case-insensitive**.
- Words in `<angle brackets>` are the parameters to be supplied by the user.
- `<task number>` is **1-based integer index** of the task in the task list.
- Date format: `yyyy-mm-dd`
- `mark` and `unmark` commands can be done multiple times on the same task without causing any error.

>[!WARNING]
> No preceding space is allowed in the command. For example, ` todo read book` is invalid.
> No preceding space is allowed before the date in the command. For example, `deadline submit report /by  2023-03-10` is invalid.