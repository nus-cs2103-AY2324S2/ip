# Raphael User Guide

// Product screenshot goes here

Rapahel is a **desktop bot for managing tasks, optimized for a use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Raphael can get your task management tasks done faster.

## Adding deadlines: `deadline`
Adds a deadline to the task list.
Format: `deadline DESCRIPTION by DEADLINE`
Examples:
* `deadline iP UG by 23/2/2024 2359`
* `deadline iP release by 23/02/2024 2359`

// A description of the expected outcome goes here

```
expected output
```

## Adding events: `event`
Adds an event to the task list.
Format: `event DESCRIPTION from START_TIME to END_TIME`
Examples:
* `event Chinese New Year Celebration from 10/2/2024 0000 to 24/2/2024 2359`

// A description of the expected outcome goes here

```
expected output
```

## Adding todos: `todo`
Adds a todo into the task list.
Format: `todo DESCRIPTION`
Examples:
* `todo sleep well`
* `todo optional reading`
// A description of the expected outcome goes here

```
expected output
```

## Deleting tasks: `delete`
Deletes a task (indicated by index) from the task list.
Format: `delete INDEX`
Examples:
* `delete 0`
* `delete 1`

// A description of the expected outcome goes here

```
expected output
```

## Marking tasks: `mark`
Marks a task in the task list as done.
Format: `mark INDEX`
Examples:
* `mark 1`
* `mark 2`
// Describe the action and its outcome.

// Give examples of usage

Example: `keyword (optional arguments)`

// A description of the expected outcome goes here

```
expected output
```

## Unmarking tasks: `unmark`
Unmarks a task in the task list as undone.
Format: `unmark INDEX`
Examples:
* `unmark 1`
* `unmark 2`

// A description of the expected outcome goes here

```
expected output
```

## Showing all tasks: `list`
Lists all the tasks in the task list.
Format: `list`
Examples:
* `list`

// A description of the expected outcome goes here

```
expected output
```

## Finding matching tasks: `find`
Finds the matching tasks from the task list that contains the given substring.
Format: `find KEYWORD`
Examples:
* `find Sleep`
* `find read`

// A description of the expected outcome goes here

```
expected output
```

## Bid farewell: `bye`
Bids farewell to the user.
Format: `bye`
Examples:
* `bye`

// A description of the expected outcome goes here

```
expected output
```