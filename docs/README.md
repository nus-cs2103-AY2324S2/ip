# Dune User Guide

![image](./Ui.png)

Dune is a task manager that helps you keep track of your tasks. It is a 
command line application that is easy to use and has a simple interface.
Users are able to create, read and delete tasks. Dune also allows
users to add three types of tasks: toDos, deadlines, events.

* [Quickstart](#quickstart)
* [Features](#features)
  * [Adding ToDos](#adding-todos)
  * [Adding Deadlines](#adding-deadlines)
  * [Adding Events](#adding-events)
  * [Listing Tasks](#listing-tasks)
  * [Deleting Tasks](#deleting-tasks)
  * [(Un)marking Tasks](#unmarking-tasks)
  * [Exiting the Application](#exit)
  * [Other Features](#other-features) 


## Quickstart

1. Ensure that you have Java 11 or above installed on your computer.
2. Download the latest dune.jar from here .
3. Copy the file to the folder you want to use as the home folder for 
Dune.
4. Open a command terminal, cd into the folder you put the jar file in, 
and use the java -jar dune.jar command to run the application. 
A GUI similar to the image at the top of this page should appear within seconds.
5. Type your command in the input box at the bottom of the screen. 
You can press Enter or click the Send button to send your message. 
Key in `help` to get the list of available commands. 
Refer to the [Features](#features) below for details of each command.

## Features

### Adding ToDos

To add a toDo, type `todo` followed by the description of the task, 
e.g. `todo read book`. The following is an example of an expected
output:


``` 
Got it. I've added this task:
[T][ ] read book
Now you have x tasks in the list.
```
`x` is the number of tasks in the list, depending on the number of tasks
you have previously added. Subsequently, we assume `x=1`.

If you enter a task that already exists, you will get an error and 
it will not be added to the list.

Both pointers apply to all types of tasks.


### Adding Deadlines

To add a toDo, type `todo` followed by the description and deadline
of the task, e.g. `deadline submit report /by 2020-12-12T06:06`. 
The `/by` and time must be included in the deadline. The expected output
is the same as above. There are several possible errors that you might 
get. The most common is

```
OOPS! Enter date in format yyyy-mm-ddThh:mm
```

This means you should follow the given date and time format.

### Adding Events

This is almost the same as adding deadlines, except that you need 2
dates, the start and end date. The expected output is the same as above. The correct format is: 
`event project meeting /from 2020-12-12T06:06 /to 2020-12-12T07:06`.
However, if you swap the 2 dates, the start date would be before the end date,
and you would get an error indicating the event was not successfully added.


### Listing Tasks

To list all tasks, type `list`. The list of all tasks will be displayed.
Any additional inputs after `list` is ignored. The following is an 
example of an expected output:

```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] submit report (by: 12 Dec 2020 6:6AM)
3. [E][ ] project meeting (from: 12 Dec 2020 6:6AM to: 12 Dec 2020 7:6AM)
```

### Deleting Tasks

To delete a task, type `delete` followed by the number of the task, e.g. 
`delete 2`. The following is an example of an expected output:

```
Noted. I've removed this task:
[D][ ] submit report (by: 12 Dec 2020 6:6AM)
Now you have 2 tasks in the list.
```

### (Un)marking Tasks

To (un)mark a task as done, type `(un)mark` followed by the number of the task, 
e.g. `(un)mark 1`. (Un)mark means that as a user, you either type in
mark or unmark. In the above example, `mark 4` would be 
outside the range of the list, and hence nothing would be done. 

### Finding Tasks

To find tasks, type `find` followed by the keyword, e.g. `find book`. 
Then all tasks whose descriptions containing the keyword will be displayed.

### Exit

Thankfully, this is not Vim and you can leave easily. Just type `bye` 
and the window will close after a few seconds.

### Other Features

* Automatic saving and retrieval of tasks
* Custom errors for invalid inputs
* If you ever forget the commands or feel lost, type `help` and you will
  be reminded of the available commands :)

