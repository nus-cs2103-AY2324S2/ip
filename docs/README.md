# Toothless User Guide

![Screenshot of Toothless application](./Ui.png)

Do you have a lot of tasks to keep track of? Toothless is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI). It is a chatbot that helps you keep track of your tasks, deadlines and events. If you can type fast, Toothless can help you keep track of your tasks faster than traditional GUI apps.

## Adding todos: `todo` 

This command adds a todo task to the task list.

> Format: `todo <task description>`

Example: `todo read book`

```
Toothless adds this task:
[T][ ] read book
Human has N tasks now.
```
where `N` is the number of tasks currently in the list.

## Adding deadlines: `deadline`

This command adds a deadline task to the task list.

> Format: `deadline <task description> /by <date>`
> 
> Do note that the `<date>` should be in the format `YYYY-MM-DDThh:mm`

Example: `deadline essay assignment /by 2024-02-20T22:00`

```
Toothless adds this task:
[D][ ] essay assignment (by: Feb 20 2024 22:00)
Human has N tasks now.
```
where `N` is the number of tasks currently in the list.

## Adding events: `event`

This command adds an event task to the task list.

> Format: `event <task description> /from <date> /to <date>`
>
> Do note that the `<date>` should be in the format `YYYY-MM-DDThh:mm`

Example: `event project meeting /from 2024-02-20T22:00 /to 2024-02-20T23:00`

```
Toothless adds this task:
[E][ ] project meeting (from: Feb 20 2024 22:00 to: Feb 20 2024 23:00)
Human has N tasks now.
```
where `N` is the number of tasks currently in the list.

## List out all tasks: `list`

This command lists out all the tasks in the task list.

> Format: `list`

Example: `list`

```
Here all human tasks:
1. [T][ ] read book
2. [D][ ] essay assignment (by: Feb 20 2024 22:00)
3. [E][ ] project meeting (from: Feb 20 2024 22:00 to: Feb 20 2024 23:00)
```

## View tasks in the form of a schedule: `schedule`

This command lists out all the tasks in the task list in the form of a schedule.

> Format: `schedule`
>

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

## Find tasks by keyword: `find`

This command finds tasks in the task list that contain the keyword.

> Format: `find <keyword>`
> 

The keyword is case-sensitive.

The keyword can be a substring of the task description.

Example: `find book`

```
Toothless have found these same tasks:
1. [T][ ] read book
```

## Mark a task as done: `mark`

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

## Mark a task as not done: `unmark`

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

## Delete a task: `delete`

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

## Goodbye Toothless: `bye`

This command exits the application.

> Format: `bye`

Example: `bye`

```
Toothless will miss you, human. Toothless will be here when you need Toothless.
```

## Saving the data

Toothless data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Command summary


| Action           | Format, Examples                                                                                                                  |
|------------------|-----------------------------------------------------------------------------------------------------------------------------------|
| Add todo         | `todo <task description>` <br> e.g., `todo read book`                                                                             |
| Add deadline     | `deadline <task description> /by <date>` <br> e.g., `deadline essay assignment /by 2024-02-20T22:00`                              |
| Add event        | `event <task description> /from <date> /to <date>` <br> e.g., `event project meeting /from 2024-02-20T22:00 /to 2024-02-20T23:00` |
| List             | `list`                                                                                                                            |
| Schedule         | `schedule`                                                                                                                        |
| Find             | `find <keyword>` <br> e.g., `find book`                                                                                           |
| Mark as done     | `mark <task number>` <br> e.g., `mark 1`                                                                                          |
| Mark as not done | `unmark <task number>` <br> e.g., `unmark 1`                                                                                      |
| Delete           | `delete <task number>` <br> e.g., `delete 1`                                                                                      |
| Exit             | `bye`                                                                                                                             |

## Acknowledgements

Toothless is based on the Duke project created by the [SE-EDU initiative](https://se-education.org). It is a project that is part of the CS2103T Software Engineering module at the National University of Singapore. It is created by the following contributors:
