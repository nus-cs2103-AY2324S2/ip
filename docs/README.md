# Axolotl


![](Ui.png)

Tired of forgetting to do tasks? Always forgetting to attend that meeting?

*AND...* also love **MINECRAFT**?

*THEN...* Axolotl is for _**YOU!**_


## Adding todos

Gotta remember to do something? Jot it down!


Syntax: `todo <task_name>`

Example: `todo spring cleaning`


Here's the expected output:
```
--------------------------------
Sure, I've added this task:
[T][ ] spring cleaning
Now you have 1 task(s) in the list.
--------------------------------
```


## Adding deadlines

Gotta finish an assignment next week? Note that down!


Syntax: `deadline <task_name> /by <due_date>`

Example: `deadline cs2103 iP /by 25/02/2024 2359`

*Note: do remember to include the date as dd/mm/yy HHmm*

Here's the expected output:
```
--------------------------------
Sure, I've added this task:
[D][ ] cs2103 iP (by: Feb 25 2024 11:59PM)
Now you have 2 task(s) in the list.
--------------------------------
```


## Adding event

Gotta remember to attend an event? Add it!


Syntax: `event <task_name> /from <start_date> /to <end_date>`

Example: `event internship briefing /from 25/02/2024 1400 /to 25/02/2024 1500`


Here's the expected output:
```
--------------------------------
Sure, I've added this task:
[E][ ] internship briefing (from: Feb 25 2024 02:00PM to: Feb 25 2024 03:00PM)
Now you have 1 task(s) in the list.
--------------------------------
```


## Mark task

Done a task? Woohoo, mark it down! :D 


Syntax: `mark <task_no>`

Example: `mark 1`

*Note: do remember to include the task number. Use list if you forget!
I will also provide a list of your tasks if the task number you provided does not exist :)*

Here's the expected output:
```
--------------------------------
Nice! I've marked task 1 as done:
[T][X] spring cleaning
--------------------------------
```


## Unmark task

Marked the wrong task? Or not done yet? Don't worry, you can unmark it!


Syntax: `unmark <task_no>`

Example: `unmark 1`

*Note: do remember to include the task number. Use list if you forget! 
I will also provide a list of your tasks if the task number you provided does not exist :)*

Here's the expected output:
```
--------------------------------
Sure, I've unmarked task 1:
[T][ ] spring cleaning
--------------------------------
```


## Delete task

Dont need a task anymore? Delete it!


Syntax: `delete <task_no>`

Example: `delete 1`

*Note: do remember to include the task number. Use list if you forget!
I will also provide a list of your tasks if the task number you provided does not exist :)*

Here's the expected output:
```
--------------------------------
Okay, I will delete this task:
[T][ ] spring cleaning
You now have 2 tasks in the list. \n" +
--------------------------------
```
## List your tasks

Want to know what's in your task list? Just ask me!

Syntax: `list`

Here's the expected output:
```
--------------------------------
Here are the tasks in your list: 
1. [D][ ] cs2103 iP (by: Feb 25 2024 11:59PM)
2. [E][ ] internship briefing (from: Feb 25 2024 02:00PM to: Feb 25 2024 03:00PM)
--------------------------------
```
## Find tasks with keyword

Too many tasks and can't find the one you are looking for? Use the find command and I'll search it for you!


Syntax: `find <keyword>`

Example: `find internship`


Here's the expected output:
```
--------------------------------
Here are the matching tasks in your list:
1. [E][ ] internship briefing (from: Feb 25 2024 02:00PM to: Feb 25 2024 03:00PM)
--------------------------------
```


## Sort tasks

Want to sort your task list by name? I'll sort them for you!

Syntax: `sort <type>`

Example: `sort name`

Types:
- [X] names
- [ ] (more to be added...)

*Note: currently, only sorting by name is available.*

Here's the expected output:

```
--------------------------------
Your list is now sorted by name.
Here are the tasks in your list:
1. [D][ ] cs2103 iP (by: Feb 25 2024 11:59PM)
2. [E][ ] internship briefing (from: Feb 25 2024 02:00PM to: Feb 25 2024 03:00PM)
--------------------------------
````

## Exiting the program

Calling it a day? Say bye to me! :)

Syntax: `bye`
```
--------------------------------
Bye. Hope to see you again soon!
--------------------------------
```

*Note: you will automatically exit the app in a second.*