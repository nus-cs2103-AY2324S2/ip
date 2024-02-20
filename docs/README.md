# Miku User Guide

![Product Screenshot](Ui.png)

# Task Features

## Adding deadlines

Adding a task: `todo`, `deadline`, `event`

Format: 

`todo <name>`: Saves a todo task.

`deadline <name> /by <due date>`: Saves a deadline.

`event <name> /from <start date> /to <end date>`: Saves an event.

## List tasks

List out all stored tasks.

Format: `list`


## Mark / Unmark task completion

Marks / unmarks status of task.

Format:

`mark <index>`: Marks task as completed.

`unmark <index>`: Marks task as not completed.

Example as demonstrated in the picture [above](#miku-user-guide).


## Delete task

Delete task off the TaskList.

Format:

`delete <index>`: Delete task.

Refer to [mark](#mark--unmark-task-completion) for its similar usage.

## Find task

Find tasks matching the specified keyword.

Format:

`find <keyword>`: Find tasks that matches with its name.

# Loan Features

## Track given loan

Adds a given loan to the loan record.

Format:

`lend <loan details> /amount <amount lent>`: Tracks a loan given to a specified party.

## Track taken loan

Adds a taken loan to the loan record.

Format:

`owe <loan details> /amount <amount taken>`: Tracks a loan taken from a specified party.

## Mark loan as returned

Marks loan as returned, either from or to the party attached to the loan.

Format:

`paid <loan index>`: Mark loan as returned.