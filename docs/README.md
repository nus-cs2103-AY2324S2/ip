# Rahhbot User Guide

## Introduction
Welcome to RahhBot! RahhBot is your friendly virtual assistant designed to make your life easier. Whether you need help managing your tasks, finding information, or just want to chat, RahhBot is here to assist you. This user guide will walk you through the basic functionalities of RahhBot and how to interact with it effectively.

<img src="Ui.png" height="400" alt="Ui Image">

## Getting Started
To start using RahhBot, simply type your queries or commands into the chat window. RahhBot will respond promptly and provide you with the assistance you need. You can ask RahhBot about tasks, deadlines, events, and more.

## Basic Commands
### 1. Creating a Task:
- To create a new task, use the command: todo **task description**.
- Example: todo Do laundry.
- **_Expected output_**
- ```
  More tasks? Someone's being very ambitious here. I like. Added:
  T | [ ] | Do laundry
  Now you have 1 tasks total!
  ```

### 2. Creating a Deadline Task:
- To create a task with a deadline, use the command: deadline **task description** /by **date**.
- Example: deadline Submit report /by 2024-02-13.
- **_Expected output_**
- ```
  More tasks? Someone's being very ambitious here. I like. Added:
  D | [ ] | Do laundry | (by: 2024-02-13)
  Now you have 2 tasks total!
  ```

### 3. Creating an Event Task:
- To create an event task, use the command: event **event description** /from **start date** /to **end date**.
- Example: event Team meeting /from 2024-02-27 /to 2024-03-27.
- **_Expected output_**
- ```
  More tasks? Someone's being very ambitious here. I like. Added:
  E | [ ] | Team meeting | (from: 2024-02-27 to 2024-03-27)
  Now you have 3 tasks total!
  ```

### 4. Listing Tasks:
- To list all tasks, use the command: list.
- **_Expected output_**
- ```
  So much to do dude,
     1. [T][ ] Do laundry
     2. [D][ ] Submit report (by: 2024-02-13)
     3. [E][ ] Team meeting (from: 2024-02-27 to 2024-03-27)
  Total number of tasks: 3
  As someone once said, WHO'S GONNA ROW THE BOATS?!!
  ```

### 5. Marking Task as Done:
- To mark a task as done, use the command: mark **task index**.
- Tip: Use the list command to find the index of the task you want to mark as done.
- Example: mark 1.
- **_Expected output_**
- ```
  Letz goo! Another task bites the dust!
  ```

### 6. Marking Task as Not Done:
- To mark a task as not done, use the command: unmark **task index**.
- Example: mark 1.
- **_Expected output_**
- ```
  Damn it ain't done yet? GET TO WORKKK!
  ```

### 7. Deleting a Task:
- To delete a task, use the command: delete **task index**.
- Example: delete 2.
- **_Expected output_**
- ```
  That task is goneee for goood. There's no pending tasks if they don't exist amirite.
  ```

### 8. Finding Tasks:
- To search for tasks containing a specific keyword, use the command: find **keyword**.
- Example: find meeting.
- It's case-insensitive, so you can use any combination of uppercase and lowercase letters or even partial keywords!

### 9. Exiting RahhBot:
- To exit RahhBot, simply type: bye.

## Additional Features
### Help Command:
- If you need assistance or want to see a list of available commands, type help!

## Conclusion
That's it! You are now ready to start using RahhBot to manage your tasks and get things done more efficiently. If you have any questions or encounter any issues, feel free to ask RahhBot for help. Enjoy chatting with RahhBot!

## Download the latest version of RahhBot [here](https://github.com/justinlengch/ip/releases/tag/A-Release).
