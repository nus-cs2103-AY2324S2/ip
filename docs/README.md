# Poe User Guide

// Update the title above to match the actual product name

// Product screenshot goes here

// Product intro goes here
Poe is a desktop app for managing tasks such as todos, deadlines and events, optimized for use via a Command Line Interface (CLI)

Sections:
[Features](https://leepoeaik.github.io/ip/#features)
- [View Help Guide : `help`](https://leepoeaik.github.io/ip/#view-help-guide)
- [Adding todo : `todo`](https://leepoeaik.github.io/ip/#adding-todo)
- [Adding deadline : `deadline`](https://leepoeaik.github.io/ip/#adding-deadline)
- [Adding event : `event`](https://leepoeaik.github.io/ip/#adding-event)
- [Mark task : `mark`](https://leepoeaik.github.io/ip/#mark-task)
- [Unmark task : `unmark`](https://leepoeaik.github.io/ip/#unmark-task)
- [Delete task : `delete`](https://leepoeaik.github.io/ip/#delete-task)
- [Find task : `find`](https://leepoeaik.github.io/ip/#find-task)
- [Display list : `list`](https://leepoeaik.github.io/ip/#display-list)
- [Exit program : `bye`](https://leepoeaik.github.io/ip/#exit-program)
- [Saving data](https://leepoeaik.github.io/ip/#saving-data)
- [Reminder feature](https://leepoeaik.github.io/ip/#reminder-feature)



### Features
>[!NOTE]
>- Words in `UPPER_CASE` are parameters to be supplied by the user
>  e.g. in `todo NAME`, `NAME` is a parameter which can be used as `todo buy groceries`
>- Extraneous parameters for commands that do not take in parameters (such as `help`, `list` and `bye`) will be ignored.
>  e.g. if the command specifies help 123, it will be interpreted as help.

## View Help Guide `help`

Displays all possible commands 

Format: `help` 

## Adding todo `todo`
Adds a todo into the task list 

Format: `todo NAME`

// Give examples of usage

Example: `todo buy groceries`

- Creates a todo task with `NAME` : 'Buy groceries'

## Adding deadline `deadline`

// Describe the action and its outcome.
Adds a deadline into the task list 

Format: `deadline NAME /by DEADLINE` 

- `DEADLINE` must be in the format of yyyy-mm-dd

// Give examples of usage

Example: `deadline linear algebra assignment 1 /by 2024-03-01`

- Creates a deadline with `NAME` :'linear algebra assignment 1' with `DEADLINE` of 1 March 2024

## Adding event `event`
Adds an event into the task list 

Format: `event NAME /from START_DATE /to END_DATE`

- `START_DATE` and `END_DATE` must be in the format of yyyy-mm-dd

// Give examples of usage

Example: `event blockchain hackathon /from 2024-05-01 /to 2024-05-03`

- Creates an event with `NAME` : 'blockchain hackathon' with `START_DATE` of 1 May 2024 and `END_DATE` of 3 May 2024

## Mark task `mark`
Marks an existing task as done

Format: `mark INDEX`
- `INDEX` must be a positive number and reflect on the index as shown in `LIST` 

## Unmark task `unmark`
Unmarks an existing task as not done

Format: `unmark INDEX`
- `INDEX` must be a positive number and reflect on the index as shown in `LIST` 


## Delete task `delete`
Deletes task from task list

Format: `delete INDEX`
- `INDEX` must be a positive number and reflect on the index as shown in `LIST` 


## Find task `find`
Find task using name of task

Format: `find NAME`

Example: `find buy groceries`

## Display list `list`

Displays all current tasks in the lsit

Format: `list` 

## Exit program `bye`

Terminates program and exits.

Format: `bye` 

## Saving data

// Feature details
Poe program automatically saves data into hard disk any time changes occur


## Reminder feature

// Feature details
Upon booting, any saved tasks which are upcoming (<= 2 days from current date) would display as a greeting screen

Example : Today's date is 23 February 2024, there is a deadline task `NAME` : homework 1 `DEADLINE` : 24 February 2024

<img width="248" alt="image" src="https://github.com/leepoeaik/ip/assets/99176866/dc043f03-d2e3-47db-9ec4-64804e5afeee">
