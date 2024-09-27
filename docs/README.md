# Univus User Guide

Hello universe! 
Do you feel hard to schedule by yourself? 
Do you often forget what you should do?
Do you often miss the deadline of tasks?
Here is the ultimate task manager for you! Univus!

This is the preview!
![preview](/docs/Ui.png)

## Quick Start

Let's get started with these steps:
1. Clone [me](https://github.com/Hibeom0929/ip) to your local machine.
2. Run the `Launcher.java` file!

** Your device should have Java installed!!

## Features
Here are the features that Univus have.
- Add Tasks: you can add your tasks to the list to be saved. _todo_, _deadline_, and _event_ are supported task type.
- Mark Tasks: you can mark task as done or not done.
- List Tasks: you can list all the tasks you have stored.
- Remove Tasks: you can remove unnecessary tasks.

All these features are done with a single line command!
## Specific Command
We can _add_, _edit_, and _list_ tasks by these command.
## Add tasks

> todo

Format: `todo (description)`

Example: `todo read book`

> deadline

Format: `deadline (description) /by (yyyy-MM-dd)`

Example: `deadline homework /by (2024-02-28)`

> event

Format: `event (description) /from (start time) /to (end time)`

Example: `event party /from 5pm /to 7pm`

## Edit tasks
> mark

Format: `mark (index)`

Example: `mark 1` means mark the fisrt task in the list as done.

> unmark

Format: `unmark (index)`

Example: `unmark 1` means unmark the fisrt task in the list as not done.

> delete

Format: `delete (index)`

Example: `delete 1` means delete the fisrt task in the list.

## List tasks
> list

Format: `list`

List all of the tasks in the list.

Example: `list`
```
1.[T][ ] run
2.[D][ ] homework (by:Feb 28 2024)
3.[E][ ] party (from: 5pm to: 7pm)
```

> find

Format: `find (keyword)`

List all of the tasks in the list that contain the keyword.

Example: `find run`
```
1.[T][ ] run
2.[T][ ] run to home
```
## Others

> bye

End the Univus.

Format: `bye`
