# Taro User Guide

Taro is a task management application that helps you keep track of different tasks. It is a desktop application that is 
designed to be used via a GUI. If you can type fast, Taro can help you manage your tasks faster than other apps.

![Taro](/Ui.png.png)

## Viewing help: `help`
Shows a message that contains all the commands that Taro can understand.

Format: `help`

## Adding tasks : `todo`, `deadline`, `event`

Adds either a Todo, Deadline or Event task to the app.

Format: `todo <description>`, `deadline <description> /by <date>`, `event <description> /from <date> /to <date>`

Examples:
- `todo read book`
- `deadline homework /by 2024-09-17`
- `event career fair /from 2024-09-17 /to 2024-09-18`

Expected Output:
```
1.[T][] read book
2.[D][] homework (by: 2024-09-17)
3.[E][] career fair (from: 2024-09-17 to: 2024-09-18)
```

## Deleting a task : `delete`
Delete a specified task.

Format: `delete INDEX`
- Deletes the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...

Examples:
- `delete 2` deletes the 2nd task in the task list.

Expected Output:
```
1.[T][] read book
2.[D][] homework (by: 2024-09-17)
3.[E][] career fair (from: 2024-09-17 to: 2024-09-18)

delete 2

1.[T][] read book
2.[E][] career fair (from: 2024-09-17 to: 2024-09-18)
```

## Marking a task: `mark`
Mark a specified task. 

Format: `mark INDEX`
- Marks the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...

Examples:
- `mark 2` marks the 2nd task in the task list.

Expected Output:
```
1.[T][] read book
2.[D][] homework (by: 2024-09-17)
3.[E][] career fair (from: 2024-09-17 to: 2024-09-18)

mark 2

1.[T][] read book
2.[D][X] homework (by: 2024-09-17)
3.[E][] career fair (from: 2024-09-17 to: 2024-09-18)
```

## Unmarking a task: `unmark`
Unmark a specified task.

Format: `mark INDEX`
- Unmarks the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...

Examples:
- `unmark 1` unmarks the 1st task in the task list.

Expected Output:
```
1.[T][X] read book
2.[D][] homework (by: 2024-09-17)
3.[E][] career fair (from: 2024-09-17 to: 2024-09-18)

unmark 2

1.[T][] read book
2.[D][] homework (by: 2024-09-17)
3.[E][] career fair (from: 2024-09-17 to: 2024-09-18)
```


## Finding tasks by keyword : `find`
Find tasks that contain the keyword.

Format: `find <keyword>`
- The search is case-sensitive. 
- The order of the keyword matters.

Examples:
- `find book` returns `[T][] read book`


## Listing all tasks : `list`
Shows a list of all tasks in the app.

Format: `list`

## Exiting the program : `bye`
Exits the program.

Format: `bye`