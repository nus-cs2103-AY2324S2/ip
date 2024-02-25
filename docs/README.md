# Alpa User Guide

![img.png](Ui.png)

Alpa is your fluffy task manager. It helps you keep track of todos, deadlines, and events - neatly organized in one place!

### Notes about command format

- Words in uppercase are to be supplied by the user.
    e.g. `todo TASK`, `TASK` is a parameter which can be used as `todo run`.

- Items in square brackets are optional.
  e.g. `deadline TASK /by DATE [TIME]`, can be used as `deadline run /by 24 Feb 2024` or as `deadline run /by 24 Feb 2024 11:59pm`.

- Extraneous parameters for commands that do not take in parameters (such as `list`) will be ignored.
  e.g. if the command specifies `list asdagsd`, it will be interpreted as `list`.


## Exiting the Programme: `help`

Format: `help`

Example: `help`

```
I am Alpa - Your Fluffy Task Manager!
Here are some commands you can use:
  1. list - lists all the tasks you have!
  2. todo <description> - Adds a todo task
  3. deadline <description> /by <date/time> - Adds a new deadline task
  4. event <description> /at <date/time> - Adds a new event task
  5. mark <task number> - Marks a task as done
  6. unmark <task number> - Marks a task as not done
  7. delete <task number> - Deletes a task
  8. bye - Exits the application
  9. help - Displays this help message
```

## Adding Todos: `todo`

Need to remember a simple task? Add a todo!

Format: `todo TASK`

For example: `todo review lecture`

This will confirm that the task is successfully added:

```
You added a task human!

     [T][ ] review lecture

Now you have 3 tasks in your list!
```

## Adding Deadlines: `deadline`

Got a deadline coming up? Let's track it!

Format: `deadline TASK /by DATE [TIME]`

For example: `deadline submit assignment /by 23 Feb`

This will confirm that the task is successfully added:

```
You added a task human!

    [D][ ] submit assignment (by: Feb 23 2024 11:59 PM)

Now you have 3 tasks in your list!
```

## Adding Events: `event`

Attending an event? Don't forget it!

Format: event TASK /from START_DATE [START_TIME] /to END_DATE [END_TIME]

For example: `event meeting /from 8 Mar 3pm /to 8 Mar 4pm`

```
You added a task human!

     [E][ ] friend's wedding (from: Mar 8 2024 12:00 PM to: Mar 8 2024 4:00 PM)

Now you have 3 tasks in your list!
```

## Listing all Tasks: `list`

Want to see all your tasks? Just list them!

Format: list

For example: `list`

```
Your list, human!

  1. [T][ ] read book
  2. [D][ ] return library books (by: Dec 11 2024 5:00 PM) 
  3. 
```
## Finding tasks: `find`

Looking for a specific task? Find it easily.

Format: find KEYWORD

- Searches descriptions only.
- Returns tasks that contain the keyword, e.g., `book` will match `read book`.

For example: `find quiz`

```
Here are the matching tasks in your list:

[D][ ] finish quiz (by: Dec 11 2024 4:00 PM)
```

## Deleting Tasks: `delete`

Don't need a task anymore? Delete it.

Format: `delete INDEX`

- Deletes the task at the specified `INDEX`.
- `INDEX` is index number shown in the displayed task list. 
- The index must be a positive integer 1, 2, 3, ...

For example: `delete 3`

```
Removed this task for you, human.

[E][ ] friend's wedding (from: Mar 8 2024 12:00 PM to: Mar 8 2024 4:00 PM)

Now you have 2 tasks left human!
```

## Marking Tasks as Done: `mark`

Complete a task? Mark it off.

Format: `mark INDEX`

- Marks the task at the specified `INDEX`.
- `INDEX` refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...

For example: `mark 5`

```
Marked as done, human!

    [T][X] revise for exam
```

## Unmarking Tasks: `unmark`

Jumped the gun? Mark a task as not done.

Format: `unmark INDEX`

- Unmarks the task at the specified `INDEX`.
- `INDEX` refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...

For example: `unmark 5`

```
Not done with this yet, human?

    [T][ ] revise for exam
```

## Exiting the Programme: `bye`

Done for now? Say goodbye to Alpa.

Format: `bye`

Example: `bye`

Alpa will close and await your next set of tasks!