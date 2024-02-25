# Thames Chatbot User Guide

Welcome! This is the user guide for my GUI chatbot named Thames. This chatbot can assist you by adding and keeping track of tasks for your everyday life!

## Setting Up Thames

1. To get started with Thames, ensure that you have Java 11 or above installed.
2. Download Thames.jar from here.
3. Copy the jar file into an empty folder.
4. Open a command window in that folder.
5. Run the command java -jar thames.jar.

## Features
> [!NOTE]
> - Words in UPPER_CASE are the parameters to be supplied by the user.
> - All parameters are mandatory. Commands with parameters that are in the wrong format (e.g. wrong order, empty parameters, additional parameters) will cause an error.
> - Extraneous parameters for commands that do not take in parameters (such as list and exit) will be ignored.
e.g. if the command specifies list 123, it will be interpreted as list.
> - Date formats must be in yyyy-mm-dd e.g. `24th March 2024` should be formatted as `"2024-03-24"`.

### Adding todo task: `Todo`
Adds a task with no time restriction to your list. 

Format: `todo NAME`

Examples: 
- `todo read book`

  
### Adding deadline task: `Deadline`
Adds a task that has a deadline to your list.

Format: `deadline NAME /by BY_DATE`

- Multiple BY_DATE are not allowed

Examples:
- `deadline finish homework /by 2024-03-01`

  
### Adding event task: `Event`
Adds a task that has a event duration to your list.

Format: `event NAME /from FROM_DATE /to /TO_DATE`

- Multiple FROM_DATE and TO_DATE are not allowed

Examples:
- `event vacation /from 2024-05-01 /to 2024-05-10`


### Show list of tasks: `List`
Show list of all tasks.

Format: `list`


### Mark task as done: `Mark`
Marks the specified task from the list as done.

Format: `mark INDEX`

- Marks the task at the specified `INDEX` as done.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …​
- The index must be a valid integer pointing to a task in the list.

Examples:
- `mark 2` marks the second task in the list as done.
- `mark 5` in a list with 3 tasks will produce an error message.


### Mark task as not done: `Unmark`
Marks the specified task from the list as not done.

Format: `unmark INDEX`

- Marks the task at the specified `INDEX` as not done.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …​
- The index must be a valid integer pointing to a task in the list.

Examples:
- `unmark 2` marks the second task in the list as not done.
- `unmark 5` in a list with 3 tasks will produce an error message.


### Remove task from list: `Delete`
Removes the specified task from the list.

Format: `delete INDEX`

- Deletes the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …​
- The index must be a valid integer pointing to a task in the list.

Examples:
- `delete 2` deletes the second task from the list.
- `delete 5` in a list with 3 tasks will produce an error message.

  
### Search for task: `Find`
Searches for tasks with a specified keyword.

Format: `find KEYWORD`

- The search is case-insensitive. e.g `Book` will match `book`
- Only the task name is searched.
- Partial words can be matched e.g. `work` will match `homework`
- Supports the search of one keyword only

  
### Show list of events for a date: `Schedule`
Shows the list of events for a specified date.

Format: `schedule DATE`

Examples:
- `schedule 2024-01-01`


### Exit the program: `Bye`
Exits the program.

Format: `bye`


