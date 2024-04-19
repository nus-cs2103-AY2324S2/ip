# Wang User Guide

![Ui.png](Ui.png)

TaskList for you to track your tasks.

## Adding todos

Add tasks

Example: `todo life`

```
Got it. I've added the task:
[T][] life
Now you have 1 tasks in the list
```

## Adding deadlines

Add tasks with set deadlines

Example: `deadline life /by 02/03/2045 1800`

1. time can be omitted from the command
2. does not accept any past datetime

```
Got it. I've added the task:
[D][] life
(by: Mar 02 2045 6:00pm)
Now you have 2 tasks in the list
```

## Adding events

Add tasks with set start time and end time

Example: `event life /from 02/03/2045 1800 /to 02/04/2066 1900`

1. time can be omitted from the command
2. does not accept any past datetime

```
Got it. I've added the task:
[E][] life
(from: Mar 02 2045 6:00pm to: April 02 2066 7:00pm)
Now you have 3 tasks in the list
```

## listing tasks

List all tasks added

Example: `list`

```
1.[T][] life
2.[D][] life
(by: Mar 02 2045 6:00pm)
3.[E][] life
(from: Mar 02 2045 6:00pm to: April 02 2066 7:00pm)
```

## Removing tasks

Remove tasks in task list

Example: `delete 1(index in list)`

1. list is 1 indexed

```
Noted. I've removed this task:
[T][] life
Now you have 2 tasks in the list.
```

## Marking and Unmarking tasks

Mark or unmark task as complete

1. list is 1 indexed

Example: `mark 1(index in list)`
Example: `unmark 1(index in list)`

```
Nice! I've marked this task as done:
[D][X] life
(by: Mar 02 2045 6:00pm)
```

## Give priority to tasks

Set high or low priority to tasks

Example: `priority 1(index in list)`

1. list is 1 indexed
2. high & low command is **not** case-sensitive

```
OK! I've updated this task's Priority:
[D][X] life HIGH!!
(by: Mar 02 2045 6:00pm)
```

