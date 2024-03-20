# Eve User Guide

![Screenshot of the UI](Ui.png)

Welcome to Eve, your personal chatbot for managing tasks. This guide will help you nagivate through using EVE and utilize her features to organize your tasks effectively.
### How to Start Using
1. Download the Jar file from Release
2. Launch the jar file using the java -jar eve.jar command
3. Start using EVE !

## Features
1. [Adding of Task](#adding-task)
    - [Creating a Todo](#adding-todo) `Todo`
    - [Adding a Event](#adding-event) `Event`
    - [Making a Deadline](#adding-deadline) `Deadline`
2. [Task Manipulation](#task-manipulation)
    - [Deleting your task](#deleting-a-task) `Delete`
    - [Tagging your task](#tagging-a-task) `Tag`
    - [Marking as Done](#updating-a-task-as-done) `Mark`
    - [Marking as UnDone](#updating-a-task-as-not-done) `Unmark`
3. [Finding your Tasks](#filtering-your-tasks)
    - [Finding your Task](#finding-a-task) `Find`
    - [Viewing back to the List of Task](#listing-all-the-availble-task) `List`
4. [Exiting](#exiting-the-chatbot)
    - [Exiting the Chatbot](#exiting-the-chatbot) `Bye`

### Adding Task
There are 3 different types of Tasks, `Todo`, `Deadline` and `Event`

Notes on formatting:
- Commands typed are not case senitive (e.g Mark is parsed the same as mark)
- the first [ ] represents the type of task, 
    - T for Todo
    - D for Dealine
    - E for Event
- The second [ ] represents whether the task is completed ornot, if it's empty it means the task is not done yet, once it's marked as done it'll be represented with an X.
- Tasks are automatically saved after every input.

## Adding Todo
A `Todo` is a task that only contains a description.

Example input `Todo Submit project`

Expected Output: `Got it. I've added the task `
                 `[T][ ] Submit project`
                 `Now you have 1 task in your list`

## Adding Deadline
A `Deadline` is a task with a description and a date andtime of which it should be done.

Example input: `deadline return book /by 2/12/2019 1800`

Expected Output: `Got it. I've added the task`
                 `[D][ ] return book (by: Dec 2 2019 1800)`
                 `Now you have 2 task in your list`

## Adding Event
A `Event` is a task with a description with a date and time of the starting and ending

Example input: `event project meeting /from 2/12/2019 1800 /to 2/12/2019 2000`

Expected Output: `Got it. I've added the task `
                 `[E][ ] project meeting (from: Dec 2 2019 1800 to Dec 2 2019 2000)`
                 `Now you have 3 task in your list`

### Task Manipulation
There are various way to manipulate yout task with eve, there are
- `Task Deletion`
- `Marking a Task as done`
- `Marking a Task as not done`
- `Tagging a task`

## Deleting a Task
We all make mistake, perhaps the task is is wrongly inputed or you would not 
like to track it anymore,in this case do delete the task with `Delete`

Example input: `delete 2`

Expected Output: `Got it. I've removed the task` 
                 `[D][ ] return book (by: Dec 2 2019 1800)`
                 `Now you have 2 task in your list`


## Updating a Task as Done
Eve provides the ability to mark a task as done using the `Mark` command


Example input: `Mark 1`

Expected Output: `Nice! I've marked this task as done:`
                 `[T][X] Submit project`

## Updating a Task as not Done
Eve provides the ability to unmark a task as done using the `Unmark` command


Example input: `unmark 1`

Expected Output: `Nice! I've marked this task as not done yet:`
                 `[T][ ] Submit project`


## Tagging a Task
You are able to add a tag to a tag using the `Tag` Command

Example input: `tag 1 hard`

Expected Output: `Nice! I've added a tag to this:`
                 `[T][ ] Submit project # Hard`

### Filtering your Tasks

## Finding a Task
To find a task in the list, we can just use the `Find` Command.

Example input: `find meeting`

Expected Output: `Here are the matching tasks in your list`
                 `1. [E][ ] project meeting (from: Dec 2 2019 1800 to Dec 2 2019 2000)`

### Listing all the availble task
Eve provides the ability to unmark a task as done using the 'List' command


Example input: `list`

Expected Output: `Here are the tasks in your list:`
                 `1. [T][ ] Submit project`
                 `2. [E][ ] project meeting (from: Dec 2 2019 1800 to Dec 2 2019 2000)`

### Exiting the Chatbot
To exit the chatbot, use the `bye` command !

This command is optional, it just let's Eve know that you don't want to talk to her anymore,
tasks are saved upon telling Eve your task.
Example input: `bye`

Expected Output: `Bye. Hope to see you again soon !`

