# Derek 🦫 User Guide

![](Ui.png)

> Productivity.

Free your mind from distractions with this simple-to-use todolist.

**Features**:
2. List tasks with `list`
3. Mark (unmark) tasks as completed (uncompleted) with `mark` and `unmark`
4. Delete tasks with `delete`
5. Find tasks by keyword matcing with `find`
6. Persistent file storage `tasklist.txt`

**Installation**:
- Download the jar file [here](https://github.com/minreiseah/ip/releases)
- Run it in your terminal of choice:
```bash
java -jar derek.jar
```

## Create New Tasks

Create tasks with `todo`, `event`, or `deadline`.

Note that duplicated tasks cannot be added.

### ToDo

A simple task.

```bash
todo {description}
```

### Deadline

A task with a due date.

```bash
deadline {description} /by {datetime}
```

### Event

A task with a start and end date.

```bash
event {description} /from {datetime} /to {datetime}
```

## List All Tasks: `list`

Displays all tasks.

## Mark/Unmark Task: `mark`/`unmark`

Mark (unmark) a task as done (undone).

```bash
mark {task-index}
```

## Delete Task: `delete`

Delete a task.

```bash
delete {task-index}
```

## Find Task(s): `find`

Searches for tasks that contain keyword(s).

Searching with multiple keywords yields tasks that match *any* keyword.

```bash
find {keyword1} {keyword2}
```
