# Mona User Guide

// Product screenshot goes here

// Product intro goes here

## Adding Tasks

Mona is capable of tracking tasks for you!
For tasks that are time-sensitive, such as deadlines and events, Mona takes them in a `dd-MM-YYYY HHmm` format

### Todo
A todo is the simplest type of task that Mona can track, consisting of just a description

Format: `todo DESCRIPTION`

Example usage:
- `todo Watch CS2103T Brief`
- `todo Complete CS2103T Quiz`

Expected Outcome: Mona adds the todo task to your list of tasks.

### Deadline
A deadline is a task that has an end date and time (a deadline so to speak...)

Format: `deadline /by TIME`

Example usage:
- `deadline Watch CS2103T Brief /by 21/02/2024 1200`
- `deadline Submit CS2103T /by 23/02/2024 2359`

Expected Outcome: Mona adds the deadline task to your list of tasks.

### Event
An event is a task with both a start and end date.

Format: `event /from TIME /to TIME`

Example usage:
- `event CS2103T tP Meeting /from 19/02/2024 2100 /to 19/02/2024 2200`
- `event CS2103T Tutorial /from 22/02/2024 1600 /to 22/02/2024 1700`

Expected Outcome: Mona adds the event task to your list of tasks.
## Mark Tasks
Mona can update the completion status of your tasks!

### Mark
Sets a task as completed

Format: `mark INDEX`

Example usage:
- `mark 1`

Expected Outcome: Mona sets the task positioned at that index in the list of tasks as completed.

### Unmark
Sets a task as not completed

Format: `unmark INDEX`

Example usage:
- `unmark 1`

Expected Outcome: Mona sets the task positioned at that index in the list of tasks as not completed.

## Delete Tasks
Deletes a task from the list of tasks.

Format: `delete INDEX`

Example usage:
- `delete 1`

Expected Outcome: Mona deletes the task positioned at that index in the list of tasks.

## Find Tasks
Finds all tasks from the list that contain the specified wording.

Format: `find WORDING`

Example usage:
- `find CS2103T`

Expected Outcome: Mona lists all tasks in her list with descriptions containing the wording as a substring.

## Update Tasks
Updates the details of a task 
>[!NOTE]
> This feature only updates details of the task with the task type remaining unchanged.

Format: `update INDEX /new NEW_DETAILS`

Example usage:
- `update 1 /new CS2103T tP Meeting /from 20/02/2024 1200 /to 20/02/2024 1300`

Expected Outcome: Mona updates the task positioned at that index with the new details.