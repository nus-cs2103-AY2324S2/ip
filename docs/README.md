## Bird User Guide

**Bird** is a simple CLI-optimized task manager application.


**1. Listing and Searching Tasks**

* **list**: 
    * Description: Displays a numbered list of all the tasks in your list.
    * Example Output:
       ```
       Here are the tasks in your list:
       1.[T][] Homework
       2.[D][] Shopping (by: Feb 24 2024)
      ``` 

* **find KEYWORD**: 
    * Description: Searches for tasks containing the specified keyword and displays matching results.
    * Example Input: `find home`
    * Example Output:
       ```
       Here are the matching tasks in your list:
       1.[T][] Homework
       ```

**2. Adding Tasks**



* **deadline NAME /by BY-DATE**: 
    * Description: Adds a deadline task with a specific due date.
    * Example Input: `deadline Submit project /by 2024-02-25`
    * Example Output:
      ```
      Got it. I've added this task:
      [D][] Submit project (by: Feb 25 2024)
      Now you have 1 tasks in the list.
      ```
* **event NAME /from FROM-DATE /to TO-DATE**: 
    * Description: Adds an event task with a start and end date. 
    * Example Input: `event Study /from 2024-02-24  /to 2024-02-28` 
    * Example Output:
      ```
      Got it. I've added this task:
      [E][] Study (from: Feb 24 2024 to: Feb 28 2024)
      Now you have 1 tasks in the list.
      ```
* **todo NAME**: 
    * Description: Adds a simple to-do task without a due date.
    * Example Input: `todo Call dentist`
    * Example Output:
      ```
      Got it. I've added this task:
      [T][] Call Dentist
      Now you have 1 tasks in the list.
      ```

* **Important! - Dates are to be formatted as yyyy-mm-dd**


**3. Modifying Tasks**

* **mark INDEX**: 
    * Description: Marks a task as completed. 
    * Example Input: `mark 2`
    * Output: The list updates, showing the 2nd task as completed ([X] instead of [ ])
* **unmark INDEX**:
    * Description: Marks a task as incomplete.
    * Example Input: `unmark 1`
    * Output:  The list updates, showing the 1st task as incomplete ([ ] instead of [X])
* **delete INDEX**: 
    * Description: Deletes a task from the list.
    * Example Input: `delete 3`
    * Output: The 3rd task is removed, and the list is renumbered accordingly.


* Note: **INDEX** follows 1-based Indexing. 


**4. Sorting**

* **sort**: 
    * Description: Sorts the list by due date in ascending order. For event tasks, the TO-DATE is used for comparison. 
      Todo tasks (which do not have dates) are placed last.
    * Example Input: `sort`
    * Example Output:
      ```
      Here are the tasks in your list:
      1. [D][] Task (by: Feb 20 2024)
      2. [E][] Study session (from: Feb 15 2024 to: Feb 28 2024)
      3. [T][] Call dentist
      ```


**5. Exiting**

* **bye**: 
    * Description: Quits the application.
