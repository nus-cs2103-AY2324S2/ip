# Application User Guide

Welcome to our application! This guide will walk you through how to use the various features and commands available to manage your tasks effectively.

## Getting Started

To begin, you'll need to input a command in the format `action [additional information]`. The application supports a variety of actions to help you manage your tasks, such as adding new tasks, marking tasks as done or not done, deleting tasks, and more.

## Commands Overview

Here's a brief overview of each command and how to use it:

### 1. `BYE`
- Closes the application.
- Example: `bye`

### 2. `LIST`
- Displays all tasks in your task list.
- Example: `list`

### 3. `DONE [task number]`
- Marks a task as done.
- The task number is the index of the task in your list.
- Example: `done 2`

### 4. `UNDONE [task number]`
- Marks a task as not done.
- The task number is the index of the task in your list.
- Example: `undone 3`

### 5. `DELETE [task number]`
- Deletes a task from your task list.
- The task number is the index of the task in your list.
- Example: `delete 1`

### 6. `FIND [keyword]`
- Finds and displays tasks that contain the keyword.
- Example: `find book`

### 7. `TODO [task description]`
- Adds a ToDo task to your task list.
- Example: `todo Read a book`

### 8. `DEADLINE [task description] /by [deadline]`
- Adds a Deadline task to your task list.
- The deadline format is `YYYY-MM-DD`.
- Example: `deadline submitReport /by 2023-03-01`

### 9. `EVENT [task description] /from [start date] /to [end date]`
- Adds an Event task to your task list.
- The event time format can be any descriptive text.
- The date format is `YYYY-MM-DD`.
- Example: `event TeamMeeting /from 2001-02-02 /to 2001-03-03`

## Error Handling

The application includes error handling for invalid input or commands. Here are some common errors you might encounter:

- **TaskList should not be null**: This error should not occur during normal operation. If you see this message, please contact support.
- **Please enter a valid task number**: Ensure you're entering a valid number for commands that require a task number.
- **Task number does not exist**: When marking a task as done/undone or deleting a task, make sure the task number exists in your task list.
- **Command info missing**: For `TODO`, `DEADLINE`, and `EVENT` commands, make sure you include the task description (and deadline/event time when necessary).

## Saving Tasks

Tasks are automatically saved to a file after any change, ensuring that your task list is up-to-date.

Thank you for using our application! If you have any questions or encounter any issues, please don't hesitate to contact support.
