# Luke User Guide

![Ui](./Ui.png)

Luke is a simple task list manager. You (the person on the right) can interact with Luke (the person on the left) through commands to manage your tasks. If this is your first time using this app, sample data has already been loaded for you. Feel free to experiment with them and delete them if not in use. Your tasks are automatically saved to the save file, which will be automatically loaded upon starting Luke.

Warning: do not edit the save file directly, or the save file may be corrupted! In this case, double clicking would not start the jar file, and trying to run it in terminal will throw an exception (you should see `Save file could not be read. If you have edited the save file, please undo the edits so that it could be read. Otherwise, it could be due to corruption.`). Worst of all, you might possibly lose all your saved tasks!

Below is the list of features/commands that are available:

## Basic functionality

To interact with Luke, you will type your input into the textbox provided, and hit "Enter" or the "Send" button. 

Inputs have to be valid for the feature to execute.

The first word in your input is called a `command`. If it does not match with one of these words (not case-sensitive): "todo", "deadline", "event", "list", "mark", "unmark", "find", "delete", "help", "bye", this error message would be displayed:

```
Sorry, please type a valid command. The valid commands are:
bye
list
mark
unmark
todo
deadline
event
delete
find
help
```

In general, error messages will be thrown if the input is invalid or the save file is corrupted, telling the user about the cause of error and what to do to rectify it.

## Adding todos

Adds a todo of the specified description, without a start or end date, to the task list.

Format: `todo [task description]`

Example: `todo exercise` adds a todo with description "exercise".

If successful, you should see this output message (the number of tasks in the task list may differ):

```
Got it. I've added this task:
[T][ ] exercise
Now you have 1 task in the list.
```

## Adding deadlines

Adds a deadline of the specified description, with only an end date, to the task list.

Format: `deadline [task description] /by [end date]`. [end date] must be in format `YYYY-MM-dd HH:mm`.

Example: `deadline homework /by 2024-02-02 23:59` adds a deadline with description "homework" that is due by 2 Feb 2024 23:59.

If successful, you should see this output message:

```
Got it. I've added this task:
[D][ ] homework (by: 02 Feb 2024 23:59)
Now you have 2 tasks in the list.
```

## Adding events

Adds an event of the specified description, with a start date and end date, to the task list.

Format: `event [task description] /from [start date] /to [end date]`. [start date] and [end date] must be in format `YYYY-MM-dd HH:mm`.

Example: `event study /from 2024-02-02 09:00 /to 2024-02-02 12:00` adds an event with description "study" from 2 Feb 2024 09:00 to 2 Feb 2024 12:00.

If successful, you should see this output message:

```
Got it. I've added this task:
[E][ ] study (from: 02 Feb 2024 09:00 to: 02 Feb 2024 12:00)
Now you have 3 tasks in the list.
```

## Listing tasks

Lists all the tasks in the task list.

Format: `list`

If successful, you should see this output message (the tasks in the task list may differ):

```
Here are the tasks in your list:
1.[T][ ] exercise
2.[D][ ] homework (by: 02 Feb 2024 23:59)
3.[E][ ] study (from: 02 Feb 2024 09:00 to: 02 Feb 2024 12:00)
```

## Marking tasks

Marks a task (presumably as completed) with an "X".

Format: `mark INDEX`, where the (INDEX)th task in the list would be marked.

Example: `mark 2` would mark the 2nd task in the list.

If successful, you should see this output message (the tasks in the task list may differ):

```
Nice! I've marked this task as done:
[D][X] homework (by: 02 Feb 2024 23:59)
```

and the task should be marked when displaying it in the task list:

```
Here are the tasks in your list:
1.[T][ ] exercise
2.[D][X] homework (by: 02 Feb 2024 23:59)
3.[E][ ] study (from: 02 Feb 2024 09:00 to: 02 Feb 2024 12:00)
```

## Unmarking tasks

Marks a task (presumably as completed) with the "X" removed.

Format: `unmark INDEX`, where the (INDEX)th task in the list would be unmarked.

Example: `unmark 2` would unmark the 2nd task in the list.

If successful, you should see this output message (the tasks in the task list may differ):

```
OK, I've marked this task as not done yet:
[D][ ] homework (by: 02 Feb 2024 23:59)
```

and the task should be unmarked when displaying it in the task list:

```
Here are the tasks in your list:
1.[T][ ] exercise
2.[D][ ] homework (by: 02 Feb 2024 23:59)
3.[E][ ] study (from: 02 Feb 2024 09:00 to: 02 Feb 2024 12:00)
```

## Finding tasks

Searches tasks whose description contains the keywords entered (contiguously).

Format: `find [keywords]`, where [keywords] are the words that the system should search for.

Example: `find work` would search for tasks that contain the keyword(s).

If successful, you should see this output message (the tasks in the task list may differ):

```
Here are the matching tasks in your list:
1.[D][ ] homework (by: 02 Feb 2024 23:59)
```

## Deleting tasks

Deletes a task from the task list.

Format: `delete INDEX`, where the (INDEX)th task in the list would be deleted.

Example: `delete 2` would delete the 2nd task in the list.

If successful, you should see this output message (the tasks in the task list may differ):

```
Noted. I've removed this task:
[D][ ] homework (by: 02 Feb 2024 23:59)
Now you have 2 tasks in the list.
```

and the task should be marked when displaying it in the task list:

```
Here are the tasks in your list:
1.[T][ ] exercise
2.[E][ ] study (from: 02 Feb 2024 09:00 to: 02 Feb 2024 12:00)
```

## Searching help

Displays a guide to help the user use the app.

Format: `help`

If successful, you should see this output message:

```
How to use:
Luke is a simple task list manager. You (the person on the right) can interact with Luke (the person on the left) through commands to manage your tasks.
...
```
