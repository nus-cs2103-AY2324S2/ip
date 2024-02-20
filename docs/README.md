# HAL9000 User Guide

![HAL9000 Screenshot](Ui.png)

HAL9000 is a Task Manager application that helps you keep track of your
tasks so that you don't have to! It is tailored to users who are familiar
with a CLI.

## Adding deadlines

Adds a deadline task to your task list.

Format: `deadline NAME /by BY-TIME`

Example: `deadline Math Homework /by 12-12-2021`

```
Got it. I've added this task: 
[D] [ ] Math Homework by: 12-12-2021 00:00
Now you have 1 tasks in list.
```

## Adding Events

Adds a event task to your task list.

Format: `event NAME /from FROM-TIME /to TO-TIME`

Example: `event Chemistry Homework /from 12-12-2021 /to 13-12-2021`

```
Got it. I've added this task: 
[E] [ ] Chemistry Homework from: 12-12-2021 00:00 to: 13-12-2021 00:00
Now you have 2 tasks in list.
```

## Adding Todos

Adds a todo task to your task list.

Format: `todo NAME`

Example: `todo Geography Homework`

```
Got it. I've added this task: 
[T] [ ] Geography Homework
Now you have 3 tasks in list.
```

## Deleting Tasks

Deletes a task from your task list.

Format: `delete INDEX`

Example: `delete 1`

```
I have deleted this:
[D] [ ] Math Homework by: 12-12-2021 00:00
```

## Marking Tasks

Marks a task on your task list.

Format: `mark INDEX`

Example: `mark 1`

```
I have marked this:
[E] [X] Chemistry Homework from: 12-12-2021 00:00 to: 13-12-2021 00:00
```

## Unmarking Tasks

Unmarks a task on your task list.

Format: `unmark INDEX`

Example: `unmark 1`

```
I have unmarked this:
[E] [ ] Chemistry Homework from: 12-12-2021 00:00 to: 13-12-2021 00:00
```
## Listing Tasks

Lists all tasks in your task list.

Format: `list`

Example: `list`

```
1. [E] [ ] Chemistry Homework from: 12-12-2021 00:00 to: 13-12-2021 00:00
2. [T] [ ] Geography Homework
```

## Searching Tasks

Finds a task in your task list.

Format: `find KEYWORD`

Example: `find Geography`

```
Here are your matching search results:
1. [T] [ ] Geography Homework
```


## Undoing Commands

Undoes the most recent command.

Format: `undo`

Example: `undo`

```
Your UNMARK command was undone!
This is your current list:
1. [E] [X] Chemistry Homework from: 12-12-2021 00:00 to: 13-12-2021 00:00
2. [T] [ ] Geography Homework
```

## Redoing Commands

Redoes the most recent command.

Format: `redo`

Example: `redo`

```
Your UNMARK command was redone!
This is your current list:
1. [E] [ ] Chemistry Homework from: 12-12-2021 00:00 to: 13-12-2021 00:00
2. [T] [ ] Geography Homework
```

## Exiting the Application
Exits and closes the application.

Format: `bye`

Example: `bye`

--- 
## Command summary

| **Action**              | **Format**                                 |
|-------------------------|--------------------------------------------|
| Adding Deadlines        | `deadline NAME /by BY-TIME`                |
| Adding Events           | `event NAME /from START-TIME /to END-TIME` |
| Adding Todos            | `todo NAME`                                |
| Deleting Tasks          | `list`                                     |
| Marking Tasks           | `mark INDEX`                               |
| Unmarking Tasks         | `unmark INDEX`                             |
| Listing Tasks           | `delete INDEX`                             |
| Searching tasks         | `find KEYWORD`                             |
| Undoing Commands        | `undo`                                     |
| Redoing Commands        | `redo`                                     |
| Exiting the Application | `bye`                                      |