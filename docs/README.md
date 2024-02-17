Chatbot name: NyanTasks

# User Guide

## Features 

### Feature-Adding Tasks

NyanTasks supports adding three types of tasks: todos, deadlines, and events.

Todos are tasks without any date/time attached.
Deadlines are tasks that need to be done before a specific date/time.
Events are tasks that occur at a specific date/time.

Finding Tasks
You can search for tasks by keywords. Duke will show you all tasks that contain the keyword in their description.

Marking Tasks as Done
Tasks can be marked as done. This helps you keep track of tasks that you have completed.

Listing All Tasks
Duke can list all your tasks at once, allowing you to see everything you have planned at a glance.

## Usage

`todo` - Add a Todo Task
Adds a todo task to your list.

Example of usage:

`todo read book`

Expected outcome:

A new todo task "read book" is added to your task list.

Expected output:
```
Got it. I've added this task:
[T][ ] read book
Now you have X tasks in the list.
```

`deadline` - Add a Deadline Task
Adds a deadline task to your list. You need to specify the task description and its deadline.

Example of usage:

`deadline return book /by 2022-12-01 1800`

Expected outcome:

A new deadline task "return book" with a deadline is added to your task list.

Expected output:
```
Got it. I've added this task:
[D][ ] return book (by: Dec 1 2022, 18:00)
Now you have X tasks in the list.
```

`event` - Add an Event Task
Adds an event task to your list. You need to specify the task description and the event date/time.

Example of usage:

`event book club meeting /from Mon 2pm /to 4pm`

Expected outcome:

A new event task "book club meeting" with its date/time is added to your task list.

Expected output:
```
Got it. I've added this task:
[E][ ] book club meeting (from: Mon: 2pm to: 4pm)
Now you have X tasks in the list.
```

`find` - Find Tasks by Keyword
Finds and lists all tasks that contain the given keyword in their description.

Example of usage:

`find book`

Expected outcome:

A list of all tasks containing the keyword "book".

Expected output:
```
Here are the matching tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Dec 1 2022, 18:00)
```

`list` - List All Tasks
Lists all tasks in your task list.

Example of usage:

`list`

Expected outcome:

A list of all your tasks is displayed.

Expected output:
```
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Dec 1 2022, 18:00)
3.[E][ ] book club meeting (at: Dec 5 2022, 14:00)
```
