# User Guide

Hammy chatbot is an application for people to navigate their tasks in daily lives. It consists of a variety of tasks, including Todo, Deadline and Event tasks. Besides, Hammy chatbot also includes other features, such as list, mark and unmark to help you control your tasks more effeciently. Most importantly, it is user-friendly and easy-to-use!

* How to read?
* Features
  * Viewing all available commands within application
  * Listing all tasks
  * Adding tasks
  * Deleting tasks
  * Mark tasks as done
  * Mark tasks as undone
  * Different type of listing your tasks
  * Say Hi To Me <3
  * Surprised!
* FAQ
* Future Updates?


## Features 

### Viewing all available commands within application
To view all available commands within application, we can enter `help` to in the user input to list out all commands.

Command:
```
help
```

### Listing all tasks
You can list all your tasks using the `list` command.

Command:
```
list
```

Shortcut:
```
/ls
```

> [!NOTE]
> If there is no tasks in the list, Hammy will output `There is no tasks currently!` instead

### Adding tasks
There are three types of tasks
1. todo
  * A task with a description
2. deadline
  * A task with a Deadline date
  * Description is optional
3. event
  * A task with a From date and a to Date
  * Description is optional

**Adding todo tasks:**
Command:
```
todo YOUR_DESCRIPTION_HERE
```

Shortcut:
```
/t YOUR_DESCRIPTION_HERE
```

Expected outcome:
```
Got it. I have added:
[T][ ] YOUR_DESCRIPTION_HERE
```

**Adding deadline tasks:**
Command:
```
deadline [YOUR_DESCRIPTION_HERE] /by: XXXX-XX-XX
```

Shortcut:
```
/d [YOUR_DESCRIPTION_HERE] /by: XXXX-XX-XX
```

Expected outcome:
```
Got it. I have added:
[D][ ] YOUR_DESCRIPTION_HERE (by: XX XXX XXXX)
```

> [!NOTE]
> For deadline tasks, description is optional.

**Adding event tasks:**
Command:
```
event [YOUR_DESCRIPTION_HERE] /from: XXXX-XX-XX /to: XXXX-XX-XX
```

Shortcut:
```
/e [YOUR_DESCRIPTION_HERE] /from: XXXX-XX-XX /to: XXXX-XX-XX
```

Expected outcome:
```
Got it. I have added:
[E][ ] YOUR_DESCRIPTION_HERE (from: XX XXX XXXX to: XX XXX XXXX)
```

> [!NOTE]
> Just like deadline tasks, description is optional.


  * Delete tasks
  * Mark tasks as done
  * Mark tasks as undone
  * Different type of listing your tasks
  * Say Hi To Me <3
  * Surprised!
## Usage

### `deadline` - add a deadline task

To add a deadline task

Example of usage: 

`deadline [YOUR_TASK_HERE] /by DATE`

Expected outcome:

The output should ensure your task has been succesffuly added, otherwise it would state your input error.

```
Got it. I have added:
[D][ ] YOUR_TASK_HERE (by: deadline_date)
```
