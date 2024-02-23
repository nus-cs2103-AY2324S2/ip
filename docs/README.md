# SIGNAL User Guide

<img src="./Ui.png" alt="A screenshot of a chat">

Signal is a chat-bot helper who is here to assist you in managing your tasks!

Feature list:
* Viewing help
* Adding a ToDo task
* Adding a Deadline task
* Adding an Event task
* Marking a task as completed
* Marking a task as uncompleted
* Marking a task as priority
* Marking a task as non-priority
* Listing all tasks
* Listing uncompleted tasks
* Listing priority tasks
* Finding a task by description
* Spelling error detection
* Deleting a task
* Exiting the program

## Viewing help 

Shows a message explaining how to access the help message.

Format: `help`


## Adding a ToDo task

Adds a new task

Format: `todo <task>`

Example: `todo sleep`

Output: The task description and type are shown.

```
Got it! I've added this task to your list:
  T | 0 | sleep
Now you have 1 task in the list.
```


## Adding a Deadline task

Adds a new task with a deadline.

Format: `deadline <task> /by <date> <time>`

Example: `deadline homework /by 2024-02-23 23:59:59`

> Tip! dates and times are formatted as `<yyyy-mm-dd> <\hh:mm:ss>`. It is optional to include the time.

Output: The task description, type and deadline are shown.

```
Got it! I've added this task to your list:
  D | 0 | homework | by: 23 Feb 2024 11:59 pm
Now you have 1 task in the list.
```


## Adding an Event task

Adds a new task with a start and end time.

Format: `event <task> /from <date> <time> /to <date> <time>`

Example: `event birthday /from 2024-12-12 /to 2024-12-12`

> Tip! dates and times are formatted as <yyyy-mm-dd> <hh:mm:ss>

Output: The task description, type, start and end time are shown.

```
Got it! I've added this task to your list:
  E | 0 | birthday | from: 12 Dec 2024 | to: 12 Dec 2024
Now you have 1 task in the list.
```


## Marking a task as completed

Marks a task as completed with an 'X'.

Format: `mark <task_number>`

Example: `mark 12`

Output: The task is shown with an 'X' beside it.

```
Nice! I've marked this task as done:
  D | X | homework | by: 23 Feb 2024 11:59 pm
```


## Marking a task as uncompleted

Marks a task as uncompleted, removing the 'X'

Format: `unmark <task_number>`

Example: `unmark 12`

Output: The task is shown without the 'X' beside it.

```
OK, I've marked this task as undone:
  D | 0 | homework | by: 23 Feb 2024 11:59 pm
```


## Marking a task as priority

Marks a task as priority with a '*'

Format: `prioritise <task_number>`

Example: `prioritise 12`

Output: The task is shown with a '*' beside it.

```
OK, I've marked this task as high priority!
  D | 0 | homework * | by: 23 Feb 2024 11:59 pm
```

## Marking a task as non-priority

Marks a task as non-priority, removing the '*'

Format: `unprioritise <task_number>`

Example: `unprioritise 12`

Output: The task is shown without the '*' beside it.

```
OK, I've marked this task as not priority!
  T | 0 | sleep 
```


## Listing all tasks

Shows a list of all the tasks added.

Format: `list`

Output: A numbered list of tasks created, in order of creation.


## Listing uncompleted tasks

Shows a list of all the uncompleted tasks.

Format: `notdonelist`

Output: A numbered list of uncompleted tasks, in order of creation.


## Listing priority tasks

Shows a list of all the tasks marked priority.

Format: `prioritylist`

Output: A numbered list of priority tasks, in order of creation.


## Locating a task by description

Shows a list of all the tasks which contain the searched keyword.

Format: `find <text>`

Output: A numbered list of tasks, which contain the keyword in their description, in order of creation.


## Spelling error detection

Shows a message checking if the user meant a certain command if the input permutation matches one of the valid commands.

Example: `lsit`

> This feature is only available for the `mark`, `unmark`, `list` and `find` commands.

```
Did you mean 'list'? (y/n)
```
Output: If the user inputs 'y', the intended command will be executed.


## Deleting a task

Deletes a task from the list.

Format: `delete <task_number>`

Example: `delete 1`

Output: The deleted task is shown.

```
Noted, I've deleted this task from your list:
  T | 0 | sleep 
Now you have 0 tasks in the list.
```


## Exiting the program

Closes the program.

> Note: Attempting to close the program with the X button shows a message reminding the user to say goodbye to Signal.

Format: `bye`

Output: Signal replies goodbye.

```
Bye! Hope you come back soon :D
```