# GPT User Guide

![Product Screenshot](./Ui.png)

## Product Introduction

This Task Manager allows you to efficiently organize your tasks by adding deadlines and managing various task types.

## Adding Deadlines

To add a deadline, follow the format below:

deadline {name of task} /from {start date} {start time}

### Example:
deadline FinishReport from 2/12/2024 16:00

### Expected Outcome:
```
Got it. I've added this task
  [D][ ] FinishReport (by: 2/12/2024 16:00)
  Now you have 1 tasks in the list.
  ```

## Adding Events
To add a event, follow the format below:

deadline {name of task} /from {start date} {start time} /to {end date} {end time}

### Example:
event Meeting /from 2/12/2024 16:00 /to 2/12/2024 18:00

### Expected Outcome:
```
Got it. I've added this task
  [E][ ] Meeting (at: 2/12/2024 16:00 - 2/12/2024 18:00)
  Now you have 2 tasks in the list.
  ```

## Adding Todos
To add a todo, follow the format below:
todo {name of task}

### Example:
todo BuyGroceries

### Expected Outcome:
```
Got it. I've added this task
  [T][ ] BuyGroceries
  Now you have 3 tasks in the list.
  ```
## Listing Tasks
To list all tasks, use the command:
list

### Example:
list

### Expected Outcome:
```
Here are the tasks in your list:
1. [D][ ] FinishReport (by: 2/12/2024 16:00)
2. [E][ ] Meeting (at: 2/12/2024 16:00 - 2/12/2024 18:00)
3. [T][ ] BuyGroceries
  ```
## Marking Tasks as Done
To mark a task as done, use the command:
done {task number}

### Example:
done 1

### Expected Outcome:
```
Nice! I've marked this task as done:
  [D][X] FinishReport (by: 2/12/2024 16:00)
  ```
## Deleting Tasks
To delete a task, use the command:
delete {task number}

### Example:
delete 3

### Expected Outcome:
```
Noted. I've removed this task:
  [T][ ] BuyGroceries
  Now you have 2 tasks in the list.
  ```
## Finding Tasks
To find tasks containing a keyword, use the command:
find {keyword}

### Example:
find Meeting

### Expected Outcome:
```
Here are the matching tasks in your list:
1. [E][ ] Meeting (at: 2/12/2024 16:00 - 2/12/2024 18:00)
  ```
## Exiting the Program
To exit the program, use the command:
bye

### Example:
bye

### Expected Outcome:
```
Bye. Hope to see you again soon!
  ```
## Saving Data
To save data to local file, use the command:
save

### Example:
save

### Expected Outcome:
```
saved!
  ```


