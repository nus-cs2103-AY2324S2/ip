# Raphael User Guide

![Raphael in action](Ui.png)

Raphael is a **desktop bot for managing tasks, optimized for a use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Raphael can get your task management tasks done faster.
- [Quick start](#quick-start)
- [Features](#features)
  - [Viewing help: `help`](#viewing-help-help)
  - [Adding deadlines: `deadline`](#adding-deadlines-deadline)
  - [Adding events: `event`](#adding-events-event)
  - [Adding todos: `todo`](#adding-todos-todo)
  - [Deleting tasks: `delete`](#deleting-tasks-delete)
  - [Marking tasks: `mark`](#marking-tasks-mark)
  - [Unmarking tasks: `unmark`](#unmarking-tasks-unmark)
  - [Showing all tasks: `list`](#showing-all-tasks-list)
  - [Finding matching tasks: `find`](#finding-matching-tasks-find)
  - [Biding farewell: `bye`](#biding-farewell-bye)
- [Command summary](#command-summary)

## Quick start
1. Ensure that you have Java `11` installed in your Computer.
2. Download the latest `Raphael.jar` from [here](https://github.com/JustWeiHao/ip/releases)
3. Copy the file to the folder you want to use as the _home folder_ for your Raphael.
4. Open a command terminal, `cd` into the folder you put the jar file in,
and use the `java -jar Rapahel.jar` command to run the application.<br>
A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
![Raphael GUI upon starting](Start.png)
5. Type the command in the command box and press Enter to execute it. 
e.g., typing `help` and pressing Enter will show the list of commands.<br>
Some example commands you can try:
   - `list`: Lists all tasks.
   - `bye`: Exits the app.
6. Refer to the [Features](#features) below for details of each command.

## Features
> Notes about the command format
> - Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
e.g., in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo iP`
> - Parameters must be in the exact order
> - Extraneous parameters for commands that do not take in parameters (such as `help`, `bye`, `list`) will be ignored.<br>
e.g., if the command specifies `list 123`, it will be interpreted as `list`
> - If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines
as space characters surrounding line-breaks may be ommited when copied over to the application.

### Viewing help: `help`
Shows the list of commands available<br>
Format: `help`

### Adding deadlines: `deadline`
Adds a deadline to the task list.<br>
Format: `deadline DESCRIPTION by DEADLINE`<br>
Examples:
* `deadline iP UG by 23/2/2024 2359`
* `deadline iP release by 23/02/2024 2359`

// A description of the expected outcome goes here

```
expected output
```

## Adding events: `event`
Adds an event to the task list.<br>
Format: `event DESCRIPTION from START_TIME to END_TIME`<br>
Examples:
* `event Chinese New Year Celebration from 10/2/2024 0000 to 24/2/2024 2359`

// A description of the expected outcome goes here

```
expected output
```

## Adding todos: `todo`
Adds a todo into the task list.<br>
Format: `todo DESCRIPTION`<br>
Examples:
* `todo sleep well`
* `todo optional reading`
// A description of the expected outcome goes here

```
expected output
```

## Deleting tasks: `delete`
Deletes a task (indicated by index) from the task list.<br>
Format: `delete INDEX`<br>
Examples:
* `delete 0`
* `delete 1`

// A description of the expected outcome goes here

```
expected output
```

## Marking tasks: `mark`
Marks a task in the task list as done.<br>
Format: `mark INDEX`<br>
Examples:
* `mark 1`
* `mark 2`

// A description of the expected outcome goes here

```
expected output
```

## Unmarking tasks: `unmark`
Unmarks a task in the task list as undone.<br>
Format: `unmark INDEX`<br>
Examples:
* `unmark 1`
* `unmark 2`

// A description of the expected outcome goes here

```
expected output
```

## Showing all tasks: `list`
Lists all the tasks in the task list.<br>
Format: `list`<br>
Examples:
* `list`

// A description of the expected outcome goes here

```
expected output
```

## Finding matching tasks: `find`
Finds the matching tasks from the task list that contains the given substring.<br>
Format: `find KEYWORD`<br>
Examples:
* `find Sleep`
* `find read`

// A description of the expected outcome goes here

```
expected output
```

## Biding farewell: `bye`
Bids farewell to the user.<br>
Format: `bye`<br>
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
|help|`help`|
| todo   | `todo DESCRIPTION`                              |
|deadline| `deadline DESCRIPTION by DEADLINE`              |
|event| `event DESCRIPTION from START_TIME to END_TIME` |
|delete| `delete INDEX`                                  |
|mark| `mark INDEX`                                    |
|unmark| `unmark INDEX`                                  |
|list| `list`                                          |
|find| `find KEYWORD`                                  |
|bye| `bye`                                            |
