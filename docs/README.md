# Klara User Guide

Klara is a desktop-app that tracks your tasks quickly and simply. It is optimised for text-based use, while 
still having the benefits of a Graphical User Interface (GUI). Typing fast can enable Klara to manage your tasks faster
than traditional GUI apps.

* [Command Summary](#command-summary)
* [Features](#features)
  * [Adding Tasks](#adding-tasks): `todo`/`deadline`/`event`
  * [Viewing all tasks](#viewing-all-tasks): `list`
  * [Marking status of tasks](#marking-status-of-tasks): `mark`/`unmark`
  * [Deleting tasks](#deleting-tasks): `delete`
  * [Finding tasks](#finding-tasks): `find`
  * [Exiting the application](#exiting-the-application): `bye`
  * [Saving the data](#saving-the-data)
* [FAQ](#faq)
* [Application Screenshot](#view-screenshot-of-the-application-here)

## Command Summary
* `todo {task name}` to add todo tasks.
* `deadline {task name} /by {yyyy-mm-dd}` to add deadlines.
* `event {task name} /from {yyyy-mm-dd} /to {yyyy-mm-dd}` to add events.
* `list` to view all tasks.
* `mark {task number}` to mark a task as done.
* `unmark {task number}` to mark a task as undone.
* `delete {task number}` to delete a task.
* `find {keyword}` to find tasks by keyword.
* `bye` to exit the application.

# Features
## Adding tasks
Klara offers three kinds of tasks you can add. They are:
* Todo
* Deadline
* Event

To add them, you can use the following commands:
* `todo {task name}` to add todo tasks.
* `deadline {task name} /by {yyyy-mm-dd}` to add deadlines.
* `event {task name} /from {yyyy-mm-dd} /to {yyyy-mm-dd}` to add events.

### Example `deadline` command
To specifically add a `deadline` task, you can use the command:
```
deadline finish 2103 assignment /by 2021-03-21
```

Klara will respond with:
```
Got it. I've added this task:
[D][ ] finish 2103 assignment (by: Mar 21 2021)
Now you have 1 tasks in the list.
```

## Viewing all tasks
To view all the tasks you have created, you can simply type the command `list`.

Depending on the tasks you have added, it will output differently. Here are my tasks:
```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: Feb 15 2024)
3. [E][ ] NUS Career Fair (from: 21 Feb 2024 to: 29 Feb 2021)
```

## Marking status of tasks
You can mark the status of a task as **done** or **undone** by using the `mark` or `unmark` command .
To toggle your task, you can use the following commands:
* `mark {task number}` to mark that task as done.
* `unmark {task number}` to unmark that task as done.


### Example `mark` command
For example, to mark the **first** task in your task list as done, you can use the command:
```
mark 1
```

Klara will output:
```
Nice! I've marked this task as done:
[T][✘] read book
```

### Example `unmark` command
To mark a task as undone, you can use the `unmark` command. 
You may notice that the task will no longer be crossed.
```
unmark 2
```

Klara will then output:
```
Nice! I've marked this task as undone:
[D][ ] return book (by: Feb 15 2024)
```

## Deleting tasks
You can delete a task by using the `delete` command.
```
delete {task number}
```

### Example `delete` command
To delete the **first** task in your task list, you can use the command:
```
delete 1
```

Klara will respond with the number of tasks remaining and the task that was deleted.
```
Noted. I've removed this task:
[T][ ] read book
Now you have 2 tasks in the list.
```

## Finding tasks
You can find tasks containing a specific **keyword** by using the `find` command
```
find {keyword}
```

### Example `find` command
To find tasks containing the keyword `book`, you can use the command:
```
find book
```

Klara will respond with the tasks that contain the keyword.
```
Here are the matching tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: Feb 15 2024)
```

## Exiting the application
You can exit the application by using the `bye` command
```
bye
```

Klara will respond with a goodbye message and save your progress.
```
Bye. Hope to see you again soon!
```

## Saving the data
Klara saves your tasks to a file so that you can access them the next time you run the application.

## FAQ
* **Q:** How do I save my data?
* **A:** Klara saves your data automatically when you perform any task. 
* You can also manually save your data by using the `bye` command.

* **Q:** How do I access my data?
* **A:** Klara will load your data from the file `duke.txt` when you run the application.

* **Q:** How do I transfer my data to another computer?
* **A:** You can transfer your data by copying the file `duke.txt` to another computer. However, you must place it in 
* the directory called `data` in the same directory as the application.

* **Q:** Can I type shorter commands?
* **A:** Yes, here are the list of aliases you can use to replace the original command:
  * `t` for `todo`
  * `e` for `event`
  * `l` for `list`
  * `m` for `mark`
  * `u` for `unmark`
  * `f` for `find`
  * `del` for `delete`
  * `dead` for `deadline`

## View screenshot of the application here:
![Alt text](Ui.png)
[//]: # (<img src="https://ui.png" width="100" height="100">)