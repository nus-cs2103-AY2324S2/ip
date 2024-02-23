# **Toothless User Guide**

Toothless is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI). It is a chatbot that helps you keep track of your tasks. 
It is ~~a simple~~ an easy-to-use task manager that is optimized for use via a CLI while still having the benefits of a Graphical User Interface (GUI). 

If you can type fast, Toothless can get your tasks managed faster than traditional GUI apps.

- [Quick Start](#quick-start)
- [Features](#features)
  - [Notes about the command format](#notes-about-the-command-format)
  - [Adding todos: `todo`](#adding-todos-todo)
  - [Adding deadlines: `deadline`](#adding-deadlines-deadline)
  - [Adding events: `event`](#adding-events-event)
  - [List out all tasks: `list`](#list-out-all-tasks-list)
  - [View tasks in the form of a schedule: `schedule`](#view-tasks-in-the-form-of-a-schedule-schedule)
  - [Find tasks by keyword: `find`](#find-tasks-by-keyword-find)
  - [Mark a task as done: `mark`](#mark-a-task-as-done-mark)
  - [Mark a task as not done: `unmark`](#mark-a-task-as-not-done-unmark)
  - [Delete a task: `delete`](#delete-a-task-delete)
  - [Goodbye Toothless: `bye`](#goodbye-toothless-bye)
- [Saving the data](#saving-the-data)
- [Command summary](#command-summary)
- [Acknowledgements](#acknowledgements)

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `toothless.jar` from [here]() to your computer.
3. Copy the file to the folder you want to use as the home folder for your Toothless.
4. Open a command terminal and `cd` to the home folder. Then run `java -jar toothless.jar` to start the app.
5. A GUI similar to the below should appear in a few seconds. Note that the GUI is not interactive and is for illustration purposes only.
![Screenshot of Toothless application](Ui.png)
6. Type the command in the command box and press Enter to execute it. e.g. typing `list` and pressing Enter will list all the tasks in the task list.

## Features

### Notes about the command format

> [!TIP]
> - Words in `<angle_brackets>` are the parameters to be supplied by the user.
    e.g. in `todo <task description>`, `<task description>` is a parameter which can be used as `todo read book`.
> - Commands with multiple parameters must be specified in the correct order.
  e.g. `event <task description> /from <date> /to <date>` must be specified in that exact order.
> - Extraneous parameters for commands that do not take in parameters (such as `list` and `bye`) will be ignored.
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.
> - Commands are case-insensitive.
  e.g. `todo` and `ToDo` are equivalent.
> - Commands must be mentioned in full.
  e.g. `t` is not a valid command.
> - Commands that are not recognized will be ignored.
  e.g. `blah` is not a valid command.
> - Commands with invalid parameters will be ignored.
  e.g. `todo` without a description will be ignored.
> - Commands use the 24-hour clock format for time.
  e.g. `22:00` is 10pm.
> - Commands do not support the use of the `|` character.


### Adding todos: `todo`

This command adds a todo task to the task list.

> Format: `todo <task description>`

Example: `todo read book`

```
Toothless adds this task:
[T][ ] read book
Human has N tasks now.
```
where `N` is the number of tasks currently in the list.

### Adding deadlines: `deadline`

This command adds a deadline task to the task list.

> Format: `deadline <task description> /by <date>`
> 
> Do note that the `<date>` should be in the format `YYYY-MM-DD HH:mm`

Example: `deadline essay assignment /by 2024-02-20 22:00`

```
Toothless adds this task:
[D][ ] essay assignment (by: Feb 20 2024 22:00)
Human has N tasks now.
```
where `N` is the number of tasks currently in the list.

### Adding events: `event`

This command adds an event task to the task list.

> Format: `event <task description> /from <date> /to <date>`
>
> Do note that the `<date>` should be in the format `YYYY-MM-DD HH:mm`

Example: `event project meeting /from 2024-02-20 22:00 /to 2024-02-20 23:00`

```
Toothless adds this task:
[E][ ] project meeting (from: Feb 20 2024 22:00 to: Feb 20 2024 23:00)
Human has N tasks now.
```
where `N` is the number of tasks currently in the list.

### List out all tasks: `list`

This command lists out all the tasks in the task list.

> Format: `list`

Example: `list`

```
Here all human tasks:
1. [T][ ] read book
2. [D][ ] essay assignment (by: Feb 20 2024 22:00)
3. [E][ ] project meeting (from: Feb 20 2024 22:00 to: Feb 20 2024 23:00)
```

### View tasks in the form of a schedule: `schedule`

This command lists out all the tasks in the task list in the form of a schedule.

> Format: `schedule`

The tasks are grouped by date.

The tasks are sorted by time within each date.

All tasks without a date are grouped under the date `No Date`.

Example: `schedule`

```
Tasks on Feb 20 2024:
1. [D][ ] essay assignment (by: Feb 20 2024 22:00)
2. [E][ ] project meeting (from: Feb 20 2024 22:00 to: Feb 20 2024 23:00)

Tasks without a date:
1. [T][ ] read book
```

### Find tasks by keyword: `find`

This command finds tasks in the task list that contain the keyword.

> Format: `find <keyword>`

The keyword is case-sensitive.

The keyword can be a substring of the task description.

Example: `find book`

```
Toothless have found these same tasks:
1. [T][ ] read book
```

### Mark a task as done: `mark`

This command marks a task as done.

> Format: `mark <task number>`
> 
> Do note that the `<task number>` should be a valid task number in the list.

The task number can be obtained by using the `list` command.

Example: `mark 1`

```
Toothless marks this task as done:
[T][X] read book
```

### Mark a task as not done: `unmark`

This command marks a task as not done.

> Format: `unmark <task number>`
> 
> Do note that the `<task number>` should be a valid task number in the list.

The task number can be obtained by using the `list` command.

Example: `unmark 1`

```
Toothless marks this task as not done:
[T][ ] read book
```

### Delete a task: `delete`

This command deletes a task from the task list.

> Format: `delete <task number>`
> 
> Do note that the `<task number>` should be a valid task number in the list.

The task number can be obtained by using the `list` command.

Example: `delete 1`

```
Toothless deletes this task:
[T][ ] read book
Human has N tasks now.
```
where `N` is the number of tasks currently in the list.

### Goodbye Toothless: `bye`

This command exits the application.

> Format: `bye`

Example: `bye`

```
Toothless will miss you, human. Toothless will be here when you need Toothless.
```

## Saving the data

Toothless data are saved in the hard disk automatically after the `bye` command. There is no need to save manually.

## Command summary


| Action           | Format, Examples                                                                                                                  |
|------------------|-----------------------------------------------------------------------------------------------------------------------------------|
| Add todo         | `todo <task description>` <br> e.g., `todo read book`                                                                             |
| Add deadline     | `deadline <task description> /by <date>` <br> e.g., `deadline essay assignment /by 2024-02-20 22:00`                              |
| Add event        | `event <task description> /from <date> /to <date>` <br> e.g., `event project meeting /from 2024-02-20 22:00 /to 2024-02-20 23:00` |
| List             | `list`                                                                                                                            |
| Schedule         | `schedule`                                                                                                                        |
| Find             | `find <keyword>` <br> e.g., `find book`                                                                                           |
| Mark as done     | `mark <task number>` <br> e.g., `mark 1`                                                                                          |
| Mark as not done | `unmark <task number>` <br> e.g., `unmark 1`                                                                                      |
| Delete           | `delete <task number>` <br> e.g., `delete 1`                                                                                      |
| Exit             | `bye`                                                                                                                             |

## Acknowledgements

Toothless is based on the Duke project created by the [SE-EDU initiative](https://se-education.org). It is a project that is part of the CS2103T Software Engineering module at the National University of Singapore.
