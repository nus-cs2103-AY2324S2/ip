---

# EchoPilot User Guide

EchoPilot is a desktop application for managing tasks, optimized for use via a Graphical User Interface (GUI). Designed with simplicity and efficiency in mind, EchoPilot allows users to keep track of their daily tasks, deadlines, and events with ease.

![EchoPilot](Ui.png)

## Quick Start

1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest `duke.jar` from [here](https://github.com/reetmitra/ip).
3. Copy the file to the folder you want to use as the home folder for EchoPilot.
4. Double-click the `duke.jar` file or run it from a command terminal with `java -jar duke.jar` to start the application.
5. Upon launch, you should see a GUI where you can start adding, managing, or searching for tasks.

## Features

### Listing All Tasks: `list`
Displays a list of all tasks.

- **Format:** `list`

### Adding a Task
Adds a task to EchoPilot. There are three types of tasks: todo, deadline, and event.

- **Todo Format:** `todo <task_description>`
- **Deadline Format:** `deadline <task_description> /by <date time>`
- **Event Format:** `event <task_description> /at <date/time>`
- **Examples:**
    - `todo Read book`
    - `deadline Submit report /by 2024-02-25 1600`
    - `event Project meeting /at Monday 10am`

### Finding a Task: `find`
Searches for tasks by keywords.

- **Format:** `find <keyword>`
- **Example:** `find book`

### Deleting a Task: `delete`
Deletes the specified task from EchoPilot.

- **Format:** `delete <task_index>`
- **Example:** `delete 1`

### Marking a Task as Done: `mark`
Marks the specified task as completed.

- **Format:** `mark <task_index>`
- **Example:** `mark 1`

### Unmarking a Task: `unmark`
Marks the specified task as not done.

- **Format:** `unmark <task_index>`
- **Example:** `unmark 1`

### Exiting the Program: `bye`
Closes EchoPilot.

- **Format:** `bye`

### Saving the Data
EchoPilot data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## FAQ

**Q: How do I transfer my data to another computer?**  
A: Install the app on the other computer and overwrite the empty data file with the file that contains the data from your previous EchoPilot folder.

## Known Issues

- No known issues at the time of writing. Please report any issues to our GitHub repository.

## Command Summary

- **List Tasks:** `list`
- **Add Todo:** `todo <task_description>` e.g., `todo Read book`
- **Add Deadline:** `deadline <task_description> /by <date>` e.g., `deadline Submit report /by 2024-02-25`
- **Add Event:** `event <task_description> /at <date/time>` e.g., `event Project meeting /at Monday 10am`
- **Find Task:** `find <keyword>` e.g., `find book`
- **Delete Task:** `delete <task_index>` e.g., `delete 1`
- **Mark Task as Done:** `mark <task_index>` e.g., `mark 1`
- **Unmark Task:** `unmark <task_index>` e.g., `unmark 1`
- **Exit:** `bye`

For any updates or detailed documentation, please click [here](https://github.com/reetmitra/ip).

---