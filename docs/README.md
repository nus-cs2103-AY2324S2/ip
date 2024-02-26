# Jojo User Guide

![Picture of Jojo's UI.](./Ui.png)

Welcome to Jojo! Jojo is a chatbot that helps you to manage your tasks.
Jojo can help you to: 
- Add a `todo` task with a description
- Add a `deadline` task with a description and the date & time of the deadline 
- Add an `event` task with a description, start and end date and/or time
- List all tasks 
- Mark a task as done 
- Unmark a task as undone
- Delete any task in the list 
- Find specific tasks easily using keywords 

Note: Any words in <> (e.g. `<DESCRIPTION>`) below are placeholder text for the parameters to be supplied by the user.

## Adding a todo
Command: `todo <DESCRIPTION>`<br>
What it does: Adds a todo task with the description provided to the list of tasks.

Example: `todo math homework` 
This adds a todo task with the description "math homework" to the list of tasks.
Expected output: 
```
Got it. I've added this task:
[T][ ] math homework
Now you have 1 tasks in the list.
```

## Adding a deadline
Command: `deadline <DESCRIPTION> /by <DATETIME>`
What it does: Adds a deadline task with the description and date & time of the deadline provided to the list of tasks.
Note: The format of the date in <DATETIME> is dd/mm/yyyy. If the date or month is a single digit, there is no need to 
add an extra "0" in front (e.g. 2/3/2024 is acceptable). The format of the time is in 24-hour format (e.g. 1300).

Example: `deadline english assignment /by 02/12/2024 2359`
This adds a deadline task with the description "english assignment", and deadline of "02/12/2024 2359" to the list of tasks.
Expected output:
```
Got it. I've added this task:
[D][ ] english assignment (by: Dec 02 2024 23:59)
Now you have 1 tasks in the list.
```

## Adding an event 
Command: `event <DESCRIPTION> /from <START_TIME> /to <END_TIME>`
What it does: Adds an event task with the description, start and end time of the event provided to the list of tasks.
Note: <START_TIME> and <END_TIME> both takes in a String.

Example: `event Taylor Swift concert /from Sat 6pm /to 9pm`
This adds a deadline task with the description "Taylor Swift concert", and time period from "Sat 6pm" to "9pm" to the list of tasks.
Expected output:
```
Got it. I've added this task:
[E][ ] Taylor Swift concert (from: Sat 6pm to: 9pm)
Now you have 1 tasks in the list.
```

## Listing all tasks
Command: `list`
What it does: Lists all of the tasks, including the state of each task.

Example: `list`
This lists all of the tasks in the task list.
Expected output:
```
Here are the tasks in your list:
1. [T][ ] math homework
2. [D][ ] english assignment (by: Dec 02 2024 23:59)
3. [E][ ] Taylor Swift concert (from: Sat 6pm to: 9pm)
```

## Marking a task as done
Command: `mark <INDEX>`
What it does: Marks the task at the specified index in the task list as done.
Tip: To identify the index of the task, use the `list` command to show the indexes of all tasks.

Example: `mark 1`
This marks the task at index 1 in the task list as done.
Expected output:
```
Nice! I've marked this task as done:
[T][X] math homework
```

## Unmarking a task as undone
Command: `unmark <INDEX>`
What it does: Unmarks the task at the specified index in the task list as undone.
Tip: To identify the index of the task, use the `list` command to show the indexes of all tasks.

Example: `unmark 1`
This unmarks the task at index 1 in the task list as undone.
Expected output:
```
OK, I've marked this task as not done yet:
[T][ ] math homework
```

## Deleting a task in the list
Command: `delete <INDEX>`
What it does: Deletes the task at the specified index in the task list.
Tip: To identify the index of the task, use the `list` command to show the indexes of all tasks.

Example: `delete 1`
This deletes the task at index 1 in the task list. The indexes of the tasks originally after the deleted task will decrease by 1.
Expected output:
```
Noted. I've removed this task:
[T][ ] math homework
Now you have 2 tasks in the list.
```

## Finding specific tasks(s) in the list by keyword
Command: `find <KEYWORD>`
What it does: Finds the task(s) in the list whose description matches the keyword. The keyword is case insensitive and partial matches are also returned.

Example: `find english`
This finds the task(s) in the list whose description matches "english" partially or fully. 
Expected output:
```
Here are the matching tasks in the list:
1. [D][ ] english assignment (by: Dec 02 2024 23:59)
2. [T][ ] english essay
```

## Terminating the program
Command: `bye`
What it does: The program is terminated.

Example: `bye`
The program is terminated.
Expected output:
The Jojo chat window closes. 
