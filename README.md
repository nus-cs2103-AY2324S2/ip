# RAGDOLL User Guide

![RAGDOLL UI]

RAGDOLL is an interactive task management chatbot designed to help you keep track of your to-dos, deadlines, and events. This user-friendly assistant can add, delete, mark, and search for tasks in your personalized task list. Let's dive into how to make the most out of RAGDOLL for managing your tasks efficiently.

## Getting Started
Upon launching RAGDOLL, the bot is ready to assist with managing your tasks right away. Use simple commands to interact with RAGDOLL and manage your task list.

## Adding Tasks
RAGDOLL supports three types of tasks: Todo, Deadline, and Event. Here's how you can add each type:

### Adding a Todo
To add a todo task, simply type `todo` followed by the task description.

Example: `todo read a book`
```
Master, I've added this task:
  [T][ ] #default read a book
Now you have 1 task in the list, Master!
```

### Adding Deadlines
To add a deadline, type `deadline` followed by the task description and the due date in the format `yyyy-mm-dd`.

Example: `deadline return library books /by 2023-10-05`
```
Master, I've added this task:
  [D][ ] #default return library books (by: Oct 5 2023)
Now you have 2 tasks in the list, Master!
```

### Adding Events
For events, type `event` followed by the event description, the start date, and the end date in the format `yyyy-mm-dd`.

Example: `event project meeting /from 2023-10-10 /to 2023-10-12`
```
Master, I've added this task:
  [E][ ] #default project meeting (from: Oct 10 2023 to: Oct 12 2023)
Now you have 3 tasks in the list, Master!
```

## Managing Tasks
RAGDOLL allows you to mark tasks as done, delete tasks, and list all tasks.

### Marking a Task as Done
To mark a task as completed, use the command `mark` followed by the task number.

Example: `mark 1`
```
Master! I've marked this task as done:
  [T][X] #default read a book
```

### Unmarking a Task
To mark a task as not completed after marking it done, use the `unmark` command followed by the task number.

Example: `unmark 1`
```
Ok, Master! I've undone this task:
[T][ ] #default read a book
```

### Deleting a Task
To delete a task, use the command `delete` followed by the task number.

Example: `delete 2`
```
Master, I've removed this task:
  [D][ ] #default return library books (by: Oct 5 2023)
Now you have 2 tasks in the list, Master!
```

### Listing All Tasks
To see all tasks in your list, simply type `list`.
```
Master, your task list has the following tasks:
1.[T][X] #default read a book
2.[E][ ] #default project meeting (from: Oct 10 2023 to: Oct 12 2023)
```

## Finding Tasks
RAGDOLL can find tasks by date or keyword.

### Finding by Date
To find tasks on a specific date, use `find` followed by the date in `yyyy-mm-dd` format.

Example: `find 2023-10-10`
```
Master, on Oct 10 2023, you have the following tasks:
1.[E][ ] #default project meeting (from: Oct 10 2023 to: Oct 12 2023)
```

### Finding by Keyword
To search for tasks containing a specific keyword, use `find` followed by the keyword.

Example: `find book`
```
Master, here are the tasks containing the keyword 'book':
1.[T][X] #default read a book
```

## Tagging Tasks
You can assign tags to tasks for better organization.

Example: `tag 1 #urgent`
```
Ok, Master! I've tagged this task as: #urgent
[T][X] #urgent read a book 
```

## Viewing Current Date/Time
RAGDOLL can display the current date and time, providing you with a quick way to check the date and time without leaving the chat.

Command: `date / time`
```
Master, the current date and time is: Oct 5 2023 14:45:23!
```

## Exiting the Application
When you're done managing your tasks and wish to exit RAGDOLL, simply use the `bye` command. RAGDOLL will bid you farewell before shutting down.

Command: `bye`
```
See ya, Master!
```
Thank you for choosing RAGDOLL as your personal task manager. We hope this guide helps you navigate and utilize all the features effectively. Happy task managing!