# Harper User Guide

![Screenshot of harper chatbot](./Ui.png)

**A powerful and interactive chatbot that helps you to manage your tasks.**
## Adding task
**Add a task to the task list.**  
harper supports 3 types of task: Todo, Deadline and Event  
Date format for task that requires: d/m/yyyy hh:MM
### Todo
Task without any time restriction.  
Command: `todo [description]`  
Example:
  - `todo read book`
  - `todo sleep`

If you successfully add the new todo task, you will see this message:
```
Got it. I've added this task into your list:
T | 0 | read book
Now you have 1 task in the list.
```
If there is a format error, you will receive this instead:
```
What do you mean? :-(
```
### Deadline
Task with a deadline.  
Command: `deadline [description] /by [deadline]`  
Example: 
  - `deadline CS2103T ip submission /by 23/2/2024 23:59`  

If you successfully add the new deadline task, you will see this message:
```
Got it. I've added this task into your list:
D | 0 | CS2103T ip submission | 23 Feb 2024 23:59
Now you have 2 tasks in the list.
```
If there is a format error, you will receive this instead:
```
Please follow the format: "deadline [description] /by [deadline]"
to add a deadline task into your list.
All fields are required.
Only the whitespace after "deadline" is required.
```

### Event
Task with a duration.  
Command: `event [description] /from [start] /to [end]`  
Example:
  - `event CS2107 lecture /from 22/2/2024 10:00 /to 22/2/2024 12:00`

If you successfully add the new event task, you will see this message:
```
Got it. I've added this task into your list:
E | 0 | CS2107 lecture | 22 Feb 2024 10:00 - 22 Feb 2024 12:00
Now you have 3 tasks in the list.
```
If there is a format error, you will receive this instead:
```
Please follow the format: "event [description] /from [start time] /to [end time]"
to add a event task into your list.
All fields are required!
Only the whitespace after "event" is required.
```

## Listing tasks
**List out all the tasks in the list.**  
Command: `list`  
You will see this message:
```
Here are the tasks in your list:
1. T | 0 | read book
2. D | 0 | CS2103T ip submission | 23 Feb 2024 23:59
3. E | 0 | CS2107 lecture | 22 Feb 2024 10:00 - 22 Feb 2024 12:00
```

## Deleting task
**Delete an existing task from the list**  
Command: `delete [index]`    
Example: 
  - `delete 1`  

If you successfully delete the task, you will see this message:
```
Ok! I've removed this task for you:
T | 0 | read book
Now you have 2 tasks in your list.
```
If you enter an invalid index, you will receive this instead:  
```Please make sure you have entered a valid index!```

## Marking task
**Mark a task as done or not done**
### Mark
**Mark a task as done**
Command: `mark [index]`  
Example:
  - `mark 1`

If you successfully mark a task as done, you will receive this:
```
Nice! I've marked this task as done:
D | 1 | CS2103T ip submission | 23 Feb 2024 23:59 
```
If you enter an invalid index, you will receive this instead:
```
Please make sure you have entered a valid index!
```

### Unmark
**Mark a task as not done**  
Command: `unmark [index]`  
Example: 
  - `unmark 1`

If you successfully mark a task as done, you will receive this:
```
Ok, I've marked this task as not done yet:
D | 0 | CS2103T ip submission | 23 Feb 2024 23:59 
```
If you enter an invalid index, you will receive this instead:
```
Please make sure you have entered a valid index!
```

## Finding task
**Find tasks with the given keyword**   
Command: `find [keyword]`  
Example:
  - `find lecture`

If you successfully find some task with that keyword, you will receive this:
``` 
Here are the matching task in your list:
2. E | CS2107 lecture | 22 Feb 2024 10:00 - 22 Feb 2024 12:00
```
If no task matches the keyword, you will receive this instead:
``` 
No matching task is found :-(
```

## Updating task
**Update an existing task in the list.**  
An update command can only update a paraeter.  

### Updating description
**Update a task's desciption**
Command: `update [index] /description [new description]`  
Example: 
  - `update 1 /description CS2103T ip hard deadline`

If you successfully update a task, you will receive this:
``` 
Nice! I've updated this task for you:
D | 0 | CS2103T ip hard deadline | 23 Feb 2024 23:59
```
If an invalid index in entered, you will receive:
``` 
Please make sure you have entered a valid index!
```
If there is a format error, you will receive:
``` 
Please make sure that you have followed the following format:
1. update [index] /description [description], for: todo, deadline and event
2. update [index] /by [deadline], for: deadline
3. update [index] /from [start date time], for: event
4. update [index] /to [end date time], for: event
Each update command can only update one field!
It might not work as expected if you put more than
one field within an update command. So, please don't do that!
```

### Updating date
**Update deadline, start time or end time of a deadline or event task.**  
Command:
  - `update [index] /by [new deadline]`
  - `update [index] /from [new start time]`
  - `update [index] /to [new end time]`

Example: 
  - `update 1 /by 23/2/2024 16:00`
  - `update 2 /from 22/2/2024 12:00`
  - `update 2 /to 22/2/2024 14:00`

If you successfully update a task, you will receive this:
``` 
Nice! I've updated this task for you:
D | 0 | CS2103T ip hard deadline | 23 Feb 2024 16:00
```
If an invalid index in entered, you will receive:
``` 
Please make sure you have entered a valid index!
```
If there is a format error, you will receive:
``` 
Please make sure that you have followed the following format:
1. update [index] /description [description], for: todo, deadline and event
2. update [index] /by [deadline], for: deadline
3. update [index] /from [start date time], for: event
4. update [index] /to [end date time], for: event
Each update command can only update one field!
It might not work as expected if you put more than
one field within an update command. So, please don't do that!
```

## Closing chatbot
**Close the chatbot**  
Command: `bye`  
You will receive:
```Hope to see you soon! Peace Out!```