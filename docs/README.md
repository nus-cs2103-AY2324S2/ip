# Ken User Guide

![Ui.png](Ui.png)

Your very own Long Term, Long Distance, Low Commitment, Casual Botfriend ;)

Ken frees your mind of having to remember things you need to do, so you can focus on beach🏖.

# Features

## Adding tasks

Adds task to todo list.

Example: `todo assignment`

```
Got it!
Added task: [T][ ] assignment
Now Barbie has 1 tasks in list
```

## Adding deadlines

Adds task with deadline to todo list.

Example: `deadline assignment /by 2024-02-24 0000`

```
Got it!
Added task: [D][ ] assignment (by: Feb 24 2024 00:00)
Now Barbie has 2 tasks in list
```

## Adding events

Adds event to todo list.

Example: `event birthday /from 2024-02-24 0000 /to 2024-02-24 2359`

```
Got it!
Added task: [E][ ] birthday (from: Feb 24 2024 00:00 to: Feb 24 2024 23:59)
Now Barbie has 3 tasks in list
```

## Marking tasks

Marks tasks as done.

Example: `mark 1`

```
SUBLIME! Task 1 completed!
 [T][X] assignment
```

## Unmarking tasks

Marks tasks as not done.

Example: `unmark 1`

```
ookayy, so task 1 is not actually done
 [T][ ] assignment
 You are not doing task very well :(
```
## Deleting tasks

Deletes task from todo list.

Example: `delete 1`

```
Ohh okayy...
Deleted task: [T][ ] assignment
Now Barbie has 2 tasks in list.
```
## Listing tasks

Lists tasks in todo list.

Example: `list`

```
Hold my ice cream,
Your tasks for today:
1. [D][ ] assignment (by: Feb 24 2024 00:00)
2. [E][ ] birthday
(from: Feb 24 2024 00:00 to: Feb 24 2024 23:59)
```

## Leaving chatbot

Leaves app.

Example: `bye`

```
Beach off!
```

## Finding tasks with Keyword

Finds all tasks in list with keyword.

Example: `seek birth`

```
seeking...

These are all the births in your list:
1. [E][ ] birthday
(from: Feb 24 2024 00:00 to: Feb 24 2024 23:59)
```

## Getting help

Provides help, shows list of all commands to give Ken.

Example: `help`

```
list
-> Lists tasks.
todo <task>
-> Adds todo task.
deadline <task> /by 
<due by in YYYY-MM-DD HHmm>
-> Adds task with deadline.
event <task> /from
<from in YYYY-MM-DD HHmm>
/to <to in YYYY-MM-DD HHmm>
-> Adds event with duration.
mark <task index>
-> Marks task numbered 'index' as not done.
delete <task index>
-> Deletes task numbered 'index'.
seek <keyword>
-> List all tasks with 'keyword'.
help
-> Lists all commands you can give me - like this!
bye
-> I'll see you next time!
So...what do you want to beach today?
```