# Fredricksen

Welcome to Fredricksen's (Chat Bot) User Guide! This Chat Bot allows you to manage your tasks through a To-Do list.
It makes use of a few key commands, a Command Line Interface, coupled with a nice Graphical User Interface to enhance user's experience.

## Content Page
1. [Getting Started](#1-getting-started)
2. [List of Commands](#2-list-of-commands)
3. [Handling of Errors/Invalid input format](#3-handling-of-errorsinvalid-input-format)
4. [Storage](#4-storage)
5. [Command Summary](#5-command-summary)

## 1. Getting Started
1. Ensure that you have installed [java 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html) locally on your device.
2. Download the latest version of Fredricksen Chat Bot [here.]()
3. Add this to a folder of your choice.
4. Using the command terminal on your device, go into the directory with the JAR file that you have downloaded in step 2.
5. Run the command ``` java -jar Fredricksen.jar ```
6. A window will pop up and you can use your chat bot through the input text field box!

## 2. List of Commands

### List of available commands
**Add tasks commands**
1. [ToDo](#todo)
2. [Deadline](#deadline)
3. [Event](#event)

**Task list commands**
1. [List](#list)
2. [Mark](#mark)
3. [Unmark](#unmark)
4. [Delete](#delete)
5. [Find](#find)

**Utility command**
1. [Help](#help)
2. [Bye](#bye)

## ToDo
Usage: todo _TASK-DESCRIPTION_

**What it does**
Adds a todo task to the current list of tasks. Description should highlight the task to be completed.

**Example Usage**

``` todo car wash ```


## Deadline
Usage: deadline _TASK-DESCRIPTION_ /by _TASK_COMPLETED_BY_

**What it does**

Adds a deadline task to the current list of tasks. Description should highlight the task to be completed.

**_Important_**

_TASK_COMPLETED_BY_ should be in Date or DateTime format.

Example: 

19/2/2024 1900 to represent Feb 19, 2024 7pm.

19/2/2024 to represent Feb 19, 2024.

**Example Usage**

``` deadline project /by 19/2/2024 1900 ```

``` deadline project /by 19/2/2024 ```

## Event

Usage: event _TASK-DESCRIPTION_ /from _TASK_START_DATE_ /to _TASK_END_DATE_

**What it does**

Adds an event task to the current list of tasks. Description should highlight the task to be completed.

**_Important_**

_TASK_START_DATE_ & _TASK_END_DATE_ should be in Date or DateTime format.

Example:

19/2/2024 1900 to represent Feb 19, 2024 7pm.

19/2/2024 to represent Feb 19, 2024.

**Example Usage**

``` event project /from 18/2/2024 1900 /to 19/2/2024 ```

``` event project /from 18/2/2024 /to 19/2/2024 2000 ```

``` event project /from 18/2/2024 1900 /to 19/2/2024 2000 ```

## List
Usage: list

**What it does**

List out all the current tasks in the list of tasks.

**Example Usage**

``` list ```

## Mark
Usage: 
1. mark _INDEX_IN_LIST_
2. mark all

**What it does**

Usage 1 marks the task at that index in the list as done.

Usage 2 marks all the current tasks in the list as done.

**Example Usage**

``` mark 3 ```

``` mark all ```

## Unmark
Usage:
1. unmark _INDEX_IN_LIST_
2. unmark all

**What it does**

Usage 1 unmarks the task at that index in the list as undone.

Usage 2 unmarks all the current tasks in the list as undone.

**Example Usage**

``` unmark 3 ```

``` unmark all ```

## Delete
Usage:
1. delete _INDEX_IN_LIST_
2. delete all

**What it does**

Usage 1 deletes the task at that index in the list from the list.

Usage 2 deletes all the current tasks in the list from the list.

**Example Usage**

``` delete 3 ```

``` delete all ```

## Find
Usage: find _KEYWORD_

**What it does**

It displays a list of all the tasks from the current list of tasks that matches the keyword provided.

**Example Usage**

``` find project ```

## Help
Usage: help

**What it does**

It displays all the available chat bot commands and the usage of it.

**Example Usage**

``` help ```

## Bye
Usage: bye

**What it does**

It displays a bye message and waits 1.3 secs before closing the window application

**Example Usage**

``` bye ```

## 3. Handling of Errors/Invalid input format

For error handling, note that the chat bot will display a message telling you what went wrong and tell you what needs to be correctly formatted.

## 4. Storage

The task List will save all tasks that are successfully added. It will also remember the previous tasks added (if any) when closing and starting up the application again.

**Important**

Please note that only bye commands are allowed to be used when closing the application window. Please do not violate this rule.

# 5. Command Summary
| Commands | Usage                                                                | Example                                                                                                                 |
|----------|----------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------|
| todo     | todo _TASK-DESCRIPTION_                                              | ``` todo car wash ```                                                                                                   |
| deadline | deadline _TASK-DESCRIPTION_ /by _TASK_COMPLETED_BY_                  | ``` deadline project /by 19/2/2024 1900 ``` <br/> ``` deadline project /by 19/2/2024 ```                                |   
| event    | event _TASK-DESCRIPTION_ /from _TASK_START_DATE_ /to _TASK_END_DATE_ | ``` event project /from 18/2/2024 1900 /to 19/2/2024 ``` <br/> ``` event project /from 18/2/2024 /to 19/2/2024 2000 ``` |
| list     | list                                                                 | ``` todo car wash ```                                                                                                   |
| mark     | mark _INDEX_IN_LIST_<br/> mark all                                   | ``` mark 2 ``` <br/> ``` mark all ```                                                                                   |   
| unmark   | unmark _INDEX_IN_LIST_<br/> unmark all                               | ``` unmark 2 ``` <br/> ``` unmark all ```                                                                               |
| delete   | delete _INDEX_IN_LIST_<br/> delete all                               | ``` delete 2 ``` <br/> ``` delete all ```                                                                               |
| find     | find _KEYWORD_                                                       | ``` find car ```                                                                                                        |   
| help     | help                                                                 | ``` help ```                                                                                                            |
| bye      | bye                                                                  | ``` bye ```                                                                                                             |

