# [Steve User Guide](https://github.com/jieqiboh/ip)

<img src="./Ui.png" width="300">

Steve frees your mind of having to remember things you need to do. It's text-based, easy-to-learn, and fast to use!  
Given below are instructions on how to use it.

## Setting up Steve
1. Download ip.jar file into your desired directory.
2. Run ip.jar. <br>

On your first use, a data file will be created that stores all your tasks. Do not modify it.

## Features

### View All Tasks: `list`
View all tasks in the list. <br>
Format: `list`

### Adding Todos: `todo`
Add a todo task to the list. <br>
Format: `todo [DESCRIPTION] /p [PRIORITY VALUE]`, where `/p` denotes the priority value.<br>
Examples:<br>
`todo buy bread /p 5`

### Adding Deadlines: `deadline`
Add a deadline task to the list.<br>
Format: `deadline [DESCRIPTION] /p [PRIORITY VALUE] /by [TIME]`, where `/p` denotes the priority value.<br>
Examples:<br>
`deadline do homework /p 4 /by 01/05/2000 0001`
<div markdown="block" class="alert alert-info">
`[TIME]` can be provided in the `dd/mm/yy hhmm` format.
</div>

### Adding Events: `event`
Add a event task to the list.<br>
Format: `event [DESCRIPTION] /p [PRIORITY VALUE] /from [TIME] /by [TIME]`, where `/p` denotes the priority value.<br>
Examples:<br>
`event group meeting /p 5 /from 01/05/2000 0001 /to 01/06/2000 0001`
<div markdown="block" class="alert alert-info">
`[TIME]` can be provided in the `dd/mm/yy hhmm` format.
</div>

### Marking Tasks as Done: `mark`
Mark a task as complete.<br>
Format: `mark [INDEX]`, where `INDEX` is the index of the task in the task list.

### Unmarking Tasks as Done: `unmark`
Unmark a task as complete.<br>
Format: `unmark [INDEX]`, where `INDEX` is the index of the task in the task list.

### Deleting Tasks: `delete`
Delete a task.<br>
Format: `delete [INDEX]`, where `INDEX` is the index of the task in the task list.

### Finding Tasks: `find`
Find all tasks containing a certain keyword.<br>
Format: `find [KEYWORD]`, where `KEYWORD` is the keyword being searched for in the tasks.

### Undo-ing Actions: `undo`
Undoes your previous action.<br>
Format: `undo`

