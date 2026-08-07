# Blob User Guide

Disarrayed tasks? Don't worry!

> With Blob, no task will make you sob.

![Screenshot of the product.](Ui.png)

Here is how:

## Adding tasks

### ToDo

Adding a ToDo task, that only have a description.

Command: `todo <description>`

Example: `todo create a resume`

Expected outcome:

```
Got it. I've added this task:
[T][] create a resume
Now you have 1 tasks in the list.
```

### Deadline

Adding a Deadline task, that have a description and a deadline.

Command: `deadline <description> /by <deadline date>`

Example: `deadline CS2103T homework /by 2024-02-26`

Expected outcome:

```
Got it. I've added this task:
[D][] CS2103T homework (by: Feb 26 2024)
Now you have 2 tasks in the list.
```

### Event

Adding an Event task, that have a description, start date, and end date.

Command:

* `event <description> /from <start date> /to <end date>` or

* `event <description> /to <end date> /from <start date>`

Example:

* `event Competition /from 2024-04-01 /to 2024-04-02`

* `event Competition /to 2024-04-02 /from 2024-04-01`

Expected outcome:

```
Got it. I've added this task:
[E][] Competition (from: Apr 1 2024 to Apr 2 2024)
Now you have 3 tasks in the list.
```

## List

To list out all of your tasks.

Command: `list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][ ] create a resume
2. [D][ ] CS2103T homework (by: Feb 26 2024)
3. [E][ ] Competition (from: Apr 1 2024 to Apr 2 2024)
```

## Mark and Unmark

Mark or unmark your tasks as done.

Command: `mark <n>` or `unmark <n>`, when you want to mark or unmark the n-th task.

Mark example: `mark 1`

Expected outcome:

```
Nice! I've marked this task done:
[T][X] create a resume
```


Unmark example: `unmark 1`

Expected outcome:

```
OK, I've marked this task as not done yet:
[T][] create a resume
```

## Delete

Delete a task from your list.

Command: `delete <n>` when you want to delete the n-th task.

Example: `delete 1` when you want to delete the first task.

Expected outcome:

```
Noted. I've removed this task:
[T][] create a resume
Now you have 2 tasks in the list.
```

## Find

Find a task that contains a keyword.

Command: `find <keyword>`

Example: `find CS2103T`

Expected outcome:

```
Here are the matching tasks in your list:
1. [D][ ] CS2103T homework (by: Feb 26 2024)
```

## Exit

Close the chat.

Command: `bye`