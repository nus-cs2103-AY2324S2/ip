# Lemona User Guide

## Starting LEMONA

run `main` of Launcher class to start the LEMONA. 

## Adding tasks

### Types of tasks :
- Todo
- Deadline
- Event

### Each types of functions should follow the following format :
- todo (taskDescription)
- deadline (taskDescription) /by (dueDate in dd/MM/yyyy HHmm format)
- event (taskDescription) /from (startDate in dd/MM/yyyy HHmm format) /to (endDate in dd/MM/yyyy HHmm format)

### Example : 
- todo take vitamin C
- deadline take lemona /by 22/02/2024 2359
- event lemona party /from 20/02/2024 2150 /to 22/02/2024 2359

## Mark / Unmark / Delete

You can mark/unmark/delete tasks in list of tasks

mark 1  -> marks first task in list as done
unmark 2 -> marks second task in list as not done
delete 3 -> deletes third task in list


## List

lists up all the tasks in your task list

## Find

finds a wanted keyword from the descriptions of the tasks in list

find lemona -> returns a task with word lemona in its description, like (deadline take lemona /by 22/02/2024 2359)
