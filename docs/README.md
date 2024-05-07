# Sky User Guide

![Sky UI Screenshot](Ui.png)

Presenting Sky, the innovative chatbot engineered to transform the way you manage your tasks. With Sky, enhance your efficiency and seamlessly arrange your tasks in an unprecedented manner. Experience the future of task management with Sky.

## Installation
---
1. **Download Sky**: Click [here](https://github.com/YowSiaoKang/ip/releases) to download your Sky chatbot.
2. **Navigate to the Folder**: Open your preferred file explorer and go to the directory containing the `Sky.jar` file.
3. **Open Terminal**: Launch a terminal window at the folder location.
4. **Execute Command**: Type the following command into the terminal: `java -jar Sky.jar`.
5. **Press Enter**: Hit the `Enter` key to execute the command.
6. **Enjoy Osiris**: Sky is now ready to use!
---

## Features

### List tasks: `list`
List all current tasks in the task list.

Format: `list`

Expected outcome:
```
Here are the tasks in your list:
1. [E][ ] Recess Week (from: Feb 24 2024 00:00 to: Mar 3 2024 00:00)
2. [T][X] Meet with Bob
3. [T][ ] Submit CS2103T iP
```

### Add Todo task: `todo`
Add a task to do with a description.

Format: `todo [description]`

Example: `todo Meet with Bob`

Expected outcome:
```
Got it. I've added this task:
  [T][ ] Meet with Bob
Now you have 4 tasks in the list.
```

### Add Deadline task: `deadline` 
Add a deadline with a description and due date.

Format: `deadline [description] /by [yyyy-mm-dd hh:mm]`

Example: `deadline Assignment /by 2024-02-05 23:59`

Expected outcome:
```
Got it. I've added this task:
  [D][ ] Assignment (by: Feb 5 2024 23:59)
Now you have 5 tasks in the list. 
```

### Add Event task: `event`
Add an event with a description, and start and end date.

Format: `event [description] /from [yyyy-mm-dd hh:mm] /to [yyyy-mm-dd hh:mm]`

Example: `event Midterm /from 2024-02-22 10:00 /to 2024-02-22 12:00`

Expected outcome:
```
Got it. I've added this task:
  [E][ ] Midterm (from: Feb 22 2024 10:00 to: Feb 22 2024 12:00)
Now you have 6 tasks in the list.
```

### Delete task: `delete`
Delete task from the list.

Format: `delete [taskIndex]`

Example: `delete 4`

Expected outcome:
```
Noted. I've removed this task:
  [T][ ] Meet with Bob
Now you have 5 tasks in the list.
```

### Mark task as done: `mark` 
Mark task as done.

Format: `mark [taskIndex]`

Example: `mark 1`

Expected outcome:
```
Nice! I've marked this task as done:
  [E][X] Recess Week (from: Feb 24 2024 00:00 to: Mar 3 2024 00:00)
```

### Mark task as not done: `unmark` 
Mark task as not done.

Format: `unmark [taskIndex]`

Example: `unmark 1`

Expected outcome:
```
OK, I've marked this task as not done yet:
  [E][ ] Recess Week (from: Feb 24 2024 00:00 to: Mar 3 2024 00:00)
```

### Find task: `find` 
Find task by keyword.

Format: `find [keyword]`

Example: `find Bob`

Expected outcome:
```
Here are the matching tasks in your list:
1. [T][X] Meet with Bob
```

### Archive Data: `archive`
Save a copy of current data as an archive.

Format: `archive`

Example: `archive`

Expected outcome:
```
Tasks have been archived to SkyArchive_2024-02-22.txt
```

### Exit program: `bye`
Close the program.

Format: `bye`

Example: `bye`

Expected outcome: Program closed.

## Saving data
The current data can be found in a text file `[JAR file location]/data/sky.txt`.
> [!CAUTION]
> Task data is only saved after closing the program with `bye` command.
> Data will not be saved if program is closed through the window button.
> Do not manually change the data file unnecessarily.

## Command Summary
* `list`
* `todo [description]`
* `deadline [description] /by [yyyy-mm-dd hh:mm]`
* `event [description] /from [yyyy-mm-dd hh:mm] /to [yyyy-mm-dd hh:mm]`
* `delete [taskIndex]`
* `mark [taskIndex]`
* `unmark [taskIndex]`
* `find [keyword]`
* `archive`
* `bye`
