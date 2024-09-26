# User Guide

Luke is a CLI-based simple to-do list app for you to keep track of all your tasks, big and small!
If Notion's overwhelming amount of functionality intimidates you, this might just be the to-do
list app for you.

## Quick start

Download the latest `Luke.jar` from (here) [https://github.com/LWS49/ip/blob/master/build/libs/luke.jar]

## Features 

Note that text in brackets are arguments you are supposed to replace when entering the command.

### Save different types of tasks

You can save your tasks under one of three types: Todo, Deadline and Event. 
1. Todo

A todo task has only one field: the description of the task.
The simplest format for the simplest tasks.

2. Deadline


A deadline task has two fields: the description of the task, and the deadline.
Suited for tasks with a deadline you need to keep track of.

3. Event

An event task has two fields: the description of the task, start time, and end time.
Suited for events with a start time and end time that you need to keep track of.

The type of task is labeled at the front of the output:

`[T]`: todo task
`[D]`: deadline task
`[E]`: event task

Some examples below:
```
[T][ ] borrow book -> todo task
[D][ ] return book (by: Wednesday) -> deadline task
[E][ ] librarian interview (from: Tues 2pm to: Tues 3pm) -> event task
```

### List all tasks

You can list all tasks using the `list` command.

It will list all tasks you have in order, displaying the following information for todo tasks:
1. Task number
2. Task type
3. Marked/Unmarked
4. Description

For deadline tasks, it will also display:
5. Deadline

For event tasks, it will also display:
5. Start time
6. End time

Example of output:
```
Here are the tasks in your list:
[T][ ] borrow book
[D][ ] return book (by: Wednesday)
[E][ ] librarian interview (from: Tues 2pm to: Tues 3pm)
```


### Mark and unmark tasks

You can mark and unmark tasks using the `mark` and `unmark` commands to keep track of whether
a task has been completed.

Example of marked vs. unmarked tasks:
```
[T][X] borrow book -> marked
[T][ ] borrow book -> unmarked
```

A new task is unmarked by default.

### Delete tasks

You can delete tasks using the `delete` commands to remove tasks from the list.
The deleted task will be displayed.

### Find tasks

You can find tasks using the `find` command to find tasks whose description contain your input.
It does not select tasks whose other fields contain your input - it only checks the description
of each task.

### Edit tasks

You use the `edit` command to edit a field of a task.

### Save tasks to your local drive

You use the `bye` command to save the list to your local drive.

Whenever you open this application, your local list is automatically loaded into the application (if
the save file exists).

## Usage

Note that all arguments are expected to be String, unless stated otherwise.

### `todo` - Saves a todo task.

Saves a todo task. Successful message shown upon successful save.

Format of command:

```
todo (description)
```
Arguments:

`description`: the description of your task

Example of usage: 

`todo borrow book`

Expected outcome:

```
I've added this task:
[T][ ] borrow book
```



### `deadline` - Saves a deadline task.

Saves a deadline task with a deadline. Successful message shown upon successful save.

Format of command:

```
deadline (description) /by (deadline)
```

Arguments:

`description`: the description of your task
`deadline`: the deadline of your task

Example of usage:

`deadline return book /by Wednesday`

Expected outcome:

```
I've added this task:
[D][ ] return book (by: Wednesday)
```


### `event` - Saves an event task.

Saves an event task with a start and end time. Successful message shown upon successful save.

Format of command:

```
event (description) /from (start time) /to (end time)
```
Arguments:

`description`: the description of your task.

`start time`: the start time of your task.

`end time`: the end time of your task.


Example of usage:

`event librarian interview /from Tues 2pm /to Tues 3pm`

Expected outcome:

```
I've added this task:
[E][ ] librarian interview (from: Tues 2pm to: Tues 3pm)
```

### `list` - Lists all saved tasks.

Lists all saved tasks.

Format of command:

```
list
```

Expected outcome:

```
Here are the tasks in your list:
[T][ ] borrow book
[D][ ] return book (by: Wednesday)
[E][ ] librarian interview (from: Tues 2pm to: Tues 3pm)
```

### `mark` - Mark a task.

Marks a task. Successful message shown upon successful marking.

Format of command:

```
mark (task number)
```
Arguments:

`task number`: the task number as listed in the `list` function.

Example of usage:

`delete 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] return book
```

### `unmark` - Unmark a task.

Unmarks a task. Successful message shown upon successful unmarking.

Format of command:

```
unmark (task number)
```
Arguments:

`task number`: the task number as listed in the `list` function.

Example of usage:

`unmark 1`

Expected outcome:

```
OK, I've marked this task as not done yet:
[T][ ] return book
```

### `delete` - Delete a task.

Delete a task. Successful message shown upon successful deletion.

Format of command:

```
delete (task number)
```

Arguments:

`task number`: the task number as listed in the `list` function.

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[T][ ] return book
You now have 2 tasks in the list.
```

### `Find` - Finds all tasks that contain a string.

Find all tasks that contain the given string in its description and list them.

Format of command:

```
find (keyword)
```

Arguments:

`keyword`: the string used to find the tasks. Can be a string containing spaces.

Example of usage:

`find book`

Expected outcome:

```
Here are the matching tasks in your list:
[T][ ] borrow book
[D][ ] return book (by: Wednesday)
```
The expected outcome if no tasks are found:
```
No tasks with the keyword found.
```

### `Edit` - Edit a task's details

Edit a task's field by replacing it with the given input. 
Successful message shown upon successful modification.

Format of command:

```
edit (task number) (field) (new string)
```

Arguments:

`task number`: the task number of the task  to be edited as listed in the `list` function.

`field`: the field of the task to be edited

`new string`: the string used to replace the field

Points to note for `field`:

1. `todo` tasks only accept `description` under the field argument.
2. `deadline` tasks accept `description` and `by` under the field argument.
3. `event` tasks accept `description` ,`from` and `to` under the field argument.

Example of usage:

`edit 1 description borrow Harry Potter`

Expected outcome:

```
Task change successful! This is the new task:
[T][ ] borrow Harry Potter
```

### `bye` - saves the file to the local drive.

Saves the file to the local drive. Successful message shown upon successful deletion.

Format of command:

```
bye
```

Expected outcome:

```
File saved. Hope to see you again soon!
```