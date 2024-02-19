# Earl User Guide

Earl is a **desktop chat application to track tasks, optimised for 
use via a Command Line Interface (CLI)** while still having the
benefits of a Graphical User Interface (GUI). If you can type fast,
Earl can get your task management done faster than traditional GUI
applications.

![](Ui.png)

# Getting Started

1. Make sure you have **Java 11** installed on your computer. Note that it does
**not** work on newer versions of Java.
2. Download the `.jar` file from [here](https://github.com/yisiox/ip/releases).
3. Open a terminal in the directory which you saved the `.jar` file, then run
   1. `java -jar earl.jar` for GUI mode.
   2. `java -jar earl.jar nogui` for CLI mode.

# Quick Reference

| Command    | Arguments                                       |
|------------|-------------------------------------------------|
| `list`     | None                                            |
| `todo`     | `<task name>`                                   |
| `deadline` | `<task name> /by <dd/mm/yyyy hhmm>`                   |
| `event`    | `<task name> /from <dd/mm/yyyy hhmm> /by <dd/mm/yyyy hhmm>` |
| `find`     | `<pattern>`                                     |
| `mark`     | `<indices> [<indices>, ...]`                    |
| `unmark`   | `<indices> [<indices>, ...]`                    |
| `delete`   | `<indices> [<indices>, ...]`                    |
| `help`     | None                                            |
| `bye`      | None                                            |

# Features

## Adding ToDos: `todo`
Adds a todo to the list of tasks.

Format: `todo <task name>`

Example: `todo homework`, `todo wash clothes`

*Notes*
+ `<task name>` cannot be empty

## Adding Deadlines: `deadline`

Adds a deadline to the list of tasks.

Format: `deadline <task name> /by <dd/mm/yyyy hhmm>`

Example: `deadline project submission /by 01/01/2024 2359`

*Notes*
+ `<task name>` cannot be empty
+ date time information must be of the format `dd/mm/yyyy hhmm`

## Adding Events: `event`

Adds an event to the list of tasks.

Format: `event <task name> /from <dd/mm/yyyy hhmm> /to <dd/mm/yyyy hhmm>`

Example: `event exam /from 01/01/2024 1200 /to 01/01/2024 1400`

*Notes*
+ `<task name>` cannot be empty
+ date time information must be of the format `dd/mm/yyyy hhmm`
+ The start cannot occur after the end

## Listing Tasks: `list`

Displays all tasks currently tracked.

Format: `list`

## Finding Tasks: `find`

Displays all tasks with details matching a pattern.

Format: `find <pattern>`

Example: `find wash clothes`

## Marking Tasks: `mark`

Marks a task as complete.

Format: `mark <indice> [<indices>, ...] `

Example: `mark 1 3-5`

*Notes*
+ Arguments are space separated single indices or ranges
+ Ranges must be of the form `<index>-<index>`
+ Tasks already marked as done are unchanged
+ Indices outside the range of valid indices are ignored
+ There must be at least 1 valid index

## Unmarking Tasks: `unmark`

Marks a task as incomplete.

Format: `unmark <indices> [<indices>, ...]`

Example: `unmark 2-4 6 8-11`

*Notes*
+ Arguments are space separated single indices or ranges
+ Ranges must be of the form `<index>-<index>`
+ Tasks already marked as undone are unchanged
+ Indices outside the range of valid indices are ignored
+ There must be at least 1 valid index

## Deleting Tasks: `delete`

Removes a task from the list.

Format: `delete <indices> [<indices>, ...]`

Example: `delete 3 5 9-11`

*Notes*
+ Arguments are space separated single indices or ranges
+ Ranges must be of the form `<index>-<index>`
+ Indices outside the range of valid indices are ignored
+ There must be at least 1 valid index

## Show List of Commands: `help`

Lists the valid commands.

Format: `help`

*Notes*
+ This is automatically executed on invalid inputs

## Closing the Application: `bye`

Saves the tasks and closes the application.

Format: `bye`

*Notes*
+ This is equivalent to pressing the cross on the GUI

# Saving Data

The application automatically saves the list of tasks to `./data/earl.txt`
in the same directory it is executed from. Note that moving the `.jar` file
will require the saved file to be moved as well for the list to persist between
sessions. The saved file may be edited directly to change any existing entry.

> Warning: if a saved entry is malformed as a result of a wrongful edit, the
> application may drop the saved data entirely. Make sure to create a backup
> before attempting any edits.

# Known Issues

+ The GUI window cannot be resized due to current limitations in adapting the 
display to changes in window size
+ There are some differences in text formatting between the CLI and GUI; this
is purely an aesthetic issue, the functionality is unaffected