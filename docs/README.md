# Capone User Guide

![Image of the Capone user interface](./Ui.png)

> _"He who fails to plan, plans to fail"_ - [_Winston Churchill_](https://en.wikipedia.org/wiki/Winston_Churchill)

**Capone is a lightweight and free task-tracking application - It's the only task tracking application you need.**

## Listing Tasks
Lists all tasks created.

Usage: `list`

> _Lists all tasks created with Capone. This includes tasks previously created and saved to `./data/tasks.json`._

## Saving Tasks
Tasks are automatically saved to `./data/tasks.json` upon successful creation of each task.

## Adding Todos
Adds a todo task to your task list.

Usage: `todo [description]`

| Field         | Optional?  | Description             |
|---------------|------------|-------------------------|
| `description` | no         | Description of the task. |

> Example: `todo Buy groceries`
> 
> _Creates a new todo task with description "Buy groceries"._

## Adding Deadlines
Adds a deadline task to your task list.

Usage: `deadline [description] /by [date] [time]`

| Field         | Optional?  | Description             |
|---------------|------------|-------------------------|
| `description` | no         | Description of the task. |
| `date`        | yes, if time is specified       | Due date of the task. Format is YYYY-MM-dd. If none specified, date will be set to the next day at the specified time.
| `time`        | yes, if date is specified      | Time when the task is due. Format is HHMM (24-hour format). If none specified, time will be set to 0000 HRS on the specified day.


> Example: `deadline Submit CS2040S lab /by 2024-02-21 2359`
> 
> _Creates a new deadline task with description "Submit CS2040S lab" and its deadline by 21st February, 2024 at 2359HRS._

## Adding Events
Adds an event task to your task list.

Usage: `event [description] /from [date] [time] /to [date] [time]`

| Field         | Optional?  | Description             |
|---------------|------------|-------------------------|
| `description` | no         | Description of the task. |
| `date`        | yes, if time is specified       | Due date of the task. Format is YYYY-MM-dd. If none specified, date will be set to the next day at the specified time.
| `time`        | yes, if date is specified      | Time when the task is due. Format is HHMM (24-hour format). If none specified, time will be set to 0000 HRS on the specified day.


> Example: `event Band practice /from 2024-02-22 1900 /to 2024-02-22 2100`
> 
> _Creates a new event with description "Band practice" and its start date/time being 22nd February 2024, 1900HRS and its end date/time being 22nd February 2024, 2100HRS._

## Marking Tasks
Marks a task as completed. Use this in conjunction with the `list` command to see the indices of the available tasks.

Usage: `mark [index]`


| Field         | Optional?  | Description             |
|---------------|------------|-------------------------|
| `index` | no         | The index of the task. Indices of tasks can be viewed using the `list` command. |

> Example: `mark 1`
> 
> _Marks the first task in the task list. The list of tasks can be viewed using the `list` command._

## Unmarking Tasks
Unmarks a task. Use this in conjunction with the `list` command to see the indices of the available tasks.

Usage: `unmark [index]`


| Field         | Optional?  | Description             |
|---------------|------------|-------------------------|
| `index` | no         | The index of the task. Indices of tasks can be viewed using the `list` command. |

> Example: `unmark 1`
> 
> _Unmarks the first task in the task list. The list of tasks can be viewed using the `list` command._

## Deleting Tasks
Deletes. Use this in conjunction with the `list` command to see the indices of the available tasks.

Usage: `delete [index]`


| Field         | Optional?  | Description             |
|---------------|------------|-------------------------|
| `index` | no         | The index of the task. Indices of tasks can be viewed using the `list` command. |

> Example: `delete 1`
> 
> _Deletes the first task in the task list. The list of tasks can be viewed using the `list` command._

## Finding Tasks
Finds tasks that matches a given keyword.

Usage: `find [keyword]`


| Field         | Optional?  | Description             |
|---------------|------------|-------------------------|
| `keyword` | no         | The keyword to be searched for within all  task descriptions. The keyword is **case sensitive**. |

> Example: `find Work`
> 
> _Finds all tasks where their description contains the keyword "Work"._

## Updating Tasks
Updates a task's description. Use this in conjunction with the `list` command.

Usage: `update [index] [new description]`


| Field         | Optional?  | Description             |
|---------------|------------|-------------------------|
| `index` | no         | The index of the task. Indices of tasks can be viewed using the `list` command. |
| `description` | no         | The new description of the task specified. |

> Example: `update 1 Revise CS2103T`
> 
> _Updates the description of the first task to "Revise CS2103T". The list of tasks can be viewed using the `list` command._

## Exiting the program
Exits the Capone application.

Usage: `bye`


| Field         | Optional?  | Description             |
|---------------|------------|-------------------------|
| `index` | no         | The index of the task. Indices of tasks can be viewed using the `list` command. |
| `description` | no         | The new description of the task specified. |

> Example: `update 1 Revise CS2103T`
> 
> _Updates the description of the first task to "Revise CS2103T". The list of tasks can be viewed using the `list` command._
