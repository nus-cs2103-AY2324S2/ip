# Sam User Guide

Sam is an interactive chatbox which facilitates tracking of tasks.
![screenshot of the user interface](https://github.com/SampsonYe1999/ip/tree/master/docs/Ui.png)

## Features
- Add a "ToDo" task with only description.
- Add a "Deadline" task with both description and a deadline specified by "by".
- Add an "Event" task with description, starting time and ending time specified by "from " and "to".
- list all the tasks stored previously.
- Mark a task as done or undone.
- Delete a task from the stored list.
- Find all tasks with matching keyword in its description.
- Update a task's description, deadline, starting datetime and ending datetime.


### 1: Add a ToDo task.
You can use the "todo" command followed with a task description to add a "ToDo" task into the list.

Example: 

Input: "todo finish quiz"

This adds a ToDo task with description "finish quiz" into the list.

Expected output:
```
This task has been added to the list:
[T][] finish quiz
Now you have 1 task(s) in the list.
```

### 2: Add a Deadline task. 
You can use the "deadline" command followed with a task description and a due date to add a "Deadline" task into the list.

Example:

Input: "deadline finish iP /by 2024-02-26 2359"

This adds a Deadline task with description "finish iP" and a deadline on Feb 26 2024 23:59.

Expected output:
```
I've added this task.
[D][] finish iP (by: Feb 26 2024 23:59)
You have 2 tasks left in the list.
```


## 3: Add an Event task
You can use the "event" command followed with a task description, starting time and end time to add an "Event" task into the list.

Example:

Input: "event workshop /from 2024-03-01 1300 /to 2024-03-01 1700"

This adds an Event task with description "workshop" and a starting time on Mar 01 2024 13:00 and an end time on Mar 01 2024 17:00.

Expected output:
```
The task has been added to the list:
[E][] workshop (from: Mar 01 2024 13:00 to: Mar 01 2024 17:00)
Now you have 3 tasks left.
```

## 4: list all the tasks 
You can use the "list" command to view all the tasks currently in the list.

Example:

Input: "list"

Expected output:

```
Here is the list of tasks:
1.[T][] finish quiz 
2.[D][] finish iP (by: Feb 26 2024 23:59)
3.[E][] workshop (from: Mar 01 2024 13:00 to: Mar 01 2024 17:00)

```

## 5: Mark a task as done or undone.
All newly added tasks are automatically marked as undone. You can use "mark" or "unmark" command to change the status of the task.
A task that has been previously marked as cant be marked again and vice versa.

Example:

Input: "Mark 3"

This marks the third task in the list as done.

Expected output:

```
This mark has been marked as done:
[E][X] workshop (from: Mar 01 2024 13:00 to: Mar 01 2024 17:00)
```


Input: "Mark 3"

As the task has already been marked as undone, its status wont be changed but a notice will be shown.

Expected output:

```
This task was already marked.
```

## 6: Delete a task from the list
You can delete a task from the list by using the "delete" command followed by an index.

Example:

Input: "delete 1"

This deletes the first task in the list.

Expected output:

```
This task has been deleted from the list:
[T][] finish quiz
Now you have 2 tasks left.
```

## 7: Find a task with a keyword 
You can search for a task with its description containing a given keyword by using the "find" command.

Example:

Input: "find finish"

This finds all the tasks in the list with description containing the word "finish".

Expected output:

```
These are the tasks with the specified keyword
1.[D][] finish iP (by: Feb 26 2024 23:59)
```
## 8: Update a task' information
You can update a task's information by using the "update" command followed by an index. 
Sam will make sure that the information provided by you is required by the task type which you want to update.

Input: "update 1 /d finish tP /by 2024-04-10 2359"
This will check the first task in the list. 
Sam will confirm that this task is a Deadline task first before updating the information.

Expected output:

```
Edited task:
[D][] finish tP (by: Apr 10 2024 23:59)
```
