# DevGPT User Guide

![DevGPT](Ui.png)

DevGPT is a smart chatbot that offloads your daily tasks from your mind and reminds you of them when needed. 
It is optimized for developers and has a command-line interface (CLI) coupled with a nice Graphical User Interface (GUI).

## Add todos
The most basic feature of DevGPT is adding todos. It allows you to add tasks to the chatbot.  
Represented by a `[T]` in the chatbot and `[X]` to represent a completed task.

Send a message in the following format:  
`todo <description>`
Example: `todo borrow book`

A message would indicate that the task has been added to the chatbot.
```
DevGPT:
	Got it. I've added this task:
		[T][ ] borrow book
    Now you have 1 tasks in the list.
```

## Add deadlines
The deadlines feature allows you to add deadlines to the chatbot. A popup window will remind you of the deadlines when it is due.  
Represented by a `[D]` in the chatbot and `[X]` to represent a completed task.

Send a message in the following format:  
`deadline <description> /by <date> <time>`  
Date and time format: `dd-mm-yyyy HHmm`  
Example: `deadline return book /by 22-12-2024 1800`  

Note: Time is in 24-hour format and is optional.

A message would indicate that the deadline has been added to the chatbot.
```
DevGPT:
    Got it. I've added this task:
        [D][ ] return book (by: 22 Dec 2024 18:00)
    Now you have 2 tasks in the list.
```

## Add events
The events feature allows you to add events to the chatbot. It allows you to keep track of the event period.  
Represented by a `[E]` in the chatbot and `[X]` to represent a completed task.

Send a message in the following format:  
`deadline <description> /from <date> /to <date>`  
date and time format: `dd-mm-yyyy `  
Example: `event project meeting /from 18-12-2024 /to 19-12-2024`  

A message would indicate that the event has been added to the chatbot.
```
DevGPT:
    Got it. I've added this task:
        [E][ ] project meeting (from: 18 Dec 2024 to: 19 Dec 2024)
    Now you have 3 tasks in the list.
```

## List all tasks
List all tasks in the chatbot.

Send a message in the following format:  
`list task`

A message would indicate that the tasks have been listed.
```
DevGPT:
    Here are the tasks in your list:
    1. [T][ ] borrow book
    2. [D][ ] return book (by: 22 Dec 2024 18:00)
    3. [E][ ] project meeting (from: 18 Dec 2024 to: 19 Dec 2024)
```

## Mark task as done
Mark a task as done in the chatbot to indicate that it has been completed.   
Represented by a `[X]` in the chatbot to indicate the task is completed.

Send a message in the following format:  
`done <task number>`  
Example: `done 1`  

A message would indicate that the task has been marked as done.
```
DevGPT:
    Nice! I've marked this task as done:
    [T][X] borrow book
```

## Mark task as undone
Mark a task as undone in the chatbot to indicate that it has not been completed.  
Represented by a `[ ]` to indicate that the task is not completed.

Send a message in the following format:  
`undone <task number>`
Example: `undone 1`  

A message would indicate that the task has been marked as undone.
```
DevGPT:
    Got it! I've marked this task as undone:
    [T][ ] borrow book
```

## Delete task
Delete a task from the chatbot when the task is no longer needed.

Send a message in the following format:  
`delete <task number>`  
Example: `delete 1`

A message would indicate that the task has been deleted.
```
DevGPT:
    Poof! I've removed this task:
    [T][X] borrow book
    Now you have 2 tasks in the list.
```

## Find task
Find a task from the chatbot using keywords from the description when you need to search for a specific task.

Send a message in the following format:  
`find <keyword>`  
Example: `find book`  

A message would indicate that the tasks have been found.
```
DevGPT:
    Here are the tasks in your list:
    1. [D][ ] return book (by: 22 Dec 2024 18:00)
```

## Exit
Exit the chatbot. Saves the tasks to a file and closes.

Send a message in the following format:
`bye`

A message would indicate that the chatbot is exiting.
```
DevGPT:
    Bye. Hope to see you again soon!
```
