# 👑 Duchess User Guide 👑

![](Ui.png)

Presenting to you **Duchess**, your ultimate companion in productivity! Life can get busy, and staying organized is key to achieving your goals. This is where **Duchess** comes in – a sleek and intuitive solution designed to streamline your daily tasks, boost your efficiency, and bring order to your hectic schedule.

With **Duchess**, you can effortlessly create and manage your tasks with simple commands. Our user-friendly interface ensures that organising your to-do list is a breeze, allowing you to focus on what matters most. Whether you are a student juggling multiple assignments, or someone simply looking to enhance their daily productivity, Duchess is here to make your life easier.

# Task Types
1. To Do
2. Deadline
3. Event

# View Menu
**Command**:
```menu```

**Outcome**: Lists available commands

**Example**:

Input:
```
menu
```

Outcome:
```
--------------How to Use Me--------------
1. To create tasks: tasks
2. To mark tasks: mark/unmark <item number in list>
3. To view tasks: list
4. To find task: find <keyword>
5. To exit: bye
```

# View Tasklist
**Command**:
```list```

**Outcome**: Lists current tasks in task list

**Example**:

Input:
```
list
```

Outcome:
```
Here are the tasks in your list:
1. [T][ ] CS2103T week 1 iP increment
2. [D][ ] CS2103T iP (by: Feb 22 2024)
3. [E][ ] CS2103T Final Exam (from: Apr 27 2024 to: Apr 28 2024)
```

# Create Task
## To do
**Command**:
```todo <description>```

**Outcome**: Creates a new to do task

**Example**:

Input:
```
todo CS2103T week 1 iP increment
```

Outcome:
```
Got it. I've added this task:
[T][ ] CS2103T week 1 iP increment
Now you have 1 tasks in the list...
```

## Deadline
**Command**: 
```deadline <description> /by <DD/MM/YYYY>```

**Outcome**: Creates a new deadline task with the date displayed in MMM DD YYYY format

**Example**:

Input:
```
deadline CS2103T iP /by 22/02/2024
```

Outcome:
```
Got it. I've added this task:
[D][ ] CS2103T iP (by: Feb 22 2024)
Now you have 1 tasks in the list...
```

## Event
**Command**:
```event <description> /from <DD/MM/YYYY> /to <DD/MM/YYYY>```

**Outcome**: Creates a new event task with the dates displayed in MMM DD YYYY format

**Example**:

Input:
```
event CS2103T Final Exam /from 27/04/2024 /to 28/04/2024
```

Outcome:
```
Got it. I've added this task:
[E][ ] CS2103T Final Exam (from: Apr 27 2024 to: Apr 28 2024)
Now you have 1 tasks in the list...
```

# Mark Tasks
## Mark as Done
**Command**:
```mark <task number>```

**Outcome**: Marks task as done with "X"

**Example**:

Input:
```
mark 1
```

Outcome:
```
Nice! I've marked this task as done:
[T][X] CS2103T week 1 iP increment
```

## Mark as Undone
**Command**:
```unmark <task number>```

**Outcome**: Marks task as undone

**Example**:

Input:
```
umark 1
```

Outcome:
```
OK, I've marked this task as not done yet:
[T][ ] CS2103T week 1 iP increment
```

# Delete Task
**Command**:
```delete 1```

**Outcome**: Delete a current task in the task list

**Example**:

Input:
```
delete 1
```

Outcome:
```
Noted. I've removed this task:
1. [T][ ] CS2103T week 1 iP increment
Now you have 0 tasks in the list...
```

# Find Task
**Command**:
```find <keyword>```

**Outcome**: Find tasks containing keywords in the task list

**Example #1**:

Input:
```
find week 1
```

Outcome:
```
Here are the tasks in your list:
1. [T][ ] CS2103T week 1 iP increment
```

**Example #2**:

Input:
```
find iP
```

Outcome:
```
Here are the tasks in your list:
1. [T][ ] CS2103T week 1 iP increment
2. [D][ ] CS2103T iP (by: Feb 22 2024)
```

# Exit
**Command**:
```bye```

**Outcome**: Exit Duchess Task Manager

**Example**:

Input:
```
bye
```

Outcome:
```
Bye. Hope to see you again soon!
```