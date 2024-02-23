# BadGPT

BadGPT is a **desktop app for managing your tasks**.

## Quick start
1. Ensure that you have Java 11 or above installed.
2. Download the latest `badgpt.jar` from [here](https://github.com/ronnnnnnnnn/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the *home folder* for BadGPT.
4. Double-click the file.
5. Type the command in the command box and press Enter to execute it. Here are some example commands to get you started:
    - `list`: Lists all tasks.
    - `todo return book`: Adds a todo task with the description return book.
    - `mark 2`: Marks the 2nd task in the list as complete.
    - `deadline homework /by 2024-02-23`: Adds a deadline task with the description homework and the deadline 23 Feb 2024.
    - `delete 3`: Deletes the 3rd task in the list.
    - `bye`: Exits the app.
6. Refer to the Features below for details of each command.

## Features
### Listing tasks: `list`
Lists all current tasks in the list.

Format: `list`

### Marking task as complete: `mark`
Marks the task in the specified position in the list as complete.

Format: `mark TASK_NUMBER`

Example: `mark 2`

### Marking task as incomplete: `unmark`
Marks the task in the specified position in the list as incomplete.

Format: `unmark TASK_NUMBER`

Example: `unmark 2`

### Adding a new todo task: `todo`
Adds a new todo task to the task list.

Format: `todo TASK_DESCRIPTION`

Example: `todo return book`

### Adding a new deadline task: `deadline`
Adds a new deadline task to the task list.

Format: `deadline TASK_DESCRIPTION /by YYYY-MM-DD`

Example: `deadline ip submission /by 2024-02-23`

### Adding a new event task: `event`
Adds a new event task to the task list.

Format: `event TASK_DESCRIPTION /from YYYY-MM-DD /to YYYY-MM-DD`

Example: `event recess week /from 2024-02-24 /to 2024-03-03`

### Deleting a task: `delete`
Deletes the task in the specified position from the task list.

Format: `delete TASK_NUMBER`

Example: `delete 3`

### Filtering tasks: `find`
Finds the tasks in the list which contain a specified keyword or phrase.

Format: `find KEYWORD`

Example: `find book` or `find buy groceries`

### Finding tasks by date: `date`
Finds any tasks occurring before or on the specified date.

Format: `date YYYY-MM-DD`

Example: `date 2024-02-23`

### Exiting the program: `bye`
Exits the program.

**IMPORTANT!** Your task list will be saved only if you exit the program by using this command.

Format: `bye`