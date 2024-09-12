# Patrick User Guide
### Launching Patrick
Prerequisites: JDK 11
1. Download patrick.jar [here](https://github.com/erv-teo/ip/releases/tag/A-Release)
2. Navigate to the file directory containing patrick.jar
3. Launch by entering java -jar patrick.jar

## Features

### Adding Tasks
Available task types: `deadline`, `todo`, `event`

Accepted DATETIME formats: "ddMM", "ddMMyy", "ddMMyy HHmm"

#### Adding Deadline tasks
Deadline tasks can be added using the `deadline` command or simply `d`.

Format: `deadline TASK /by DATETIME` OR `d TASK /by DATETIME`

Expected output: 
```
added: [][D] TASK (by DATETIME)
You now have ___ tasks.
```

#### Adding Todo tasks
Todo tasks can be added using the `todo` command or simply `t`.

Format: `todo TASK` OR `t TASK`

Expected output: 
```
added: [][T] TASK
You now have ___ tasks.
```
#### Adding Event tasks
Event tasks can be added using the `event` command or simply `e`.

Format: `event TASK /from DATETIME /to DATETIME` OR `e TASK /from DATETIME /to DATETIME`

Expected output: 
```
added: [][E] TASK (from DATETIME to DATETIME)
You now have ___ tasks.
```

### Adding Tags
Tags can be added to tasks to categorize them.

Format: `tag INDEX TAG_NAME`

`INDEX` should be an integer value which represents the index of the task in the list. 

`TAG_NAME` can be any String.

Expected output:
```
Tag added to task
[][T] TASK [TAG_NAME]
```

### Seeing your tasks
Use the `list` or `ls` command to view all your tasks.


### Finding tasks
The `find` or `f` command can be used when you are looking for specific tasks. 

Format: `find STRING`

Patrick will list all tasks with names that contain STRING.

### Mark tasks
You can indicate tasks as completed using the `mark` or `m` keyword.

Format: `mark INDEX`
`INDEX` should be an integer value which represents the index of the task in the list.

### Unmark tasks
You can unmark a task using the `unmark` or `u` keyword.

Format: `unmark INDEX`
`INDEX` should be an integer value which represents the index of the task in the list.

### Deleting tasks
You can delete a task from the list using the `delete` or `del` keyword.

Format: `delete INDEX`
`INDEX` should be an integer value which represents the index of the task in the list.

### Clearing all tasks
You can clear all tasks in the list using `clear` or `cl`.

### Exiting the program
Use `bye` or `b` to close the program.







