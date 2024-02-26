# Tyler User Guide

![Screenshot of Tyler](https://github.com/Ty-stan0417/ip/blob/master/docs/Ui.png)

Tyler help you managed your list of tasks, so that you can forget about all your tasks and go to play around with your friends.

## Adding deadlines

Add your deadline task into Tyler

Example: `deadline CS2103T /by 2024-02-26 2359`

```
Got it! I've added this task:
  [D][ ] CS2103T (by: 26 Feb 2024 11:59pm)
Now you have 1 tasks in the list.
```

## Mark Task

Mark your specific task to be doned

Example: `mark 1`

```
Nice! I've marked this task as done:
  [D][X] CS2103T (by: 26 Feb 2024 11:59pm)
```


## List

Listing out all the tasks in task list

Example: `list`

```
1. [D][X] CS2103T (by: 26 Feb 2024 11:59pm)
```

## Add Event

Add your event task into Tyler

Example: `event CS2103T meeting /from 2024-02-26 2359 /to 2024-02-27 0200`

```
Got it! I've added this task:
  [E][ ] CS2103T meeting (from: 26 Feb 2024 11:59pm to: 27 Feb 2024 2:00am)
Now you have 2 tasks in the list.
```

## Find task

Find your task by specific keyword

Example: `find meeting`

```
Here are the matching tasks in your list:
  1. [E][ ] CS2103T meeting (from: 26 Feb 2024 11:59pm to: 27 Feb 2024 2:00am)
```

## Delete task

Delete your task by index

Example: `delete 2`

```
Noted! I've deleted this task:
  [E][ ] CS2103T meeting (from: 26 Feb 2024 11:59pm to: 27 Feb 2024 2:00am)
Now you have 1 tasks in the list.
```

## Help

To help user to have an in-apps guidance about the command and format

Example: `help`

```
Below here is some function you can use and the format of each:
  list
  todo <name>
  deadline <name> /by <end>
  event <name> /from <start> /to <end>
  mark <index>
  unmark <index>
  delete <index>
  find <keyword>
  bye
```