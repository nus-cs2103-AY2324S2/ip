# MR WONG User Guide

MR.WONG is a task management chatbot that helps you keep track of your tasks!

![Ui screenshot](Ui.png)

## Adding todos, deadlines, and events

You can add todos, deadlines, or events to your task list.

Format: `todo NAME`, `deadline NAME /by BY`, `event NAME /from FROM /to TO`

Examples:

- `todo gym`
- `deadline homework /by 4pm`
- `event seminar /from 2pm /to 4pm`


## Listing tasks

Shows a list of all tasks saved.

Format: `list`

## Finding tasks

Finds the tasks whose names contain the given string.

Format: `find STRING`

## Deleting tasks

Deletes task from the task list (by index).

Format: `delete INDEX`

- INDEX should be a **positive integer**.

Examples: `delete 2`, `delete 3`

## Marking tasks

You may mark tasks as "done" (by index).

Format: `mark INDEX`

- INDEX should be a **positive integer**.

Examples: `mark 2`, `mark 3`

## Tagging tasks

You may tag tasks (by index) with a keyword.

Format: `tag INDEX NAME`

Examples: 
- `tag 2 fun` adds the tag "#fun" to the second item in the list.
- `tag 3 important` adds the tag "#important" to the third item in the list.