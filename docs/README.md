# Bingus User Guide




![Product Screenshot](Ui.png)


Bingus is a cat-themed chatbot designed to record and manage your tasks, deadlines and events so ypu can see them in a clear concise manner.

<!-- @@author Ragnapop-reused -->
<!-- Adapted from AB3 user guide https://se-education.org/addressbook-level3/UserGuide.html#features -->
> ### Note
> 1. Words in `UPPER_CASE` are the parameters to be supplied by the user.
>    e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo Add Project Increment`.
> 2. Items in square brackets are optional.
>    e.g. in `event DESCRIPTION [/tentative]`, the argument `/tentative` is optional.
> 3. Tokens starting with `/` refers to a parameter name. Parameters can be in any order.
>    e.g. If the command specifies, `/from FROM /to TO`, `/to TO /from FROM` is also acceptable.
<!-- @@author -->
## Adding a todo task: `todo`
This command adds a todo task to the task list.

Format: `todo DESCRIPTION`

Example input:
```
todo task
```
Expected output: 
```
Got it. I've added this task:
[T][ ] Task (none)
Now you have 1 tasks in the list.
```
## Adding deadlines: `deadline`
This command adds a deadline task to the task list.

Format: `deadline DESCRIPTION /by DATE`

Example input:
```
deadline Deadline /by 2024-02-23
```

### Please Note
> Times must be in the format `YYYY-MM-DD.
> 
> Times not given in this format will be recorded as invalid input.


Expected output: 
```
Got it. I've added this task:
[D][ ] Deadline (by: 23 Feb 2024) (none)
Now you have 1 tasks in the list.
```
## Adding events: `event`
This command adds an event task to the task list.

Format: `event DESCRIPTION /to DATE /from DATE`

Example input:
```
event  Event /from 2024-02-23 /to 2024-02-24
```

Expected output: 
```
Got it. I've added this task:
[E][ ] Event (from: 23 Feb 2024 to: 24 Feb 2024) (none)
Now you have 1 tasks in the list.
```

## Displaying the list of tasks: `list`
This command displays the task list.
Format: `list`

Example input:
```
list
```
Expected output: 
```
Here are the tasks in your list:

1. [T] [ ]  task (none)
2. [D] [ ]  Deadline  (by: Feb 23 2024) (none)
3. [E] [ ]  Event (from: Feb 23 2024 to: Feb 24 2024) (none)
```
## Marking a task as done: `mark`

This command marks a specified task in the task list as done.

Format: `mark INDEX`

Example input:
```
mark 1
```
Expected output: 
```
Nice! I've marked this task as done:

[T] [X]  task (none)
```
## Unmarking a task as done: `unmark`

This command unmarks a specified task in the task list as done.

Format: `unmark INDEX`

Example input:
```
unmark 1
```
Expected output: 
```
OK, I've marked this task as not done yet:

[T] [ ]  task (none)
```

## Deleting a task: `delete`

This command deletes a task from the task list.

Format: `delete INDEX`

Example input:
```
delete 1
```
Expected output: 
```
Noted. I've removed this task:
[T] [ ]  task (none)
Now you have 4 tasks in the list.
```

## Finding all tasks with a keyword: `find`

This command finds all tasks whose description contains a keyword.

Format: `find KEYWORD`

Example input:
```
find tasks
```
Expected output: 
```
Here are the matching tasks in your list:
1. [T] [ ]  task 1 (none)
2. [T] [ ]  task 2 (none)

```

## Adding priority to your tasks: `priority`

This command add a priority to your tasks.

Format: `priority INDEX LEVEL`

Example input:
```
priority 1 high
```
Expected output: 
```
OK, I've changed the priority of this task:
[T] [ ]  task (high)


```

## Saying goodbye to Bingus: `bye`

This command terminates the program.

Format: `bye`

Example input:
```
bye
```
Expected output: 
```
Bye. Bingus hopes to see you again soon!


```

## Speaking to Bingus: `meow`

This command meows.

Format: `meow`

Example input:
```
meow
```
Expected output: 
```
Mrow. :3


```



