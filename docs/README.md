# User Guide

## Structure
src

## Features 
## This are the features available in Maltese
### Add a new Todo task 
> **Usage:**
   ```
    todo <description>
   ```
> **Example:**
   ```
    todo drink coffee
   ```
  - If this task already exists on tasklist, the response will be
    ```
    Unfortunately, this task is already on your list.
    ```
  - Else, the task will be added to the tasklist and the response will be
    ```
    Got it. I've added this task:
      [T][0] drink coffee
    Now you have 5 tasks in the list.
    ```
### Add a new Deadline task 
> **code:**
   ```
    deadline <description> /by <due date>
   ```
> **example:**
   ```
    deadline buy cake /by 2024-10-20
   ```
### Add a new Event task 
> **code:**
   ```
    event <description> /from <start date> /to <end date>
   ```
> **example:**
   ```
    event attend career talk /from 2024-09-09 /to 2024-10-09
   ```
### View all tasks 👀
> **code:**
   ```
    list
   ```
### Mark task(s) as done
> **code:**
   ```
    mark <indice(s)>
   ```
> **example:**
   ```
    mark 2 3
   ```
### Unmark task(s) as undone
> **code:**
   ```
    unmark <indice(s)>
   ```
> **example**
   ```
    unmark 1
   ```
### Delete task(s)
> **code:**
   ```
    delete <indice(s)>
   ```
> **example:**
   ```
    delete 2 4
   ```
### Find task(s) containing the keyword
> **code:**
   ```
    find <keyword>
   ```
> **example:**
   ```
    find coffee
   ```
### Exit the application
> **code:**
   ```
    bye
   ```
