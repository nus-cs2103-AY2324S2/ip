# Jelly Chatbot User Guide
Welcome to the Jelly Chatbot User Guide! Jelly is your personal assistant for managing tasks, reminders, and much more. This guide will help you get started with Jelly and make the most of its features. Below, you'll find detailed instructions on how to use each command, along with example responses to guide you.

## Getting Started
To start using Jelly, simply type your command in the chat and press Enter. Jelly understands a variety of commands to help you manage your tasks effortlessly.

## Commands Overview
Jelly supports the following commands:

- `todo`: Adds a todo task.
- `deadline`: Adds a task with a deadline.
- `event`: Adds an event with a start and end time.
- `list`: Lists all tasks.
- `delete`: Deletes a specific task.
- `find`: Finds tasks containing a specific keyword.
- `mark`: Marks a task as done.
- `unmark`: Marks a task as not done.
- `bye`: Exits the chat.

### Todo Command
Syntax: `todo [task name]`

Description: Adds a todo task to your list.

#### Example:

Input: `todo read a book`

Response: 
```
Got it. I've added this task: 
[T][ ] read a book 
Now you have 1 tasks in the list.
```

### Deadline Command
Syntax: `deadline [task name] /by [deadline]`

Description: Adds a task with a specific deadline.

#### Example:

Input: `deadline submit report /by Sunday`

Response: 
```
Got it. I've added this task: 
[D][ ] submit report (by: Sunday) 
Now you have 2 tasks in the list.
```
### Event Command
Syntax: `event [event name] /from [start time] /to [end time]`

Description: Adds an event with a start and end time.

#### Example:
Input: `event team meeting /from Monday 10am /to Monday 11am`

Response: 
```
normal Got it. I've added this task: 
[E][ ] team meeting (from: Monday 10am to Monday 11am) 
Now you have 3 tasks in the list.
```
### List Command
Syntax: `list`

Description: Lists all current tasks.

#### Example:
Input: `list`
Response:
```
1. [T][ ] read a book
2. [D][ ] submit report (by: Sunday)
3. [E][ ] team meeting (from: Monday 10am to Monday 11am)
```

### Delete Command
Syntax: `delete [task number]`

Description: Deletes a task by its number.
#### Example:
Input: `delete 2`
   
Response: 
```
normal Noted, I've removed this task: 
[D][ ] submit report (by: Sunday) 
Now you have 2 tasks in the list.
```
### Find Command
   
Syntax: `find [keyword]`
   
Description: Finds tasks that contain the specified keyword.

#### Example:
   
Input: `find book`
   
Response: 
```
Here are the tasks that match your keyword! 
1. [ ] read a book
```
   
### Mark Command
   
Syntax: `mark [task number]`

Description: Marks a specific task as done.

#### Example:
   
Input: `mark 1`
   
Response: 
```
excited Nice, I've marked this task as done: 
[T][x] read a book
```
   
### Unmark Command
   
Syntax: `unmark [task number]`
   
Description: Marks a specific task as not done.
   
#### Example:
   
Input: `unmark 1`
   
Response: 
```
normal OK, I've marked this task as not done yet: 
[T][ ] read a book
```
   
### Bye Command
   
Syntax: `bye`
   
Description: Exits the chat.

#### Example:
   
Input: `bye`
   
Response: 
```
Bye. Hope to see you again soon!
```

## Ask Jelly Anything!

Jelly is powered by ChatGPT when he is connected to the internet! so feel free to ask him anything under the sun.
He is encouraging and fun to be around and will help you with getting your commands right!

## Additional Information
   
- Formatting errors or incorrect command usage will prompt Jelly to respond with a helpful message to guide you in using the correct format.
   
- Jelly is designed to be intuitive and user-friendly, making task management easier and more efficient.
   
Thank you for choosing Jelly as your personal task management assistant!
