# Goblin User Guide

This is your goblin master who somehow 
decided to help you manage your daily tasks. 
Be grateful that he decided not to eat you 
for dinner. At least not today.


The following are the key features.
## Adding ToDos

You can add a ToDo task to the list.

Format: todo {description}

Example: `todo have fun`

Outcome: The ToDo task will be added to the list.
```
Got it. I've added this task: 
  [T][ ] have fun
Now you have 3 tasks in the list.
```

## Adding Deadlines

You can add a task with a deadline  to the list.

Format: deadline {description} {/by deadline}

Example: `deadline return book /by 2/12/2019 1800`

Outcome: The Deadlines task will be added to the list.
```
Got it. I've added this task: 
 [D][ ] return book(by:2nd of DECEMBER 2019 6pm)
Now you have 4 tasks in the list.
```


## Adding Events

You can add a event task with a time period to the list.

Format: event {description} 
{/from start date and time} {/to end date and time} 

Example: `event return book /from 2/12/2019 1800 /to 2/12/2019 1800`

Outcome: The Event task will be added to the list.
```
Got it. I've added this task:
   [E][ ] return book(from:2nd of DECEMBER 2019 6pm to 2nd of DECEMBER 2019 6pm)
Now you have 5 tasks in the list.
```
## Delete a Task
You can delete a task from the list.

Format: delete {index of the task}

Example: `delete 1`

Outcome: Task 1 will be removed from the list.
```
Noted. I've removed this task:
[T][ ] have fun
Now you have 4 tasks in the list.
```
## Find a Task
You can find a task which contains certain words in their names.

Format find {word}

Example: `find have fun`

Outcome: succeed or fail to find such task
```
Here are the matching tasks:
  2.[T][ ] have fun
```

## Mark as Done
You can mark a task in the list as done.

Format: mark {index of the task}

Example: `mark 1`

Outcome: Task 1 will be marked as done..
```
Nice! I've marked this task as done: 
	[E][X] have fun(from:2nd of DECEMBER 2019 6pm to 2nd of DECEMBER 2019 6pm)
```
## Unmark as Undone
You can mark a task in the list as undone.

Format: unmark {index of the task}

Example: `unmark 1`

Outcome: Task 1 will be marked as done..
```
Nice! I've marked this task as undone: 
	[E][] have fun(from:2nd of DECEMBER 2019 6pm to 2nd of DECEMBER 2019 6pm)
```
## List out the Tasks
You can list out the tasks in the list.

Format: list

Example: `list`

Outcome: Tasks in the list will be listed out
```
Read it yourself.
  1.[E][X] have love(from:2nd of DECEMBER 2019 6pm to 2nd of DECEMBER 2019 6pm)
  2.[T][ ] have fun
  3.[D][ ] return book(by:2nd of DECEMBER 2019 6pm)
  4.[E][ ] return book(from:2nd of DECEMBER 2019 6pm to 2nd of DECEMBER 2019 6pm)
```