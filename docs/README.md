# Homie User Guide

![Ui.png](Ui.png)

Welcome to **Homie!**

Are you in need of a task-managing application where you can keep track of all your todo tasks, deadlines and events?
Then you have come to the right place! 

**Homie** is a desktop app designed for managing your daily tasks such as todo, events and deadlines, optimized for use 
via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type 
fast, **Homie** can can help you keep track of your tasks **faster** than traditional GUI apps.

Say goodbye to missing deadlines, forgetting what tasks to do, or events to go, or having to write down your tasks in
physical notebooks! Welcome to the world of efficient task handling where **Homie** will be your best buddy!

## Features

Here we explore all the features of **Homie!**

**Important notes about the features:**
- Words in curly braces `{}` and `UPPER_CASE` are the parameters to be supplied by the user. e.g. in `todo
{TODO_DESCRIPTION}`, `{TODO_DESCRIPTION}` is a parameter which can be used as `todo read book`.
- Date Time formats specified in curly braces `{}` will not be in `UPPER_CASE` as they have their specified format. e.g.
`{dd MM yyyy HHmm}` as MM represents Month but mm represents minutes.
- Commands are **case-insensitive** but parameters are **case-sensitive**. e.g. both `todo read book` and `TODO read 
book` will add a new todo task named 'read book' while `todo read BOOK` will add a new todo task named 'read BOOK'.
- After adding or deleting a task, **Homie** will remind you of the number of tasks remaining in your task list.
- If the command requires a parameter, the parameter **CANNOT** be empty, and format has to be followed closely.

### Adding todo

Adds a new todo task to the list of tasks.

**Formats:** 
- `todo {TODO_DESCRIPTION}`
- `t {TODO_DESCRIPTION}`

**Notes:**
- `{TODO_DESCRIPTION}` **cannot** be empty. Empty `{TODO_DESCRIPTION}` will raise an exception and **no tasks** will be
created.

**Examples:** 
- `todo read book`
- `t read book`

**Expected outcome**
```
Got it. I've added this task:
  [T][] read book
Now you have 1 tasks in the list.
```

### Adding deadlines

Adds a new deadline task to the list of tasks.

**Formats:**
- `deadline {DEADLINE_DESCRIPTION} /by {DD MM YYYY HHmm}`
- `dead {DEADLINE_DESCRIPTION} /by {DD MM YYYY HHmm}`

**Notes:**
- `{DEADLINE_DESCRIPTION}` cannot be empty. Empty `{DEADLINE_DESCRIPTION}` will raise an exception and **no tasks**
will be created. 
- `{DD MM YYYY HHmm}` must be in the correct format as specified with **no missing values**. 
- **Incorrect formats** or **any missing values** will cause an exception and **no tasks** will be created.
- Format of the output date time will be in `{MM-DD-YYYY HH:mm}` e.g. `02-24-2024 23:59`

**Examples:**
- `deadline CS2103 Quiz 3 /by 24 02 2024 2359`
- `dead CS2103 Quiz 3 /by 24 02 2024 2359`

**Expected outcome:**
```
Got it. I've added this task:
  [D][] CS2103 Quiz 3 (by: 02-24-2024 23:59)
Now you have 1 tasks in the list.
```

### Adding event

Adds a new event task to the list of tasks.

**Formats:** 
- `event {EVENT_DESCRIPTION} /from {DD MM YYYY HHmm} /to {DD MM YYYY HHmm}`
- `e {EVENT_DESCRIPTION} /from {DD MM YYYY HHmm} /to {DD MM YYYY HHmm}`

**Notes:**
- `{EVENT_DESCRIPTION}` cannot be empty. Empty `{EVENT_DESCRIPTION}` will raise an exception and no tasks will be
created.
- `{DD MM YYYY HHmm}` must be in the correct format as specified with **no missing values**.
- **Incorrect formats** or **any missing values** will cause an exception and **no tasks** will be created.
- Format of the output date time will be in `{MM-DD-YYYY HH:mm}` e.g. `02-24-2024 23:59`

**Examples:**
- `event CS2103 Briefing /from 24 02 2024 1600 /to 24 02 2024 1800`
- `e CS2103 Briefing /from 24 02 2024 1600 /to 24 02 2024 1800`

Expected outcome:
```
Got it. I've added this task:
  [E][] CS2103 Briefing (from: 02-24-2024 16:00 to: 02-24-2024 18:00)
Now you have 1 tasks in the list.
```

### Listing all tasks

Lists all existing tasks in the task list.

**Formats:**
- `list`
- `l`

**Examples:**
- `list`
- `l`

Expected outcome:
```
Here are the tasks in your list:
  1.[D][] CS2103 Quiz 3 (by: 02-24-2024 23:59)
  2.[T][] read book
```

### Deleting tasks

Deletes an existing tasks from the task list.

**Formats:**
- `delete {INDEX}`
- `del {INDEX}`

**Notes:**
- Deletes the task at the specified `{INDEX}` from the task list. The `{INDEX}` refers to the index number shown in the 
displayed task list **after** using the `list` command.
- The `{INDEX}` must be a **positive integer** 1, 2, 3...
- The `{INDEX}` must be within the range of **0 < `{INDEX}` < Total number of tasks in the list + 1**
- An exception will be thrown when `{INDEX}` is out of specified range`

**Examples:**
- `delete 1`
- `del 1`

Expected outcome:
```
Noted. I've removed this task:
  [D][] CS2103 Quiz 3 (by: 02-24-2024 23:59)
Now you have 1 tasks in this list.
```

### Marking task

Marks an existing task in the task list as done.

**Formats:** 
- `mark {INDEX}`
- `m {INDEX}`

- Marks the task at the specified `{INDEX}` as **done**. The `{INDEX}` refers to the index number shown in the displayed
task list after using the `list` command. 
- The `{INDEX}` must be a **positive integer** 1, 2, 3...
- The `{INDEX}` must be within the range of **0 < `{INDEX}` < Total number of tasks in the list + 1**
- An exception will be thrown when `{INDEX}` is out of specified range`

**Examples:** 
- `mark 1`
- `m 1`

Expected outcome:
```
Nice! I've marked this task as done:
  [E][X] CS2103 Briefing (from: 02-24-2024 16:00 to: 02-24-2024 18:00)
```

### Unmarking task

Marks an existing task in the task list as not done.

**Formats:** 
- `unmark {INDEX}`
- `unm {INDEX}`

- Marks the task at the specified `{INDEX}` as **not done**. The `{INDEX}` refers to the index number shown in the
displayed task list after using the `list` command. 
- The `{INDEX}` must be a **positive integer** 1, 2, 3...
- The `{INDEX}` must be within the range of **0 < `{INDEX}` < Total number of tasks in the list + 1**
- An exception will be thrown when `{INDEX}` is out of specified range`

**Examples:** 
- `unmark 1`
- `unm 1`

Expected outcome:
```
Ok, I've marked this task as not done yet:
[E][] CS2103 Briefing (from: 02-24-2024 16:00 to: 02-24-2024 18:00)
```

### Find task

Finds any tasks with the matching keyword.

Formats:
- `find {KEYWORD}`
- `f {KEYWORD}`

**Notes:**
- Shows all tasks with the matching keyword.
- If no tasks contain the matching keyword, no tasks will be shown.

Examples:
- `find read`
- `f read`

Expected outcome:
```
Here are the matching tasks in your list:
  1. [T][] read book
  2. [T][] read CS2103 lecture notes
```

### Exit Application

Exits the Application.

Formats:
- `bye`
- `b`

This is the end of the **Homie** User Guide!