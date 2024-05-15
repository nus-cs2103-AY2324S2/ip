# Chimp User Guide

## Features & Commands
Chimp stores your tasks in a human-readable format under
/data/chimp.txt, allowing exportability to other/custom tools.

Chimp provides the following commands

> Note: dates are expected to be in the format yyyy-mm-dd

### List (`list`/`ls`)
Displays the current list of tasks <br>

Arguments:
- None

Format:
```
list
```

Example: <br>
`list` will display all current tasks in the task list

----------
### Mark (`mark`/`m`)
Marks a task as complete <br>

Arguments:
- **task number:** the list number of the task to be marked

Format:
```
mark <task number>
```
Example: <br>
`list` followed by `mark 1` will mark the first task as complete

----------
### Unmark (`unmark`/`u`)
Marks a task as incomplete <br>

Arguments:
- **task number:** the list number of the task to be unmarked

Format:
```
unmark <task number>
```
Example: <br>
`list` followed by `unmark 2` will mark the second task as incomplete

----------
### Todo (`todo`/`t`)
Adds a new todo task <br>

Arguments:
- **description:** the description of the todo task

Format:
```
todo <description>
```
Example: <br>
`todo Buy milk` will add a new todo task with the description "Buy milk"

----------
### Event (`event`/`e`)
Adds a new event task <br>

Arguments:
- **description:** the description of the event
- **start date:** the start date of the event, formatted as YYYY-MM-DD
- **end date:** the end date of the event, formatted as YYYY-MM-DD

Format:
```
event <description> /from <start date> /to <end date>
```
Example: <br>
`event Team meeting /from 2023-03-15 /to 2023-03-15` will add a new event task with the description "Team meeting" that starts and ends on March 15th, 2023

----------
### Deadline (`deadline`/`d`)
Adds a new deadline task <br>

Arguments:
- **description:** the description of the task with a deadline
- **by date:** the due date for the task, formatted as YYYY-MM-DD

Format:
```
deadline <description> /by <by date>
```
Example: <br>
`deadline Submit report /by 2023-04-20` will add a new task with the description "Submit report" and a deadline of April 20th, 2023

----------
### Delete (`delete`)
Removes a task from the list <br>

Arguments:
- **task number:** the list number of the task to be deleted

Format:
```
delete <task number>
```
Example: <br>
`delete 3` will remove the third task from the list

----------
### Find (`find`)
Searches for tasks containing the provided text in their descriptions <br>

Arguments:
- **search query:** the text to search for within task descriptions

Format:
```
find <search query>
```
Example: <br>
`find book` will list all tasks that contain the word "book" in their descriptions

----------
### Bye (`bye`)
Exits the application <br>

Arguments:
- None

Format:
```
bye
```
Example: <br>
`bye` will terminate the application safely

---
## Summary

| Command  | Format                                            | Example                                        |
| -------- | ------------------------------------------------- | ---------------------------------------------- |
| List     | `list`                                            |                                                |
| Mark     | `mark <task number>`                              | `mark 1`                                       |
| Unmark   | `unmark <task number>`                            | `unmark 2`                                     |
| Todo     | `todo <description>`                              | `todo Buy milk`                                |
| Event    | `event <description> / <start date> / <end date>` | `event Team meeting / 2023-03-15 / 2023-03-15` |
| Deadline | `deadline <description> / <by date>`              | `deadline Submit report / 2023-04-20`          |
| Delete   | `delete <task number>`                            | `delete 3`                                     |
| Find     | `find <search query>`                             | `find book`                                    |
| Bye      | `bye`                                             |                                                |
