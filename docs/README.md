# UkeCat

(https://ziiqii.github.io/ip/Ui.png)

**UkeCat** is a desktop app for managing your tasks, optimized for use via a Command Line Interface (CLI) 
while retaining the benefits of a Graphical User Interface (GUI).
If you can type fast, UkeCat can get help you view and update your tasks in a flash.

- [Quickstart](#quickstart)
- [Features](#features)
    * [Adding todos](#adding-todos-todo)
    * [Adding deadlines](#adding-deadlines-deadline)
    * [Adding events](#adding-events-event)
    * [Adding recurring tasks](#adding-recurring-tasks-recur)
    * [Deleting tasks](#deleting-tasks-delete)
    * [Marking tasks](#marking-tasks-mark-or-unmark)
    * [Displaying tasks](#displaying-tasks-list)
    * [Finding tasks containing keyword](#finding-task-by-keyword-find)
    * [Exiting the program](#exiting-the-program-bye)
    * [Saving the data](#saving-the-data)


## Quickstart
1. Ensure you have Java `11` or above installed in your Computer.
1. Download the latest UkeCat.jar from [here](https://github.com/ziiqii/ip/releases).
1. Copy the file to the folder you want to use as the _home folder_ for UkeCat.
1. Open a command terminal, cd into the folder you put the jar file in and use the java -jar UkeCat.
   jar command to run the application.
1. Refer to the [Features](#features) below for details of each command.


## Features
In this program, each of the following is a type of task:
* todos
* deadlines
* events
* recurring tasks

## Adding todos: `todo`
Adds a todo to the task list.

A todo has the following properties:
* Represented by `[T]` in the task list.

Format: `todo Description`
* `DESCRIPTION` cannot be blank and can be any value.

Example: `todo order dinner`

## Adding deadlines: `deadline`
Adds a deadline to the task list.

A deadline has the following properties:
* A deadline has only an end date.
* Represented by `[D]` in the task list.

Format: `deadline DESCRIPTION /by DATE`
* `DESCRIPTION` cannot be blank and can be any value.
* `DATE` cannot be blank and must be in the format YYYY-MM-DD.

Example: `deadline submit assignment /by 2024-02-23`

## Adding events: `event`
Adds an event to the task list.

An event has the following properties:
* An event has both start and end dates.
* Represented by `[E]` in the task list.

Format: `event DESCRIPTION /from DATE /to DATE`
* `DESCRIPTION` cannot be blank and can be any value.
* Both of the `DATE` cannot be blank and must be in the format YYYY-MM-DD.

Example: `event hackathon /every 2024-07-12 /to 2024-07-15`

## Adding recurring tasks: `recur`
Adds a recurring task to the task list.

A recurring task has the following properties:
* Represented by `[RD]`, `[RW]` or `[RM]` in the task list.
* A countdown is displayed alongside the recurring task's description.
* Initially, the countdown is calculated from date of creation.
* Upon opening the program, every saved recurring task is checked against the current date:
  * If the current date is ahead of the expiry date, the recurring task will be unmarked, and a new 
    countdown will be generated based on creation date.

Format: `recur DESCRIPTION /every RECURTYPE`
* `DESCRIPTION` cannot be blank and can be any value.
* `RECURTYPE` cannot be empty, and can only be one of these values:
    * day
    * week
    * month

Examples:
* `recur feed cat /every day`
* `recur gym /every week`

## Deleting tasks: `delete`
Deletes the specified task from the task list.

Format: `delete INDEX`
* Deletes the task at the specified `INDEX`
* The index refers to the index number shown in the task list.
* The index **must be a positive integer** 1, 2, 3, …

Example: `delete 2` deletes the 2nd task in the task list.

## Marking tasks: `mark` or `unmark`
Marks or unmarks the specified task in the task list.

* Marked tasks are represented with `[x]`
* Unmarked tasks are represented with `[ ]`

Format: `mark / unmark INDEX`
* Marks or unmarks the task at the specified `INDEX`
* The index refers to the index number shown in the task list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `mark 2` marks the 2nd task in the task list.
* `unmark 3` unmarks the 3rd task in the task list.

## Displaying tasks: `list`
* Shows a list of all tasks in the task list.

Format: `list`

## Finding task by keyword: `find`
Finds tasks whose descriptions contain the given keyword.

Format: `find KEYWORD`
* Currently only supports finding with one keyword.
* The search is case-insensitive, e.g. `egg` will match `BUY EGG`
* Only the description is searched.
* Partial words will be matched, e.g. `ook` will match both `cook` and `look`
* Tasks matching the keyword will be listed.

Example: `find egg` returns `buy eggs` and `cook steamed egg`

## Exiting the program: `bye`
Exits the program.

Format: `bye`

## Saving the data
Tasks are saved in the hard disk automatically after any command that changes the data.

There is no need to save manually.