# Buddy User Guide

![Screenshot of Buddy](/Ui.png)

Buddy is an **app for managing your tasks, optimized for use via a Command Line Interface** (CLI). If you need help
remembering all the things you need to do, **_Buddy will be your friend!_**
--- 
## Quick Start
1. Ensure you have `Java 11` or above installed in you computer.
2. Download the latest version of Buddy
3. Copy the `buddy.jar` file into a directory of your choosing.
4. Open the command terminal, `cd` into the directory you put the jar file in, and
use `java -jar buddy.jar` to run the application.
5. Type a command into the text field at the bottom of the GUI and press Enter or
the Send button to execute it.
6. Refer to the [Features](#features) below for details of each command.
--- 
## Features

### Adding a todo task: `todo`
Adds a todo task into the task list.

Format: `todo TASK`

Examples:
* `todo Homework`
* `todo Chores`

### Adding a deadline task: `deadline`
Adds a task with a deadline into the task list.

Format: `deadline TASK /by DATETIME`

Examples:
* `deadline Assignment 1 /by 26/08/2024 2359`
* `deadline Book Return /by 25/12/2024 1300`

### Adding an event task: `event`
Adds an event with a start and end time into the task list.

Format: `event TASK /from DATETIME /to DATETIME`

Examples:
* `deadline Meeting /from 01/01/2024 1000 /to 01/01/2024 1200`
* `deadline Holiday /from 20/06/2024 0900 /to 27/06/2024 1200`

### Listing all tasks: `list`
Shows a list of all tasks.

Format: `list`

### Marking a task: `mark`
Marks a task as done.

Format: `mark TASK_NUMBER`

Examples:
* `mark 1`

### Unmarking a task: `unmark`
Marks a task as done not done.

Format: `unmark TASK_NUMBER`

Examples:
* `unmark 5`

### Removing a task: `delete`
Removes the task from the task list.

Format: `delete TASK_NUMBER`

Examples:
* `delete 2`

### Locating a task: `find`
Finds a task which contains the given keyword

Format: `find KEYWORD`

Examples:
* `find Homework`
* `find Meet`

### Edit a task: `edit`
Edits an existing task entry.

Format: `edit TASK_NUMBER [/task TASK] [/by DATETIME] [/from DATETIME] [/to DATETIME]`

Examples:
* `edit 1 /task Club Practice`
* `edit 3 /by 25/04/2024 1630`