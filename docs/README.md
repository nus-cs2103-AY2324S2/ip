# ALLY User Guide

Introducing ALLY. Your personal Task Tracking assistant. 

![Screenshot of ALLY](Ui.png)
## Features

1. **Starting Ally**: Launch the application from your terminal or command prompt.
2. **Adding Tasks**: Use commands to add tasks. Supported task types include *todos*, *deadlines*, and *events*.
3. **Viewing Tasks**: Easily view all your tasks.
4. **Updating Tasks**: Mark tasks as done or unmark them with simple commands.
5. **Removing Tasks**: No longer need a task? Delete it with a single command.
6. **Searching Tasks**: Search for task based on specific keywords(s).
7. **Archiving Tasks**: Archive a task.

Example: `todo finish cs2103T iP`

"Finish cs2103T iP" is now added into the task list. 

![img.png](img.png)

## ALLY Commands
- `bye`: Exits the application. Your tasks are automatically saved.
- `list`: Lists all the tasks, excluding archived tasks.
- `list all`: Lists all the tasks, including archived tasks.
- `mark <task_number>`: Marks a task as done.
- `unmark <task_number>`: Reverts a task to not done.
- `todo <task_description>`: Adds a new todo task.
- `deadline <task_description> /by <date: yyyy-mm-dd>`: Adds a new deadline task.
- `event <task_description> /from <date: yyyy-mm-dd> /to <date: yyyy-mm-dd>`: Adds a new event task.
- `delete <task_number>`: Deletes a specified task.
- `archive <task_number>`: Archives a specified task.
- `find <keyword>`: Finds tasks containing the specified keyword.

Feel free to contact [@4llysa](https://github.com/4llysa) for any enquiries, bug reports or suggestions for improvements.