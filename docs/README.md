# Paimon

The chatbot is a task management tool that helps you keep track of your tasks. 

## Command

### Adding a Todo Task : `todo`

Adds a new todo task to the task list.

Format: `todo DESCRIPTION`

- Adds a new todo task with the specified DESCRIPTION.
- The DESCRIPTION is a brief summary of the task to be done.

Examples:

- `todo Read a book` adds a new todo task with the description "Read a book" to the task list.
- `todo Complete homework` adds a new todo task with the description "Complete homework" to the task list.

### Adding a Deadline Task : `deadline`

Adds a new deadline task to the task list.

Format: `deadline DESCRIPTION /by DATETIME`

- Adds a new deadline task with the specified DESCRIPTION and DATETIME.
- The DESCRIPTION is a brief summary of the deadline.
- The DATETIME is the time the deadline need to be finished by.
- DATETIME has for mate of "dd/mm/yyyy hhmm" e.g 01/01/2024 0123, however, non-zero months and days are also supported e.g 1/1/2024 0123.
    

Examples:

- `deadline Read a book /by 20/2/2024 2359` adds a new deadline task with the description "Read a book" and datetime of 20/2/2024 11:59pm to the task list.

### Adding a Event Task : `event`

Adds a new event task to the task list.

Format: `event DESCRIPTION /from FROM /to TO`

- Adds a new event task with the specified DESCRIPTION and FROM and TO.
- The DESCRIPTION is a brief summary of the event .
- The FROM and TO is the start and end time of the event.
- FROM and TO can be any String e.g "tmr", "weekend" with no specific datetime format.

Examples:

- `event sleep /from today /to tmr` adds a new event task with the description "sleep" and duration from "today" to "tmr" to the task list.

### Listing all Tasks : `list`

Displays all the tasks in the task list.

Format: `list`

- Displays all the tasks in the task list, including their task type, done status, description, and additional details.

Examples:

- `list` displays all the tasks in the task list.

### Marking a Task as Done : `mark`

Marks a task as done in the task list.

Format: `mark INDEX`

- Marks the task at the specified INDEX as done.
- The INDEX refers to the position of the task in the task list.
- The task will be marked with a checkmark to indicate it is done when call `list` command.

Examples:

- `done 1` marks the task at index 1 as done in the task list.
- `done 3` marks the task at index 3 as done in the task list.

### Unmarking a Task as Done : `unmark`

Unmarks a task as done in the task list.

Format: `unmark INDEX`

- Unmarks the task at the specified INDEX as not done.
- The INDEX refers to the position of the task in the task list.
- The task will no longer have a checkmark to indicate it is not done when calling the `list` command.

Examples:

- `unmark 1` unmarks the task at index 1 as not done in the task list.
- `unmark 3` unmarks the task at index 3 as not done in the task list.

### Deleting a Task : `delete`

Deletes a task from the task list.

Format: `delete INDEX`

- Deletes the task at the specified INDEX from the task list.
- The INDEX refers to the position of the task in the task list.
- Once deleted, the task cannot be recovered.

Examples:

- `delete 1` deletes the task at index 1 from the task list.
- `delete 3` deletes the task at index 3 from the task list.

### Finding Tasks : `find`

Finds tasks in the task list that match a given keyword.

Format: `find KEYWORD`

- Finds tasks in the task list that contain the specified KEYWORD.
- The search is case-insensitive, so it will match both uppercase and lowercase letters.
- The search is performed on the task description.
- The `find` command only searches for tasks based on their description. It does not search for tasks based on their task type or additional details.

Examples:

- `find book` finds all tasks in the task list that contain the keyword "book" in their description.
- `find meeting` finds all tasks in the task list that contain the keyword "meeting" in their description.



### Archiving Tasks into local saves : `archive`

Save current task list into local archives.

Format: `archive ARCHIVE_NAME`

- Save full task list into an archive.
- Archived task list are saved on local storage, and can be access with `load` command.
- Current task list will not be removed.
- Save current task list under `ARCHIVE_NAME.txt` file in local storage.

Examples:

- `archive 1` archives current task list into archive 1 on local storage



### Archive List : `archive_list`

Displays all the archives in the local storage.

Format: `archive_list`

- Shows a list of all the archived task lists saved in the local storage.
- Each archived task list is identified by its archive name.

Examples:

- `archive_list` displays all the archived task lists in the local storage.

### Loading an Archived Task List : `load`

Loads a previously archived task list from the local storage.

Format: `load ARCHIVE_NAME`

- Loads the archived task list with the specified ARCHIVE_NAME from the local storage.
- The loaded task list will replace the current task list.

Examples:

- `load 1` loads the archived task list from archive 1 in the local storage.

Warning: 
- Loading an archived task list will replace the current task list. 
- Make sure to save your current task list before loading an archive if you want to keep it.




### Exit : `bye`

Ends the program and save the current task list to local storages.

Format: `bye`

Warning:
- Close programme without saying goodbye will not save your current task list for next usage!

Examples:

- `bye` displays a goodbye message and ends the program.

### Command Summary


| Action | Format |
|-------------|--------|
| add todo    | `todo DESCRIPTION` |
| add deadline| `deadline DESCRIPTION /by DATE_TIME` |
| add event   | `event DESCRIPTION /from FROM /to TO` |
| list        | `list` |
| mark  | `unmark INDEX` |
| unmark  | `unmark INDEX` |
| delete  | `delete INDEX` |
| find    | `find KEYWORD` |
| archive | `archive ARCHIVE_NAME` |
| archive_list | `archive_list` |
| load    | `load ARCHIVE_NAME` |
| exit     | `bye` |


Please replace INDEX, KEYWORD, ARCHIVE_NAME, DESCRIPTION, FROM, TO, and DATE_TIME with the specific values you want to use.
