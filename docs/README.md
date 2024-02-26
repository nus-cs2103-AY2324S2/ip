# Eve User Guide

![Screenshot of the UI](Ui.png)

Welcome to Eve, your personal chatbot for managing tasks. This guide will help you nagivate through using EVE and utilize her features to organize your tasks effectively.
### How to Start Using
1. Download the Jar file from Release
2. Launch the jar file using the java -jar eve.jar command
3. Start using EVE !

## Features
1. Adding of Task
    - Todo
    - Event
    - Deadline
2. Editing of Task
    - Deleting
    - Tagging
    - Marking as Done
    - Marking as UnDone
3. Viewing of Task
    -Viewing the List of Task
4. Exiting
    -Exiting the Chatbot

### Adding Task
There are 3 different types of Tasks, Todo, Deadline and Event

Notes on formatting:
- Commands typed are not case senitive (e.g Mark is parsed the same as mark)
- the first [ ] represents the type of task, 
    - T for Todo
    - D for Dealine
    - E for Event
- The second [ ] represents whether the task is completed ornot, if it's empty it means the task is not done yet, once it's marked as done it'll be represented with an X.

## Adding Todo
A Todo is a task that only contains a description.

Example input Todo Submit project

Expected Output: Got it. I've added the task 
                 [T][ ] Submit project
                 Now you have 1 task in your list

## Adding Deadline
A Deadline is a task with a description and a date andtime of which it should be done.

Example input: deadline return book /by 2/12/2019 1800

Expected Output: Got it. I've added the task 
                 [D][ ] return book (by: 2019-12-02T18:00)
                 Now you have 2 task in your list

## Adding Event
A Event is a task with a description with a date and time of the starting and ending

Example input: event project meeting /from 2/12/2019 1800 /to 2/12/2019 2000

Expected Output: Got it. I've added the task 
                 [E][ ] project meeting (from: 2019-12-02T18:00 to 2019-12-02T20:00 )
                 Now you have 3 task in your list

### Task Manipulation
There are various way to manipulate yout task with eve, there are
    - Task Deletion
    - Task Finding
    - Marking a Task as done/not done
    - Tagging a task 
## Deleting a Task
We all make mistake, perhaps the task is is wrongly inputed or you would not like to track it anymore, in this case do delete the task with

Example input: delete 2

Expected Output: Got it. I've removed the task 
                 [D][ ] return book (by: 2019-12-02T18:00)
                 Now you have 2 task in your list

## Finding a Task
To find a task in the list, we can just use the 'Find' Command.

Example input: find meeting

Expected Output: Here are the matching tasks in your list
                1. [E][ ] project meeting (from: 2019-12-02T18:00 to 2019-12-02T20:00 )


## Updating a Task as Done
Eve provides the ability to mark a task as done using the 'Mark' command


Example input: Mark 1

Expected Output: Nice! I've marked this task as done:
                [T][X] Submit project

## Updating a Task as not Done
Eve provides the ability to unmark a task as done using the 'Unmark' command


Example input: unmark 1

Expected Output: Nice! I've marked this task as not done yet:
                [T][ ] Submit project


## Tagging a Task



### Listing all the availble task
Eve provides the ability to unmark a task as done using the 'List' command


Example input: list

Expected Output: Here are the tasks in your list:
                1. [T][ ] Submit project
                2. [E][ ] project meeting (from: 2019-12-02T18:00 to 2019-12-02T20:00 )

### Exiting the Chatbot
To exit the chatbot, use the 'bye' command !

Example input: bye:

Expected Output: Bye. Hope to see you again soon !

// Seems like saving the application is gone, need to find