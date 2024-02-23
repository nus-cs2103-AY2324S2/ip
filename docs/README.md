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
- `bye`: Exits the application. Your tasks are automatically saved.

## Examples

1. List unarchived tasks
---
List out all the task stored currently that are unarchived. 

**Format**: `list`

**Sample Output**:
```
Here are the tasks in your list.
1.[T][]revise CS2103T
2.[E][]project meeting (from: Mon 2pm to: 4pm)
```

2. List all archived and unarchived tasks.
---
List out all the task stored, including those archived.

**Format**: `list all`

**Sample Output**:
```
Here are the tasks in your list.
1.[T][]revise CS2103T
2.[E][]project meeting (from: Mon 2pm to: 4pm)
Here are the tasks that are archived.
1.[T][] Finish CS3230 Assignment
```

3. Mark Task
---
Marks a task as done.

**Example**: `mark 1`

**Format**: `mark <task_number>`

**Use the specific number as shown in the list**

4. Unmark Task
---
Unmarks a task as done.

**Example**: `unmark 1`

**Format**: `unmark <task_number>`

**Use the specific number as shown in the list**

5. Add Todo
---

**Example**: `todo eat dinner`

**Format**: `deadline [TASK NAME] /by [yyyy-mm-dd]`

- **Use the specific formats for date and time as shown above.**

6. Add Deadline
---

**Example**: `deadline submit iP /by 2023-02-23`

**Format**: `deadline [TASK NAME] /by [yyyy-mm-dd]`

- **Use the specific formats for date and time as shown above.**

7. Add Event
---

**Example**: `event hack and roll /from Mon 12pm /to Tues 12pm`

**Format**: `event [TASK NAME] /from [START DATE] to [END DATE]`
- **Use the specific formats for date and time as shown above.**

8. Delete task
---
Delete a task so that it will be removed from task list

**Example**: `delete 1`

**Format**: `delete [TASK NUMBER]`
- **Use the specific number as shown in the list**

9. Archive task
---
To unmark a task that has been previously marked.

**Example**: `unmark 1`
- Unmarks task 1

**Format**: `unmark [TASK NUMBER]`
- To view archived tasks, you can use `list all` as mentioned in (2)
- **Use the specific number as shown in the list**
10. Find task
---
Delete a task from a task list.

**Example**: `delete 1`
- Deletes task 1

**Format**: `delete [TASK NUMBER]`

11. Exit the Program
---
**Format**: `bye`


Feel free to contact [@4llysa](https://github.com/4llysa) for any enquiries, bug reports or suggestions for improvements.

Inspiration for User Guide taken from [@jyztintan](https://github.com/jyztintan) and [@yleeyilin](https://github.com/yleeyilin)