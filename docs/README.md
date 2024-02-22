# Destiny User Guide

![Screenshot of Destiny](Ui.png)

Welcome to **Destiny**'s user guide!

**Destiny** is your personal secretary, and will help you to manage your tasks,
track your progress on them, and delete them. All this happens while it automatically
saves the tasklist just in case your computer crashes.

Here's the full list of features (and commands) that comes with Destiny:
* Displaying the tasks in the task list
  * list 
  * find
* Progress Tracking
  * mark
  * unmark
* Adding tasks to the task list
  * todo
  * deadline
  * event
* Removing specific tasks
  * delete
* Exiting
  * bye

## Displaying tasks
Currently, you can either list out all tasks or display specific tasks
you want to find.

### List

Simply lists out all tasks in the task list.

Example: `list`

Destiny then prints out all the tasks for you.

```
Here are the tasks in your list:
 1. [T][ ] Daily: Laundry
 2. [D][ ] submit cs2103 deliverables (by: Mar 23 2024 11:59PM)
 3. [E][ ] host friends for a movie night (from: Feb 24 2024 03:00PM  to: Feb 24 2024 11:00PM)
```

### Find 

List tasks which are named similarly to the one you're trying to find.

Format: `find [(partial) name of task]`

Example: `find laundry` or `find laund`

Destiny then prints out all the tasks it has found that matches the
description you have provided.

```
Here are the matching tasks in your list:
1. [T][ ] Daily: Laundry
```

## Progress Tracking

We can track the progress of tasks by marking or un-marking them.

### Mark

Marks a task as done. The task marked will follow the index indicated when
using the `list` command

Format: `mark [index]`

Example: `mark 1`

After the command, the task will be visibly marked with a cross.

```
Nice! I've marked this task as done:
  [T][X] Daily: Laundry
```
### Unmark

Marks a task as not done. The task unmarked will follow the index indicated when
using the `list` command

Format: `unmark [index]`

Example: `unmark 1`

After the command, the task will be unmarked, and the cross will be removed.

```
OK, I've marked this task as not done yet:
  [T][ ] Daily: Laundry
```
## Adding Tasks

Destiny supports three types of tasks:
1. ToDo
   - Basic task with a description
2. Deadline
   - Task with a specified deadline
3. Event
   - Task with a specified timeline
   
### ToDo

Creates a ToDo task.

Format: `todo [description]`

Example: `todo Get 7 hours of sleep`

Displays the new task you have added to the task list.

```
Got it. I've added this task:
   [T][ ] Get 7 hours of sleep
Now you have 4 tasks in the list.
```

### Deadline


Creates a ToDo task.

Format: `todo [description]`

Example: `todo Get 7 hours of sleep`

Displays the new task you have added to the task list.

```
Got it. I've added this task:
   [T][ ] Get 7 hours of sleep
Now you have 4 tasks in the list.
```

### Event

Creates a ToDo task.

Format: `todo [description]`

Example: `todo Get 7 hours of sleep`

Displays the new task you have added to the task list.

```
Got it. I've added this task:
   [T][ ] Get 7 hours of sleep
Now you have 4 tasks in the list.
```

### 

// Describe the action and its outcome.

// Give examples of usage

Example: `keyword (optional arguments)`

// A description of the expected outcome goes here

```
expected output
```

## Feature ABC

// Feature details


## Feature XYZ

// Feature details