# Bob User Guide

![Ui](Ui.png)

Welcome to Bob, your personal assistant chatbot designed to make your life easier and more organized. Bob is here to
help you keep track of various tasks effortlessly.

## Adding todos

Adds a todo to the task list.

Format: `todo TASK_DESCRIPTION`

Example: `todo submit ip`

A todo with description "submit ip" will be added to the task list.

```
added:
  [T][ ] submit ip
now you have 1 task(s)
```

## Adding deadlines

Adds a deadline to the task list.

Format: `deadline TASK_DESCRIPTION /by d/M/yyyy HHmm`

Example: `deadline submit ip /by 29/2/2024 2359`

A deadline with description "submit ip" that is due on 29th February 2024 at 11:59pm will be added to the task list.

```
added:
  [D][ ] submit ip (by: Feb 29 2024 2359)
now you have 2 task(s)
```

## Adding events

Adds an event to the task list.

Format: `deadline TASK_DESCRIPTION /from d/M/yyyy HHmm /to d/M/yyyy HHmm`

Example: `event interest group session /from 5/3/2024 2000 /to 5/3/2024 2200`

An event with description "interest group session" that starts on 29th February 2024 at 8pm and ends at 10pm will be
added to the task list.

```
added:
  [E][ ] interest group session (from: Mar 05 2024 2000 to: Mar 05 2024 2200)
now you have 3 task(s)
```

## Adding periods

Adds a period to the task list. A period is a task that needs to be done within a certain period.

Format: `period TASK_DESCRIPTION /start d/M/yyyy HHmm /end d/M/yyyy HHmm`

Example: `period collect certificate /start 15/1/2024 0000 /end 25/1/2024 2359`

An event with description "collect certificate" that starts on 15th January 2024 at 12am and ends on 25th January 2024
at 11:59pm will be added to the task list.

```
added:
  [P][ ] collect certificate (start: Jan 15 2024 2000 end: Jan 25 2024 2359)
now you have 4 task(s)
```

## Listing tasks

Shows a list of tasks in the task list. There are two ways the list can be filtered:

- Show only undone deadlines and periods that are due in a specified number of days.
- Show only undone events that are occurring on a specific date. 

Format: `list [/due_in DAYS] [/on d/M/yyyy]`

Example: `list`

The list of tasks in the task list will be shown.

```
list of tasks:
1. [T][ ] submit ip
2. [D][ ] submit ip (by: Feb 29 2024 2359)
3. [E][ ] interest group session (from: Mar 05 2024 2000 to: Mar 05 2024 2200)
4. [P][ ] collect certificate (start: Jan 15 2024 2000 end: Jan 25 2024 2359)
```

Example: `list /due_in 1`

The list of undone deadlines and periods that are due in 1 day will be shown.

```
list of tasks:
1. [D][ ] submit ip (by: Feb 29 2024 2359)
```

Example: `list /on 5/3/2024`

The list of undone events that are occurring on 5th March 2024 will be shown.

```
list of tasks:
1. [E][ ] interest group session (from: Mar 05 2024 2000 to: Mar 05 2024 2200)
```

## Marking a task as done/undone

Marks a task in the task list as done/undone.

Format:

- `mark INDEX`
- `unmark INDEX`

Example: `mark 1`

The first task in the task list will be marked as done.

```
good job!
  [T][X] submit ip
```

Example: `unmark 1`

The first task in the task list will be marked as undone.

```
ok you just undid this task
  [T][ ] submit ip
```

## Locating tasks by description

Finds tasks whose descriptions contain the given keyword. Multiple keywords are treated as one keyword.

Format: `find [KEYWORD]`

Example: `find interest`

The list of tasks whose descriptions contain the word "interest" will be shown.

```
matching tasks:
1. [E][ ] interest group session (from: Mar 05 2024 2000 to: Mar 05 2024 2200)
```

## Deleting tasks

Deletes the specified task from the task list. The task to delete is specified by an index, which refers to the index
number shown by executing `list`.

Format: `delete INDEX`

Example: `delete 4`

The 4th task in the task list will be deleted.

```
removed:
  [P][ ] collect certificate (start: Jan 15 2024 2000 end: Jan 25 2024 2359)
3 task(s) left
```

## Exiting the program

Exits the program.

Format: `exit`

Example: `exit`

The program will terminate.