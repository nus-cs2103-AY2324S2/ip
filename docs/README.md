# Maltese User Guide

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
> **Usage:**
   ```
    deadline <description> /by <due date>
   ```
> **Example:**
   ```
    deadline buy cake /by 2024-10-20
   ```
  - If this task already exists on tasklist, the response will be
    ```
    Unfortunately, this task is already on your list.
    ```
  - Else, the task will be added to the tasklist and the response will be
    ```
    Got it. I've added this task:
      [D][0] buy cake (by: 20 OCTOBER 2024)
    Now you have 6 tasks in the list.
    ```
### Add a new Event task 
> **Usage:**
   ```
    event <description> /from <start date> /to <end date>
   ```
> **Example:**
   ```
    event attend career talk /from 2024-09-09 /to 2024-10-09
   ```
  - If this task already exists on tasklist, the response will be
    ```
    Unfortunately, this task is already on your list.
    ```
  - Else, the task will be added to the tasklist and the response will be
    ```
    Got it. I've added this task:
      [E][0] attend career talk (from: 9 OCTOBER 2024 to: 9 OCTOBER 2024)
    Now you have 7 tasks in the list.
    ```
### View all tasks 
> **Usage:**
   ```
    list
   ```
> **Example:**
   ```
    list
   ```
  - The expected output will be
    
    ```
    Here are the tasks in your list:
    1. [T][0] drink coffee
    2. [D][0] buy cake (by: 20 OCTOBER 2024)
    3. [E][0] attend career talk (from: 9 OCTOBER 2024 to: 9 OCTOBER 2024)
    ```
    
### Mark task(s) as done
> **Usage:**
   ```
    mark <indice(s)>
   ```
> **Example:**
   ```
    mark 2 3
   ```
  - The expected output will be
    ```
    Here are the tasks in your list:
    1. [T][0] drink coffee
    2. [D][X] buy cake (by: 20 OCTOBER 2024)
    3. [E][X] attend career talk (from: 9 OCTOBER 2024 to: 9 OCTOBER 2024)
    ```
### Unmark task(s) as undone
> **Usage:**
   ```
    unmark <indice(s)>
   ```
> **Example**
   ```
    unmark 2
   ```
  - The expected output will be
    ```
    Here are the tasks in your list:
    1. [T][0] drink coffee
    2. [D][0] buy cake (by: 20 OCTOBER 2024)
    3. [E][X] attend career talk (from: 9 OCTOBER 2024 to: 9 OCTOBER 2024)
    ```
### Delete task(s)
> **Usage:**
   ```
    delete <indice(s)>
   ```
> **Example:**
   ```
    delete 2 1
   ```
  - The expected output will be
    ```
    Noted, I have removed the following tasks:
    [D][0] buy cake (by: 20 OCTOBER 2024)
    [T][0] drink coffee
    Now you have 1 task left in the list.
    ```
### Find task(s) containing the keyword
> **Usage:**
   ```
    find <keyword>
   ```
> **Example:**
   ```
    find coffee
   ```
  - If there are matching tasks found, the response will be
    ```
    Here are the matching tasks in your list:
    1. [D][0] buy coffee cake (by: 20 OCTOBER 2024)
    2. [T][0] drink coffee
    ```
  - Else, the response will be
    ```
    No matching tasks found.
    ```
### Change directory of tasklist
> **Usage:**
   ```
    change <file path>
   ```
> **Example:**
   ```
    change ./data/sample.txt
   ```
  - The expected output will be
    ```
    Changing file path to ./data/sample.txt
    ```
### Exit the application
> **Usage:**
   ```
    bye
   ```
