# Cro User Guide

***Welcome to the Cro task-bot user guide!***
 
![Screenshot of Cro](./Ui.png)

Cro helps you manage your important tasks, deadlines and events!

## Adding Deadlines

Using the `deadline` function, you can add a deadline task to the Cro chat bot.


Example Usage: 
```
deadline <description> /by YYYY mm dd HH mm
```

Expected Outcome:

```
added: [D][] <description> (by: dd mmm YYYY HH:mm)
```

## Adding Events

Using the `event` function, you can add an event task to the Cro chat bot.

Example Usage:
```
event <description> /from YYYY mm dd HH mm /to YYYY mm dd HH mm`
```
Expected Outcome:

```
added: [E][] <description> (from: dd mmm YYYY HH:mm to: dd mmm YYYY HH:mm)
```

## Adding Todos

Using the `todo` function, you can add a todo task to the Cro chat bot.

Example Usage: 
```
todo <description>
```

Expected Outcome:

```
added: [T][] <description>
```

## Viewing your Tasks

Using the `list` function, you can view all the tasks currently in Cro.

Example Usage:
```
list
```
Expected Outcome:
```
1. [T][] task 1
2. [E][] event 1 (from: 23 Feb 2024 19:00 to 24 Feb 2024 19:00)
```

## Marking and Unmarking Tasks

Using the `mark` and `unmark` functions, you can mark various tasks as done or undone.

Example Usage:
```
mark <task number>
```
Expected Outcome:
```
Nice! I've marked this task as done!
1. [T][X] task 1
```
Example usage:
```
unmark 1
```
Expected Outcome:
```
OK, I've marked this task as not done yet.
[T][] task 1
```

## Finding Tasks

Using the `find` function, you can find tasks based on a string.

Example Usage:
```agsl
find book
```

Expected Outcome:
```
Here are the matching tasks in your list:
1. [T][] buy book
2. [T][] borrow book
3. [T][] visit bookshop
```

## Deleting Tasks

Using the `delete` function, you can delete tasks based on their index.

Example Usage:
```agsl
delete <task number>
```

Expected Outcome:
```
OK, I've removed this task.
[T][] task 1
Now you have 2 tasks in the list.
```

## Tagging Tasks

Using the `tag` function, you can add tags to tasks. A tag is a string.

Example Usage:
```agsl
tag <task number> <tag>
```
Expected Outcome:
```
The tag: [tag] has been added to task <task number>
```

## Closing Cro

Using the `bye` function, you can end the program and save all tasks into a locally stored text file, `saveData.txt`.

Example Usage:
```agsl
bye
```
Expected Outcome:
```agsl
Bye. Hope to see you again soon!
```
After which, you are free to close the application.