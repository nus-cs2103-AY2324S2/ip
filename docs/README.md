# Lindi User Guide

![product screenshot](Ui.png)

Lindi: The all in one app for managing your upcoming Tasks, Events and Deadlines.

## List all tasks

Prints out the tasks that have been tracked by the chatbot

Example: `list`

Prints out the tasks that have been tracked by the chatbot

expected output:
```
    1. [E][ ] NUSChoir concert (when: Feb 23 2024 19:00 - Feb 23 2024 21:00)
    2. [T][ ] my laundry
    ...
```


## Find tasks

Search by keyword the tasks tracked by the chatbot

Example: `find <keyword?`
Example: `find concert`

Prints out the tasks that have been tracked by the chatbot

expected output:
```
    1. [E][ ] NUSChoir concert (when: Feb 23 2024 19:00 - Feb 23 2024 21:00)
```



## Adding todos

Adds a todo to the list of tasks

Example: `todo <description>`
Example: `todo my laundry`

Adds a todo for the task bot to track, with the given date time.

expected output:
```
Okay. I've added this task:
	[T][ ] laundry
Now you have 5 tasks in the list.
```


## Adding deadlines

Adds a deadline to the list of tasks

Example: `deadline <description> /by YYYY-MM-DD-HH-mm`

Adds a deadline for the task bot to track, with the given date time.

## Adding event

Adds an event to the list of tasks

Example: `event <description> /from YYYY-MM-DD-HH-mm /to YYYY-MM-DD-HH-mm`
Example: `event NUSChoir concert /from 2024-02-23-19-00 /to 2024-02-23-21-00`


Adds an event for the task bot to track, with the given start time and end time

```
expected output
```

## Mark, Unmark tasks

Marks a task as done; unmarks a task as done

Example: `mark/unmark <indices>`
Example: `mark 1, 2, 5-9`
Example: `unmark 6`

Indices can be a singular index (as shown by 'list'), or expressed as a range or multi selection.
The format is indices separated by commas. For ranges, indices separated by dashes '-'.
Duplicate indices not allowed.

Expected output:
```
Nice! I've marked this task as done:
	[T][X] laundry

Nice! I've marked this task as done:
	[E][X] NUSChoir concert (when: Feb 23 2024 19:00 - Feb 23 2024 21:00)
```

## Delete a task

Marks a task as done; unmarks a task as done

Example: `delete <indices>`
Example: `delete 1, 2, 5-9`
Example: `delete 6`

Indices can be a singular index (as shown by 'list'), or expressed as a range or multi selection.
The format is indices separated by commas. For ranges, indices separated by dashes '-'.
Duplicate indices not allowed.

Expected output:
```
Okay. I've deleted this task:
	[E][X] NUSChoir concert (when: Feb 23 2024 19:00 - Feb 23 2024 21:00)
Now you have 4 tasks in the list.
```

## Bye

This allows you to exit the program. All data will be saved between sessions.

Example: `bye`

Program and GUI will terminate after 1 second.