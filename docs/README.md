# fakegpt User Guide

fakegpt is a desktop app for managing tasks, helping one keep track of
various different kind of tasks and their dates.

## Quickstart

1. Download jar file from [here](https://github.com/jerremyng/ip/releases)
2. Copy the jar file into an empty folder.
3. Open a command window in that folder.
4. Run the command `java -jar {filename}.jar`  
`e.g., java -jar Duke.jar` (i.e., run the command in the same folder as the jar file).

A GUI should pop up and fakegpt is ready for use! See below for commands.

## Commands

### Viewing tasks: `list` or `ls`
Displays entire list of tasks.

### Exit program: `bye`
Saves tasks and quits program.

### Create new todo task: `todo {taskDescription}`
Adds a new todo type task to the list.

__Params__  
taskDescription: brief description of task

### Create new deadline task: `deadline {taskDescription} /by {dueDate}`
Adds a new deadline type task to the list.

__Params__  
taskDescription: brief description of task  
dueDate: a date in the format such as 01 Jan 2022

### Create new event task: `event {taskDescription} /from {startDate} /to {endDate}`
Adds a new event type task to the list.

__Params__  
taskDescription: brief description of task  
startDate: a date in the format such as 01 Jan 2022  
endDate: a date in the format such as 01 Jan 2022

### Delete a task: `delete {index}`
Deletes a task at specified index

__Params__  
index: number representing which task to delete

### Find a task: `find {searchPhrase}`
Returns a list of tasks that contain searchPhrase

__Params__  
searchPhrase: string to look for in task description.

### Sort tasks: `sort`
Sorts task list based on due date. ToDo events are put first.

### Mark as done: `mark {index}`
Marks a task as done by putting a check beside it.

__Params__  
index: index of task to mark as done.

### Mark as not done: `unmark {index}`
Unmarks a task as done by removing the check beside it.

__Params__  
index: index of task to unmark as done.