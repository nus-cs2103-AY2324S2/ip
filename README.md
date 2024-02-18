# shuheng.bot

> Shu Heng is a nerd - [Haters](https://www.youtube.com/watch?v=562ngVq9EQM)

This is a project template for a greenfield Java project. 
It's named after the *top student* in NUS CS (~~Allegedly~~)! :bowtie:

Why use `shuheng`?

* **Free!**
* **Easy** to learn!
* **Easy** to use too!

## How do I run it?

1. Download the jar file!
2. Run it!
3. Add your tasks...
4. And let `shuheng` handle everything else!

```bash
java --jar shuheng.jar
```

## Features

### Add Tasks

The following commands allow you to give shuheng tasks to track!
Each task has a tagged `PRIORITY`. This ranges from 1-3, with 1 
being the highest priority and 3 being the lowest!

For tasks that require the notion of time, such as deadlines and events, shuheng takes in inputs
in a `YYYY-MM-DD HHMM` format!

#### Todo

Todos refer to tasks that do not have a fixed deadline. The `todo` command lets you add a Todo!
Format: `todo DESCRIPTION /priority PRIORITY`

Some examples:

- `todo Watch TV /priority 3`
- `todo Wash the dishes /priority 1`

#### Deadline

Deadlines are tasks with - you guessed it - deadlines! The `deadline` command lets you add a 
deadline. Format: `deadline DESCRIPTION /by TIME /priority PRIORITY`

Some examples:

- `deadline CS2103T assignments /by 2024-02-18 1800 /priority 1`

#### Event

Events are tasks that have a fixed start and end time. The `event` command lets you add a event.
Format: `deadline DESCRIPTION /from TIME (Start) /to TIME (End) /priority PRIORITY`

Some examples:

- `deadline CS2103T lecture /from 2024-02-18 1600 /to 2024-02-18 1800 /priority 1`

### List tasks

Shows all the tasks being tracked by shuheng, each task in the list is associated with a `INDEX`.

### Mark tasks

This allows you to manipulate the completion status of a task. The status of all your tracked task can be 
found with the `list` function! Format: `list`.

#### Mark

To set a task as complete. Format: `mark INDEX`.

#### Unmark

To set a task as incomplete. Format: `unmark INDEX`.

### Delete Tasks

This removes a task at a specific index. Format: `delete INDEX`.

### Find Tasks

This allows you to search for a task based on a `PHRASE`. This phrase would return you tasks with 
descriptions that contain `PHRASE` as a substring. Format: `find PHRASE`.

Some examples:

- `find do dishes`: This would return tasks with "do dishes" as part of its description.