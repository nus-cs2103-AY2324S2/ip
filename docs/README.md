# Arc User Guide

![Arc GUI](./Ui.png)

_Redefining task management_

---

# Installation

1. Ensure that Java 11 is installed. You may download Oracle's jdk 11 from [here](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html).
2. Download the latest version of Arc from [here](https://github.com/jjchee77/ip/releases). As of this writing, the latest version is 3.0.0
3. Double-click on the jar file to execute Arc.
4. If double-clicking does not work, launch a terminal, navigate to the directory containing Arc and execute `java -jar Arc-<ver>.jar`

---

# Features

## Adding a todo: `todo`
Adds a new todo to the task list.

Format: 

`todo <description>`

Arguments:

`description`: The description of the todo.

> Example: `todo Make dinner reservation`

## Adding a deadline: `deadline`
Adds a deadline to the task list.

Format:

`deadline <description> /by YYYY-MM-DD`

Arguments:

`description`: The description of the deadline.

`/by`: The date of the deadline.

> Example: `deadline SEP deliverables /by 2024-02-23`

## Adding an event: `event`
Adds an event to the task list.

Format:

`event <description> /from YYYY-MM-DD /to YYYY-MM-DD`

Arguments:

`description`: The description of the event.

`/from`: The start date of the event.

`/to`: The end date of the event.

> Example: `event Richard's birthday party /from 2024-02-24 /to 2024-02-25`

## Listing the task list: `list`
Lists the entries in the task list.

Format: `list`

> Example: `list`

## Finding a task: `find`
Finds a matching task based on description.

Format:

`find <keyword>`

Arguments:

`keyword`: The keyword to search for.

> Example: `find Wash`

## Sorting deadlines in the task list: `sort-deadlines`
Sorts deadlines in the task list in place by chronological order.

Format: `sort-deadlines`

> Example: `sort-deadlines`

## Marking a task as completed: `mark`
Marks a task as completed.

Format:

`mark <task_number>`

Arguments:

`task_number`: The task number as displayed by the `list` command.

> Example: `mark 1`

## Marking a task as incomplete: `unmark`
Marks a task as incomplete.

Format:

`unmark <task_number>`

Arguments:

`task_number`: The task number as displayed by the `list` command.

> Example: `unmark 1`

## Removing a task from the task list: `delete`
Removes a task from the task list.

Format:

`delete <task_number>`

Arguments:

`task_number`: The task number as displayed by the `list` command.

> Example: `delete 1`

## Exiting Arc: `bye`
Terminates the chat-bot.

Format: `bye`

> Example: `bye`

## Task list storage
Tasks are automatically saved in `./data/arc.tasks`