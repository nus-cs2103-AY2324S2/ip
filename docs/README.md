# Henry User Guide

Henry is a desktop chatbot app for managing tasks, optimized for use via Command Line Interface (CLI).

![](./Ui.png)

Here's what Henry can do:
- Adding a `todo` task that has only description.
- Adding a `deadline` task that has description, and a deadline datetime (`by`).
- Adding an `event` task that has description, a starting datetime (`from`) and an ending datetime (`to`).
- Mark or unmark a task as done or undone.
- Delete a task from the list.
- Update a task's `description`, deadline (`by`), starting datetime (`from`) and/or ending datetime (`to`).

## Adding a Todo Task: `todo {description}`

You can add a `Todo` task into the list. A todo task only has a `description`

Example: `todo buy bread`

- This adds a todo item into the list with the description of "buy bread".

Expected outcome:
```
Added this task
[T][] buy bread
Now you have 1 tasks in the list :(
```

## Adding a Deadline Task: `deadline {description} /by {datetime}`

You can add a `Deadline` task into the list.

Example: `deadline return book /by 29/2/2024 15:00`

- This adds a deadline with description "return book" on 29 February 2024 15:00.

Expected outcome:
```
Added this task
[D][] return book (by: Feb 29 2024 15:00)
Now you have 1 tasks in the list :(
```

## Adding an Event Task: `event {description} /from {datetime} /to {datetime}`

You can add an `Event` task into the list.

Example: `event project meeting /from 29/2/2024 16:00 /to 29/2/2024 17:00`

- This adds an event with description "project meeting" from 29 February 2024 16:00 to 29 February 17:00.

Expected outcome:
```
Added this task
[E][] project meeting (from: Feb 29 2024 16:00 /to Feb 29 2024 17:00)
Now you have 1 tasks in the list :(
```

## Listing tasks: `list`

You can list all the tasks that were added.

Example: `list`
- This will list all the tasks that are in the list.

Expected outcome:
```
Here is a list of tasks
1. [T][X] item1
2. [T][] item2
```

## Marking a Task: `mark {index}`

You can mark a task as done after completing it.

Example: `mark 1`
- This marks the task with index of 1 as done.

Expected outcome:
```
This task has been marked as done. XD
[T][X] task
```

## Unmarking a Task: `unmark {index}`

You can mark a task as done after completing it.

Example: unmark 1
- This marks the task with index of 1 as done.

Expected outcome:
```
This task has been marked as undone. :(
[T][] task
```

## Deleting a Task: `delete {index}`

You can delete an existing task from the list.

Example: `delete 1`
- This deletes the task of index 1 from the list.

Expected outcome:
```
This task is deleted :)
[T][] buy bread
```

## Updating a Task: `update {index} /d {description} /by {datetime} /to {datetime} /from {datetime}`

You can update a task that is already added into the list. Other than the index, you can only specify the information that you want to change.

Example 1: `update 1 /d new description`
- This updates the description of task of index 1 into "new description" 

Expected outcome:
```
Edited task: [T][] new description
```

Example 2: `update 1 /by 08/02/2024 1400`
- This updates the deadline of task of index 1 into "new description"

Expected outcome:
```
Edited task: [D][] return book (by Feb 08 2024 14:00)
```

## Exit Henry: `bye`

You can exit Henry and your tasks will be saved automatically.

Example: `bye`

Expected outcome: the program exits.