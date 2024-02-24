# Demon User Guide

## About

Demon is a chatbot with an intuitive Graphical User Interface (GUI) that helps you track your tasks.

These are the tasks that Demon supports:
* To-do: Basic task with description
* Deadline: Task with a date to be completed by
* Event: Task with a start and end date

If you're new here, you can start with [Getting Started](#getting-started).

If you have used Demon before, you can jump straight to Demon's [Features and Commands](#features-and-commands).

<img src="Ui.png" width="70%" display='block' margin-left="auto" margin-right="auto"/>

**Figure 1**: Example of Demon's GUI.

## Getting Started

1. Ensure you have [Java 11](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html) installed on your Computer by opening up a command terminal and typing `java -version`. The build version is contained in the (brackets).
2. Download the latest from [here](https://github.com/colex2000/ip/releases/tag/v0.2).
3. Copy the file to the folder you want to use as the home folder for your Demon.
4. Open a command terminal.
5. Change the working directory to the folder you put the .jar in, by typing and running `cd {directory}`
6. Then, type and run the `java -jar demon.jar` command to run the application.

## Features and Commands

### `todo` - Add a to-do task

To add a todo task to your task list, you can type `todo [DESCRIPTION]`

**Example of usage:**

`todo submit cs2103t ip`

**Expected outcome:**

```
Yes Master, I've added this task:
[T][ ] submit cs2103t ip 
Now you have 1 tasks in the list.
```

> [!NOTE]
> Demon will not allow you to add task that already exist .

Demon will also let you know how many tasks you have in the list after adding this task.

---
### `deadline` - Add a deadline task

To add a task with a deadline to your task list, you can type `deadline [DESCRIPTION] /by [DEADLINE_DATE_TIME]`

> [IMPORTANT]
> Please note that the format for the `[DEADLINE_DATE_TIME]` is **'DD/MM/YYYY HHMM'**.
> HHMM is an **optional** field. By default, HHMM is set to 0000 (12 AM).


**Example of usage:**

`deadline ES2660 /by 02/04/2024 2211`

**Expected outcome:**

```
Yes Master, I've added this task:
[D][ ] ES2660 (by: Apr 2 2024 10:11 PM) 
Now you have 2 tasks in the list.
```
> [!NOTE]
> Demon will not allow you to add task that already exist .
> 
Demon will also let you know how many tasks you have in the list after adding this task.

---
### `event` - Add an event task

To add an event task with a start and end time to your task list, you can type `event [DESCRIPTION] /from [START_DATE_TIME] /to [END_DATE_TIME]`

> [!IMPORTANT]
> Please note that the format for both `START_DATE_TIME` and `END_DATE_TIME` is **'DD/MM/YYYY HHMM'**.
> HHMM is an **optional** field. By default, HHMM is set to 0000 (12 AM).

**Example of usage:**

`event Bruno Mars concert /from 01/04/2024 /to 10/04/2024 2359`

**Expected outcome:**

```
Yes Master, I've added this task:
[E][ ] Bruno Mars concert (from: Apr 1 2024 12:00 AM to: Apr 10 2024 11:59 PM)
Now you have 3 tasks in the list.
```

> [!NOTE]
> Demon will not allow you to add task that already exist .
> 
Demon will also let you know how many tasks you have in the list after adding this task.

---
### `list` - View all tasks

To view all the tasks you have added so far, you can type `list`

**Example of usage:**

`list`

**Expected outcome:**

```
Here are the tasks in your list:
1. [T][ ] submit cs2103t ip
2. [D][ ] ES2660 (by: Apr 2 2024 10:11 PM) 
3. [E][ ] Bruno Mars concert (from: Apr 1 2024 12:00 AM to: Apr 10 2024 11:59 PM)
```
---
### `mark` - Mark a task as done

To mark a task as done, you can type `mark [TASK_INDEX]`

> [!IMPORTANT]
> Please note that `[TASK_INDEX]` is **1-based**.

**Example of usage:**

`mark 1`

**Expected outcome:**

```
Sure Master, I've marked this task as done:
[T][X] submit cs2103t ip
```
---
### `unmark` - Unmark a task as not done

To mark a task as not done, you can type `unmark [TASK_INDEX]`

> [!IMPORTANT]
> Please note that `[TASK_INDEX]` is **1-based**.

**Example of usage:**

`unmark 1`

**Expected outcome:**

```
Sure Master, I've unmarked this task as not done:
[T][ ] submit cs2103t ip 
```
---
### `delete` - Delete a task

To delete a task, you can type `delete [TASK_INDEX]`

> [!IMPORTANT]
> Please note that `[TASK_INDEX]` is **1-based**.

**Example of usage:**

`delete 2`

```
Noted Master. I've removed this task:
[D][ ] ES2660 (by: Apr 2 2024 10:11 PM) 
Now you have 2 tasks in the list.
```
Demon will also let you know how many tasks you have in the list after deleting this task.

---
### `find` - Find a task

To find a task, you can type `find [KEYWORDS]`

`[KEYWORDS]` refers to any words you want to find, not case-sensitive.

**Example of usage:**

`find bruno mars`

**Expected outcome:**

```
Here are your matching tasks in your list:
2. [E][ ] Bruno Mars concert (from: Apr 1 2024 12:00 AM to: Apr 10 2024 11:59 PM)
```
> [!TIP]
> You can also find tasks by specifying the date.

**Example of usage:**

`find Apr 10`

**Expected outcome:**

```
Here are your matching tasks in your list:
2. [E][ ] Bruno Mars concert (from: Apr 1 2024 12:00 AM to: Apr 10 2024 11:59 PM)
```

---
### `bye` - Exit the bot

To exit the bot, you can type `bye`

**Example of usage:**

`bye`

**Expected outcome:**

```
Bye Master, hope you had a great time, see you!
```