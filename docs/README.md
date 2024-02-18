# Aerial ChatBot User Guide

![Screenshot of the Gui](https://reganchoy.github.io/ip/Ui.png)

Aerial Chatbot is a desktop app for keeping track of your daily tasks. It is optimized with the use of a Command Line Interface (CLI)

>[!NOTE]
>Words in <span style="color:orange">[UPPER_CASE]</span> are parameters.

## Adding Tasks

There are 3 kinds of tasks you can create.

1)ToDo - Tasks can be done anytime, not time sensitive

2)Deadlines - Tasks must be done before a date/time

3)Events - Tasks that have a start and end date/time

>Time format must be as follows: <span style="color:blue">DD-MM-YYYY HH:MM</span>

>Example: 3 Feb 2001 6pm should be 03-02-2001 18:00

### Todo Command Format: 
todo <span style="color:orange">
[TASK DESCRIPTION]
</span>

Example: Create a todo for baking cookies
```
todo bake cookies
```

### Deadline Command Format: 
deadline <span style="color:orange">[TASK DESCRIPTION]</span> <span style="color:green">/[TIME]</span>

Example: Create a deadline to pack cookies by 3 Feb 2001 12pm
```
deadline pack cookies /03-02-2001 12:00
```

### Event Command Format: 
event <span style="color:orange">[TASK DESCRIPTION]</span> <span style="color:green">/[START TIME]</span> <span style="color:blue">/[END TIME]</span>

Example: Create a birthday party event from 3 Feb 2001 2pm to 3 Feb 2001 6pm.
```
event birthday party /03-02-2001 14:00 /03-02-2001 18:00
```

## Features

### List Task
This command lists all the tasks that you have.
```
list
```

>Use the list command to find the task index for each task

### Mark Task
This command marks task as completed.

mark <span style="color:orange">[TASK INDEX]</span>
```
mark 1
mark 2
```

### Unmark Task
This command unmarks task as uncompleted.

unmark <span style="color:orange">[TASK INDEX]</span>
```
unmark 1
unmark 2
```

### Delete Task
This command deletes task from the task list.

delete <span style="color:orange">[TASK INDEX]</span>
```
delete 1
delete 2
```

### Clear Tasks
This command removes all tasks permanently.
```
clear
```

### Postpone
This command enables you to change the date/time of deadlines and events.

postpone <span style="color:orange">[TASK NAME]</span> <span style="color:green">/[TIME FORMAT]</span>
```
postpone pack cookies /04-03-2001 12:00
```

>[!IMPORTANT]
>Postponing an event will require a start and end <span style="color:green">/[TIME FORMAT]</span>

### Find Task
This command enables you to find all tasks that contain the keyword.

find <span style="color:orange">[KEYWORD]</span>
```
find bake cookies
find birthday
```

>[!WARNING]
>Users should never edit the data.txt file directly as it can corrupt the data.

>If data has been corrupted, users can delete the data.txt file to reset.
