# Wei User Guide

![Screenshot of product.](/Ui.png)

**Welcome to Wei, where efficient task management meets intuitive design. Say goodbye to missed deadlines and 
scattered to-do lists, and hello to better organization at your fingertips.**

## User Commands:

### Adding tasks

1. todos 
   - Adds a normal task
   - Format: `todo DESCRIPTION` 
   - Example: `todo complete assignment`

2. deadlines
   - Adds a task that have a specific due date
   - Format: `deadline DESCRIPTION /by DATE` 
   - Example: `deadline complete assignment by/ 2024-02-20`

3. events
   - Adds a task with a specific start and end period
   - Format: `event DESCRIPTION /start DATE /end DATE` 
   - Example: `event competition /start 2024-02-20 /end 2024-02-25`

### Deleting tasks
Deletes the specified task from the task list.

Format: `delete INDEX`

Example: `delete 1`

### Listing tasks
Shows a list of all persons in the address book.

Format: `list`

### Marking/Unmarking task status
Indicate a task as complete or incomplete.

Format: `mark INDEX`, `unmark INDEX` 

Example: `mark 1`, `unmark 1`

### Adding reminders
Set reminder for specific task on a specific day.

Format: `remind INDEX DATE`

Example: `remind 1 2024-02-20`

### Exiting the program
Exits the program.
   
Format: `bye`


## Notes

- Words in `UPPER_CASE` are the parameters to be supplied by the user.
- `DATE` must be in format `YYYY-MM-DD`