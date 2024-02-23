# Eggy User Guide

![Screenshot of Eggy chatbot.](Ui.png)

Eggy is a chatbot that helps you manage your tasks with ease. 
It utilizes a graphical user interface (GUI) and executes actions based on specific commands entered.

## Quick Start
1. Ensure that Java 11 or above is installed in your computer.
2. Download the latest release from [here](https://github.com/DhiraPT/ip/releases).
3. Copy the jar file into an empty folder.
4. Open a command window in that folder.
5. Run the command `java -jar Eggy-v{version}.jar` (i.e., `java -jar Eggy-v0.3.jar`).

## Features

### Adding todos: `todo`

Adds a todo to your task list.

Format: `todo DESCRIPTION`

Example: `todo read books`

### Adding deadlines: `deadline`

Adds a deadline to your task list.

Format: `deadline DESCRIPTION /by DATE`

The due date must be in the format `d/MM/yyyy HHmm`

Example: `deadline return book /by 2/12/2019 1800`

### Adding events: `event`

Adds an event to your task list.

Format: `event DESCRIPTION /from START_DATE /to END_DATE`

The start and end date must both be in the format `d/MM/yyyy HHmm`

Example: `event project meeting /from 2/12/2019 1800 /to 2/12/2019 2000`

### Listing all tasks: `list`

Shows a list of all the tasks in your task list.

Format: `list`

### Marking a task as done: `mark`

Marks a task as done.

Format: `mark TASK_NUMBER`

Example: `mark 1`

### Marking a task as not yet done: `unmark`

Marks a task as not yet done.

Format: `unmark TASK_NUMBER`

Example: `unmark 2`

### Deleting a task: `delete`

Deletes a task from your task list.

Format: `delete TASK_NUMBER`

Example: `delete 3`

### Finding tasks by keyword: `find`

Finds tasks that contain the keyword.

Format: `find KEYWORD`

Example: `find book`

### Exiting the program: `bye`

Exits the program.

Format: `bye`

### Saving the data

The data is saved to the hard disk automatically after any command that changes the data.
There is no need to save manually.

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: Install the app in the other computer and copy the data/tasks.txt from your previous Eggy's program home folder
to the new home folder (the folder where the jar file is located).