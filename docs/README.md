# Seiki User Guide

![Ui.png](Ui.png)

Welcome! Seiki is here to help you track your tasks!
There are 3 types of tasks that you can track:
 - ToDo
 - Deadline
 - Events

## Adding todos : `todo`
Adds todo task to the task list.

Format: `todo [TASKTITLE]`

Example: `todo read this book`

```dtd
The following task has been added:
➔ [T] read book 
You have a total of 1 tasks in the list.
```

## Adding deadline : `deadline`
Adds deadline task to the task list.

Format: `deadline [TASKTITLE] /by [DATETIME]`

Parameter: `TASKTITLE`, `DATETIME` (must be `yyyy/MM/dd HHmm`)

Example: `deadline return book /by 2022/06/06 1409`

```dtd
The following task has been added:
→ [D] return book (by: 06 Jun 2019 1409)
You have a total of 2 tasks in the list.
```

## Adding events : `event`
Adds event task to the task list.

Format: `event [TASKTITLE] /from [STARTDATETIME] /to [ENDDATETIME]`

Parameter: `TASKTITLE`, `STARTDATETIME` (must be `yyyy/MM/dd HHmm`), `ENDDATETIME` (must be `yyyy/MM/dd HHmm`)

Example: `event project meeting /from 2020/08/06 1400 /to 2020/08/06 1600`

```dtd
The following task has been added:
→ [E] project meeting (from: 06 Aug 2020 1400 to: 06 Aug 2020 1600)
You have a total of 3 tasks in the list.
```

## List : `list`
Lists down all the stored tasks.

Format: `list`

Example: `list`

```dtd
Here are the task(s) you have:
→ 1. [T] read book
→ 2. [D] return book (by: 06 Jun 2019 1409)
→ 3. [E] project meeting (from: 06 Aug 2020 1400 to: 06 Aug 2020 1600)
```

## Marking tasks : `mark`
Mark a task identified by the task number.

Format: `mark [TASKNUMBER]`

Example: `mark 2`

```dtd
The following task has been marked:
→ [D] return book (by: 06 Jun 2019 1409) ✓
```

## Unmarking tasks : `unmark`
Unmark a task identified by the task number.

Format: `unmark [TASKNUMBER]`

Example: `unmark 2`

```dtd
The following task has been marked:
→ [D] return book (by: 06 Jun 2019 1409)
```

## Deleting tasks : `delete`
Delete a task identified by the task number.

Format: `delete [TASKNUMBER]`

Example: `delete 2`

```dtd
The following task has been deleted:
→ [D] return book (by: 06 Jun 2019 1409)
You have a total of 2 tasks in the list.
```

## Getting help : `help`
Displays chatbot usage instructions.

Format: `help`

Example: `help`

```dtd
→ bye: Ends the chatbot.
Example: bye
→ deadline: Creates a deadline task.
Parameters: TASK_TITLE, DATETIME (must be yyyy/MM/dd HHmm)
Example: deadline read book /by 2022/02/22 1234
→ delete: Delete a task identified by the task number.
Parameters: TASK_NUMBER (must be positive interger)
Example: delete 1
→ event: Creates an event task.
Parameters: TASK_TITLE, START_DATE_TIME (must be yyyy/MM/dd HHmm), END_DATE_TIME (must be yyyy/MM/dd HHmm)
Example: event read book /from 2022/02/22 1234 /to 2022/02/22 2234
→ find: Find tasks containing the given keyword.
Parameters: KEYWORD
Example: find book
→ help: Displays chatbot usage instructions.
Example: help
→ list: Lists down all the stored task(s).
Example: list
→ mark: Marks task identified by the task number as completed.
Parameters: TASK_NUMBER (must be positive interger)
Example: mark 1
→ todo: Creates a deadline task.
Parameters: TASK_TITLE
Example: todo read book /by 2022/02/22 1234
→ unmark: Unmarks task identified by the task number as completed.
Parameters: TASK_NUMBER (must be positive interger)
Example: unmark 1
```

## Exit chatbot : `bye`
Exiting from the chatbot.

Format: `bye`

Example: `bye`

```dtd
Goodbye! If you ever need assistance in the future, don't hesitate to reach out. Take care!
```
