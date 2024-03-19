# Anna User Guide

![](Ui.png)

Anna! The unique chatbot that implements all the features exactly the same way as every other project in CS2103T! It is also by pure happenstance, a very basic personally task manager.

## General Command Structure

Commands are of the form `keyword [arguments]`, where `arguments` are optionally seperated by labels of the form `/argument-name`.

## Adding `todo`
Adds a to-do task to keep track of.

### Command structure
```
todo [description]
```
* description: User description of the task.

**Example**: `todo buy bread`

### Behaviour
A to-do task will be added into the chatbot's list, and displayed with the following format:

```
[T][X] buy bread
```

* [T]: Indicates the task is a `todo`.
* [X]: Indicates the task has been done. The absence of `X` indicates otherwise.

## Adding `deadlines`

Adds a deadline to keep track of.

### Command structure
```
deadline [description] /by [datetime]
```
* `description`: User description of the task.
* `datetime`: Date and time is specified in `d/M/yyyy HH:mm` format.

**Example**: `deadline return book /by 2/12/2019 18:00`

### Behaviour

A deadline task will be added into the chatbot's list, and displayed
with the following format:

```
[D][X] return book (by: Dec 02 2019 18:00)
```
* `[D]`: Indicates the task is a `deadline`.
* `[X]`: Indicates the task has been done. The absense of `X` indicates otherwise.

## Adding `event`

Adds an event to keep track of.

### Command structure

```
event [description] /from [start_datetime] /to [end_datetime]
```

* `description`: User description of the event.
* `start_datetime`: Date and time the event starts, specified in `d/M/yyyy HH:mm` format.
* `end_datetime`: Date and time the event ends, specified in `d/M/yyyy HH:mm` format.

**Example:** `event project meeting /from 2/12/2020 12:00 /to 2/12/2020 14:00`

### Behaviour

An event task will be added into the chatbot's list, and displayed with the following format:

```
[E][X] project meeting (from: Dec 02 2020 12:00 to: Dec 02 2020 14:00)
```

* `[E]`: Indicates the task is an `event`.
* `[X]`: Indicates the event has been done. The absence of `X` indicates otherwise.

## Adding `duration`

Adds a task with a specified duration.

### Command structure

```
duration [description] /duration [hours]:[minutes]:[seconds]
```

* `description`: User description of the task.
* `hours`, `minutes`, `seconds`: Duration of the task specified in `HH:mm:ss` format.

**Example:** `duration do technical interview /duration 02:10:11`

### Behaviour

A task with duration will be added into the chatbot's list, and displayed with the following format:

```
[F][X] do technical interview (duration: 02:10:11)
```

* `[F]`: Indicates the task is a `duration` task.
* `[X]`: Indicates the task has been done. The absence of `X` indicates otherwise.

## `list` Command

Displays the tasks in the task list.

### Command structure

```
list
```

**Example**: `list`

### Behaviour

The `list` command displays the tasks in the chatbot's list in the following format:

```
Here are the tasks in your list:
1.[T][ ] read book
2.[T][ ] return book
3.[T][ ] buy bread
4.[D][ ] return book (by: Dec 02 2019 18:00)
5.[E][ ] project meeting (from: Dec 02 2020 12:00 to: Dec 02 2020 14:00)
```

## `find` Command

Searches for tasks containing a specific keyword.

### Command structure

```
find [keyword-phrase]
```

* `keyword-phrase`: The term to search for in task descriptions.

**Example:** `find book`

### Behaviour

The `find` command displays the matching tasks in the chatbot's list in the following format:

```
Here are the matching tasks in your list:
1.[T][ ] read book
2.[T][ ] return book
3.[D][ ] return book (by: Dec 02 2019 18:00)
```

## `mark` Command

Marks a task as completed in the task list.

### Command structure

```
mark [task_index]
```

* `task_index`: The index of the task to mark as done.

**Example:** `mark 2`

### Behaviour

The `mark` command marks the specified task as done in the chatbot's list. If the task was a task task, it will be displayed as follows:

```
Task marked as done:
[T][X] return book
```

## `unmark` Command

Unmarks a completed task, making it active again in the task list.

### Command structure

```
unmark [task_index]
```

* `task_index`: The index of the task to unmark.

**Example:** `unmark 2`

### Behaviour

The `unmark` command unmarks the specified completed task in the chatbot's list, making it active again. If the task was a to-do task, it will be displayed as follows:

```
Task marked as not done:
[T][ ] return book
```

## `delete` Command

Deletes a task from the task list.

### Command structure

```
delete [task_index]
```

**Example**: `delete 3`

### Behaviour

The `delete` command removes the specified task from the chatbot's list. After deletion, the updated list might look like this:
```
Task deleted:
3.[T][ ] buy bread
```


## `end` Command

Terminates the chat session and ends the task management system.

### Command structure

```
end
```

**Example**: `end`

### Behaviour

The `end` command terminates the chat session.

