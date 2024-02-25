# Victor User Guide

> Planning right will lead you to victory

![Ui.png](Ui.png)

Victor is a unique bot that help users to keep tract
of their tasks. He is designed for users who prefer to use a 
Command Line Interface (CLI). 

If you want to use Victor, simply follow the following instructions:
1. Make sure you have java 11 installed on your computer
2. Download the latest version of `victor.jar` from [here]([victor.jar](..%2Fbuild%2Flibs%2Fvictor.jar))
3. place the `victor.jar` file in the folder or dictionary you plan to use in your local machine
4. Run `java -jar victor.jar` command on your terminal, and Victor should be up and running.

## Note
Do not close the window when you are done with Victor. It will not save any new data.
To end Victor, use the Exit command listed below.

## Features
This list all the different features and keywords to Victor can use. 

All items listed in {} are considered user input.
1. [Adding todo task](#1-adding-todo-task-todo)
2. [Adding deadline task](#2-adding-deadline-task-deadline)
3. [Adding event task](#3-adding-event-task-event)
4. [Listing all tasks](#4-listing-all-tasks-list)
5. [Marking a task as done](#5-marking-a-task-as-done-mark)
6. [Marking a task as not done](#6-marking-a-task-as-not-done-unmark)
7. [Deleting a task](#7-deleting-a-task-delete)
8. [Finding a task](#8-finding-a-task-find)
9. [Exiting the program](#9-exiting-the-program-bye)
10. [Duplication Detection](#duplication-detection)

### 1. Adding todo task: `todo`
Adds a todo task into the Victor database

Format: `todo {DESCRIPTION}`

Example: `todo read harry potter book 1` adds a todo task with the description "read harry potter book 1"

The outcome you will receive is:
```
Understood, I've added the task:
[T][] read harry potter book 1
Now you have 1 tasks in the list.
```

### 2. Adding deadline task: `deadline`
Adds a deadline task into the Victor database

Format: `deadline {DESCRIPTION} /by {DEADLINE_DATE}`

Example: `deadline buy harry potter book 2 /by 2024-07-02` 
adds a deadline task with the description "buy harry potter book 2" and 
the deadline of 2nd July 2024.

The outcome you will receive is:
```
Understood, I've added the task:
[D][] buy harry potter book 2 (by: Jul 02 2024)
Now you have 1 tasks in the list.
```

### 3. Adding event task: `event`
Adds an event task into the Victor database

Format: `event {DESCRIPTION} /from {START_DATE_TIME} /to {END_DATE_TIME}`

Example: `event harry potter convension /from 2024-09-06 10:00 
/to 2024-09-08 19:00` adds an event task with the 
description "harry potter convension" with the from date 6th September 2024 at 10am 
and the to date 8th September 2024 at 7pm.

The outcome you will receive is:
```
Understood, I've added the task:
[E][] harry potter convension (from: Sep 06 2024 10:00 am to: Sep 08 2024 07:00 pm)
Now you have 1 tasks in the list.
```

### 4. Listing all tasks: `list`
Lists out all the tasks within the database.

Format: `list`

Example: `list`

The outcome you will receive is:
```
Here are the tasks in your list
1.[T][] read harry potter book 1
2.[D][] buy harry potter book 2 (by: Jul 02 2024)
3.[E][] harry potter convension (from: Sep 06 2024 10:00 am to: Sep 08 2024 07:00 pm)
```

### 5. Marking a task as done: `mark`
Marks a task as completed

Format: `mark {LIST_INDEX}`

Example: `mark 1`

The outcome you will receive is:
```
Nice! I've marked this task as done:
[T][X] read harry potter book 1
```

### 6. Marking a task as not done: `unmark`
Marks a task as uncompleted

Format: `unmark {LIST_INDEX}`

Example: `unmark 1`

The outcome you will receive is:
```
Ok, I've marked this task as not done yet:
[T][] read harry potter book 1
```

### 7. Deleting a task: `Delete`
Deletes a task from the database

Format: `delete {LIST_INDEX}`

Example: `delete 1`

The outcome you will receive is:
```
Noted. I've removed this task
[T][] read harry potter book 1
```

### 8. Finding a task: `find`
Finds and return a list of all tasks with the description you provided

Format: `find {DESCRIPTION}`

Example: `find harry` would return all the tasks which contains the word "harry" as part of it's description

The outcome you will receive is:
```
Here are the matching tasks in your list:
1.[T][] read harry potter book 1
2.[D][] buy harry potter book 2 (by: Jul 02 2024)
3.[E][] harry potter convension (from: Sep 06 2024 10:00 am to: Sep 08 2024 07:00 pm)
```

### 9. Exiting the program: `bye`
Ends the program and closes the window.

Format: `exit`

The outcome you will receive is:

The program window will close. Any new changes you made to the data will have been saved.

**Warning**: Do not close the program in any other way except the `bye` command. If you close
the program in a different way, then any new changes you made to the database will not be saved.

### Duplication Detection
Victor will detect any attempts to add a duplicate task.

For a todo task, it would check the description.

Example: `todo read harry potter book 1`

The outcome you will receive is:
```
Todo duplicate detected.
There is already a todo task for 'read harry potter book 1'.
```

For a deadline task, it would check for both the description and the by date.

Example: `deadline buy harry potter book 2 /by 2024-07-02`

The outcome you will receive is:
```
Deadline duplicate detected.
There is already a duplicate dateline for 'buy harry potter book 2' by 2024-07-02.
```

For an event task, it would check for the description, the from date and the to date.

Example: `event harry potter convension /from 2024-09-06 10:00 /to 2024-09-08 19:00`

The outcome you will receive is:
```
Event duplicate detected.
There is already a duplicate event for 'harry potter convension' 
from Sep 06 2024 10:00 am to Sep 08 2024 07:00 pm.
```
