# Chatteroo User Guide

Chatteroo is a application that helps users to manage their tasks using a Graphical User Interface (GUI).

The user guide will showcase the different features of Chatteroo and how they can be used.

![Chatteroo](Ui.png)

## Features

### Add a todo task: `todo`

Adds a todo task to the task list.

FORMAT: `todo DESCRIPTION`

Example: `todo finish assignment`

The expected outcome is:

```
UnderstOOd! I've added this task:
[T][] finish assignment
Now you have 3 tasks in the list.
```

### Add a deadline task: `deadline`

Adds a deadline task to the task list.

FORMAT: `deadline DESCRIPTION /by DATE TIME`

Example: `deadline proposal /by 2024-03-12 2359`

The expected outcome is:

```
UnderstOOd! I've added this task:
[D][] proposal (by: Mar 12 2024 23:59)
Now you have 4 tasks in the list.
```

### Add a event task: `event`

Adds a event task to the task list.

FORMAT: `event DESCRIPTION /from DATE TIME /to DATE TIME`

Example: `event meeting /from 2024-01-04 1800 /to 2024-01-04 2000`

The expected outcome is:

```
UnderstOOd! I've added this task:
[E][] meeting (by: Jan 4 2024 18:00 to Jan 4 2024 20:00)
Now you have 5 tasks in the list.
```

### Mark a task as done: `mark`

Marks a task in the task list as done by using the task number.

FORMAT: `mark TASKNUMBER`

Example: `mark 1`

The expected outcome is:

```
BravOO mate! I've marked this task as done:
[T][X] finish assignment
```

### Mark a task as not done: `unmark`

Marks a task in the task list as not done by using the task number.

FORMAT: `unmark TASKNUMBER`

Example: `mark 1`

The expected outcome is:

```
OK mate, I've marked this task as not done yet:
[T][] finish assignment
```

### Delete a task: `delete`

Deletes a task in the task list by using the task number.

FORMAT: `delete TASKNUMBER`

Example: `delete 1`

The expected outcome is:

```
OOOK! I've removed this task:
[T][] finish assignment
Now you have 4 tasks in the list.
```

### List all tasks: `list`

Lists all the tasks in the task list.

FORMAT: `list`

Example: `list`

The expected outcome is:

```
Here are the tasks in your list:
1. [T][] finish assignment
2. [D][] proposal (by: Mar 12 2024 23:59)
3. [E][] meeting (by: Jan 4 2024 18:00 to Jan 4 2024 20:00)
```

### Find task using a key word: `find`

Finds a task in the task list using a key word.

FORMAT: `find KEYWORD`

Example: `find proposal`

The expected outcome is:

```
Here are the matching tasks in your list:
1. [D][] proposal (by: Mar 12 2024 23:59)
```

### Clear tasks marked as done: `done`

Clears all the tasks in the task list that are marked as done.

FORMAT: `done`

Example: `done`

The expected outcome is:

```
Good on ya! I've removed all the tasks that are done from the list.
```

### Exit the application: `bye`

Shows the exit message and closes the application.

FORMAT: `bye`

Example: `bye`

The expected outcome is:

```
Cheers mate! ChatterOO will catch ya later!
```

## Commands summary
`todo`

`deadline`

`event`

`mark`

`unmark`

`delete`

`list`

`find`

`done`

`bye`