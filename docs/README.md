# Aether User Guide

![Ui.png](Ui.png)

Aether! The chatbot that helps you manage all your tasks in one place :)

## Hello!


Aether says hello to all new users!


Example: `hello`

Aether will send a welcome message

```
Hello! I'm Aether!
What can I do for you?
```

## Adding tasks

There are three different types of tasks that can be added: Todo,
Deadline and Event to a numbered list


## Todo Tasks

A todo task can be added to your list of tasks with a simple description


Example: `todo write essay`

Aether will add the todo task with a description as well as [T] to denote 
it's a Todo Task

```
Gotchu. I've added this task: 
[T][]write essay
Now you have 1 tasks in the list
```
If no description is added, Aether will send an error message too!

## Deadline Tasks
A deadline task can be added with a description
alongside the date the task is due 


Example: `deadline read harry potter /by 2024-01-01 1200`

Aether will add the deadline task with the description,
the day it's due by and [D] to denote it's a description task

```
Gotchu. I've added this task: 
[D][]read harry potter (by: Jan 01 2024 1200)
Now you have 1 tasks in the list
```
If there's no description or the date is given in the wrong format eg. 12:00 
instead of 1200, Aether will send a warning

## Event Tasks
An event task can be added with a description
along with the duration till when the event is


Example: `event run in school /from 2024-01-09 1200 /to 2024-01-09 1300`

Aether will add the event task with the description,
the start and end date of the event and [E] to denote it's an event task

```
Gotchu. I've added this task: 
[E][]run in school (from:2024-01-09 1200 to:2024-01-09 1300)
Now you have 1 tasks in the list
```
If there's no description or the date is given in the wrong format eg. 12:00
instead of 1200, Aether will send a warning
## Finding Tasks
An event task can be added with a description
along with the duration till when the event is


Example: `event run in school /from 2024-01-09 1200 /to 2024-01-09 1300`

Aether will add the event task with the description,
the start and end date of the event and [E] to denote it's an event task

```
Gotchu. I've added this task: 
[E][]run in school (from:2024-01-09 1200 to:2024-01-09 1300)
Now you have 1 tasks in the list
```
If there's no description or the date is given in the wrong format eg. 12:00
instead of 1200, Aether will send a warning
## Viewing the list of tasks
The list of tasks can be viewed by a simple word.


Example: `list`

The list of all tasks present, both unmarked and marked will be shown

```
1.[D][]write reflection (by: Jan 01 2024 1200)
2.[T][X]read a book
```
# Additional Features

## Mark task as done
Any task can be marked as done


Example: `mark 2`

Aether will mark the task as done by putting X in the bracket

```
Nice! I've marked this task as done:
[T][X]read a book
```
If an invalid index is given, Aether will send an invalid index warning
## Unmark task as done
Any task can be unmarked to show pending work


Example: `unmark 2`

Aether will unmark the task as done by removing the X in the bracket

```
Better do soon, I've marked this task as not done yet:
[T][]read a book
```
If an invalid index is given, Aether will send an invalid index warning
## Delete task
Any task can be deleted from the list


Example: `delete 2`

Aether will delete the task from the list

```
Noted with thanks. I've removed this task:
[T][]read
Now you have 0 tasks in the list.
```
If an invalid index is given, Aether will send an invalid index warning
## Find task by keyword
Any task can be easily found using the keyword


Example: `find read`

Aether will find tasks with the word "read" in them

```
Here are the matching tasks in your list
[T][X]read W1 lesson plan
[T][]read example essay
```
If no tasks are present with the relevant word, Aether sends a no 
matching tasks found message
## Detect Duplicate tasks
Duplicate tasks of any kind will not be added 

Example: `event run in school /from 2024-01-09 1200 /to 2024-01-09 1300`

Aether will add not add the event task because one already exists
in the list with the same description and time.
If either one of those are different, then those tasks will be added

```
Ohno, you've already added this before!:
[E][]run in school (from:2024-01-09 1200 to:2024-01-09 1300)
This task was not added to the list
```
If either one of the description or timing are different, 
then those tasks will be added
## Saying Bye!
The conversation with Aether can be ended by saying bye


Example: `bye`

Aether will send a goodbye message and the window will close

```
Goodbye! Hope to see you again soon!
```