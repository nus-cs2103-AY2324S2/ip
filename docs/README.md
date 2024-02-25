# BernardBot User Guide

## Chatbot UI

![Ui](https://qinboan.github.io/ip/Ui.png)

## Setup Instructions
1. Download the BernardBot.jar file onto your desktop.
2. Open your command-line interface and `cd` to the folder where the jar file is located.
3. Run `java -jar BernardBot.jar` and wait for the chatbot to open.


## Features

### Adding a Todo: `todo`

Adds a todo to the list.

Format: `todo [DESCRIPTION]`

Examples:
- `todo read book`
- `todo get gf`

### Adding a Deadline: `deadline`

Adds a task with a deadline to the list.

Format: `deadline [DESCRIPTION] /by [DUE_DATE]`
- DUE_DATE must be of the form yyyy-MM-dd

Examples:
- `deadline homework /by 2024-01-01`

### Adding an Event: `event`

Adds an event with the timing to the list.

Format: `event [DESCRIPTION] /from [START_TIME] /to [END_TIME]`
- START_TIME and END_TIME must be of the form yyyy-MM-dd HH:mm

Examples:
- `event hackathon /from 2024-01-01 08:00 /to 2024-01-02 16:00`

### Adding a Fixed Duration Task: `fixed`

Adds a task with a fixed duration to the list.

Format: `fixed [DESCRIPTION] /time [time]`

Examples:
- `fixed go run /time 1 hour`

### Listing all tasks: `list`

Shows a list of all tasks in the task list.

Format: `list`

### Finding tasks by name: `find`

Find tasks whose descriptions contain any of the given words.

Format: `find [KEYWORD] `
- Only the description of tasks are searched.
- Partial words are also searched

Examples:
- `find book` returns `return book` and `read book`
- `find read` returns `read book`
- `find boo` returns `return book` and `read book`

### Deleting a task: `delete`

Deletes the specified task from the list.

Format: `delete [INDEX]`
- Deletes the task at the specified INDEX.
- The index refers to the index number shown in the task list.
- The index must be a positive integer 1, 2, 3, ...

Examples:
- `delete 2` deletes the 2nd task in the task list.

### Marking a task as done: `mark`

Marks the specified task from the list as done.

Format: `mark [INDEX]`
- Marks the task at the specified INDEX as done.
- The index refers to the index number shown in the task list.
- The index must be a positive integer 1, 2, 3, ...

Examples:
- `mark 2` marks the 2nd task in the task list as done.

### Marking a task as not done: `unmark`

Marks the specified task from the list as not done.

Format: `unmark [INDEX]`
- Marks the task at the specified INDEX as not done.
- The index refers to the index number shown in the task list.
- The index must be a positive integer 1, 2, 3, ...

Examples:
- `unmark 2` marks the 2nd task in the task list as not done.

### Exiting the program: `bye`

Exits the program.

Format: `bye`

