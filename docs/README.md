# Waffles User Guide
Hi, I'm Waffles, your personal task management assistant!

- [Quick Start](#quick-start)
- [Features](#features)
  - [1. List tasks](#1-list-all-tasks)
  - [2. Add ToDo task](#2-add-todo-task)
  - [3. Add Deadline task](#3-add-deadline-task)
  - [4. Add Event task](#4-add-event-task)
  - [5. Mark task](#5-mark-task)
  - [6. Unmark task](#6-unmark-task)
  - [7. Delete task](#7-delete-task)
  - [8. Find task](#8-find-task)
  - [9. Exit](#9-exit)

## Quick Start
1. Ensure you have Java 11 or above installed in your computer.
2. Download the latest jar file from [here](https://github.com/shawnnlimm/ip/releases).
3. From your terminal, run the program with `java -jar Waffles.jar`.

## Features
ℹ️ Command format note: Words enclosed with <> are to be specified by the user!

Below are the list of commands that can be entered by the user.

### 1. List all tasks
Shows the current task list.

Format: `list`

Expected output:
```
Here are the tasks in your list:
1.[T][ ] go to the gym
```

### 2. Add ToDo task
Adds a ToDo task to the list.

Format: `todo <task description>`

Example: `todo complete homework`

Expected output:
```
Got it. I've added this task: 
[T][ ] complete homework
Now you have 1 task in the list.
```

### 3. Add Deadline task
Adds a Deadline task to the list.

Format: `deadline <task description> /by <date> <time>`

Example: `deadline complete homework /by 2024/04/04 2200`

- `<date>` must be in the format `yyyy/MM/dd`.
- `<time>` must be in the format `HHmm`.

Expected output:
```
Got it. I've added this task: 
[D][ ] complete homework (by: Sun 2200 04/04/2024)
Now you have 1 task in the list.
```

### 4. Add Event task
Adds an Event task to the list

Format: `event <task description> /from <start date> <start time> /to <end date> <end time>`

Example: `event birthday party /from 04/04/2024 2000 /to 04/04/2024 2200`

- `<start date>` and `<end date>` must be in the format `yyyy/MM/dd`.
- `<start time>` and `<end time>` must be in the format `HHmm`.
- 
Expected output:
```
Got it. I've added this task: 
[E][ ] birthday party (from: Thu 2000 04/04/2024 to: Thu 2200 04/04/2024)
Now you have 1 task in the list.
```

### 5. Mark task
Marks a task as completed using the index of the task in the list.

Format: `mark <task index>`

Example: `mark 1`

Expected output:
```
Nice! I've marked this task as done:
[T][X] complete homework
```

### 6. Unmark task
Un-marks a task as un-completed using the index of the task in the list.

Format: `unmark <task index>`

Example: `unmark 1`

Expected output:
```
OK, I've marked this task as not done yet:
[T][ ] complete homework
```

### 7. Delete task
Deletes a task from the list using the index of the task in the list.

Format: `delete <task index>`

Example: `delete 1`

Expected output:
```
Noted. I've removed this task: 
[T][ ] complete homework
Now you have 0 tasks in the list.
```

### 8. Find task
Finds a task in the list using one or more keywords.

Format: `find <keywords>`

Example: `find complete`

Expected output:
```
Here are the matching tasks in your list:
1.[T][ ] complete homework
```

### 9. Exit
Exits the chat with Waffles.

Format: `bye`

Example: `bye`

Expected output:
```
Bye! Hope to see you again soon!
```
