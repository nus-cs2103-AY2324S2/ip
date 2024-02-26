# Reacher User Guide

![](/docs/Ui.png)

This is a bot to help with recording of tasks.

## Adding Todos

Adds a task with no time.

Format: `add, {task name}, todo`
Example: `add, shower, todo`
```
I've added [T][] shower
```
## Adding deadlines

Adds a task with a deadline.

Format: `add, {task name}, deadline, {deadline}`
Example: `add, reading, deadline, 2001-12-12`
```
I've added [D][] reading (by: Dec 12 2001)
```

## Adding Events

Adds a task with a start and end time.

Format: `add, {task name}, event, {start date}, {end date}`
Example: `add, Dancing, event, 2001-12-12, 2002-12-12`
```
I've added [E][] Dancing (from: Dec 12 2001 to:2002-12-12)
```

## Edit Tasks

Mark tasks done, undone or delete them.

Format: `edit, {task number}, {done, undone or delete}`
Example: `edit, 1, done`
```
I've added [D][X] reading (by: Dec 12 2001)
```
## Find Tasks

Find and show all tasks with keyword in their name.

Format: `find, {keyword}`
Example: `find, read`
```
Tasks:
[T][] read
[D][X] reading (by: Dec 12 2001)
```
## List Tasks

List all tasks recorded.

Example: `list`
```
Tasks:
[T][] shower
[T][] read
[D][X] reading (by: Dec 12 2001)
```
## Exit

Exits the program.

Example: `bye`

## Help

Shows the format for every command.

Example: `help`
```
Command structure:
Add, {name of task}, {task type}, {start}, {end}
edit, {task no}, {done, undone or delete}
list
find, {keyword}
bye
```















