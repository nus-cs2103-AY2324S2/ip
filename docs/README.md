# Plana User Guide

![Ui Showcase Image](Ui.png)

Feeling overloaded?  Meet Plana, your pocket-sized productivity pro!  Chat with Plana to schedule events, manage tasks, track goals, and get things done. ✨ Conquer your day and achieve more with Plana by your side!  Start chatting today!

## Features

> ### Notes about the command format:
> * Words in `UPPER_CASE` are the parameters to be supplied by the user.
>
>   e.g. in `deadline by/ DATE`, `DATE` is a parameter which can be used as `deadline /by 2024-08-02`.
> 
> 
> * Where the command requires a DATE, it can be in any of the following formats:
> 
>   `yyyy-MM-dd HH:mm`, `yyyy-MM-dd`, `yyyy-MM`
> 
> 
> * Extraneous parameters for commands that do not take in parameters (such as list and bye) will result in an error.
>
> 
> * If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.


### Adding Tasks

#### Simple: `todo`

Adds a simple todo task with no extra features.

Format: `todo NAME`

Example: `todo buy groceries`

#### Deadline: `deadline`

Adds a todo task with a specified deadline.

Format: `deadline /by DATE`

Examples:
- `deadline /by 2040-12-12`
- `deadline /by 2040-12-12 18:00`

#### Fixed Duration: `fixed`

Adds a todo task with a fixed duration.

Format: `fixed /for HOURS`

Examples:
- `fixed /for 2`

#### Event: `event`

Adds a todo task with a start and end time.

Format: `event /from DATE /to DATE`

Examples:
- `event /from 2040-12-09 /to 2040-12-12`
- `event /from 2040-12-12 16:00 /to 2040-12-12 18:00`

### Listing tasks: `list`

Shows a list of all tasks added to the todo list.

Format: `list`

### Completing tasks: `mark`

Marks a task as complete.

Format: `mark INDEX`

Example: `mark 1`


### Undoing marks: `unmark`

Marks a task as incomplete.

Format: `unmark INDEX`

Example: `unmark 1`

### Finding tasks: `find`

Finds tasks with the given query.

> `QUERY` is case sensitive

Format: `find QUERY`

Example: `find groceries`

### Deleting tasks: `delete`

Deletes a task from the todo list.

Format: `delete INDEX`

Example: `delete 1`


### Exiting: `bye`

Exits and closes Plana.

Format: `bye`
