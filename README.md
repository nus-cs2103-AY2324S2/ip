# Signal: A task helper

This is a project based on the template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

<img src="/" alt="A screenshot of the chatbot" />

# Features
Create tasks, set deadlines and create events
Mark some tasks as priority
Mark tasks done and un-done


# Accepted inputs:

CREATING TASKS:
* todo () -- creates a To Do task, which has no deadline. 
* deadline () \by () -- creates a Deadline task, indicate its deadline after '\by'.
* event () \from () \to () -- creates an Event task, indicate its start and end after '\from' and '\to'.
Note: dates are formatted as yyyy-mm-dd. time is formatted as 

COMMANDS: 
* list -- prints a numbered list of the tasks created, in input order.
* mark [] -- marks the task at index [] as completed. 
* unmark [] -- marks the task at index [] as uncompleted. 
* notdonelist -- show the list of tasks that are uncompleted.
* prioritise [] -- sets the task at index [] as priority.
* unprioritise [] -- sets the task at index [] as non priority
* prioritylist -- show the list of prioritised tasks
* delete [] -- removes the task at index [] from the list.
* bye -- exits the program.
