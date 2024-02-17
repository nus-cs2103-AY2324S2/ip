# User Guide

## Introduction

Welcome to Duking Time! Duking Time is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI). If you can type fast, Duking Time can help you manage your tasks faster than traditional GUI apps.

## Features

### Add tasks

You can add 3 types of tasks: `todo`, `deadline`, and `event`.
- `todo` tasks are simple tasks with no date or time.
- `deadline` tasks are tasks that need to be done before a specific date and time.
- `event` tasks are tasks that occur at a specific date and time.

### Mark and unmark tasks as done

You can mark and unmark tasks as done when you have completed them.

### List tasks

You can list all the tasks you have added.

### Delete tasks

You can delete tasks from the task list.

### Get help message

You can get help message for each command.

## Usage

### `todo` - Add a todo task

Add a todo task to the task list.

To add a `todo` task, run the following command:
```
todo <description>
```

Example of usage:

`todo read book`

Expected outcome:

The todo task is added to the task list.

```
Got it. I've added this task:
[T][✘] read book
Now you have 1 task in the list.
```

### `deadline` - Add a deadline task

Add a deadline task to the task list.

To add a `deadline` task, run the following command:
```
deadline <description> /by <deadline>
```

Example of usage:

`deadline return book /by 2021-09-30`

Expected outcome:

The deadline task is added to the task list.

```
Got it. I've added this task:
[D][✘] return book (by: 2021-09-30)
Now you have 2 tasks in the list.
```

### `event` - Add an event task

Add an event task to the task list.

To add an `event` task, run the following command:
```
event <description> /from <start date> /to <end date>
```

Example of usage:

`event project meeting /from 2021-10-01 /to 2021-10-01`

Expected outcome:

The event task is added to the task list.

```
Got it. I've added this task:
[E][✘] project meeting (from: 2021-10-01 to: 2021-10-01)
Now you have 3 tasks in the list.
```

### `mark` - Mark a task as done

Mark a task as done when you have completed it.

To mark a task as done, run the following command:
```
mark <task number>
```

Example of usage:

`mark 1`

Expected outcome:

The task is marked as done.

```
Nice! I've marked this task as done:
[T][✓] read book
```

### `unmark` - Unmark a task as done

Unmark a task as done when you have not completed it.

To unmark a task as done, run the following command:
```
unmark <task number>
```

Example of usage:

`unmark 1`

Expected outcome:

```
The task is unmarked as done.
```

### `list` - List all tasks

List all the tasks you have added.

To list tasks, run the following command:
```
list
```

Example of usage:

`list`

Expected outcome:

The tasks are listed.

```
Here are the tasks in your list:
1. [T][✘] read book
2. [D][✘] return book (by: 30 Sep 2021 06:00 PM)
3. [E][✘] project meeting (from: 01 Oct 2021 to: 01 Oct 2021)
```

### `delete` - Delete a task

Delete a task from the task list.

To delete a task, run the following command:
```
delete <task number>
```

Example of usage:

`delete 2`

Expected outcome:

The task is deleted from the task list.

```
Noted. I've removed this task:
[D][✘] return book (by: 30 Sep 2021 06:00 PM)
Now you have 2 tasks in the list.
```

### `help` - Show help message

Show the help message.

To show the help message, run the following command:
```
help <optional command>
```

Example of usage:

`help delete`

Expected outcome:

The help message is shown.

```
Delete a task from the task list.
Format: delete <task number>
Example: delete 2
```

