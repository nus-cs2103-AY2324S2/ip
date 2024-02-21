# Howie User Guide

> Just Do It - Nike
> 
![Ui.png](Ui.png)

Simple to Use

Free-Of-Charge

User Friendly GUI

## How to use Howie?

  ```powershell
  java -jar C:\filepath\of\the\downloaded\jar\file
  ```

## Sample Commands
Adds shower into the to-do list. `todo shower`

Add a deadline. `deadline Assignment Submission /by Thursday`

Add an event. `event Prom date /from Wed 9pm /to Wed 11pm`

Mark an item as completed. `mark 1`/ `mark 3`

Unmark an item as uncompleted `unmark 3`

## Basic Commands

### Add a todo task
Adds a basic task: `todo todo-task-name`

### Add a deadline task
Adds a deadline: `deadline deadline-task-name /by a-date-or-time`.

Alternatively, you can format the `/by` in `yyyy-mm-dd`.

### Add an event task
Adds an event: `event event-name /from today /to tomorrow`

Alternatively, you can for `/from` and `/by` in `yyyy-mm-dd`.

### List all tasks
Shows the full list of tasks: `list`

### Mark
Mark/Set a task as completed: `mark [index of task]`.

### Unmark
Unmark/Unset a task as uncompleted: `unmark [index of task]`.

### Getting help
If you are unsure of all the commands, type: `help`