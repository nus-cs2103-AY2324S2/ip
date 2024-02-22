# Dune User Guide

(./Ui.png.png)

Dune is a task manager that helps you keep track of your tasks. It is a 
command line application that is easy to use and has a simple interface.
Users are able to create, read and delete tasks. Dune also allows
users to add three types of tasks: toDos, deadlines, events.


## Adding toDos

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

If you enter a task that already exists, you will get a reminder and 
it will not be added to the list.

Both pointers apply to all types of tasks.


## Adding deadlines

To add a toDo, type `todo` followed by the description and deadline
of the task, e.g. `deadline submit report /by 2020-12-12T06:06`. 
The `/by` and time must be included in the deadline. The expected output
is the same as above. There are several possible reminders that you might 
get. The most common is

```
OOPS! Enter date in format yyyy-mm-ddThh:mm
```

This means you should follow the given date and time format.

## Adding events

This is almost the same as adding deadlines, except that you need 2
dates, the start and end date. The expected output is the same as above. The correct format is: 
`event project meeting /from 2020-12-12T06:06 /to 2020-12-12T07:06`.
However, if you swap the 2 dates, the start date would be before the end date,
and you would get a reminder indicating the event was not successfully added.


## Listing tasks

To list all tasks, type `list`. The list of all tasks will be displayed.
Any additional inputs after `list` is ignored. The following is an 
example of an expected output:

```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] submit report (by: 12 Dec 2020 6:6AM)
3. [E][ ] project meeting (from: 12 Dec 2020 6:6AM to: 12 Dec 2020 7:6AM)
```

## Deleting tasks

To delete a task, type `delete` followed by the index of the task, e.g. 
`delete 2`. The following is an example of an expected output:

```
Noted. I've removed this task:
[D][ ] submit report (by: 12 Dec 2020 6:6AM)
Now you have 2 tasks in the list.
```

## (Un)marking tasks as done

To (un)mark a task as done, type `(un)mark` followed by the number of the task, 
e.g. `(un)mark 1`. (Un)mark means either mark or unmark. Same for unmark. In the above example, `mark 4` would be 
outside the range of the list, and hence nothing would be done. 

## Finding tasks

To find tasks, type `find` followed by the keyword, e.g. `find book`. 
Then all tasks whose descriptions containing the keyword will be displayed.

## Ask For Help

If you ever forget the commands or feel lost, type `help` and you will
be reminded of the commands :)

## Exit

Thankfully, this is not Vim and you can leave easily. Just type `bye` and
the window will close after a few seconds. 

