# FriendlyTool User Guide


![FriendlyTool's Ui](Ui.png)


FriednlyTool can organise your important tasks and easily manage important deadlines.

## TODO: Adding things to do

You can add tasks you have to do

Example: `todo math homework`

If your task is added successfully, you will get this message:

```
Completed. I've added this task:
[T][] math homework
Now you have 1 tasks in the list
```

## Deadline: Adding tasks with deadlines

You can add tasks you have to finish by specific deadline

Example: `deadline bio homework /by 2020-10-12T16:00`

If your deadline task is added successfully, you will get this message:

```
Completed. I've added this task:
[D][] bio homework (by:Oct 12 2020 16:00)
Now you have 2 tasks in the list
```


## Event: Adding tasks with from and to date

You can add tasks with from and to date.

Example: `event chem homework /from 2022-10-12T16:00 /to 2022-10-15T20:00`

If your event task is added successfully, you will get this message:

```
Completed. I've added this task:
[E][] chem homework (by:Oct 12 2022 16:00 to:Oct 15 2022 20:00)
Now you have 3 tasks in the list
```
## List: Listing all of your tasks

You can view a list of all tasks.

Command: `list`

## Bye: Exiting the program

You can exit using a bye command.

Command: `bye`

## Mark: Marking your finished task

You can mark your task if you are done.

Example: `mark 1` if you want to mark the first task in the list.

## Unmark: Unmarking your tasks

You can unmark your task if you are not done.

Example: `unmark 2` if you want to unmark the second task in the list.

## Delete: Deleting tasks that is unnecessary

You can delete a task that you don't want to track anymore.

Example: `delete 3` if you want to mark the third task in the list.

## Find: Finding the tasks that contains a certain keyword

You can find a task that contains a certain keyword.

Example: `find homework` if you want to every tasks containing the keyword "homework".

## Sort: Sorting all of the tasks in your list

You can sort your tasks chronologically.

ToDo tasks comes first

Event tasks and Deadline tasks are rearranged chronologically based on its end date(by, to dates)

Command: `sort`