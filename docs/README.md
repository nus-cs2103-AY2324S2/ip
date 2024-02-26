# Luna

todo:
- add links
- quick start instructions
- (DD-MM-YYYY) format
- submit here

Luna is a desktop application for managing your tasks, optimized for usage via a GUI.
* Quick start
* Features
  * View all your task entries: `list`
  * Add a to-do task: `todo`
  * Add a task with a deadline: `deadline`
  * Add an event: `event`
  * Mark a task as done: `mark`
  * Unmark a task as undone: `unmark`
  * Delete a task from the list: `delete`
  * Save any changes made: `save`
  * Load saved tasks: `load`
  * Find a task using a keyword: `find`
  * Snooze a _deadline_ or _event_ task: `snooze`
  * Exit the program: `exit`
* FAQ
* Known Issues
* Command Summary
-------------------------------------------------------------------------------------------------------------------

## Quick Start
1. 

## Features

### View all your task entries: `list`
Shows all your entries in the list.\
--> Format: list

#### Add a to-do task: `todo`
Add a task named `<TASK_NAME>` with the **todo** task type.\
--> Format: `todo` `<TASK_NAME>`

#### Add a task with a deadline: `deadline`
Add a task named `<TASK_NAME>` with the **deadline** task type and set its end date to be `<END_DATE>`.\
--> Format: `deadline` `<TASK_NAME>` `<END_DATE>`

#### Add an event: `event`
Add a task named `<TASK_NAME>` with the **event** task type and set its start date to `<START_DATE>` and its end date to be `<END_DATE>`.\
--> Format: `event` `<TASK_NAME>` `<START_DATE>` `<END_DATE>`

#### Mark a task as done: `mark`
Mark a task in the list using its `index` in the list to show that a task is _done_.\
--> Format: `mark` **<INDEX>**

#### Unmark a task as undone: `unmark`
Unmark a task in the list using its `index` in the list to show that a task is _undone_.\
--> Format: `unmark` **<INDEX>**

#### Delete a task from the list: `delete`
Remove the entry using its `index` in the list.\
--> Format: `delete` **<INDEX>**

#### Save any changes made: `save`
Save the current list into the device you are running the program.\
--> Format: `save`

#### Load saved tasks: `load`
Load the current list from the device you are running the program.\
--> Format: `load`

#### Find a task using a keyword: `find`
Show only the task from the list that contains the `keyword` in its task name.\
--> Format: `find` **<KEY_WORD>**

#### Snooze a _deadline_ or _event_ task: `snooze`
Snooze a _deadline_ or _event_  using its `index` in the list by the `<DAYS_TO_SNOOZE>` number of days.\
--> Format: `snooze` **<INDEX_IN_LIST> <DAYS_TO_SNOOZE>**

#### Exit the program: `exit`
Closes the window and ends the program.\
--> Format: exit

## FAQ
(none at the moment)

## Known Issues
(none at the moment)

## Command Summary
