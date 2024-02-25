# RahBot

 
> Your Best Bot and Friend! 

![Product Image](./Ui.png)

1. [About Me](#about-me)
2. [Quick Start](#quick-start)
3. [Features](#features)
    - [Viewing Data](#viewing-data)
    - [Editing Data](#editing-data)
    - [Adding Tasks](#adding-tasks)
    - [Others](#others)
4. [FAQ](#faq)
5. [Command Summary](#command-summary)

<a id="about-me"></a>

# About Me
I am a bot that helps you organise your tasks and boost your productivity so that you can focus on the more important things in life, such as watching your favourite TV show. I can:
- Able to read your text inputs.
- Execute commands to help you keep your tasks organised.
- Keep it saved locally once you leave the program.

<a id="quick-start"></a>

## Quick Start
All you need to do is to [download](https://github.com/rahhulleee/ip) me and run the `Launcher.java` file! :shit: And your life will become so much __*more organised*__.

1. No more forgetting deadlines.
2. No longer missing your meds.
3. No more missing outings with your friends

**Ensure you have Java 11 or above installed in your Computer.**

<a id="features"></a>

## Features

<a id="viewing-data"></a>

### Viewing Data

>#### List
Returns a list of tasks in the order when the task was added.

Example: `list`

```
1.[T][X] homework
2.[D][X] more homework (by: 1/1/2023 1900hrs)
3.[E][ ] hackathon (from: 20/2/2024 1000 hrs to: 22/2/2024 1900hrs)
```
---
>#### Find

Returns a list of tasks in the order when the task was added matching a given key.

Example: `find math`

```
Here are the matching tasks in your list!:
1.[T][X] Math Assignment
```
---

<a id="editing-data"></a>

### Editing Data
>#### Mark

Marks the task at index to be completed.

Example: `mark 1`
```
Nice! I've marked this task as done:
[T][X] homework
```
---
>#### Unmark

Unmarks the task at index to be completed.

Example: `unmark 1`

```
Ok, I've marked this task as not done yet:
[T][ ] homework
```
---
>#### Delete

Deletes task at index.

Example: `delete 1`
```
Noted, I've removed this task:
[T][ ] homework
Now you have 0 tasks in this list!
```
---

<a id="adding-tasks"></a>

### Adding Tasks
>#### Todo

Adds a todo task with its name as the parameter.

Example: `todo homework`
```
Got it. I've added this task: 
[T][ ] homework
Now you have 2 tasks in this list.
```
---
>#### Deadline

Adds a deadline task with its name and deadline (date) as the parameter.

Example: `deadline homework`
```
Got it. I've added this task: 
[D][X] more homework (by: 19/2/2024 1900hrs)
Now you have 3 tasks in this list!
```
---
>#### Event

Adds a event task with its name, from (start datetime) and to (end datetime) as the parameter.

Example: `event hackathon /from 2024-02-01 1000 /to 2024-02-02 1900`
```
Got it. I've added this task: 
[E][ ] hackathon (from: 1/2/2024 1000hrs to: 2/2/2024 1900hrs)
Now you have 4 tasks in this list!
```
---

<a id="others"></a>

### Others
>#### Help
Lists all available commands. 

Example: `help`
```
Please input all time in 24hrs format and date in YYYY-MM-DD.
Here are the list of commands and formats:
1) todo <EVENT>
2) deadline <EVENT> /by <DATE> <TIME>
3) event EVENT /from <DATE> <TIME> /to <DATE> <TIME>
4) mark <INDEX>
5) unmark <INDEX>
6) delete <INDEX>
7) list
8) sort
9) find <KEYWORD>
```
---
>#### Bye
Exits the program.

Example: `bye`

---

<a id="faq"></a>

## Frequently Asked Questions (FAQ)

#### How do I add a task to the list?
To add a task, you can use the following commands:
- For a todo task: `todo <name>`
- For a deadline task: `deadline <name> /by <date> <time>`
- For an event task: `event <name> /from <start_date> <time> /to <end_date> <time>`

#### How do I mark a task as completed?
To mark a task as completed, use the `mark <index>` command followed by the index of the task you want to mark.

#### How do I delete a task from the list?
To delete a task from the list, use the `delete <index>` command followed by the index of the task you want to delete.

#### How do I search for tasks containing a specific keyword?
To search for tasks containing a specific keyword, use the `find <keyword>` command followed by the keyword you want to search for.

#### How do I exit the program?
To exit the program, simply type `bye` and press Enter.

---

<a id="command-summary"></a>

## Command Summary

| Command           | Description                                    | Example                                 |
|-------------------|------------------------------------------------|-----------------------------------------|
| `list`            | Lists all tasks saved.                         | `list`                                  |
| `help`            | Lists all available commands.                  | `help`                                  |
| `find <key>`      | Lists all tasks matching given key.            | `find homework`                         |
| `mark <index>`    | Marks the task at index to be completed.      | `mark 1`                                |
| `unmark <index>`  | Marks the task at index to be not completed.  | `unmark 1`                              |
| `delete <index>`  | Deletes task at index.                        | `delete 1`                              |
| `todo <name>`     | Adds a todo task with its name.               | `todo homework`                         |
| `deadline <name> /by <date> <time>` | Adds a deadline task with name and date in `yyyy-mm-dd` format. | `deadline homework /by 2024-02-01 1900` |
| `event <name> /from <date> <time> /to <date> <time>` | Adds an event task with name and date in `yyyy-mm-dd` format. | `event career fair /from 2024-01-01 1900 /to 2024-01-02 1000` |
| `bye`             | Exits the program.                            | `bye`                                   |

