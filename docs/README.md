# Arona User Guide

Arona is a chatbot app for managing your schedules and tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). Arona has a cheerful personality that is sure to make your day.

## Adding deadlines

Add a deadline of your task to Arona

Format: ``` deadline [DESCRIPTION] [/by DATE] ```

Example: 
- ``` deadline run /by 2020-12-31 ``` Add a deadline task called run to be done by 2020-12-31.
- ``` deadline submit lab report /by 2021-10-20 ``` Add a deadline task called submit lab report to be done by 2021-10-20.

## Adding todos

Add a todo of your task to Arona

Format: ``` todo [DESCRIPTION] ```

Example: 
- ``` todo run ``` Add a todo task called run.
- ``` todo submit lab report ``` Add a todo task called submit lab report.

## Adding events

Add a event of your task to Arona

Format: ``` event [DESCRIPTION] [/from DATE] [/to DATE] ```

Example: 
- ``` event run /from 2020-12-31  /to 2021-01-01 ``` Add a event task called run that occur from 2020-12-31 to to 2021-01-01.
- ``` event submit lab report /from 2021-10-20 /to 2022-10-20 ``` Add a event task called submit lab report that occur from 2021-10-20 to 2022-10-20.
  
## Mark tasks as completed

Mark a task as completed

Format: ``` mark [INDEX] ```

Example:
- ``` mark 1 ``` Mark task 1 on the list as completed.

## Mark tasks as incomplete

Mark a task as incomplete

Format: ``` unmark [INDEX] ```

Example:
- ``` unmark 2 ``` Mark task 2 on the list as incomplete.

## Delete a task

Delete a task from Arona

Format: ``` delete [INDEX] ```

Example:
- ``` delete 3 ``` Delete task 3 on the list.

## List out all the tasks

List all tasks from your list

Format: ``` list ```

Example:
- ```list ``` List all tasks from your list.

# Find a specific task

Find a specific task you are looking for from Arona

Format: ``` find [DESCRIPTION] ```

Example:
- ``` find errands ``` Find all tasks with matching description 'errands'
