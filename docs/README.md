# William User Guide

William is a **desktop application designed for managing tasks such as to-dos, events, and deadlines.** Optimized for use via the Command Line Interface (CLI), it features a One Piece theme, catering to fans of One Piece or anime in general. If you're a fast typist, William can track your tasks more swiftly than Luffy's journey to becoming the King of Pirates—a saga so lengthy, it's hard to keep up with.

## Quick Start
1. Ensure that you have Java `11` or above installed on your Computer
2. Download the latest `William.jar` from [here](https://github.com/KhoonSun47/ip/releases)
3. Copy the file to the folder you want to use as the _home folder_ for your William
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar William.jar` command to run the application. A GUI similar to the one below should appear in a few seconds. Note how the app contains some sample data.<br>
5. Type the command in the command box and press Enter to execute it. e.g. typing `list` and pressing enter will show you the list of tasks.<br>
   Some example commands you can try
    - `list`: Lists all the tasks.
    - `todo cs2103 assignment`: Add a todo with a description of the cs2103 assignment to your list of tasks.
    - `delete 3`: Deletes the 3rd task shown in the current list.
    - `bye`: Exits the application.
6. Refer to [Features](#features) below for details of each command

## Features
ℹ️ Notes about the command format:
- Words in UPPER_CASE are the parameters to be supplied by the user.<br>
  e.g. in todo DESCRIPTION, DESCRIPTION is a parameter that can be used as todo n/play games.
- Parameters must be in fixed order.<br>
  e.g. in event DESCRIPTION /from FROM_DATE /to TO_DATE, FROM_DATE must always be before TO_DATE.
- If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines
  as space characters surrounding line breaks may be omitted when copied over to the application.

### Listing all tasks: `list`
Shows a list of all tasks in the task list.  
Format: `list`

### Listing all tasks (sorted in priority): `sort`
Shows a list of all tasks in the task list, sorted by the priority of the task.  
Format: `sort`

### Adding a todo: `todo`
Adds a todo to the task list.

Format: `todo DESCRIPTION​`<br>
Examples
- `todo math homework`
- `todo cs2103 assignment`

### Adding a deadline: `deadline`
Adds a deadline to the task list.

Format: `deadline DESCRIPTION /by DD/MM/YYYY HHMM`<br>
Examples:
- `deadline cs3230 assignment 1 /by 19/02/2024 2359`
- `deadline homework /by 12/12/2024 2359`

### Adding an event: `event`
Adds an event to the task list

Format: `event DESCRIPTION /from DD/MM/YYYY HHMM /to DD/MM/YYYY HHMM`<br>
Examples:
- `event Travis Scott concert /from 01/01/2024 2100 /to 02/01/2024 0400`
- `event F1 /from 01/09/2024 2100 /to 02/09/2024 0100`
> [!IMPORTANT]
> The FROM DATE must always be before the TO DATE!

### Finding task by description: `find`
Finds tasks whose descriptions contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`
- The search is case-sensitive. e.g `play` will not match `Play`
- Only the description is searched.
- Partial words will be matched e.g. `pla` will match `play`
- Task matching at least one keyword will be returned e.g. `CS` will return `CS3230`, `CS2103`

Examples:
- `find play` returns `play games`
- `find CS` returns `CS3230`, `CS2103`

### Deleting a task: `delete`
Deletes the specified task from the task list.

Format: `delete INDEX`
- Deletes the task at the specified `INDEX`.
- The `INDEX` refers to the index number shown in the displayed task list
- The `INDEX` **must be a positive integer** 1, 2, 3, …​

Examples:
- `delete 3` deletes the 3rd task in the task list.

### Marking a task as Done: `mark`
Marks the task at the specified index as done.

Format: `mark INDEX`
- Marks the task at the specified `INDEX`.
- The `INDEX` refers to the index number shown in the displayed task list.
- The `INDEX` **must be a positive integer** 1, 2, 3, …​

Examples:
- `mark 3` marks the 3rd task as done in the task list.

### Marking a task as Not Done: `unmark`
Marks the task at the specified index as not done.

Format: `unmark INDEX`
- Unmarks the task at the specified `INDEX`.
- The `INDEX` refers to the index number shown in the displayed task list.
- The `INDEX` **must be a positive integer** 1, 2, 3, …​

Examples:
- `unmark 3` marks the 2nd task as not done in the task list.

### Add Priority to a task: `priority`
Priorities the task at a specified index

Format: `priority /id INDEX /priority PRIORITY_LEVEL`
- Prioritise the task at a specified `INDEX` with an appropriate **PRIORITY_LEVEL**
- The `INDEX` refers to the index number shown in the displayed task list.
- The `INDEX` **must be a positive integer** 1, 2, 3, …​
- The `PRIORITY_LEVEL` has 4 categories only: `HIGH`, `MEDIUM`, `LOW`, `NONE`

Examples:
- `priority /id 1 /priority high`
- `priority /id 2 /priority none`

### Exiting the program: `bye`
Exits the program.

Format: `bye`

### Saving the data
William's data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file
William's data is saved automatically as a .txt file [JAR file location]/data/tasks.txt. Advanced users are welcome to update data directly by editing that data file.

> [!CAUTION]
Caution: If your changes to the data file make its format invalid, Willaim will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
>
> Furthermore, certain edits can cause William to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.

## FAQ
Q: How do I transfer my data to another Computer?
A: Install the app on the other computer and overwrite the empty data file it creates with the file that contains the data of your previous William home folder.

## Command Summary
| Action  | Format, Examples |
| :---: | :---: |
| **ToDo**  | `todo DESCRIPTION` <br>​ e.g. `todo math homework` |
| **Deadline**  | `deadline DESCRIPTION /by DD/MM/YYYY HHMM` <br> e.g. `deadline homework /by 12/12/2024 2359` |
| **Event**  | `event DESCRIPTION /from DD/MM/YYYY HHMM /to DD/MM/YYYY HHMM` <br> e.g. `event F1 /from 01/09/2024 2100 /to 02/09/2024 0100` |
| **List**  | `list` |
| **Find**  | `find KEYWORD [MORE_KEYWORDS]` |
| **Delete**  | `delete INDEX` <br> e.g. `delete 3` |
| **Mark**  | `mark INDEX` <br> e.g. `mark 3` |
| **Unmark**  | `unmark INDEX` <br> e.g. `unmark 3` |
| **Priority**  | `priority /id INDEX /priority PRIORITY_LEVEL` <br> e.g. `priority /id 1 /priority high` |
| **Sort**  | `sort` |
| **Bye**  | `bye` |
