# Yoda Chatbot User Guide

Welcome to the Yoda Chatbot, your personal assistant for managing tasks with the wisdom of Yoda himself. This guide will help you navigate through the application and utilize its features to organize your to-do list effectively.

## Starting Up
- Download A-Jar-3 from the 'Releases' section.
- Launch the Yoda Chatbot application.
- A greeting from Yoda will welcome you to the application.
- You are now ready to start managing your tasks.

## Features

## Adding Tasks
- To add a task, type in the task description and send it.
- For Todo tasks: Simply type the task and send it.
- For Deadline tasks: Type the task followed by 'by:' and the due date/time.
- For Event tasks: Type the event description followed by 'from:' and 'to:' with start and end date/time.

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

## Marking Tasks as Done
- To mark a task as done, type 'mark' followed by the task number.

#### `mark` - Marks a task as done.

Example of usage:
`mark 1`

Expected outcome:
Done, this task is:
[T][X] read book

## Marking Tasks as Undone
- To mark a task as not done, type 'unmark' followed by the task number.

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

## Deleting Tasks
- To delete a task, type 'delete' followed by the task number.

#### `delete` - Deletes a task.

Example of usage:
`delete 1`

Expected outcome:
Removed, this task has been:
[T][X] read book
Tasks in the list, now you have 2, hmm.

## Saving Your List
- To save your tasks, type 'save'. Your tasks will be saved for the next time you use the chatbot.

#### `save` - Saves the current task list.

Example of usage:
`save`

Expected outcome:
Saved, your tasks have been.

## Updating Tasks
- To update a task, type 'update' followed by the task number. Yoda will prompt you for the details to update, depending on the type of task.
    - For Todo: You'll be asked for a new description.
    - For Deadline: You can update the description or the due date/time.
    - For Event: You have the option to update the description, the start date/time, or the end date/time.

#### `update` - Updates a task.

Example of usage:
`update 1` (for a Todo task)

Expected outcome:
Yoda: Enter 'description' you must.
User: description
Yoda: New description, enter you must.
User: [new description]
Yoda: Updated, the task description is.


## Exiting the Chatbot
You can exit the chatbot using the `bye` command.

#### `bye` - Exits the application.

Example of usage:
`bye`

Expected outcome:
Farewell. See you again, I hope!
