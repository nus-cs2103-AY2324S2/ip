# Dino User Guide

![Ui.png](Ui.png)

Dino is a chatbot that allows you to use simple commands to keep your tasks organised.
Make your day productive by using Dino today! 

## Setting Up the chatbot
Prerequisites: JDK 11, update Intellij to the most recent version.

1. Download the latest release version of the application.
2. In your terminal, locate the java file that you have downloaded.
3. Use `java -jar Dino.jar` to launch the application. 


## Features

### Adding a todo task: `todo`
Adds a todo task to the tasklist.

Usage: `todo <description>`

Example: 
`todo read a book`

### Adding a deadline task: `deadline`
Adds a deadline task to the tasklist.

Usage: `deadline <description> /by dd-mm-yyyy`

- Make sure that there are no scheduling conflicts with the time interval
in the current tasklist. Else, Dino will reply:
```angular2html
Scheduling conflict: The new task conflicts with an existing task.
```

Example:
`deadline assignment /by 01-04-2025`

### Adding a event task: `event`
Adds a event task to the tasklist.

Usage: `event <description> /from dd-mm-yyyy /to dd-mm-yyyy`
- Make sure that there are no scheduling conflicts with the time interval
  in the current tasklist. Else, Dino will reply:
```angular2html
Scheduling conflict: The new task conflicts with an existing task.
```

Example:
`event holiday /from 01-04-2025 /to 09-04-2025`

### Listing the tasklist: `list`
Lists all the tasks in the tasklist.

Usage: `list`

### Deleting a task: `delete`
Deletes a task from the tasklist as specified.

Usage: `delete <task index>`

- The task index will be 1-index (i.e. index starts from 1)
- The index should be a number in the range of the current length of the tasklist.

Example:
`delete 3`

### Marking a task: `mark`
Marks a task from the tasklist as specified.

Usage: `mark <task index>`

- The task index will be 1-index (i.e. index starts from 1)
- The index should be a number in the range of the current length of the tasklist.

Example:
`mark 3`

### Unmarking a task: `unmark`
Unmarks a task from the tasklist as specified.

Usage: `unmark <task index>`

- The task index will be 1-index (i.e. index starts from 1)
- The index should be a number in the range of the current length of the tasklist.

Example:
`unmark 3`

### Finding a task: `find`
Finds a task from the tasklist using the keyword.

Usage: `find <keyword>`

- Command returns tasks that has description that matches the keyword.
- The search is case-insensitive.

Example:
`find sl` returns
```angular2html
Matching tasks:
T | 0 | buy slippers
T | 0 | sleep
```
