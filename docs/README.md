# Venus User Guide

Venus is a **desktop app for managing events** you have planned for yourself.
It supports both **GUI and CLI** although the `GUI is more recommended`.
The `GUI` is the newest update and the CLI is a `legacy version`.

![Product Image](/docs/Ui.png)

- [Quick Start](#quick-start)
- Features
    - [Adding Todo to track: `Todo`](#adding-todo)
    - [Adding Deadline task to track `Deadline`](#adding-deadline)
    - [Adding Event to track `Event`](#adding-event)
    - [Listing out all tasks tracked `List`](#list-all-tasks)
    - [Mark a task to be done `Mark`](#mark-a-task-as-done)
    - [Mark a task to be not done `Unmark`](#mark-a-task-as-not-done)
    - [Delete a task by index `Delete`](#delete-a-task)
    - [Find tasks by keyword `Find`](#find-tasks-by-keyword)
    - [Find duplicated tasks `duplicate` (GUI only)](#find-duplicated-tasks-in-the-list)
- [Saving the data](#saving-the-data)
- [Editing the data](#editing-the-data-file)
- [Command Summary](#command-summary)

## Quick Start
1. Ensure you have Java `11` installed on your Computer. For mac users, ensure that your Java 11 version is `Azul Zulu version 11.0.22`
2. Download the latest copy of Jar from releases in this repo, open it.
3. Build it and it should be able to run.
4. For `GUI`, download the latest Jar release and launch it with `java -jar venus.jar`.
5. For `CLI`, compile the source code in a project setting and launch it from java file `Venus`.
6. Type command in the command box, and press enter to proceed. You can try the following commands
- `list`: List all the tasks.
- `event /from 2020-01-01 /to 2020-01-02`: Adds an event from 2020 1st Jan to 2020 2nd Jan to be tracked.
- `delete 5` delete the fifth item in the list of events tracked, by the current index.

## Adding Todo

Add a Todo task to the venus task tracker.

ote that a new Todo is marked as not done by default.

Format `Todo TASK`

Example: `Todo play tennis`

This will add a `play tennis` Todo task for venus to track.

```
expected output

Got it, I have added this task:
  [T][]play tennis
Now you have {total task number} tasks in the list.
```

## Adding Deadline
Add a task with deadline to the venus task tracker. 

Note that a new deadline is marked as not done by default.

Format `Deadline TASK /by YYYY-MM-DD`

Example: `Deadline finish work /by 2020-02-02`

This will add a `finish work` Deadline task for venus to track.

```
expected output

Got it, I have added this task:
  [D][]finish work
Now you have {total task number} tasks in the list.
```
## Adding Event
Add an Event task with `start date` and `end date` to the venus task tracker.

Note that a new event is marked as not done by default.

Format `Event TASK /from YYYY-MM-DD /to YYYY-MM-DD`

Example: `Event A meeting /from 2020-02-03 /to 2020-02-04`

This will add a `A meeting` Event task for venus to track.

```
expected output

Got it, I have added this task:
  [E][]A meeting(from:Feb 2 2020 to:Feb 3 2020
Now you have {total task number} tasks in the list.
```
## List all tasks
List all the `task` tracked by Venus right now.

Format `List`

Example: `List`

This will list out all the tasks currently tracked by venus.

```
expected output

Here are the tasks in your list:
  1.[T][X] Todo 
  2.[D][] Deadline
  3.[E][] Event
  4.[T][] Lorem ipsum
```

## Mark a task as done
Mark a `task` as done by its index indicated by the `list` command.

Format `Mark INDEX`

Example: `Mark 1`

This will Mark the first task in the list tracked by Venus as done.

```
expected output (provided that the first task tracked by venus is work)

Nice, I have marked this task as done:
 [T][X] work
```

## Mark a task as not done
Unmark a `task` as not done by its index indicated by the `list` command.

Format `Unmark INDEX`

Example: `Unmark 1`

This will Unmark the first task in the list tracked by Venus as done.

```
expected output (provided that the first task tracked by venus is work)

Ok, I have marked this task as not done yet:
 [T][] work
```

## Delete a task
Delete a `task` by its index indicated by the `list` command, the index of original 
tasks in the `list` command will be refreshed.

Format `Delete INDEX`

Example: `Delete 2`

This will Delete the second task in the list tracked by Venus as done.

```
expected output (provided that the second task tracked by venus is play)

Noted, I have removed this task:
 [T][] play
Now you have {total task number} tasks in the list.
```

## Find tasks by keyword
Find if a `task` tracked by Venus using a keyword that is part of the task description.

Format `Find KEYWORD`

Example: `Find film`

This will Delete the second task in the list tracked by Venus as done.

```
expected output (provided that these tasks exist in your list)

Here are the matching tasks in your list:
1.[T][] Watch a film with my girlfriend
2.[E][] Finishing editing my film (from: 2020-02-20 to:2020-02-27)
```

## Find duplicated tasks in the list
Find if a `task` tracked by Venus contains duplicated tasks.

Format `Duplicate`

Example: `Duplicate`

This will find duplicated `task` currently tracked by Venus, if any.

```
expected output (provided that these tasks exist in your list)

Here are the duplicated tasks in your list:
1.[T][] Play video games
2.[D][] Play video games (by: Mar 3 2021)
```

## Saving the data
Tasks are automatically saved into the file after each user action, there is
no need to save manually.

## Editing the data file
Data is saved automatically as a [venus.txt](..%2Fdata%2Fvenus.txt)
Advanced users are welcome to update data. Also do take note that corrupted
data will cause issues with loading and saving.

# Command Summary
| Action                           | Format, example                                                                                                |
|----------------------------------|----------------------------------------------------------------------------------------------------------------|
| Add deadline task                | Format `Deadline TASK /by YYYY-MM-DD` Example: `Deadline finish work /by 2020-02-02`                           |
| Add event task                   | Format `Event TASK /from YYYY-MM-DD /to YYYY-MM-DD` Example: `Event A meeting /from 2020-02-03 /to 2020-02-04` |
| Add todo task                    | Format `Todo TASK` Example: `Todo play tennis`                                                                 |
| Listing all tasks tracked        | Format `List` Example: `List`                                                                                  |
| Mark a task as done              | Format `Mark INDEX` Example: `Mark 1`                                                                          |
| Mark a task as not done          | Format `Unmark INDEX` Example: `Unmark 1`                                                                      |
| Delete a task by index           | Format `Delete INDEX` Example: `Delete 2`                                                                      |
| Find tasks by a keyword          | Format `Find KEYWORD` Example: `Find film`                                                                     |
| Find duplicated tasks (GUI only) | Format `Duplicate` Example: `Duplicate`                                                                        |
