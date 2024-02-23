# OAK-Dex User Guide

![Ui.png](Ui.png)

Welcome to OAK-Dex!

Have you ever forgotten tasks that you had to do? Missed the submission deadline for your assignment? Misremembered the date of an Event you were looking forward to and missed it?

Well, fret no longer! Oak-Dex is your all-in-one solution to all your problems! (Well, not *all* of them, but you get the point)

Oak-Dex allows you to efficiently organise your schedule, and stay on top of all your important deadlines and events. Oak-Dex takes over the mental burden of remembering everything that you wished you remembered, but never did!

# Working with Oak-Dex

This section introduces how **you** can work with Oak-Dex! 

Note that 'Tasks' refer to Todos, Events and Deadlines.

## Adding Tasks

### Todos

Adds a new Todo item to Oak-Dex

Syntax: `todo <name>`

**Example**

Input: `todo CS2103 Week 6 Quiz`

```
Added new Todo: CS2103 Week 6 Quiz
```

### Events

Adds a new Event item to Oak-Dex

Syntax: `event <name> /from <from-date> @ <from-time> /to <to-date> @ <to-time>`

Note that the formats for Date and Time are:
- `YYYY-MM-DD`: `from-date` and `to-date`
- `HH:mm`: `from-time` and `to-time`

Example: `event lecture /from 2024-12-01 @ 10:00 /to 2024-12-01 @ 12:00`

Adds a new Event Item, called 'Lecture' which occurs from 10am (`10:00`) to 12pm (`12:00`) on 1st December 2024 (`2024-12-01`).

```
Added new Event: lecture occuring from 2024-12-01 @ 10:00 to 2024-12-01 @ 12:00
```

### Deadlines

Adds a new Deadline item to Oak-Dex

Syntax: `event <name> /by <by-date> @ <by-time>`

Note that the formats for Date and Time are:
- `YYYY-MM-DD`: `by-date`
- `HH:mm`: `by-time`


Example: `deadline CS2103 Assignment /by 2024-02-23 @ 23:59`

Adds a new Deadline Item, called 'CS2103 Assignment', which is due by 23rd February 2024 (`2024-03-23`), 2359 (`23:59`)

```
Added new Deadline: CS2103 Assignment with Due Date: 2024-02-23 @ 23:59
```
## Listing Tasks

Lists all tasks currently stored in Oak-Dex, regardless of if they are marked as completed.

Syntax: `list`

Example Output:
```
1. [T][ ] CS2103 Week 6 Quiz
2. [E][ ] lecture (from: 01 12 2024 @ 1000 to: 01 12 2024 @ 1200)
3. [D][ ] CS2103 Assignment (by: 23 02 2024 @ 2359)
```

## Deleting Tasks

Deletes a Task (Event, Todo or Deadline) from Oak-Dex using the `taskId` as an identifier.
To find the appropriate `taskId` for the task you wish to delete, use the `list` command.
See [here](#listing-tasks-) for more information on the `list` command.

Syntax: `delete <taskId>`

Example: `delete 1`

Deletes the Task with the `taskId` 1. 

```
Are you giving up? Or is this task no longer needed?
Hmmmm.. I've deleted Task 1 for now.
But, I'll be watching you.
```

## Marking Tasks

Marks a Task (Event, Todo or Deadline) from Oak-Dex as completed, using the `taskId` as an identifier.
To find the appropriate `taskId` for the task you wish to mark, use the `list` command.
See [here](#listing-tasks-) for more information on the `list` command.

Syntax: `mark <taskId>`

Example: `mark 1`

Marks the Task with the `taskId` 1 as completed

```
Ok! I've marked Task 1 as completed!
```

## Unmarking Tasks

Unmarks a Task (Event, Todo or Deadline) from Oak-Dex as not completed, using the `taskId` as an identifier.
To find the appropriate `taskId` for the task you wish to unmark, use the `list` command.
See [here](#listing-tasks-) for more information on the `list` command.

Syntax: `unmark <taskId>`

Example: `unmark 1`

Unmarks the Task with the `taskId` 1 as completed, that is, removing the completed mark from the Task

```
Hmmm, were you teasing me?
Well, I've marked Task 1 as uncompleted,
But don't do this again, you hear me?
```

## Reminders

Lists all Events / Deadline Tasks for which the `to-datetime` and the `by-datetime` respectively is after the current System datetime.

Syntax: `reminder`

```
[E][ ] lecture (from: 01 12 2024 @ 1000 to: 01 12 2024 @ 1200)
[D][ ] CS2103 Assignment (by: 23 02 2024 @ 2359)
```

## Finding Tasks

Finds all Tasks matching the string passed in by the user, that is, the string passed in is a substring of the Task Name.

Syntax: `find <matching_string_value>`

Example: `find CS2103`

```
1. [T][ ] CS2103 Week 6 Quiz
3. [D][ ] CS2103 Assignment (by: 23 02 2024 @ 2359)
```
