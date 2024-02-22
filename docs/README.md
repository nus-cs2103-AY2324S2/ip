# Bob User Guide

![Bob](Ui.png)

Bob is a chatbot that helps you manage your daily tasks; be it todos, events, or deadlines, Bob can help you keep track of them all.

## Adding Todos
Todos are suitable for tasks that does not have a strict deadline.

Todos can be added through the command `todo DESCRIPTION`

Example usage: `todo Buy groceries for dinner today`

When the todo has been added successfully, you should see the following message:
```
Here is your newly added task:[T][] Buy groceries for dinner today`
You have N tasks in your list. 
```

## Listing Tasks
Naturally, after adding tasks, you will want to view them.

Tasks can be viewed through the command `list`

Example usage: `list`

The format of a task in general is `[TASK_TYPE][DONE_STATUS] DESCRIPTION`

`TASK_TYPE` tells you the type of task it is, can be `T` for Todos, `D` for Deadlines, or `E` for Events.

`DONE_STATUS` tells you whether the task is done or not. By default, it is empty when not done, and displayed as `[X]` when marked as done.

You should see the following message, followed by your own task list (the following is just an example):
```
Here are the tasks in your list:
1.[T][] Buy groceries for dinner today
2.[T][] Go to Popular Bookstore to buy ballpoint pen
3.[T][] Buy fish from the supermarket
```

## Adding Deadlines
Deadlines are suitable for tasks that needs to be done by a certain date or time.

Deadlines can be added through the command `deadline DESCRIPTION /by DEADLINE`

Example usage 1: `deadline return book /by Sunday`

Example usage 2: `deadline buy food /by 24/12/2023 1800`

Example usage 2: `deadline buy food /by 24/12/2023`

When `DEADLINE` is specified in the following formats, they can be recognized as a proper date and will be stored and displayed in a different manner.

dd - day, mm - month, yyyy - year, hh - hour (24 hour format), mm - minute. 

Format 1: dd/mm/yyyy

Format 2: dd/mm/yyyy hhmm

When the deadline has been added successfully, you should see the following message:
```
Here is your newly added task:[D][] buy food (by: Dec 24 2023 18:00)`
You have N tasks in your list. 
```

## Adding Events
Events are suitable for tasks that spans between multiple periods of time, or other events.

Events can be added through the command `event EVENT_NAME /from START /to END`

Example usage: `event Hackathon /from Monday /to Tuesday`

When the event has been added successfully, you should see the following message:
```
Here is your newly added task:[E][] Hackathon (from: Monday  to: Tuesday)`
You have N tasks in your list.
```

## Marking Tasks as Done/Undone
Now that you have added some tasks, you might want to update their status as they are done, or undone.

Tasks can be marked as done through the command `mark TASK_ID`

Example usage: `mark 1`

Tasks can be marked as undone through the command `unmark TASK_ID`

Example usage: `unmark 1`

Where to retrieve `TASK_ID`?

Use the `list` command to see what `TASK_ID` each task has. It is the number to the left of the task.

When tasks are marked as done or undone, you should see the following message:
```
You have marked task as done:
<THE_TASK>
```
```
You have marked task as undone:
<THE_TASK>
```
`<THE_TASK>` shows more details about the task that you are marking.

## Deleting Tasks
When tasks become obsolete, you can delete them.

Tasks can be deleted through the command `delete TASK_ID`

Example usage: `delete 1`

Where to retrieve `TASK_ID`?

Use the `list` command to see what `TASK_ID` each task has. It is the number to the left of the task.

When a task has been deleted successfully, you should see the following message:
```
You have removed the current task:<THE_TASK>
You have N tasks in your list.
```
`<THE_TASK>` shows more details about the task that you are deleting.

## Find Tasks
As your task list grows big, you might want to filter it using keywords.

A particular task can be searched using a keyword through the command `find KEYWORD`

Example usage: `find food`

After executing the command, you should see the following message:
```
Here are the matching tasks in your list:
<TASK_LIST>
```
`<TASK_LIST>` lists tasks that successfully matches the keyword criterion.

## Sorting Tasks
You can sort tasks based on its description in alphabetical order.

Use the command `sort ORDER` to sort your task.

Example usage 1: `sort asc` (Sorts tasks in ascending alphabetical order)

Example usage 2: `sort desc` (Sorts tasks in descending alphabetical order)

After executing the command, you should see the following message:
```
You have succesfully sorted the list by <ORDER> description order. Here are the tasks in your list:
<TASK_LIST>
```
`<ORDER>` can be either `asc` (ascending) or `desc` (descending.
`<TASK_LIST>` lists tasks in the new sorting order.

## Archiving Tasks
Sometimes, you might not want to delete a task completely, and keep them for later.

Use the command `archive TASK_ID` to sort your task.

Example usage: `archive 1`

After executing the command, you should see the following message:
```
Your task has been archived. Here are the tasks in your list:
<TASK_LIST>
```
`<TASK_LIST>` tasks that are not archived.

## Exiting the Application
To exit, simply click the "X" button on the user interface.

Task are saved as soon as they are modified so there is no need to explicitly save it again before you end the application.