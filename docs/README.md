# Dav User Guide

![Dav TaskBot Interface](/docs/Ui.png)

Dav Taskbot is designed to help you manage your tasks. 
It's an interactive and user-friendly tool that can help 
streamline tracking of tasks and respective information.

## Adding todo task

Add todo tasks.

Command Format: `todo TASK`

Expected output:

```
OK! I've added this task:
 [T][] Task
Now you have x tasks in the list.
```
## Adding deadline task

Add deadline tasks containing date and time of respective task.

Command Format: `deadline TASK /by yyyy-MM-dd HHmm`

Expected output:

```
OK! I've added this task:
 [D][] Task
 (by: yyyy-MM-dd HHmm)
Now you have x tasks in the list.
```
## Adding event task

Add event tasks containing starting and ending dates and times of respective task.

Command Format: `event TASK /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm`

Expected output:

```
OK! I've added this task:
 [E][] Task
 (from: yyyy-MM-dd HHmm to: yyyy-MM-dd HHmm)
Now you have x tasks in the list.
```
## Listing tasks
Lists tasks in the list. 

Command Format: `list`

Expected output:

```
Here are the tasks in your list:
1. ...
```
## Marking tasks done
Marks the respective task as done.

Command Format: `mark LIST_INDEX`

Expected output:

```
OK! I've marked this task as done:
 [T][X] TASK
```
## Marking tasks undone
Marks the respective task as undone.

Command Format: `unmark LIST_INDEX`

Expected output:

```
OK! I've marked this task as not done yet:
 [T][] TASK
```
## Checking tasks on a specific date
Lists task occurring on a specific date.

Command Format: `check yyyy-MM-dd`

Expected output:

```
Tasks on yyyy-MM-dd:
1. ...
```
## Finding tasks 
Lists tasks with keyword in the task description.

Command Format: `find KEYWORD`

Expected output:

```
Here are the matching tasks in your list:
1. ...
```

## Deleting tasks
Deletes task from the list.

Command Format: `delete LIST_INDEX`

Expected output:

```
Task removed:
 [T][X] TASK
Now you have x tasks in the list.
```
## Tagging tasks 
Tags task with tag.

Command Format: `tag LIST_INDEX TAG_NAME`

Expected output:

```
Task tagged:
 [T][X] TASK
 Tags: TAG_NAME
```
## Untagging tasks
Removes tag for task.

Command Format: `untag LIST_INDEX TAG_NAME`

Expected output:

```
Task untagged:
 [T][X] TASK
```