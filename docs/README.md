# CatChat User Guide

![Ui.png](Ui.png)

_CatChat_ is my attempt at making my personalised version of _Duke_. 

Given below are instructions on how to use it.

## Adding todos

User types keyword `todo` followed by the task to be added. 
Press ENTER to add the task to the list.

`todo <task>`

Example:

**Input**

```
> todo read book
```

**Output**

```
> Added todo: read book
```

## Adding deadlines

User types keyword `deadline` followed by the task to be added and the date by which it is due. 
Press ENTER to add the task to the list.

`deadline <task> /by <date>`

Example:

**Input**

```
> deadline return book /by 30-03-2023
```

**Output**

```
> Added deadline: return book (by: 30-03-2023)
```

## Adding events

User types keyword `event` followed by the task to be added and the date of the event.
Press ENTER to add the task to the list.

`event <task> /from <date> /to <date>`

Example:

**Input**

```
> event project meeting /from 30-03-2023 /to 31-03-2023
```

**Output**

```
> Added event: project meeting (from: 30-03-2023, to: 31-03-2023)
```

## Helpful Commands

Here is a list of commands that you can input:
1. `help` - _Displays this helpful list of commands_
2. `list` - _Displays your task list_
3. `todo <task>` - _Adds a Todo task to the list_
4. `deadline <task> /by <date>` - _Adds a Deadline task to the list_
5. `event <task> /from <date> /to <date>` - _Adds an Event task to the list_
6. `mark done <index>` - _Marks the task at the given index as done_
7. `mark undone <index>` - _Marks the task at the given index as undone_
8. `delete <index>` - _Deletes the task at the given index_
9. `find <keyword>` - _Finds tasks that contain the given keyword_
10. `bye` - _Exits the program_

Hope you enjoy using it!

