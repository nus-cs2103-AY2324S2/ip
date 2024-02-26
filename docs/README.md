# Yarr User Guide

![Screenshot of Yarr handling adding a todo task, listing tasks and marking a task](Ui.png)

Yarr is a desktop app for managing tasks, optimized for use via a typewritten commands. If you can type fast, Yarr can help you manage your tasks faster than traditional Graphical User Interface (GUI) apps.

## Installation
1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest `yarr.jar` from [the releases page](https://github.com/H4mes/ip/releases).
3. Run the `.jar` file by double-clicking it or running `java -jar yarr.jar` in the terminal.
4. Type commands into the command box and press Enter to execute them (Refer to the Features below for the commands available).

## Features
### 1. Adding a todo task: `todo`
Adds a todo task to the task list.

Format: `todo <description>`

Aliases: `td`

Examples: `todo Read book`, `td Read book`

>Expected outcome:
>```
>I've appended this to yer list:
>[T][ ] Read book 
>```

### 2. Adding a deadline: `deadline`
Adds a deadline task to the task list.

Format: `deadline <description> /by <date> <time>`

Aliases: 
- For `deadline`: `dl`
- For `/by`: `/b`, `/at` or `/a`

Examples: `deadline Read book /by 10/10/2024 1000`, `dl Read book /b 10/10/2024 1000`

>Expected outcome:
>```
>I've appended this to yer list:
>[D][ ] Read book (by: Oct 10 2024 10:00AM)
>```

### 3. Adding an event: `event`
Adds an event task to the task list.

Format: `event <description> /from <date> <time> /to <date> <time>`

Aliases:
- For `event`: `ev`
- For `/from`: `/f`, `/start` or `/s`
- For `/to`: `/t`, `/end` or `/e`

Examples: `event Read book /from 10/10/2024 1000 /to 10/10/2024 1400`, `ev Read book /s 10/10/2024 1000 /e 10/10/2024 1400`

>Expected outcome:
>```
>I've appended this to yer list:
>[D][ ] Read book (from: Oct 10 2024 10:00AM to: Oct 10 2024 2:00PM)
>```

### 4. Listing all tasks: `list`
Lists all tasks in the task list.

Format: `list`

Aliases: `ls`, `l`

>Expected outcome:
>```
>Behold, yer roster of endeavours!
>1. [T][X] Read book
>2. [D][ ] Read book (by: Oct 10 2024 10:00AM)
>3. [E][ ] Read book (from: Oct 10 2024 10:00AM to: Oct 10 2024 2:00PM)
>```

### 5. Marking a task as done: `mark`
Marks a task as done in the task list.

Format: `mark <index>`

Aliases: `m`, `done`

Examples: `mark 1`, `m 1`, `done 1`

>Expected outcome:
>```
>X marks the spot. I've crossed this task off yer list, me heartie!
>[T][X] Read book
>```

### 6. Marking a task as not done: `unmark`
Marks a task as not done in the task list.

Format: `unmark <index>`

Aliases: `um`, `undone`

Examples: `unmark 1`, `um 1`, `undone 1`

>Expected outcome:
>```
>The winds be shiftin', and I be lettin' this task sail with the breeze unmarked.
>[T][ ] Read book
>```

### 7. Deleting a task: `delete`
Removes a task from the task list.

Format: `delete <index>`

Aliases: `del`, `rm`

Examples: `delete 1`, `del 1`, `rm 1`

>Expected outcome:
>```
>As ye command, this one has walked the plank:
>[T][ ] Read book
>
>Only 2 tasks remain, captain!
>```

### 8. Finding tasks by keyword: `find`
Find tasks in the task list by keyword.

Format: `find <keyword>`

Aliases: `f`, `search`

Examples: `find book`, `f book`, `search book`

>Expected outcome:
>```
>Land ho! I've spied these tasks over yonder!
>1. [T][X] Read book [index: 4]
>2. [D][ ] Read book (by: Oct 10 2024 10:00AM) [index: 6]
>3. [E][ ] Read book (from: Oct 10 2024 10:00AM to: Oct 10 2024 2:00PM) [index: 7]
>```

### 9. Exiting the program: `bye`
Exits the program.

Format: `bye`

Aliases: `exit`, `quit`

>Expected outcome: Program window will close.

### 10. Getting help: `help`
Provides information about available commands.

Format: `help` or `help <command>`

Aliases: `h`

Examples: `help`, `help todo`

>Expected outcome:
>```
>Here be ye valid commands: 
>1. todo <description>
>2. deadline <description> /by <date> <time>
>.
>.
>.
>```
>
>```
>Adds a todo task to the list
>Format: todo <description>
>Aliases: 'td'
>Examples: 'todo Read book', 'td Read book'
>```

## Date Time Formats
Yarr accepts the following formats for all commands that require date and time input:
- `dd/MM/yyyy HHmm` (e.g. 10/10/2024 1000)
- `dd-MM-yyyy HHmm` (e.g. 10-10-2024 1000)
- `ddMMyyyy HHmm` (e.g. 10102024 1000)

## Autosaving
Yarr automatically saves your tasks to a file called `yarr.txt` in a folder `data` in the directory as the program. There is no need to save manually.

### Data storage
If no suitable directory or file is found, Yarr will create the file and folder for you.
Subsequently launching the program will read the task list from said file.