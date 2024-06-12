# Arona User Guide

![Product Screenshot](Ui.png)

Arona is a chatbot for managing your tasks, deadlines, and events. Send your command request and the chatbot will process it for you.
The list of tasks is continuously saved on the user's computer. in `.txt` format.

## How to Use

1. Ensure you have Java 11 or above installed.
> Refer [here](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A) for the installation guide.
2. Download the latest release of the arona jar file. The releases can be found [here](https://github.com/tzaph/ip/releases).
3. Copy the file to a folder as the home folder. The folder will also contain your task list data.
4. Open the terminal at the respective folder and run the `java -jar arona.jar` command to run the application.

## Features

> ### Note
> Words in `UPPER_CASE` are the parameters to be supplied by the user.
>    >    e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo Add User Guide`.

### Adding Tasks

Adds a task to your task list. There are three types of tasks:

### To Do Tasks: `todo`

A To Do task is a task without any start or due date attached.

Format: `todo DESCRIPTION`, where `DESCRIPTION` can contain multiple words

Alternative aliases: `t`, `td`

Examples:
* `todo Exercise`
* `t Clean my bedroom`

Arona will display the added task and the number of tasks in the task list.

```
I've added this task, Sensei!
    [T][ ] Clean my bedroom
Now your task list has 1 task.
```


### 2. Deadline tasks: `deadline`

A Deadline is a task that has a due date attached. In other words, it is the type of task to be done before a specific date.

Format: `deadline DESCRIPTION /by DUE_DATE`

The due date must be in the format `YYYY-MM-DD`, otherwise the command is rejected.

Alternative aliases: `d`, `dl`

Examples:
* `deadline ST2334 Quiz /by 2024-02-22` Adds a deadline with description "ST2334 Quiz" and due date set to be 22nd February 2024.

Expected output:
```
I've added this deadline, Sensei!
    [D][ ] ST2334 Quiz (by: Feb 22 2024)
Now your task list has 2 tasks.
```

### 3. Event tasks: `event`

An Event is a task that has a start date and an end date attached. In other words, it is the type of task to be done between a date interval.

Format: `event DESCRIPTION /from START_DATE /to END_DATE`

Alternative aliases: `e`, `ev`

Examples:
* `ev Recess Week (Grind Session) /from 2024-02-24 /to 2024-03-03` Adds an Event with description "Recess Week (Grind Session)"
  where start date and end date will be set to 24th February 2024 and March 3rd 2024 respectively.

Expected output:
```
I've added this event, sensei!
    [E][ ] Recess Week (Grind Session) (from: Feb 24 2024 to: Mar 03 2024)
Now your task list has 3 tasks.
```

### List all current tasks: `list`

Lists all tasks currently on the list.

Format: `list`

Alternative aliases: `l`, `ls`, `li`

Expected output:
```
Sense! Pick a task. I'll back you up!
1. [T][ ] Clean my bedroom
2. [D][ ] ST2334 Quiz (by: Feb 22 2024)
3. [E][ ] Recess Week (Grind Session) (from: Feb 24 2024 to: Mar 03 2024)
4. [T][ ] Finalize CS2103T iP
5. [T][ ] CS2102 Practice Quizzes
```


### Find tasks by specified keyword: `find`

Lists all tasks containing a certain keyword along their index number of the list.

Format: `find KEYWORD`

The command only searches for the keyword in the description of the tasks. Searching for tasks with a specified month or date is not supported yet.

Alternative aliases: `f`

Examples:
* `find Recess week`
* `find quiz`

Expected output:
```
Here are the matching tasks in your list:
2. [D][ ] ST2334 Quiz (by: Feb 22 2024)
5. [T][ ] CS2102 Practice Quizzes
```

### Delete a task by index: `delete`

Deletes a task by its index from the list.

Format: `delete INDEX` deletes the task at the specified `INDEX`

The index refers to the index number shown in the task list when we use `list`.

Alternative aliases: `del`

Examples:
* `delete 3` deletes task 3 from the list

Expected output:
```
I've removed this task, Sensei!
    [E][ ] Recess Week (Grind Session) (from: Feb 24 2024 to: Mar 03 2024)
Now, your task list has 4 tasks.
```

### Mark a task by index: `mark`

Marks a task at an index from the list as done. A marked task has the second box filled (`[X]`)

Format: `mark INDEX` marks the task at the specified `INDEX` as done.

The index refers to the index number shown in the task list when we use `list`

Alternative aliases: `m`

Examples:
* `mark 2` marks task 2 as done

Expected output:
```
I've mark this task as done, Sensei!
    [D][X] ST2334 Quiz (by: Feb 22 2024)
```


### Unmark a task by index: `unmark`

Marks a task at an index from the list as not done.

Format: `unmark INDEX` marks the task at the specified `INDEX` as not done.

The index refers to the index number shown in the task list when we use `list`

Alternative aliases: `u`, `um`

Expected output:
```
I've mark this task as not done, Sensei!
    [D][ ] ST2334 Quiz (by: Feb 22 2024)
```


### Exit : `bye`

Terminates the chatbot. 

Format: `bye`

Alternative aliases: `exit`, `b`

The task list is stored persistently in the user's local folder, so the task list will stay the same in the next use of the chatbot.

## Command Summary

| **Action**                | **Format**                                        |
|---------------------------|---------------------------------------------------|
| Add a todo task           | `todo DESCRIPTION`                                |
| Add a deadline task       | `deadline DESCRIPTION /by DUE_DATE`               |
| Add an event task         | `event DESCRIPTION /from START_DATE /to END_DATE` |
| List all tasks            | `list`                                            |
| Find all tasks by keyword | `find KEYWORD`                                    |
| Delete a task             | `delete INDEX`                                    |
| Mark a task as done       | `mark INDEX`                                      |
| Mark a task as not done   | `unmark INDEX`                                    |
| Terminate the chatbot     | `bye`                                             |

## Uncovered Issues
If you have any issues with the app, feel free to address you concerns [here](https://github.com/tzaph/ip/issues)