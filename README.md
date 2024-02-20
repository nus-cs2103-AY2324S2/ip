# Linus the task-managing chatbot

Linus is an application used for managing tasks using Command Line Interface (CLI)

* Table of Contents
{:toc}

## Quickstart
   1. Download the jar file here
   2. Run the jar file
   3. Start adding tasks

## Things to note
   - Date variables are written like this: "dd/mm/yyyy hhhh"
      - "30/03/2024 1900"
   - Indexing starts from 1

## Features


### Saving file: `bye`

Saves the file in the task so that the file can be loaded up again

Format: `bye`


### Show current list of tasks: `list`

Brings up the current list of task

Format: `list`


### Mark task based on index: `mark`

Mark task as done

Format `mark 2`


### Unmark task based on index: `unmark`

Mark task as done

Format `unmark 2`


### Delete task based on index: `delete`

Deletes task from the list

Format `delete 2`

### Find a specific task from the list: `find`

Finds a task from the list that matches the subsequent word

Format `find homework`


### Sorts the list by criteria: `sort`

criteria: `description`, `task`, `mark`, `date`

description: sorts by alphabetical description

task: sorts by task type

mark: sorts by marked and unmarked

date: sorts by task then by date

Format `sort description`


### Adds *ToDo* task in list: `todo`

Adds todo task with description, ToDo tasks have no dates

Format `todo homework`


### Adds *Deadline* task in list: `deadline`

Adds Deadline task with description and deadline by date

Format `deadline homework \by 20/02/2024 2359`


### Adds *Event* task in list: `event`

Adds Event task with description with 2 dates, from and to

Format `event attend meeting /from 20/02/2024 1200 /to 20/02/2024 1400`
