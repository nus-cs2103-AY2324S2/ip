# James User Guide

James Bot is a task planner designed to help you keep track of your todos, deadlines, and events, inspired by the Duke project. With a simple and intuitive command line interface, James makes managing your tasks easy and efficient.

![User Interface](../docs/Ui.png)

## Features

### 1. Add Tasks
You can add three types of tasks: Todos, Deadlines, and Events.

### **Todos:** Simple tasks without a specified due date or time.
```
todo [description]
```
Examples:
- ``` todo Read Book ``` will add the task "Read Book" to the tasklist.
- ``` todo Get a new shirt ``` will add the task "Get a new shirt" to the tasklist.

### **Deadlines:** Tasks that need to be done before a specific date/time.

Note that many different formats for dates and times are accepted including: only giving the date and only giving the time. 

In such cases, the date will be set to the current day and the time will be set to 0000.
```
deadline [description] /by [DD-MM-YYYY HHMM]
```
Examples:
- `deadline CS2103T IP /by 26-02-2024 2359` adds a task with a deadline for "CS2103 IP".
- `deadline Return the book /by 26-04-2024` adds a task with a deadline for "Return the book".

### **Events:** Tasks that start and end at specific times.

Note that many different formats for dates and times are accepted including: only giving the date and only giving the time.

In such cases, the date will be set to the current day and the time will be set to 0000.
```
event [description] /from [start time] /to [end time]
```

Examples: 
- `event John's Birthday Party /from 29-03-2024 1400 /to 29-03-2024 1800` adds the event of "John's birthday" to your task list.
- `event Meeting with Jack /from 1000 /to 1500` adds the event of "Meeting  with Jack" to your task list.


### 2. List Tasks
Display all your tasks.
```
list
```

### 3. Mark Tasks as Done
Mark a task as completed.
```
mark [task number]
```
-The task number refers to the task index shown in the displayed person list.

-The task number must be a positive integer 1, 2, 3…

Example:
- `mark 1` will mark the first task in your list as done.

### 4. Unmark Tasks
Mark a completed task as not done.
```
unmark [task number]
```
-The task number refers to the task index shown in the displayed person list.

-The task number must be a positive integer 1, 2, 3…

Example:
- `unmark 1` will unmark the first task in your list.

### 5. Delete Tasks
Remove a task from your list.
```
delete [task number]
```
-The task number refers to the task index shown in the displayed person list.

-The task number must be a positive integer 1, 2, 3…

Example:
- `delete 1` will remove the first task from your list.

### 6. Find Tasks
Search for tasks by description. The find command can be used to find tasks stored even by searching part of the task description.

The find command is also case insensitive to facilitate searching even more.
```
find [keyword]
```
Examples:
`find book` will display the tasks "Read Book" and "Return the book" from the earlier examples.

### 7. Sort Tasks
Sorts all tasks in chronological order.
```
sort
```
Note:
Todos will be sorted to the end of the list.

### 8. Exiting the Program
Quit the application.
```
bye
```

## Usage

## Handling Errors
James is designed to provide helpful error messages should you enter a command incorrectly. Pay attention to the command format and required information to ensure smooth operation.

## Storage
James Bot automatically saves your tasks in a file on your computer, so you can pick up right where you left off.

## Customization
Feel free to customize James Bot by modifying its source code to better suit your needs.

Enjoy managing your tasks with James Bot!