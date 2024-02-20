# Tofu User Guide

![Screenshot of a typical Tofu the Cat application in usage.](/Ui.png)

Tofu the Cat is a desktop app for managing tasks, 
optimized for use via a Command Line Interface (CLI) while still 
having the benefits of a Graphical User Interface (GUI). 
If you can type fast, Tofu the Cat can manage your tasks
faster than traditional GUI apps.

## Quick start

Type the command in the text field and press `Enter` or 
click the `SEND` button to execute it.
(e.g. typing list and pressing `Enter` will list all the tasks you currently have.)

Some example commands you can try:

- `list` : Lists all tasks.
- `todo exercise` : Adds a ToDo task with a description "exercise" to the current list.
- `delete 3` : Deletes the 3rd task shown in the current list. 
- `mark 2` : Marks the 2nd task shown in the current list to be done. 
- `find test` : Finds all tasks in the current list which contains "task" in their description.

Refer to the Features below for details of each command.

## Features

### Adding a task
Adds a task to the current list.

Format:
1. `todo DESC`
   - Adds a ToDo task with the description.
2. `deadline DESC \by yyyy-mm-dd`
    - Adds a Deadline task with a deadline date.
    - The date input must be in the format of `yyyy-mm-dd`
3. `event DESC \from DATE yyyy-mm-dd \to DATE yyyy-mm-dd`
   - Adds an Event task with a date range.
   - The date input must be in the format of `yyyy-mm-dd`

Examples:
1. `todo exercise`
2. `deadline CS2103 assignment \by 2024-02-24`
3. `event recess week \from 2024-02-27 \to 2024-03-02`

### Listing all tasks
Shows a list of all tasks in the current list.

Format: `list`

### Delete a task
Deletes a task from the current list.

Format: `delete INDEX`
- Deletes the task at the specified INDEX. The index refers to the index number 
shown in the current list. 
- The index must be a positive integer 1, 2, 3, ...

Examples: `delete 3` Deletes the task in the 3rd index in the current list.

### Locating a task by description
Finds tasks whose description contain the given keywords.

Format: `find KEYWORDS`
- The search will match the keywords as a phrase to the description of the tasks 
in the current list.

Examples: `find sleep` Finds the tasks in the current list that contains the phrase "sleep"
in their description.

### Marking/Un-marking a task
Marks or un-marks a task in the current list.

Format:
1. `mark INDEX`
2. `unmark INDEX`
   - Marks or un-marks the task at the specified INDEX. The index refers to the index number shown in the current list.
   - The index must be a positive integer 1, 2, 3, ... 

Examples:
1. `mark 3` Marks the task in the 3rd index in the current list.
2. `unmark 2` Un-marks the task in the 3rd index in the current list.