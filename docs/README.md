# Greg User Guide

![User interface](Ui.png)

# Thank you for choosing Greg!
Your all-in-one task organiser and 
reminder chatbot is happy to help you!

# Features
### Add and remove todos, deadlines and events!
### Search for a particular task!
### Get your tasks for a particular day!
### Mark and unmark your tasks as you complete them!

# Commands Available
## Help
Provides the help for the command specified.

If command not specified, returns the guide for the command 'help'

Syntax: `help <command>`

Example: `help event`

Output:
```
event or e: Adds an event task with the specified name, start date/time, and end date/time
Syntax: event <taskName> /from <startDate> /to <endDate>
or e <taskName> /from <startDate> /to <endDate>
startDate and endDate to be specified in the format d/M/YY HH:mm (e.g. 21/2/24 10:00)
```
## bye
Quits the program

Syntax: `bye`
## list
Returns the list of items in your task list

Syntax: `list`

Expected Output:
```
1: [T][ ] work
2: [D][ ] study (by: 21 Feb 24 14:00)
3: [D][ ] study CS2103T (by 22 Feb 24 10:00)
```
## find
Returns all the tasks that match the search query

Syntax: `find <search_query>`

Example Command: `find study`

Expected Output:
```
1: [D][ ] study (by: 21 Feb 24 14:00)
2: [D][ ] study CS2103T (by 22 Feb 24 10:00)
```

## mark
Marks a task with the specified index as done

Syntax: `mark <index>`

Example Command: `mark 1`

Expected Output:
```
Good job! You have completed this task:
[T][X] work
```
## unmark 
Marks a task with the specified index as not completed

Syntax: `unmark <index>`

Example Command: `unmark 1`

Expected Output:
```
Alright. This task has been unmarked
[T][ ] work
```
## delete 
Deletes the task with the specified index from the task list

Syntax: `delete <index>`

Example Command: `delete 1`

Expected Output:
```
Alright. Removing this task
[T][X] work
You have 2 task(s) left
```
## todo
Creates a ToDo task that has a `name` field only.

Syntax: `todo <name>` or `t <name>`

Example Command: `todo work on project`

Expected Output:
```
Alright. Adding this task
[T][ ] work on project
You now have 3 task(s)
```
## deadline
Creates a Deadline task that has a `name` field and a `deadline` field.

Syntax: `deadline <name> /by <deadline>` or `d <name> /by <deadline>`

`deadline` field should be of the format `d/M/YY HH:mm` (e.g. `21/2/24 09:00`)

Example Command: `deadline practice piano /by 22/2/24 10:00`

Expected Output:
```
Alright. Adding this task
[D][ ] practice piano (by: 22 Feb 2024 10:00)
You now have 4 task(s)
```
## event
Creates an Event task that has a `name` field, a `startDate` field, and an `endDate` field.

Syntax: `event <name> /from <startDate> /to <endDate>` or `e <name> /from <startDate> /to <endDate>`

`startDate` and `endDate` field should be of the format `d/M/YY HH:mm` (e.g. `21/2/24 09:00`)

Example Command: `event piano exam /from 24/2/24 10:00 /to 24/2/24 11:00`

Expected Output:
```
Alright. Adding this task
[E][ ] piano exam (from: 24 Feb 2024 10:00
to: 24 Feb 2024 11:00)
You now have 5 task(s)
```
## clear
Clears the entire task list

Syntax: `clear`
## schedule
Returns all the events and deadline tasks that fall on the date specified.

If no date is specified, defaults to today.

Syntax: `schedule <date>`

`date` should be of the form `d/M/YY` (e.g. 22/2/24)

Example Command: `schedule 22/2/24`

Expected Output:
```
Searching for tasks on 2024-02-22:
1: [D][ ] study CS2103T (by: 22 Feb 2024 10:00)
2: [D][ ] practice piano (by: 22 Feb 2024 10:00)

```

