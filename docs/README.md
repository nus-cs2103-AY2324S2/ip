# Leto User Guide

![Screenshot of Leto's GUI](Ui.png)

**Leto** is a powerful and intuitive task management application designed to help you keep track of your daily tasks
efficiently. Whether you're managing deadlines, events, or simple to-do items, Duke provides a streamlined and 
user-friendly interface to enhance your productivity. It also supports saving to a portable CSV format so you
can import them into a spreadsheet tool like Excel or Google Drive.

## Quick start
1. Ensure you have Java 11 installed.
2. Download the latest version of Leto from [here, under assets, `leto.jar`](https://github.com/camille-readbean/ip/releases/)
3. Copy the downloaded `.jar` file to a folder you want to use as the _home folder_ for __Leto__.
4. You can run the `leto.jar` directly.

## Features

### Notes about command format
**Leto** follow the format 
```bash
command <detail> /options <option details>
```
In most cases, commands are case-insensitive. However, the order of the arguments matter and unless
specified, it is compulsory. Options are also **case-sensitive**. 
Where needed, index starts from 1, same as the output of `list`.

### List of features
1. [List of All Tasks](#listing-all-tasks)
2. [Marking a Task as Done](#marking-a-task-as-done)
3. [Unmarking a Task](#unmarking-a-task)
4. [Adding a Todo](#adding-a-todo)
5. [Adding Deadlines](#adding-deadlines)
6. [Adding Events](#adding-events)
7. [Finding a Task](#finding-a-task)
8. [Snoozing a Deadline](#snoozing-a-deadline)
9. [Displaying the help message](#displaying-the-help-message)
10. [Saving Tasks](#saving-tasks)
11. [Exiting the Application](#exiting-the-application)
12. [CSV Storage format](#csv-storage-format)  
    a. [File recovery](#file-recovery)   

### Listing all tasks
To list all your tasks, use the `list` command. This command displays all tasks you have added in Leto, regardless of their type (todo, deadline, or event).

Format: `list`

### Marking a Task as Done
To mark a task as completed, use the `mark` command followed by the task index.

Format: `mark <index>`

Example: `mark 1` - Mark task 1 in the list as done.

### Unmarking a Task
If you accidentally marked a task as done, you can revert it using the `unmark` command followed by the task index.

Example: `unmark <index>`

Example: `unmark 1` - Mark task 1 in the list as done.

### Adding a Todo
To add a task without a specific deadline or time, use the `todo` command followed by the task details.

Format: `todo <details>`

Example: `todo Read a book` - Add a to-do task with the description "Read a book".

### Adding Deadlines
To add a task with a specific due date, use the `deadline` command followed by the description and the date in YYYY-MM-DD format.

Format: `deadline <description> /by <date>`

Example: `deadline Submit report /by 2024-03-01` - Add a deadline task "Submit report" due by March 1, 2024.


### Adding Events
To add an event, use the `event` command with the event details, start time, and end time in YYYY-MM-DD format.

Format: `event <details> /from <start_time> /to <end_time>`

Example: `event Team meeting /from 2024-03-01 /to 2024-03-02` - Schedule an event "Team meeting" from March 1 to March 2, 2024.

### Finding a Task
To find a specific task by keyword, use the `find` command.

Format: `find <keyword>`

Example: `find report` - Search for tasks containing the keyword "report".

### Snoozing a Deadline
To postpone a deadline, use the `snooze` command followed by the task index and the number of days to postpone.

Format: `snooze <task number> /by <number> d[ays]`

Example: `snooze 3 /by 2 days` - Postpone the deadline of the third task by two days.

### Displaying the Help Message
To view all available commands and their formats, use the `help` command.

Format: `help`

### Saving Tasks
To save your tasks to persistent storage, use the `save` command. This ensures that your tasks are not lost after closing the application.

Format: `save`

### Exiting the Application
To exit Leto, use the `bye` command.  
`quit` and `exit` are aliases to `bye`.  
Any of them will cause the program to **save** and then exit.

Format: `bye`, `quit`, `exit`

## CSV storage format
Tasks are stored in the format in the file `Leto-Tasks.csv` next to where you run it.  
```
Type,Completed,Task,By,From,To
```

Example of a file:
```
D,N,CS3230 Programming Assignment 1,2024-02-19,,
E,N,Talk about Pi,,2024-02-22,2024-02-22
T,Y,Get back to Prof about project,,,
D,N,Project 2000 words report,2024-02-17,,

```

In case of any corrupted entries, it will **wipe** the list.  
  
### File Recovery
If **Leto** indicates the file is corrupted, it will start
with an empty list.

:warning: Do not quit or save the program. Open up the `Leto-Tasks.csv` as fast as possible and
copy it somewhere else. 
Because of the human-readable nature of the file, you might be able to
manually recover it.
