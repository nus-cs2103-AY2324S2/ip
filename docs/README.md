# Cinnamo >.<
> The cuttest chatbot in the world!

Welcome! Cinnamo is a chatbot modelled after my favourite character CinnamoRoll that helps you manage the tasks you need to complete!

If you want to use Cinnamo chatbot, you can download and run the app via following procedures:
1. Download `.jar` file via acekhoon iP -> tags -> A-Release -> `Cinnamo.jar`. You can download it [here](https://github.com/acekhoon/ip/tags)
1. Place the `.jar` file in the folder or directory you want to put in your local machine
1. Run `java -jar Cinnamo.jar` command on your terminal, and you are all settled!

## Features 
Cinnamo is able to perform following task management jobs for you! Here is short layout on what Cinnamo can do:
1. **Basic CRUD** functions: 
    - Create and save tasks on local machine
    - Read tasks from saved files
    - Add and delete tasks 
    - Mark and unmark important tasks to take note of
1. Managing **duplicate tasks** and **uniquifying** the task list
1. **Searching** for specific tasks by keywords

### 1. todo

Todo command will create todo event and add it into the task list.

#### Usage

Syntax to add todo task into the task list:

`todo <name of the task>`

Example:

`todo CS2103T week 6 quiz`

Expected outcome:
```
Got it. I've added the task:
   [T][] CS2103T week 6 quiz
Now you have 1 tasks in the list
```

### 2. deadline

Deadline command will create deadline task and add it into the task list.

#### Usage

Syntax to add deadline task into the task list:

`deadline <name of the task> /by <YYYY-MM-DD HH:MM>`

Example:

`deadline MA5211 Assignment 2 /by 2024-02-26 12:00`

Expected outcome:
```
Got it. I've added the task:
   [D][] MA5211 Assignment 2 (by: Feb 26 2024 12:00)
Now you have 1 tasks in the list
```

### 3. event

Event command will create event task and add it into the task list.

#### Usage

Syntax to add event task into the task list:

`event <name of the task> /from <YYYY-MM-DD HH:MM> /to <YYYY-MM-DD HH:MM>`

Example:

`event Family KBBQ Dinner /from 2024-02-18 18:00 /to 2024-02-18 20:00`

Expected outcome:
```
Got it. I've added the task:
   [E][] Family KBBQ Dinner (from: Feb 18 2024 18:00 to: Feb 18 2024 20:00)
Now you have 1 tasks in the list
```

### 4. list

List all tasks in the current task list.

#### Usage

Syntax to display list of tasks:

`list`

Example:

`list`

Expected outcome:
```
Here are the tasks in your list >.<:
1. [T][] CS2103T week 6 quiz
2. [D][] MA5211 Assignment 2 (by: Feb 26 2024 12:00)
3. [E][] Family KBBQ Dinner (from: Feb 18 2024 18:00 to: Feb 18 2024 20:00)
```

### 5. delete

Syntax to delete task in the list:

`delete <task index>`

Example:

`delete 1`

Expected outcome:
```
Noted. I've removed the following task:
   [T][] CS2103T week 6 quiz
Now, you have 2 tasks in the list
```

### 6. mark

Syntax to mark task in the list:

`mark <task index>`

Example:

`mark 1`

Expected outcome:
```
Nice! I've marked this task for you:
   [D][X] MA5211 Assignment 2 (by: Feb 26 2024 12:00)
```

### 7. unmark

Syntax to unmark task in the list:

`unmark <task index>`

Example:

`unmark 1`

Expected outcome:
```
Ok! I've unmarked this task for you:
   [D][] MA5211 Assignment 2 (by: Feb 26 2024 12:00)
```

### 7. find

Syntax to find task in the list by string input from the user:

`find <string to search for>`

Example:

`find MA5211`

Expected outcome:
```
Here are the matching tasks in your list:
   1.[D][X] MA5211 Assignment 2 (by: Feb 26 2024 12:00)
```

### 9. duplicate

Syntax to find duplicate tasks with occurrence in terms of tasknames from the list:

`duplicate`

Example:

`duplicate`

Expected outcome:
```
Here are duplicate tasks in your list:
   1.CS2103T iP (occurrence: 3 times)
   2.Dinner (occurrence: 2 times)
```

### 10. uniquify

Syntax to remove duplicate tasks and make every task in the list to be unique:

`uniquify`

Example:

`uniquify`

Expected outcome:
```
Cinnamo has uniquified the tasks for you >.<

> list
Here are the tasks in your list >.<:
1. [T][] CS2103T iP
2. [T][] Dinner
```

### 11. bye

Syntax to close Cinnamo:

`bye`

Example:

`bye`

Expected outcome:
```
See you again! Hope you had great time with me >.<
```