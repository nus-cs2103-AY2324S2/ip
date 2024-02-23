# Dibo User Guide

## About

Dibo is a chatbot with an intuitive Graphical User Interface (GUI) that helps you track your tasks. :smile:

These are the tasks that Dibo supports:
* To-do: Basic task with description
* Deadline: Task with a date to be completed by
* Event: Task with a start and end date
* Do-after: Task that can only be done after a certain date

If you're new here, you can start with [Getting Started](#getting-started). :full_moon:

If you have used Dibo before, you can jump straight to Dibo's [Features and Commands](#features-and-commands). :new_moon:

<img src="./docs/Ui.png" width="70%" display='block' margin-left="auto" margin-right="auto"/>

**Figure 1**: Example of Dibo's GUI.

## Getting Started

1. Ensure you have [Java 11](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html) installed on your Computer by opening up a command terminal and typing `java -version`. The build version is contained in the (brackets).

2. Download the latest  from [here](https://github.com/ziyang27/ip/releases/tag/A-Release).

3. Copy the file to the folder you want to use as the home folder for your Dibo.

4. Open a command terminal.

5. Change the working directory to the folder you put the .jar in, by typing and running `cd {directory}`

6. Then, type and run the `java -jar dibo.jar` command to run the application.

## Features and Commands

---
### `todo` - Add a to-do task

To add a todo task to your task list, you can type `todo [TASK_NAME]`

**Example of usage:**

`todo cs2103t ip`

**Expected outcome:**

```
Good news sir! I've added this task:
[T][ ] cs2103t ip 
Now you have 1 tasks in the list.
```
Dibo will also let you know how many tasks you have in the list after adding this task.

---
### `deadline` - Add a deadline task

To add a task with a deadline to your task list, you can type `deadline [TASK_NAME] /by [DEADLINE_DATE]`

> [!IMPORTANT]
> Please note that the format for the `[DEADLINE_DATE]` is **'YYYY-MM-DD'**


**Example of usage:**

`deadline cs2109s ps4 /by 2024-02-24`

**Expected outcome:**

```
Good news sir! I've added this task:
[D][ ] cs2109s ps4 (by: Feb 24 2024) 
Now you have 2 tasks in the list.
```
Dibo will also let you know how many tasks you have in the list after adding this task.

---
### `do-after` - Add a do-after task

To add a task with a date to do after to your task list, you can type `do-after [TASK_NAME] /after [DO_AFTER_DATE]`

> [!IMPORTANT]
> Please note that the format for the `[DO_AFTER_DATE]` is **'YYYY-MM-DD'**

**Example of usage:**

`do-after watch football /after 2024-02-24`

**Expected outcome:**

```
Good news sir! I've added this task:
[A][ ] watch football (after: Feb 24 2024) 
Now you have 3 tasks in the list.
```
Dibo will also let you know how many tasks you have in the list after adding this task.

---
### `event` - Add an event task

To add an event task with a start and end time to your task list, you can type `event [TASK_NAME] /from [START_DATE] /to [END_DATE]`

> [!IMPORTANT]
> Please note that the format for both `START_DATE` and `END_DATE` is **'YYYY-MM-DD'**

**Example of usage:**

`event taylor swift's concert /from 2024-03-02 /to 2024-03-09`

**Expected outcome:**

```
Good news sir! I've added this task:
[E][ ] event taylor swift's concert (from: Mar 02 2024 to Mar 09 2024) 
Now you have 4 tasks in the list.
```
Dibo will also let you know how many tasks you have in the list after adding this task.

---
### `list` - View all tasks

To view all the tasks you have added so far, you can type `list`

**Example of usage:**

`list`

**Expected outcome:**

```
Here are the tasks in your list:
1.[T][ ] cs2103t ip 
2.[D][ ] cs2109s ps4 (by: Feb 24 2024)
3.[A][ ] watch football (after: Feb 24 2024) 
4.[E][ ] event taylor swift's concert (from: Mar 02 2024 to Mar 09 2024) 
```
---
### `mark` - Mark a task as done

To mark a task as done, you can type `mark [TASK_INDEX]`

> [!IMPORTANT]
> Please note that the `[TASK_INDEX]` is **1-based**.

**Example of usage:**

`mark 1`

**Expected outcome:**

```
OK, I've marked this task as done:
[T][X] cs2103t ip 
```
---
### `unmark` - "Unmark" a task as not done

To mark a task as not done, you can type `unmark [TASK_INDEX]`

> [!IMPORTANT]
> Please note that the `[TASK_INDEX]` is **1-based**.

**Example of usage:**

`unmark 1`

**Expected outcome:**

```
OK, I've marked this task as not done yet:
[T][ ] cs2103t ip 
```
---
### `delete` - Delete a task

To delete a task, you can type `delete [TASK_INDEX]`

> [!IMPORTANT]
> Please note that the `[TASK_INDEX]` is **1-based**.

**Example of usage:**

`delete 4`

```
Noted, I'm removing this task:
[E][ ] event taylor swift's concert (from: Mar 02 2024 to Mar 09 2024)
Now you have 3 tasks in the list.
```
Dibo will also let you know how many tasks you have in the list after deleting this task.

---
### `find` - Find a task

To find a task, you can type `find [WORDS]`

`[WORDS]` refers to any words contained within the task, and it is case-sensitive.

**Example of usage:**

`find cs2109s`

**Expected outcome:**

```
Good news sir! We've found the tasks in your list:
1. [D][ ] cs2109s ps4 (by: Feb 24 2024)
```
> [!TIP] 
> You may enter more than one word to conduct a more specific search for tasks that contain **ALL** the keywords.

---
### `bye` - Exit the bot

Going so soon? :slightly_frowning_face:

To exit the bot, you can type `bye`

**Example of usage:**

`bye`

**Expected outcome:**

```
Bye sir! Always happy to assist you :D
Hope to see you again soon!
```
