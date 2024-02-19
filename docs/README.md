# Kaipybara Bot User Guide
```
          /'  _/                                /'
        /' _/~                                /'
     ,/'_/~  ____     O   ____              /'__     ____      ____     ____
    /\/~   /'    )  /'  /'    )--/'    /  /'    )  /'    )   )'    )--/'    )
  /'  \  /'    /' /'  /'    /' /'    /' /'    /' /'    /'  /'       /'    /'
/'     \(___,/(__(__/(___,/'  (___,/(__(___,/(__(___,/(__/'        (___,/(__
                  /'             /'
                /'       /     /'
              /'        (___,/'
```
> Your Best Bot and Friend! 

// Product screenshot goes here

# About Me
I am a bot that helps you organise your tasks and boost your productivity so that you can focus on the more important things in life, such as watching your favourite TV show. I can:
- Able to read your text inputs.
- Execute commands to help you organise your life.
- Keep it saved locally once you leave the program.

## Quick Start
All you need to do is to [download](https://github.com/c-wenlong/ip) me and run the `Launcher.java` file! :shit: And your life will become so much __*more organised*__.

1. No longer worried about forgetting deadlines.
2. No longer missing your dates with your potential partners.
3. No longer missing your meds.

**Ensure you have Java 11 or above installed in your Computer.**

## Features

### Viewing Data

>#### List
Returns a list of tasks in the order when the task was added.

Example: `list`

```
Here are the tasks in your list:
1.[T][X] homework Tags:[]
2.[D][X] more homework (by: Feb-19-2024) Tags:[]
3.[E][ ] hackathon (from: Feb-20-2024 to: Feb-22-2024) Tags:[important, urgent, resume]
4.[T][ ] finish coding for hackathon Tags:[urgent]
```
---
>#### Find

Returns a list of tasks in the order when the task was added matching a given key.

Example: `find homework`

```
Here are the matching tasks in your list:
1.[T][X] homework Tags:[]
```
---
### Editing Data
>#### Mark

Marks the task at index to be completed.

Example: `mark 1`
```
Nice! I've marked this task as done:
[T][X] homework Tags:[]
```
---
>#### Unmark

Unmarks the task at index to be completed.

Example: `unmark 1`

```
Nice! I've marked this task as not done yet:
[T][ ] homework Tags:[]
```
---
>#### Delete

Deletes task at index.

Example: `delete 1`
```
OK, I've deleted this task:
[T][ ] homework Tags:[]
Now you have 2 tasks in this list!
```
---
### Adding Tasks
>#### Todo

Adds a todo task with its name as the parameter.

Example: `todo homework`
```
Got it. I've added this task: 
[T][ ] homework Tags:[]
Now you have 2 tasks in this list!
```
---
>#### Deadline

Adds a deadline task with its name and deadline (date) as the parameter.

Example: `deadline homework`
```
Got it. I've added this task: 
[D][X] more homework (by: Feb-19-2024) Tags:[]
Now you have 3 tasks in this list!
```
OR

Example: `deadline homework /tags urgent important`
```
Got it. I've added this task: 
[D][X] more homework (by: Feb-19-2024) Tags:[urgent, important]
Now you have 3 tasks in this list!
```
---
>#### Event

Adds a event task with its name, from (start date) and to (end date) as the parameter.

Example: `event hackathon /from 20-2-24 /to 22-2-24`
```
Got it. I've added this task: 
[E][ ] hackathon (from: Feb-20-2024 to: Feb-22-2024) Tags:[]
Now you have 4 tasks in this list!
```
OR

Example: `event hackathon /from 20-2-24 /to 22-2-24 /tags urgent important resume`
```
Got it. I've added this task: 
[E][ ] hackathon (from: Feb-20-2024 to: Feb-22-2024) Tags:[urgent, important, resume]
Now you have 4 tasks in this list!
```
---
### Others
>#### Help
Lists all available commands. 

Example: `help`
```
Active commands:

VIEWING
"list: lists all tasks saved. (Example: list)
"help: lists all available commands. (Example: help)
"find <String:key>: lists all tasks matching given key. (Example: find homework)"

EDITING
mark <int:index>: <index>: marks the task at index to be completed. (Example: mark 1)
unmark <int:index>: marks the task at index to be not completed. (Example: unmark 1)
delete <int:index>: deletes task at index. (Example: delete 1)
 
ADD TASK
todo <String:name>: adds a todo task with its name. (Example: todo homework)
deadline <String:name> /by <date>: adds a deadline task with name and date in d-M-yy format. (Example: deadline homework /by 19-2-24)
event <String:name> /from <date> /to <date>: adds an event task with name and date in d-M-yy format. (Example: event career fair /from 20-2-24 /to 22-2-24)
```
---
>#### Bye
Exits the program.

Example: `bye`

---

## Frequently Asked Questions (FAQ)

#### How do I add a task to the list?
To add a task, you can use the following commands:
- For a todo task: `todo <name>`
- For a deadline task: `deadline <name> /by <date>`
- For an event task: `event <name> /from <start_date> /to <end_date>`

#### How do I mark a task as completed?
To mark a task as completed, use the `mark <index>` command followed by the index of the task you want to mark.

#### How do I delete a task from the list?
To delete a task from the list, use the `delete <index>` command followed by the index of the task you want to delete.

#### How do I search for tasks containing a specific keyword?
To search for tasks containing a specific keyword, use the `find <keyword>` command followed by the keyword you want to search for.

#### How do I exit the program?
To exit the program, simply type `bye` and press Enter.

---

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
| `deadline <name> /by <date>` | Adds a deadline task with name and date in `d-M-yy` format. | `deadline homework /by 19-2-24` |
| `event <name> /from <date> /to <date>` | Adds an event task with name and date in `d-M-yy` format. | `event career fair /from 20-2-24 /to 22-2-24` |
| `bye`             | Exits the program.                            | `bye`                                   |

---