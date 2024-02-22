# Teletubbi User Guide

![GitHub Logo](Ui.png)

Teletubbi is a **desktop app for managing tasks, optimized for use via a Graphical User Interface** (GUI).
If you are *clumsy* with your typing, you will love Teletubbi's fuzzy search capabilities.  

## Adding deadlines

Adds a task with a specified description and deadline. 

Format: `deadline DESCRIPTION /by DEADLINE`

Examples: 
- `deadline grow mushrooms /by 2024-01-28`
- `deadline eat mushrooms /by Sunday`

Teletubbi will output a confirmation message which includes
- the deadline added
- the updated number of tasks

Note: deadlines entered in *YYYY-MM-DD* format will be converted
to a more readable *MMM DD YYYY* format
```
Got it. I've added this task:
[D][ ] grow mushrooms (by: Jan 28 2024)
Now you have 1 task in the list.
```

## Adding to-dos

Adds a task to be completed.

Format: `todo DESCRIPTION`

Examples:
- `todo eat`
- `todo eat mushrooms`

Teletubbi will output a confirmation message which includes
- the task added
- the updated number of tasks

```
Got it. I've added this task:
[T][ ] eat
Now you have 1 task in the list.
```


## Adding events

Adds a task with a specified description, start time and end time.

Format: `event DESCRIPTION /from START /to END`

Examples:
- `event eat mushrooms /from today /to tmr`

Teletubbi will output a confirmation message which includes
- the event added
- the updated number of tasks

```
Got it. I've added this task:
[E][ ] eat mushrooms (from: today to: tmr)
Now you have 1 task in the list.
```

## Listing tasks : `list`

Lists tasks added according to
the order they were added.

Format: `list`

Example output:
```
Here are the tasks in your list:
1. [D][ ] grow mushrooms (by: Jan 28 2024)
2. [T][ ] eat
3. [E][ ] eat mushrooms (from: today to: tmr)
```

## Marking tasks : `mark`

Marks the task at the specified index as done.

Format: `mark INDEX`

- The index must be a positive integer.

Examples:
- `mark 2`

Teletubbi will output a confirmation message including the marked task and updated status.
```
Nice! I've marked this task as done:
[T][X] eat
```

## Unmarking tasks : `unmark`

Marks the task at the specified index as undone.

Format: `unmark INDEX`

- The index must be a positive integer.

Examples:
- `unmark 2`

Teletubbi will output a confirmation message including the unmarked task and updated status.
```
Ok, I've marked this task as not done yet
[T][ ] eat
```

## Deleting tasks : `delete`

Deletes the task at the specified index.

Format: `delete INDEX`

- The index must be a positive integer.

Examples:

- `delete 1`

Teletubbi will output a confirmation message and the updated number of tasks.

```
Noted. I've removed this task:
[T][ ] eat
Now you have 0 tasks in the list.
```

## Finding tasks: `find`

Finds tasks that match the specified keyword or are similar enough to the keyword.

Format: `find KEYWORD`

- KEYWORD may have multiple words, and will be treated as a block.
- Tasks with high similarity between task descriptions and KEYWORD are returned.
- Tasks with descriptions containing KEYWORD are also returned.

Examples: 
- `find mushroo`

Teletubbi will output a list of matching tasks. 
```
Here are the matching tasks in your list:
1. [T][ ] mushroom
2. [T][ ] ushroo
3. [T][ ] mushroo
```

## Exiting the program: `bye`

Exits Teletubbi.

## Saving the data

Teletubbi data is saved in the hard disk automatically after any command is made.

---

## Command summary

| Action | Format, Examples                                                                                         |
|-------|----------------------------------------------------------------------------------------------------------|
| deadline | `deadline DESCRIPTION /by DEADLINE` e.g. `deadline eat mushrooms /by tmr`  `deadline eat /by 2023-02-02` |
| todo  | `todo DESCRIPTION`, e.g. `todo eat mushrooms`                                                            |
| event | `event DESCRIPTION /from START /to END` e.g. `event eat /from today /to tmr`                             |
|list| `list`                                                                                                   |
|mark| `mark INDEX` e.g. `mark 1`                                                                               |
|unmark| `unmark INDEX` e.g. `unmark 1`                                                                           |
|delete| `delete INDEX` e.g. `delete 1`                                                                           |
|find| `find KEYWORD` e.g. `find grow mushroom`                                                                 |



