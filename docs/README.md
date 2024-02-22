Dukey User Guide

![Dukey](Ui.png)

Welcome to Dukey! A user friendly chatbot that will allow you to keep track of 
all your tasks!

## Adding deadlines

Set a deadline to be met! This task requires a specific date/time to for the deadline

For example, should one be required to buy a cake by 22 October, one can use the input:

deadline buy a cake /by 2024-10-22 00:00

This would add this deadline task to the tasklist!

Inputting the command "list" will now show :

```
1.[D][ ] buy a cake (by:Oct 22 2024 00:00)
```
## Adding todos

Set a todo task to be done! this has no time restriction

For example, should one be required to finish an assignment, they use the input:

todo finish assignment

This would add this todo task to the tasklist!

Inputting the command "list" will now show :

```
1.[T][ ] finish assignment
```
## Adding events

Set a event! This task requires a duration of the event

For example, for the event gala lasting from 4-6 pm on 10 October:

event gala /from 2024-10-10 16:00 /to 2024-10-10 18:00

This would add this event task to the tasklist!

Inputting the command "list" will now show :

```
1.[E][ ] gala (from 10 Oct 2024 16:00 to 10 Oct 2024 18:00)
```

## Marking and unmarking tasks as done

To mark a task as done, first find its number in the list by calling 'list'.
Then mark/unmark it by calling :

mark n 

or

unmark n

if the task to be marked is the nth task


## Deleting a task

Similar to mark/unmark, delete a task from the list by calling 

delete n 

if the task is the nth task in the list.

## Finding a task from the list

To find tasks using a keyword, for example 'event', simply use the command:

find event

All tasks that may include your keyword will be displayed.

## Clearing the list

To clear the list, simply using the command:

reset

this will clear the list!