# GeePeeTee User Guide

GeePeeTee is a desktop application for users to manage their tasks using a Command Line Interface (CLI) based input with the visuals of a Graphical User Interface (GUI) to improve ease of use. Manage your tasks efficiently with GeePeeTee, the Java-based task manager that keeps you organized and productive!

- [Quick Start](#quick-start)
- [Features](#features)
  - [Adding a Todo Task: `todo`](#adding-a-todo-task-todo)
  - [Adding a Deadline Task: `deadline`](#adding-a-deadline-task-deadline)
  - [Adding an Event Task: `event`](#adding-an-event-task-event)
  - [Listing All Tasks: `list`](#listing-all-tasks-list)
  - [Marking a Task as Done: `mark`](#marking-a-task-as-done-mark)
  - [Unmarking a Task as Not Done: `unmark`](#unmarking-a-task-as-not-done-unmark)
  - [Deleting a Task: `delete`](#deleting-a-task-delete)
  - [Finding Tasks by Keyword: `find`](#finding-tasks-by-keyword-find)
  - [Tagging a Task with Priority: `tag`](#tagging-a-task-with-priority-tag)
- [FAQ](#faq)
- [Command Summary](#command-summary)


## Quick Start
  1. Ensure that you have Java `11` or above installed in your computer.
  2. Download the latest `geepeetee.jar` from here.
  3. Copy the file to the folder you want to use as the home folder for the GeePeeTee task manager.
  4. Open a command terminal, `cd` into the folder you just put the jar file in, and use the `java -jar geepeetee.jar` command to run the application. A GUI similar to the below should appear in a few seconds. <br>
  <img src="./Ui.png" alt="GeePeeTee" height="800">
  <br>
  5. Type the command in the input box at the bottom of the GUI and press Send to execute it. Here are some example commands that you can try: <br>
    - `help`: Displays the list of all possible commands <br>
    - `list`: Lists all existing tasks <br>
    - `event Upcoming meeting /from 2024-03-01 /to 2024-03-02`: Adds an event to the task list with the description `Upcoming meeting`, a start date `2024-03-01` and end date `2024-03-02` <br>
    - `delete 1`: Deletes the 1st item on the task list


## Features

### <span style="color: #0B6E4F;">Adding a Todo Task: `todo`</span>
Adds a todo task to the task list.

🔹 **Format**: `todo DESCRIPTION`<br><br>
🌟 **Example**: `todo read book`<br><br>
✅ **Expected Outcome**: The application will add a Todo task with the description "read book" to the task list and display a confirmation message along with the updated number of tasks in your list.<br><br><br>


### <span style="color: #0B6E4F;">Adding a Deadline Task: `deadline`</span>
Adds a task with a deadline to the task list, with a specified by date.

🔹 **Format**: `deadline DESCRIPTION /by DATE`<br> 
    - The `DATE` specified must be the <strong>YYYY-MM-DD</strong> format <br>
<br>
🌟 **Example**: `deadline return book /by 2023-03-15`<br><br>
✅ **Expected Outcome**: The application will add a Deadline task with the description "return book" and deadline date of "2023-03-15" to the task list and display a confirmation message along with the updated number of tasks in your list.<br><br><br>


### <span style="color: #0B6E4F;">Adding an Event Task: `event`</span>

Adds an event task to the task list, with a specified from and to date.

🔹 **Format**: `event DESCRIPTION /from DATE /to DATE`<br>
    - The `DATE` specified must be the <strong>YYYY-MM-DD</strong> format <br>
<br>
🌟 **Example**: `event team meeting /from 2023-03-20 /to 2023-03-21`<br><br>
✅ **Expected Outcome**: The application will add an Event task with the description "team meeting, start date of "2023-03-20" and end date of "2023-03-21" to the task list and display a confirmation message along with the updated number of tasks in your list.<br><br><br>


### <span style="color: #0B6E4F;">Listing All Tasks: `list`</span>

Displays all tasks in the task list.

🔹 **Format**: `list`<br><br>
✅ **Expected Outcome**: The application will displays all tasks in your task list, ordered by priority (if tasks are tagged with a priority level) and order of addition of tasks.<br><br><br>


### <span style="color: #0B6E4F;">Marking a Task as Done: `mark`</span>

Marks an index-specified task as completed.

🔹 **Format**: `mark INDEX`<br>
    - The `INDEX` specified refers to the index number shown in the displayed task list. The index must be a positive integer and a valid index.<br>
<br>
🌟 **Example**: `mark 2`<br><br>
✅ **Expected Outcome**: The application will mark a task as completed, denoted by the [X] symbol on the task.<br><br><br>


### <span style="color: #0B6E4F;">Unmarking a Task as Not Done: `unmark`</span>

Marks an index-specified task as not completed.

🔹 **Format**: `unmark INDEX`<br>
    - The `INDEX` specified refers to the index number shown in the displayed task list. The index must be a positive integer and a valid index.<br>
<br>
🌟 **Example**: `unmark 2`<br><br>
✅ **Expected Outcome**: The application will mark the specified task as completed, denoted by the [ ] symbol on the task.<br><br><br>


### <span style="color: #0B6E4F;">Deleting a Task: `delete`</span>

Removes a specified task from the task list.

🔹 **Format**: `delete INDEX`<br>
    - The `INDEX` specified refers to the index number shown in the displayed task list. The index must be a positive integer and a valid index.<br>
<br>
🌟 **Example**: `delete 3`<br><br>
✅ **Expected Outcome**: The application will remove the specified task from the task list and display a confirmation message along with the updated number of tasks in your list.<br><br><br>


### <span style="color: #0B6E4F;">Finding Tasks by Keyword: `find`</span>

Finds tasks by a keyword.

🔹 **Format**: `find KEYWORD`<br>
    - The search is case-insensitive, e.g `meeting` will match `Meeting`<br>
    - Partial words will be matched as well, e.g `meet` will match `meeting`<br>
<br>
🌟 **Example**: `find book`<br><br>
✅ **Expected Outcome**: The application will search through the the task list for any tasks which description contains the specified keyword and displays the list of matching tasks.<br><br><br>


### <span style="color: #0B6E4F;">Tagging a Task With Priority: `tag`</span>

Tags an index-specified task with a specified priority level

🔹 **Format**: `tag INDEX TAG`<br>
    - The `INDEX` specified refers to the index number shown in the displayed task list. The index must be a positive integer and a valid index.<br>
    - The `TAG` must be one of these 3 supported options (case-insensitive): `high`, `medium`, `low`<br>
<br>
🌟 **Example**: `tag 1 high`<br><br>
✅ **Expected Outcome**: The application will tag the specified task with a priority level.<br><br><br>


### <span style="color: #0B6E4F;">Exiting the Application: `bye`</span>

Exits the application.

🔹 **Format**: `bye`<br><br><br>

## FAQ
**Q:** How do i transfer my data to another Computer?<br>
**A:** Install the app in the other computer and overwrite the **GeePeeTee.txt** data file that it creates with the existing **GeePeeTee.txt** data file in your current folder.<br><br><br>

## Command Summary 

| Action        | Format                                                  | Examples |
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

