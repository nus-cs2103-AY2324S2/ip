# ELIAS User Guide

![Screenshot of UI](./docs/Ui.png)

# Elias is here to serve  you! 🤵‍♂️

> "It's not enough to be busy, so are the ants. The question is, what are we busy about?" - Henry David Thoreau

Elias is an ongoing project of [Project Duke](https://github.com/se-edu/duke),
which is a project template for a greenfield Java project
from [SE-EDU](https://se-education.org/docs/templates.html). Elias is a
lightweight task list manager that currently supports:

## Adding Todos

Adds a Todo to your task list. A Todo is a task with no deadline or time period
associated with it.

Syntax: `todo <name>`

Example: `todo Homework`

Output:
```
Got it. I've added this task:
[T][] Homework
Now you have 1 task in the list.
```

## Adding Deadlines

Adds a Deadline to your task list. A Deadline is a tasks that is associated with
a deadline

Syntax: `deadline <name> /by dd/mm/yy hhmm`

Example: `deadline CS2103T iP submission /by 23/02/24 2359`

Output:
```
Got it. I've added this task:
[D][] CS2103T iP submission (by: 23/02/24 11:59pm)
Now you have 2 task in the list.
```

## Adding Events

Adds an Event to your task list. An Event is a task associated with a start
and end time

Syntax: `event <name> /from dd/mm/yy hhmm /to dd/mm/yy hhmm`

Example: `event NUS Career Fest /from 20/02/24 9:00am /to 22/02/24 5:00pm`

Output:
```
Got it. I've added this task:
[E][] NUS Career Fest (from: 20/2/24 9:00 am to: 22/2/24, 5:00 pm)
Now you have 3 task in the list.
```

## Mark/Unmark Tasks

Marks a task as complete or unmarks completed tasks to note them as incomplete
using the task index.

Syntax: `mark <i>` where i refers to the index of the item in the Item List

Example: `mark 1`

Output:
```
Nice! I've marked this task as done:
[T][X] Homework
```

## List Tasks

Lists all tasks recorded in the Task List. This can be sorted chronologically using
the sort command, but will otherwise list tasks in the order of insertion into the
list. The index observed from the output is the same index used to mark/unmark or
delete tasks from the task list.

Syntax: `list`

Output:
```
Here are your remaining tasks:
1. [T][X] Homework
2. [D][] CS2103T iP submission (by: 23/02/24 11:59pm)
3. [E][] NUS Career Fest (from: 20/2/24 9:00 am to: 22/2/24, 5:00 pm)
```

## Delete Tasks

Deletes a task from the Task List using the task index.

Syntax: `delete <i>` where i refers to the index of the item in the Item List

Example: `delete 1`

Output:
```
Noted. I've removed this task:
[T][X] Homework
Now you have 2 tasks in the list
```

## Find

Finds a task from the Task List whose name contains the search string. This command is case-sensitive.

Syntax: `find <search string>`

Examples:
- `find Career`
- `find career`

Output:
```
[E][] NUS Career Fest (from: 20/2/24 9:00 am to: 22/2/24, 5:00 pm)
```
```
No tasks contain the search string: career
```
## Sort

Sorts the Task List in ascending chronological order. Events are sorted using their start time, while Todos
are always sorted to the end of the Task List. This operation is non-reversible, and modifies the Task List directly.
This means that subsequent `list` commands will display the chronologically sorted order of tasks.

Syntax: `sort`

Output:
```
1. [E][] NUS Career Fest (from: 20/2/24 9:00 am to: 22/2/24, 5:00 pm)
2. [D][] CS2103T iP submission (by: 23/02/24 11:59pm)
```

## Save Task List

Saves the task list to memory, and exits the programme.

Syntax: `bye`