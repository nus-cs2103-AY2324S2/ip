# GanAnWo User Guide

![Product screenshot](/docs/Ui.png)

GanAnWo is a chatbot application that helps you to manage your tasks. By using GanAnWo, you will be
able to easily add, edit, and delete your tasks. Therefore, if you need help on managing your tasks,
GanAnWo will be able to help you to manage your tasks easily and faster.

## Adding task 

Adds a new task to your task list. A task could be a todo, deadline, or event. Each of them has a different
format of method.

### 1. Adding todo
Adds a new todo task to your task list.

Format: `todo [description]`

Example: 
- `todo reading book`
- `todo apply internships`

You will see this message if you successfully add a new todo task to your task list:
```
Got it. I've added this task:
[T][ ] description
Now you have 1 tasks in the list.
```

If there is an error, you will see this message:
```
Wrong format!, please use this format: todo Description
```

### 2. Adding deadline
Adds a new deadline task to your task list.

Format: `deadline [description] /by [Date]`

Date Format: `yyyy-MM-dd HH:mm`

Example: 
- `deadline cs2103 quiz /by 2024-02-01 23:59`
- `deadline submit essay /by 2024-04-03 16:00`

You will see this message if you successfully add a new deadline task to your task list:
```
Got it. I've added this task:
[D][ ] cs2103 quiz (by: Feb 01 2024 23:59)
Now you have 2 tasks in the list.
```

If there is an error, you will see this message:
```
Wrong format!, please use this format: deadline Description /by yyyy-MM-dd HH:mm
```

If there is an error in the date format, you will see this message:
```
Please use the date format: yyyy-MM-dd HH:mm (e.g.: 2023-02-01 12:30)
```

### 3. Adding event
Adds a new event task to your task list.

Format: `event [description] /from [Date] /to [Date]`

Date Format: `yyyy-MM-dd HH:mm`

Example: 
- `event career fair /from 2024-02-01 10:00 /to 2024-02-04 18:00`
- `event welcome tea /from 2024-03-11 10:00 /to 2024-03-11 13:00`

You will see this message if you successfully add a new event task to your task list:
```
Got it. I've added this task:
[E][ ] career fair (from: Feb 01 2024 10:00 to: Feb 04 2024 18:00)
Now you have 3 tasks in the list.
```

If there is an error, you will see this message:
```
Wrong format!, please use this format: event Description /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm
```

If there is an error in the date format, you will see this message:
```
Please use the date format: yyyy-MM-dd HH:mm (e.g.: 2023-02-01 12:30)
```

## View task list
Views all the tasks in the task list.

Format: `list`

Example: 
- `list`

You will see this message if you successfully view the tasks:
```
Now you have 6 tasks in the list. 
1.[D][X] return book (by: Feb 01 2023 12:30)
2.[E][X] project meeting (from: Feb 01 2023 12:30 to: Feb 01 2023 12:35)
3.[D][ ] home (by: Nov 12 2003 20:30)
4.[T][ ] reading book
5.[D][ ] cs2103 quiz (by: Feb 01 2024 23:59)
6.[E][ ] career fair (from: Feb 01 2024 10:00 to: Feb 04 2024 18:00)
```

## Mark task
Marks a task as done.

Format: `mark [task number]`

> :book: Note: task number is based on the 'list' feature.

Example: 
- `mark 1`
- `mark 2`

You will see this message if you successfully mark the task:
```
Nice! I've marked this task as done:
[T][X] apply internships
```

If there is an error, you will see either of these messages:
- When the task number is not in number format:
```
The task number given is not a number
```
- When task number exceeds available task or negative number:
```
No task number 4
```
- When using the wrong format:
```
The command format for mark is mark number (e.g.: mark 1)
```

## Unmark task
Unmarks a task as not done.

Format: `unmark [task number]`

> :book: Note: task number is based on the 'list' feature.

Example: 
- `unmark 1`
- `unmark 2`

You will see this message if you successfully unmark the task:
```
OK, I've marked this task as not done yet:
[T][] apply internships
```

If there is an error, you will see either of these messages:
- When the task number is not in number format:
```
The task number given is not a number
```
- When task number exceeds available task or negative number:
```
No task number 4
```
- When using the wrong format:
```
The command format for unmark is unmark number (e.g.: mark 1)
```

## Delete task
Deletes a task from the task list.

Format: `delete [task number]`

> :book: Note: task number is based on the 'list' feature.

Example: 
- `delete 1`
- `delete 2`

You will see this message if you successfully delete the task:
```
Noted. I've removed this task:
[T][ ] apply internships
Now you have 2 tasks in the list. 
```

If there is an error, you will see either of these messages:
- When task number is not in number format:
```
The task number given is not a number
```
- When task number exceed available task or negative number:
```
No task number 4
```
- When using the wrong format:
```
The command format for delete is delete number (e.g.: delete 1)
```

## Find tasks
Find tasks with the given keyword.

Format: `find [keyword]`

Example: 
- `find book`
- `find meeting`

You will see this message if there are tasks with the keyword:
```
1.[D][X] return book (by: Feb 01 2023 12:30)
2.[T][ ] reading book
```

You will see this message if there are no task with the keyword:
```
Not found task with keyword: abc
```

## Quit program/session
Quits the program.

Format: `bye`

Example:
- `bye`

You will see this message if you successfully quit the program:
```
Bye. Hope to see you again soon!
```
