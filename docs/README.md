# Earl User Guide

Earl is a **desktop chat application to track tasks, optimised for 
use via a Command Line Interface (CLI)** while still having the
benefits of a Graphical User Interface (GUI). If you can type fast,
Earl can get your task management done faster than traditional GUI
applications.

![](Ui.png)

## Quick Reference

| Command    | Arguments                                       |
|------------|-------------------------------------------------|
| `list`     | None                                            |
| `todo`     | `<task name>`                                   |
| `deadline` | `<task name> /by <date time>`                   |
| `event`    | `<task name> /from <date time> /by <date time>` |
| `find`     | `<pattern>`                                     |
| `mark`     | `<indices> [<indices>, ...]`                    |
| `unmark`   | `<indices> [<indices>, ...]`                    |
| `delete`   | `<indices> [<indices>, ...]`                    |
| `bye`      | None                                            |

## Features

### Adding ToDos: `todo`
Adds a todo to the list of tasks.

Format: `todo <task name>`

Example: `todo homework`, `todo wash clothes`

*Notes*
+ `<task name>` cannot be empty

### Adding Deadlines: `deadline`

Adds a deadline to the list of tasks.

Format: `deadline <task name> /by <date time>`

Example: `deadline project submission /by 01/01/2024 2359`

*Notes*
+ `<task name>` cannot be empty.
+ `<date time>` must be of the format `dd/mm/yyyy hhmm`

### Adding Events: `event`

Adds an event to the list of tasks.

Format: `event <task name> /from <date time> /to <date time>`

Example: `event exam /from 01/01/2024 1200 /to 01/01/2024 1400`

*Notes*
+ `<task name>` cannot be empty.
+ `<date time>` must be of the format `dd/mm/yyyy hhmm`

### Listing Tasks: `list`

Displays all tasks currently tracked.

Format: `list`

### Finding Tasks: `find`

Displays all tasks with details matching a pattern.

Format: `find <pattern>`

Example: `find wash clothes`

### Marking Tasks: `mark`

Marks a task as complete.

Format: `mark <indice> [<indices>, ...] `

Example: `mark 1 3-5`

*Notes*
+ Arguments are space separated single indices or ranges
+ Ranges must be of the form `<index>-<index>`
+ Tasks already marked as done are unchanged
+ Indices outside the range of valid indices are ignored
+ There must be at least 1 valid index

### Unmarking Tasks: `unmark`

Marks a task as incomplete.

Format: `unmark <indices> [<indices>, ...]`

Example: `unmark 2-4 6 8-11`

*Notes*
+ Arguments are space separated single indices or ranges
+ Ranges must be of the form `<index>-<index>`
+ Tasks already marked as undone are unchanged
+ Indices outside the range of valid indices are ignored
+ There must be at least 1 valid index

### Deleting Tasks: `delete`

Removes a task from the list.

Format: `delete <indices> [<indices>, ...]`

Example: `delete 3 5 9-11`

*Notes*
+ Arguments are space separated single indices or ranges
+ Ranges must be of the form `<index>-<index>`
+ Indices outside the range of valid indices are ignored
+ There must be at least 1 valid index

### Closing the Application: `bye`

Saves the tasks and closes the application.

Format: `bye`

*Notes*
+ This is equivalent to pressing the cross on the GUI