# ChrisPBacon User Guide

![Product screenshot](https://github.com/agreatdayy/ip/blob/master/docs/Ui.png)

Chris P Bacon is a todo-list chatbot that helps users keep track of their tasks.

## The whole list

A list showing all the tasks added by the user.

// Give examples of usage

Example: `list`
```
expected output:
________________________________________
Oink! Here are the tasks:
1. [ ][T] drink bbt
2. [ ][E] sell bbt (from: tmr to: tdy)
________________________________________
```

## Adding deadlines

A task with a deadline will be added to the current task list.

// Give examples of usage

Example: `deadline (task description) /by (dd/MM/yyyy)`
```
expected output:
________________________________________
Oink! Nice I have added this task:
 >> [ ][D] make bbt (by: Feb 22 2022)
Oink's task count: 1
________________________________________
```

## Adding todo tasks

A todo task will be added to the current task list.

// Give examples of usage

Example: `todo (task description)`
```
expected output:
________________________________________
Oink! Nice I have added this task:
 >> [ ][T] buy bbt
Oink's task count: 2
________________________________________
```


## Adding events

An event with its duration will be added to the current task list.

// Give examples of usage

Example: `event (task description) /from (event start) /to (event end)`
```
expected output:
________________________________________
Oink! Nice I have added this task:
 >> [ ][E] sell bbt (from: tdy to: tmr)
Oink's task count: 3
________________________________________
```

## Deleting tasks

The task to be deleted will be removed from the task list.

// Give examples of usage

Example: `delete (task number in the list)`
```
expected output:
________________________________________
Oink! Yosh I have removed this task:
 >> [ ][E] sell bbt (from: tdy to: tmr)
Oink's task count: 2
________________________________________
```

## Finding tasks

Find the tasks in the list that matches the keyords.

// Give examples of usage

Example: `find (keyword e.g. sell)`
```
expected output:
________________________________________
Oink! Here are the matching tasks in the
list:
1. [ ][E] sell bbt (from: tdy to: tmr)
________________________________________
```

## Marking tasks

Marks the task completed.

// Give examples of usage

Example: `mark (task number)`
```
expected output:
________________________________________
Oink! You have completed this task! Nice
nice nice
 >> [x][T] buy bbt
________________________________________
```

## Unmarking tasks

Marks the task as not completed.

// Give examples of usage

Example: `unmark (task number)`
```
expected output:
________________________________________
Oink! You have unmarked this task! Why
why why
 >> [ ][T] buy bbt
________________________________________
```

## Help Page

Opens a popup window with all the commands and how to use them.

Example: `help`

![Help Page](https://github.com/agreatdayy/ip/blob/master/docs/HelpPage.png)
