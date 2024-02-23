# Tfamily bot User Guide

![Product Screenshot](./Ui.png)

Tfamily bot is a chatbot designed to assist you with creating, tracking, and managing your tasks. It can handle Todos, Deadlines, and Events with ease.

When you start the chatbot, you will be greeted with:

> "Hi! I'm Tfamily bot. How can I assist you today?"

## Features

### Todo

- **Add a Todo**
  - Example: `todo laundry`
  - Reply:
    ```
    Got it. I've added this task:
    [T][] laundry
    Now you have 1 task in the list.
    ```

### Deadline

- **Add a Deadline**
  - Example: `deadline return book /by 2019-10-15`
  - Reply:
    ```
    Got it. I've added this task:
    [D][] return book (by: Oct 15 2019)
    Now you have 2 tasks in the list.
    ```

### Event

- **Add an Event**
  - Example: `event project meeting /from Mon 2pm /to 4pm`
  - Reply:
    ```
    Got it. I've added this task:
    [E][] project meeting (from: Mon 2pm to 4pm)
    Now you have 3 tasks in the list.
    ```

### List All Tasks

- **View All Tasks**
  - Command: `list`
  - Reply:
    ```
    Here are all the todos, deadlines, and events currently present:
    [Your tasks list here]
    ```

### Mark and Unmark as Done

- **Mark/Unmark a Task as Done**
  - To mark as done: `mark 2`
  - To unmark: `unmark 2`
  - Reply:
    ```
    [Mark/Unmark] the second task in the list as [completed/uncompleted].
    ```

### Deleting a Task

- **Delete a Task**
  - Example: `delete 2`
  - Reply:
    ```
    Noted. I've removed this task:
    [Details of the deleted task]
    Now you have [number] tasks in the list.
    ```

### Find Tasks

- **Search for Tasks**
  - Example: `find book`
  - Reply:
    ```
    Here are all the tasks containing "book":
    [List of tasks with "book"]
    ```

### Check Upcoming Deadlines

- **View Upcoming Deadlines**
  - Command: `upcoming`
  - Reply:
    ```
    Here are all the upcoming deadlines:
    [List of upcoming deadlines]
    ```

### Ending the Conversation

- **Say Goodbye**
  - Command: `bye`
  - Reply:
    ```
    Bye! Hope to see you again soon!
    ```

### Invalid Input

- **Handle Unknown Commands**
  - Reply:
    ```
    Sorry, invalid input.
    ```

For a more detailed guide on each feature and additional commands, please refer to our [Extended User Manual](link-to-extended-manual).
