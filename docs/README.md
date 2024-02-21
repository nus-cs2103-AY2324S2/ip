# Mean-Duke User Guide

![Product Screenshot](/Ui.png)

Mean-Duke is a **desktop app for keeping track of tasks, optimized for use via a Command Line Interface (CLI)**, but still
having a cute Graphical User Interface (GUI) to enhance user experience. If you often need to keep track of your tasks,
Mean-Duke can help you do so in a fast and efficient way. However, be warned that as its name suggests, Mean-Duke can be
quite mean sometimes.

## Features

Notes about the command format:
- Parameters in angle brackets `<PARAMETER>` are compulsory
- Parameters in square brackets `[HH:MM]` are optional
- For greater efficiency, shortcut syntax for commands and task types are available
  - Example: `a t A short todo` is equivalent to `add todo A short todo`
  - Example: `l` is equivalent to `list`

<br/>

### Adding Tasks: `add`
Mean-Duke supports adding the following types of tasks:

<br/>

#### ToDos
A task with a simple description.

Format: `add todo <DESCRIPTION>`

Example: 
- `add todo Take out the trash`
- `add todo Buy a new car`

After successfully adding a todo task, Mean-Duke will say:

```
Added ToDo task: Take out the trash
```

<br/>

#### Deadlines
A task with a simple description and a specific date/time that it must be completed by.

Format: `add deadline <DESCRIPTION> /by <YYYY-MM-DD> [HH:MM]`

Example:
- `add deadline Finish math homework /by 2024-04-12`
- `add deadline Submit online assignment /by 2025-12-01 23:59`

After successfully adding a deadline task, Mean-Duke will say:

```
Added Deadline task: Submit online assignment (by: 2025-12-01 23:59)
```

<br/>

#### Event
A task with a simple description and a specific date/time that it begins and end at.

Format: `add event <DESCRIPTION> /from <YYYY-MM-DD> [HH:MM] /to <YYYY-MM-DD> [HH:MM]`

Example:
- `add event Summer holiday /from 2024-04-30 /to 2024-08-11`
- `add event John's birthday party /from 2024-07-05 10:00 /to 2024-07-05 16:00`

After successfully adding a Event task, Mean-Duke will say:

```
Added Event task: John's birthday party 
(from: 2024-07-05 10:00
   to: 2024-07-05 16:00)
```
<br/><br/>

### Listing all tasks: `list`
Shows a list of all tasks, including their type and completion status.

Format: `list`

Example output of a list with some tasks:

```
1. [T][ ] Take out the trash
2. [D][X] Submit online assignemnt (by: 1 DECEMBER 2025 23:59)
3. [E][ ] John's birthday party (5 JULY 2024 10:00 - 5 JULY 2024 16:00)
```
`[T]`, `[D]`, and `[E]` represents todos, deadlines, and events respectively.

`[X]` indicates that the task has been marked as completed using the `mark` command.

<br/>

### Marking and unmarking a task as complete: `mark`, `unmark`
`mark` Marks a task as complete. `unmark` undos this. 
Refer to `list` section for what marking a task as done does.

Format: `mark <TASK_NUMBER>`, `unmark <TASK_NUMBER>`, where `TASK_NUMBER` is the corresponding number beside the task when you do `list`.

<br/>

### Searching for a task: `find`
Filters the list of task for tasks that contain a specific word or phrase.

Format: `find <PHRASE>`

<br/>

### Deleting a task: `delete`
Delete a task from the list. (**Warning**: cannot be undone)

Format: `delete <TASK_NUMBER>`, where `TASK_NUMBER` is the corresponding number beside the task when you do `list`.

Example: `delete 1`

Upon successfully deleting a task, Mean-Duke says the following:
```
deleted task:
[T][ ] Take out the trash
```

<br/>

### Exiting the app: `end`
Quits the app. Equivalent to using the cross button.
