# Pookie User Guide

![](/docs/Ui.png)

pookie is a chatbot that helps you to keep track of your tasks!

# Features
## 1. Add todo

Adds a todo task.

Format: `todo DESCRIPTION`

Examples:
- `todo write user guide`
- `todo submit ip`

## 2. Add event

Adds an event task that contains a start and end time

Format: `event DESCRIPTION /from START_TIME /to END_TIME`

Examples: 
- `event tp project meeting /from 2pm /to 4pm`

## 3. Add deadline
Adds a deadline task that contains a deadline

Format: `deadline DESCRIPTION /by DATE&TIME(yyyy-mm-dd format)`

Examples: 
- `deadline return book /by 2/12/2019 1800`

## 4. List
List out all added tasks

Format: `list`

## 5. Mark tasks
Marks a specified task

Format: `mark TASK_NUMBER`

Examples: `mark 4`

## 6. Unmark tasks
Unmarks a specificied task

Format: `unmark TASK_NUMBER`

Examples: `unmark 4`

## 7. Mark and unmark multiple tasks in a single command
Marks/unmarks multiple tasks in a single command

Format:
- `mark TASK_NUMBERS...`
- `unmark TASK_NUMBERS...`

Examples:
- `mark 1 3 5`
- `unmark 2 4 6`

## Exit 
Ends the program

Format:
- `bye`
