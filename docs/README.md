# ShaunBot User Guide


## <font color = "darkcyan"> Introduction </font>

Welcome to ShaunBot,

Designed to assist you in organizing your tasks, events, and deadlines, ShaunBot ensures efficient tracking.

This user guide will introduce you to the remarkable features of ShaunBot and guide you on how to utilize them for maximum effectiveness.


## <font style = "Ariel" color="darkslate"> Features </font>



### <font color = "darkcyan"> Managing Tasks </font>


Three types of tasks are handles by ShaunBot :


1) **<font color = "lightblue">Deadline</font>** - These tasks have specific deadlines.

example- Giving a gift on 14th February 2023 by 10:00.



2) **<font color = "lightblue">ToDo</font>**  - These tasks don't have specific deadlines.

example- Todo washing clothes




3) **<font color = "lightblue">Events</font>**  - These are the events that happen on a prticular date-time and have an end date-time also.

example- Enjoy vacation from 25th September 2023 1200 to 28th September 2023 1200






## <font style = "Ariel" color="darkslate"> Usage </font>


### `todo` -  <font color = "darkcyan" size = "3"> Add a ToDo Task </font>


Adds a Todo Task


**Example of usage:** ```todo project```



**Expected outcome:**


Adds a task to the list and displays a message upon succesful addition to the tasklist.




```

Boss!!. I've added this task:

[T][] project for CS2103


Now you have 5 tasks in the list.

```


### `deadline` -  <font color = "darkcyan" size = "3"> Add a Deadline Task </font>


Adds a Deadline Task


**Example of usage:** ```deadline return book /by 2023-09-22 2359```



**Expected outcome:**


Adds a Deadline task to the list with date-time and displays a message upon succesful addition to the tasklist.




```

Boss!!. I've added this task:

[D][] return novel (by: Sep 2022 25 23:59)


Now you have 6 tasks in the list.

```

### `event` -  <font color = "darkcyan" size = "3"> Add an Events Task </font>


Adds an Events Task, the date and time entered should follow the format YYYY-MM-DD hhMM


**Example of usage:** ```event attend a conference /from 2023-07-17 1200 /to 2023-09-17 1200```



**Expected outcome:**


Adds an event task to the list and displays a message upon succesful addition to the tasklist.


```

Boss!!. I've added this task:

[E][] attend a meeting (from: Sep 23 2023 10:00 AM to: 12:00 PM)


Now you have 7 tasks in the list.

```


### `find` -  <font color = "darkcyan" size = "3"> Find a relevant task </font>


Searches the particular task.


**Example of usage:** ```find project for CS2103```



**Expected outcome:**

Displays the relevant task with a message.

``` 
Here are the tasks which matched Boss!:

1.[T][] project for CS2103

```
### `view` -  <font color = "darkcyan" size = "3"> view a task specified by date  </font>


Searches the particular task with the specified date.


**Example of usage:** ```view 2023-08-23```



**Expected outcome:**

Displays the relevant task with a message.

``` 
Hi!, Following are your scheduled Tasks for the day

1.[D][] return novel (by: Sep 27 2022 13:25)

```

### `mark` -  <font color = "darkcyan" size = "3"> marks the task as done </font>


Marks the completed task as done.


**Example of usage:** ```mark 1```



**Expected outcome:**

Marks the task at number 1 with a message.

``` 
Okay Boss! Task marked as done:

[T][X] project for CS2103

```

### `unmark` -  <font color = "darkcyan" size = "3"> marks the task as undone </font>


Marks the completed task as undone.


**Example of usage:** ```unmark 4```



**Expected outcome:**

Unmarks the task at 4th number with a message.

``` 
Okay Boss! Task marked as undone:

[T][] go grocery shopping

```

### `delete` -  <font color = "darkcyan" size = "3"> deletes a Task specified by index number </font>


Marks the completed task as undone.


**Example of usage:** ```delete 5```



**Expected outcome:**

Deletes the task at 5th number with a message.

``` 
Okay boss. Task removed:

 [T][] project for CS2103

Boss you have 4 tasks in the list. 

```

### `list` -  <font color = "darkcyan" size = "3"> Displays the TaskList </font>


Shows the List of Tasks

**Example of usage:** ```list```



**Expected outcome:**

Prints the Tasks present in the list with the relevant completion status along with Date and Time if applicable.

``` 
Here are all the tasks in your list:

[D][] Complete the assignment (by: Sep 24, 2023, 11:59 PM)
[T][X] Laundry

```



### `bye` -  <font color = "darkcyan" size = "3"> Quits the ShaunBot </font>


Exits the application and the tasks are stored in the file and can be retrieved in the next run.


**Example of usage:** ```bye```



**Expected outcome:**

Exits the application with a goodbye message.

``` 
Bye. Hope to see you again soon!

```


