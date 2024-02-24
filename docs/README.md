# Raphael User Guide

![Raphael in action]("./Ui.png")

Raphael is a **desktop bot for managing tasks, optimized for a use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Raphael can get your task management tasks done faster.
- [Adding deadlines: `deadline`](#deadline)
- [Adding events: `event`](#event)
- [Adding todos: `todo`](#todo)
- [Deleting tasks: `delete`](#delete)
- [Marking tasks: `mark`](#mark)
- [Unmarking tasks: `unmark`](#unmark)
- [Showing all tasks: `list`](#list)
- [Finding matching tasks: `find`](#find)
- [Bid farewell: `bye`](#bye)

## Adding deadlines: `deadline` {#deadline}
Adds a deadline to the task list.
Format: `deadline DESCRIPTION by DEADLINE`
Examples:
* `deadline iP UG by 23/2/2024 2359`
* `deadline iP release by 23/02/2024 2359`

// A description of the expected outcome goes here

```
expected output
```

## Adding events: `event` {#event}
Adds an event to the task list.
Format: `event DESCRIPTION from START_TIME to END_TIME`
Examples:
* `event Chinese New Year Celebration from 10/2/2024 0000 to 24/2/2024 2359`

// A description of the expected outcome goes here

```
expected output
```

## Adding todos: `todo` {#todo}
Adds a todo into the task list.
Format: `todo DESCRIPTION`
Examples:
* `todo sleep well`
* `todo optional reading`
// A description of the expected outcome goes here

```
expected output
```

## Deleting tasks: `delete` {#delete}
Deletes a task (indicated by index) from the task list.
Format: `delete INDEX`
Examples:
* `delete 0`
* `delete 1`

// A description of the expected outcome goes here

```
expected output
```

## Marking tasks: `mark` {#mark}
Marks a task in the task list as done.
Format: `mark INDEX`
Examples:
* `mark 1`
* `mark 2`

// A description of the expected outcome goes here

```
expected output
```

## Unmarking tasks: `unmark` {#unmark}
Unmarks a task in the task list as undone.
Format: `unmark INDEX`
Examples:
* `unmark 1`
* `unmark 2`

// A description of the expected outcome goes here

```
expected output
```

## Showing all tasks: `list` {#list}
Lists all the tasks in the task list.
Format: `list`
Examples:
* `list`

// A description of the expected outcome goes here

```
expected output
```

## Finding matching tasks: `find` {#find}
Finds the matching tasks from the task list that contains the given substring.
Format: `find KEYWORD`
Examples:
* `find Sleep`
* `find read`

// A description of the expected outcome goes here

```
expected output
```

## Bid farewell: `bye` {#bye}
Bids farewell to the user.
Format: `bye`
Examples:
* `bye`

// A description of the expected outcome goes here

```
expected output
```

## Saving the data
Raphael data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Editing the data file
Raphael data are saved automatically as a text file `[JAR file location]/data/tasks.txt`. Advanced users are welcome to update data directly by editing that data file.

> **Caution:** If your changes to the data file makes its format invalid, Raphael will start as if the file is empty. Hence, it is recommended to take a backup of the file before editing it.
> Furthermore, certain edits can cause the Raphael to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.

## Command summary
| Action | Format, Examples                                |
|--------|-------------------------------------------------|
| todo   | `todo DESCRIPTION`                              |
|deadline| `deadline DESCRIPTION by DEADLINE`              |
|event| `event DESCRIPTION from START_TIME to END_TIME` |
|delete| `delete INDEX`                                  |
|mark| `mark INDEX`                                    |
|unmark| `unmark INDEX`                                  |
|list| `list`                                          |
|find| `find KEYWORD`                                  |
|bye| `bye`                                            |