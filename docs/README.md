# Killua Online User Guide

![Product screenshot](Ui.png)

Killua Online is your best everyday partner, it helps you to keep track of various tasks with simple commands.

## Add Tasks

There are three types of "tasks" that are supported by Killua Online:
1. Todo - Tasks with description.
2. Deadline - Tasks with description, due date and time.
3. Event - Tasks with description, start date and time, end date and time.

### Add a Todo task

Add a Todo task to be keep tracked.

Command Syntax: `todo {task description formed by at least one word}`

Example: `todo breakfast`, `todo return book`

### Add a Deadline task

Add a Deadline task to be keep tracked.

Syntax: `deadline {task description formed by at least one word} /by {yyyy-MM-dd HH:mm or yyyy-MM-dd}`

Example: `deadline homework /by 2022-12-31 15:00`

### Add an Event task

Add an Event task to be keep tracked.

Syntax: `event {task description formed by at least one word} 
/from {yyyy-MM-dd HH:mm or yyyy-MM-dd} /to {yyyy-MM-dd HH:mm or yyyy-MM-dd}`

Example: `event exam /from 2022-12-31 15:00 /to 17:00`

## List tasks 

List out all the existing tasks being keep tracked.

Syntax: `list`

Example: `list`

## Finding task by keyword

Find all tasks containing a certain given keyword.

Syntax: `find {keyword formed by at least one word}`

Example: `find book`

## Delete task

Delete one of the task from the existing tasks by giving the index in tasklist.

Syntax: `delete {index of task (integer in [1, total number of tasks])}`

Example: `delete 1`

## Mark task

Mark one of the task from the existing tasks by giving the index in tasklist as done.

Syntax: `mark {index of task (integer in [1, total number of tasks])}`

Example: `mark 1`

## Unmark task

Mark one of the task from the existing tasks by giving the index in tasklist as not done.

Syntax: `unmark {index of task (integer in [1, total number of tasks])}`

Example: `unmark 1`

## Add tag to task

Add tag to one of the task from the existing tasks by giving the index in tasklist.

Syntax: `tag {index of task (integer in [1, total number of tasks])} {tag (single word without space)}`

Example: `tag 1 urgent`

## Exit program

Exit program.

Syntax: `bye`

Example: `bye`