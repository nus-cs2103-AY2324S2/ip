# Cicada User Guide

![Ui.png](Ui.png)

Cicada is a chatbot for managing tasks, optimized for use via a Command Line Interface (CLI) 
while still having the benefits of a Graphical User Interface (GUI).



## Adding a todo task: `todo`
Adds a todo task to the task list.

Format: `todo <task description>`

Examples:

- `todo borrow book`


## Adding a deadline task" `deadline`
Adds a deadline task to the task list.

Format: `deadline <task description> by <deadline date>`

Examples:

- `deadline return book by 2024-02-02 1800`


## Adding a event task" `event`
Adds a event task to the task list.

Format: `event <task description> from <start time> to <end time>`

Examples:

- `event meeting from 2024-02-02 1800 to 2024-02-02 2000`

## Listing all tasks: `list`
Shows a list of all tasks in the task list.

Format: `list`


## Mark a task as done: `mark`
Marks a task a done in the task list.

Format: `mark <index>`

Examples:

- `mark 1`


## Unmark a task as done: `unmark`
Unmarks a task a done in the task list.

Format: `unmark <index>`

Examples:

- `unmark 1`


## Delete a task: `delete`
Deletes a task from the task list.

Format: `delete <index>`

Examples:

- `delete 1`

## Find tasks: `find`
Search for tasks with a keyword.

Format: `find <keyword>`

Examples:

- `find book`


## View schedules: `view`
Views the schedule of a given date.

Format: `view <date>`

Examples:

- `view 2024-02-02`

## Exit the program: `bye`
Exits the program

Format: `bye`


## Saving the data
Cicada will automatically save the tasks when there is changes to the task list.