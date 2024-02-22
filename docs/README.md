# SecretaryW User Guide

![SecretaryW screenshot](Ui.png)

SecretaryW is your personal chatbot designed to help you manage your tasks.
Extremely easy to learn and use! :)

## Here are some of the key features:

### Adding todo tasks - 'todo'

Adds a to do task to the current task list.

Example: 'todo learn python'

```
Got it. I've added this task:
[T] [] learn python
Now you have 1 task in the list.
```
### Adding deadline tasks - 'deadline'

Adds a deadline task to the current task list.

Example: 'deadline CS2102 Assignment 1 /by 7/03/2024'

```
Got it. I've added this task:
[D] [] CS2102 Assignment 1 (by March 07 2024)
Now you have 2 task in the list.
```
### Adding event tasks - 'event'

Adds a event task to the current task list.

Example: 'event Career Fair /from 20/02/2024 /to 22/02/2024'

```
Got it. I've added this task:
[E] [] Career Fair (from: Feb 20 2024 to: Feb 22 2024)
Now you have 3 task in the list.
```

### Show all current tasks - 'list'

Lists out all current task with their task type and status.

Example: 'list'

```
Here are the tasks in your list:
1.[T] [] learn python
2.[D] [] CS2102 Assignment 1 (by March 07 2024)
3.[E] [] Career Fair (from: Feb 20 2024 to: Feb 22 2024)
```

### Marking and Unmarking tasks - 'mark' & 'unmark'

Marks and unmarks a task as done.

Example: 'mark 2'

```
Nice! I've marked this task as done:
[X] CS2102 Assignment 1
```

Example: 'unmark 2'

```
OK. I've marked this task as not done yet.
[] CS2102 Assisngment 1
```

### Deleting a task - 'delete'

Deletes a task from the current tasklist.

Example: 'delete 3'

```
Noted. I've removed this task:
[E] Career Fair (from: Feb 20 2024 to: Feb 22 2024)
```

### Finding a task - 'find'

Finds a specific task from the current tasklist. (Case Sensitive)

Example: 'find Assignment'

```
Here are the matching tasks in your list:
[D] [] CS2102 Assignment 1 (by March 07 2024)
[T] [] MA1522 Assignment 2 
```

### Closing chatbot - 'bye'

Ends the session with the chatbot with task list saved to local computer.

Example: 'bye'


### Help message - 'help'

Receives help messages on commands and format in the chatbot.

Example: 'help'


That's all for this simple yet wonderful secretaryW. 


