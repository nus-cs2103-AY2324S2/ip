# TaskYapper User Guide

Welcome to TaskYapper by [Justin Tan](https://www.linkedin.com/in/tan-wee-kian-justin/)

The ultimate task management tool that helps you keep track of your todos, deadlines, and events with ease! 🚀🚀🚀

![Representative Screenshot for TaskYapper](/docs/Ui.png)

## TaskYapper Features

1. **Starting TaskYapper**: Launch the application from your terminal or command prompt.
2. **Adding Tasks**: Use commands to add tasks. Supported task types include *todos*, *deadlines*, and *events*.
3. **Viewing Tasks**: Easily view all your tasks or filter them based on specific criteria.
4. **Updating Tasks**: Mark tasks as done or unmark them with simple commands.
5. **Removing Tasks**: No longer need a task? Delete it with a single command.

## TaskYapper Commands
- `BYE`: Exits the application. Your tasks are automatically saved.
- `YAP`: Lists all your tasks.
- `MARK <task_number>`: Marks a task as done.
- `UNMARK <task_number>`: Reverts a task to not done.
- `ADD_TODO <task_description>`: Adds a new todo task.
- `ADD_DEADLINE <task_description> /by <date: yyyy-mm-dd>`: Adds a new deadline task. 
- `ADD_EVENT <task_description> /from <date: yyyy-mm-dd> /to <date: yyyy-mm-dd>`: Adds a new event task.
- `DELETE <task_number>`: Deletes a specified task.
- `FIND <keyword>`: Finds tasks containing the specified keyword.
- `SCHEDULE <date: yyyy-mm-dd>`: Lists events and deadlines occurring on a specified date.

Example Commands:
```
todo Finish reading TaskYapper user guide
```
This task adds a "todo" task to our task list.

```
deadline CS2103T IP /by 2024-02-23
```
This task adds a "deadline" task to our task list.

```
event AY23/24 Semester 2 /from 2024-01-15 /to 2024-05-11
```
This task adds a "event" task to our task list.

- [ ] I have read finish this user guide and am ready to start using TaskYapper!

## Tips
> Pro Tip: Regularly use the YAP command to review your tasks and stay on top of your commitments!

Feel free to contact @jyztintan for any enquiries, bug reports or suggestions for improvements.
