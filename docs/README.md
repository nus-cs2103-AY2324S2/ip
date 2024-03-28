# Pyrite User Guide

![image](https://raw.githubusercontent.com/ReflectiveObsidian/ip/master/docs/Ui.png)

Pyrite helps you manage your To-Dos, Deadlines and Events.

## Getting Started

### Adding a To-Do

Add a simple task containing a description.

Command: todo `todo <description>`

Example: `todo Go mining`

A task with the description "Go mining" will be added to your task list.

![image](https://github.com/ReflectiveObsidian/ip/assets/122241304/696e11ae-32eb-4d60-98be-937d324caef7)

### Listing your tasks

List all tasks.

Command: `list`

All tasks will be listed in order of their index.

![image](https://github.com/ReflectiveObsidian/ip/assets/122241304/06eb53aa-867d-4a17-bcac-a3ef54ca2948)

### Mark task as done

Task will be marked as done with a checkmark.

Command: `mark <index>`

Example:`mark 1`

The task with index 1 will be marked as done.

![image](https://github.com/ReflectiveObsidian/ip/assets/122241304/c6ddab2d-a369-4852-ba07-b01d0cf16ead)

### Delete task

Task will be removed from the task list.

Command: `delete <index>`

Example:`delete 1`

The task with index 1 will no longer appear in the list.

![image](https://github.com/ReflectiveObsidian/ip/assets/122241304/ae658d39-ab02-4e88-af21-31a4675d8724)

## Other features

### Add Deadline

Deadlines are tasks with a do-by time.

Command: `deadline <description> /by <deadline datetime>`, where datetime format is `yyyy-mm-ddThh:mm`

Example: `deadline Leave mine /by 2024-01-01T05:00`

A deadline will be added to the list.

### Add Event

Events are tasks with a start and end time.

Command: `event <description> /from <start datetime> /to <end datetime>`, where datetime format is `yyyy-mm-ddThh:mm`

Example: `event Sell ores /from 2024-01-01T08:00 /to 2024-01-01T20:00`

An event will be added to the list.

### Find tasks

Filter tasks by partially matching characters.

Command: `find <search term>`

Example: `find mining`

All the tasks where their text representations partially match the search term will be listed.

### Mark task as not done

Remove the 'X' mark from a Task.

Command: `unmark <index>`

Example: `unmark 1`

Subsequently the task will be treated as not done.

### Enter multiple commands per input

Chain multiple commands together to increase efficiency.

Command: `<command 1>; <command 2>; ...`

Example: `delete 1; delete 2; delete 3`

Responses to each command will be shown, separated by a blank line.
If an error occurs while executing multiple commands, the remaining unexecuted commands will be shown at the bottom of the response.

### Exit Pyrite

Close Pyrite.

Command: `bye`

The Pyrite window will close.

### Error highlighting

If an error occurs during the execution of the commands, the error message will be displayed, and the dialog box will be coloured red.
