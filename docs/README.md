# Georgie User Guide

<p align="center">
  <img src="Georgie.png" alt="Alt Text" width="100" style="border: 1px solid #ccc;"/>
</p>

Meet Georgie, your personal task management companion designed to simplify your life. Georgie is more than just an app; it's your ally in staying organized and on top of your daily tasks. With Georgie by your side, you can effortlessly manage your to-dos, deadlines, and events, allowing you to focus on what truly matters. Let Georgie be your guide to a seamlessly organized life!

**Preview**
<p align="center">
  <img src="Ui.png" alt="Alt Text" width="300"/>
</p>

## Quick Start

Ensure that your computer has Java 11 or above installed

## Features

### Adding Tasks

#### To-do Task

Adds a task to your list of tasks.

Format: `todo <TASK_DESCRIPTION>`

Sample Input:
```
todo laundry
```
Sample Output:
```
Got it. I've added this task:
[T] [] laundry
Now you have 1 task in the list.
```

#### Deadline Task

Adds a task with a deadline to the list of tasks.

Format: `deadline <TASK_DESCRIPTION> /by <DATE>`

Sample Input:
```
deadline assignment /by 2024-03-03
```
Sample Output:
```
Got it. I've added this task:
[D] [] assignment (by: Mar 03 2024)
Now you have 1 task in the list.
```

> [!NOTE]
> Date format must be 'yyyy-MM-dd'.

#### Event Task

Adds a task with start and end date to the list of tasks.

Format: `event <TASK_DESCRIPTION> /from <DATE> /to <DATE>`

Sample Input:
```
 event sleepover /from 2024-03-01 /to 2024-03-04
```
Sample Output:
```
Got it. I've added this task:
[E] [] sleepover (from: Mar 01 2024 to: Mar 04 2024)
Now you have 1 task in the list.
``` 

> [!NOTE]
> Date format must be 'yyyy-MM-dd.'

### Listing Tasks

Shows the task(s) that were added to the list.

Format: `list`

Sample Input:
```
 list
```
Sample Output:
```
Here are the tasks in your list:
1. [T] [ ] laundry
2. [D] [ ] assignment (by: Mar 03 2024)
3. [E] [ ] sleepover (from Mar 01 2024 to: Mar 04 2024)
``` 

### Deleting Tasks

Deletes a task from the list at specified index.

Format: `delete <INDEX>`

Sample Input:
```
delete 1
```
Sample Output:
```
Noted. I've removed this task:
[T] [ ] laundry
Now you have 2 tasks in the list.
``` 

### Marking Tasks

Marks a task from the list at specified index.

Format: `mark <INDEX>`

Sample Input:
```
mark 1
```
Sample Output:
```
Nice! I've marked this task as done:
[T] [X] laundry
``` 

### Unmarking Tasks

Unmarks a task from the list at specified index.

Format: `unmark <INDEX>`

Sample Input:
```
unmark 1
```
Sample Output:
```
Ok, I've marked this task as not done yet:
[T] [ ] laundry
``` 

### Finding Tasks

Finds a task from the list using keywords.

Format: `find <KEYWORD>`

Sample Input:
```
find assignment
```
Sample Output:
```
Here are the matching tasks in your list:
1. [D] [ ] assignment (by: Mar 03 2024)
``` 

### Exiting Georgie

Exit and closes the Georgie application. 

Format: `bye`

Sample Input:
```
bye
```
Sample Output:
```
Bye. Hope to see you again soon!
``` 

## Additional Notes

### Duplicate Tasks
Georgie does not allow the addition of duplicate tasks to ensure that your task list remains organized and free of redundancy. 

If you attempt to add a task that already exists, Georgie will notify you with the following message:
```
Umm... The task already exists. Duplicate tasks are not allowed.
```

## Command Summary

| Action       | Format                                             |
|--------------|----------------------------------------------------|
| add todo     | `todo <TASK_DESCRIPTION>`                          |
| add deadline | `deadline <TASK_DESCRIPTION> /by <DATE>`           |
| add event    | `event <TASK_DESCRIPTION> /from <DATE> /to <DATE>` |
| list         | `list`                                             |
| delete       | `delete <INDEX>`                                   |
| mark         | `mark <INDEX>`                                     |
| unmark       | `unmark <INDEX>`                                   |
| find         | `find <KEYWORD>`                                   |
| exit         | `bye`                                              |