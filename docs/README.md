# Dwight User Guide

// Update the title above to match the actual product name

// Product screenshot goes here

// Product intro goes here

## Adding deadlines

// Describe the action and its outcome.

// Give examples of usage

Example: `keyword (optional arguments)`

// A description of the expected outcome goes here

```
expected output
```

# Address Book CLI Readme

Welcome to the Address Book CLI repository! This command-line interface (CLI) allows you to manage your address book efficiently. Below are the commands and their respective formats that you can use:

## View tasks : list

Lists out all tasks entered by user

Format: `list`

## Mark task : mark

Marks tasks as done.

Format: `mark INDEX`

Examples:
- `mark 1`

## Unmark task : unmark

Unmarks task as done.

Format: `unmark INDEX`

Examples:
- `unmark 1`

## Find tasks by keyword: find

Finds tasks whose names contain any of the given keywords.

Format: `find KEYWORD`

The search is case-insensitive. e.g hans will match Hans.

Only full words will be matched e.g. Han will not match Hans.

Examples:
- `find eat` returns eat and eat cake.

## Deleting a task : delete

Deletes the specified person from the address book.

Format: `delete INDEX`

Deletes the task at the specified INDEX. The index refers to the index number shown in the displayed task list. The index must be a positive integer 1, 2, 3, …​

Examples:
- `list` followed by `delete 2` deletes the 2nd task in the address book.

## Adds todo task : todo

Adds a todo task to the task list.

Format: `todo TASK_NAME`

Examples:
- `todo eat chicken`

## Adds deadline task : deadline

Adds a deadline task to the task list.

Format: `deadline TASK_NAME /by DD/MM/YYYY HHMM`

Examples:
- `deadline return book /by 2/12/2019 1800`

## Adds event task : event

Adds an event task to the task list.

Format: `event TASK_NAME /from START_TIME /to END_TIME`

Examples:
- `event project meeting /from Mon 2pm /to 4pm`

## Exiting the program : bye

Exits the program.

Format: `bye`