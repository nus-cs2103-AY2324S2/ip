# Lucky User Guide
Lucky is a **desktop app developed for managing various tasks such as to-dos, deadlines, and events, optimized for use via Command Line Interface** (CLI). If you're a programmer who's used to typing command lines, then this app is for you! 

- [Quick Start](https://lokidoki102.github.io/ip/#quick-start)
- [Features](#features)
  - [Features](https://lokidoki102.github.io/ip/#features)
  - [Features](https://lokidoki102.github.io/ip/#features)
  - [Features](https://lokidoki102.github.io/ip/#features)
  - [Features](https://lokidoki102.github.io/ip/#features)
  - [Features](https://lokidoki102.github.io/ip/#features)
  - [Features](https://lokidoki102.github.io/ip/#features)
  - [Features](https://lokidoki102.github.io/ip/#features)
- [FAQ](https://lokidoki102.github.io/ip/#faq)
- [Command Summary](https://lokidoki102.github.io/ip/#command-summary)

## Quick Start
1. Ensure that you have Java `11` or above installed on your Computer
2. Download the latest `Lucky.jar` from [here](https://github.com/lokidoki102/ip/releases).
3. Copy the file to the folder you want to use as the _home folder_ for your Lucky app.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar Lucky.jar` command to run the application. <br>A GUI similar to the one below should appear in a few seconds. Note how the app contains some sample data.<br>![Ui](./Ui.png)
5. Type the command in the command box and press Enter to execute it. e.g. typing `list` and pressing enter will show you the list of tasks you have added so far.<br>
   
   Some example commands you can try
    - `list`: Lists all the tasks.
    - `todo ip user guide`: Add a todo with task a description of the **ip user guide** to your list of tasks.
    - `delete 1`: Deletes the 1st task in the list.
    - `tag 1 #cs2103`: Tags the 1st task in the list with **#cs2103**.
    - `bye`: Exits the application.
6. Refer to [Features](#features) below for details of each command

## Features
> [!Notes about the command format:]
> - Words in UPPER_CASE are the parameters to be supplied by the user.<br>
    e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter that can be used as `todo play games`.
> - Items with ... after them can be used multiple times.
> <br> e.g. `tag 1 #TAG...` can be used as `tag 1 #onetagonly, tag 1 #two #tags, tag 1 #three #tags #here`.
> - Words in UPPER_CASE are the parameters to be supplied by the user.<br>
  e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter that can be used as `todo finish writing this user guide`.
> - Parameters must be followed in fixed order as stated below.<br>
  e.g. in `deadline DESCRIPTION /by BY_DATE`, `DESCRIPTION` must always be followed by `BY_DATE`.
> - If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines
  as space characters surrounding line breaks may be omitted when copied over to the application.

### Adding a todo: `todo`
Adds a todo task to the task list.

Format: `todo DESCRIPTION`<br>
Examples
- `todo CS3230 PA2`
- `todo CS2103 final submission`

### Adding a deadline: `deadline`
Adds a deadline task to the task list.

Format: `deadline DESCRIPTION /by DD/MM/YYYY HHMM`<br>
Examples:
- `deadline CS3230 PA1 /by 23/02/2024 2359`
- `deadline CS2103 iP submission /by 26/02/2024 2359`

### Adding an event: `event`
Adds an event task to the task list

Format: `event DESCRIPTION /from DD/MM/YYYY HHMM /to DD/MM/YYYY HHMM`<br>
Examples:
- `event Week 6 Zoom meeting /from 24/02/2024 1400 /to 24/02/2024 1500`
- `event cs2103 tutorial /from 08/03/2024 0900 /to 08/03/2024 1000`
> [!IMPORTANT]
> <br>The `/from` date should always be the same or earlier than `/to` date.

### Listing all tasks:: `list`
Shows a list of all tasks in the task list.
<br>Format: `list`

### Deleting a task: `delete`
Deletes the specified task from the task list.

Format: `delete INDEX`
- Deletes the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...
- The index can only be at most the same number of tasks inside the task list.

Examples:
- `delete 1` deletes the 1st task in the task list.

### Marking a task as Done: `mark`
Marks the specified task from the task list as done.

Format: `mark INDEX`
- Marks the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...
- The index can only be at most the same number of tasks inside the task list.

Examples:
- `mark 1` marks the 1st task as done in the task list.

### Unmarking a task: `unmark`
Unmarks the specified task from the task list.

Format: `unmark INDEX`
- Unmarks the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...
- The index can only be at most the same number of tasks inside the task list.

Examples:
- `unmark 1` unmarks the 1st task from done to not done in the task list.

### Finding task: `find`
Finds tasks whose descriptions/#tag contain any of the given keywords.

Format: `find KEYWORD...` OR `find #TAG`
- Tasks are searchable by either description or #tag.
- The search is case-sensitive. e.g. `essay` will not match `Essay`.
- When searching by `KEYWORD`, partial keywords can be matched. e.g. `ess` will match `essay`.
- When searching by `#TAG`, the `#TAG` has to fully match the tag being searched. e.g. `#school` will **NOT** match`#schoolstuff`, but `#schoolstuff` will match `#schoolstuff`.
- Tasks matching at least one keyword or #tag will be returned e.g. `tutorial` will return `cs2103 tutorial`, `ma1522 tutorial`.

Examples:
- `find #tutorial` returns `cs2103 #tutorial`, `ma1522 #tutorial`
- `find essay` returns `GEC1030 essay`, `NUR1113A essay`

### Tagging a task: `tag`
Tags the specified task from the task list.

Format: `tag INDEX #TAG...`
- Tags the task at the specified `INDEX` with `#TAG`.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...
- The index can only be at most the same number of tasks inside the task list.
- Multiple `#TAG` can be used, but each `#TAG` can only be one word. e.g. `#school stuff` will **NOT** work, but `#school #stuff` will.

Examples:
- `tag 1 #tutorial` tags task 1 with `#tutorial`.
- `tag 1 #essay #gec1030` tags task 1 with `#essay #gec1030`.

### Removing a tag from a task: `removetag`
Removes a tag the specified task from the task list.

Format: `removetag INDEX #TAG...`
- Removes the specified `#TAG` from the task at the specified `INDEX` with .
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...
- The index can only be at most the same number of tasks inside the task list.
- Multiple `#TAG` can be removed at a time.
- Removing a `#TAG` that does not exist will not do anything.


Examples:
- `removetag 1 #tutorial` removes the tag `#tutorial` from task 1.
- `removetag 1 #essay #gec1030` removes the tag `#essay #gec1030` from task 1.

### Exiting the program: `bye`
Exits the program.

Format: `bye`

### Saving the data
Lucky data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## FAQ
Q: How do I transfer my data to another Computer?<br>
A: Install the app on the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Lucky home folder.

## Command Summary

| Action        | Format, Examples                                                                                                                              |
|---------------|-----------------------------------------------------------------------------------------------------------------------------------------------|
| **ToDo**      | `todo DESCRIPTION` <br>e.g. `todo CS3230 PA2`                                                                                                 |
| **Deadline**  | `deadline DESCRIPTION /by DD/MM/YYYY HHMM` <br> e.g. `deadline CS2103 iP submission /by 26/02/2024 2359`                                      |
| **Event**     | `event DESCRIPTION /from DD/MM/YYYY HHMM /to DD/MM/YYYY HHMM` <br> e.g. `event Week 6 Zoom meeting /from 24/02/2024 1400 /to 24/02/2024 1500` |
| **List**      | `list`                                                                                                                                        |
| **Delete**    | `delete INDEX` <br> e.g. `delete 1`                                                                                                           |
| **Mark**      | `mark INDEX` <br> e.g. `mark 1`                                                                                                               |
| **Unmark**    | `unmark INDEX` <br> e.g. `unmark 1`                                                                                                           |
| **Find**      | `find KEYWORD...` OR `find #TAG`                                                                                                              |
| **tag**       | `tag INDEX #TAG...` <br> e.g. `tag 1 #essay #gec1030`                                                                                         |
| **removetag** | `removetag INDEX #TAG...` <br> e.g. `removetag 1 #essay #gec1030`                                                                             |
| **Bye**       | `bye`                                                                                                                                         |