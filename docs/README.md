# Harvard Chatbot User Guide

<img src="/Users/chengyu/ip/docs/Ui.png"/>

Welcome to the prestigious Harvard Chatbot User Guide! Here you will learn how to use the amazing chatbot which will 
help simplify your life from this point onwards...

## Adding deadlines

To add deadlines, you need to specify a task name and deadline date.

Example: `deadline CS2103T Assignment /by 2024-02-21`

This specifies a task called "CS2103T Assignment" with a deadline on the 21st of February 2024.

The Harvard Chatbot will acknowledge with:

```
Got it. I've added this task:
[D][] CS2103T Assignment (by: Feb 21 2024). 
Now you have x tasks in the list.
```

## Adding todo

To add todo, you need to specify a task name.

Example: `todo sleep`

This specifies a todo task called "sleep".

The Harvard Chatbot will acknowledge with:

```
Got it. I've added this task:
[T][] sleep
Now you have x tasks in the list.
```


## Adding event

To add event, you need to specify a task name, from date and to date.

Example: `event hackathon /from 2024-02-21 /to 2024-02-22`

This specifies an event called "hackathon" occurring from 21st of February 2024 to 22nd of February 2024.

The Harvard Chatbot will acknowledge with:

```
Got it. I've added this task:
[E][] hackathon (from: Feb 21 2024 to: Feb 22 2024) 
Now you have x tasks in the list.
```


## Finding a task

You may search up tasks by using the command "find".

Example: `find eat`

The Harvard Chatbot will acknowledge with:

```
Here are the matching tasks in your list:
1.[T][] eat
2.[T][] eating
...
```
