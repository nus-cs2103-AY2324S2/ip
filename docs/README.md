# Bobby User Guide

![Screenshot of Bobby Program](https://github.com/yapxuanxuan/ip/blob/master/docs/Ui.png?raw=true) <br>

Bobby is a chat bot that helps to **keep track of your tasks, deadlines and events via a Command Line Interface** (CLI)
while still having the benefits of a Graphical User Interface (GUI). Bobby helps you to remember your tasks so you can 
keep your mind free for other things!

## Adding a todo task: todo
Adds a todo task to your task list. <br>

Format: `todo TASK_DESCRIPTION`

Examples:
- `todo buy 5 apples`
- `todo finish up presentation preparation`


## Listing all tasks: list
Lists out all the tasks in your task list, retrieving the information stored in your hard disk. <br>

Format: `list`

## Adding a deadline task: deadline
Adds the deadline task to your task list. <br>
Note that the deadline date needs to be in dd-mm-YY format and the time needs to be in HHmm format. (24h clock) <br>
Format: `deadline TASK_DESCRIPTION /by DEADLINE_DATE DEADLINE_TIME`

Examples:
- `deadline cs2109s ps4 /by 24-02-2024 2359`
- `deadline cs2106 lab2 /by 10-03-2024 2359`


## Adding an event task: event
Adds the event task to your task list. 
Format: `event EVENT_DESCRIPTION /from START_TIMING /to END_TIMING`

Examples:
- `event career fest /from tuesday /to thursday`
- `event artbox /from 26 Jan /to 28 Jan`


## Marking a task as done: mark
Marks the specified task as done, as indicated by a 'X'. Information is updated in the hard disk. <br>
Task number is the number of the task in your list. Enter list command to check the number.
Format: `mark TASK_NUMBER`

Example:
- `mark 2`

## Unmarking a task: unmark
Marks the specified task as undone. Information is updated in the hard disk. <br>
Task number is the number of the task in your list. Enter list command to check the number.

Format: `unmark TASK_NUMBER`

Example:
- `unmark 2`

## Deleting a task: delete
Deletes the specified task from your task list. <br>
Task number is the number of the task in your list. Enter list command to check the number.

Format: `delete TASK_NUMBER`

Example:
- `delete 1`

## Finding tasks by keywords: find
Finds the matching tasks in your task list where the task description matches the keyword that you inputted.

Format: `find KEYWORD`

Examples: 
- `find cs2109s`
- `find buy`

## Updating task information: update
Updates the task information based on the specified task number, task attribute and updated information. <br>
**Note that updated deadline information needs to be in the same format as when you are creating a deadline task. <br>
Updated deadline information needs to follow the format: dd-mm-YY HHmm (24h clock)* <br> <br>
The different types of tasks have different attributes, as follows:
- Todo task: desc (description)
- Deadline task: desc (description), deadline
- Event task: desc (description), from (start timing), to (end timing)

Format: `update TASK_NUMBER /TASK_ATTRIBUTE UPDATED_INFO`

Examples:
- `update 1 /desc buy 3 apples`
- `update 2 /deadline 24-02-2024 1700`
- `update 3 /from 23 Jan`
- `update 3 /to 30 Jan`

## Exiting the program: bye
Prints out a bye message and exits the program after 1 second.

Format: `bye` <br> <br>

# Command Summary
| Command keyword | Format, examples                                                                                                    |
|-----------------|---------------------------------------------------------------------------------------------------------------------|
| **todo**        | `todo TASK_DESCRIPTION` <br> e.g `todo sleep`                                                                       |
| **deadline**    | `deadline TASK_DESCRIPTION /by DEADLINE_DATE DEADLINE_TIME` <br> e.g `deadline cs2109s ps4 /by 24-02-2024 2359`     |
| **event**       | `event EVENT_DESCRIPTION /from START_TIMING /to END_TIMING` <br> e.g `event career fest /from tuesday /to thursday` |
| **list**        | `list`                                                                                                              |
| **mark**        | `mark TASK_NUMBER` <br> e.g `mark 2`                                                                                |
| **unmark**      | `unmark TASK_NUMBER` <br> e.g `unmark 2`                                                                            |
| **delete**      | `delete TASK_NUMBER` <br> e.g `delete 2`                                                                            |
| **find**        | `find KEYWORD` <br> e.g `find cs2109s`                                                                              |
| **update**      | `update TASK_NUMBER /TASK_ATTRIBUTE UPDATED_INFO` <br> e.g `update 1 /desc buy 3 apples`                            |
| **bye**         | `bye`                                                                                                               |
