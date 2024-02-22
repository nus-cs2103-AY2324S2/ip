# Taylor v1.0 User Guide

![Taylor_v1.0.png](Ui.png)

*Taylor* ChatBot is a **desktop app that offers organisational tools including to-do list,
deadline and event management.** 

- [Quick start](#quick-start)
- [Features](#features)
    - [Inserting tasks: `todo`](#inserting-tasks-todo)
    - [Inserting tasks: `deadline`](#inserting-tasks-deadline)
    - [Inserting tasks: `event`](#inserting-tasks-event)
    - [Listing out all tasks](#listing-out-all-tasks)
    - [Deleting task: `delete`](#deleting-task-delete)
    - [Mark a task: `mark/unmark`](#mark-a-task-markunmark)
    - [Find a task: `find`](#find-a-task-find)
    - [Search a task: `search`](#search-a-task-search)
    - [Exit the program: `bye`](#exit-the-program-bye)
- [FAQ](#FAQ)
- [Command Summary](#command-summary)


## Quick start
1. Ensure you have `Java 11` or above installed in your Computer.
2. Clone this repository:
   - `git clone https://github.com/AidenLYT/ip.git` 
3. Create a file `taylor.txt` in the folder you have cloned in.
   - This is where your database will be stored.
4. Refer to the [Features](#Features) below for details of each command.

## Features
>[!NOTE]
> After insertion, Taylor will announce the total tasks in the
> respective groups of tasks (todo, deadline or event).
> 
> Not the total tasks in the database. 
### Inserting tasks: `todo`
Adds a to-do task to the database

Format: `todo <DESCRIPTION>`

Example input:
- `todo cry to 'You're losing me' from Midnights (The Vault)`

Example output:
- `[T][] cry to 'You're losing me' from Midnights (The Vault)`
- `Now you have ***Number*** tasks in the list.`

### Inserting tasks: `deadline`
Adds a deadline to the database

Format: `deadline <DESCRIPTION> /by <YYYY-MM-dd HHmm>`

Example input:
- `deadline The Eras Tour /by 2024-03-02 1900`

Example output:
- `[D][] The Eras Tour (by: 02 Mar 2024 07:00PM)`
- `Now you have ***Number*** tasks in the list.`

### Inserting tasks: `event`
Adds an event to the database

Format: `event <DESCRIPTION> /from <YYYY-MM-dd HHmm> /to <YYYY-MM-dd HHmm>`

Example input:
- `event The Eras Tour Singapore /from 2024-03-02 1900 /to 2024-03-09 2200`

Example output:
- `[E][] The Eras Tour Singapore (from: 02 Mar 2024 07:00PM to: 09 Mar 2024 10:00PM)`
- `Now you have ***Number*** tasks in the list.`

### Listing out all tasks
Lists the tasks that are in the database

Format: `list`

### Deleting task: `delete`
Delete a task

Format: `delete <TASK_TYPE> <TASK_INDEX>`

> [!TIP]
> Use `list` function to check the task of the task to be deleted

Example input:
- `delete deadline 1`

### Mark a task: `mark/unmark`
Mark a task as done or unmark a task as incomplete

Format: 
- To mark: `mark <TASK_TYPE> <TASK_INDEX>`
- To unmark: `unmark <TASK_TYPE> <TASK_INDEX>`

Example input:
- `mark todo 1`

Example output:
- `[T][X] cry to 'You're losing me' from Midnights (The Vault)`

### Find a task: `find`
Find a task with description containing a **keyword**

> [!IMPORTANT]
> `Taylor v1.0` only supports **1 (ONE)** keyword at the moment

Format: `find <keyword>`

Example input:
- `find Singapore`

Example output:
- `[E][] The Eras Tour Singapore (from: 02 Mar 2024 07:00PM to: 09 Mar 2024 10:00PM)`

### Search a task: `search`
Search for a task with Date and Time

> [!IMPORTANT]
> `Taylor v1.0` only supports <YYYY-MM-dd HHmm> format at the moment

Format: `find <YYYY-MM-dd HHmm>`

Example input
- `find 2024-03-02 1900`

Example output
- `[E][] The Eras Tour Singapore (from: 02 Mar 2024 07:00PM to: 09 Mar 2024 10:00PM)`

### Exit the program: `bye`
Quit talking to Taylor

> [!TIP]
> Fear not, Taylor will automatically save your inputs after each command

Format: `bye`

## FAQ
**Q.** Can I adjust the dimension of the Taylor application?
**A.** Unfortunately, Taylor v1.0 does not support such function yet. 
Do look forward to Taylor v2.0 Era :smile:

## Command summary
| Action              | Format                                                              |
|---------------------|---------------------------------------------------------------------|
| Insert: `todo`      | `todo <DESCRIPTION>`                                                |
| Insert : `deadline` | `deadline <DESCRIPTION> /by <YYYY-MM-dd HHmm>`                      |
| Insert : `event`    | `event <DESCRIPTION> /from <YYYY-MM-dd HHmm> /to <YYYY-MM-dd HHmm>` |
| List                | `list`                                                              |
| Delete              | `delete <TASK_TYPE> <TASK_INDEX>`                                   |
| Marking : `mark`    | `mark <TASK_TYPE> <TASK_INDEX>`                                     | 
| Marking : `unmark`  | `unmark <TASK_TYPE> <TASK_INDEX>`                                   |
| Find                | `find <keyword>`                                                    |
| Search              | `search <YYYY-MM-dd HHmm>`                                          |





