# YoudonBot User Guide
***
Introducing YoudonBot, a brand-new revolutionary task manager that can help you manage ToDos, Deadlines and Events!

![Screenshot of YoudonBot's GUI](Ui.png)
## Table of Contents
***
- [Todo](#Todo)
- [Deadline](#Deadline)
- [Event](#Event)
- [Mark](#Mark)
- [Unmark](#Unmark)
- [List](#List)
- [Delete](#Delete)
- [Find](#Find)
- [Snooze](#Snooze)
- [Bye](#Bye)
## Features
***
### Todo
Adds a To-Do task to your current Tasklist.
Format: `todo <description>`
Example: `todo borrow book`

Expected Response from YoudonBot:
```
Alright! Task added:
     [T][ ] borrow book
You now have 1 task(s) in the list.

Tasklist saved!
```
### Deadline
Adds a Deadline task to your current Tasklist.
Format: `deadline <description> /by <date> <time>`
Example: `deadline return book /by 2/12/2019 1800`

Expected Response from YoudonBot:
```
Alright! Task added:
     [D][ ] return book (by: Dec 02 2019 18:00)
You now have 2 task(s) in the list.

Tasklist saved!
```
### Event
Adds a Event task to your current Tasklist.
Format: `event <description> /from <date> <time> /to <date> <time>`
Example: `event project meeting /from 5/6/2020 1900 /to 7/7/2021 1900`

Expected Response from YoudonBot:
```
Alright! Task added:
     [E][ ] project meeting (from: Jun 05 2020 19:00 to: Jul 07 2021 19:00)
You now have 3 task(s) in the list.

Tasklist saved!
```
### Mark
Marks the task in your Tasklist as done.
Format: `mark <taskNumber>`
Example: `mark 1`

Expected Response from YoudonBot:
```
Nicely done! The task has been marked as done:
     [T][X] borrow book

Tasklist saved!
```
### Unmark
Marks the task in your Tasklist as undone.
Format: `unmark <taskNumber>`
Example: `unmark 1`

Expected Response from YoudonBot:
```
Okies! The task has been marked as undone:
     [T][ ] borrow book

Tasklist saved!
```
### List
Lists our all tasks in your Tasklist.
Format: `list`
Example: `list`

Expected Response from YoudonBot:
```
Here are your tasks:
     1. [T][ ] borrow book
     2. [D][ ] return book (by: Dec 02 2019 18:00)
     3. [E][ ] project meeting (from: Jun 05 2020 19:00 to: Jul 07 2021 19:00)
```
### Delete
Deletes the task from your Tasklist.
Format: `delete <taskNumber>`
Example: `delete 1`

Expected Response from YoudonBot:
```
Alright! The task has been deleted:
     [T][ ] borrow book

Tasklist saved!
```
### Find
Finds all tasks in your Tasklist with the input keyword(s).
Format: `find <keyword(s)>`
Example: `find return`

Expected Response from YoudonBot:
```
Here are your tasks:
     1. [D][ ] return book (by: Dec 02 2019 18:00)
```
### Snooze
Snoozes the Deadline task for 1 day.
Format: `snooze <taskNumber>`
Example: `snooze 1`

Expected Response from YoudonBot:
```
Okies! The task has been snoozed by 1 day:
     [D][ ] return book (by: Dec 03 2019 18:00)

Tasklist saved!
```
### Bye
Ends the conversation with YoudonBot.
Format: `bye`
Example: `bye`

Expected Response from YoudonBot:
```
Bye. Hope to see you again soon!
```