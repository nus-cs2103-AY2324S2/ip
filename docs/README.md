# HariUp User Guide

<img src="./Ui.png" width="300">

You need to Hurry-Up! <br>
Those countless tasks are not going to manage themselves! <br>
No need to worry, just use "Hari-Up!" 

## Setting up HariUp
1. Download the hari.jar file into a folder of your choice
2. Run hari.jar, using the following command, within your folder;
```
java -jar hari.jar
```

## Features

### View All Tasks: `list`
View all tasks in the list. <br>
Format: `list`
### Adding Todos: `todo`
Add a todo task to the list. <br>
Format: `todo [DESCRIPTION]<br>
Examples:<br>
```
todo do assignment
```

### Adding Events: `event`
Add an event task to the list.<br>
Format: `event [DESCRIPTION] /from [TIME] /to [TIME]` <br>
Examples:<br>
```
event watch CS2103T lecture videos /from 20/02/2024 0000 /to 20/02/2024 0230
```
`[TIME]` can be provided/recommended to be in the `dd/mm/yy hhmm` format.

### Adding Deadlines: `deadline`
Add a deadline task to the list.<br>
Format: `deadline [DESCRIPTION] /by [TIME]` <br>
Examples:<br>
```
deadline do CS2103T IP /by 23/02/2024 2359
```
`[TIME]` can be provided/recommended to be in the `dd/mm/yy hhmm` format.

### Marking Tasks as Done: `mark`
Mark a task as complete.<br>
Format: `mark [INDEX]`, where `INDEX` is the index of the task in the task list.

Example: 
```
mark 1 (refer to the task number)
```

### Unmarking Previously Marked Tasks: `unmark`
Unmark a previously marked task.<br>
Format: `unmark [INDEX]`, where `INDEX` is the index of the task in the task list.

Example: 
```
unmark 1 (refer to the task number)
```

### Finding Tasks: `find`
Find all tasks containing a certain keyword.<br>
Format: `find [KEYWORD]`, where `KEYWORD` is the keyword searched for among the tasks.

Example: 
```
find lecture
```

### Deleting Tasks: `delete`
Delete a task.<br>
Format: `delete [INDEX]`, where `INDEX` is the index of the task in the task list.

Example: 
```
delete 1 (refer to the task number)
```

### Undo-ing Actions: `undo`

Undo your previous action.<br>
Format: `undo`
