# DylanBot User Guide

DylanBot is a chatbot that helps you **manage your tasks and deadlines**. 
It is a desktop app that has a **Graphical User Interface (GUI)**. 
If you can type fast, DylanBot can get your task management done faster than traditional GUI apps.

- [Quick Start](#quick-start)
- Features
  - [Listing all tasks: `list`](#listing-all-tasks)
  - Adding a task: `todo`, `deadline`, `event`
  - Marking a task as complete/incomplete: `mark`/`unmark`
  - Tagging a task: `tag`
  - Deleting a task: `delete`
  - Finding tasks by keyword: `find`
  - Finding tasks by tag: `filter`
  - Exiting the program: `bye`

## Quick Start
1. Ensure you have Java `11` installed on your computer.
2. Download the latest `dylanbot.jar` from [here](samplelink.com)
3. Copy the file to the folder you want to use as the home folder for your DylanBot.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar dylanbot.jar` command to run the application. 
<br>A GUI similar to the below should appear in a few seconds.
![DylanBot GUI](Ui.png)
5. Type a command into the input box and press `Enter` or click `Send` to execute it.
<br>Some example commands you can try to get started:
    - `todo read book`: Creates a `TodoTask` with the description `read book`
    - `deadline return book /by 2021-09-30 1800`: Creates a `DeadlineTask` with the description `return book` and the deadline `30th September 2021, 6:00pm`
    - `list`: Lists all tasks
    - `bye`: Exits the app
6. Refer to the Features section below for details of each command.

## Features

### Listing all tasks: `list`
Lists all tasks with their 1-based indices.

Format: `list`

### Adding a task: `todo`, `deadline`, `event`
Adds a task of the specified type along with its description and other relevant details.

Format (todo): `todo d/DESCRIPTION`

Format (deadline): `todo d/DESCRIPTION /by DEADLINE`

Format (event): `todo d/DESCRIPTION /from STARTDATE /to ENDDATE`

Examples:
- `todo read book`
- `deadline return book /by 2021-09-30 1800`
- `event project meeting /from 2021-10-01 /to 2021-10-02`

> [!TIP]
> The date and time format for `DEADLINE`, `STARTDATE`, and `ENDDATE` can either be:
>- `yyyy-MM-dd HHmm`
>- `yyyy-MM-dd` (if no time is specified)

### Marking a task as complete/incomplete: `mark`/`unmark`
Marks a task as complete or incomplete.
- The index used is the 1-based index shown in the `list` command.
- The index must be a **positive integer**.

Format (mark): `mark i/INDEX` 

Format (unmark): `unmark i/INDEX`

Examples:
- `mark i/1`
- `unmark i/2`

### Tagging a task: `tag`
Adds a tag to a task
- The index used is the 1-based index shown in the `list` command.
- The index must be a **positive integer**. 
- Tags can only be added **1 at a time**. 

Format: `tag i/INDEX t/TAG`

Examples:
- `tag i/1 t/important`
- `tag i/2 t/urgent`

>[!TIP]
> Each task can have multiple tags!

### Deleting a task: `delete`
Deletes a task.
- The index used is the 1-based index shown in the `list` command.
- The index must be a **positive integer**.

Format: `delete i/INDEX`

Examples:
- `delete i/1`

### Finding tasks by keyword: `find`
Finds tasks whose descriptions contain the given keyword. 
- Whitespaces and multiple words will be treated as part of the keyword to search for.

Format: `find k/KEYWORD`

Examples:
- `find k/read`

### Finding tasks by tag: `filter`
Finds tasks that have the given tag.

Format: `filter t/TAG`

Examples:
- `filter t/important`

### Exiting the program: `bye`
Exits the program.

Format: `bye`