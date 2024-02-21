# Pyrite User Guide

[![image](https://github.com/ReflectiveObsidian/ip/blob/master/docs/Ui.png)](https://github.com/ReflectiveObsidian/ip/blob/master/docs/Ui.png)

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


List all tasks.

Command: `list`

All tasks will be listed in order of their index.

![image](https://github.com/ReflectiveObsidian/ip/assets/122241304/06eb53aa-867d-4a17-bcac-a3ef54ca2948)

All tasks will be listed in order of their index.

![image](https://github.com/ReflectiveObsidian/ip/assets/122241304/06eb53aa-867d-4a17-bcac-a3ef54ca2948)

## Other features

### Add Deadline

Deadlines are tasks with a do-by time.

Command: `event <description> /by <deadline datetime>`, where datetime format is `yyyy-mm-ddThh:mm`

Example: `deadline Leave mine /by 2024-01-01T05:00`

### Add Event

Events are tasks with a start and end time.

Command: `event <description> /from <start datetime> /to <end datetime>`, where datetime format is `yyyy-mm-ddThh:mm`

Example: `event Sell ores /from 2024-01-01T08:00 /to 2024-01-01T20:00`

### Find tasks

Command: `find <search term>`

Example: `find mining`

### Mark task as not done

Command: `unmark <index>`

Example: `unmark 1`

### Exit Pyrite

Command: `bye`
