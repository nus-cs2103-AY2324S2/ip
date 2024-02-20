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

### Adding a todo task: 'todo'
Description: todo tasks are tasks without date to finish or start.
Format: `todo <task description>`
Examples: 
```
todo read CS2103T textbook
```

### Adding a deadline task: 'deadline'
Description: deadline tasks are tasks with a date to finish.
Format: `deadline <task description> /by <yyyy-mm-dd>`
Examples: 
```
deadline submit report /by 2023-03-10
```

### Adding an event task: 'event'
Description: event tasks are tasks with a date to start and end.
Format: `event <task description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>`
Examples:
```
event finish math homework /from 2022-11-13 /to 2022-11-14
```

### Marking a task as done: 'mark'
Description: mark a task as done.
Format: `mark <task number>`
Examples:
```
mark 1
```

### Unmarking a task as done: 'unmark'
Description: unmark a task as done.
Format: `unmark <task number>`
Examples:
```
unmark 1
```

### Deleting a task: 'delete'
Description: delete a task from the task list.
Format: `delete <task number>`
Examples:
```
delete 1
```

### Finding tasks by keyword: 'find'
Description: find tasks by keyword in the task content.
Format: `find <keyword>`
Examples:
```
find homework
```

### Listing all tasks: 'list'
Description: list all tasks in the task list.
Format: `list`
Examples:
```
list

// Expected output
Here are the tasks in your list:
1. [T][ ] read CS2103T textbook
2. [D][ ] submit report (by: Mar 10 2023)
3. [E][ ] finish math homework (from: Nov 13 2022 to: Nov 14 2022)
```

### Exiting the application: 'bye'
Description: exit the application after 2 seconds.
Format: `bye`
Examples:
```
bye
```

### Displaying most recent tasks upon entering the application
Description: When user enters the application, the 2 unmarked tasks with the most recent due will be displayed.

### Saving the data
Description: The data will be saved in the hard disk in the form of a txt file automatically after any command that changes the data. There is no need to save manually.

### Loading the data
Description: The data will be loaded from the txt file in the hard disk when the application starts up.
>[!NOTE]
> If it is the first time the application starts up, the application will automatically create the data file for storage.

>[!CAUTION]
> DO NOT CHANGE THE DATA FILE MANUALLY: If the format of data in the data file is invalid, Plaudern will not be able to load the data and will require the user to delete the data file manually.

## Additional notes about commands
- Usage of the special character `|` is strictly prohibited in the command.
- Words in `<angle brackets>` are the parameters to be supplied by the user.
- `<task number>` is **1-based integer index** of the task in the task list.
- Date format: `yyyy-mm-dd`
- `mark` and `unmark` commands can be done multiple times on the same task without causing any error.