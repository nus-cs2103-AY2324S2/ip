# Liv User Guide
__Disclaimer__: this user guide is inspired by [wongkj12](https://github.com/wongkj12)'s [user guide](https://wongkj12.github.io/ip/) for his chatbot *[Mr Wong](https://github.com/wongkj12/ip)*
## General Introduction
__*Liv*__ is an Chinese Dragon Lunar New Year themed Chatbot. His main purpose is to track tasks inputted by the user.

![Ui screenshot](Ui.png)
## Commands 
### Add Tasks
You can add *todo*, *deadline* and *event* to your task list.
Command format: `todo NAME`, `deadline NAME /by TIME`, `event NAME /from TIME /to TIME`
Note: TIME should be specified in DDMMYY-T-HHMinMin: e.g. 260224T0900 for 26th Feb 2024 9am, 270224T1600 for 27th Feb 2024 4pm
Sample commands:
- `todo Submit iP`
- `deadline Submit iP /by 270224T0000`
- `event Workshop /from 270224T1200 /to 270224T1400`

### Close App
You can close the app. (if not exited with `close` command, changes would not be saved)
Command format: `close`

### List Tasks
You can list the tasks saved.
Command format: `list`

### Find Tasks
You can filter tasks based on case-sensitive keywords. Multiple keywords can also be used at once. (the order of keywords does not affect the search result)
Command format: `find String...`
Sample commands:
- `find Submit`
- `find Submit iP` (equivalent with 'find iP Submit'')

### Delete Tasks
You can delete tasks by their indices in the list.
Command format: `delete INDEX`
Note: INDEX should be a positive integer
Sample commands:
- `delete 1`

### Mark Tasks
You can mark a task as done with its index.
Command format: `mark INDEX`
Note: INDEX should be a positive integer
Sample commands:
- `mark 1`

### Unmark Tasks
You can mark a task as undone with its index.
Command format: `unmark INDEX`
Note: INDEX should be a positive integer
Sample commands:
- `unmark 1`

### Sort Tasks
You can sort tasks. (based on the alphabetical order of type-description-time)
Command format: `sort`

### Remove Duplicated Tasks
You can remove duplicated tasks. (tasks are duplicates of each other if their types, descriptions and other time properties are the same)
Command format: `distinct`

### Undo Actions
You can undo actions. (at most 1 action in the past can be undone)
Command format: `undo`