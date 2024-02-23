# Wis User Guide

![UI Image](https://github.com/0-yibai/ip/docs/Ui.png)

Wis is a Command-Line-based task manager. You can use it to manage tasks todo, deadlines and events.

Tasks added will be saved automatically even after closing the app. You
will not lose data by restarting out app.

To use Wis chatbot, download `Wis.jar` from our lastest github release,
and type the following line in terminal at the directory containing
`wis.jar` to start the app:
```
java -jar wis.jar
```


# Supported Commands

## Adding todos

You can add a todo-type task to your task list.

Execute by keying in: `todo <task_description>`

Example: `todo buy some fruits`

```
    Got it. I've added this task:

       [T][ ] buy some fruits
     Now you have 1 tasks in the list.
```

## Adding deadlines

You can add a deadline-type task to your task list.

Execute by keying in: `deadline <task_description> /by <date_and_time>`
`<date_and_time>` needs to be in this standard format: `YYYY-MM-DD HH:MM`

Example: `deadline CS2103T quiz /by 2024-02-27 23:59`

```
    Got it. I've added this task:

       [D][ ] CS2103T quiz (by: FEB 27 2024, 23:59)
     Now you have 2 tasks in the list.
```

## Adding events

You can add an event-type task to your task list.

Execute by keying in: `event <task_description> /from <date_and_time> /to <date_and_time>`
`<date_and_time>` needs to be in this standard format: `YYYY-MM-DD HH:MM`

Example: `event hackathon /from 2024-02-28 09:00 /to 2024-03-01 16:00`

```
    Got it. I've added this task:

       [E][ ] hackathon (from: FEB 28 2024, 09:00 to: MAR 1 2024, 16:00)
     Now you have 3 tasks in the list.
```

## Listing tasks

You can list all tasks added.

Each task is displayed in the following form:
`<index>.[task_type][done?]<task_description> <optional_and_time>`

Execute by keying in: `list`

```
    Here are the tasks in your list:
     1.[T][ ] buy some fruits
     2.[T][ ] buy some fruits
     3.[D][ ] CS2103T quiz (by: FEB 27 2024, 23:59)
```

## Deleting tasks

You can delete a task specified by an index in the list.

Execute by keying in: `delete <task_index>`

Example: `delete 2`

```
     Noted. I've removed this task:
       [T][ ] buy some fruits
     Now you have 2 tasks in the list.
```

## Marking tasks as done

You can mark a task as done.

Execute by keying in: `mark <task_index>`

Example: `mark 2`

```
     Nice! I've marked this task as done:
       [D][X] CS2103T quiz (by: FEB 27 2024, 23:59)

```

## Unmarking tasks

You can unmark a task.

Execute by keying in: `unmark <task_index>`

Example: `unmark 2`

```
     OK, I've marked this task as not done yet:
       [D][ ] CS2103T quiz (by: FEB 27 2024, 23:59)
```

## Search for tasks by keyword

You can search for tasks matching some specified keyword
or part of a keyword.

Execute by keying in: `find <pattern>`

Example: `find some`

```
     Here are the matching tasks in your list:
       1. [T][ ] buy some fruits
```

## Undo the last action

You can undo the last storage-changing action. Non-storage-changing
actions are simply ignored. Our app will trace back to find the most
recent storage-changing action.

The following commands are considered storage-changing actions:
`todo`, `deadline`, `event`, `mark`, `unmark`, `delete`.

Note that `undo` itself is not considered a storage-changing action.

Example: `undo`

```
     Undo unmark successful.
```


