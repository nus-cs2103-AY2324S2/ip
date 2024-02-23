# Sleepy User Guide

![Ui.png](./docs/Ui.png)

Sleepy is an **automated chatbot which helps you store your tasks - todos, deadlines, events
and plans**! It is **optimised for use for a Command Line Interface** (CLI) but still has a
basic Graphical User Interface (GUI).

<!-- TOC -->
* [Sleepy User Guide](#sleepy-user-guide)
* [Quick Start](#quick-start)
* [User commands](#user-commands)
  * [Notes about the command format](#notes-about-the-command-format)
  * [Adding a todo: `todo`](#adding-a-todo--todo)
  * [Adding a deadline: `deadline`](#adding-a-deadline--deadline)
  * [Adding a plan: `plan`](#adding-a-plan--plan)
  * [Adding an event: `event`](#adding-an-event--event)
  * [Marking a task: `mark`](#marking-a-task--mark)
  * [Unmarking a task `unmark`](#unmarking-a-task-unmark)
  * [Deleting a task: `delete`](#deleting-a-task--delete)
  * [Finding task(s) by keyword: `find`](#finding-task--s--by-keyword--find)
  * [Listing all your tasks: `list`](#listing-all-your-tasks--list)
  * [Exiting the chatbot: `bye`](#exiting-the-chatbot--bye)
* [Features](#features)
<!-- TOC -->

# Quick Start
1. Ensure that you have Java `11` or above installed on your computer.
2. Download the latest sleepy.jar from [here
   ](https://github.com/kjw142857/ip/releases).
3. Copy the file to the folder you want to use as the _home folder_ for Sleepy.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the java -jar sleepy.jar command to run the application.
   A GUI similar to the image at the top of this page should appear within seconds.
5. Type your command in the input box at the bottom of the screen. You can press Enter or
   click the `SEND HELP PLS` button to send your message.
   Some example commands you can try (not case-sensitive):
    * `list`: Lists all your tasks.
    * `todo sleep`: Adds a reminder for you to get some sleep.
    * `delete 7`: Deletes the 7th item in your task list.
    * `bye`: Exits the app after a short delay.
6. Refer to the [User commands](#user-commands) below for details of each command.

# User commands


## Notes about the command format

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which
  can be used, such as `todo sleep`.

* Parameters **must** be in the specified order.<br>
  e.g. if the command specifies `event DESCRIPTION /from START_TIME /to END_TIME`,
  `event DESCRIPTION /to END_TIME /from START_TIME` is not recognised.

* For adding of tasks with one or more types of `TIME` (i.e. Plans, Deadline and Events), if
  any `TIME` is in `YYYY-MM-DD` format, it will automatically be converted for you
  to `DD-MMM-YYYY` format.
  For example, `event study /from 2023-01-26 /to next Wednesday` will be converted to
  `[E][ ] study (from: 26 Jan 2023 to: next Wednesday)`


## Adding a todo: `todo`

Adds a todo to the list.

Format: `todo DESCRIPTION`

Example: `todo not stay up until 3am`

Expected output:
```
added: [T][ ] not stay up until 3am
```

## Adding a deadline: `deadline`

Adds a deadline to the list.

Format: `deadline DESCRIPTION /by TIME`

Example: `deadline submit homework /by this friday`

Expected output:
```
added: [D][ ] submit homework (by: this friday)
```

## Adding a plan: `plan`

Adds a plan to the list.

Format: `plan DESCRIPTION /after TIME`

Example: `plan submit homework /after this friday`

Expected output:
```
added: [P][ ] submit homework (after: this friday)
```

## Adding an event: `event` 

Adds an event to the list.

Format: `event DESCRIPTION /from START_TIME /to END_TIME`

Example: `event sleep 2 hours /from 5am /to 7am`

Expected output:
```
added: [E][ ] sleep 2 hours (from: 5am to: 7am)
```

## Marking a task: `mark`

Marks a task in the list.

Format: `mark TASK_NUMBER`

Example: `mark 1`

Expected output:
```
Nice! I've marked this task as done:
[E][X] sleep 2 hours (from: 5am to: 7am)
```

## Unmarking a task `unmark`

Marks a task in the list.

Format: `unmark TASK_NUMBER`

Example: `unmark 1`

Expected output:
```
OK, I've marked this task as not done yet:
[E][ ] sleep 2 hours (from: 5am to: 7am)
```

## Deleting a task: `delete`

Deletes a task in the list.

Format: `delete TASK_NUMBER`

Example: `delete 1`

Expected output:
```
Noted. I've removed this task:
[E][ ] sleep 2 hours (from: 5am to: 7am)
Now you have 0 task(s) in the list.
```

## Finding task(s) by keyword: `find`

Finds all tasks whose description match a given user input.

Format: `find KEYWORDS`

Example: `find sleep`

Expected output (list has matches):
```
Here are the matching tasks in your list:
1. [E][ ] sleep 2 hours (from: 5am to: 7am)
2. [T][ ] get some SLEEP
```

Expected output (list has no match):
```
There are no matching tasks in your list!
*Yawn*
```

## Listing all your tasks: `list`

Lists all your tasks in numerical order.

Format: `list`

Expected output (list has tasks):
```
1. [D][ ] submit homework (by: this friday)
2. [T][ ] not stay up until 3am
```

Expected output (list is empty):
```
Your task list is empty! Looks like you can go back to sleep.
```

## Exiting the chatbot: `bye`

Exits Sleepy with a goodbye message, with a delay of around 2 seconds.

Format: `bye`

Expected output:
```
Bye. Gonna go back to sleep now *yawn*
```


# Features
* A dynamic GUI which supports **window resizing and fullscreen view**
* **Custom error messages** for invalid user commands
* **Automatic saving** of your task list on your computer, so that when you reopen Sleepy
  your data will be instantly retrieved!