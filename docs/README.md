# Pororo User Guide
Your friend pororo is here to help you stay on track and improve efficiency.

## Features 
1. List
2. Add 
3. Delete 
4. Mark 
5. Unmark 
6. Find 
7. Undo 

### 1. List tasks 
Show a list of all your task to be done. 

Example: `List`

Expected output: 

```
Pompom here are the tasks on your list: 
1.[T][] complete pset
```

### 2. Add tasks
Adds a task to your current task list.

Todo, Deadline and Event can be added to your task list.


#### Adding Todo

Example: `todo submit pset`

Expected output:

```
Okay. I've added this task:
[T][] submit pset 
Now you have 2 tasks in the list. 
```

#### Adding Deadline

Example: `deadline submit pset /by 2024-02-11`

Expected output:

```
Okay. I've added this task:
[D][] submit proposal (by: Feb 11 2024)
Now you have 3 tasks in the list. 
```

#### Adding Event

Example: `event piano lesson /from Jan /to Dec`

Expected output:

```
Okay. I've added this task:
[E][] piano lession (from:Jan to:Dec)
Now you have 4 tasks in the list. 
```

### 3. Delete tasks
Delete tasks from your current task list. 

Example: `delete 1`

Expected output:

```
Got it. I've removed this task:
[T][] complete pset
Now you have 3 tasks in the list. 
```

### 4. Mark tasks 
Mark your task if you have already completed it. 

Example: `mark 1`

Expected output:

```
Good job! I've marked this task as done:
[X] submit pset
```

### 5. Unmark tasks
Unmark your task if it is still incompleted. 

Example: `unmark 1`

Expected output:

```
Noted, I've marked this task as not done yet:
[] submit pset
```

### 6. Find tasks
Find a specific task according to the keyword you entered. 

Example: `find piano`

Expected output:

```
Pompom here are the matching tasks in your list:
1.[E][] piano lession (from:Jan to:Dec)
```

### 7. Undo tasks
Undo your most recent command. 

Example: `undo`

Expected output:

```
Good job! I've marked this task as done:
[X] submit pset
```

### Addtional commands 

#### Start
Pororo will introduce itself to you. 

Example: `hell0`

Expected output:

```
Hi, i'm Pororo
How can i help you?
```

#### Ending 
It allows you to exit the program. 

Example: `bye`

Expected output:

```
Bye bye. Hope to see you soon
```
