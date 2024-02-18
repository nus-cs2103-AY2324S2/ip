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

Expected Outcome: The Todo task gets added to shuheng.

#### Deadline

Deadlines are tasks with - you guessed it - deadlines! The `deadline` command lets you add a
deadline. Format: `deadline DESCRIPTION /by TIME /priority PRIORITY`

Some examples:

- `deadline CS2103T assignments /by 2024-02-18 1800 /priority 1`

Expected Outcome: The Deadline task gets added to shuheng.

#### Event

Events are tasks that have a fixed start and end time. The `event` command lets you add a event.
Format: `deadline DESCRIPTION /from TIME (Start) /to TIME (End) /priority PRIORITY`

Some examples:

- `event CS2103T lecture /from 2024-02-18 1600 /to 2024-02-18 1800 /priority 1`
  
Expected Outcome: The Event task gets added to shuheng.

### List tasks

Shows all the tasks being tracked by shuheng, each task in the list is associated with a `INDEX`.

Expected Outcome: shuheng returns all the task it is currently tracking as well as its status.

### Mark tasks

This allows you to manipulate the completion status of a task. The status of all your tracked task can be
found with the `list` function! Format: `list`.

#### Mark

To set a task as complete. Format: `mark INDEX`.

Expected Outcome: The task tagged to `INDEX` gets marked as complete.

#### Unmark

To set a task as incomplete. Format: `unmark INDEX`.

Expected Outcome: The task tagged to `INDEX` gets marked as incomplete.

### Delete Tasks

This removes a task at a specific index. Format: `delete INDEX`.

Expected Outcome: The task tagged to `INDEX` gets removed from shuheng.

### Find Tasks

This allows you to search for a task based on a `PHRASE`. Format: `find PHRASE`.

Some examples:

- `find do dishes`: This would return tasks with "do dishes" as part of its description.

Expected Outcome: The tasks being tracked by shuheng with
descriptions that contain `PHRASE` as a substring.
