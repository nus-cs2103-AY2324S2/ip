# Lai User Guide

![Screenshot of the user interface for Lai](https://casaarlai.github.io/ip/Ui.png)

_A rather **cynical** personality that helps you keep track of your life._
> Write it down. Written goals have a way of transforming wishes into wants; cant's into cans; dreams into plans; and plans into reality. Don't just think it - ink it! – Michael Korda [(source)](https://www.azquotes.com/quote/856710)

Lai is currently able to keep track of
- [x] Events (with support for start and end dates)
- [x] Deadlines (with support for due dates)
- [x] To-do items
- [ ] Emotional ramblings (hopefully Lai never has to!)

## Adding a ToDo: ```todo```
Adds a to-do task to the list.  

Format: ```todo DESCRIPTION```

Examples: 
+ ```todo read book```
+ ```todo recommend book```

Expected output:
```
Added: [T][] read book
Total number of tasks: 1
```

## Adding a Deadline: ```deadline```
Adds a task with a specific deadline to the list.

Format: ```deadline DESCRIPTION /by YYYY-MM-DD```

Examples:
+ ```deadline return book /by 2024-01-01```
+ ```deadline finish assignment /by 2024-12-13```

Expected output:
```
Added: [D][] return book (by: 01 Jan 2024)
Total number of tasks: 2
```

## Adding an Event: ```event```
Adds an event with a start date and an end date to the list.

Format: ```event DESCRIPTION /from YYYY-MM-DD /to YYYY-MM-DD```

Examples:
+ ```event library book sharing /from 2024-01-02 /to 2024-01-04```
+ ```event band concert /from 2024-04-20 /to 2024-04-21```

Expected output:
```
Added: [E][] library book sharing (from: 02 Jan 2024 to: 04 Jan 2024)
Total number of tasks: 3
```

## Marking a task: ```mark```
Mark a task as done.

Format: ```mark INDEX```

Examples:
+ ```mark 2```

Expected output:
```
You actually did something? Marked done:
[D][X] return book (by: 01 Jan 2024)
```

## Unmarking a task: ```unmark```
Mark a task as not done.

Format: ```unmark INDEX```

Examples:
+ ```unmark 2```

Expected output:
```
Come on now, don't be useless. Marked not done:
[D][] return book (by: 01 Jan 2024)
```

## Listing all tasks: ```list```
Shows a list of all the existing tasks in the list.

Format: ```list```

Examples:
+ ```list```

Expected output:
```
1. [T][] read book
2. [D][] return book (by: 01 Jan 2024)
3. [E][] library book sharing (from: 02 Jan 2024 to: 04 Jan 2024)
```

## Deleting a task: ```delete```
Deletes an existing tasks in the list.

Format: ```delete INDEX```

Examples:
+ ```delete 1```

Expected output:
```
I have deleted: [T][] read book
```
## Finding tasks by their description: ```find```
Finds all tasks with descriptions that contain the given keyword(s)

Format: ```find KEYWORD(S)```

Examples:
+ ```find sharing```
+ ```find book```

Expected output:
```
1. [E][] library book sharing (from: 02 Jan 2024 to: 04 Jan 2024)
```
