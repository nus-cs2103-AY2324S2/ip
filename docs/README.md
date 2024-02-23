# ATSISBot User Guide

Welcome to ATSISBot, your tool for managing tasks with ease and efficiency. This guide will help you navigate through ATSISBot's functionalities, ensuring a smooth experience as you organize your tasks.

## Table of Contents

- [Getting Started](#getting-started)
- [Functionalities](#functionalities)
    - [Creating a Todo Task](#creating-a-todo-task)
    - [Setting a Deadline Task](#setting-a-deadline-task)
    - [Scheduling an Event](#scheduling-an-event)
    - [Displaying Tasks](#displaying-tasks)
    - [Completing a Task](#completing-a-task)
    - [Reverting a Task to Incomplete](#reverting-a-task-to-incomplete)
    - [Removing a Task](#removing-a-task)
    - [Searching for Tasks](#searching-for-tasks)
    - [Setting priority for a Task](#setting-priority-for-a-task)
    - [Organizing Tasks](#organizing-tasks)
    - [Exiting the Application](#exiting-the-application)
    - [Automatic Data Saving](#automatic-data-saving)
- [FAQ](#faq)
- [Troubleshooting](#troubleshooting)
- [Summary of Commands](#summary-of-commands)

## Getting Started

Follow these steps to start using ATSISBot:

1. Ensure Java `11` or later is installed on your computer.
2. Download the latest `ATSISBot.jar` from the [releases page](https://github.com/purivirakarin/ip/releases/tag/Level-10).
3. Place the jar file in a new folder.
4. Open a terminal, navigate to the folder, and execute `java -jar ATSISBot.jar`. The ATSISBot GUI will appear shortly.
5. Input commands in the chat box and press send to use ATSISBot.

![ATSISBot GUI](./Ui.png)

## Functionalities

### Creating a Todo Task
- **Command**: `todo [TASK_DESCRIPTION]`
- **Example**: `todo Read a book`

### Setting a Deadline Task
- **Command**: `deadline TASK_DESCRIPTION /by DD/MM/YYYY HH:MM`
- **Example**: `deadline assignment /by 12/12/2023 23:59`

### Scheduling an Event
- **Command**: `event TASK_DESCRIPTION /from DD/MM/YYYY HH:MM /to DD/MM/YYYY HH:MM`
- **Example**: `event meeting /from 12/12/2023 23:59 /to 13/12/2023 23:59`

### Displaying Tasks
- **Command**: `list`

### Completing a Task
- **Command**: `mark INDEX`
- **Example**: `mark 1`

### Reverting a Task to Incomplete
- **Command**: `unmark INDEX`
- **Example**: `unmark 2`

### Removing a Task
- **Command**: `delete INDEX`
- **Example**: `delete 3`

### Setting priority for a Task
- **Command**: `set_priority INDEX PRIORITY`
- **Example**: `set_priority 3 high`

### Searching for Tasks
- **Command**: `find KEYWORD`
- **Example**: `find book`

### Exiting the Application
- **Command**: `bye`

### Automatic Data Saving
Data is saved automatically after any command that alters it.

## FAQ

**Q: How do I transfer my data to another computer?**
A: Move the `data/task.txt` file to the ATSISBot folder on the new computer after installing the jar file.

## Troubleshooting

1. If the GUI doesn't close after `bye`, manually exit the window.

## Summary of Commands

| Command              | Format & Examples                                                                                  |
|----------------------|----------------------------------------------------------------------------------------------------|
| Add Todo             | `todo [TASK]` e.g., `todo Read a book`                                                             |
| Add Deadline         | `deadline TASK /by DATE&TIME` e.g., `deadline assignment /by 12/12/2023 23:59`                     |
| Add Event            | `event TASK /from START /to END` e.g., `event meeting /from 12/12/2023 23:59 /to 13/12/2023 23:59` |
| List Tasks           | `list`                                                                                             |
| Mark Task Complete   | `mark INDEX` e.g., `mark 1`                                                                        |
| Mark Task Incomplete | `unmark INDEX` e.g., `unmark 2`                                                                    |
| Delete Task          | `delete INDEX` e.g., `delete 3`                                                                    |
| Find Task            | `find KEYWORD` e.g., `find book`                                                                   |
| Setting a Priority   | `set_priority INDEX PRIORITY` e.g., `set_priority 3 high`                                          |
| Exit                 | `bye`                                                                                              |

Enjoy using ATSISBot to streamline your task management!
