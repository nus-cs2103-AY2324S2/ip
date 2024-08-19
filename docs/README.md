# Peanut ChatBot User Guide

![Screenshot of Peanut ChatBot](/Ui.png)

Peanut is a versatile chatbot designed to simplify task management. Whether you need to organize deadlines, events, or simple to-dos, Peanut is here to help you stay on top of your tasks effortlessly.

## Features
> Notes about the command format:
>
> - Words in `UPPER_CASE` are the parameters to be supplied by the user.
>
>  e.g. in `deadline TASK /by DD-MM-YYYY HHMM`, `TASK`, `DD`, `MM`, `YYYY`, and `HHMM` are parameters which can be used as `deadline homework /by 23-02-2024 2359`.
>
>
> - Parameters must be in the same order.
>
>  e.g. if the command specifies `/from` first before `/to`, users have to follow.
>
>
> - Extraneous parameters for commands that do not take in parameters (such as `list`) will result in error.
>
>
> - Invalid or Missing index on commands (such as `delete`, `find`, `mark`, `unmark`) will result in error.
> 
>  e.g. [E-ERROR? Oh, good grief... ERROR] Invalid task index!
> 
> 
> - Invalid format on commands (such as `add`, `update`) will result in error.
> 
>  e.g. [E-ERROR? Oh, good grief... ERROR] Invalid format!

## Viewing tasks
Viewing tasks allows you to see all the tasks currently stored in Peanut.

Example: `list`

Expected Output:
```
Okay... sigh. Here are the tasks in your list:
     1.[T][ ] go gym
     2.[D][X] math homework (by: Feb 23 2024, 11:59PM)
     3.[E][ ] party (from: Jun 22 2024, 6:00PM to: Jun 22 2024, 11:00PM)
```

## Adding tasks
### Todos
Todo tasks represent simple tasks that need to be done without any specific deadline or event associated with them.

Example: `todo TASK`

Expected Output:
```
Okay... sigh. I've added this task:
     [T][ ] go for a run
Now you have 1 tasks in the list.
```

### Deadlines
Deadline tasks represent tasks that have a specific deadline by which they need to be completed.

Example: `deadline TASK /by DD-MM-YYYY HHMM`

Expected Output:
```
Okay... sigh. I've added this task:
     [D][ ] math homework (by: Feb 23 2024, 11:59PM)
Now you have 2 tasks in the list.
```

### Events
Event tasks represent tasks that occur within a specific time frame.

Example: `event TASK /from DD-MM-YYYY HHMM /to DD-MM-YYYY HHMM`

Expected Output:
```
Okay... sigh. I've added this task:
     [E][ ] party (from: Jun 22 2024, 6:00PM to: Jun 22 2024, 11:00PM)
Now you have 3 tasks in the list.
```

## Updating tasks
Updating tasks allows you to modify the description of an existing task.

Example: `update INDEX TASK`

Expected Output:
```
Okay... sigh. Task updated successfully:
     [T][ ] go gym
```

## Deleting tasks
Deleting tasks allows you to remove a task from your list.

Example: `delete INDEX`

Expected Output:
```
Okay... sigh. I've removed this task:
     [T][ ] go gym
Now you have 2 tasks in the list.
```

## Marking tasks
Marking tasks allows you to update the status of a task as complete.

Example: `mark INDEX`

Expected Output:
```
Okay... sigh. I've marked this task as done:
     [D][X] math homework (by: Feb 23 2024, 11:59PM)
```

## Unmarking tasks
Unmarking tasks allows you to update the status of a task back to incomplete.

Example: `unmark INDEX`

Expected Output:
```
Okay... sigh. I've marked this task as not done:
     [D][ ] math homework (by: Feb 23 2024, 11:59PM)
```

## Finding tasks
Finding tasks allows you to search for tasks containing a specific keyword within their descriptions.

Example: `find KEYWORD`

Expected Output:
```
Okay... sigh. Here are the matching tasks in your list:
     1.[E][ ] party (from: Jun 22 2024, 6:00PM to: Jun 22 2024, 11:00PM)
```

## Terminate ChatBot
When you send the bye command, Peanut will acknowledge the request and terminate the conversation, bidding you farewell first.

Example: `bye`

Expected Output:
```
Goodbye... Take care now.
[ChatBot platform close]
```
