Initial planning for the implementation of the dukeGUI.Duke project Chatbot

date: 25 Jan 2024

Overall Description:

The project aims to build a Personal Assistant Chatbot that helps a person to keep track of various things


_____________________________________________________

Level 0

Requirements:
- rename: Liv
- Greet the user once launched
- Exits after the conversation is finished

Design:
- Try to adhere to singleton principle by keeping only one Liv active
- abstract greet() and exit() behaviour
- store lines locally in functions, simply as strings. 
	- rationale: given the purpose of the bot being to track various things, it is likely not to need many lines

_____________________________________________________

Level 1

Requirements:
- echo the user input when it is not "bye"
- exits when the input is "bye"

Design:
- uses enum for states
- implement a ProcessInput function to handle inputs


_____________________________________________________

Level 2

Requirements:
- add: add items to the agenda
- list: list the items added, in chronological order
	- the index by which it is listed must provide access to the item when searching/deleting

Design:
- item: implement as class dukeGUI.task.Task
- list: implement with LinkedList
	- rationale: to allow ease of access and update


_____________________________________________________

Level 3:

Requirements:
- mark task as done
- mark task as not done

Design:
- add isDone boolean field to dukeGUI.task.Task class and implement relevant functions



_____________________________________________________

Level 4:

Requirements:
- split task into three types:
	- todo: basically a task
	- deadline: a task with a deadline
		- additional param: /by
	- events: a task with a start and end time
		- additional param: /from /to
- when adding a task, finish by giving the number of tasks in the list

Implementation:
- make task an abstract class with the method 

Challenges:
- the different parsing delimiter for different tasks
	- observation: the information can be processed and stored by individual classes



_____________________________________________________

Level 5:

Requirements:
- error handling

Design:
- introduced various exception classes



_____________________________________________________

Level 6:

Requirement:
- delete task

Design:
- use delete method of LinkedList

DEFAULT README.md:

# Duke User Guide

// Update the title above to match the actual product name

// Product screenshot goes here

// Product intro goes here

## Adding deadlines

// Describe the action and its outcome.

// Give examples of usage

Example: `keyword (optional arguments)`

// A description of the expected outcome goes here

```
expected output
```

## Feature ABC

// Feature details


## Feature XYZ

// Feature details
