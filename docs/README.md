# Ran

![Ui.png](Ui.png)

Ran is a bot that can help users keep track of their activities.
Ran is designed for users familiar with a Command Line Interface (CLI).

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
I've added this task to the list:
[T][ ] sleep
There is now 1 task in the list.
_______________________________________________________
```

## Adding deadlines

Adds a deadline task of the specified description with an end date to the task list.

Format: `deadline {description} /by {end}`. `{end}` must be in the format `YYYY-MM-dd`.

Example: `deadline ip /by 2024-02-23` adds a deadline with description "ip" that is due by 23 Feb 2024.

If successful, you should see the following output message:

```
_______________________________________________________
I've added this task to the list:
[D][ ] ip (by: 23 Feb 2024)
There are now 2 tasks in the list.
_______________________________________________________
```

## Adding events

Adds an event task of the specified description with a start and end date to the task list.

Format: `event {task description} /from {start} /to {end}`. `{start}` and `{end}` must be in format `YYYY-MM-dd`.

Example: `event dinner /from 2024-02-02 /to 2024-02-02` adds an event with description "dinner" from 2 Feb 2024 to 2 Feb 2024.

If successful, you should see the following output message:

```
_______________________________________________________
I've added this task to the list:
[E][ ] dinner (from: 2 Feb 2024 to: 2 Feb 2024)
There are now 3 tasks in the list.
_______________________________________________________
```
## Listing tasks

Lists all the tasks in the task list.

Format: `list`

If successful, you should see the following output message:

```
_______________________________________________________
1. [T][ ] sleep
2. [D][ ] ip (by: 23 Feb 2024)
3. [E][ ] dinner (from: 2 Feb 2024 1700 to: 2 Feb 2024)
_______________________________________________________
```

## Marking tasks

Marks tasks that are done with an "X".

Format: `mark {INDEX}...`

Example: `mark 2 3` would mark the second and third task in the list as done.

If successful, you should see the following output message:

```
_______________________________________________________
Alright. I have marked this task as complete:
[D][X] ip (by: 23 Feb 2024)
Alright. I have marked this task as complete:
[E][X] dinner (from: 2 Feb 2024 to: 2 Feb 2024)
_______________________________________________________
```

## Unmarking tasks

Marks tasks (presumably completed) with the "X" removed.

Format: `unmark {INDEX}...`

Example: `unmark 2 3` would unmark the third task in the list.

If successful, you should see the following output message:

```
_______________________________________________________
If that's the case, I'll set that task as incomplete:
[D][ ] ip (by: 23 Feb 2024)
If that's the case, I'll set that task as incomplete:
[E][ ] dinner (from: 2 Feb 2024 to: 2 Feb 2024)
_______________________________________________________
```

## Finding tasks

Searches for tasks whose description contains the target string entered as a substring.

Format: `find {target}`, where `{target}` is the string used to search for tasks.

Example: `find e` would search for tasks that contain the string `e`.

If successful, you should see the following output message:

```
_______________________________________________________
Found 2 matches:
1. [T][ ] sleep
2. [E][ ] dinner (from: 2 Feb 2024 to: 2 Feb 2024)
_______________________________________________________
```

## Deleting tasks

Deletes tasks from the task list.

Format: `delete {INDEX}...`

Example: `delete 1` would delete the first task in the list.

If successful, you should see the following output message:

```
_______________________________________________________
I've deleted this task:
[T][ ] sleep
There are now 2 tasks in the list.
_______________________________________________________
```

## Exiting the application

Format: `bye`

You should see the following output message:

```
_______________________________________________________
Goodbye, please return soon.
_______________________________________________________
```