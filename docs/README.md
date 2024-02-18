# GeePeeTee User Guide

![Alt GeePeeTee](/docs/Ui.png)

GeePeeTee is a desktop application for users to manage their tasks using a Command Line Interface (CLI) based input with the visuals of a Graphical User Interface (GUI) to improve ease of use. Manage your tasks efficiently with GeePeeTee, the Java-based task manager that keeps you organized and productive!


## Features

### Adding a Todo Task: 'todo'
Adds a todo task to the task list
:small_blue_diamond: Format: `todo <description>`
:star: Example: `todo read book`
- Expected Outcome: The application will add "read book" to the task list and display a confirmation message along with the updated number of tasks in your list.

### Adding a Deadline Task: 'deadline'
Adds a task with a deadline to the task list, with a specified by date.

:small_blue_diamond: Format: `deadline <description> /by <yyyy-mm-dd>`
:star: Example: `deadline return book /by 2023-03-15`
:white_check_mark: Expected Outcome: 

### Adding an Event Task: `event`

Adds an event task to the task list, with a specified from and to date.

:small_blue_diamond: Format: `event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>`
:star: Example: `event team meeting /from 2023-03-20 /to 2023-03-21`


### Listing All Tasks: `list`

Displays all tasks in the task list.

:small_blue_diamond: Format: `list`
:star: Example: `list`

### Marking a Task as Done: `mark`

Marks a specified task as completed.

:small_blue_diamond: Format: `mark <taskNumber>`
:star: Example: `mark 2`

### Unmarking a Task as Not Done: `unmark`

Marks a specified task as not completed.

:small_blue_diamond: Format: `unmark <taskNumber>`
:star: Example: `unmark 2`

### Deleting a Task: `delete`

Removes a specified task from the task list.

:small_blue_diamond: Format: `delete <taskNumber>`
:star: Example: `delete 3`

### Finding Tasks by Keyword: `find`

Finds tasks by a keyword.

:small_blue_diamond: Format: `find <keyword>`
:star: Example: `find book`

### Tagging a Task: `tag`

Tags a specified task.

:small_blue_diamond: Format: `tag <taskNumber> <tag>`
:star: Example: `tag 1 high`
- Paramter Options: `high, medium, low`

### Exiting the Application: `bye`

Exits the application.

:small_blue_diamond: Format: `bye`
:star: Example: `bye`