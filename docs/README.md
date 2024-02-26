# Judy User Guide

<img src="Judy.png" alt="Judy" width="200">

> Forget about forgetting.
> 
> Hey, I'm Judy! You personal assistant for task management.

## Start Using Judy
1. Click [here](https://github.com/ziyi22/ip/releases/download/v0.1/Judy.jar) to download Judy.
2. Navigate to your Downloads folder using the terminal.
3. Enter `java -jar Judy.jar` in your terminal.
4. Press enter.
5. Start manage your task

## Adding todo

Add a todo task in your list.

Example: `todo laundry`

Expected output:
```
Got it! I've added this task:
   [T][ ] laundry
   Now you have 1 tasks in the list. 
```

## Adding Deadline

Add a deadline task in your list.

Example: `deadline Assignment 1 /by 2025-03-01 2359`

Expected output:
```
Got it! I've added this task:
   [D][ ] Assignment 1 (by: Jan 03 2025 11:59 PM)
   Now you have 2 tasks in the list. 
```

## Adding Event

Add an event task in your list.

Example: `event CNY party /from 2024-05-05 1400 /to 2024-05-05 2200`

Expected output:
```
Got it! I've added this task:
   [E][ ] CNY party (from: May 05 2024 02:00 PM to: May 05 2024 10:00 PM)
   Now you have 3 tasks in the list. 
```
## Displaying task list

Display all the tasks in your task list.

Example: `list`

Expected output:
```
Here are the tasks in your task list:
   1. [T][ ] laundry
   2. [D][ ] Assignment 1 (by: Jan 03 2025 11:59 PM)
   3. [E][ ] CNY party (from: May 05 2024 02:00 PM to: May 05 2024 10:00 PM)
```

## Mark a task

Use `mark` to mark a task as done by specifying the task index.
The task index must be a positive integer.

Example: `mark 1`

Expected output:
```
Nice! I've marked this task as done:
   [T][X] laundry
```

## Unmark a task

Use `unmark` to mark a task as undone by specifying the task index.
The task index must be a positive integer.

Example: `unmark 1`

Expected output:
```
Nice! I've unmarked this task as done:
   [T][ ] laundry
```

## Delete a task

Use `delete` to delete a task by specifying the task index.
The task index must be a positive integer.

Example: `delete 1`

Expected output:
```
Noted. I've removed this task:
   [T][ ] laundry
   Now you have 2 tasks in the list.
```

## Finding a task

Use `find` to find a task using keyword.

Example: `find Assingment`

Expected output:
```
Here are the matching tasks in your list:
   1. [D][ ] ssignment 1 (by: Jan 03 2025 11:59 PM)
```

## Exit Judy

Use `bye` to exit the chat.