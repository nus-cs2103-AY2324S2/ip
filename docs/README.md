# Poe User Guide

Poe is a desktop app for managing tasks such as todos, deadlines and events, optimized for use via a Command Line Interface (CLI)

Sections:

[Features](https://leepoeaik.github.io/ip/#features)
- [View Help Guide : `help`](https://leepoeaik.github.io/ip/#view-help-guide-help)
- [Adding todo : `todo`](https://leepoeaik.github.io/ip/#adding-todo-todo)
- [Adding deadline : `deadline`](https://leepoeaik.github.io/ip/#adding-deadline-deadline)
- [Adding event : `event`](https://leepoeaik.github.io/ip/#adding-event-event)
- [Mark task : `mark`](https://leepoeaik.github.io/ip/#mark-task-mark)
- [Unmark task : `unmark`](https://leepoeaik.github.io/ip/#unmark-task-unmark)
- [Delete task : `delete`](https://leepoeaik.github.io/ip/#delete-task-delete)
- [Find task : `find`](https://leepoeaik.github.io/ip/#find-task-find)
- [Display list : `list`](https://leepoeaik.github.io/ip/#display-list-list)
- [Exit program : `bye`](https://leepoeaik.github.io/ip/#exit-program-bye)
- [Saving data](https://leepoeaik.github.io/ip/#saving-data)
- [Reminder feature](https://leepoeaik.github.io/ip/#reminder-feature)

<br>

# Features
> [!NOTE]
>- Words in `UPPER_CASE` are parameters to be supplied by the user
>  e.g. in `todo NAME`, `NAME` is a parameter which can be used as `todo buy groceries`
>- Extraneous parameters for commands that do not take in parameters (such as `help`, `list` and `bye`) will be ignored.
>  e.g. if the command specifies `help` 123, it will be interpreted as `help`.

<br>

## View Help Guide `help`

Displays all possible commands 

Format: `help` 

<br>

## Adding todo `todo`
Adds a todo into the task list 

Format: `todo NAME`

Example: `todo buy groceries`

- Creates a todo task with `NAME` : 'Buy groceries'

<br>

## Adding deadline `deadline`

Adds a deadline into the task list 

Format: `deadline NAME /by DEADLINE` 

- `DEADLINE` must be in the format of yyyy-mm-dd

Example: `deadline linear algebra assignment 1 /by 2024-03-01`

- Creates a deadline with `NAME` :'linear algebra assignment 1' with `DEADLINE` of 1 March 2024

<br>

## Adding event `event`
Adds an event into the task list 

Format: `event NAME /from START_DATE /to END_DATE`

- `START_DATE` and `END_DATE` must be in the format of yyyy-mm-dd

Example: `event blockchain hackathon /from 2024-05-01 /to 2024-05-03`

- Creates an event with `NAME` : 'blockchain hackathon' with `START_DATE` of 1 May 2024 and `END_DATE` of 3 May 2024

<br>

## Mark task `mark`
Marks an existing task as done

Format: `mark INDEX`
- `INDEX` must be a positive number and reflect on the index as shown in `LIST`

<br>

## Unmark task `unmark`
Unmarks an existing task as not done

Format: `unmark INDEX`
- `INDEX` must be a positive number and reflect on the index as shown in `LIST`

<br>

## Delete task `delete`
Deletes task from task list

Format: `delete INDEX`
- `INDEX` must be a positive number and reflect on the index as shown in `LIST`

<br>

## Find task `find`
Find task using name of task

Format: `find NAME`

Example: `find buy groceries`

<br>

## Display list `list`

Displays all current tasks in the lsit

Format: `list` 

<br>

## Exit program `bye`

Terminates program and exits.

Format: `bye` 

<br>

## Saving data

Poe program automatically saves data into hard disk any time changes occur

<br>

## Reminder feature

Upon booting, any saved tasks which are upcoming (<= 2 days from current date) would display as a greeting screen

Example : Today's date is 23 February 2024, there is a deadline task `NAME` : homework 1 `DEADLINE` : 24 February 2024

<img width="650" alt="image" src="https://github.com/leepoeaik/ip/assets/99176866/dc043f03-d2e3-47db-9ec4-64804e5afeee">
