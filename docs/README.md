# Panda User Guide

![Product Screenshot](Ui.png)

Panda is a chat-based task manager. You can add tasks, keep track of their status, and even tag them to a category. Try it out now for free!

## Feature: Adding tasks

There are 3 types of task: todo, deadline, and event.

To add, type the type of task you want to add, followed by the name, then the type-specific arguments

Examples:

Adding todo task `todo read book`

Adding deadline task `deadline read book /by FEB 23 2024`

Adding event task `deadline read book /from FEB 23 2024 /to MAR 1 2024`

Expected output:

```
Got it. I've added this task.
  [T][ ] read books
Now you have 3 tasks in the list.
```

## Feature: Marking tasks

To mark the task as done / not done, simply type `mark` or `unmark` followed by the task number (it's order in the task list).

Examples:

Mark a task `mark 1`

Unmark a task `unmark 1`

Expected output:

```
Nice! I've marked this task as done:
  [T][X] read books
```

## Feature: Tagging tasks

To tag/untag the task as a specifc tag, simply type `tag` or `untag` followed by the task number (it's order in the task list), then a tag of your choice. You can also filter the list for specific tags by using `filter` command.

Examples:

Tag a task as "sports" `tag 1 sports`

Untag "sports" from a task `untag 1 sports`

Filter your list for tasks with tag "sports" `filter sports`

Expected output:

```
Nice! I've tagged this task as [sports]:
  [T][X] read books
```

### Command List

To see the full command list, type `help` in the program.
