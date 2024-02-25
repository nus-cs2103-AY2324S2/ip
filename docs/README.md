# Pengu Chatbot User Guide

This is Pengu, Pengu is a Java-based **desktop app for managing Tasks** optimised 
for use via a **Command Line Interface (CLI)** while still having the benefits of a
**Graphical User Interface (GUI)**. Pengu is a one-stop for all your task management needs.

## Quick Start

1. Ensure that you have `Java 11` or above installed on your computer
2. Download the latest version of `Pengu.jar` from [here](https://github.com/nigellim27022001/ip/releases).
3. Place the `Pengu.jar` file into the directory of your choice.
4. Open your command prompt, `cd` and locate the `Pengu.jar` file.
5. Using the following command `java -jar Pengu.jar` to run the application.
6. Begin typing in the command in the chatbox and press enter of the button to execute.

## Features
> [!NOTE]
> 
> - Words in `UPPER_CASE` are the parameters to be supplied by the user.
> e.g. in `deadline TASK_NAME /by BY_DATE`, `TASK_NAME` and `BY_DATE` can be used as
> `deadline CS2103T ip /by 2024-02-22 1800`
> - Parameters cannot be in any order other than the ones specified.
> - Extraneous parameters for commands that do not take in parameters (such as `list`) 
> will be ignored.


### Adding a ToDo task: `todo`

Adds a ToDo task to the existing TaskList. ToDo tasks are the simplest form of tasks
and only contains a `description`.

Format: `todo TASK_DESCRIPTION`

Example: `todo CS2103T Assignment`

```
Pengu has added this task:
[T][] CS2103T Assignment
Get back to work! you have 1 task in the list.
```

### Adding a Deadline Task: `deadline`

Adds a Deadline task to the existing TaskList. Deadline tasks are tasks that contains
a `description` and a `by date`.

Format: `deadline TASK_DESCRIPTION /by BY_DATE`

Example: `deadline CS2103T IP /by 2024-02-20 1800`

```
Pengu has added this task:
[D][] CS2103T IP (by: Feb-20-2024 1800)
Get back to work! you have 1 task in the list.
```

### Adding a Event Task: `event`

Adds a Event task to the existing TaskList. Event tasks are tasks that contains
a `description` , a `from date` and a `to date`.

Format: `event TASK_DESCRIPTION /from FROM_DATE /to TO_DATE`

Example: `event CS2103T IP /from 2024-02-20 1800 /to 2024-02-22 1800`

```
Pengu has added this task:
[E][] CS2103T IP (from: Feb-20-2024 1800, to: Feb-22-2024 1800)
Get back to work! you have 1 task in the list.
```

### Listing out all tasks: `list`

Lists out all tasks currently in the TaskList.

Format: `list`

Example: `list`

```
Pengu has listed your current tasks:
1.[D][] CS2103T IP (by: Feb-20-2024 1800)
2.[T][] CS2103T Assignment
```

### Marking a task: `mark`

Marks an unmarked task on the TaskList as done.

Format: `mark TASK_NUMBER`

Example: `mark 1`

```
Good Job! Pengu has marked this task as done:
[D][X] CS2103T IP (by: Feb-20-2024 1800)
```

### Unmarking a Task: `unmark`

Unmark a marked task on the TaskList as not done.

Format: `unmark TASK_NUMBER`

Example: `unmark 1`

```
Pengu has unmarked this task as not done:
[D][] CS2103T IP (by: Feb-20-2024 1800)
```

### Finding a Task: `find`

Finds a task in the TaskList, given a specific keyword.

Format: `find KEY_WORD`

Example: `find assignment`

```
*Honk! Pengu has found the following tasks containing the assignment keyword:
1.[T][]CS2103T Assignment
```

### Deleting a Task: `delete`

Deletes a tasks in the TaskList.

Format: `delete TASK_NUMBER`

Example: `delete 1`

```
*Honk* Pengu has removed the following task:
[D][] CS2103T IP (by: Feb-20-2024 1800)
```

### Exiting the program: `bye`

Exits the program

Format: `bye`

### Saving the data

Pengu stores the previously added tasks in the tasklist to the hard disk 
automatically after any command that changes the tasklist state. There is 
no need to save manually.

### Editing the data file

Pengu task data are saved automatically as a txt file `[JAR file location]/data/tasks.txt`
. Advanced users are welcome to update data directly by editing that data file.