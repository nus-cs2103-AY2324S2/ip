# Ursa Chatbot User Guide

Welcome to the Ursa Chatbot User Guide!

## Features

### Listing All Tasks
- **Command:** `list`
- Lists all tasks you have added to Ursa.

### Adding a Todo Task
- **Command:** `todo TASK_DESCRIPTION`
- Adds a todo task to your list.
- Example: `todo read book`

### Adding a Deadline Task
- **Command:** `deadline TASK_DESCRIPTION /by YYYY-MM-DD`
- Adds a deadline task with a specific due date.
- Example: `deadline return book /by 2022-12-31`

### Adding an Event Task
- **Command:** `event TASK_DESCRIPTION /from YYYY-MM-DD /to YYYY-MM-DD`
- Adds an event task with a start and end date.
- Example: `event project meeting /from 2022-12-01 /to 2022-12-02`

### Marking a Task as Done
- **Command:** `mark TASK_NUMBER`
- Marks a task as completed.
- Example: `mark 2` (Marks the second task in the list as done.)

### Unmarking a Task
- **Command:** `unmark TASK_NUMBER`
- Marks a task as not done.
- Example: `unmark 2` (Unmarks the second task in the list.)

### Deleting a Task
- **Command:** `delete TASK_NUMBER`
- Deletes a task from your list.
- Example: `delete 3` (Deletes the third task in the list.)

### Finding Tasks by Keyword
- **Command:** `find KEYWORD`
- Finds tasks that contain the given keyword.
- Example: `find book` (Finds all tasks that contain the word "book".)

### Exiting the Chatbot
- **Command:** `bye`
- Exits the chatbot application.

We hope this guide helps you get started with using Ursa Chatbot to manage your tasks more effectively!
