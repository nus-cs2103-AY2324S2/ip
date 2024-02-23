# Luke User Guide
Luke is a simplistic Todo List application that helps you to keep track of your tasks and manage your time more efficiently. It is a desktop app that allows you to manage your tasks using a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

![Ui.png](Ui.png)

## Adding Tasks

To add a task, you can use the `todo` keyword followed by the task description and press the "Send" button.

Example: `todo Go for a run`

```
Got it! I've added this task:
 [T][ ] Go for a run
Now you have 1 task in the list.
```

## Adding Deadlines

To add a deadline task, you can use the `deadline` keyword followed by the task description and the deadline datetime with `/by`. Then, press the "Send" button.

Date Format: `dd/mm/yyyy` or `dd-mm-yyyy` or `yyyy-mm-dd` or `yyyy/mm/dd`

Time Format (optional, default is 00:00): `HH:mm` or `HH[:mm]`

Example: `deadline Do Assignment /by 13/03/2024 23:59`

```
Got it! I've added this task:
 [D][ ] Do Assignment (by: Mar 13 2024 11:59 PM)
Now you have 2 tasks in the list.
```

## Adding Events

To add an event task, you can use the `event` keyword followed by the event description, the start datetime with `/from`, and the end datetime with `/to`. Then, press the "Send" button.

Date Format: `dd/mm/yyyy` or `dd-mm-yyyy` or `yyyy-mm-dd` or `yyyy/mm/dd`

Time Format (optional, default is 00:00): `HH:mm` or `HH[:mm]`

Example: `event Project meeting /from 13/03/2024 13:00 /to 13/03/2024 16:00`

```
Got it! I've added this task:
 [E][ ] Project meeting (from: Mar 13 2024 13:00 to: Mar 13 2024 16:00)
Now you have 3 task in the list.
```

## Marking Tasks as Done

To mark a task as done, you can use the `mark` keyword followed by the index and press the "Send" button.

Example: `mark 1`

```
Nice! I've marked this task as done:
 [T][X] Go for a run
```

## Unmarking Tasks as Undone

To unmark a task as undone, you can use the `unmark` keyword followed by the index and press the "Send" button.

Example: `unmark 1`

```
Nice! I've marked this task as not done yet:
 [T][ ] Go for a run
```

## Finding Tasks

To find tasks containing a specific keyword, you can use the `find` keyword followed by the keyword and press the "Send" button.

Example: `find meeting`

```
Here are the matching tasks in your list:
 1.[E][ ] Project meeting (from: Mar 13 2024 13:00 to: Mar 13 2024 16:00)
```

## Viewing the Task List

To view the list of tasks, you can use the `list` keyword and press the "Send" button.

Example: `list`

```
Here are the tasks in your list:
 1.[T][ ] Go for a run
 2.[D][ ] Do Assignment (by: Mar 13 2024 11:59 PM)
 3.[E][ ] Project meeting (from: Mar 13 2024 13:00 to: Mar 13 2024 16:00)
```

## Deleting Tasks

To delete a task, you can use the `delete` keyword followed by the index and press the "Send" button.

Example: `delete 1`

```
Noted! I've removed this task:
 [T][] Go for a run
Now you have 2 tasks in the list.
```

## Help Page

To view the help page, just type `help` and press the "Send" button.

Example: `help`

```
Here are the commands you can use:
1. todo <description> - Adds a todo task to the list.
2. deadline <description> /by <date> - Adds a deadline task to the list.
3. event <description> /from <date> /to <date> - Adds an event task to the list.
4. list - Lists all the tasks in the list.
5. find <keyword> - Finds all the tasks with the keyword in the list.
6. delete <index> - Deletes the task at the index from the list.
7. mark <index> - Marks the task at the index as done.
8. unmark <index> - Marks the task at the index as not done yet.
9. bye - Exits the application.
10. help - Shows the list of commands you can use.
Examples of date and time formats:
1. dd/MM/yyyy HH:mm
2. yyyy-MM-dd HH:mm
3. yyyy/MM/dd HH:mm
4. dd-MM-yyyy HH:mm
You can choose to enter the time or not, the default time is 00:00.
If you wish to enter the time, please enter the time in 24-hour format such as HH[:mm] after the date.
```


## Exiting Luke

To exit Luke, just type `bye` and press the "Send" button.

Example: `bye`