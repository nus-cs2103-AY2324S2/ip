# Podz User Guide

Podz is a **task managing desktop app** designed to enhance your productivity with a blend Command Line Interface (CLI) efficiency and the user-friendly appeal of a Graphical User Interface (GUI). By seamlessly combining the benefits of CLI speed with the intuitive GUI, Podz empowers users to complete tasks effortlessly.

![Podz GUI](Ui.png "Podz GUI")

## Quick Start

1. Ensure you have Java `11` or above installed.
2. Download the latest `podz.jar` [here](https://github.com/raysonchia/ip/releases).
3. Copy the file to the folder you want to use as the *home folder* for Podz.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar podz.jar` command to run the application.
A GUI similar to the above should appear in a few seconds.
1. Type a command in the command box and press Enter to execute it. 
2. Refer to the features below for details of each command.

## Features

### Listing all tasks: **`list`**
Shows a list of all tasks in the task list.

Outcome:
```
Here are the tasks in your list:
1. [T][X] read book
2. [D][X] return book (by: January 05 2024 18:00)
3. [E][ ] project meeting (from: February 01 2024 12:00 to: February 01 2024 16:00)
4. [T][ ] join sports club
5. [T][ ] borrow book
```

### Adding a todo task: **`todo`**
Adds a todo task to the task list.

Format: `todo DESCRIPTION` <br>
Example: `todo borrow book`

Outcome:
```
Sure thing! Added a new todo task:
[T][ ] borrow book
Now you have 5 tasks in the list.
```

### Adding a deadline task: **`deadline`**
Adds a deadline task to the task list. The time parameter is optional.

Format: `deadline DESCRIPTION /by YYYY-MM-DD [HHmm]` <br>
Example: `deadline return book /by 2024-01-05 1800`

Outcome:
```
Got it! A new deadline task has been added:
[D][ ] return book (by: January 05 2024 18:00)
Now you have 2 tasks in the list.
```

### Adding a event task: **`event`**
Adds a event task to the task list. The time parameter is optional.

Format: `event DESCRIPTION /from YYYY-MM-DD [HHmm] /to YYYY-MM-DD [HHmm]`
` <br>
Example: `event project meeting /from 2024-02-01 1200 /to 2024-02-01 1600`

Outcome:
```
Great choice! An event task has been added:
[E][ ] 1 project meeting (from: February 01 2024 12:00 to: February 01 2024 16:00)
Now you have 3 tasks in the list.
```

### Marking a task: **`mark`**
Marks a task as complete.

Format: `mark INDEX` <br>
Example: `mark 5`

Outcome:
```
Great job! I've marked the task as complete. Keep up the good work!
[T][X] borrow book
```

### Unmarking a task: **`unmark`**
Unmarks a task as complete.

Format: `unmark INDEX` <br>
Example: `unmark 5`

Outcome:
```
Task status updated! The task is now marked as incomplete:
[T][ ] borrow book
```

### Deleting a task: **`delete`**
Deletes a task from the task list.

Format: `delete INDEX` <br>
Example: `delete 5`

Outcome:
```
Noted. I've removed this task:
[T][ ] borrow book
Now you have 4 tasks in the list.
```

### Locate tasks by keywords: **`find`**
Finds tasks that contain the given keywords.

Format: `find KEYWORD(s)` <br>
Example: `find book`

Outcome:
```
Here are the tasks in your list:
1. [T][X] read book
2. [D][X] return book (by: January 05 2024 18:00)
```

### Close program: **`bye`**
Closes the application.

Format: `bye` <br>

Outcome:
```
Goodbye for now!
It was a pleasure assisting you, Podz is here whenever you need help.
```

### Shortened command syntax
Podz can recognize shorter aliases for commands.<br>
Example: `t` can be shorter alias for `todo`.

## Saving the data
Task list data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually. let us know.

## Command Summary

| Command | Format | Example |
|---------|--------|---------|
| List tasks | `list` | `list` |
| Add todo | `todo DESCRIPTION` | `todo borrow book` |
| Add deadline | `deadline DESCRIPTION /by YYYY-MM-DD [HHmm]`  | `deadline return book /by 2024-01-05 1800` |
| Add event | `event DESCRIPTION /from YYYY-MM-DD [HHmm] /to YYYY-MM-DD [HHmm]` | `event project meeting /from 2024-02-01 1200 /to 2024-02-01 1600` |
| Mark task | `mark INDEX` | `mark 1` |
| Unmark task | `unmark INDEX` | `unmark 1` |
| Delete task | `delete INDEX` | `delete 2` |
| Find tasks | `find KEYWORD(s)` | `find book` |
| Exit program | `bye` | `bye` |

## Credits
This user guide was inspired by the user guide of [AB3](https://se-education.org/addressbook-level3/UserGuide.html#features).