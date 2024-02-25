# Bob User Guide

![image of product](./Ui.png)

A simple task manager to keep track of your tasks!

## Adding deadlines

Add a task with a deadline.
Use the keyword `deadline` + `(your task)` with `/by` and a datetime with the format **YYYY-MM-DD HHMM**.

Example: `deadline make breakfast /by 2022-01-01 1000`


```
Got it. I've added this task:
[D][] make breakfast (by: Jan 01 2022 10:00)
Now you have 1 tasks in the list.
```

## Adding todos

Add a todo task.
Use the keyword `todo` with `(your task)`.

Example: `todo make breakfast`


```
Got it. I've added this task:
[T][] make breakfast
Now you have 2 tasks in the list.
```

## Adding events

Add an event task.
Use the keyword `event` with `(your task)` followed by a `/from` + `(date)` and `/to` + `(date)`.

Example: `event make dinner /from Sunday 6pm /to 7pm`


```
Got it. I've added this task:
[E][] make dinner (from: Sunday 6pm to: 7pm)
Now you have 3 tasks in the list.
```

## Deleting tasks

Delete a task.
Use the keyword `delete` with `(task number)`.

Example: `delete 1`

```
Noted. I've removed this task:
[D][] make breakfast (by: Jan 01 2022 10:00)
Now you have 2 tasks in the list.
```

## Mark tasks

Mark a task as done.
Use the keyword `mark` with `(task number)`.

Example: `mark 1`

```
Nice! I've marked this task as done:
[T][X] make breakfast
```

## Unmark tasks

Unmark a task as done.
Use the keyword `unmark` with `(task number)`.

Example: `unmark 1`

```
OK, I've marked this task as not done yet:
[T][] make breakfast
```

## List tasks

List all tasks in the task list.
Use the keyword `list`.

Example: `list`

```
Here are the tasks in your list:
1.[T][] make breakfast
2.[E][] make dinner (from: Sunday 6pm to: 7pm)
```

## Find tasks

Find the task with the specified keyword.
Use the keyword `find` followed by `(keyword)`.

Example: `find dinner`

```
Here are the matching tasks in your list:
1.[E][] make dinner (from: Sunday 6pm to: 7pm)
```

## Clear tasks

Clear the task list.
Use the keyword `clear`.

Example: `clear`

```
Your tasks have been cleared.
```

## Save and exit

Save the task list.
Use the keyword `bye`.

Example: `bye`

```
Bye. Hope to see you again soon!
```