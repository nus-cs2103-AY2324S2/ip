# Iggly User Guide

<img src="../src/main/resources/images/Icon.png" alt="Icon" width="150">

> This is Iggly, your personal assistant chatbot! 🐧

<img src="Ui.png" alt="Screenshot">
Colour scheme: #CFDDD9, #B7CDC7, #F3EEC3, #FFC099

## How to use Iggly?

1. Download Iggly [here](https://github.com/yiwern5/ip/releases/download/v0.2/Iggly.jar).
2. Navigate to your Downloads folder using the terminal.
3. Type `java -jar Iggly.jar` in your terminal.
4. Press enter.
5. Give it a try now!

## Adding todo: `todo`

Adds a todo task in your task list.

Example: `todo Laundry`

Expected output:

```
Got it! I've added this task:
   [T][ ] Laundry
   Now you have 1 tasks in the list. 🐧
```

## Adding event: `event`

Adds an event task in your task list.

Example: `event Pizza party /from 17-02-2024 1800 /to 2000`

Expected output:

```
Got it! I've added this task:
   [E][ ] Pizza party (on: 17 Feb 2024 6:00PM to 8:00PM)
   Now you have 2 tasks in the list. 🐧
```

## Adding deadline: `deadline`

Adds a deadline task in your task list.

Example: `deadline iP submission /by 23-02-2024 2359`

Expected output:

```
Got it! I've added this task:
   [D][ ] iP submission (on: 23 Feb 2024 11:59PM, FRIDAY)
   Now you have 3 tasks in the list. 🐧
```

## Adding schedule: `schedule`

Adds a schedule task in your task list.

Example: `schedule Orientation camp /from 18-02-2024 /to 22-02-2024`

Expected output:

```
Got it! I've added this task:
   [S][ ] Orientation camp (from: 18 Feb 2024, SUNDAY to: 22 Feb 2024, THURSDAY)
   Now you have 4 tasks in the list. 🐧
```

## Display task list: `list`

Displays all the tasks in your task list.

Example: `list`

Expected output:

```
🐧 Here are the tasks in your task list:
   1. [T][ ] Laundry
   2. [E][ ] Pizza party (on: 17 Feb 2024 6:00PM to 8:00PM)
   3. [D][ ] iP submission (on: 23 Feb 2024 11:59PM, FRIDAY)
   4. [S][ ] Orientation camp (from: 18 Feb 2024, SUNDAY to: 22 Feb 2024, THURSDAY)
```

## Mark a task: `mark`

Marks a task as done by specifying the task index.
- The index refers to the index number shown in the displayed task list.
- The index **MUST be a positive integer** 1, 2, 3, ...

Example: `mark 1`

Expected output:

```
🐧 Nice! I've marked this task as done:
   [T][X] Laundry
```

## Unmark a task: `unmark`

Marks a task as not done by specifying the task index.
- The index refers to the index number shown in the displayed task list.
- The index **MUST be a positive integer** 1, 2, 3, ...

Example: `unmark 1`

Expected output:

```
🐧 OK, I've marked this task as not done yet:
   [T][ ] Laundry
```

## Delete a task: `delete`

Deletes a task by specifying the task index.
- The index refers to the index number shown in the displayed task list.
- The index **MUST be a positive integer** 1, 2, 3, ...

Example: `delete 1`

Expected output:

```
Noted. I've removed this task:
   [T][ ] Laundry
   Now you have 3 tasks in the list. 🐧
```

## Find: `find`

Finds a task using keyword.
- The search is case-insensitive. (e.g. `pizza` will match `Pizza`)
- Only the task title is searched.

Example: `find pizza`

Expected output:

```
🐧 Here are the matching tasks in your list:
   1. [E][ ] Pizza party (on: 17 Feb 2024 6:00PM to 8:00PM)
```

## Bye: `bye`

Exit the program. The window will be close automatically.

## Saving the data

Iggly's data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Command summary

| Action   | Format, Examples                                                                                                                       |
|----------|----------------------------------------------------------------------------------------------------------------------------------------|
| Todo     | `todo <todo title>` <br/> e.g. `todo Laundry`                                                                                          |
| Event    | `event <event title> /from <dd-mm-yyyy HHmm> /to <HHmm>`<br/> e.g. `event Pizza party /from 17-02-2024 1800 /to 2000`                  |
| Deadline | `deadline <deadline title> /by <dd-mm-yyyy HHmm>` <br/> e.g. `deadline iP submission /by 23-02-2024 2359`                              |
| Schedule | `schedule <schedule title> /from <dd-mm-yyyy> /to <dd-mm-yyyy>` <br/> e.g. `schedule Orientation camp /from 18-02-2024 /to 22-02-2024` |
| List     | `list`                                                                                                                                 |
| Mark     | `mark <task number>` <br/> e.g. `mark 1`                                                                                               |
| Unmark   | `unmark <task number>` <br/> e.g. `unmark 1`                                                                                           |
| Delete   | `delete <task number>` <br/> e.g. `delete 1`                                                                                           |
| Find     | `find <keyword>` <br/> e.g. `find homework`                                                                                            |
| Bye      | `bye`                                                                                                                                  |
| Help     | `help`                                                                                                                                 |