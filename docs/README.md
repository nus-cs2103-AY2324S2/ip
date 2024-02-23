# Shon User Guide

![Screenshot of Shon chatbot](Ui.png)

Shon is a chatbot application that helps you easily manage your tasks and your notes!

There are 3 types of tasks it can help you track:
1. Todo: tasks with a description
2. Deadline: tasks with a description and a given date and time as deadline
3. Event: tasks with a description, and a from and to date time of the event

## General Commands

### Viewing help: `help`

Shows a message with all the available commands.

Format: `help`

Output:
```
The following command are available:
1. help: displays this current list of commands available
2. tlist: displays your current task list
3. todo <desc>: adds a Todo task into your task list with the description
4. deadline <desc> /by <datetime>: adds a Deadline task into your task list with the description and datetime.
Datetime must be given in dd/mm/yyyy hhmm
5. event <desc> /from <datetime> /to <datetime>: adds an Event task into your task list with the description and
duration. Datetime must be given in dd/mm/yyyy hhmm
6. mark <idx>: marks the task at given index as done
7. unmark <idx>: unmarks the task at given index as done
8. deltask <idx>: deletes the task at given index
9. find <keyword>: finds all tasks with matching keyword
10. nlist: displays your current note list
11. note <text>: adds a note with the given text to your note list
12. delnote <idx>: deletes the note at the given index
13. bye: exits the app
```

### Exiting the program: `bye`

Exits the program.

Format: `bye`

## Task-Related Commands

### Listing all tasks: `tlist`

Shows a list of all your tasks and their completion status.

Format: `tlist`

Example output:
```
Here are the tasks in your list:
1.[T][X] read book
2.[D][ ] quiz (by: 25 Feb 2024 1200)
3.[E][ ] meeting (from: 26 Feb 2024 0900 to: 26 Feb 2024 1100)
```

> :information_source: The`[T]`, `[D]`, and `[E]` indicates that the task is a Todo task, Deadline task, and Event
> task respectively.

### Adding a Todo task: `todo`

Adds a Todo task with the given description to the list of tasks.

Format: `todo DESCRIPTION`

Example input: `todo read book`

Example output:
```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 task in the list.
```

### Adding a Deadline task: `deadline`

Adds a Deadline task with the given description and the datetime as deadline to the list of tasks.

Format: `deadline DESCRIPTION /by DD/MM/YYYY HHMM`

Example input: `deadline assignment /by 25/02/2024 1300`

Example output:
```
Got it. I've added this task:
  [D][ ] assignment (by: 25 Feb 2024 1300)
Now you have 2 tasks in the list.
```

### Adding an Event task: `event`

Adds an Event task with the given description and a from and to datetime to the list of tasks.

Format: `event DESCRIPTION /from DD/MM/YYYY HHMM /to DD/MM/YYYY HHMM`

Example input: `event meeting /from 26/02/2024 0900 /to 26/02/2024 1100`

Example output:
```
Got it. I've added this task:
  [E][ ] meeting (by: 25 Feb 2024 1300)
Now you have 2 tasks in the list.
```

### Marking a task: `mark`

Marks a task as done.

Format: `mark INDEX`

* Marks the task at the specified `INDEX` as done, with an `X`.
* The index refers to the index number shown when you use the `tlist` command.
* The index must be a positive integer 1, 2, 3, ...

Example input: `mark 1`

Example output:
```
Nice! I've marked this task as done:
  [T][X] read book
```

### Unmarking a task: `unmark`

Marks a task as not done.

Format: `unmark INDEX`

* Marks the task at the specified `INDEX` as not done, by removing the `X`.
* The index refers to the index number shown when you use the `tlist` command.
* The index must be a positive integer 1, 2, 3, ...

Example input: 'unmark 1'

Example output:
```
OK, I've marked this task as not done yet:
  [T][ ] read book
```

### Deleting a task: `deltask`

Deletes the specified task from the list of tasks.

Format: `deltask INDEX`

* Deletes the task at the specified INDEX.
* The index refers to the index number shown when you use the `tlist` command.
* The index must be a positive integer 1, 2, 3, ...

Example input: `deltask 2`

Example output:
```
Noted. I've removed this task:
  [D][ ] quiz (by: 25 Feb 2024 2359)
Now you have 1 task in the list.
```

### Finding tasks by keyword: `find`

Finds tasks where their descriptions contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

> :exclamation: Item(s) in square brackets are optional.

* The search is case-insensitive e.g. `book` will match `Book`.
* Partial matching is allowed e.g. 'bo' search will yield 'book' as a result.

Example input: `find bo`

Example output:
```
Here are the matching tasks in your list:
1.[T][X] read book
```

## Note-Related Commands

### Listing all notes: `nlist`

Shows a list of all your notes.

Format: `nlist`

Example output:
```
Here are your notes:
1. shoe size US10
```

### Adding a note: `note`

Adds a note to the list of notes.

Format: `note TEXT`

Example input: `note shoe size US10`

Example output:
```
Got it. I've added this note:
  shoe size US10
```

### Deleting a note: `delnote`

Deletes the specified note from the list of notes.

Format: `delnote INDEX`

* Deletes the note at the specified INDEX.
* The index refers to the index number shown when you use the `nlist` command.
* The index must be a positive integer 1, 2, 3, ...

Example input: `delnote 2`

Example output:
```
This note has been deleted:
  shoe size US10
```

## Saving Data

Shon chatbot data are saved in the hard disk automatically after any command
that changes the data. There is no need to save manually.
