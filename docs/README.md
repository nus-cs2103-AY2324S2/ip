# MicroManager User Guide

![](Ui.png)

MicroManager is a task tracking application with a command line interface.

It is quick and simple to use.

## Adding a todo task: `todo`

This command adds a todo task to the task list, which comprises only a description.

Format: `todo DESCRIPTION`

Example: `todo Write report for meeting`

MicroManager will confirm the addition of the task and display the total number of tasks in the list.

```
Got it. I've added this task:
  [T][ ] Write report for meeting
Now you have 5 tasks in the list.
```

## Adding a deadline: `deadline`

This command adds a task with a deadline to the task list, including a description and a due time.

Format: `deadline DESCRIPTION /by DUE-TIME`

> ### Important
>
> Make sure the due time is in the format `YYYY-MM-DD`. For example, `2024-02-29` is acceptable. Time formats not matching this pattern will be rejected.

Example: `deadline Complete project proposal /by 2024-03-05`

MicroManager confirms the addition of the deadline task along with the total number of tasks.

```
Got it. I've added this task:
  [D][ ] Complete project proposal (by: Mar 5 2024)
Now you have 6 tasks in the list.
```

## Adding an event: `event`

This command adds an event to the task list, comprising a description, start time, and end time. The event can be marked as tentative.

Format: `event DESCRIPTION /from START-TIME /to END-TIME`

Examples:

-   `event Team meeting /from 2024-03-03 /to 2024-03-03`

MicroManager confirms the addition of the event along with the total number of tasks.

```
Got it. I've added this task:
  [E][ ] Team meeting (from: Mar 3 2024 to: Mar 3 
2024)
Now you have 7 tasks in the list.
```

## Listing all tasks: `list`

This command lists all tasks currently in the task list.

Format: `list`

MicroManager displays the current list of tasks along with their indices.

```
Here are the tasks in your list:
1.[T][ ] Write report for meeting
2.[D][ ] Complete project proposal (by: Mar 5 2024)
3.[E][ ] Team meeting (from: Mar 3 2024 to: Mar 3 2024)
```

## Marking a task as done: `mark`

This command marks a task as completed in the task list. The index of the task needs to be specified.

Format: `mark INDEX`

Example: `mark 1`

MicroManager confirms the task as done.

```
Nice! I've marked this task as done:

  [T][X] Write report for meeting
```

## Unmarking a task as not done yet: `unmark`

This command unmarks a previously marked task, reverting its status to incomplete.

Format: `unmark INDEX`

Example: `unmark 1`

MicroManager confirms the task as not done.

```
OK, I've unmarked this task as done:

  [T][ ] Write report for meeting
```

## Deleting a task: `delete`

This command deletes a task from the task list based on the specified index.

Format: `delete INDEX`

Example: `delete 1`

MicroManager confirms the deletion of the task and displays the total number of remaining tasks.

```
Noted. I've removed this task:
  [T][ ] Write report for meeting
Now you have 2 tasks in the list.
```

## Finding all tasks with a keyword: `find`

This command searches for tasks containing a specific keyword in their description.

Format: `find KEYWORD`

> ### Note
>
> The keyword provided does not have to be case sensitive. For example, the query `find project` is equivalent to the query `find Project`.

Example: `find project`

MicroManager displays the tasks that match the keyword.

```
Here are the matching tasks in your list:
1.[D][ ] Complete project proposal (by: Mar 5 2024)
```

## Saying bye to the MicroManager: `bye`

This command closes the interaction with MicroManager.

Format: `bye`

The application is closed.
