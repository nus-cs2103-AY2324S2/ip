# KaiYap Task Manager

Welcome to **KaiYap Task Manager**, a straightforward and efficient tool designed to help you keep track of your tasks, deadlines, and events. Whether you need to manage your daily to-dos, keep up with important deadlines, or schedule events, KaiYap makes it easy and intuitive.

___
## Features

KaiYap Task Manager comes packed with features designed to help you stay organized and efficient. Here are some of the key features:

- **Task Management:** Easily add, delete, and mark tasks as completed.
- **Multiple Task Types:** Support for Todos, Deadlines, and Events, each with customisable details.
- **Batch Operations:** Mark multiple tasks as done with a single command.
- **Search Functionality:** Quickly find tasks by searching for keywords.
- **Persistence:** Save your task list between sessions so you never lose track of your responsibilities.
___

## Adding Tasks

To add a task to your list, simply use the command associated with the type of task you want to add. KaiYap supports three types of tasks: Todos, Deadlines, and Events.

### Adding Todos

A Todo is a task without a specific deadline.

**Example:** `todo Read a book`

**Expected Outcome:**

```
_______________________________________
Got it. I've added this task:
    [T][] Read a book
You now have X tasks in the list.
_______________________________________
```
___
### Adding Deadlines

A Deadline is a task that needs to be completed by a certain date and time. The deadline is expected in dd/MM/yyyy HH:mm format. 

**Example:** `deadline Submit assignment /by 17/02/2024 2359`

**Expected Outcome:**

```
_______________________________________
Got it. I've added this task:
    [D][] Submit assignment (by: 17/02/2024 23:59)
You now have X tasks in the list.
_______________________________________
```
___
### Adding Events

An Event is a task that occurs at a specific time.

**Example:** `event Project meeting /from 12/02/2024 1400 /to 12/02/2024 1600`

**Expected Outcome:**
```
_______________________________________
Got it. I've added this task:
    [E][] Project meeting (from: 12/02/2024 14:00 to: 12/02/2024 16:00)
You now have X tasks in the list.
_______________________________________
```
___
## Listing Tasks

To view all your tasks, simply enter the command `list`.

**Example:** `list`

**Expected Outcome:**

```
_______________________________________
Here are the tasks in your list:
1. [T][] Read a book
2. [D][] Submit assignment (by: 17/02/2024 23:59)
3. [E][] Project meeting (from: 12/02/2024 14:00 to: 12/02/2024 16:00)
_______________________________________
```
___
## Marking Tasks as Done

To mark a task as done, use the `mark` command followed by the task number(s).

**Example:** `mark 1 2`

**Expected Outcome:**
```
_______________________________________
Nice! I've marked these tasks as done:
    [T][X] Read a book
    [D][X] Submit assignment (by: 17/02/2024 23:59)
_______________________________________
```
---
## Deleting Tasks

To delete a task from your list, use the `delete` command followed by the task number.

**Example:** `delete 1`

**Expected Outcome:**
```
_______________________________________
Noted. I've removed this task:
    [T][X] Read a book
You now have 2 tasks in the list.
_______________________________________
```
___
## Finding Tasks

To search for tasks containing a specific keyword, use the `find` command followed by the keyword.

**Example:** `find assignment`

**Expected Outcome:**
```
_______________________________________
Here are the matching tasks in your list:
1. [D][X] Submit assignment (by: 17/02/2024 23:59)
_______________________________________
```
---
*This guide provides a basic overview of using KaiYap Task Manager. Explore further to discover more functionalities and customize your task management experience.*
