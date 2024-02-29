# Xavier User Guide

## Introduction

![Ui](./Ui.png)

Xavier is a simple chatbot which helps to manage your daily tasks!

Download it from [<u>here</u>](https://github.com/shunjieee/ip/releases/download/A-Release/xavier.jar) now!

---
## Features 
List of features supported:
* Add
* Delete
* List
* Mark
* Unmark
* Find
* Sort
* Exit

---
### Adding a task: `todo`, `deadline` and `event`

There are three types tasks that Xavier supports, to-do, deadline and event. The commands add the respective task to the task list.

**<u>todo</u>**

Formart: 
`todo <taskname>`

Example:
`todo wash dishes`

Expected outcome:
```
Got it. I've added this task:
  [T][ ] wash dishes
Now you have 1 tasks in the list.
```

**<u>deadline</u>**

Format: 
`deadline <taskname> /by <date & time>`

Example:
`deadline CS2103T Quiz 7 /by 7/3/2024-2359`

Expected outcome:
```
Got it. I've added this task:
  [D][ ] CS2103T Quiz 7 (by: Mar 07 2024, 23:59)
Now you have 2 tasks in the list.
```

**<u>event</u>**

Format: 
`event <taskname> /from <start date & time> /to <end date & time>`

Example:
`event Taylor Swift Concert /from 3/3/2024-1900 /to 3/3/2024-2200`

Expected outcome:
```
Got it. I've added this task:
  [E][ ] Taylor Swift Concert (from: Mar 03 2024, 19:00 to: Mar 03 2024, 22:00)
Now you have 3 tasks in the list.
```

> [!IMPORTANT]
> The date-time format **MUST** be in **dd/MM/yyyy-HHmm**.

---
### Deleting a task: `delete`

Format:
`delete <task number>`

Example:
`delete 1`

Expected outcome:
```
Noted, I've removed this task:
 [T][ ] wash dishes
Now you have 2 tasks in the list.
```

---
### Listing all the task in the list: `list`

Format:
`list`

Example and expected outcome:
```
Here are the tasks in your list:
1. [D][ ] CS2103T Quiz 7 (by: Mar 07 2024, 23:59)
2. [E][ ] Taylor Swift Concert (from: Mar 03 2024, 19:00 to: Mar 03 2024, 22:00)
```

---
### Marking task as done: `mark`

Format:
`mark <task number>`

Example:
`mark 1`

Expected outcome:
```
Nice! I've marked this task as done:
 [D][X] CS2103T Quiz 7 (by: Mar 07 2024, 23:59)
```

---
### Unmarking task: `unmark`

Format:
`unmark <task number>`

Example:
`mark 2`

Expected outcome:
```
OK, I've marked this task as not done yet:
 [E][ ] Taylor Swift Concert (from: Mar 03 2024, 19:00 to: Mar 03 2024, 22:00)
```

---
### Finding tasks in the list: `find`

Format:
`find <keyword>`

Example:
`find Quiz`

Expected outcome:
```
Here are the matching tasks in your list:
1. [D][X] CS2103T Quiz 7 (by: Mar 07 2024, 23:59)
```
> [!NOTE]
> `keyword` is case sensitive.

---
### Sorting the list: `sort`

The list will be sorting with the unmark tasks at the top and marked tasks at the bottom. In each section, the tasks will be sorted alphabetically.

Format:
`sort`

Example and expected outcome:
```
Here are the tasks in your list:
1. [E][ ] Taylor Swift Concert (from: Mar 03 2024, 19:00 to: Mar 03 2024, 22:00)
2. [D][X] CS2103T Quiz 7 (by: Mar 07 2024, 23:59)
```

---
### Exiting the program: `bye`

The program will save the list in `./data/data.txt` before exiting.

Format: 
`bye`

Example and expected outcome **(only CLI)**:
```
Saving data ...
Data saved successfully. :)
Bye. Hope to see you again soon!
```
