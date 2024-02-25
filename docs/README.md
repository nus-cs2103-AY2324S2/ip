---
output:
  html_document: default
  pdf_document: default
---
# XiaoBai User Guide

![Product screenshot](../Ui.png)

XiaoBai is a loyal dog that simplifies your day by organizing your tasks into a streamlined task list. Whether it's deadlines, events, or simple to-dos, XiaoBai efficiently captures and categorizes your tasks. With its intuitive interface, adding, managing, and tracking tasks is a breeze. Stay on top of your commitments and boost productivity with XiaoBai by your side.

## Adding Deadlines

Adds a deadline to the task list.

Format: `deadline NAME /BYDATETIME`

Example: `deadline submit report /by 2022-02-28 09:00`

```
WOOOoof. I've added this task:
[D][ ] submit report (by:Feb 28 2022 0900)
Now you have 1 tasks in the list.
```

## Adding Events

Adds an event to the task list.

Format: `event NAME /FROMDATETIME /TODATETIME`

Example: `event project meeting /from 18/10/2019 1900 /to 18/10/2019 2100`

```
WOOOoof. I've added this task:
[E][ ] project meeting (from: Oct 18 2019 1900 to: Oct 18 2019 2100)
Now you have 1 tasks in the list.
```

## Adding Todos

Adds a todo to the task list.

Format: `todo NAME`

Example: `todo borrow book`

```
WOOOoof. I've added this task:
[T][ ] borrow book
Now you have 1 tasks in the list.
```

## Deleting Tasks

Deletes a task from the task list.

Format: `delete INDEX`

Example: `delete 1`

```
Woooof. I've removed this task:
[T][ ] borrow book
Now you have 0 tasks in the list.
```

## Marking Tasks

Marks a task as done from the task list.

Format: `mark INDEX`

Example: `mark 1`

If task is already done:
```
This task has already been marked as done:
[T][X] borrow book
```

If task is not done:
```
WOOF! I've marked this task as done:
[T][X] borrow book
```

## Unmarking Tasks

Marks a task as not done from the task list.

Format: `unmark INDEX`

Example: `unmark 1`

If task is already marked as not done:
```
"This task has already been marked as not done:
[T][ ] borrow book
```

If task is done:
```
WOOF, I've marked this task as not done yet:
[T][ ] borrow book
```

## Printing List

Prints the task list.

Format: `list`

Example: `list`

```
Here are the 2 tasks in your list
1.[T][ ] borrow book
2.[E][ ] project meeting (from: Oct 18 2019 1900 to: Oct 18 2019 2100)
```

## Finding Tasks

Prints the task with given keyword.

Format: `find KEYWORD`

Example: `find book`

```
WOOF! I've found these matching tasks in your list:
1.[T][ ] borrow book
```

## Sorting Tasks

Sorts the task list by alphabetical order.

Format: `sort`

Example: `sort`

```
Here is the sorted list of 2 tasks:
1.[T][ ] borrow book
2.[E][ ] project meeting (from: Oct 18 2019 1900 to: Oct 18 2019 2100)
```
