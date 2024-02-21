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
[T] [] watch lecture 15
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
[D] [] submit proposal (by: today)
You no have 1 task in the list
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
[D] [] submit proposal (by: Feb 23 2024)
You no have 1 task in the list
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
[E] [] submit proposal (from: 2pm to: 3pm)
You no have 1 task in the list
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
1. [T] watch lecture 15
2. [D] submit proposal (by: today)
3. [E] tutorial (from: 2pm to: 3pm)
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
[T] [X] watch lecture 15
```
Where the `[X]` signifies that the task has been marked
Similarly to Unmark a task type: `unmark <Task index number>`

For example:
````
unmark 1
````
Haro should reply with:
````
Alright I've marked this task as not done yet
[T] [] watch lecture 15
````
## 4. 