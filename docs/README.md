# PepegPro User Guide

![alt text](./Ui.png)

PepegPro is a **chatbot app for managing task via a Command Line Interface** (CLI)
while still having the benefits of a Graphical User Interface (GUI).

- Features
    - [Show list of tasks](#show-all-tasks) : list
    - [Mark a task as done](#mark-a-task) : mark
    - [Add a todo task](#adding-todos) : todo
    - [Add a deadline task](#adding-deadlines) : deadline
    - [Add an event task](#adding-events) : event
    - [Delete a task](#delete-a-task) : delete
    - [Find list of tasks with keyword](#show-a-list-of-task-with-keyword) : find
    - [Make a deadline or event task recurrable](#make-a-task-recurrable) : recur
    - [Close the chatbot](#close-the-chatbot) : bye 

## Show all tasks

Shows all tasks in the task list

Format: ```list```

## Mark a task

Mark a task as done in the task list

Format: ```mark INDEX```

Example:
- ```mark 1```

```
Nice! I've marked this task as done:
[T][X] Buy groceries
```

## Adding todos

Adds a todo task to the task list.

Format: ```todo TASK_DESCRIPTION```

Example:
- ```todo read book```

```
Got it. I've added this task:
[T][ ] read book
Now you have 7 tasks in the list.
```

## Adding deadlines

Adds a deadline task to the task list.

Format: ```deadline TASK_DESCRIPTION /by DATE_TIME```

Example: 
- ```deadline read book /by 2024-02-21 1800```

```
Got it. I've added this task:
[D][ ][ ] read book (by: Feb 21 2024 6:00PM)
Now you have 6 tasks in the list.
```

## Adding events

Adds am event task to the task list.

Format: event TASK_DESCRIPTION /from DATE_TIME /to DATE_TIME

Example:
- ```event read book /from 2024-02-21 1800 /to 2024-02-21 2100```

```
Got it. I've added this task:
[E][ ][ ] read book (from: Feb 21 2024 6:00PM to: Feb 21 2024 9:00PM)
Now you have 7 tasks in the list.
```

## Delete a task

Delete a task in the task list

Format: ```delete INDEX```

Example:
- ```delete 7```

```
Noted. I've removed this task:
[E][R][ ] read book (from: Feb 21 2024 6:00PM to: Feb 21 2024 9:00PM)
Now you have 6 tasks in the list.
```

## Show a list of task with keyword

Show a list of all tasks in the task list with the keyword

Format: ```find KEYWORD```

Example:
- ```find book```

```
Here are the tasks in your list:
1. [D][R][ ] Read a book (by: May 10 2023 6:00PM)
2. [D][R][X] Borrow a book (by: May 15 2023 6:00PM)
```

## Make a task recurrable

Make a task recurrable in the task list

Format: ```recur INDEX```

Example:
- ```recur 6```

```
Nice! I've made this task recur:
[E][R][ ] do assignment (from: Feb 21 2024 6:00PM to: Feb 21 2024 9:00PM)
```

## Close the chatbot

Format: ```bye```

```
Bye. Hope to see you again soon!
```

## Load

Data saved from previous session is loaded automatically

## Saving the data

Task list data are saved in the hard disk automatically

### Command summary

| Action                                 | Format                                       |
|----------------------------------------|----------------------------------------------|
| Show list of tasks                     | `list`                                       |
| Mark a task as done                    | `mark INDEX`                                 |
| Add a todo task                        | `todo TASK_DESCRIPTION`                      |
| Add a deadline task                    | `deadline TASK_DESCRIPTION /by DATE_TIME`   |
| Add an event task                      | `event TASK_DESCRIPTION /from DATE_TIME /to DATE_TIME` |
| Delete a task                          | `delete INDEX`                               |
| Find list of tasks with keyword        | `find KEYWORD`                               |
| Make a deadline or event task recurrable| `recur INDEX`                                |
| Close the chatbot                       | `bye`                                        |

