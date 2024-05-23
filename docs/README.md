# Checkbot User Guide

![Checkbot](./Ui.png)

Checkbot is a chatbot that lets you manage your tasks more easily!

## Adding todos: `todo`

Todos are simple tasks that just require you to tick off the checklist.

Example usage: `todo Mop floor`

A todo with the name "Mop floor" will be created and added into the task list.

Example response:

```
I have added this task to the list:
[T] [ ] Mop floor
You have now 1 task in the list.
```

## Adding deadlines: `deadline`

Deadlines are tasks that have a due date.

Example usage: `deadline CS3230 Assignment 1 /by 18 Feb 2024`

A deadline with the name "CS3230 Assignment 1" with the due date 18 Feb 2024 will be created and added to the task list.

Example response:

```
I have added this task to the list:
[D] [ ] CS3230 Assignment 1 (by: Feb 18 2024)
You have now 1 task in the list.
```

For date formatting, please refer [here](#date-formatting).

## Adding events: `event`

Events are self-explanatory – they are events that have a start and end date.

Example usage: `event JB trip /from 27 February 2024 /to 28 February 2024`

An event with the name "JB trip" from 27 Feb - 28 Feb 2024 will be created and added to the task list.

Example response:

```
I have added this task to the list:
[E] [ ] JB trip (from: Feb 27 2024 to: Feb 28 2024)
You have now 1 task in the list.
```

## Viewing your task list: `list`

You can simply display your task list by typing `list` as the command.

Example response:

```
Here is your todo list:
[T] [ ] CS2106 Tutorial
[D] [ ] CS3230 Assignment 1 (by: 18 Feb 2024)
```

## Marking/Unmarking tasks: `mark/unmark`

Marking and unmarking tasks are as simple as typing `mark` or `unmark` followed by the task number.

Example usage 1: `mark 2`

This marks the second task in the list as completed.

Example response 1:

```
Good job! I have marked this task as completed:
[D] [X] CS3230 Assignment 1 (by: 18 Feb 2024)
```

Example usage 2: `unmark 2`

This marks the second task in the list as incomplete.

Example response 2:

```
Alright, I have marked this task as incomplete:
[D] [ ] CS3230 Assignment 1 (by: 18 Feb 2024)
```

## Deleting tasks: `delete`

Similar to marking/unmarking your tasks, you are required to input a task number after the `delete` command.

Example usage: `delete 1`

Example response:

```
Alright, I deleted this task:
[T] [ ] CS2106 Tutorial
You have now 1 task in the list.
```

## Finding tasks: `find`

The `find` command allows you to search for tasks by their names (case-insensitive).

Example usage 1: `find cs`

Example response 1:

```
Here are the matching tasks in your list:
[T] [ ] CS2106 Tutorial
[D] [ ] CS3230 Assignment 1 (by: 18 Feb 2024)
```

Example usage 2: `find tutorial`

Example response 2:

```
Here are the matching tasks in your list:
[T] [ ] CS2106 Tutorial
```

## Opening the user guide: `help`

Typing `help` will open this page on your browser. You can also click on the message to open this page again. However,
if that is unsupported by your system, a different message will show and clicking on it will instead copy this link into
your clipboard.

Example usage: `help`

Example response 1:

```
Click here to open the user guide in your browser:
https://zhekaiii.github.io/ip/
```

Example response 2:

```
Looks like I am unable to open the user guide in your browser. Click here to copy the link to your clipboard instead.
https://zhekaiii.github.io/ip/
```

## Quitting Checkbot: `bye`

Typing `bye` will just exit the program.

## FAQ

**Q:** Does Checkbot save my tasks?
**A:** Yes, Checkbot saves your tasks into `tasks.txt` in the same folder as your `checkbot.jar` file. It updates the
file whenever you make changes to your task list.

**Q:** Can I sync my tasks across devices?
**A:** Unfortunately, cross device support is not supported at the moment. The only way of transferring tasks is if you
transfer over `tasks.txt` to your other device.

## Command summary

| Command  | Usage                                                                                        |
|----------|----------------------------------------------------------------------------------------------|
| todo     | `todo TASK NAME`<br/>e.g.`todo CS2106 Tutorial`                                              |
| deadline | `deadline TASK NAME /by DATE`<br/>e.g. `deadline CS3230 Assignment 1 /by 18/2/2024`          |
| event    | `event TASK NAME /from DATE /to DATE`<br/>e.g. `event JB trip /from 27/2/2024 /to 28/2/2024` |
| mark     | `mark INDEX`<br/>e.g.`mark 1`                                                                |
| unmark   | `unmark INDEX`<br/>e.g.`unmark 1`                                                            |
| delete   | `delete INDEX`<br/>e.g.`delete 1`                                                            |
| list     | `list`                                                                                       |
| find     | `find SUBSTRING`<br/>e.g.`find cs`, `find tutorial`, `find birthday`                         |
| help     | `help`                                                                                       |

## Date formatting

For deadlines and events, the `by`, `to` and `from` argument recognizes if you typed a valid date in several formats.
However, you are allowed to enter any text as the date if you wish to.

The following date formats are recognized:

* dd-MM-yyyy
    * 01-02-2024
    * 23-12-2024
* d-M-yyyy
    * 1-2-2024
    * 23-12-2024
* d/M/yyyy
    * 1/2/2024
* dd/MM/yyyy
    * 01/02/2024
* d MMM yyyy
    * 1 Feb 2024
* d MMMM yyyy
    * 1 February 2024
* dd MMM yyyy
    * 01 Feb 2024
* dd MMMM yyyy
    * 01 February 2024
* MMM d yyyy
    * Feb 1 2024
* MMMM d yyyy
    * February 1 2024
* MMM dd yyyy
    * Feb 01 2024
* MMMM dd yyyy
    * February 01 2024