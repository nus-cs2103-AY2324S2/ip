# Jerry User Guide

![Screenshot of Jerry Chatbot](Ui.png)

Can't remember your chores as a household cat? Not to worry! Your best friend / Enemy Jerry is here to help you!



## List

This command will ask the chatbot show all items in your current list.

Example: list
```
Here are the tasks in your list:
1.[T][] catch mouse
2.[DI[] set mouse trap (by: Feb 25 2024)
3.[E][X] drink millk (from: Feb 20 2024 08:00 to: Feb 20 2024 10:00)
4.[D][] buy biscuits (by: Mar 03 2023)
```


## Marking/ Unmarking

This command will ask the chatbot to mark/ unmark a task as done.

Example: mark 1

The first item will be marked with an X.
```
Nice! I've marked this task as done:
[T][X] catch mouse
```

## Adding deadlines

Add an event with a deadline, of the format yyyy-MM-dd



Example: deadline buy biscuits /by 2023-03-03

Jerry will add the task into the list. This will be the output shown:

```
Got it. I've added this task:
[D][] buy biscuits (by: Mar 03 2023)
Now you have 4 tasks in the list.
```

