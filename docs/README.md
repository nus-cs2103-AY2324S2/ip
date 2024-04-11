# SirDuke User Guide

![product screenshot](Ui.png)

SirDuke is at your service. With SirDuke you would be able to:
- Add tasks
    - events
    - deadlines
    - todos
- Find for tasks by a query
- delete tasks by index
- Archive your tasks
- mark/unmark your tasks
- list tasks

## Adding todo

Add a `todo` that sets a task 

Example: `todo meet alfred`

You should be able to see...

```
Got it. I've added this task:
[T][ ] meet alfred
Now you have 2 tasks in the list.
```

## Adding deadlines

Add a `deadline` that sets a task that should end by a certain period

parameters:
`by` when is the deadline by?

Example: `deadline meet John Wick /by 12-12-1212 12:12`

You should be able to see...

```
Got it. I've added this task:
[D][ ] meet John Wick (by: Dec 12 1212 12:12)
Now you have 3 tasks in the list.
```
## Adding Events

Add a `event` that sets a task that should start and end by a certain period

parameters:
`from` when does your event start?
`to` when does your event end?

Example: `event wine and dine with Bruce Wayne /from 12-12-1212 12:12 /to 13-13-1313 13:13`

You should be able to see...

```
Got it. I've added this task:
[E][ ] wine and dine with bruce wayne (from: Nov 11 1111 11:11 to: Dec 12 1212 12:12)
Now you have 1 tasks in the list.
```

## Find

Returns a list of task that matches that query you have passed in

parameters:
`query` task that you would like to find

Example: `find book`

You should be able to see...

```
1. [D][X] return book (by: Dec 2 2019 18:00) 
2. [D][X] book (by: Dec 12 1212 12:12)
```

## Delete

Deletes the task by index. Index of a task can be found using the `list` command. Items in the list are 1-indexed.

parameters:
`index` index of task

Example: `delete 1`

You should be able to see...

```
Got it. I've deleted this task:
[D][X] return book (by: Dec 2 2019 18:00)
Now you have 1 tasks in the list.
```

## Mark Tasks
Mark your task as done

paramters:
`index` index of task that you would like to be marked

Example: `mark 1`

You should be able to see...
```
Nice! I've marked this task as done:

[D][X] book (by: Dec 12 1212 12:12)
```

## Unmark tasks
Unmark your task if it was already marked

paramters:
`index` index of task that you would like to be marked

Example: `unmark 1`

You should be able to see...
```
OK, I've marked this task as not done yet:

[D][ ] book (by: Dec 12 1212 12:12)
```

## List tasks

List all your tasks

Example: `List`

You should be able to see...
```
1. [D][ ] book (by: Dec 12 1212 12:12
```

## Archive

Command: `archive` (case-insensitive)

With this command you would **clear** all the tasks in storage (stored in the `data`) and have it copied over to
another txt file in the `archive` directory

You should be able to see...
```
"All your contacts have been archived. You can view them in archive/archived.txt"
```
