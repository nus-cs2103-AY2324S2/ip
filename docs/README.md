# Dude User Guide

Dude is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the
benefits of a Graphical User Interface (GUI). If you can type fast, Dude can get your task management done faster than
traditional GUI apps.

## Table of Contents

- [Quick Start](#quick-start)
- [Features](#features)
    - [Adding Todos](#adding-todos)
    - [Adding Deadlines](#adding-deadlines)
    - [Adding Events](#adding-events)
    - [Marking and Unmarking Tasks](#marking-and-unmarking-tasks)
    - [Deleting Tasks](#deleting-tasks)
    - [Listing Tasks](#listing-tasks)
    - [Finding Tasks](#finding-tasks)
    - [Exiting the Program](#exiting-the-program)

---

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `dude.jar`
3. Copy the file to the folder you want to use as the home folder for Dude.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.

![Dude GUI](Ui.png)

---

## Features

Dude is a task management app that allows you to manage your tasks. It supports the following features:

* Adding and [removing](#deleting-tasks) Tasks like [Todos](#adding-todos), [Deadlines](#adding-deadlines)
  and [Events](#adding-events)
* [Marking](#marking-tasks) and [unmarking](#unmarking-tasks) Tasks as completed
* [Listing](#listing-tasks) all Tasks
* [Searching](#finding-tasks) of Tasks by keyword

There are also some features that Dude does [under the hood](#under-the-hood) to make your experience better.

## Adding Todos

You can add a todo task to the list of tasks.

### Format

The format of the command is `todo <description>`

### Usage

Example: `todo read book`

Result:

```
Got it. I've added this task:
    [T][ ] read book
Now you have 1 task in the list.
```

## Adding deadlines

You can add a task with a deadline to Dude with a specific deadline date.

### Format

The format of the command is `deadline <description> /by <date>`.
The `<date>` should be in the format `dd/mm/yyyy` or `dd/mm/yy h:t`.

### Usage

Example: `deadline return book /by 02/12/2019 18:00`
Result:

```
Got it. I've added this task:
    [D][ ] return book (by: 02 Dec 2019 @ 6:00PM)
Now you have 2 tasks in the list.
```

## Adding events

You can add events to Dude with a specific start time and end time.

### Format

The format of the command is `event <description> /from <date> /to <date>`.
The `<date>` should be in the format `dd/mm/yyyy` or `dd/mm/yy h:t`.

### Usage

Example: `event recess week /from 26/02/2023 12:00 /to 03/03/2023 12:00`
Result:
```
Got it. I've added this task:
    [E][ ] recess week (from: 26 Feb 2023 @ 12:00PM to: 03 Mar 2023 @ 12:00PM)
Now you have 3 tasks in the list.
```

--- 

## Marking and Unmarking Tasks

Dude also provides a task management feature. You can mark tasks as completed or unmark them as incomplete using the
following commands.

### Marking Tasks

To mark a task as completed, use the following command:

Format: `mark <id>`

Replace `<id>` with the ID of the task you want to mark as completed.

### Unmarking Tasks

To unmark a completed task as incomplete, use the following command:

`unmark <id>`

Replace `<id>` with the ID of the task you want to mark as incomplete.

### Usage

Example: `mark 1`
Output:

```
Nice! I've marked this task as done:
    [T][X] read book
```

Example: `unmark 1`
Output:

```
Nice! I've marked this task as incomplete:
    [T][ ] read book
```

---

## Deleting Tasks

You may wish to delete tasks that are no longer relevant. You can do so using the following command.

### Format

The format of the command is `delete <id>`
Replace `<id>` with the ID of the task you want to delete.

### Usage

Example: `delete 1`
Output:

```
Noted. I've removed this task:
    [T][X] read book
Now you have 2 tasks in the list.
```

---

## Listing Tasks

You can view all the tasks in the list using the `list` command.

### Usage

Example: `list`

Output:

```
Here are the tasks in your list:
    1. [D][ ] return book (by: 02 Dec 2019 @ 6:00PM)
    2. [E][ ] recess week (from: 26 Feb 2023 @ 12:00PM to: 03 Mar 2023 @ 12:00PM)
```

---

## Finding Tasks

You can search for tasks that contain a specific keyword using the `find` command.

### Format

The format of the command is `find <keyword>`
Replace `<keyword>` with the keyword you want to search for.

### Usage

Example: `find book`
Output:

```
Here are the matching tasks in your list:
    1. [D][ ] return book (by: 02 Dec 2019 @ 6:00PM)
```

---

## Exiting the Program

Apart from closing the GUI window, you can also exit the program using the `bye` command.

### Usage

Example: `bye`

Output:
`*The Program exits.*`

---

## Under the Hood

### Data Storage

Dude stores your tasks in a local file, so you can access your tasks even after you close the program. The file is
located at `/data/tasks.ser` relative to `dude.jar`.

### Sorting

Dude automatically sorts your tasks by their `deadline`/`start` date and then their type. Todos are sorted first,
followed by `deadlines` and `events` in chronological order.

### Error Handling

Dude has a robust error handling system. It will prompt you with an error message if you enter an invalid command or if
the command is missing a required parameter.

### Help Command

You can access a rudimentary user guide by typing `help` in the command line. You can also get help for a specific
command by typing `help <command>`.

For example, `help deadline` will provide you with the usage and format of the `deadline` command.