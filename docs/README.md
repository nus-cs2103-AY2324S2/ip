# Bearducky User Guide

// Product screenshot goes here

Bearducky is a CLI-based chatbot that allows the user to handle Tasks, meant to represent a duck wearing a bear hat.
Tasks consist of three types:
1. Todo, tasks that do not have any deadlines.
2. Deadline, tasks that have a single deadline.
3. Event, tasks that have a start date and end date.

## Features available
-Adding tasks
-Deleting tasks
-Marking tasks
-Unmarking tasks
-Searching for tasks
-Listing out tasks
-Feeding bearducky bread





## Adding tasks

### Adding Todo:
To add a Todo task, the syntax is todo taskname. For example, to add task "do 2103", one would enter **todo do 2103**.

### Adding Deadline:
To add a Deadline task, the syntax is deadline /by date (mm/dd/yyyy format). For example, to add deadline "do 2103" by 23/2/2023, one would enter 
**deadline do 2103 /by 02/23/2023**. When listed, it will appear as "do 2103 (by: 2023-02-23)".

### Adding Event:
To add a Event task, the syntax is event /from startdate (mm/dd/yyyy format) /to enddate (mm/dd/yyyy format). For example, to add deadline "do 2103" from 23/2/2023 to 24/2/2023, one would enter **event do 2103 /from 2/23/2023 /to 2/24/2023**. 
When listed, it will appear as "do 2103 (from: 2023-02-23 to 2023-02-24)".

## Deleting tasks
To delete the nth item on the list, enter the command **delete n**.

## Marking tasks
To mark the nth item on the list, enter the command **mark n**.

## Unmarking tasks
To unmark the nth item on the list, enter the command **unmark n**.

## Searching for tasks
To search for a task, enter the command **find task**. All tasks that have matching names will show up.
