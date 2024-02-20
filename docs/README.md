# Johnny User Guide

![Ui](Ui.png "Johnny")

Welcome to Johnny, a chatbot that tracks all your tasks and saves it for reuse. 
There are 3 variations of tasks you can save, todos, deadlines and events.

## Adding a todo: todo

Adds a todo to the list of tasks.

Format: `todo NAME`

Example: `todo take a nap`

```
Go get this done bro:
[T][ ] take a nap
You still have _ tasks in you list bro.
```

## Adding a deadline: deadline

Adds a deadline to the list of tasks.

Format: `deadline NAME /by BY (yyyy/MM/dd HHmm)`

Example: `deadline return book /by 2023/08/12 1230`

```
Go get this done bro:
[D][ ] return book (by: Aug 12 2023 12:30 PM)
You still have _ tasks in you list bro.
```

## Adding an event: event

Adds an event to the list of tasks.

Format: `event NAME /from FROM (yyyy/MM/dd HHmm) /to TO (yyyy/MM/dd HHmm)`

Example: `event party /from 2023/09/01 1800 /to 2023/09/01 2359`

```
Go get this done bro:
[E][ ] part (from: Sep 01 2023 06:00 PM to: Sep 01 2023 11:59 PM)
You still have _ tasks in you list bro.
```

## Listing tasks: list

Lists all tasks being tracked

Format: `list`

Example: `list`

```
Get all these done bro:
1. [T][ ] take a nap
2. [D][ ] return book (by: Aug 12 2023 12:30 PM)
```

## Marking tasks: mark

Mark tasks being tracked based on indices

Format: `mark INDICES`

Example: `mark 1 2`

```
Finally done bro:
1. [T][X] take a nap
2. [D][X] return book (by: Aug 12 2023 12:30 PM)
```

## Unmarking tasks: unmark

Unmark tasks being tracked based on indices

Format: `unmark INDICES`

Example: `unmark 2`

```
Why are you not done yet bro:
1. [D][ ] return book (by: Aug 12 2023 12:30 PM)
```

## Deleting tasks: delete

Delete tasks being tracked based on indices

Format: `delete INDICES`

Example: `delete 1`

```
Tasks removed. Why so lazy bro:
1. [T][ ] take a nap
You still have _ tasks in your list bro.
```

## Exit: bye

Application will close upon command.


## Save tasks in local storage

Tasks will be saved into your local computer upon edits. 
You don't have to worry about losing changes if you computer crashes.


## Load tasks from local storage

Tasks will be loaded from you local computer automatically. No further steps are needed.