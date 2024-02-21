# Lite User Guide

Lite is a user-friendly chatbot, especially designed towards
forgetful students to keep track of their ToDos, Events, and Deadlines.
Lite will be able to keep track the dates and status of completion of tasks,
allowing better planning for your future tasks.

## List of Features

- [x] Adding ToDo task
- [x] Adding Event task
- [x] Adding Deadline task
- [x] Mark tasks as done
- [x] Unmark tasks as undone
- [x] Keep track and list out all of the added tasks
- [x] Delete tasks from the list
- [x] Find a task using a keyword
- [x] Close the program
- [ ] Reminder (Coming soon)


## Adding Todo task

Allows users to add a ToDo task that has no date.

Format: todo [description]

Example: `todo return a book`

Expected output:

```
Got it. I've added this task:
[T][] return a book
Now you have <x> tasks in the list
```

## Adding Deadline Task

Allows users to add a Deadline task that has a due date.

Format: deadline [DESCRIPTION] /by [DATE].
- The date has to be in the format of YYYY-MM-DD, and a time from 00:00 to 23:59.

Example: deadline 2103T Assignment /by 2024-02-20 23:59


Expected output:

```
Got it. I've added this task:
[D][] 2103T Assignment (by: FEBRUARY 20 2024 23:59)
Now you have <x> tasks in the list
```


## Adding Event Task

Allows users to add a Event task that has a start date and end date.

Format:event [DESCRIPTION] /from [DATE] /to [DATE]
- The date has to be in the format of YYYY-MM-DD, and a time from 00:00 to 23:59.

Example: event 2101 group meeting /from 2024-02-20 17:30 /to 2024-02-20 18:30


Expected output:

```
Got it. I've added this task:
[E][] 2101 group meeting (from: FEBRUARY 20 2024 17:30 to: 
FEBRUARY 20 2024 18:30)
Now you have <x> tasks in the list
```

## Mark Tasks as Done

Allow users to mark their tasks as completed

Format: mark INDEX

Example: mark 1

Assuming that the very first task you added was the ToDo task of returning a book,
this is the expected output:

```
This task has been marked as done!
[T][X] return a book
```

## Unmark Tasks

Similarly, you can also unmark your tasks to undo it. This is mainly to undo
if you incorrectly marked your task as done.

Format: unmark INDEX

Example: unmark 1

Assuming that the very first task you added was the ToDo task of returning a book,
this is the expected output:

```
This task has been unmarked!
[T][] return a book
```
 
## List out all the tasks added

Allow users to see the list of tasks that they have added so far.
It also keeps track of the tasks that may have been added before 

Format: list

Assuming that you had added a todo, deadline, and event task in that respective order,
this is the expected output:

```
1. [T][] return a book
2. [D][] 2103T Assignment (by: FEBRUARY 20 2024 23:59)
3. [E][] 2101 group meeting (from: FEBRUARY 20 2024 17:30 to: 
FEBRUARY 20 2024 18:30)
```


## Delete Tasks

Allow users to delete the tasks that are in the list

Format: delete INDEX

Example: delete 1

Assuming that the very first task you added was the ToDo task of returning a book,
this is the expected output:

```
Noted. I've removed this task:
[T][] return a book
Now you have <x> tasks in the list
```

Also note that if you delete a task, all the index after that task will drop by 1.
So if you had added all a todo, deadline, event tasks all in order, calling list will result in this output:

```
Here are the tasks in your list:
1. [D][] 2103T Assignment (by: FEBRUARY 20 2024 23:59)
2. [E][] 2101 group meeting (from: FEBRUARY 20 2024 17:30 to: 
FEBRUARY 20 2024 18:30)
```

## Finding a task using a keyword
Allow users to find a task that contains a specified word

Format: find [KEYWORD]
- The search is case-sensitive e.g book will not find Book
- The search uses partial search e.g gro will find group


Example: find Assignment

Expected output:
```
Here are the matching tasks in your list:
1. [D][] 2103T Assignment (by: FEBRUARY 20 2024 23:59)
```


## Terminating the program
Aside from clicking the X button on the top right, you can also provide
a command to close the app. This command is "bye" (without the " ")

Format: bye


Note that it is case-sensitive, so it must be exactly the same as bye



