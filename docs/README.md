# TaskFlow User Guide

TaskFlow is a text-based personal assistant chatbot designed to help you keep track of various tasks!

![Screenshot of the product](/Ui.png)

## Features 

### Adding a todo task

Adds a todo task to the task list.

Format: `todo <description>`

Example: `todo smtg...`

Expected output:
```
Got it. I've added this task:
T | | todo smtg
Now you have 1 tasks in the list.
```

### Adding a deadline task 

Adds a task with deadline to the task list.

Format: `deadline <description> /by <yyyy-MM-dd hh:mma>`

Example: `deadline ip /by 2024-02-23 11:59pm`

Expected output:
```
Got it. I've added this task:
D | | ip |Feb 23 2024 at 11:59pm
Now you have 1 tasks in the list.
```

### Adding an event

Adds an event with time to the list.

Format: `event <description> /from <startDate> /to <endDate>`

Example: `event career fest /from 20-2-2024 /to 22-2-2024`

Expected output:
```
Got it. I've added this task:
E | | career fest | 20-2-2024 - 22-2-2024
Now you have 1 tasks in the list.
```

### List

To view the list of tasks.

Format: `list`

Expected output:
```
Here are the tasks in your list:
1. T | | todo smtg
2. D | | ip |Feb 23 2024 at 11:59pm
3. E | | career fest | 20-2-2024 - 22-2-2024
```

### Delete

To delete a task from the list.

Format: `delete <index>`

Example: `delete 1`

Expected output:
```
Noted. I've removed this task:
T | | todo smtg
Now you have 1 tasks in the list.
```
### Mark

To mark a task in the list as done.

Format: `mark <index>`

Example: `mark 1`

Expected output:
```
Nice! I've marked this task as done:
T |X| todo smtg
```

### Unmark

To unmark a task in the list as undone.

Format: `unmark <index>`

Example: `unmark 1`

Expected output:
```
OK, I've marked this task as not done yet:
T | | todo smtg
```

### Archive

To archive a task from the list.

Format: `archive <index>`

Example: `archive 1`

Expected output:
```
This task has been archived successfully!
T | | todo smtg
Now you have 1 tasks in the archive list.
```

### Unarchive

To unarchive a task from the archive list to the task list.

Format: `unarchive <index>`

Example: `unarchive 1`

Expected output:
```
This task has been unarchived sucessfully!
T | | todo smtg
Now you have 1 tasks in the archive list.
```

### Find

To find a task with specific keyword.

Format: `find <keyword>`

Example: `find book`

Expected output:
```
Here are the matching tasks in your list:
1. T | | todo read book
2. T | | todo return book
```

### ListArchive

To view the list of archived tasks.

Format: `list archive`

Expected output:
```
Here are the tasks in your list:
1. T | | todo smtg
```

### Saving The Data

TaskFlow data are saved automatically after any command that changes the data.