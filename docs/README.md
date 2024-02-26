# Luna
Luna is a desktop application for managing your tasks, optimized for usage via a GUI.
* [Quick start](https://whitesnowx.github.io/ip/#quick-start)
* [Features](https://whitesnowx.github.io/ip/#features)
  * [View all your task entries](https://whitesnowx.github.io/ip/#add-a-to-do-task-todo) `list`
  * [Add a to-do task](https://whitesnowx.github.io/ip/#add-a-to-do-task-todo) `todo`
  * [Add a task with a deadline](https://whitesnowx.github.io/ip/#add-a-task-with-a-deadline-deadline) `deadline`
  * [Add an event](https://whitesnowx.github.io/ip/#add-an-event-event) `event`
  * [Mark a task as done](https://whitesnowx.github.io/ip/#mark-a-task-as-done-mark) `mark`
  * [Unmark a task as undone](https://whitesnowx.github.io/ip/#unmark-a-task-as-undone-unmark) `unmark`
  * [Delete a task from the list](https://whitesnowx.github.io/ip/#delete-a-task-from-the-list-delete) `delete`
  * [Save any changes made](https://whitesnowx.github.io/ip/#save-any-changes-made-save) `save`
  * [Load saved tasks](https://whitesnowx.github.io/ip/#load-saved-tasks-load) `load`
  * [Find a task using a keyword](https://whitesnowx.github.io/ip/#find-a-task-using-a-keyword-find) `find`
  * [Snooze a _deadline_ or _event_ task](https://whitesnowx.github.io/ip/#snooze-a-deadline-or-event-task-snooze) `snooze`
  * Exit the program: `exit`
* [FAQ](https://whitesnowx.github.io/ip/#faq)
* [Known Issues](https://whitesnowx.github.io/ip/#known-issues)
* [Command Summary](https://whitesnowx.github.io/ip/#command-summary)
-------------------------------------------------------------------------------------------------------------------

## Quick Start
1. Download from [here](https://github.com/whitesnowx/ip/releases/tag/A-Jar2)
2. Double-click the file.
3. Add your tasks
4. Let your _worries_ disappear!

## Features
* Note that `<START_DATE>` and `<END_DATE>` parameters are to be specific in the following format: **DD-MM-YYYY**
* The commands are not case sensitive and allows leading and trailing spaces.
* The list allows for duplicate tasks.
* The arguments are tightly restricted and do not allow for less or more than the specified (as shown below).  **With exception of Exit command**.

#### View all your task entries: `list`
Shows all your entries in the list.\
--> Format: list\

#### Add a to-do task: `todo`
Add a task named `<TASK_NAME>` with the **todo** task type.\
--> Format: `todo` `<TASK_NAME>` \


#### Add a task with a deadline: `deadline`
Add a task named `<TASK_NAME>` with the **deadline** task type and set its end date to be `<END_DATE>`.\
--> Format: `deadline` `<TASK_NAME>` `<END_DATE>`\

#### Add an event: `event`
Add a task named `<TASK_NAME>` with the **event** task type and set its start date to `<START_DATE>` and its end date to be `<END_DATE>`.\
--> Format: `event` `<TASK_NAME>` `<START_DATE>` `<END_DATE>` \

#### Mark a task as done: `mark`
Mark a task in the list using its `index` in the list to show that a task is _done_.\
--> Format: `mark` `<INDEX>` 


#### Unmark a task as undone: `unmark`
Unmark a task in the list using its `index` in the list to show that a task is _undone_.\
--> Format: `unmark` `<INDEX>` 


#### Delete a task from the list: `delete`
Remove the entry using its `index` in the list.\
--> Format: `delete` `<INDEX>` 


#### Save any changes made: `save`
Save the current list into the device you are running the program.\
--> Format: `save` 


#### Load saved tasks: `load`
Load the current list from the device you are running the program.\
--> Format: `load` 


#### Find a task using a keyword: `find`
Show only the task from the list that contains the `keyword` in its task name.\
--> Format: `find` `<KEY_WORD>` 


#### Snooze a _deadline_ or _event_ task: `snooze`
Snooze a _deadline_ or _event_  using its `index` in the list by the `<DAYS_TO_SNOOZE>` number of days.\
--> Format: `snooze` `<INDEX_IN_LIST>` `<DAYS_TO_SNOOZE>` 


#### Exit the program: `exit`
Closes the window and ends the program.\
--> Format: `exit` 


## FAQ
(none at the moment)

## Known Issues
(none at the moment)

## Command Summary
