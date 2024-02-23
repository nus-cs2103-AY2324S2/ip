# Cruisey User Guide

### _Cruise_ through your tasks with your one and only Tom Cruise! 🚢
<br>

![Ui](https://github.com/Teee728/ip/assets/122243742/66c403aa-1266-4a87-b430-7dc35ca68650)

<br>

### Say hello to _Cruisey_, your personal task list manager!
The O.G Maverick here keeps track of the tasks you have to get done.
<br>


To give it a try, follow the steps given below:
  1. Download the ```.jar``` file [here](https://github.com/Teee728/ip/releases/tag/A-Release)
  2. Ensure you have ```JDK 11``` installed on your local machine. Otherwise, you can download it from [here](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html)
  3. Click on the ```jar``` file once downloaded and you will see your application running. Have fun! 😄

## Features
 Here is a list of features Cruisey can do:
  1. Add/Delete tasks, deadlines and events.
  2. Display your task list
  3. Mark and unmark tasks in your list
  4. Attach priority levels to different tasks
  5. Search for specific tasks by keywords 

### 1. todo
> What it does 

The ```todo``` command helps you create a task for you to do.

> Example

```todo get iP done```

The chatbot creates the new task, "get iP done" into your task list.

> Expected output
```
   Coool, I've added this task:
   [T][] get iP done
    Now you have 1 task in the list.
```

### 2. deadline
> What it does 

The ```deadline``` command helps you create a task with a deadline.

> Example

```deadline get iP done /by 23/02/2024 2359```

The chatbot creates the new task, "get iP done" into your task list.
The ```/by``` tag is important for this command as it helps you set a date to complete this task.

> Expected output
```
   Coool, I've added this task:
   [D][] get iP done (by: Feb 23 2024 23:59)
    Now you have 2 tasks in the list.
```

### 3. event
> What it does 

The ```event``` command helps you create an event you have to attend.

> Example

```event project meeting /from 12/12/2024 1200 /to 12/12/2024 1300```

The chatbot creates a new event, "project meeting" into your task list.
The ```/from``` and ```/to``` tags are important for this command as you need it to set the event duration.

> Expected output
```
   Coool, I've added this task:
   [E][] project meeting (from: Dec 12 2024 12:00 to: Dec 12 2024 13:00)
    Now you have 3 tasks in the list.
```

### 4. mark
> What it does 

The ```mark``` command helps you mark a task done.

> Example

```mark 1```

The chatbot marks the first task in the list.
* The index number after the command is important.

> Expected output
```
   Way to go! I've marked this task as done:
   [T][X] get iP done
```

### 5. unmark
> What it does 

The ```unmark``` command helps you mark a task as undone.
* The index number after the command is important.

> Example

```unmark 1```

The chatbot unmarks the first task in the list.

> Expected output
```
  Okayy, I've marked this task as not done yet:
   [T][] get iP done
```

### 6. delete
> What it does 

The ```delete``` command helps you delete a task from the list.
* The index number after the command is important.

> Example

```delete 1```

The chatbot deletes the first task in the list.

> Expected output
```
   Noted, I've removed this task:
   [T][] get iP done
    Now you have 2 tasks in the list.
```

### 7. list
> What it does 

The ```list``` command displays the whole tasklist.

> Example

```
list works?
```

The chatbot displays all your tasks.

> Expected output
```
   Here are the tasks in your list:
   1.[D][] get iP done (by: Feb 23 2024 23:59)
   2.[E][] project meeting (from: Dec 12 2024 12:00 to: Dec 12 2024 13:00) 
```

### 8. find
> What it does 

The ```find``` command helps you find a task by a certain keyword.

> Example

```find project```

The chatbot finds the tasks matching with "project" from the tasklist.

> Expected output
```
  Here are the tasks matching with "project" in your list:
   1.[E][] project meeting (from: Dec 12 2024 12:00 to: Dec 12 2024 13:00)
```

### 9. /p (priority)
> What it does 

The ```/p``` command helps you attach a priority to a task.
* There are three levels of priority:
  - ```HIGH```
  - ```MEDIUM```
  - ```LOW```

> Example

```todo get iP done /p HIGH```

The chatbot creates the new task, "get iP done" with a priority "HIGH" into your task list.

> Expected output
```
   Coool, I've added this task:
   [T][] get iP done [Priority: HIGH]
    Now you have 3 task in the list.
```

### 10. bye
> What it does 

The ```bye``` command helps you exit from the application.

> Example

```exit```

The chatbot closes after a 2 second delay.

> Expected output
```
   Bye! Keep Cruisn' :))
```
