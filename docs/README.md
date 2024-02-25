# DinoBot User Guide

Welcome to DinoBot, your virtual assistant! This user guide will help you get started with using DinoBot and explore its features.

## Getting Started

DinoBot is a chatbot designed to assist you with task management. To start interacting with DinoBot, follow these steps:

1. **Set up**: Ensure that you have Java 11 or above installed in your Computer.

2. **Installation**: Get the most recent version of duke.jar from here and Move the downloaded file to the directory you wish to designate as Dino's home folder.

3. **Launch**: Launch a command terminal, navigate (cd) to the directory containing the .jar file, and execute java -jar dino.jar.

4. **Start**: Enter a command into the chat box and hit Enter to execute it.

## Usage

### `list`

Displays all tasks currently in the task list.

Format: `list`

Example usage:

```text
list
```

Expected outcome:

All tasks in the task list are shown.

```text
Here are the tasks in your list:
1. [D][X] CS2109 Problem Set 2 (by 23:59 27 Feb 2024)
2. [T][ ] Groceries Shopping
3. [E][ ] OpenGL Workshop (from 16:00 12 March 2024 to 18:00 Tuesday 12 March 2024)
You have 3 tasks in your list.
```

### Reminders

Never miss an important event again with DinoBot's reminder feature. Use these commands to set reminders:

- `remind me to <task> at <time>`: Set a reminder for a specific task at a given time.
- `remind me to <task> in <duration>`: Set a reminder for a task after a certain duration.

### `todo`

Adds a new Todo task to the list.

Format: `todo <DESCRIPTION>`

Example usage:

```text
todo Do Laundry
```

Expected outcome:

A new Todo task will be added to the list.

```text
Got it. I've added this task:
[T][ ] Do Laundry
Now you have 1 task in total.
```


### `deadline`

Adds a new deadline task in the list.

Format: `deadline <DESCRIPTION> /by <DUE_DATE>`

Example usage:

```text
deadline Tutorial Problem /by 28/02/2024 2359
```

Expected outcome:

A new Deadline task will be added to the list.

```text
Got it. I've added this task:
[D][ ] Tutorial Problem by 23:59 28 Feb 2024
Now you have 2 task in total.
```

### `event`

Adds a new deadline task in the list.

Format: `event <DESCRIPTION> /from <START_TIME> /by <END_TIME>`

Example usage:

```text
event Orientation Camp /from 01/03/2024 1000 /to 03/03/2024 1800 
```

Expected outcome:

A new Event task will be added to the list.

```text
Got it. I've added this task:
[E][ ] Orientation Camp from 10:00 01 Mar 2024 to 18:00 03 Mar 2024
Now you have 3 task in total.
```

### `find`

Search tasks based on a keyword.

Format: `find <KEYWORD>`

Example usage:

```text
find Tutorial
```

Expected outcome:

All tasks with descriptions containing the keyword will be displayed.

```text
Here are the matching tasks in your list:
1. [D][ ] Tutorial Problem by 23:59 28 Feb 2024
```

### `delete`

Search tasks based on a keyword.

Format: `delete <TASK_NUMBER>>`

Example usage:

```text
delete 1
```

Expected outcome:

The task with the specified task number will be removed.

```text
Noted. I've removed this task:
[T][ ] Do Laundry
You now have 2 tasks in total.
```

```text
delete 1
```

Expected outcome:

The task with the specified task number will be removed.

```text
Noted. I've removed this task:
[T][ ] Do Laundry
You now have 2 tasks in total.
```

### `mark`

Marks a task as completed.

Format: `mark <TASK_NUMBER>`

Example usage:

```text
mark 1
```

Expected outcome:

The task with the specified task number will be marked as incomplete.

```text
Nice! I've marked this task as done:
[D][X] Tutorial Problem by 23:59 28 Feb 2024
```

### `bye`

Terminates the DinoBot program.

Format: `bye`

Example usage:

```text
bye
```

Expected outcome:

The DinoBot program will be terminated.

```text
Goodbye! Hope to see you again soon.
```

## Command Summary

| Command    | Usage                                                   |
|------------|---------------------------------------------------------|
| `list`     | `list`                                                  |
| `todo`     | `todo <DESCRIPTION>`                                    |
| `deadline` | `deadline <DESCRIPTION> /by <DUE_DATE>`                 |
| `event`    | `event <DESCRIPTION> /from <START_TIME> /by <END_TIME>` |
| `find`     | `find <KEYWORD>`                                        |
| `delete`   | `delete <TASK_NUMBER>`                                  |
| `mark`     | `mark <TASK_NUMBER>`                                    |
| `unmark`   | `unmark <TASK_NUMBER>`                                  |
| `bye`      | `bye`                                                   |


---

We're constantly working to improve DinoBot and provide the best user experience.

Enjoy using DinoBot!
