# Jux User Guide

![Ui.png](Ui.png)

Jux is a desktop chatbot app for managing tasks
with a Graphical user Interface (GUI) and it will remember the tasks added
# Quick Start

1. Ensure you have Java 11 or above installed in your computer.
2. Download the latest jux.jar from [here](https://github.com/justincred/ip/releases/tag/A-Release).
3. Copy the file to your target folder.
4. Open a command terminal, enter the directory you put the jar file in, 
and use the java -jar jux.jar command to run the application.
5. Type commands into the text box and press send or enter to submit.

Some commands that you can try
- `list`: Lists all tasks
- `todo task`: Adds a Todo task to the list
- `deadline task/Mon`: Adds a Deadline task to the list with a date/time
- `event task/Mon/Tue`: Adds an Event task to the list with start and end date/time
- `mark 1`: marks the task
- `unmark 1`: unmarks the task
- `delete 1`: deletes the task
- `bye`: exit the program or click the x
# Features
***Notes for the feature format***
- Dates will be written as DATE and can be given in a variety of formats with optional time, 
    where dd refers to the day, MM refers to the month and yyyy refers to the year. Examples include:
  - "yyyy-MM-dd HH:mm",
  - "dd-MM-yyyy HH:mm",
  - "yyyyMMdd HH:mm",
  - "yyyy MM dd HH:mm",
  - "dd MM yyyy HH:mm",
  - "ddMMyyyy HH:mm"
- Dates can also be given in the format of Mon or Monday where the nearest Monday will be chosen 
and the first letter has to be capitalised
- Task in the format will be written as TASK and it is the task description that is to be provided
- Index of the task will be written as INDEX
- index chosen needs to be between 1 and the length of the list
## Adding todos: `todo`
Adds a Todo task to the list without any dates

Format: `todo TASK`

Example: `todo read manga`

Expected output:

```
Woof I added the task below! 
Did i do a great job?
todo read manga
You have 1 task remaining
```
## Adding deadlines: `deadline`

Adds a Deadline task to the task with a specified date
Format : `deadline TASK/DATE`

Example: `deadline read science/Fri 23:59`

Expected output:

```
Woof I added the task below! 
Did i do a great job?
deadline read science/Fri 23:59
You have 2 task remaining
```

## Adding events `event`

Adds an Event task to the task with a specified start and end date

Format : `event TASK/DATE/DATE`

Example: `event read history/2024-05-05/2024-05-06`

Expected output:
```
Woof I added the task below! 
Did i do a great job?
event read history/2024-05-05/2024-05-06
You have 3 task remaining
```

## Listing all tasks `list`
Shows a list of all the tasks

Format: `list`

Expected output:
```
Here are the tasks in your list! Woof!
1.[T][ ]read manga
2.[D][ ]read science(Feb 23 2024 23:59)
3.[E][ ]go jb (May 05 2024 - May 06 2024)
```
## Marking tasks `mark`
Marks a task as done using its index

index chosen needs to be between 1 and the length of the list

Format: `mark INDEX`

Example: `mark 1`

Expected output:
```
Good Work! I've marked the task as done
[T][X]read manga
```
## Unmarking tasks `unmark`
Unmarks a task as not done using its index

index chosen needs to be between 1 and the length of the list

Format: `unmark INDEX`

Example: `unmark 3`
Expected output:
```
Sure thing! I've marked this task as not done yet. Keep working hard!
[T][X]read manga
```

## Finding tasks `find`
Format: `find desc`

Example: `find read` would return read science and read history 

Expected output:
```
"Woof! I found what you were looking for. Here it is:\n";
1.[T][ ]read manga
2.[D][ ]read science(Feb 23 2024 23:59)
```

## Deleting tasks `delete`
Deletes a task from the task list

Format: `delete INDEX`

Example: `delete 1`
Expected output:
```
Noted. I've removed this task. Have I been a good dog?
[T][X]read manga
You now have 2 tasks remaining
```
## Exiting app `bye`
Closes the app, alternatively, click the x icon

Format: `bye`

