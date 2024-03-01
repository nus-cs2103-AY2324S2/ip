# User Guide
## Introduction
Nihao is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI). If you can type fast, 
Nihao can help you manage your tasks faster than traditional Graphical User Interface (GUI) apps. Currently, Nihao 
supports three types of tasks: `todo`, `deadline` and `event`. Nihao also has a GUI which displays your queries and 
its responses in a dialogue style.

## Features 

### List tasks
List all tasks in the task list.

### Add a task
Add a task of the type `todo`, `deadline` or `event` to the task list.

### Delete a task
Delete a task at a specified index from the task list.

### Mark a task as done/undone
Mark a task at a specified index as done/undone.

### Find tasks
Find tasks in the task list that contain a specified keyword.

## Usage

### `list` - List tasks

Displays all tasks in the task list.

Format: `list`

&nbsp;

### `todo` - Add a todo task

Adds a todo task to the task list.

Format: `todo DESCRIPTION`

Example of usage:

`todo read book` adds a todo task with the description "read book".

&nbsp;

### `deadline` - Add a deadline task

Adds a deadline task to the task list.

Format: `deadline DESCRIPTION /by DATETIME`

Example of usage:

`deadline return book /by 09/30/2023 2000` adds a deadline task with the description "return book" and the deadline 
30 September 2023 2000 hrs.

&nbsp;

### `event` - Add an event task
Adds an event task to the task list.

Format: `event DESCRIPTION /from DATETIME /to DATETIME`

Example of usage:

`event project meeting /from 09/30/2023 1400 /to 09/30/2023 1600` adds an event task with the description "project 
meeting" that starts at 30 September 2023 1400 hrs and ends at 30 September 2023 1600 hrs.

&nbsp;

### `delete` - Delete a task

Deletes a task at a specified index from the task list.

Format: `delete INDEX` or `delete all`

Example of usage:

`delete 2` deletes the task at index 2 from the task list.

`delete all` deletes all tasks from the task list.

&nbsp;

### `mark` - Mark a task as done

Marks a task at a specified index as done.

Format: `mark INDEX`

Example of usage:

`mark 3` marks the task at index 3 as done.

&nbsp;

### `unmark` - Mark a task as undone

Marks a task at a specified index as undone.

Format: `unmark INDEX`

Example of usage:

`unmark 3` marks the task at index 3 as undone.

&nbsp;

### `find` - Find tasks

Finds tasks in the task list that contain a specified keyword.

Format: `find KEYWORD`

Example of usage:

`find book` finds all tasks in the task list that contain the keyword "book".

&nbsp;

### `bye` - Exit the program

Exits the program.

Format: `bye`

> Note: `bye` also saves any changes made to the task list to the local storage. Exiting the program by simply 
> closing the window will cause any changes made to the task list to be lost.