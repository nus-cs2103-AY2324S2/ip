# Chat User Guide

chat is a desktop application for managing tasks, optimized for use via a Command Line Interface (CLI). 

## Environment

1. ensure you have Java 11 installed on your computer
2. download the latest version of chat.jar from [here](

## All features

1. [Adding a task](#adding-a-task)
2. [Listing all tasks](#listing-all-tasks)
3. [Marking or unmarking a task as done](#marking-a-task-as-done)
4. [Deleting a task](#deleting-a-task)
5. [Finding tasks related to input](#finding-a-task)


## Adding a task
### Type of task
- Todo
- Deadline
- Event

### Prompt
- Todo: todo {task description}

    _e.g. adding task todo `read book`_
    ```
    todo read book
    ```

- Deadline: deadline {task description} /by {date}

    _e.g. adding task deadline `return book` before `2021-09-17`_
    ```
    deadline return book /by 2021-09-17
    ```
  
- Event: event {task description} /from {date} /to {date}

    _e.g. adding task event `project meeting` from `2021-09-17` to `2021-09-18`_
    ```
    event project meeting /from 2021-09-17 /to 2021-09-18
    ```
  ### Expected output for all adding tasks
    ```
    Got it. I've added this task: {task name}
    ```
> Note: the following eg for each command will be using the output of the above prompt

## Listing all tasks
### Prompt
- list

    _e.g. listing all tasks_
    ```
    list
    ```
  ### Expected output
    ```
    Here are the tasks in your list:
    1. [T][ ] read book
    2. [D][ ] return book (by: Sep 17 2021)
    3. [E][ ] project meeting (from: Sep 17 2021 to: Sep 18 2021)
    ```

## Deleting a task
### prompt
- delete {task number}

    _e.g. deleting task 3_
    ```
    delete 3
    ```
    ### Expected output
    ```
    Noted. I've removed this task: {task number}
    ```
    this can be verified by listing all tasks:
    ```
    list
    Here are the tasks in your list:
    1. [T][ ] read book
    2. [D][ ] return book (by: Sep 17 2021)
    ```

## Marking a task as done
### Prompt
- mark {task name}

    _e.g. marking task 1 as done_
    ```
    mark read book
    ```
    ### Expected output
    ```
    Nice! I've marked this task as done: {task name}
    ```
    this can be verified by listing or finding tasks:
  ```
  list
  Here are the tasks in your list:
  1. [T][X] read book
  2. [D][ ] return book (by: Sep 17 2021)
  3. [E][ ] project meeting (from: Sep 17 2021 to: Sep 18 2021)
  ```

- unmark {task name}

  _e.g. unmarking read book_
  ```
  unmark read book
  ```
  ## Expected output
  ```
  unmarked 
  I've unmarked this task as done: {task name}
  ```
  this can be verified by listing all tasks:
  ```
  list
  Here are the tasks in your list:
  1. [T][ ] read book
  2. [D][ ] return book (by: Sep 17 2021)
  3. [E][ ] project meeting (from: Sep 17 2021 to: Sep 18 2021)
  ```

## Finding a task
### prompt
- find {keyword}

    e.g. finding tasks related to `book`
    ```
    find book
    ```
    ### Expected output
    ```
    Here are the matching tasks in your list:
    1. [T][ ] read book
    2. [D][ ] return book (by: Sep 17 2021)
    ```
  