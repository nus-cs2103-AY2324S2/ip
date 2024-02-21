# Alice User Guide

![alt text](https://Benson15912.github.io/ip/Ui.png)

Alice is a desktop app for managing task lists, optimized for use via a 
Command Line Interface while still having the benefits of a Graphical User Interface.


## Adding tasks

### Add ToDos
This action allows users to add a ToDo task to the task list.

A user can add a task by:
todo 'taskname'

Example:
```todo Wash Dishes``` 

Outcome:
```Task successfully added!```

### Add Events
This action allows users to add an Event to the task list.

A user can add a task by:

event 'event name' /from 'day/time' /to 'day/time'

Example:
```event Holiday /from 12Feb /to 13Feb```

Outcome:
```Event successfully added!```


### Add Deadlines
This action allows users to add a Deadline to the task list.

A user can add a task by:

deadline 'name' /by 'YYYY-MM-DD'

Example:
```deadline homework /by 2024-01-01```

Outcome:
```Deadline successfully added!```


### Display Task List
This action displays the current task list

A user can see their tasklist by typing
list

Example:
```list```

Outcome:
```1. [T] [] Push-Level-10```

### Delete a Task
This action allows user to delete a Task

A user can delete a task by typing:

delete "index"

Example:
```delete 1```

Outcome:
```Noted. The following task is removed! [T] [] Push Level-10```


### Mark a Task
This action allows user to mark a task as complete

A user can mark tasks by typing:

mark "index"

Example:
```mark 1```

Outcome:
```Congratulations! Task Completed:[X] Push Level-10```

### Unmark a Task
This action allows user to mark a task as incomplete

A user can unmark tasks by typing:

unmark "index"

Example:
```unmark 1```

Outcome:
``` Task marked as undone :[] Push Level-10```

### Find a Task
This action allows user to find a task by searching.

A user can find a task by:

find "related keywords"

Example:
```find Push```

Outcome:
``` Here are the following matches in your list: 1.[] Push Level-10```


## Feature Check Duplicates

This feature prevents your from adding duplicated tasks to avoid confusion in your list!


## Feature AutoSave

This feature allows the program to autosave after every command,
ensuring that you don't lose any data
