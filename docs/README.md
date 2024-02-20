# TheAdvisor project
This is a chatbot that can help you and store tasks that you wish to do, such as todo, events and deadline. With features such as find, marking and unmarking a task as done or not done and many more!

## Features
- **list**: To view the tasks stored in the list, will also load tasklist from previous usage.
- **mark**: To mark a task and set it as done.
- **unmark**: To unmark a task and set it as undone.
- **todo**: To add a todo task into your tasklist.
- **deadline**: To add a task with a deadline into your tasklist.
- **event**: To add a task with a start date and an end date into your tasklist.
- **find**: Search for tasks by keyword.
- **Error Handling**: Informative error messages for incorrect inputs.
- **Storage**: Persist tasks between sessions.
- **User Interface**: Simple and intuitive user interface.
  =======
- **list**: View the tasks stored in the list; will also load the task list from previous sessions.
- **mark**: Mark a task as done.
- **unmark**: Unmark a task, setting it as undone.
- **delete** Delete a task, with 1-based indexing
- **todo**: Add a todo task to your task list.
- **deadline**: Add a task with a deadline to your task list.
- **event**: Add a task with a start and end date to your task list.
- **find**: Search for tasks by keyword.
- **Error Handling**: Informative error messages for incorrect inputs.
- **Storage**: Persist tasks between sessions.
- **User Interface**: Simple and intuitive interface for interaction.

## Examples of creating tasks
- Todo task
    - todo sleep
```
Very well, your task has been added: 
[T][ ] sleep
Now you have 1 tasks in the list.
```
- Deadline task
    - deadline CS2103 ip /by 2024-02-23 2359
```
Very well, your task has been added: 
[D][ ] CS2103 ip (by Feb 23 2024 2359hrs)
Now you have 1 tasks in the list.
```
- Event task
    - event play game /from 2024-02-20 2100 /to 2024-02-20 2300
```
Very well, your task has been added: 
[E][ ] play game (from Feb 20 2024 2100hrs to: Feb 20 2024 2300hrs)
Now you have 1 tasks in the list.
```

## Examples of functions in the chatbot
- Delete task
    - delete 1
```
Very well, I've removed this task with my supreme visual prowess: 
[T][ ] sleep
Now you have 2 tasks in the list.
```
- Mark task
    - mark 2
```
Very well, I've marked this task with my supreme visual prowess: 
[E][X] play game (from Feb 20 2024 2100hrs to: Feb 20 2024 2300hrs)
Now you have 2 tasks in the list.
```
- Unmark task
    - unmark 2
```
Very well, I've unmarked this task with my supreme visual prowess: 
[E][ ] play game (from Feb 20 2024 2100hrs to: Feb 20 2024 2300hrs)
Now you have 2 tasks in the list.
```
- Find task
    - find sl
```
My eyes have shown it to me, those that you seek are here:
1. [T][ ] sleep
```
