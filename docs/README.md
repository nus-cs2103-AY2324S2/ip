# Using BartenderBob

![Screenshot of BartenderBob chatbot.](Ui.png)

Welcome to the _BartenderBob user guide_! Get step-by-step instructions on how to use BartenderBob,
and learn about its features below!

## Feature notes
- Dates are in the form of `YYYY-MM-DD`.
- Your tasks are automatically saved! No need to worry about losing them!
- You can access the saved tasks in a file called `tasks.txt` in the `data` folder.
- Remember to follow the format of the commands to get the best results!

## Adding todos!
Add a task that you have to do!

Example: `todo buy bread`
```
Got it. I've added this task:
[T][ ] buy bread
Now you have 1 tasks in the list.
```

## Adding deadlines!

Have a task that has a deadline? Just add it in!

Example: `deadline return book /by 2023-10-01`

```
Got it. I've added this task:
[D][ ] return book (by: Oct 1 2023)
Now you have 1 tasks in the list.
```

## Adding events!

Add an event that you have to attend!

Example: `event project meeting /from 2023-10-10 /to 2023-10-12`

```
Got it. I've added this task:
[E][ ] project meeting (from: Oct 10 2023 to: Oct 12 2023)
Now you have 1 tasks in the list.
```

## Marking/Unmarking tasks

Mark a task as done when you're done with it!
Remember to use the task number, which you can find using the `list` command.

Example: `mark 1`

```
Nice! I've marked this task as done:
[T][X] buy bread
```
OR, if you want to unmark it:
Example: `unmark 1`
```
Nice! I've unmarked this task:
[T][ ] buy bread
```

## Delete tasks

Delete a task that you no longer need!

Example: `delete 1`

```
Noted. I've removed this task:
[T][X] buy bread
Now you have 0 tasks in the list.
```

## Find tasks

Find tasks that contain a certain keyword!

Example: `find book`

```
Here are the matching tasks in your list:
1. [D][ ] return book (by: Oct 1 2023)
```

## Exit the program

Exit the program when you're done!

Example: `bye`

```
Bye! Another round next time!
```

## Command summary
- `todo <description>`: Adds a todo task.
- `deadline <description> /by <YYYY-MM-DD>`: Adds a deadline task.
- `event <description> /from <YYYY-MM-DD> /to <YYYY-MM-DD>`: Adds an event task.
- `list`: Lists all tasks.
- `mark <task number>`: Marks a task as done.
- `unmark <task number>`: Unmarks a task.
- `delete <task number>`: Deletes a task.
- `find <keyword>`: Finds tasks with a certain keyword.
- `bye`: Exits the program.

