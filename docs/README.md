# Aerial ChatBot User Guide

// Update the title above to match the actual product name

// Product screenshot goes here
![Screenshot of the Gui](https://reganchoy.github.io/ip/Ui.png)
// Product intro goes here
Aerial Chatbot is a desktop app for keeping track of your daily tasks. It is optimized with the use of a Command Line Interface (CLI)

## Command Format:
Words in [UPPER_CASE] are parameters.

## Adding Tasks

// Describe the action and its outcome.
There are 3 kinds of tasks you can create.
1)ToDo - Tasks can be done anytime, not time sensitive
2)Deadlines - Tasks must be done before a date/time
3)Events - Tasks that have a start and end date/time

Time format must be as follows:
DD-MM-YY HH:MM

Todo Command Format: todo [TASK DESCRIPTION]
Example: Create a todo
todo Bake Cookies

Deadline Command Format: deadline [TASK DESCRIPTION] /[TIME]
Example: Create a deadline to pack cookies by 3 Feb 2001 12pm
deadline pack cookies /03-02-2001 12:00

Event Command Format: event [TASK DESCRIPTION] /[START TIME] /[END TIME]
Example: Create a birthday party event from 3 Feb 2001 2pm to 3 Feb 2001 6pm.
event birthday party /03-02-2001 14:00 /03-02-2001 18:00

// A description of the expected outcome goes here

```
expected output
```

## Features

### List Task
This command lists all the tasks that you have.
Format: list

### Mark Task
This command marks task as completed.
Format: mark [TASK INDEX]

### Unmark Task
This command unmarks task as uncompleted.
Format: unmark [TASK INDEX]

### Delete Task
This command deletes task from the task list.
Format: delete [TASK INDEX]

### Clear Tasks
This command removes all tasks permanently.
Format: clear

### Postpone
This command enables you to change the date/time of deadlines and events.
Format: postpone [TASK NAME] /[TIME FORMAT]

### Find Task
This command enables you to find all tasks that contain the keyword.
Format: find [KEYWORD]