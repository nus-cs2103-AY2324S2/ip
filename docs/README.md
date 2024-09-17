# Blawg User Guide

A helpful chatbot for managing tasks.

## Features

You can create 3 types of tasks:
- **Todo**, 
- **Deadline** 
- **Event**

Use these commands to manage your tasks:
- **list**: to view your task list
- **mark**: to mark a task as done
- **unmark**: to unmark a task as done
- **delete**: deletes a task 
- **find**: finds a specific task by matching descriptions
- **help**: offers guidelines on how to use each command

Type **bye** to exit the application.

Blawg stores your task list so that every time you re-open the application, your tasks are easily retrievable

Some sample demonstrations are as follows:

`Todo buy milk`

`Mark 1`

`list`


This commands creates a Todo task with the description buy milk, the user then marks it as completed and then views 
their updated list. This would be how their list would look like:

```
[T][X] Buy bread
```

## Todo

Creates a Todo task. A todo command takes in one parameter: **description**

Usage: `Todo {task description}`


## Deadline

Creates a Deadline task. A deadline command takes in two parameter: **description**, **due date**

Usage: `Deadline {task description} by {date}`

## Event

Creates an Event task. An event command takes in three parameter: **description**, **start date**, **end date**

Usage: `Event {task description} from {start date} to {end date}`

## List

Displays the user's current task list

Usage: `List`

## Mark

Marks a task as complete. A Mark command takes in one parameter: **index of task**

Usage: `Mark {index}`

## Unmark

Marks a task as incomplete. An unmark command takes in one parameter: **index of task**

Usage: `Unmark {index}`

## Delete

Deletes a task. A delete command takes in one parameter: **index of task**

Usage: `Delete {index}`

## Find

Finds a task using fuzzy search. A find command takes in one parameter: **description of task**

Usage: `Find {search term}`

## Help

Displays help for usage of application. A help command takes in one optional parameter: **command name**, if no 
parameter is given, a general help guide is displayed instead

Usage: `Help {optional command}`