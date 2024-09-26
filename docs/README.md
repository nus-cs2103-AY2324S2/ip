# Maltese User Guide 🐕

## Set up
- Ensure you have Java 11 installed in your computer
- Open up your terminal and locate the maltese.jar file
- Enter `java -jar maltese.jar`
- As this is your first time setting up, there is a high chance that you might encounter these error messages
  
  ```
  WARNING: Unsupported JavaFX configuration: classes were loaded from 'unnamed module @5b2ba064'
  File not found: ./data/tasks.txt
  Creating the file...
  Sorry ./data/tasks.txt (No such file or directory)
  Error loading data from file.
  ```
  
  You may choose to ignore these error messages as **Maltese will still run as intended**.
  On subsequent runs, however, you should only encounter this error message as by now, your directory should contain ./data/tasks.txt

  ```
  WARNING: Unsupported JavaFX configuration: classes were loaded from 'unnamed module @5b2ba064'
  ```

- By now, Maltese should be up and running! Do take a look at the features available to get the best out of Maltese!
  

## Features 
## This are the features available in Maltese
### Add a new Todo task 
**Usage:**
   ```
    todo <description>
   ```
**Example:**
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
**Usage:**
   ```
    deadline <description> /by <due date>
   ```
**Example:**
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
**Usage:**
   ```
    event <description> /from <start date> /to <end date>
   ```
**Example:**
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
**Usage:**
   ```
    list
   ```
**Example:**
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
**Usage:**
   ```
    mark <indice(s)>
   ```
**Example:**
   ```
    mark 2 3
   ```
  - The expected output will be
    ```
    Nice! I've marked the following tasks as done:
    [D][X] buy cake (by: 20 OCTOBER 2024)
    [E][X] attend career talk (from: 9 OCTOBER 2024 to: 9 OCTOBER 2024)
    ```
    
### Unmark task(s) as undone
**Usage:**
   ```
    unmark <indice(s)>
   ```
**Example**
   ```
    unmark 2
   ```
  - The expected output will be
    ```
    OK, I've marked this task as not done yet:
    [D][0] buy cake (by: 20 OCTOBER 2024)
    ```
    
### Delete task(s)
**Usage:**
   ```
    delete <indice(s)>
   ```
**Example:**
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
**Usage:**
   ```
    find <keyword>
   ```
**Example:**
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
    
### Change directory of tasklist ( note that existing tasklist will overwrite the tasklist in new directory if any )
**Usage:**
   ```
    change <file path>
   ```

**Example:**
   ```
    change ./data/sample.txt
   ```
  - The expected output will be
    ```
    Changing file path to ./data/sample.txt
    ```
    
### Exit the application
**Usage:**
   ```
    bye
   ```
