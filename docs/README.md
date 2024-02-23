# **USER GUIDE: Tam the Task Manager**

Tam is a desktop task manager app, optimized for all Command Line Interface (CLI) lovers.
Add tasks, remove tasks, mark them as done or undone, all with just your keyboard!

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `tam.jar` from [here]() to your computer.
3. Copy the file to the folder you want to use as the home folder for your Toothless.
4. Open a command terminal and `cd` to the home folder. Then run `java -jar Tam.jar` to start the app.
5. A GUI similar to the below should appear in a few seconds.

![Screenshot of application](Ui.png)

6. Type the command in the command box and press Enter to execute it. e.g. typing `list` and pressing Enter will list all the tasks in the task list.

## Features

### Notes about the command format

> [!TIP]
> - Words in `[]` are the parameters supplied by the user (omit these square brackets when you fill your own information)
    e.g. in `todo [DESCRIPTION]`, `[DESCRIPTION]` is a parameter which can be used as `todo read book`.
> - Commands with multiple parameters must be specified in the correct order.
  e.g. `event [DESCRIPTION] /from [DATE] /to [DATE]` must be specified in that exact order.
> - Dates must be in YYYY-MM-DD format
  e.g. 24th February 2024 is 2024-02-24
> - The symbol `|` cannot be used in any of the command descriptions.


### Adding todos: `todo`

Add a todo task to the task list.

> Format: `todo [DESCRIPTION]`

Example: `todo Laundry`

### Adding deadlines: `deadline`

Add a deadline task to the task list.

> Format: `deadline [DESCRIPTION] /by [DATE IN YYYY-MM-DD]`

Example: `deadline Assignment /by 2024-02-20`

### Adding events: `event`

Add an event task to the task list.

> Format: `event [DESCRIPTION] /from [DATE IN YYYY-MM-DD] /to [DATE IN YYYY-MM-DD]`

Example: `event Camp /from 2024-02-23 /to 2024-02-26`

### List out all tasks: `list`

List out all of your current tasks.

> Format: `list`

Example: `list`

### Find tasks by keyword: `find`

This command finds tasks in the task list that contain the keyword.

> Format: `find [KEYWORD]`

The keyword is case-sensitive.

The keyword can be a substring of the task description.

Example: `find book`

### Mark a task as done: `mark`

This command marks a task as done.

> Format: `mark [TASK NUMBER IN LIST]`

The task number can be obtained by using the `list` command.

Example: `mark 4`

### Mark a task as not done: `unmark`

This command marks a task as not done.

> Format: `unmark [TASK NUMBER IN LIST]`

The task number can be obtained by using the `list` command.

Example: `unmark 2`

### Delete a task: `delete`

This command deletes a task from the task list.

> Format: `delete [TASK NUMBER IN LIST]`
> 
> Do note that the `[TASK NUMBER IN LIST]` should be a valid task number in the list.

The task number can be obtained by using the `list` command.

Example: `delete 3`

### Delete all tasks: `delete all`

This command deletes a task from the task list.

> Format: `delete all`

The task number can be obtained by using the `list` command.

Example: `delete all`

### Exit application: `bye`

This command exits the application.

> Format: `bye`

Example: `bye`

## Saving your task list

Your task list is automatically saved in the hard disk after each command is entered.
No special command needs to be entered to save the list.

## Command summary


| Action           | Format, Examples                                                                                                                       |
|------------------|----------------------------------------------------------------------------------------------------------------------------------------|
| Add todo         | `todo [DESCRIPTION]` <br> e.g., `todo read book`                                                                                       |
| Add deadline     | `deadline [DESCRIPTION] /by [DATE IN YYYY-MM-DD]` <br> e.g., `deadline Assignment /by 2024-02-24`                                      |
| Add event        | `event [DESCRIPTION] /from [DATE IN YYYY-MM-DD] /to [DATE IN YYYY-MM-DD]` <br> e.g., `event Camp /from 2024-02-23 /to 2024-02-26`      |
| List             | `list`                                                                                                                                 |
| Find             | `find [KEYWORD(S)]` <br> e.g., `find book`                                                                                             |
| Mark as done     | `mark [TASK NUMBER IN LIST]` <br> e.g., `mark 1`                                                                                       |
| Mark as not done | `unmark [TASK NUMBER IN LIST]` <br> e.g., `unmark 4`                                                                                   |
| Delete           | `delete [TASK NUMBER IN LIST]` <br> e.g., `delete 5`                                                                                   |
| Delete all tasks | `delete all`                                                                                                                           |
| Exit             | `bye`                                                                                                                                  |
