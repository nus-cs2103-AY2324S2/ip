# Zoe User Guide

![Zoe Ui](/docs/Ui.png)

Hey you! Yes you! Do you need to manage tasks? Well I can do it for you!

## Get Started

1. Ensure you have java 11 installed in your computer
2. Download the latest Zoe.jar from here
3. Copy the jar file to a directory of your choosing
4. Open the command terminal, change to the directory you chose in step 3 and key in `java -jar Zoe.jar`
5. aaaaaaaaaaaaand Viola! you can start managing your tasks

## Features

### Add ToDos

Adds a new todo task with no other parameters, just to keep things in mind

```
todo <task-description>
```

Note, you cannot type todo on its own

### Add Events

Adds a new event task with a timeframe 

```
event <task-description> /from <start> /to <end>
```

Note, the time frame currently does not follow any date/time standards so you can key in any timeframe you want

### Add Deadlines

Adds a new deadline task with the by parameter to signify when you must finish the task

```
deadline <task-description> /by yyyy-mm-dd HH:mm
```
Example: `deadline complete project /by 2024-03-20 23:59`

Note, the `/by` parameter only accepts `yyyy-MM-dd HH:mm`

### List

Lists all of the tasks you currently have when you call `list`

For dealines, they are listed according to their date time

### Marking, Unmarking and Deleting

Marks, unmarks or deletes a task according to its index in list

Examples: `mark 1` or `unmark 2` or `delete 3`

### Finding

Shows tasks that contain a certain `word` in their description

Example: Calling `find book` will display all tasks that contain `book` in their description

### Exiting

Calling `bye` or clicking on the red cross closes Zoe

### Saving

Data saving is done automatically everytime a todo, event or deadline is created