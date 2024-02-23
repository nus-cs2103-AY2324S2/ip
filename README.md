# Signal: A task helper

This is a project based on the template for a greenfield Java project. Given below are instructions on how to use it.

<img src="./docs/Ui.png" alt="A screenshot of the chatbot" />

## Features
* Create tasks, set deadlines and create events
* Mark some tasks as priority
* Mark tasks done and un-done

Find the full feature list [here](https://tahnya.github.io/ip/)!

## Accepted inputs:

Note:
The round brackets indicate you can enter any text, square brackets indicate you should enter a number, without the brackets.

CREATING TASKS:
* todo <task> -- creates a To Do task, which has no deadline.
* deadline <task> /by <date> <time> -- creates a Deadline task, indicate its deadline after '/by'. Adding the time is optional.
* event <task> /from <date> <time> /to <date> <time> -- creates an Event task, indicate its start and end after '/from' and '/to'. Adding the time is optional.

Note: dates and times are formatted as <dd-mm-yyyy> \<hhmm>. Dates added are always in the current year if not specified.

COMMANDS: 
* list -- prints a numbered list of the tasks created, in input order.
* mark <task_number> -- marks the task at index <task_number> as completed. 
* unmark <task_number> -- marks the task at index <task_number> as uncompleted. 
* notdonelist -- show the list of tasks that are uncompleted.
* prioritise <task_number> -- sets the task at index <task_number> as priority.
* unprioritise <task_number> -- sets the task at index <task_number> as non priority.
* prioritylist -- show the list of prioritised tasks.
* find <text> -- show a list of tasks which contain <text> in their description.
* delete <task_number> -- removes the task at index [] from the list.
* bye -- exits the program.
