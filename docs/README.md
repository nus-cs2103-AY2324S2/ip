# GeePeeTee User Guide

GeePeeTee is a desktop application for users to manage their tasks using a Command Line Interface (CLI) based input with the visuals of a Graphical User Interface (GUI) to improve ease of use. Manage your tasks efficiently with GeePeeTee, the Java-based task manager that keeps you organized and productive!

- [Quick Start](#Quick-Start)
- [Features](#Features)
  - [Adding a Todo Task: `todo`](#Adding-a-Todo-Task)
  - [Adding a Deadline Task: `deadline`](#Adding-a-Deadline-Task)
  - [Adding an Event Task: `event`](#Adding-an-Event-Task)
- [Command Summary](#Command-Summary)
## Quick Start
1. Ensure that you have Java `11` or above installed in your computer.
2. Download the latest `geepeetee.jar` from here.
3. Copy the file to the folder you want to use as the home folder for the GeePeeTee task manager.
4. Open a command terminal, `cd` into the folder you just put the jar file in, and use the `java -jar geepeetee.jar` command to run the application. A GUI similar to the below should appear in a few seconds.
![Alt GeePeeTee](/docs/Ui.png)
5. Type the command in the input box at the bottom of the GUI and press Send to execute it. Here are some example commands that you can try:
- `help`: Displays the list of all possible commands
- `list`: Lists all existing tasks
- `event Upcoming meeting /from 2024-03-01 /to 2024-03-02`: Adds an event to the task list with the description `Upcoming meeting`, a start date `2024-03-01` and end date `2024-03-02`
- `delete 1`: Deletes the 1st item on the task list


## Features

### Adding a Todo Task: 'todo'
Adds a todo task to the task list
🔹 Format: `todo <description>`<br>
🌟 Example: `todo read book`<br>
✅ Expected Outcome: The application will add "read book" to the task list and display a confirmation message along with the updated number of tasks in your list.

### Adding a Deadline Task: 'deadline'
Adds a task with a deadline to the task list, with a specified by date.

🔹 Format: `deadline <description> /by <yyyy-mm-dd>`<br>
🌟 Example: `deadline return book /by 2023-03-15`<br>
✅ Expected Outcome: 

### Adding an Event Task: `event`

Adds an event task to the task list, with a specified from and to date.

🔹 Format: `event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>`<br>
🌟 Example: `event team meeting /from 2023-03-20 /to 2023-03-21`<br>
✅ Expected Outcome: 

### Listing All Tasks: `list`

Displays all tasks in the task list.

🔹 Format: `list`<br>
✅ Expected Outcome: 

### Marking a Task as Done: `mark`

Marks a specified task as completed.

🔹 Format: `mark <taskNumber>`<br>
🌟 Example: `mark 2`<br>
✅ Expected Outcome: 

### Unmarking a Task as Not Done: `unmark`

Marks a specified task as not completed.

🔹 Format: `unmark <taskNumber>`<br>
🌟 Example: `unmark 2`<br>
✅ Expected Outcome: 

### Deleting a Task: `delete`

Removes a specified task from the task list.

🔹 Format: `delete <taskNumber>`<br>
🌟 Example: `delete 3`<br>
✅ Expected Outcome: 

### Finding Tasks by Keyword: `find`

Finds tasks by a keyword.

🔹 Format: `find <keyword>`<br>
🌟 Example: `find book`<br>
✅ Expected Outcome: 

### Tagging a Task: `tag`

Tags a specified task.

🔹 Format: `tag <taskNumber> <tag>`<br>
🌟 Example: `tag 1 high`<br>
- Paramter Options: `high, medium, low`<br>
✅ Expected Outcome:

### Exiting the Application: `bye`

Exits the application.

🔹 Format: `bye`<br>
✅ Expected Outcome: 

## Command Summary
| Action        | Format                                                  | Examples|
|---------------|---------------------------------------------------------|-----------------|
| Add Todo      | `todo <description>`                                    | `todo read book`|
| Add Deadline  | `deadline <description> /by <yyyy-mm-dd>`               | `deadline return book /by 2023-03-15` |
| Add Event     | `event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>` | `event team meeting /from 2023-03-20 /to 2023-03-21` |
| List Tasks    | `list`                                                  | `list`|
| Mark Done     | `mark <taskNumber>`                                     | `mark 2`|
| Unmark Done   | `unmark <taskNumber>`                                   | `unmark 2`|
| Delete Task   | `delete <taskNumber>`                                   | `delete 3`|
| Find Tasks    | `find <keyword>`                                        | `find book`|
| Tag Task      | `tag <taskNumber> <tag>`                                | `tag 1 high`|
| Exit App      | `bye`                                                   | `bye`|

