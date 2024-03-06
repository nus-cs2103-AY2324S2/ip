# Cortana User Guide

![Cortana](./Ui.png)

## Product Description
Cortana is a personal productivity assistant that helps you stay on top of your tasks and deadlines. It is designed to be a simple and intuitive tool that helps you manage your time and tasks more effectively.

## Adding a Task
There are three types of tasks that you can add to Cortana: `todo`, `deadline`, and `event`. Each task type has a different format for adding the task.

### Adding a Todo
To add a todo, use the following format:
```
todo <description>
```

Example: `todo read book`

### Adding a Deadline
To add a deadline, use the following format:
```
deadline <description> /by <date> <time>
```

Example: `deadline return book /by 2021-09-30 1800`

### Adding an Event
To add an event, use the following format:
```
event <description> /from <start date> <start time> /to <end date> <end time>
```

Example: `event project meeting /from 2021-10-01 1400 /to 2021-10-01 1600`

Note: The date and time must be in the format `YYYY-MM-DD HHMM`.

## Listing Tasks
To list all tasks, use the following command:
```
list
```

## Marking a Task as Done
To mark a task as done, use the following command:
```
mark <task number>
```

Example: `mark 1`

## Unmarking a Task as Done
To unmark a task as done, use the following command:
```
unmark <task number>
```

Example: `unmark 1`

## Deleting a Task
To delete a task, use the following command:
```
delete <task number>
```

Example: `delete 1`

## Finding Tasks
To find tasks that contain a specific keyword, use the following command:
```
find <keyword>
```

Example: `find book`
Note: If the keyword is not contained in any task, Cortana will use fuzzy search to find tasks that contains word that are similar to the keyword.

## Exiting the Application
To exit the application, use the following command:
```
bye
```
