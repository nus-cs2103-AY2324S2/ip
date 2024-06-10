# RoeBot User Guide

![Alt text](Ui.png)

Note: There are three types of tasks: Todo, Deadline and Event.

## Features

### Adding a todo: `todo`

Creates a todo. Duplicate todo names are not allowed.

Format: `todo [todo name]`

Example:
* `todo homework`

### Adding a deadline: `deadline`

Creates a deadline with a given datetime any format specified. For instance, dd/mm/yyyy hhmm. Duplicate deadline names are not allowed.

Format: `deadline [deadline name] /by [datetime]`

Example:
* `deadline homework /by 2/4/2024 2359`

### Adding an event: `event`

Creates an event with the given start and end time. Duplicate event names are not allowed.

Format: `event [event name] /from [datetime] /to [datetime]`

Example:
* `event career fair /from 1/2/2024 1100 /to 1/2/2024 1400`

### Listing all events: `list`

Lists all events.

Format: `list`

### Marking task as done: `mark`

Format: `mark INDEX`

* Marks the task at the specified `INDEX` as done.
* The index refers to the index number shown in the task list.
* The index **must be a positive integer**.

### Marking task as undone: `unmark`

Format: `unmark INDEX`

* Marks the task at the specified `INDEX` as undone.
* The index refers to the index number shown in the task list.
* The index **must be a positive integer**.

### Deleting task: `delete`

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the task list.
* The index **must be a positive integer**.

### Finding tasks: `find`

Finds task(s) whose names match the given pattern, this is not case-sensitive.

Format: `find [pattern]`

Examples:
* `find assignment`
* `find 3 Sep 2023`

### Exiting the program: `bye`

Exits the program.

Format: `bye`

### Saving the data

Task data are saved locally after any data is modified. There is no need to save manually.

