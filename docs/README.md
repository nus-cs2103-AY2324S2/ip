# Güliedistodiez User Guide

![Ui.png](Ui.png)

> "Due tomorrow do tomorrow." - Some guy

Güliedistodiez will help you keep track of various tasks. He is,
- text-based
- **easy** to use
- *fast*

All you need to do is,
1. download the latest jar from [here](https://github.com/Wyrkx/ip/releases)
2. run it!

Features:
- [X] Adding/removing tasks
- [X] Searching for tasks by name
- [X] Searching for tasks by date

# Commands

## Adding todos

Add a todo to your tasklist

`todo {NAME}`
 - `NAME`: The name of the task

```
todo buy new monitor
____________________________________________________________
 Understood. I have added this task:
  [T][ ] buy new monitor
 You now have 4 tasks in the list
```

## Adding deadlines

Add a deadline to your task list.

`deadline {NAME} /by {DEADLINE}`
 - `NAME`: The name of the task
 - `DEADLINE`: Date and time of the deadline

```
deadline iP project submission /by 2024-02-23T23:59
____________________________________________________________
 Understood. I have added this task:
   [D][ ] iP project submission (by: 23/02/2024 23:59)
 You now have 1 task in the list
____________________________________________________________
```

## Adding events

Add a deadline to your task list.

`event {NAME} /from {START} /by {END}`
- `NAME`: The name of the task
- `START`: Date and time of the start of the event
- `END`: Date and time of the end of the event

```
event cs2103t tutorial /from 2024-02-23T12:00 /to 2024-02-23T13:00
____________________________________________________________
 Understood. I have added this task:
   [E][ ] cs2103t tutorial (from: 23/02/2024 12:00 to: 23/02/2024 13:00)
 You now have 2 tasks in the list
____________________________________________________________
```

## Listing tasks

Lists your current task list.

`list`

```
list
____________________________________________________________
 1. [E][ ] cs2103t tutorial (from: 23/02/2024 12:00 to: 23/02/2024 13:00)
 2. [D][ ] iP project submission (by: 23/02/2024 23:59)
 3. [T][ ] buy new monitor
____________________________________________________________
```

## Deleting tasks

Delete a task from your task list.

`delete {INDEX}`
 - `INDEX`: The index of the task to be deleted

```
list
____________________________________________________________
 1. [E][ ] cs2103t tutorial (from: 23/02/2024 12:00 to: 23/02/2024 13:00)
 2. [D][ ] iP project submission (by: 23/02/2024 23:59)
 3. [T][ ] buy new monitor
____________________________________________________________
delete 3
____________________________________________________________
 I have removed this task:
   [T][ ] buy new monitor
 You now have 2 tasks in the list
____________________________________________________________
```

## Marking/unmarking tasks

Marks or unmarks a task from your task list.

`mark {INDEX}`
`unmark {INDEX}`
 - `INDEX`: The index of the task to be marked/unmarked

```
list
____________________________________________________________
 1. [E][ ] cs2103t tutorial (from: 23/02/2024 12:00 to: 23/02/2024 13:00)
 2. [D][ ] iP project submission (by: 23/02/2024 23:59)
____________________________________________________________
mark 1
____________________________________________________________
 I have marked this task as completed:
   [E][X] cs2103t tutorial (from: 2024-02-23T12:00 to: 2024-02-23T13:00)
____________________________________________________________
list
____________________________________________________________
 1. [E][X] cs2103t tutorial (from: 23/02/2024 12:00 to: 23/02/2024 13:00)
 2. [D][ ] iP project submission (by: 23/02/2024 23:59)
____________________________________________________________
mark 2
____________________________________________________________
 I have marked this task as completed:
   [D][X] iP project submission (by: 2024-02-23T23:59)
____________________________________________________________
unmark 1
____________________________________________________________
 I have marked this task as incomplete:
   [E][ ] cs2103t tutorial (from: 2024-02-23T12:00 to: 2024-02-23T13:00)
____________________________________________________________
list
____________________________________________________________
 1. [E][ ] cs2103t tutorial (from: 23/02/2024 12:00 to: 23/02/2024 13:00)
 2. [D][X] iP project submission (by: 23/02/2024 23:59)
____________________________________________________________
```

## Find tasks

Searches for a task using a keyword from your task list.

`find {KEYWORD}`
 - `KEYWORD`: The keyword used to search for tasks

```
list
____________________________________________________________
 1. [D][ ] iP project submission (by: 23/02/2024 23:59)
 2. [E][ ] cs2103t tutorial (from: 23/02/2024 12:00 to: 23/02/2024 13:00)
____________________________________________________________
find tutorial
____________________________________________________________
 These are the matching tasks in your list: 
 2. [E][ ] cs2103t tutorial (from: 23/02/2024 12:00 to: 23/02/2024 13:00)
____________________________________________________________
```

## Get schedule

Gets tasks scheduled on a certain date.

`schedule {DATE}`
 - `DATE`: The date to get tasks from

```
list
____________________________________________________________
 1. [D][ ] iP project submission (by: 23/02/2024 23:59)
 2. [E][ ] cs2103t tutorial (from: 23/02/2024 12:00 to: 23/02/2024 13:00)
 3. [T][ ] buy new monitor
 4. [E][ ] cs2103t meeting (from: 27/02/2024 14:00 to: 27/02/2024 15:00)
____________________________________________________________
schedule 2024-02-23
____________________________________________________________
 These are the tasks you have on 23/02/2024
 [D][ ] iP project submission (by: 23/02/2024 23:59)
 [E][ ] cs2103t tutorial (from: 23/02/2024 12:00 to: 23/02/2024 13:00)
____________________________________________________________
```

# Formatting

## About dates

Dates should be entered in the format yyyy-mm-dd

Example: `2024-02-23`

## About date and times

Datetimes should be entered in the format yyyy-mm-ddThh:mm

Example: `2024-02-23T23:59`