# Belle User Guide

<picture>
<img alt="Belle Ui" src="Ui.png">
</picture>

Belle is a friendly chatbot that helps you to
keep track of your many tasks.

## 1) Adding Deadline Tasks : `deadline`

Add deadline tasks to todo list.
Deadline tasks are tasks with a deadline.

### Command:

```dtd
deadline {deadline task to do} /by {deadline}
```
### Example Command:
To add "submit essay" task with a deadline of "3 March 23.59pm" the command is

```dtd
deadline submit essay /by 3 March 23.59pm
```
Expected output is
```
--------------------------
Got it. I've added this task.
[D] [] submit essay (by: 3 March 23.59pm) 
Now you have 1 tasks in the list.
--------------------------
```
### Extra notes
If deadline given is in yyyy-mm-dd format (e.g., 2019-10-15), 
deadline will be transformed to a more readable form MMM dd yyyy 
e.g., (Oct 15 2019).

Command given:
```dtd
deadline submit essay /by 2019-10-15
```
Expected output is
```
--------------------------
Got it. I've added this task.
[D] [] submit essay (by: Oct 15 2019) 
Now you have 1 tasks in the list.
--------------------------
```


## 2) Adding Event Tasks : `event`

Add event tasks to todo list.
Event tasks are tasks with a start date/time and an end date/time.

### Command:

```dtd
event {event task to do} /from {start date/time} /to {end date/time}
```
### Example Command:
To add "project meeting" task with a start date/time
of "Mon 2pm" and end date/time of "4pm" the command is

```dtd
event project meeting /from Mon 2pm /to 4pm
```
Expected output is
```
--------------------------
Got it. I've added this task.
[E] [] project meeting (from: Mon 2pm to: 4pm) 
Now you have 1 tasks in the list.
--------------------------
```
### Extra notes
If start/end date/time given is in yyyy-mm-dd format (e.g., 2019-10-15),
the date/time will be transformed to a more readable form MMM dd yyyy
e.g., (Oct 15 2019).

Command given:
```dtd
event project meeting /from 2003-02-13 /to 2003-02-14
```
Expected output is
```
--------------------------
Got it. I've added this task.
[E] [] project meeting (from: Feb 13 2003 to: Feb 14 2003) 
Now you have 1 tasks in the list.
--------------------------
```


## 3) Adding Todo Tasks : `todo`

Add Todo tasks to todo list.
Todo tasks do not have additional information.

### Command:

```dtd
todo {todo task to do}
```
### Example Command:
To add "submit essay" task with a deadline of "3 March 23.59pm" the command is

```dtd
todo pet cat
```
Expected output is
```
--------------------------
Got it. I've added this task.
[T] [] pet cat
Now you have 1 tasks in the list.
--------------------------
```

## 4) bye : `bye`

Stops the program. Upon entering another input, the program will exit.

### Command:

```dtd
bye
```
### Example Command:
```dtd
bye
```
Expected output is
```
--------------------------
Till next time!! Goodbye.
--------------------------
```



## 5) Delete : `delete`

Removes a task from the todo list by specifying its index.

### Command:

```dtd
delete {index of task to be removed}
```
### Example Command:
To delete task at index 2 the command is
```dtd
delete 2
```
Expected output is
```
--------------------------
Noted. I've removed this task:
[T] [] pet cat
Now you have 5 tasks in the list.
--------------------------
```

## 6) Find : `find`

Search for a task by a keyword in the name of the task.

### Command:

```dtd
find {key word}
```
### Example Command:
To search for a task with key word "project"
```dtd
find project
```
Expected output is
```
--------------------------
Here are the matching tasks in your list.
1. [E] [] project meeting (from:Feb 13 2003 to:Feb 14 2003) 
2. [E] [] project submission (from:Feb 13 2003 to:Feb 14 2003)
--------------------------
```



## 7) List : `list`

List out all the tasks in your todo list.

### Command:

```dtd
list
```
### Example Command:
```dtd
list
```
Expected output is
```
--------------------------
Here are the tasks in your list.
1. [T] [] pet cat 
2. [E] [] project submission (from:Feb 13 2003 to:Feb 14 2003)
--------------------------
```



## 8) Mark : `mark`

Mark a task as done by using the index of the task.

### Command:

```dtd
mark {index of task to be marked as done}
```
### Example Command:
To mark task at index 2 as done the command is
```dtd
mark 2
```
Expected output is
```
--------------------------
Nice! I have marked this task as done:
[T] [X] pet cat 
--------------------------
```



## 9) Unmark : `unmark`

Mark a task as not done by using the index of the task.

### Command:

```dtd
unmark {index of task to be marked as not done}
```
### Example Command:
To mark task at index 2 as not done the command is
```dtd
unmark 2
```
Expected output is
```
--------------------------
OK, I've marked this task as not done yet:
[T] [] pet cat 
--------------------------
```

## 10) Snooze : `snooze`

Edit the deadline of deadline tasks and end date of event tasks.

### Command:

```dtd
snooze {index of task to snooze} to {new deadline}
```
### Example Command:
To edit the deadline of task at index 2 to 4pm the command is
```dtd
snooze 2 to 4pm
```
Expected output is
```
--------------------------
Nice! I've shifted the deadline of:
[D] [] pet cat (by: 4pm)
--------------------------
```
### Extra Notes
Snooze cannot be used on todo tasks as they do not have a deadline.
