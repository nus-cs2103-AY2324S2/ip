# shirmin User Guide

shirmin is a simple todo list app that allows you to add, delete, manipulate and find your tasks!

# Commands: 
Note: Do not include the angle brackets <> for all fields enclosed within them <br>

Format for guide: 

#### Command
    <Function>
    <Format of command>
    <Example use of command>
    <Expected output from example use>
    

## Adding tasks

#### todo <br> 
    Adds an uncompleted todo task to the list 
    Format: "todo <todo name>" 
    Example: "todo do work" 
    Expected output: Added: [T][] do work 

#### deadline <br> 
    Adds an uncompleted deadline task to the list 
    Format: "deadline <deadline name> /by <YYYY-MM-DD HHMM> (
    Example: "deadline return book /by 2019-10-15 1800" 
    Expected output: Added: [D][] return book (by: Oct 15 2019 18:00)

#### event
    Adds an event task to the list 
    Format: "event <event name> /from <YYYY-MM-DD HHMM> /to <YYYY-MM-DD HHMM>"
    Example: "event work /from 2019-10-15 1800 /to 2019-10-15 2300"
    Expected output: Added: [E][] work (from: Oct 15 2019 18:00 to Oct 15 2019 23:00)

## Viewing tasks
#### list 
    Displays all current tasks
    Format: "list"
    Example: "list"
    Expected output (Example): 
    "1. [T][] task 1
     2. [T][] task 2"
#### find
    Displays current tasks with search term
    Format: "find <search term>"
    Example: "find task"
    Expected output (Example): 
    "Here are the matching tasks in your list:
    1. [T][] task 1
    2. [T][] task 2"


## Manipulating tasks
#### mark
    Marks a single task to indicate it is complete
    Format: "mark <task number>"
    Example: "mark 1"
    Expected output (Example):
    "Marked as done: [T][X] task 1"

#### unmark
    Marks a single tasks to indicate it is incomplete
    Format: "unmark <task number>"
    Example: "unmark 1"
    Expected output (Example):
    "Marked as not done: [T][] task 1"
#### delete
    Deletes a single task from the list
    Format: "delete <task number>"
Example: "delete 1" <br>
Expected output (Example):
    "Deleted task: [T][] task 1"

## Exiting App
#### bye
    Exits app
    Format: "bye"
    Example: "bye"
    Expected output: App closes
    

Thanks for using the app!