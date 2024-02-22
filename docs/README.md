# Felix User Guide

![Ui.png](Ui.png)

Felix is a friendly bot that can help users keep track of their activities.
Felix is designed for users familiar with a Command Line Interface (CLI).

# Note
The output messages for the features below were constructed assuming that there is no prior data and the commands were executed sequentially in the order they were introduced in this User Guide.
The actual output message may differ depending on the tasks added to the task list. 

I would like to credit my teammate Yan Jie, whose User Guide I used a reference to create this User Guide.

## Adding todos

Adds a todo task of the specified description without a start or end date to the task list.

Format: `todo {description}`

Example: `todo sleep` adds a todo with description "sleep".

If successful, you should see the following output message:

```
_______________________________________________________
Got it. I've added this task:
[T][ ] sleep
Now you have 1 tasks in the list.
_______________________________________________________
```

## Adding deadlines

Adds a deadline task of the specified description with an end date to the task list.

Format: `deadline {description} /by {end}`. `{end}` must be in the format `YYYY-MM-dd HHmm`.

Example: `deadline ip /by 2024-02-23 2359` adds a deadline with description "ip" that is due by 23 Feb 2024 2359.

If successful, you should see the following output message:

```
_______________________________________________________
Got it. I've added this task:
[D][ ] ip (by: 23 Feb 2024 2359)
Now you have 2 tasks in the list.
_______________________________________________________
```

## Adding events

Adds an event task of the specified description with a start and end date to the task list.

Format: `event {task description} /from {start} /to {end}`. `{start}` and `{end}` must be in format `YYYY-MM-dd HHmm`.

Example: `event dinner /from 2024-02-02 1700 /to 2024-02-02 1800` adds an event with description "dinner" from 2 Feb 2024 1700 to 2 Feb 2024 1800.

If successful, you should see the following output message:

```
_______________________________________________________
Got it. I've added this task:
[E][ ] dinner (from: 2 Feb 2024 1700 to: 2 Feb 2024 1800)
Now you have 3 tasks in the list.
_______________________________________________________
```
## Listing tasks

Lists all the tasks in the task list.

Format: `list`

If successful, you should see the following output message:

```
_______________________________________________________
1. [T][ ] sleep
2. [D][ ] ip (by: 23 Feb 2024 2359)
3. [E][ ] dinner (from: 2 Feb 2024 1700 to: 2 Feb 2024 1800)
_______________________________________________________
```

## Marking tasks

Marks a task that is done with an "X".

Format: `mark {INDEX}`

Example: `mark 3` would mark the third task in the list as done.

If successful, you should see the following output message:

```
_______________________________________________________
Nice! I have marked this task as done:
[E][X] dinner (from: 2 Feb 2024 1700 to: 2 Feb 2024 1800)
_______________________________________________________
```

## Unmarking tasks

Marks a task (presumably as completed) with the "X" removed.

Format: `unmark INDEX`

Example: `unmark 3` would unmark the third task in the list.

If successful, you should see the following output message:

```
_______________________________________________________
OK, I've marked this task as not done yet:
[E][ ] dinner (from: 2 Feb 2024 1700 to: 2 Feb 2024 1800)
_______________________________________________________
```

## Finding tasks

Searches for tasks whose description contains the target string entered as a substring.

Format: `find {target}`, where `{target}` is the string used to search for tasks.

Example: `find e` would search for tasks that contain the string `e`.

If successful, you should see the following output message:

```
_______________________________________________________
Here are the matching tasks in your list:
1. [T][ ] sleep
2. [E][ ] dinner (from: 2 Feb 2024 1700 to: 2 Feb 2024 1800)
_______________________________________________________
```

## Deleting tasks

Deletes a task from the task list.

Format: `delete INDEX`

Example: `delete 1` would delete the first task in the list.

If successful, you should see the following output message:

```
_______________________________________________________
Noted. I've removed this task:
[T][ ] sleep
Now you have 2 tasks in the list.
_______________________________________________________
```

## Updating tasks

Updates tasks from the task list.

Format: </br>
`update INDEX {description}` for updating todos at INDEX in task list

`update INDEX {description} /by {end}` for updating deadlines at INDEX in task list

`update INDEX {description} /from {start} /to {end}` for updating events at INDEX in task list 

Example: `update 2 sports /from 2024-02-23 1700 /to 2024-02-23 1800` will update the description, start time and end time of the event at index 2 in the task list to "sports", 23 Feb 2024 1700, and 23 Feb 2024 1800.

If successful, you should see the following output message:

```
_______________________________________________________
Task was successfully updated:
Old task: [E][ ] dinner (from: 2 Feb 2024 1700 to: 2 Feb 2024 1800)
_______________________________________________________
New task: [E][ ] sports (from: 23 Feb 2024 1700 to: 23 Feb 2024 1800)
_______________________________________________________
```

## Exiting the application

Format: `bye`

You should see the following output message:

```
_______________________________________________________
Bye. Hope to see you again soon!
_______________________________________________________
```