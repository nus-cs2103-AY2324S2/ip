# **Coat** User Guide

Coat (the Catbot) is a chatbot application designed to help you manage your tasks efficiently. It combines the ease of a graphical user interface (GUI) with the speed of a command-line interface (CLI), making task management simple and quick.

## Quick start

1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest coat.jar file from the [releases page](https://github.com/amanzainal/ip/releases/).
3. Place the JAR file in the directory where you want to run Coat the Catbot.
4. Open a command terminal, navigate to the directory containing the JAR file, and run the command:
``` bash
   java -jar coat.jar
```
5. The Coat the Catbot GUI should open, allowing you to start managing your tasks immediately.

## Features

- **Viewing help**: `help`
- **Adding a task**: `todo`, `deadline`, `event`
- **Listing all tasks**: `list`
- **Marking a task as done**: `mark`
- **Marking a task as incomplete**: `unmark`
- **Deleting a task**: `delete`
- **Finding tasks by keyword**: `find`
- **Viewing tasks by date**: `viewbydate`
- **Exiting the program**: `bye`

## Command summary

Action | Format, Examples
------ | ----------------
Add | `todo TASK_DESCRIPTION`<br> `deadline TASK_DESCRIPTION /by DEADLINE`<br> `event TASK_DESCRIPTION /from START_DATETIME /to END_DATETIME`
List | `list`
Mark as done | `mark INDEX`
Mark as incomplete | `unmark INDEX`
Delete | `delete INDEX`
Find | `find KEYWORD`
View by date | `viewbydate DATE`
Exit | `bye`

## Usage

- To add a task, use the `todo`, `deadline`, or `event` commands followed by the task description and optional date/time details.
- To view all tasks, simply type `list`.
- To mark a task as done, use the `done` command followed by the task index.
- To delete a task, use the `delete` command followed by the task index.
- To find tasks containing a specific keyword, use the `find` command followed by the keyword.
- To view tasks scheduled for a specific date, use the `viewbydate` command followed by the date in YYYY-MM-DD format.
- To exit the program, type `bye`.

Coat the Catbot automatically saves your tasks after each command, so you never have to worry about losing your data.

## Known issues

- None at the moment.

## About Us

Coat the Catbot is developed by our team of passionate developers who are dedicated to creating useful and intuitive software solutions. We strive to continuously improve Coat the Catbot and welcome your feedback and suggestions.

```
