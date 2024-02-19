# Cappy User Guide

![Product screenshot](Ui.png)

> Hello I'm here to help you manage your tasks! - Cappy

Do you have a **hard time**
- managing your tasks?
- dealing with GUI software?

If you do Cappy is the _perfect_ companion for you!
~Forget your current task management software.~

## Setup instructions 🛠️
1. Download the [jar](https://github.com/Dethada/ip/releases/tag/A-Release)
2. Make sure java 11 is installed

## Usage 📖
Run it by double clicking on it. Or run `java -jar Cappy.jar` in the terminal.

## Commands

- Add a Todo Task:
   - Syntax: `todo <task_description>`
   Example: `todo Finish math assignment`

- Add a Deadline Task:
   - Syntax: `deadline <task_description> /by <DateTime>`
   - DateTime format: YYYY-MM-DD'T'HH:MM
   Example: `deadline Finish math assignment /by 2024-02-14T14:59`

- Add an Event Task:
   - Syntax: `event <task_description> /from <DateTime> /to <DateTime>`
   - DateTime format: YYYY-MM-DD'T'HH:MM
   Example: `event Math Exam /from 2024-02-14T15:00 /to 2024-02-14T17:00`

- List all Tasks:
   - Command: `list`

- Delete a Task:
   - Syntax: `delete <task_id>`
   Example: `delete 2`

- Mark Task as Completed:
   - Syntax: `mark <task_id>`
   Example: `mark 1`

- Mark Task as not completed:
   - Syntax: `unmark <task_id>`
   Example: `unmark 1`

- Find a Task containing a given keyword:
   - Syntax: `find <keyword>`
   Example: `find math`

- Exit Application:
   - Syntax: `bye`

- Show Help:
   - Syntax: `help`

