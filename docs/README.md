# GhBot User Guide

![Product screenshot](Ui.png)

Hi! **GhBot** is an efficient chat bot that remembers your tasks for you!

GhBot has the following capabilities:

## Instruction List
> #### Add Task
> - `todo [task name]`
> - `deadline [task name] /by [date&time]`
> - `event [task name] /from [date&time] /to [date&time]`
> #### List Task(s)
> - `list`
> #### Mark Task
> - `mark [index of the task]`
> #### Delete Task
> - `delete [index of the task]`
> #### Unmark Task
> - `unmark [index of the task]`
> #### Find Task
> - `find [partial word or keyword]`
> #### Exit GhBot
> - `bye`

# Refer below for the details of each instruction
## Adding new task
There are three different ways you can add a task.

### Adding a Todo task
Instruction: `todo [task name]`

Example: `todo gym`

Expected outcome:
```
Got it. I've added this task:
[T][] gym
Now you have 1 tasks in the list.
```

### Adding a Deadline task
Instruction: `deadline [task name] /by [date&time]`

> The `[date&time]` format is `yyyy-MM-dd HHmm`

Example: `deadline CS2103 IP Project /by 2024-02-23 2359`

Expected outcome:
```
Got it. I've added this task:
[D][] CS2103 IP Project (by: 23 Feb 2024 1159PM)
Now you have 2 tasks in the list.
```

### Adding an Event task
Instruction: `event [task name] /from [date&time] /to [date&time]`

> The `[date&time]` format is `yyyy-MM-dd HHmm`

> The start time(from) should be earlier than the end time(to).

Example: `event gymming gathering /from 2024-02-24 0900 /to 2024-02-24 1100`

Expected outcome:
```
Got it. I've added this task:
[E][] gymming gathering (from: 24 Feb 2024 0900AM to: 24 Feb 2024 1100AM)
Now you have 3 tasks in the list.
```

## Listing the tasks
This instruction will list all the task created by the user.

Example: `list`

Expected output:

```
1.[T][] gym
2.[D][] CS2103 IP Project (by: 23 Feb 2024 1159PM)
3.[E][] gymming gathering (from: 24 Feb 2024 0900AM to: 24 Feb 2024 1100AM)
```

## Marking a completed task
Instruction: `mark [index of the task]`

Example: `mark 1`

Expected output:

```
Nice! I've marked this task as done:
[T][X] gym
```

## Deleting a task
Instruction: `delete [index of the task]`

Example: `delete 2`

Expected output:

```
Noted. I've removed this task:
[D][] CS2103 IP Project (by: 23 Feb 2024 1159PM)
Now you have 2 tasks in the list.
```

## Unmarking an uncompleted task
Instruction: `unmark [index of the task]`

Example: `ummark 1`

Expected output:

```
OK, I've marked this task as not done yet:
[T][] gym
```

## Finding task
Instruction: `find [partial word or keyword]`

Example: `find gym`

Expected output:

```
Here are the list of tasks that matches with your description
1.[T][] gym
2.[E][] gymming gathering (from: 24 Feb 2024 0900AM to: 24 Feb 2024 1100AM)
```

## Exiting GhBot
Instruction: `bye`

> GhBot will exit after 1.5 seconds to show goodbye message!

Example: `bye`

Expected output:

```
Bye. Hope to see you again soon!
```