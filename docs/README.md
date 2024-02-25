# Kokbot User Guide

![Kokbot in usage](./Ui.png)

Kokbot is a todo list application which will help you to keep track of your daily tasks! It is designed for fast typers who prefer the use of a command line over a traditional todo list GUI.

---
## How it works

Interact with Kokbot by giving it commands through the chat input. Read below to find out more about the commands which Kokbot supports.


## Starting it up

To start it up on `cmd`, move to the directory where `kokbot.jar` is stored and run the command below:
```
java -jar kokbot.jar
```
This should open up a new empty chat window.

As part of the initial setup, a directory named `data` 
will be created within the same directory as `kokbot.jar`. 
**<u>Make sure this folder and its contents are not deleted.</u>** 
This folder contains the textfile which the application uses to store current tasks.

---
## Using the Kokbot command line

The command line supports the following functions:

- [**Creating** of 3 different types of tasks:](#creating-of-tasks)
    - To-dos - tasks with no attached dates
    - Deadline - tasks with an attached deadline
    - Event - tasks with a start time and end time
- [**Listing** of current tasks](#listing-of-tasks-list)
- [**Marking** (and unmarking) tasks as complete](#marking-of-tasks-as-complete-mark)
  - [**Deleting** of existing tasks](#deleting-of-existing-tasks-delete)
- [**Searching** for tasks by its description](#searching-by-description-find)
- [**Storing of tasks** for subsequent runs of Kokbot](#storing-of-tasks)

### Usage of command keywords:  
`keyword [optional arguments]`

E.g. `list bydate`, `todo taskDescription`, `delete 5`

Refer to each keyword's corresponding sections below to understand what each command does.

---
## Creating of tasks

Kokbot supports the creation of 
three different types of tasks:
- To-dos
- Deadlines
- Events

## Creating To-dos: `todo`

To-do tasks are tasks which only contain a description.  
Format: `todo [taskDescription]`

E.g. `todo Homework1`, `todo CS2103T A-UserGuide`

## Creating Deadlines: `deadline`

Deadline tasks are tasks which contain a description and a deadline.  
Format: `deadline [taskDescription] /by [deadlineDateTime]`

E.g.`deadline iP Final Submission /by 230224 2359`

**NOTE:** Kokbot accepts multiple input formats for DateTime, 
refer to heading [Accepted DateTimes](#accepted-datetimes) below for further information. 

## Creating Events: `event`

Event tasks are tasks which contain a description, a start time and an end time.   
Format: `event [taskDescription] /from [startDateTime] /to [endDateTime]`

E.g.`event CS2103T Finals /from 270424 0900 /to 270424 1100`

**NOTE:** Kokbot accepts multiple input formats for DateTimes,
refer to heading [Accepted DateTimes](#accepted-datetimes) below for further information.

### Accepted DateTimes

The formats shown in the examples below are accepted by Kokbot:

#### Dates:
- `2/4/2024`
- `2-4-24`
- `02-04-2024`
- `2024-04-02`
- `02042024`
- `020424`

#### Times:
- `2000`
- `20:00`
- `20`
- `8:00pm`

Any combinations of `<Date> <Time>` or `<Time> <Date>` will be accepted as a valid DateTime format.
E.g. `2/4/2024 20:00`, `20:00 2/4/24`, `8:00pm 020424`

---
## Listing of tasks: `list`

Lists all tasks currently stored in Kokbot.   
Format: `list`

- Each task will be shown with the format below:  
`4. [D][X] Example Description (by: Feb 23 2024 11.59pm)`

  - `[D]` shows the task is a deadline. Similarly:
    - `[T]` represents To-dos
    - `[E]` represents Events

  - `[X]` represents the task is marked as complete.
    - `[ ]` represents an incomplete task

  - Task description is shown, followed by date specified for Deadlines and Events


### Sorted listings:

Kokbot supports the list of tasks to be sorted based on certain criteria:

Sort based on task type.
Format: `list bytype`

Sort based on deadlineDate(Deadline) and startDate(Event). 
Format: `list bydate`

Sort alphabetically based on description.
Format: `list bydesc`

---
## Marking of tasks as complete: `mark`

Marks a task as complete  
Format: `mark [taskNumber]`

Example: `mark 5` will mark the 5th task on the list as completed.

## Unmarking of task: `unmark`

Unmarks a task which was previously marked.  
Format: `unmark [taskNumber]`

## Deleting of existing tasks: `delete`

Deletes a task from the list
Format: `delete [taskNumber]`

For example, `delete 3` will delete the 3th task on the list.

The task will be removed from the list.

## Searching by description: `find`

To filter for tasks by description:  
Format: `find [taskDescription]`

For example, `find CS2103T` will return all tasks with CS2103T in its description

---
## Closing of application

The application can be closed by the command:  
Format: `bye`

### Storing of tasks

Tasks are stored in `data/kokbot.txt` after each command is run. 
On each subsequent launch of Kokbot, it will continue where the 
application left off the previous run.




