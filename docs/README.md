# RochinBot User Guide

![Screenshot of the product.](Ui.png)

Welcome to RochinBot, your personal task management chatbot! RochinBot helps you organize your tasks efficiently. Whether it's a simple to-do list or managing deadlines and events, RochinBot has got you covered. This guide will walk you through the various features and commands you can use to interact with RochinBot.


## Quick start
1. Ensure you have Java 11 or above version installed in your computer.
2. Download the latest rochin.jar
3. Copy the file to the folder you want to use as the *home folder* for RochinBot.
4. Open a command terminal, 'cd' into the folder you put the jar file in, and use the 'java -jar rochin.jar' command to run the application.

## Basic Commands

### Adding a Todo Task
* Command: todo <description>
* Example: `todo Clean the house`

Add a new todo task with the given description.

Expected output:
```
Got it. I've added this task:
[T][ ] Clean the house
Now you have 1 tasks in the list.
```

### Adding a Deadline Task
* Command: deadline description /by date and time
* Example: `deadline Submit report /by 2024-02-28 1700`

Add a new deadline task with the given description and deadline.

Expected output:
```
Got it. I've added this task:
[D][ ] Submit report (by: Feb 28 2024 1700)
Now you have 2 tasks in the list.
```

### Adding an Event Task
* Command: event description /from startDateTime /to endDateTime
* Example: `event Project meeting /from 2024-02-25 1000 /to 2024-02-25 1200`

Add a new event task with the given description and duration.

Expected output:
```
Got it. I've added this task:
[E][ ] Project meeting (from: Feb 25 2024 1000 to: Feb 25 2024 1200)
Now you have 3 tasks in the list.
```

### Listing Tasks
* Command: list

List all tasks currently in the task list.

Expected output:
```
Here are the tasks in your list:
1.[T][ ] Clean the house
2.[D][ ] Submit report (by: Feb 28 2024 1700)
3.[E][ ] Project meeting (from: Feb 25 2024 1000 to: Feb 25 2024 1200)
```

### Marking/Unmarking a Task as Done
* Command: mark taskIndex
* Example: `mark/unmark 1`

Mark or unmark the task at the specified index as done.

Expected output:
```
Nice! I've marked this task as done:
[T][X] Clean the house
```

### Deleting a Task
* Command: delete taskIndex
* Example: `delete 2`

Delete the task at the specified index from the task list.

Expected output:
```
Noted. I've removed this task.
Now you have 2 tasks in the list.
```

### Searching for Tasks
* Command: find <keyword>
* Example: `find meeting`

Search for tasks containing the specified keyword in their description.

Expected output:

```
Here are the tasks in your list:
1.[E][ ] Project meeting (from: Feb 25 2024 1000 to: Feb 25 2024 1200)
```
