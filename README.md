# Buto

Buto is a personal task manager that can help you to track your tasks.

## Prerequisites

Prerequisites: JDK 11, update Intellij to the most recent version.

##Features
### 1. Add Todo Task

Add a todo task to your task list.

Syntax: `todo [task description]`

Example: todo Read a book

### 2. Add Deadline Task

Add a deadline task with a specified due date or time to your task list.

Syntax: 
- `deadline [task description] /by [due date]`
- `deadline [task description] /by [due date] [due time]`

Example: deadline Submit report /by 2024-03-01 23:59

### 3. Add Event Task

Add an event task with a specified start and end time to your task list.

Syntax: 
- `event [task description] /from [start time] /to [end time]`

Example: event Project meeting /from today /to: tomorrow

### 4. List Tasks

Display all tasks in your task list.

Syntax: `list`

### 5. Delete Task

Delete a task from your task list.

Syntax: `delete [task index]`

Example: delete 2

### 6. Tag Task

Tag a task with one or more tags.

Syntax: `tag [task index] #[tag1]`

Example: tag 1 #important

### 7. Mark Task as Done

Mark a task as done in your task list.

Syntax: `mark [task index]`

Example: mark 1

### 8. Unmark Task as Done

Mark a task as undone in your task list.

Syntax: `unmark [task index]`

Example: unmark 1

### 9. Exit Application

Exit the Buto application.

Syntax: `bye`

## Next Steps

After setting up Buto and familiarizing yourself with its features, you can start managing your tasks effectively by adding, updating, and deleting tasks as needed. Refer to the commands above for more information on how to interact with the application.
