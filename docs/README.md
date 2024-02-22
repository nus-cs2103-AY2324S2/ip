# Yapper User Guide
Yapper is a **desktop app for managing tasks, optimized for use via a
Command Line Interface (CLI)** while still having the benefits of a Graphical
User Interface (GUI).

## Features 

### Adding a Todo Task: `todo`

Adds a todo task to the task list.

Format: `todo [your task]`

Example: `todo read book`


### Adding a Task with Deadline: `deadline`

Adds a task with deadline to the task list.

Format: `deadline [your task] /by [yyyy-mm-dd] [hhmm]`

Example: `deadline return book /by 2019-10-15 1800`


### Adding an Event: `event`

Adds a event to the task list.

Format: `event [your task] /from [yyyy-mm-dd] [hhmm] /to [yyyy-mm-dd] [hhmm]`

Example: `event meeting /from 2019-10-15 1800 /to 2019-10-15 1900`


### Listing all tasks: `list`

Shows a list of all the tasks in the tasklist.

Format: `list`


### Marking a Task as Done: `mark`

Marks the task on the tasklist as done.

Format: `mark [index of task]`

Example: `mark 1`


### Unmarking a Task as Undone: `unmark`

Unmarks the task on the tasklist as undone.

Format: `unmark [index of task]`

Example: `unmark 1`


### Deleting a Task: `delete`

Deletes the task from the tasklist.

Format: `delete [index of task]`

Example: `delete 1`

### Exit chatbot: `bye`

Closes the chatbot and exits.

Format: `bye`


## Usage

### `todo` - Adding a Todo Task

Adds a todo task to tasklist and returns the current task list with the
todo task added.

Example of usage: 

`todo read book`

Expected outcome:

Current tasklist with new task.

```
Got it. I've added this task:
   [T] [ ] readbook
Now you have 1 tasks in your yapping list.

```


### `deadline` - Adding a Task with Deadline

Adds a task with deadline to tasklist and returns the current task list with the 
task added.

Example of usage: 

`deadline return book /by 2019-10-15 1800`

Expected outcome:

Current tasklist with new task.

```
Got it. I've added this task:
   [D][ ] return book (by: Oct 15 2019, 6:00PM)
Now you have 1 tasks in your yapping list.

```


### `event` - Adding a Event

Adds a event to tasklist and returns the current task list with the 
task added.

Example of usage: 

`event meeting /from 2019-10-15 1800 /to 2019-10-15 1900`

Expected outcome:

Current tasklist with new task.

```
Got it. I've added this task:
   [E][ ] meeting Oct 15 2019, 6:00PM to Oct 15 2019, 7:00PM
Now you have 1 tasks in your yapping list.

```

### `list` - Lists all the tasks in the current tasklist

Lists all the tasks in the current tasklist together with the type of 
task and status.

Example of usage: 

`list`

Expected outcome:

Current tasklist

```
Here are the tasks in your yapping list:
1.[T] [ ] read book
2.[D][ ] return book (by: Oct 15 2019, 6:00PM)
3.[E][ ] meeting Oct 16 2019, 6:00PM to Oct 16 2019, 7:00PM

```


### `mark` - Marking a Task as Done

Marks the task in the tasklist with an 'X' and returns the task that is marked
as done.

Example of usage: 

`mark 1`

Expected outcome:

The task that has been marked as done.

```
Nice yap! I've marked this task as done:
 [T] [X] read book

```


### `unmark` - Unmarking a Task as Undone

Unmarks the task in the tasklist and returns the task that has been unmarked

Example of usage: 

`umark 1`

Expected outcome:

Current taskist with new task.

```
Ok bro, I've marked this task as not done yet:
 [T] [ ] read book

```


### `delete` - Deleting a Task

Unmarks the task in the tasklist and returns the task that has been unmarked

Example of usage: 

`umark 1`

Expected outcome:

Current taskist with new task.

```
Ok bro, I've marked this task as not done yet:
 [T] [ ] read book

```

