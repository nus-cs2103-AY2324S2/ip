# Haro User Guide
## Heya it's Haro!
***Haro*** is here to serve you to help you keep track of all your tasks!
Be it menial chores, crucial deadlines you have to meet or exciting events
that you would not want to miss out on, Haro's got your back.

Simply browse the ***user guide*** below to get started.

![Haro GUI](Ui.png)
## Greetings!
### Greeting message
Upon launching the app, you should be greeted by the following message
```
Heya! I'm Haro!
What can I do for you today?
```

# Basic features
## 1. Adding your tasks
### Simple tasks
To add a simple task type in the following command: `todo <task name>`
For instance:
````
todo watch lecture 15
````
Haro should reply with the following output:
```
Got it I've added this task:
[T][] watch lecture 15
You now have 1 task in the list
```
### Tasks with deadlines
To add a task with a deadline type in the following command:
`deadline /by <due date>`, 

For example:
````
deadline submit proposal /by today
````
Where Haro should reply with:
```
Got it I've added this task:
[D][] submit proposal (by: today)
You now have 1 task in the list
```
**Using calendar dates**

Type in the due date in `YYYY/MM/DD` form 
to use as a calendar date.

For instance you could type in:
````
deadline submit proposal /by 2024-02-23
````
Where Haro should reply with:
```
Got it I've added this task:
[D][] submit proposal (by: Feb 23 2024)
You now have 1 task in the list
```
### Events
To add an Event, with a start and end timing, simply type in the 
following command:

`event /from <start time> /to <end time>`

For example:
````
event tutorial /from 2pm /to 3pm
````

Haro should answer with:
```
Got it I've added this task:
[E][] submit proposal (from: 2pm to: 3pm)
You now have 1 task in the list
```

Similarly, you can supply start and end timings in the form `YYYY/MM/DD`

## 2. Viewing task list
To view all the tasks in your list simply type in:
````
list
````
If your list is *empty* Haro will reply with:
```
The tasklist is currently empty! Add tasks!
```

Otherwise Haro will display your task list:
```
Here are the tasks in your list:
1. [T][] watch lecture 15
2. [D][] submit proposal (by: today)
3. [E][] tutorial (from: 2pm to: 3pm)
```



## 3. Marking and Unmarking tasks
### Marking tasks:
To mark a task simply type: `mark <Task index number>`

For example:
````
mark 1
````
Haro should reply with:
```
Nice! I've marked this task as done
[T][X] watch lecture 15
```
Where the `[X]` signifies that the task has been marked.
### Unmarking tasks:
Similarly, to Unmark a task, type: `unmark <Task index number>`

For example:
````
unmark 1
````
Haro should reply with:
````
Alright I've marked this task as not done yet
[T][] watch lecture 15
````
and your unmarking should be reflected when you call the `list` command.
## 4. Delete tasks
To delete a task from your list enter the following:
`delete <Task index number>`

For example:
````
delete 2
````
Haro should reply with something like:
```
Noted I've removed this task
[D][] submit proposal (by: today)
You now have 2 tasks in the list
```
and your deletion should be reflected when you call the `list` command.

# Advanced features
## 1. Finding a task
To find a task in your task list, type in: `find <search word>`

For example: 
````
find proposal 
````

Haro should reply with:
```
Here are the matching tasks in your list:
1. [D][] submit proposal (by: today)
```

If you receive the following reply:
```
Sorry there are no current matches in your list! :(
```
It means that there are no matching tasks in your list.
## 2. Editing a task
> Please take note that you can ***only edit 1 attribute per command***.

### Editing task name
To edit the task name simply input:
`edit <Task index number> /task <new task name>`

For instance:
````
edit 1 /task watch lecture 14
````
Haro should reply with:
```
Got it I've edited this task:
1. [T][] watch lecture 14
```
and your changes should be reflected when you call the `list` command.
### Editing due date
To edit the due date of a deadline task, simply type in:
`edit <Task index number> /by <new due date>`

For example:
````
edit 2 /by tomorrow
````

Haro should reply with:
```
Got it I've edited this task:
2. [D][] submit proposal (by: tomorrow)
```
### Editing start and end date
**Editing Start date**

To edit the **start** date of an event task, type in:
`edit <Task index number> /from <new start date>`

For example:
````
edit 3 /from 1pm
````

Haro should reply with something like:
```
Got it I've edited this task:
3. [E][] tutorial (from: 1pm to: 3pm)
```

**Editing end date**

To edit the **end** date of an event task, type in:
`edit <Task index number> /to <new end date>`

For example:
````
edit 3 /to 2pm
````

Haro should reply with something like:
```
Got it I've edited this task:
3. [E][] tutorial (from: 1pm to: 2pm)
```
and your changes should be reflected when you call the `list` command.

# Goodbye
### Saying bye
To say bye to Haro, type in: `bye`

Haro will bid you farewell with the following message:
```
Bye. Hope to see you some time soon!
```
And you will exit the application.