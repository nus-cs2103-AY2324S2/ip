# Stille User Guide


![](UI.png)

Stille is a chatbot for managing tasks via a **Command Line Interface** (CLI).
It supports basic features such as adding, finding, deleting tasks, and more.
---
# Quickstart
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest stille.jar from the repository.
3. Copy the file to the folder you want to use as the home folder.
4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar stille.jar command to run the application.
5. Start typing commands in the input field and press Enter on your keyboard or the send button on the window. See below for the 
list of commands available.

---
# Features
## Adding tasks
A task minimally consists of: 
- _Description_ defined by the user and cannot be empty
- _Status_ to indicates whether the task is completed or not

When a task is added, it is placed at the end of the internal tasklist with status set to 
not complete initially. If successful, the chatbot will reply with a confirmation message
displaying the task just added, along with the number of tasks in the list. 
See the list section for how to interpret the task shown.

Some types of tasks have starting and/or ending dates. When adding such tasks, note that
the format is strictly `YYYY-MM-DD`.

### Adding ToDo
ToDo tasks are the most basic form of tasks with no associated dates. They are best
used to represent less important jobs where time is not a concern.

Example input: `todo haircut`
```
Got it. I've added this task:
 [T][] haircut
 
Now you have 1 tasks in the list.
```

### Adding Deadline

Deadline tasks have an end date. It is suitable for tasks such as school work where 
the due date is important to take note.

Example input: `deadline assignment /by 2024-03-01`

```
Got it. I've added this task:
 [D][] assignment (by: Mar 01 2024)
 
Now you have 2 tasks in the list.
```

### Adding Event
Event tasks have both start and end date. It is meant for tasks that spans multiple days.

Example input: `event business trip /from 2024-04-01 /to 2024-04-08`

```
Got it. I've added this task:
 [E][] business trip (from: Apr 01 2024 to: Apr 8 2024)
 
Now you have 3 tasks in the list.
```

## Listing all tasks
Shows the current state of the tasklist. Each task is displayed in the order of addition.

Example input: `list`
```
Here are the tasks in your list:
 1.[T][] haircut
 2.[D][] assignment (by: Mar 01 2024)
 3.[E][] business trip (from: Apr 01 2024 to: Apr 8 2024)
```
The first square bracket indicates the type of task, while the second
shows the status, which changes to [X] if the task is completed.

## Deleting tasks
Removes a task by specifying its index in the list. To ensure that the correct task is specified, 
check with the `list` command beforehand. 

Example input: `delete 1`
```
Noted. I've removed this task:
 [T][] haircut
 
Now you have 2 tasks in the list.
```

## Finding tasks
Look for tasks where any part of description matches the words given.

Example input: `find trip`
```
Here are the matching tasks in your list:
 1.[E][] business trip (from: Apr 01 2024 to: Apr 8 2024)
```

## Mark and unmark tasks as complete
Modifies the status of the task at the provided index.

Example input: `mark 1`
```
Nice! I've marked this task as done:
 [D][X] assignment (by: Mar 01 2024)
```

Example input: `unmark 1`
```
Ok, I've marked this task as not done yet:
 [D][] assignment (by: Mar 01 2024)
```

## Update task descriptions and/or dates
Changes the details of the specified task. Note that the type of task and position in the list 
cannot be altered. The status of the task is also carried over.

Example input: `update 1 assignment 2 /by 2024-04-02`
```
Sure! Here's how the task looks like now:
 [D][] assignment 2 (by: Apr 02 2024)
```

## Exiting the application
Saves the current tasklist and closes the application. Functionally identical to 
manually closing the window.

Example input: `bye`

## Saving data
Upon running the application for the first time, a folder named `data` will be created.
Each time the application is closed, the most recent state of the tasklist is saved in there
as a file named `data.txt`. **Please do not edit the file** in any way
as the application may not be able to read in the data properly. If you encounter an error with loading 
tasks, make a copy of the file elsewhere before closing the application, as the file will
be overwritten.