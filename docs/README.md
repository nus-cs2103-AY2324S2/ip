# Fireraya User Guide

![Fireraya Image](https://dexter-wong.github.io/ip/Ui.png)

> Ever wanted to use a chatbot as an app to track your tasks? 
Fireraya Chatbot is here for all your recording needs and purposes!

## Adding a todo Task

Adds a task to the chatbot. It will remember the task and store it

Below is an example. Exclude the round brackets.

Example: `todo (Task you want)`

You should see:

```
I've added this task:
[T][] (Task)
You have n tasks currently
```

## Adding a deadline Task

Adds a deadline to the chatbot. It will remember the deadline and store it

Below is an example. Exclude the round brackets.

Example: `deadline (Task you want) /by (date)`

You should see:

```
I've added this task:
[D][] (Task) (by: date)
You have n tasks currently
```

## Adding a event Task

Adds an event to the chatbot. It will remember the start date, end date and store it

Below is an example. Exclude the round brackets.

Example: `event (Task you want) /from (date) to (date)`

You should see:

```
I've added this task:
[E][] (Task) (from: <start> to: <end>)
You have n tasks currently
```

## Adding a Do-After Task

Adds a task you want to start working on only after a certain date! 

Below is an example. Exclude the round brackets.

Example: `do (Task you want) /after (date)`

You should see:

```
I've added this task:
[A][] (Task) (do after: <date>)
You have n tasks currently
```

## List all your tasks

Lists all the tasks you have

Below is an example.

Example: `list`

You should see:

```
Here is a list of your tasks!
<Tasks>
```

## Exit the program

Exits the program automatically

Below is an example.

Example: `bye`

The program will automatically close after that!


## Find specific tasks

You can filter out your list of tasks with a search keyword!

Below is an example. Exclude the round brackets.

Example: `find (keyword)`

You should see:

```
Here are some results I found in your list!
Here is a list of your tasks!
<Tasks>
```

## Mark a specific task as done

You can mark a specific task as done using the mark command. 
Use the index (number) of the task you want to mark.

Below is an example. Exclude the round brackets.

Example: `mark (index)`

You should see:

```
Good job on completing this task!
<task>
```

## Unmark a specific task

You can also unmark a specific task you have previously marked.
Use the index (number) of the task you want to mark.

Below is an example. Exclude the round brackets.

Example: `unmark (index)`

You should see:

```
Unmarking task:
<task>
```


Find my GitHub [here](https://github.com/Dexter-Wong)!