# Echo User Guide

Product screenshot
![Ui.png](Ui.png)

## Product introduction 
Echo is a task management chatbot with a GUI. 
Users can add and manage tasks such as 
Todo, Deadline and Event.
Echo is a chatbot with a bad personality. 
Echo does not believe the user is able to complete tasks efficiently.
Echo thinks that the user is just trying to avoid
all responsibilities and procastinate.
Hence Echo is not a friendly chatbot.
But Echo keeps the user on track 
and strive to complete their tasks to avoid getting scolded by Echo.

## Parameters Accepted
all commands should be not capitalized.
Follow spaces and `/`commands accurately to avoid
task format errors when adding tasks.

### List Task
Command: `list`

This command shows the current task list.

### Adding Todo
Command: `todo {todo} `

Example: `todo sleep`

This will allow a *Todo* to be added to the list of tasks.
*Todo* is a task that needs to be done. 
It has no time limit or duration.

Upon successful addition of *Todo*,
a message wil be displayed by Echo.


```
expected output: 
Echo: Ok you are never going to finish your tasks: :
[T][] sleep
Now you have 1 tasks in the list.
```

### Adding Deadline
Command: `deadline {deadline} /by YYYY-MM-DD HHMM`

Example: `deadline submit /by 2024-02-22 2359`

Allows users to add a deadline which has a time limit.
Message below will be displayed by bot.
```
expected output: 
Echo: Ok you are never going to finish your tasks: :
[D][] submit (by Feb 22 2024 23:59)
Now you have 1 tasks in the list.
```

### Adding Event
Command: `event {event} /from {YYYY-MM-DD HHMM} /to {YYYY-MM-DD HHMM}`

Example: `event meeting /from 2024-02-22 1000 /to 2024-02-22 1200`

Allows user to add an event with start and end
date and time.
Message below will be displayed if successful.
```
expected output: 
Echo: Ok you are never going to finish your tasks: :
[E][] meeting (from: Feb 22 2024 10:00 to: Feb 22 2024 12:00)
Now you have 1 tasks in the list.
```

### Mark and unmark task
Command: `mark {task index}`
`unmark {task index}`

Example: `mark 1`
`unmark 1`

This two functions allow users to mark tasks
as done or undone.
```
Mark Task expected output: 
Echo: Can't believe you finished a task haha:
[E][X] meeting (from: Feb 22 2024 10:00 to: Feb 22 2024 12:00)
```
```
Unmark Task expected output: 
Echo: I knew you couldn't do this task:
[E][] meeting (from: Feb 22 2024 10:00 to: Feb 22 2024 12:00)
```

### Delete task
Command: `delete {task index}`

Example: `delete 1`

The task with index one in the task list will
be deleted. Echo will display message of task
removed upon deletion.

### Find task
Command: `find {keyword}`

Example: `find books`

This searches through all tasks and returns
results containing the keyword.
Echo will give a list of tasks containing the keyword.

### Detect Scheduling Error
When a task is added, Echo will auto detect 
conflicts in scheduling. E.g. clashes in events

## Error Catching
If commands are wrong or command formats are wrong,
Echo will prompt user with the error and
provide the correct formats for users to use
the commands correctly.