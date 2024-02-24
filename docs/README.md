# FICIN

![FICIN Chat Bot](Ui.png)

FICIN is **VERY EASY** chat bot which can help you to track and record your tasks

## **Features:**

* Loading & Saving Task
* Addition of tasks with ```TODO```, ```DEADLINE```, ```EVENT```
  * Date Command for Deadline and Event (```TODAY```, ```TOMORROW```)
* Show lists of Tasks with ```LIST```
* Deletion of tasks with ```DELETE```
* Search tasks with ```FIND```
* Mark the task is done/undone with ```MARK``` and ```UNMARK```
* Show commands that can guide user with ```HELP```

## **User Guide**
____________________________________

### **TODO**: 
Simply record your task.

Command format: ```todo <task>```

```
//Example of Todo
Todo Study 
```
### **DEADLINE**: 
Set deadline for your task with command ```/by```.
* The format of date can be either ```YYYY/MM/dd```or ```YYYY/MM/dd HHmm```.
* It also allows to type ```today``` and/or ```tomorrow``` in ```/by```.

Command format: ```deadline <task> /by YYYY/MM/dd HHmm```
<br> or <br> 
Command format: ```deadline <task> /by YYYY/MM/dd```
```
// Example of Deadline
Deadline Assignment /by tomorrow
```

### **EVENT**: 
Set event time for your task with command ```/from ... /to ...```.
* The format of date can be either ```YYYY/MM/dd```or ```YYYY/MM/dd HHmm```.
* It also allows to type ```today``` and/or ```tomorrow``` in ```/from``` and ```/to```.

Command format: ```event <task> /from YYYY/MM/dd HHmm /to YYYY/MM/dd HHmm```
<br> **or** <br>
Command format: ```event <task> /from YYYY/MM/dd /to YYYY/MM/dd```
<br> **or** <br>
Command format: ```event <task> /from YYYY/MM/dd /to YYYY/MM/dd HHmm```

```
// Example of Event
Event Travel /from today /to 2024/04/29 

// Set deadline with specific hour (11pm)
Event Travel /from 2024/02/26 /to 2024/04/29 2300 
```

### **LIST**:

List every tasks which are saved

Command format: ```list```

```
list // User Input
 Here is the list of tasks:
 1.[T][ ] Study life
 2.[D][ ] IP (by: Feb 22 2024)
```

### **DELETE**:

Delete task which is not needed by typing its number starting from 1

Command format: ```delete <task number>```

```
// Same task list is given 

delete 1 // User Input
 Got it. I have removed this task:
   [T][ ] Study Life
 Now you have 1 tasks in the list.
```

### **FIND**:

Search specific task with keyword

Command format: ```find <task>```

```
find IP // User Input
 Here is the list of tasks:
 1.[D][ ] IP (by: Feb 22 2024)
```

### **MARK & UNMARK**:

Mark & Unmark the task

**Mark:**
Command format: ```mark <task number>```

```
mark 1 // User Input
 Great! I have marked the task as done:
   [D][X] IP (by: Feb 22 2024)
```

**Unmark:** Command format: ```unmark <task number>```

```
unmark 1 // User Input
 Sure, I have marked this task as not done yet:
   [D][ ] IP (by: Feb 22 2024)
```

### **HELP**:

Shows the list of commands that is used in the Chat bot

Command format: ```help```

```
help // User Input
Here are the available commands:
  - help: show commands
  - list: List all tasks
  - find <word>: Find tasks
  - todo <task>: Add a todo task
  - deadline <task> /by <date>: Add a deadline task
  - event <task> /from <date> /to <date>: Add an event task
    - date can be done by YYYY/MM/dd or YYYY/MM/dd HHmm 
    - today & tomorrow can be used for date
```

### **BYE**
_**CAUTION! Tasks can only be saved after ```bye``` command properly proceed**_

Type ```bye``` on Chat bot to exit

```
bye // User Input
Bye! See you again!
```

### **Saving**
Tasks are automatically saved in ```../data/tasks.txt``` after ```bye``` command typed properly

### Summarized Guide
  *  ```todo <task>```
  *  ```deadline <task> /by <date>```
  *  ```event <task> /from <date> /to <date>```
  *  ```list```
  *  ```delete <task number>```
  *  ```find <word>```
  *  ```mark 1``` & ```unmark 1```
  *  ```help```
  *  ```bye```
