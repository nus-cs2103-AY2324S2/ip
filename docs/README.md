```

                                                                                                      
       ____                 _____    ____________ ___________        ____________             _____   
   ____\_  \__         _____\    \  /            \\          \      /            \       _____\    \  
  /     /     \       /    / \    ||\___/\  \\___/|\    /\    \    |\___/\  \\___/|     /    / \    | 
 /     /\      |     |    |  /___/| \|____\  \___|/ |   \_\    |    \|____\  \___|/    |    |  /___/| 
|     |  |     |  ____\    \ |   ||       |  |      |      ___/           |  |      ____\    \ |   || 
|     |  |     | /    /\    \|___|/  __  /   / __   |      \  ____   __  /   / __  /    /\    \|___|/ 
|     | /     /||    |/ \    \      /  \/   /_/  | /     /\ \/    \ /  \/   /_/  ||    |/ \    \      
|\     \_____/ ||\____\ /____/|    |____________/|/_____/ |\______||____________/||\____\ /____/|     
| \_____\   | / | |   ||    | |    |           | /|     | | |     ||           | /| |   ||    | |     
 \ |    |___|/   \|___||____|/     |___________|/ |_____|/ \|_____||___________|/  \|___||____|/      
  \|____|                                                                                             

```

# Osiris User Guide

> _" A groundbreaking chatbot designed to revolutionize task management. "_


Introducing Osiris, the groundbreaking chatbot designed to revolutionize task management. 
With Osiris, streamline your productivity and effortlessly organize your tasks like never before.

## Installation

---

1. **Download Osiris**: Click [here](https://github.com/Austintjh19/ip/releases) to download the Osiris application.
2. **Navigate to the Folder**: Open your preferred file explorer and go to the directory containing the `Osiris.jar` file.
3. **Open Terminal**: Launch a terminal window at the folder location.
4. **Execute Command**: Type the following command into the terminal: `java -jar Osiris.jar`.
5. **Press Enter**: Hit the `Enter` key to execute the command.
6. **Enjoy Osiris**: Osiris is now ready to use! Enjoy the streamlined task management experience! 🚀


---
### Adding Todo Tasks

The "Add Todo Task" feature enables users to add simple todo tasks to their task list.

**Usage:**

To add a todo task, use the following command format:

```
todo task_name
```

- Replace `task_name` with the description of your todo task.

**Example:**

```
todo Buy groceries
```

**Expected Output:**

The system will confirm the addition of the todo task and display relevant details.

```
Task Added:
[T] [] Buy groceries
Current Tack Count: 11
```
---
### Adding Deadlines

The "Add Deadline" feature allows users to create tasks with specific deadlines.

**Usage:**

To add a deadline task, use the following command format:

```
deadline task_name /by deadline_date 
```

- Replace `task_name` with the name of your task.
- Replace `deadline_date` with the deadline date in the format `DD-MM-YYYY`.

**Example:**

```
deadline Complete project report /by 15-03-2024
```

**Expected Output:**

The system will confirm the addition of the deadline task and display relevant details.

```
Task Added:
[D] [] Complete project report (by: Mar 15 2024)
Current Tack Count: 10
```
---
### Adding Event Tasks

The "Add Event" feature allows users to schedule event tasks within a specified time range.

**Usage:**

To add an event task, use the following command format:

```
event task_name /from start_date_time /to end_date_time
```

- Replace `task_name` with the name of your event task.
- Replace `start_date_time` with the start date and time of the event.
- Replace `end_date_time` with the end date and time of the event.

**Example:**

```
event Team meeting /from 13-03-2023 1800 /to 13-03-2023 2000
```

**Expected Output:**

The system will confirm the addition of the event task and display relevant details.

```
Task Added:
[E] [] Team meeting (from: Mar 13 2023 6:00 PM to: Mar 13 2023 8:00 PM)
Current Tack Count: 12
```
---
### Marking Tasks Complete

The "Mark Task Complete" feature allows users to mark tasks as completed.

**Usage:**

To mark a task as complete, use the following command format:

```
mark task_index
```

- Replace `task_index` with the index of the task you want to mark as complete.

**Example:**

```
mark 3
```

**Expected Output:**

The system will confirm the task completion and update the task status accordingly.

```
Marked Task:
    [T] [X] 100 sit-ups
```

---

### Marking Tasks Incomplete

The "Mark Task Incomplete" feature allows users to mark completed tasks as incomplete.

**Usage:**

To mark a task as incomplete, use the following command format:

```
unmark task_index
```

- Replace `task_index` with the index of the task you want to mark as incomplete.

**Example:**

```
unmark 5
```

**Expected Output:**

The system will update the task status to incomplete and confirm the change.

```
Unmarked Task:
   [T] [] Buy groceries
```
---
### Deleting Tasks

The "Delete Task" feature enables users to remove tasks from their task list.

**Usage:**

To delete a task, use the following command format:

```
delete task_index
```

- Replace `task_index` with the index of the task you want to delete.

**Example:**

```
delete 5
```

**Expected Output:**

The system will confirm the deletion of the task from the task list.

```
Removed Task:
   [T] [] Buy groceries
Current Tack Count:
```
---
### Listing User Tasks

The "List Tasks" feature allows users to view their current task list.

**Usage:**

To list all tasks, use the following command:

```
list
```

**Expected Output:**

The system will display a list of all tasks currently stored in the task list, including details such as task name, deadline (if applicable), and completion status.

Example output:

```
Pending Tasks:
1. [T] [] Read Book
2. [D] [X] 1231 Tut Sheet (by: Dec 12 2023)
3. [E] [X] Orientation Camp (from: Jan 12 2023 12:00 AM to: Jan 15 2023 11:59 PM)
```

In the output:
- Tasks marked with `[x]` indicate marked tasks.
- Tasks marked with `[ ]` indicate incomplete tasks.
- Todo tasks marked with [T]
- Deadline tasks marked with [D]
- Event tasks marked with [E]

---

### Finding Tasks

The "Find Tasks" feature allows users to search for specific tasks based on keywords or phrases.

**Usage:**

To find tasks containing specific keywords, use the following command format:

```
find keyword
```

- Replace `keyword` with the search term or phrase you want to use.

**Example:**

```
find book
```

**Expected Output:**

The system will display a list of tasks that contain the specified keyword or phrase.

Example output:

```
Here are the matching tasks in your list:
  1. [T] [X] Borrow Book
  2. [T] [] Read Book
  3. [D] [X] Return Book (by: Dec 22 2023)
```
---
### Terminating Chat

The "Terminate Chat" feature allows users to gracefully exit the Osiris application.

**Usage:**

To terminate the chat, use the following command:

```
bye
```

**Expected Output:**

The system will acknowledge the termination request and gracefully exit the application.

Example output:

```
Bye. Hope to see you again soon!
```
---