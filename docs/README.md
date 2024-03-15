# JerryBot User Guide

Welcome to our task management chatbot! This chatbot is designed to help you keep track of your todos, deadlines, 
and events. Here's a short guide on how to use it effectively:

[JerryBot Ui](Ui.png)

## Adding Tasks:

To add a new todo task, simply type: `todo <task description>`
To add a new deadline task, use: `deadline <task description> /by <deadline>`
To add a new event task, enter: `event <task description> /from <start time> /to <end time>`

## Listing Tasks:

To see a list of all your tasks, type: `list`

## Marking Tasks as Done:

If you've completed a task, mark it as done by typing: `mark <task number>`

## Unmarking Tasks:

If you've made a mistake or need to undo a task completion, unmark it with: `unmark <task number>`

## Deleting Tasks:

If you want to remove a task from your list, simply type: `delete <task number>`

## Preventing Duplicate Tasks:

Our chatbot is smart enough to prevent you from adding duplicate tasks. If you try to add a task that's already in 
your list, it will notify you and prevent duplication.

Examples:

```
todo Read a book
deadline Complete project /by 2024-03-01
event Team meeting /from 2024-02-28 14:00 /to 2024-02-28 15:00
list
mark 2
delete 3
```

### Note

Make sure to follow the specified format for deadlines (YYYY-MM-DD) and events (YYYY-MM-DD HH:mm) to 
ensure accurate task management.

That's it! With these simple commands, you can efficiently manage your tasks and stay organized. 
Happy tasking!