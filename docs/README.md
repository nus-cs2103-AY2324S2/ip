# Nicky User Guide

![Ui](Ui.png)

Welcome to Nicky, your personal task management assistant designed to help you keep track of your todos, deadlines, and events with ease. Nicky is intuitive and easy to use, allowing you to add, list, find, and mark tasks with simple commands.

## Listing Tasks

View all your tasks. For deadlines, they are listed in chronological order to help you prioritize.

### List All Tasks
Type `list` to see all your tasks.

## Adding Deadlines

Deadlines are tasks that need to be completed by a specific date and time.

### Action
To add a deadline, specify the task description followed by `/by` and the deadline date and time.

### Example
```
deadline return library books /by 2023-01-15 18:00
```

### Expected Outcome
Nicky will add the deadline to your task list and confirm it has been saved:
```
Got it. I've added this task:
[D][ ] return library books (by: Jan 15 2023 18:00)
Now you have X tasks in the list.
```

## Adding Events

Events are activities that occur within a specific start and end time.

### Action
To add an event, provide the event description followed by `/from` for the start date and time, and `/to` for the end date and time.

### Example
```
event project meeting /from 2023-01-15 14:00 /to 2023-01-15 16:00
```

### Expected Outcome
Nicky will add the event to your task list and confirm it:
```
Got it. I've added this task:
[E][ ] project meeting (from: Jan 15 2023, 14:00 to: Jan 15 2023, 16:00)
Now you have X tasks in the list.
```

## Adding Todos

Todos are simple tasks without a specific deadline or time.

### Action
To add a todo, type `todo` followed by the task description.

### Example
```
todo read a book
```

### Expected Outcome
Nicky confirms the addition of the todo to your task list:
```
Got it. I've added this task:
[T][ ] read a book
Now you have X tasks in the list.
```

## Finding Tasks

Search for tasks by keyword.

### Action
Type `find` followed by the keyword(s).

### Example
```
find book
```

### Expected Outcome
Nicky displays tasks that match the keyword:
```
Here are the matching tasks in your list:
1. [D][ ] return library books (by: Jan 15 2023 18:00)
2. [T][ ] read a book
```

## Mark and Unmark Tasks

Track your progress by marking tasks as done or unmarking them.

### Marking a Task
To mark a task as done, type `mark` followed by the task number.

### Example
```
mark 1
```

### Expected Outcome
Nicky marks the specified task as done: 
```
Nice! I've marked this task as done:
[T][X] read a book
```

### Unmarking a Task
To unmark a task, type `unmark` followed by the task number.

### Example
```
unmark 1
```

### Expected Outcome
Nicky unmarks the specified task:
```
OK, I've marked this task as not done yet:
[T][ ] read a book
```

## Exiting the Application
Nicky will save your tasks to a file and exit when you type `bye`.

### Action
Type `bye` to exit the application.

### Example
```
bye
```

### Expected Outcome
Nicky saves your tasks and exits:
```
Bye. Hope to see you again soon!
```