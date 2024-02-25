# Michelle User Guide

// Update the title above to match the actual product name

// Product screenshot goes here

Welcome to the MichelleBot User Guide!
MichelleBot is a desktop app chatbot designed to manage tasks, deadlines and events. 

## Features

* Viewing help: ==helpg==
* List your Tasks: ==list==
* Add a Task: ==add==
* Adding ToDos: ==todo==
* Adding Deadlines: ==deadline==
* Adding Events: ==event==
* Mark and Unmark a Task: ==mark== ; ==unmark==
* Delete a Task: ==delete==
* Find Tasks: ==find==
* Exiting the program: ==bye==

Notes about the command format: 
dates should be in 'dd-mm-yyyy hhmm' format. 

Ensure that commands that do not take in parameters (such as helpg, list, bye) do not have additional text as it will be taken into account! 
e.g if the input is 'list 123', it would return an error. 

## Viewing help: ==helpg==
Shows a message referring the user to the user guide page by using the 'helpg' command. 

## List your Tasks: ==list==
To view your current tasks, use the 'list' command

Example: 'list' 
Expected output: Here are your current tasks: 
                 1. task 1 
                 .... 

## Add a Task: ==add==
To add a simple task, use the 'add' command followed by the task description.
Example: 'add Watch Video' 
Expected output: Roger that! I've added in this task 
                    [] Watch Video 
                Now you have [n] tasks in your list. (n refers to the current size of your tasklist)

## Adding ToDos: ==todo==
To add a new ToDo to your list, use the 'todo' command followed by the task description. 

Example: 'todo Meet Client'
Expected output: Roger that! I've added in this task 
                    [T][] Meet Client 
                Now you have [n] tasks in your list. (n refers to the current size of your tasklist)

## Adding Deadlines: ==deadline==

To add a new Deadline to your list, use the 'deadline' command followed by the task description and end off with /by [deadline] 
(in dd-mm-yyyy hhmm format)

Example: 'deadline Assignment A /by 20-2-2024 1100'
Expected output: Roger that! I've added in this task 
                    [D][] Assignment A (by: 20-2-2024 1100)
                Now you have [n] tasks in your list. (n refers to the current size of your tasklist)

## Adding Events: ==event==

To add a new Event to your list, use the 'event' command followed by the task description, and end off with a start and end date in the format of /from [startDate] /to [endDate] 
(in dd-mm-yyyy hhmm format)

Example: 'event Entrepreneurship talk /from 22-2-2024 1100 /to 22-2-2024 1400'  
Expected output: Roger that! I've added in this task 
                    [E][] Entrepreneurship talk (from: 22-2-2024 1100 to: 22-2-2024 1400)
                Now you have [n] tasks in your list. (n refers to the current size of your tasklist)

## Mark and Unmark a Task: ==mark== ; ==unmark==

To mark a task as done or unmark a task as not done yet, use the 'mark' and 'unmark' commands respectively followed by the index number of the task you would like to modify. 

Example: 'mark 3'

Expected output: I've marked this task as done: 
                    [insert selected task here]

## Delete a Task: ==delete==
To delete a task, use the 'delete' command followed by the index number of the task you would like to delete.
Example: 'delete 1'

Expected output: Roger that! I've removed this task:
                    [insert deleted task here]
                Now you have [n] tasks in your list. (n refers to the current size of your tasklist)

## Find Tasks: ==find==
To search for tasks based on input, use the 'find' command followed by the search input. 
Example: 'find assignment' 

Expected output: Here are the results:
                 Asssignment A 
                 .... 


## View schedule: ==viewschedule==
To view the task list in the form of a schedule, use the 'viewschedule' command. 
The output is a button that creates a new window with the schedule. 
Example: 'viewschedule'

Expected output: Click on the button! (button) 


## Exiting the program: ==bye==
Exits the program with the 'bye' command. 
Example: 'bye'

Expected output: Bye. Hope to see you again soon! \(^-^)/ 

