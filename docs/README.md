# Datuk User Guide 👴

![](/docs/Ui.png)

Datuk is a chatbot that helps you manage your tasks.

## Quick start
1. Ensure you have java 11 installed on your device.
2. Download the latest [Datuk.jar](https://github.com/breezetall/ip/releases/tag/A-Release).
3. Double click to run and enjoy!

## Feature todo
Adds a todo event to your task list. <br>
Format: 
```
todo [description]
```
Example: `todo homework`
<br>

## Feature deadline
Adds a deadline event to your task list. <br>
Format:
```
deadline [description] /by [yyyy-MM-dd]
```

Example: `deadline assignment 1 /by 2024-10-10`
<br>

## Feature event
Adds an event deadline to your task list. <br>
Format:
```
event [description] /from [start] /to [end]
```

Example: `event quiz 1 /from 25 feb /to 1 mar`
<br>

## Feature mark
Marks the specified tasks in the list as completed. <br>
Format:
```
mark [index]
```

Example: `mark 5`
<br>

## Feature unmark
Marks the specified task in the list as incomplete. <br>
Format:
```
unmark [index]
```

Example: `unmark 2`
<br>

## Feature delete
Removes the specified task from the task list. <br>
Format:
```
delete [index]
```

Example: `delete 3`
<br>

## Feature find
Searches the task list for the specified keyword in each task decription and return matching tasks. <br>
Format:
```
find [keyword]
```

Example: `find homework`
<br>

## Feature bye
Exits the program. <br>
<br>
Example: `bye`
