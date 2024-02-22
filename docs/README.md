# HariUp User Guide

<img src="./Ui.png" width="300">

Imagine you have to Hurry-Up!
Those countless tasks are not going to manage themselves
Just Level Up with "Hari-Up!" 

## Setting up HariUp
1. Download the hari.jar file into your desired directory.
2. Run hari.jar. <br>

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

### Adding Deadlines: `deadline`
Add a deadline task to the list.<br>
Format: `deadline [DESCRIPTION] /by [TIME]` <br>
Examples:<br>
```
deadline do CS2103T IP /by 23/02/2024 2359<br>
```
`[TIME]` can be provided/recommended to be in the `dd/mm/yy hhmm` format.

### Adding Events: `event`
Add an event task to the list.<br>
Format: `event [DESCRIPTION] /from [TIME] /to [TIME]` <br>
Examples:<br>
```
event watch CS2103T lecture videos /from 20/02/2024 0000 /to 20/02/2024 0230<br>
```
`[TIME]` can be provided/recommended to be in the `dd/mm/yy hhmm` format.

### Marking Tasks as Done: `mark`
Mark a task as complete.<br>
Format: `mark [INDEX]`, where `INDEX` is the index of the task in the task list.

Example: `mark 1(index in list)`
Example: `unmark 1(index in list)`

### Unmarking Tasks as Done: `unmark`
Unmark a previously marked task.<br>
Format: `unmark [INDEX]`, where `INDEX` is the index of the task in the task list.

### Deleting Tasks: `delete`
Delete a task.<br>
Format: `delete [INDEX]`, where `INDEX` is the index of the task in the task list.

Example: `delete 1(index in list)`

### Finding Tasks: `find`
Find all tasks containing a certain keyword.<br>
Format: `find [KEYWORD]`, where `KEYWORD` is the keyword searched for among the tasks.

Example: `find lecture`

### Undo-ing Actions: `undo`

Undo your previous action.<br>
Format: `undo`