# Zack User Guide

![Screenshot of a use case of the Zack chatbot showcasing its full GUI window](Ui.png)

Introducing Zack, your innovative personal assistant chatbot designed 
to streamline your task management through the convenience of a Command 
Line Interface (CLI). Zack combines the efficiency and speed of CLI 
inputs with the visual clarity of a Graphical User Interface (GUI), 
ensuring that managing your to-do list becomes a swift, hassle-free 
experience. Tailored for those who can navigate keyboard commands 
with ease, Zack outpaces traditional task management apps by allowing 
rapid entry and modification of tasks, reminders, and deadlines. 
Whether you're juggling personal projects, work assignments, or daily 
chores, Zack is engineered to keep you organized and on track, 
transforming task management into a seamless part of your day.

- [Quick Start](https://zhuuyicheng.github.io/ip/#quick-start)
- [Features](https://zhuuyicheng.github.io/ip/#features)
  - [Adding todos: `todo`](https://zhuuyicheng.github.io/ip/#adding-todos-todo)
  - [Adding deadlines: `deadline`](https://zhuuyicheng.github.io/ip/#adding-deadlines-deadline)
  - [Adding events: `event`](https://zhuuyicheng.github.io/ip/#adding-events-event)
  - [Lisiting all tasks: `list`](https://zhuuyicheng.github.io/ip/#lisiting-all-tasks-list)
  - [Marking tasks: `mark`](https://zhuuyicheng.github.io/ip/#marking-tasks)
  - [Deleting a task: `delete`](https://zhuuyicheng.github.io/ip/#deleting-a-task-delete)
  - [Locating tasks by name: `find`](https://zhuuyicheng.github.io/ip/#locating-tasks-by-name-find)
  - [Locating tasks by date: `date`](https://zhuuyicheng.github.io/ip/#locating-tasks-by-date-date)
  - [Sorting tasks: `sort`](https://zhuuyicheng.github.io/ip/#sorting-tasks-sort)


## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest zack.jar from [here](https://github.com/zhuuyicheng/ip/releases/tag/A-Release)
3. Copy the file to the folder you want to use as the _home folder_ for Zack.
4. Double-click on the jar file to launch the application. A GUI similar to the screenshot above should 
appear in a few seconds.


## Features
### Adding todos: `todo`
Adds a todo to the task list. 

Format: `todo (DESCRIPTION)`
-  A description of the todo task is necessary

Example: `todo homework`

Expected output:

```
Righty-ho! I've added this task:
[T][] homework
Now you have 1 tasks in the list. All the best!
```


### Adding deadlines: `deadline`

Adds a task with a deadline to the task list.

Format: `deadline (DESCRIPTION) [/by] (date of deadline in YYYY-MM-DD HHMM format)`
-  A description of the deadline task is necessary
- Note that the date and time format must be followed strictly; and discrepancies 
will result in an error.


Example: `deadline assignment /by 2024-03-01 2200`

Expected output:
```
Righty-ho! I've added this task:
[D][] assignment (by: 2024-03-01T22:00)
Now you have 2 tasks in the list. All the best!
```


### Adding events: `event`
Adds an event to the task list.

Format: `event (DESCRIPTION) [/from] (start of event in YYYY-MM-DD HHMM format) [/to] (end of event in YYYY-MM-DD HHMM format)`
-  A description of the deadline task is necessary
- Note that the date and time format must be followed strictly; and discrepancies
  will result in an error.

Example: `event test /from 2024-04-21 2200 /to 2024-04-23 2200`

Expected output:
```
Righty-ho! I've added this task:
[E][] test (from: 2024-04-21T22:00 to: 2024-04-23T22:00)
Now you have 3 tasks in the list. All the best!
```


### Lisiting all tasks: `list`
Shows a list of all tasks in the address book.

Format: `list`
-  Lists all the tasks that are currently in the task list. If there are none,
a message saying that the list is empty will be displayed to the user.

Example: `list`

Expected output:
```
Here are all tasks in your list:
1. [T][] homework
2. [D][] assignment (by: 2024-03-01T22:00)
3. [E][] test (from: 2024-04-21T22:00 to: 2024-04-23T22:00)
```


### Marking tasks: `mark`
This marks tasks as completed, or unmarks completed tasks by making them uncompleted.

Format: `mark (INDEX)` or `unmark (INDEX)`
- Marks the task at the specified `INDEX`. 
- The index refers to the index number shown in the displayed task list. 
- The index must be a positive integer 1, 2, 3, ...

Example: `mark 1`

Expected output:
```
Good job completing your task! It's now officially done:
[T][X] homework
```


### Deleting a task: `delete`
Deletes the specified task from the task list.

Format: `delete (INDEX)`
- Deletes the task at the specified `INDEX`. 
- The index refers to the index number shown in the displayed task list. The index must be a positive integer 1, 2, 3, ...

Example: `delete 1`

Expected output:
```
Gotcha, let's get rid of that task for ya. I've removed:
[T][X] homework
Now you have 2 tasks left in the list.
```


### Locating tasks by name: `find`
Finds tasks whose description contain the given keyword.

Format: `find (KEYWORD)`
- The search is case-insensitive. e.g `HOMEWORK` will match `homework`
- Only the description is searched.
- Partial words will be matched e.g. `home` will match `homework`
- Multiple tasks matching the keyword may be returned if there are
e.g. `homework` will return `homework 1`, `homework 2`

Example: `find as`

Expected output:
```
Here are the matching tasks in your list:
1. [D][] assignment (by: 2024-03-01T22:00)
```


### Locating tasks by date: `date`
Finds tasks whose date matches the given date.

Format: `date (date in YYYY-MM-DD format)`
- The date must be provided and in the right format.
- If no tasks correspond to the given date, the chatbot informs the user
so and prompts the user to try another date.

Example: `date 2024-03-01`

Expected output:
```
Here are the matching tasks in your list:
1. [D][] assignment (by: 2024-03-01T22:00)
```


### Sorting tasks: `sort`
Sorts tasks according to the criteria provided in either ascending or 
descending order.

Format: `sort (CRITERIA) (asc OR desc)`
- There are currently only 3 criterias to sort by:
  - alpha - sorts by alphabetical order of the description
  - deadlines - sorts and only shows the deadlines in the task list by their due date
  - addtime - sorts by the time that the task was added into the task list
- Note that the order of the task list will be permanently changed to the 
new order after each sort. To change back to the original order, use the command
`sort addtime asc`

Example: `sort alpha asc`

Expected output:
```
Here are your tasks sorted in asc alphabetical order:
1. [D][] assignment (by: 2024-03-01T22:00)
2. [E][] test (from: 2024-04-21T22:00 to: 2024-04-23T22:00)
```