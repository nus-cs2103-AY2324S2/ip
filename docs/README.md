# Greg
Welcome to the Greg User Guide!

![User interface](Ui.png)

# Features
- [X] Add and remove todos, deadlines and events!
- [X] Search for a particular task!
- [X] Get your tasks for a particular day!
- [X] Mark and unmark your tasks as you complete them!

#  Thank you for choosing Greg!
Your all-in-one task organiser and
reminder chatbot is happy to help you!

# Set Up
1. Download the JAR file from [here](https://github.com/whelan-low/ip/releases/tag/A-Release). (The file name is Greg v0.2.jar)
2. Preferably, set up a folder to put the JAR file inside
3. Ensure you have Java 11 installed: [link is here](https://www.oracle.com/java/technologies/downloads/#java11)
4. Run the JAR file, and let Greg do the rest!

# Commands Available

### Legend:

- (optional_parameter)

- <compulsory_parameter>

## Adding tasks

## todo

Creates a Todo task that has a `name` field only.

Syntax: `todo <name>` or `t <name>`

- `name` is any non-empty string.

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

- `name` is any non-empty string.

- `deadline` field should be of the format `d/M/YY HH:mm` (e.g. `21/2/24 09:00`)

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

- `name` is any non-empty string.

- `startDate` and `endDate` field should be of the format `d/M/YY HH:mm` (e.g. `21/2/24 09:00`)

Example Command: `event piano exam /from 24/2/24 10:00 /to 24/2/24 11:00`

Expected Output:
```
Alright. Adding this task
[E][ ] piano exam (from: 24 Feb 2024 10:00
to: 24 Feb 2024 11:00)
You now have 5 task(s)
```

## Removing tasks

## clear
Clears the entire task list

Syntax: `clear`

## delete

Deletes the task with the specified index from the task list

Syntax: `delete <index>`

- `index` must be a positive integer between 1 and the number of items in the task list

Example Command: `delete 1`

Expected Output:
```
Alright. Removing this task
[T][X] work
You have 2 task(s) left
```

## Listing and searching

## find

Returns all the tasks that match the search query

Syntax: `find <search_query>`

- `search_query` must be a string. It is case-sensitive (i.e. `John Doe` does not match `john doe`)

Example Command: `find study`

Expected Output:
```
1: [D][ ] study (by: 21 Feb 24 14:00)
2: [D][ ] study CS2103T (by 22 Feb 24 10:00)
```

## list

Returns the list of items in your task list

Syntax: `list`

Expected Output:
```
1: [T][ ] work
2: [D][ ] study (by: 21 Feb 24 14:00)
3: [D][ ] study CS2103T (by 22 Feb 24 10:00)
```

## schedule

Returns all the events and deadline tasks that fall on the date specified.

If no date is specified, defaults to today.

Syntax: `schedule (date)`

- `date` should be of the form `d/M/YY` (e.g. 22/2/24)

Example Command: `schedule 22/2/24`

Expected Output:
```
Searching for tasks on 2024-02-22:
1: [D][ ] study CS2103T (by: 22 Feb 2024 10:00)
2: [D][ ] practice piano (by: 22 Feb 2024 10:00)
```

## Marking and un-marking

## mark
Marks a task with the specified index as done

Syntax: `mark <index>`

- `index` must be a positive integer between 1 and the number of items in the task list

Example Command: `mark 1`

Expected Output:
```
Good job! You have completed this task:
[T][X] work
```

## unmark

Marks a task with the specified index as not completed

Syntax: `unmark <index>`

- `index` must be a positive integer between 1 and the number of items in the task list

Example Command: `unmark 1`

Expected Output:
```
Alright. This task has been unmarked
[T][ ] work
```

## Miscellaneous

## help

Provides the help for the command specified.

If command not specified, returns the guide for the command 'help'

Syntax: `help (command)`

Example: `help event`

Output:
```
event or e: Adds an event task with the specified name, start date/time, and end date/time
Syntax: event <taskName> /from <startDate> /to <endDate>
or e <taskName> /from <startDate> /to <endDate>
startDate and endDate to be specified in the format d/M/YY HH:mm (e.g. 21/2/24 10:00)
```

- Note: For commands that require at least 1 compulsory parameter, giving no parameters brings up the help page for that command
  (e.g. `todo` and `event` do the same thing as `help todo` and `help event` respectively)

## bye
Quits the program

Syntax: `bye`










