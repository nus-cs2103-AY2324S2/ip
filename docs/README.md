# Homie User Guide

// Product screenshot goes here

Homie is a desktop app for managing your daily tasks such as todo, events and deadlines, optimized for use via a
Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast,
Homie can can help you keep track of your tasks faster than traditional GUI apps. Homie is also your homie, so don't be
afraid to treat him as your homie and get him now!

## Adding deadlines

Adds a deadline to the list of tasks.
Format: `deadline {NAME_OF_DEADLINE} /by {DD-MM-YYYY HHmm}` or `dead {NAME_OF_DEADLINE} /by {DD-MM-YYYY HHmm}`

Example: `deadline CS2103 Quiz 3 /by 24 02 2024 2359` or `dead CS2103 Quiz 3 /by 24 02 2024 2359`

Expected outcome:
```
_______________________________________ \nGot it. I've added this task\n [D][] CS2103 Quiz 3 (by: 02-24-2024 23:59)
\nNow you have 1 tasks in the list.\n_______________________________________
```

## Adding todo

Adds a todo to the list of tasks.
Format: `todo {NAME_OF_TODO}` or `t {NAME_OF_TODO}`

Example: `todo read book` or `t read book`

Expected outcome:
```
_______________________________________ \nGot it. I've added this task\n [T][] read book
\nNow you have 1 tasks in the list.\n_______________________________________
```

// Feature details
Empty description will raise an exception.
Error message: `todo desription cannot be empty!`


## Adding event

Adds an event to the list of tasks.
Format: `event {NAME_OF_EVENT} /from {DD-MM-YYYY HHmm} /to {DD-MM-YYYY HHmm}` or `e {NAME_OF_EVENT} /from
{DD-MM-YYYY HHmm} /to {DD-MM-YYYY HHmm}`

Example: `event CS2103 Briefing /from 24 02 2024 1600 /to 24 02 2024 1800` or `e CS2103 Briefing /from 24 02 2024
1600 /to 24 02 2024 1800`

Expected outcome:
```
_______________________________________ \nGot it. I've added this task\n [E][] CS2103 Briefing (from: 02-24-2024 16:00 
to: 02-24-2024 18:00)\nNow you have 1 tasks in the list.\n_______________________________________
```

## Marking task

Marks an existing task as done.
Format: `mark {INDEX}` or `m {INDEX}`

- Marks the task at the specified `INDEX` as done. The index refers to the index number shown in the displayed task
list. The index must be a positive integer 1, 2, 3...
- The `INDEX` must be within the range of 0 < INDEX < Total number of tasks in the list + 1
- Exception will be thrown when `INDEX is out of specified range`

Example: `mark 1` or `m 1`

Expected outcome:
```
_______________________________________ \nNice! I've marked this task as done:\n [E][X] CS2103 Briefing (from: 
02-24-2024 16:00 to: 02-24-2024 18:00)\n_______________________________________
```

## Listing all tasks

Lists all existing tasks in the task list.
Format: `list` or `l`

Example: `list` or `l`

Expected outcome:
```
_______________________________________ \nHere are the tasks in your list:\n 1.[D][] CS2103 Quiz 3 (by: 02-24-2024 23:59)
\nNow you have 1 tasks in the list.\n2. [T][] read book\n_______________________________________
```

## Unmarking task

Marks an existing task as not done.
Format: `unmark {INDEX}` or `unm 1`

- Marks the task at the specified `INDEX` as done. The index refers to the index number shown in the displayed task
  list. The index must be a positive integer 1, 2, 3...
- The `INDEX` must be within the range of 0 < INDEX < Total number of tasks in the list + 1
- Exception will be thrown when `INDEX is out of specified range`

Example: `unmark 1` or `unm 1`

Expected outcome:
```
_______________________________________ \nOk, I've marked this task as not done yet:\n [E][] CS2103 Briefing (from: 
02-24-2024 16:00 to: 02-24-2024 18:00)\n_______________________________________
```