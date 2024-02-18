# Remi User Guide

This is a user guide for Remi, a c(h)atbot for managing your different tasks.

## Downloading and running the file
Prerequisite: Ensure java 11 is downloaded on your computer
1. Open github and go to this repository: https://docs.github.com/en/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax
2. Download remi.jar from `Code` > `Releases` > `ip v0.2` > `remi.jar`
3. Run `remi.jar` by double-clicking on file explorer or by running `./remi.jar` on terminal.
4. A program called Remi should show up now, with a welcome message.
5. Type a command in the text box and press Enter. This will execute it and return a message. These are some sample commands:
    - `todo go home` will create a todo task called go home
    - `list` will list all your tasks
    - `delete 1` will delete your first task on the list
    - `exit` will close the app


## Features

### Creating new tasks
There are three main types of task in this app: todos, deadlines, and events.
#### Creating a `todo`
- Format: `todo LABEL` <br>
#### Creating a `deadline`
- Format: `todo LABEL /by TIME` <br>
#### Creating a `event`
- Format: `event LABEL /from TIME /to TIME` <br>
  `LABEL` and `TIME` cannot be blank or an error may occur. Note that all tags (`/by`, `/from`, `/to`) are all required for their respective tasks.

### Listing all tasks: `list`
Simply shows a list of all tasks in the task list. This is the list arrangement to be used for indexing purposes.

### Listing all tasks in alphabetical order: `listsort`
Shows a list of all tasks in the task list. This is used only for viewing, in all future commands the alphabetical indices are not used.

### Marking tasks as finished: `mark`
- Format: `mark INDEX` <br>
  This marks a task as finished.
  `INDEX` must be provided and must be a number between 1 and the size of the list or an error may occur e.g. 1, 2, 3. Words such as one, two, or three will not be acceptable<br>
  The `INDEX` should be based on the indexing given by the `list` command and not the `listsort` or `find` commands.

### Marking tasks as unfinished: `unmark`
- Format: `mark INDEX` <br>
  This unmarks a task, making the task unfinished. <br>
  See previous notes regarding `INDEX` in the `mark` section

### Finding a task by keyword: `find`
- Format: `find QUERY_STRING` <br>
  This finds any task that have the `QUERY_STRING` as part of their label. `QUERY_STRING` may be empty. <br>
  Examples:
- `go` will find tasks labelled: `go home`, `go to church`, `government errands`
- `buy place` will find tasks labelled: `buy place at 1`, `buy placebo`, `government errands`
- `buy place` will NOT find tasks labelled: `place buy in market`

### Deleting a task: `delete`
- Format: `delete INDEX` <br>
  This deletes a task, removing it from the list. Deleted tasks aren't recoverable so be careful! <br>
  See previous notes regarding `INDEX` in the `mark` section

### Saving your work
All work is automatically saved whenever the list of tasks is changed. There is no need to save changes manually.

### Exiting the application: `exit`
Exits the remi application, equivalent to pressing the `X` button on the GUI. <br>

