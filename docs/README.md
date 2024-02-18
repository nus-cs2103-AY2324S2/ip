# FishStock User Guide

<img src="Ui.png" width="400">

FishStock is a **desktop app for tracking tasks, optimized for use via a Command Line Interface (CLI)** while still having
the benefits of a Graphical User Interface (GUI). If you can type fast, FishStock can get your task management done faster
than traditional GUI apps.

## Quick start
1. Ensure you have **Java 11** installed in your Computer.
2. Download the latest `fishstock.jar` from [here](https://github.com/bryanyee33/ip/releases).
3. Open a terminal within the folder you saved `fishstock.jar` in.
4. Type `java -jar fishstock.jar` into the terminal.
<p>&nbsp;</p>

## Command Summary
| Action              | Syntax                                                |
|---------------------|-------------------------------------------------------|
| Add: ***Todo***     | `todo <description>`                                  |
| Add: ***Deadline*** | `deadline <description> /by <datetime>`               |
| Add: ***Event***    | `event <description> /from <datetime> /to <datetime>` |
| Delete              | `delete <index>`                                      |
| Find                | `find <keyword>`                                      |
| Mark                | `mark <index>`                                        |
| Unmark              | `unmark <index>`                                      |
| Undo                | `undo`                                                |
| List                | `list`                                                |
| Help                | `help`                                                |
| Exit                | `bye`                                                 |

> `datetime` is of format `<dd/mm/yyyy hh:mm>`
> 
> `index` is based on the `list` order
<p>&nbsp;</p>

# Features
## Adding a Todo: `todo`
Adds a Todo to the list of tasks.

- Format: `todo <description>`
- Example: `todo borrow book`
<p>&nbsp;</p>

## Adding a Deadline: `deadline`
Adds a Deadline to the list of tasks.

- Format: `deadline <description> /by <datetime>`
  - `<datetime>` is of format `<dd/mm/yyyy hh:mm>`
- Example: `deadline return book /by 27/01/2024 17:15`
<p>&nbsp;</p>

## Adding an Event: `event`
Adds an Event to the list of tasks.

- Format: `event <description> /from <datetime> /to <datetime>`
  - `<datetime>` is of format `<dd/mm/yyyy hh:mm>`
- Example: `event project meeting /from 01/03/2024 10:00 /to 01/03/2024 12:00`
<p>&nbsp;</p>

## Deleting a task: `delete`
Removes a task from the list.

- Format: `delete <index>`
  - `<index>` is based on the `list` order.
- Example: `delete 3` deletes the 3rd task from the list.  
  
<p>&nbsp;</p>

## Finding a task: `find`
Searches for tasks in the list that contain a specified keyword in their description.

- Format: `find <keyword>`
  - The search is case-sensitive.
  - Only tasks that contain the exact keyword will be found.
- Example: `find book` finds all tasks with descriptions that contain "book".
<p>&nbsp;</p>

## Marking a task: `mark`
Marks a task as completed.

- Format: `mark <index>`
  - `<index>` is based on the `list` order.
- Example: `mark 1` marks the 1st task on the list as completed.
<p>&nbsp;</p>

## Unmarking a task: `unmark`
Marks a task as incomplete.

- Format: `unmark <index>`
  - `<index>` is based on the `list` order.
- Example: `unmark 2` marks the 2nd task on the list as incomplete.
<p>&nbsp;</p>

## Undoing commands: `undo`
Reverts the list to its previous state.

- Format: `undo`
  - `undo` can be done multiple times.
  - All previous states since app launch can be reverted to.
  - Only commands that potentially modify the list can be undone.
    - Eg. Adding tasks, deleting tasks, marking tasks, etc.
  - Commands that don't modify the list cannot be undone.
    - Eg. Showing the list, finding tasks, etc.
<p>&nbsp;</p>

## Listing tasks: `list`
Shows all tasks in the list.

- Format: `list`
<p>&nbsp;</p>

## Show commands: `help`
Show a list of all commands.

- Format: `help`
<p>&nbsp;</p>

## Exiting: `bye`
Exits the app.

- Format: `bye`
<p>&nbsp;</p>
