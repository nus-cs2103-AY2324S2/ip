# Lulu Task Manager 🤖

Lulu Task Manager is a simple command-line GUI task manager written in Java that allows you to keep track of your tasks with ease!

## Introduction

Lulu Task Manager allows users to add, mark, unmark, and delete tasks, as well as perform queries based on specific criteria. The application utilizes a command-line interface for a straightforward user experience.

## Features

## Adding Tasks

You can add new tasks to your task list with the command associated with the type of the task. Three types of tasks are currently supported: Todos, Deadlines, and Events.
___
### Adding Todos

Todos is the most basic task.

**Example:** `todo example_todo`

**Expected Outcome:**

```
Gotcha. I've added this task:
    [T][ ] example_todo
Now you have 1 tasks in the list.
```
___
### Adding Deadlines

Deadlines is a task with an end date.

**Example:** `deadline example_deadline /by 2024-03-04`

**Expected Outcome:**

```
Gotcha. I've added this task:
    [D][ ] example_deadline (by: 4 March 2024)
Now you have 1 tasks in the list.
```
___
### Adding Events

Events is a task with a start date and an end date.

**Example:** `event example_event /from 2024-01-02 /to 2024-03-04`

**Expected Outcome:**
```
Gotcha. I've added this task:
    [E][ ] example_event (from: 2 January 2024 to: 4 March 2024)
Now you have 1 tasks in the list.
```
___
## Managing Tasks

Lulu task manager allows you to manipulate and manage your tasks. Examples include them marking or unmarking them as done and deleting.

### List Tasks

Allows you to view all your tasks.

**Example:** `list`

**Expected Outcome:**

```
Here are the tasks in your list:
1.[T][ ] example_todo
2.[D][ ] example_deadline (by: 4 March 2024)
3.[E][ ] example_event (from: 2 January 2024 to: 4 March 2024)
```
___
### Marking Tasks as Done

Allows you to mark tasks as done for given index(s).

**Example:** `mark 1 2`

**Expected Outcome:**
```
Nice! I've marked this task as done:
[T][X] example_todo
Nice! I've marked this task as done:
[D][X] example_deadline (by: 4 March 2024)
```
___
### Unmarking Tasks as Done

Allows you to unmark tasks as done for given index(s).

**Example:** `unmark 1 2`

**Expected Outcome:**
```
OK, I've marked this task as not done yet:
[T][ ] example_todo
OK, I've marked this task as not done yet:
[D][ ] example_deadline (by: 4 March 2024)
```
---
### Deleting Tasks

Allows you to delete tasks as done for given index(s).

**Example:** `delete 1`

**Expected Outcome:**
```
Noted. I've removed this task:
    [T][ ] example_todo
Now you have 2 tasks in the list.
```
___
## Querying Tasks

Lulu task manager also allows you to query tasks based on certain characteristics.
___
### Finding Tasks

Allows you to search tasks whose name contains a given string.

**Example:** `find deadline`

**Expected Outcome:**
```
Here are the matching tasks in your list:
1.[D][ ] example_deadline (by: 4 March 2024)
2.[D][ ] deadline1 (by: 6 May 2024)
```
---
### Querying Tasks

Allows you to query tasks of a given type who match a given time.

**Example:** `query event 2024-02-01`

**Expected Outcome:**
```
Below are events that are operating on 1 February 2024
[E][X] example_event (from: 2 January 2024 to: 4 March 2024)
```
---
*This guide provides a basic overview of using Lulu Task Manager.*


## Installation

This is Lulu `Lulu chatbot = new Lulu();`.

```java
public static void main(String[] args) {
        Lulu chatbot = new Lulu();
        UI.start();
        try {
        chatbot.respond();
        } catch (Exception e) {
        System.out.println(e.getMessage());
        }
        UI.exit();
        }
```