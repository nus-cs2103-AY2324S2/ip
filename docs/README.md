#  Chatbot Pro User Guide

#### Chatbot Pro is a desktop app for keeping track of your tasks, optimized for use via a Command Line Interface (CLI).

Use it to free your mind of having to remember things you need to do!

![Preview of Chatbot Pro](Ui.png)


# Feature List 

## Adding Tasks

There are 3 types of tasks you can add: Todo, Deadline and Event.

### 1. Todo 
To add a Todo in your list, type in the __description__ followed by `todo` (e.g.`todo read book`).

_Expected output:_
```
Gotcha. I've added this task:
 [T][] read book
Now you have 1 task(s) in the list.
```

### 2. Deadline
To add a Deadline in your list, type in the __description__ and __due date__ (`/by`) followed by `deadline` 
(e.g. `deadline return book /by 2/12/2019 1800`).

_Expected output:_
```
Gotcha. I've added this task:
 [D][ ] return book (by: Dec 02 2019, 18:00)
Now you have 2 task(s) in the list.
```

### 3. Event
To add an Event in your list, type in the __description__ and __event duration__ (`/from`, `/to`) followed by `event`
(e.g. `event project meeting /from 2020-01-01 /to 2020-01-02"`).

_Expected output:_
```
Gotcha. I've added this task:
 [E][ ] project meeting (from: Jan 01 2020, 00:00 to: Jan 02 2020, 00:00)
Now you have 3 task(s) in the list.
```
***
## Updating Status of Completion

To mark a task as _done_, type `mark` followed by the __task number__ (e.g, `mark 2`).

_Expected output:_
```
Good job on finishing this task:
 [D][X] return book (by: Dec 02 2019, 18:00)
```

To change the status back to _not done_, type `unmark` followed by the __task number__ (e.g, `unmark 2`).

_Expected output:_
```
Aw OK, I've marked this task as not done yet:
 [D][ ] return book (by: Dec 02 2019, 18:00)
```
***
## Viewing Task List

To view all tasks saved, type `list`.

_Expected output:_
```
Here are the tasks in your list:
1. [T][ ] borrow book
2. [D][ ] return book (by: Dec 02 2019, 18:00)
3. [E][ ] project meeting (from: Jan 01 2020, 00:00 to: Jan 02 2020, 00:00)
```
***
## Deleting Tasks

To delete a task from the task list, type `delete` followed by the __task number__ (e.g, `delete 2`).

_Expected output:_
```
Sure, I've removed this task:
 [D][ ] return book (by: Dec 02 2019, 18:00)
Now you have 2 tasks in the list.
```
***
## Sorting Tasks

To sort the tasklist (i.e., keep Todo tasks at the front and sort Event and Deadline tasks by date chronologically thereafter), 
type `sort`.

_Given the following list:_
```
Here are the tasks in your list:
1. [E][ ] project meeting (from: Jan 01 2020, 00:00 to: Jan 02 2020, 00:00)
2. [T][ ] borrow book
2. [D][ ] return book (by: Dec 02 2019, 18:00)
```

_Expected Output:_
```
Here are the tasks in your list:
1. [T][ ] borrow book
2. [D][ ] return book (by: Dec 02 2019, 18:00)
3. [E][ ] project meeting (from: Jan 01 2020, 00:00 to: Jan 02 2020, 00:00)
```
