# Woody User Guide

![img.png](Ui.png)

Woody frees your mind of having to remember things you need to do. It helps you
track tasks such as events, deadlines and todos - All in one place!

### Notes about command format

- Words in uppercase are to be supplied by the user.

    e.g. `todo TASK`, `TASK` is a parameter which can be used as `todo run`.
- Items in square brackets are optional.

  e.g. `deadline TASK /by DATE [TIME]`, can be used as `deadline run /by 11-11-2024` or as `deadline run /by 11-11-2024 1800`.
- Extraneous parameters for commands that do not take in parameters (such as `list`) will be ignored.

  e.g. if the command specifies `list uierjwghuier`, it will be interpreted as `list`.

## Adding deadlines: `deadline`

What if you want to start using Woody by a certain date? Add a deadline!

Format: `deadline TASK /by DATE [TIME]`

For example: `deadline start using woody /by 11-11-2024`

This will produce the following printout to confirm that the task is successfully added

```
    Got it. I've added this task:
        [D][ ] start using woody (by: Nov 11 2024)
    Now you have 8 tasks in the list.
_________________________________________________________________________
```

## Adding events: `event`

Want to take part in an event? Add an Event!

Format: `event TASK /from DATE [TIME] /to DATE [TIME]`

For example: `event career fair /from 11-11-2024 1300 /to 11-11-2024 1800`

This will produce the following printout to confirm that the task is successfully added

```
    Got it. I've added this task:
        [E][ ] career fair (from: Nov 11 2024 1300 to: Nov 11 2024 1800)
    Now you have 9 tasks in the list.
_________________________________________________________________________
```

## Adding todos: `todo`

Have a task in mind? Add a todo!

Format: `todo TASK`

For example: `todo run`

This will produce the following printout to confirm that the task is successfully added

```
    Got it. I've added this task:
        [T][ ] run
    Now you have 10 tasks in the list.
_________________________________________________________________________
```


## Listing tasks: `list`

Shows a list of all tasks

Format: `list`

For example: `list`

## Finding tasks: `find`

Find tasks that contain any of the given keywords.

Format: `find KEYWORD`

- Only the description is searched
- Tasks that contain the keyword is returned e.g. `cook` will match `todo cooking competition`.

For example: `find run`

```
    Here are the matching tasks in your list:
    1. [T][ ] run 2.4km
    2. [T][ ] run
    3. [D][ ] run (by: Aug 11 2024)
_________________________________________________________________________
```

## Deleting tasks: `delete`

Deletes the specified task from the task list.

Format: `delete INDEX`

- Deletes the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list. 
- The index must be a positive integer 1, 2, 3, ...

For example: `delete 6`
```
    Noted. I've removed this task:
        [D][ ] run (by: Aug 11 2024)
    Now you have 9 tasks in the list.
_________________________________________________________________________
```

## Marking tasks: `mark`

Marks the specified task from the task list as completed.

Format: `mark INDEX`

- Marks the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...

For example: `mark 5`
```
    Nice! I have marked this task as done: 
        [T][X] run
_________________________________________________________________________
```

## Unmarking tasks: `unmark`

Marks the specified task from the task list as not done.

Format: `unmark INDEX`

- Unmarks the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...

For example: `unmark 5`
```
    Ok, I've marked this task as not done yet: 
        [T][ ] run
_________________________________________________________________________
```

## Exiting the program: `exit`

Exits the program.

Format: `exit`