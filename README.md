# Duke Zeh

Duke Zeh is a task management application based on [Project Duke](https://nus-cs2103-ay1920s1.github.io/website/se-book-adapted/projectDuke/index.html).

![Screenshot of application](https://kohguanzeh.github.io/ip/Ui.png)

# User Guide

## Running Duke Zeh
1. Download the `dukezeh.jar` [here](https://github.com/KohGuanZeh/ip/releases/tag/A-Release).
2. It is recommended to move `dukezeh.jar` to an isolated folder.
3. Open the terminal and navigate to folder where `dukezeh.jar` is located.
4. Run `java -jar dukezeh.jar`.

## Command List

### Add ToDo Task
Adds a new task with no datetime attached.
- Note that the `<task-name>` **cannot** be empty.
```
todo <task-name>
```

### Add Deadline Task
Adds a new task with a deadline attached.
- `<task-name>` **cannot** be empty.
- Datetime format **must** adhere to `dd-MM-yyyy HH:mm`.
```
deadline <task-name> \by <dd-MM-yyyy HH:mm>
```

### Add Event Task
Adds a new task with a start datetime and end datetime.
- `<task-name>` **cannot** be empty.
- Datetime format **must** adhere to `dd-MM-yyyy HH:mm`.
```
event <task-name> \from <dd-MM-yyyy HH:mm> \to <dd-MM-yyyy HH:mm>
```

### List Tasks
List out all tasks saved in the list.
```
list
```

### Mark Task
Marks a task as done based on specified index.
- `<task-number>` is based on the **displayed index** of task.
```
mark <task-number>
```

### Unmark Task
Marks a task a not done based on specified index.
- `<task-number>` is based on the **displayed index** of task.
```
unmark <task-number>
```

### Delete Task
Deletes a given task based on specified index. Tasks after the deleted task will have its index shifted.
- `<task-number>` is based on the **displayed index** of task.
- Task deletion can cause displayed indexes to **change**. Recommended to call `list` after `delete` operation.
```
delete <task-number>
```

### Add Task Priority
Assigns a priority to a task based on specified index and value.
- `<task-number>` is based on the **displayed index** of task.
- By default no priority (priority none) is attached to all added tasks.
- `<priority-value>` **ONLY** accepts `high`, `low` or `none`.
- priority value of `none` will remove the given priority to the task.
```
priority <task-number> <priority-value>
```

### Find Task
Lists tasks whose `<task-name>` contains all characters of `<keyword>` in order.
- `<keyword>` cannot be empty but can contain ` `.
```
find <keyword>
```

### Exiting Program
Program can be exited through closing the window or running the `bye` command.
```
bye
```

# Acknowledgements
Many thanks to the professors in charge of CS2103. Many of their resources provided have been referenced for learning.

Peers participating in the [forum](https://github.com/nus-cs2103-AY2324S2/forum/issues) have also been helpful towards development of this project.

Most of the JavaFX code setup has been reused based on their [tutorial guide](https://se-education.org/guides/tutorials/javaFxPart1.html).

The code base also attempts to adhere to the following [Java style guide](https://se-education.org/guides/conventions/java/index.html#java-coding-standard-all).

The [textbook](https://nus-cs2103-ay2324s2.github.io/website/se-book-adapted/index.html) also served as an important guide in this software development project.
