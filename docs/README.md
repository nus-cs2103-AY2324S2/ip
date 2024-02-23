# Leo User Guide
![Ui.png](https://github.com/meenulekha/ip/blob/master/docs/Ui.png)

Welcome to Leo, a task management application that helps you stay organized and keep track of your tasks. This user guide will walk you through the various features and functionalities of Leo.

##Product Introduction
Leo is a simple and intuitive task management application designed to assist you in managing your tasks effectively. With Leo, you can easily add, mark, unmark, delete, and find tasks, making task management a breeze.

## Adding Tasks, deadlines and events

Leo allows you to add tasks with deadlines. Follow the steps below to add a deadline to your tasks.

Action: deadline <task_description> /by <deadline_date>
<task_description>: Description of the task.
<deadline_date>: Deadline date in the format dd/MM/yyyy or MMM dd yyyy.


```
deadline Complete report /by 28/02/2024

```
expected output:
```
Got it. I've added this task:
[D] Complete report (by: Feb 28, 2024)
Now you have 1 task(s) in your list!
------------------------------
```

## Adding Events

    To add an event to your tasks, use the following command:

    **Command:** `event <event_description> /from <start_date> /to <end_date>`

    - **<event_description>**: Description of the event.
    - **<start_date>**: Start date of the event in the format `dd/MM/yyyy` or `MMM dd yyyy`.
    - **<end_date>**: End date of the event in the format `dd/MM/yyyy` or `MMM dd yyyy`.

    **Example:**

    ```plaintext
    event Team meeting /from 28/02/2024 /to 29/02/2024
    ```

    **Expected Output:**

    ```
    Got it. I've added this task:
    [E] Team meeting (from: Feb 28, 2024, to: Feb 29, 2024)
    Now you have 2 task(s) in your list!
    ------------------------------
    ```
## Deleting a Task

    To delete a task, use the following command:

    **Command:** `delete <task_number>`

    - **<task_number>**: The number associated with the task in the list.

    **Example:**

    ```plaintext
    delete 1
    ```

    **Expected Output:**

    ```
    Noted. I've removed this task:
    [D] Complete report (by: Feb 28, 2024)
    Now you have 1 task(s) in the list.
    ------------------------------
    ```
## Finding Tasks

    To find tasks containing a specific keyword, use the following command:

    **Command:** `find <keyword>`

    - **<keyword>**: The keyword to search for in task descriptions.

    **Example:**

    ```plaintext
    find meeting
    ```

    **Expected Output:**

    ```
    Here are the matching tasks in your list:
    1. [E] Team meeting (from: Feb 28, 2024, to: Feb 29, 2024)
    ------------------------------
    ```

## Chronologically sorted deadlines!

"list" will allow you to see all of your tasks, deadlines and events in a chronological order


## Find your tasks easily!
"find" will allow you to find certain tasks, deadlines and events that you are looking for!

***Feel free to explore Leo's other features by using the commands mentioned in the user guide. 

  Happy tasking with Leo!
