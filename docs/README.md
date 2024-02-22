# SirDuke User Guide

![product screenshot](Ui.png)

SirDuke is at your service. With SirDuke you would be able to:
- Add deadlines
- Find for tasks by index
- Add events
- Archive your tasks

## Adding deadlines

Add a `deadline` that sets a task that should end by a certain period

Example: `deadline meet John Wick /by 12-12-1212 12:12`

You should be able to see...

```
Got it. I've added this task:
[D][ ] meet John Wick (by: Dec 12 1212 12:12)
Now you have 3 tasks in the list.
```

## Archive

Command: `archive` (case-insensitive)

With this command you would **clear** all the tasks in storage (stored in the `data`) and have it copied over to
another txt file in the `archive` directory
