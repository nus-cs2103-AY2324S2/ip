Certainly! I've updated the user guide with general daily tasks examples such as doing the laundry, grocery shopping, etc.

# Martin User Guide

## About

Martin is a chatbot with an intuitive Graphical User Interface (GUI) that helps you track your tasks.

These are the tasks that Martin supports:
* To-do: Basic task with description
* Deadline: Task with a date to be completed by
* Event: Task with a start and end date

If you're new here, you can start with [Getting Started](#getting-started). 

If you have used Martin before, you can jump straight to Martin's [Features and Commands](#features-and-commands). 

<img src="Ui.png" width="70%" display='block' margin-left="auto" margin-right="auto"/>

**Figure 1**: Example of Martin's GUI.

## Getting Started 

1. Ensure you have [Java 11](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html) installed on your computer by opening up a command terminal and typing `java -version`. The build version is contained in the (brackets).
2. Download the latest from [here](https://github.com/tanyunchao/ip/releases/tag/v0.2).
3. Copy the file to the folder you want to use as the home folder for your Martin.
4. Open a command terminal.
5. Change the working directory to the folder you put the .jar in, by typing and running `cd {directory}`
6. Then, type and run the `java -jar martin.jar` command to run the application.

## Features and Commands

> [!IMPORTANT]
> Please note that the commands are NOT case sensitive!

### `help` - Display help information

To display a list of all the commands you can use, you can type `help`

**Example of usage:**

`help`

**Expected outcome:**

```
Here are the commands you can use:
1. todo - add a todo task
2. deadline - add a deadline task
3. event - add an event task
4. list - list all tasks
5. mark - mark a task as done
6. unmark - unmark a task as done
7. delete - delete a task
8. find - find tasks with a keyword
9. help - display help information
10. bye - exit the program
```

### `todo` - Add a to-do task
To add a todo task to your task list, you can type `todo [TASK_NAME]`

**Example of usage:**

`todo do the laundry`

**Expected outcome:**

```
task added: [T][ ] do the laundry 
```

---
### `deadline` - Add a deadline task

To add a task with a deadline to your task list, you can type `deadline [TASK_NAME] /by [DEADLINE_DATE]`

> [!IMPORTANT]
> Please note that the format for the `[DEADLINE_DATE]` is **'YYYY-MM-DD'**

**Example of usage:**

`deadline file taxes /by 2024-04-15`

**Expected outcome:**

```
task added: [D][ ] file taxes (by: Apr 15 2024) 
```

---
### `event` - Add an event task

To add an event task with a start and end time to your task list, you can type `event [TASK_NAME] /at [START_TIME]-[END_TIME]`


**Example of usage:**

`event family reunion /at 1800-2300`

**Expected outcome:**

```
task added: [E][ ] family reunion (from: 1800 to: 2300)
```

---
### `list` - View all tasks

To view all the tasks you have added so far, you can type `list`

**Example of usage:**

`list`

**Expected outcome:**

```
Here are the tasks in your list:
1.[T][ ] do the laundry 
2.[D][ ] file taxes (by: Apr 15 2024)
3.[E][ ] family reunion (from: 1800 to: 2300)
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
[T][X] do the laundry 
```
---
### `unmark` - "Unmark" a task as not done

To mark a task as not done, you can type `unmark [TASK_INDEX]`

> [!IMPORTANT]
> Please note that the `[TASK_INDEX]` is **1-based**.

**Example of

 usage:**

`unmark 1`

**Expected outcome:**

```
OK, I've marked this task as not done yet:
[T][ ] do the laundry 
```
---
### `delete` - Delete a task

To delete a task, you can type `delete [TASK_INDEX]`

> [!IMPORTANT]
> Please note that the `[TASK_INDEX]` is **1-based**.

**Example of usage:**

`delete 3`

**Expected outcome:**

```
Noted, I'm removing this task:
[E][ ] family reunion (from: Jul 20 2024 to Jul 22 2024)
Now you have 2 tasks in the list.
```
Martin will also let you know how many tasks you have in the list after deleting this task.

---
### `find` - Find a task

To find a task, you can type `find [WORDS]`

`[WORDS]` refers to any words contained within the task, and it is case-sensitive.

**Example of usage:**

`find laundry`

**Expected outcome:**

```
Good news sir! We've found the tasks in your list:
1. [T][ ] do the laundry 
```
> [!TIP] 
> You may enter more than one word to conduct a more specific search for tasks that contain **ALL** the keywords.

---
### `bye` - Exit the bot

Going so soon?

To exit the bot, you can type `bye`

**Example of usage:**

`bye`

**Expected outcome:**

```
Bye from Martin
```