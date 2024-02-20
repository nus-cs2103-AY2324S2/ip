# GoldBot User Guide

// Update the title above to match the actual product name

![GoldBot](Ui.png)

GoldBot is a desktop chatbot for managing tasks, optimized for use via a text-based interface.


## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `GoldBot.jar` from the releases page.
3. Copy the file to an empty folder.
4. Open a terminal and navigate to the folder.
5. Run the command `java -jar GoldBot.jar` to start the app.

## Features

### Adding a todo task: `todo`

Adds a todo task to the task list.
This is a task with only a description and no other attributes.

Format: `todo DESCRIPTION`

Examples:
- `todo read book`
- `todo return book`

### Adding deadlines: `deadline`

Adds a deadline to the task list.
A deadline is a task with a description and a deadline.

Format: `deadline DESCRIPTION /by DATETIME`
`DATE` should be in the format `yyyy-MM-dd HHmm`.

Examples:
- `deadline do homework /by 2024-03-12 2359`

### Adding events: `event`

Adds an event to the task list.
An event is a task with a description and, a start date and time and an end date and time.

Format: `event DESCRIPTION /at START_DATETIME /to END_DATETIME`
`START_DATETIME` and `END_DATETIME` should be in the format `yyyy-MM-dd HHmm`.

Examples:
- `event Rachmaninoff concert /at 2024-03-14 1930 /to 2024-03-14 2200`

### Adding loans: `loan`

Adds a loan to the task list.
A loan is a task with a description and a loan amount.

Format: `loan DESCRIPTION AMOUNT`
`AMOUNT` should be a nonnegative integer.

Examples:
- `loan john 1000`

### Listing all tasks: `list`

Shows a list of all tasks in the task list.

Format: `list`

### Marking a task as done: `mark`

Marks a task as done in the task list.

Format: `mark INDEX`

`INDEX` refers to the 1-indexed number shown in the displayed task list.

Examples:
- `mark 2`

### Unmarking a task as done: `unmark`

Unmarks a task as done in the task list.

Format: `unmark INDEX`

`INDEX` refers to the 1-indexed number shown in the displayed task list.

Examples:
- `unmark 2`

### Paying off a loan: `pay`

Pays off a loan in the task list.

Format: `pay INDEX AMOUNT`

`INDEX` refers to the 1-indexed number shown in the displayed task list.
`AMOUNT` should be a nonnegative integer, less than or equal to the loan amount.

Examples:
- `pay 2 100`

### Finding tasks by keyword: `find`

Finds tasks whose descriptions contain the given keyword.

Format: `find KEYWORD`

Examples:
- `find concert`

### Deleting a task: `delete`

Deletes a task from the task list.

Format: `delete INDEX`

`INDEX` refers to the 1-indexed number shown in the displayed task list.

Examples:
- `delete 2`

### Exiting the program: `bye`

Exits the program.

Format: `bye`

### Saving data

GoldBot data is automatically saved after every command.