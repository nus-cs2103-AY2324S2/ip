# User Guide for Yoda Chatbot

Yoda Chatbot is a task management assistant that helps you keep track of various tasks including todos, deadlines, and events.

## Features

### Adding a Task
You can add three types of tasks: todo, deadline, and event.

#### `todo` - Adds a todo task.
A todo task only contains a description.

Example of usage:
`todo read book`

Expected outcome:
Hmm, added this task, I have:
[T][ ] read book
Tasks in the list, now you have 1, hmm.

#### `deadline` - Adds a task with a deadline.
A deadline task contains a description and a date/time by which the task should be completed.

Example of usage:
`deadline return book /by Sunday`

Expected outcome:
Hmm, added this task, I have:
[D][ ] return book (by: Sunday)
Tasks in the list, now you have 2, hmm.

#### `event` - Adds an event task.
An event task contains a description and a start and end time.

Example of usage:
`event project meeting /from Monday 2pm /to 4pm`

Expected outcome:
Hmm, added this task, I have:
[E][ ] project meeting (from: Monday 2pm to: 4pm)
Tasks in the list, now you have 3, hmm.

### Marking a Task as Done
You can mark a task as completed.

#### `mark` - Marks a task as done.

Example of usage:
`mark 1`

Expected outcome:
Done, this task is:
[T][X] read book

### Marking a Task as Unone
You can unmark a task as completed.

#### `unmark` - Marks a task as done.

Example of usage:
`unmark 1`

Expected outcome:
Undone, this task remains:
[T][ ] read book

## Usage

### `list` - Lists all tasks

Shows a list of all tasks in the task manager.

Example of usage:
`list`

Expected outcome:
Tasks in your list, here they are:

1. [T][X] read book
2. [D][ ] return book (by: Sunday)
3. [E][ ] project meeting (from: Monday 2pm to: 4pm)

### Deleting a Task
You can delete a task from your list.

#### `delete` - Deletes a task.

Example of usage:
`delete 1`

Expected outcome:
Removed, this task has been:
[T][X] read book
Tasks in the list, now you have 2, hmm.

### Exiting the Chatbot
You can exit the chatbot using the `bye` command.

#### `bye` - Exits the application.

Example of usage:
`bye`

Expected outcome:
Farewell. See you again, I hope!
