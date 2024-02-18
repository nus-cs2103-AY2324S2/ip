# Toothless User Guide

![Screenshot of Toothless GUI](Ui.png)

Toothless is a chatbot that remembers your tasks with its singular braincell.
> Made of **100% void** 🐈‍⬛

Link to the Github Respository (here)[https://github.com/getsquared/ip/].

## Features

### List tasks: `list`
List all current tasks in the task list.
Format: `list`
Expected outcome:
```
Here are the tasks in your list:
1. [T][ ] clean room
    Tags: #chore
2. [D][X] book assignment (by: Feb 1 2024, 18:00)
    Tags: #hw, #english
3. [E][ ] dinner (from: Feb 2 2024, 18:00 to: Feb 2 2024, 20:30)
    Tags: #family
```

### Add todo: `todo`
Add a task to do with a description.
Format: `todo <description>`
Example: `todo wash dishes`
Expected outcome:
```
Got it. I've added this task:
    [T][ ] wash dishes
    Tags: NIL
Nya-ow you have 4 tasks in the list.
```

### Add deadline: `deadline` 
Add a deadline with a description and due date.
Format: `deadline <description> /by <yyyy-mm-dd> <hh:mm>`
Example: `deadline math tutorial /by 2024-02-05 23:59`
Expected outcome:
```
Got it. I've added this task:
    [D][ ] math tutorial (by: Feb 5 2024, 23:59)
    Tags: NIL
Nya-ow you have 5 tasks in the list.
```

### Add event: `event`
Add an event with a description, and start and end date.
Format: `event <description> /from <yyyy-mm-dd> <hh:mm> /to <yyyy-mm-dd> <hh:mm>`
Example: `event camp /from 2024-02-07 10:00 /to 2024-02-09 18:00`
Expected outcome:
```
Got it. I've added this task:
    [E][ ] camp (from: Feb 7 2024, 10:00 to: Feb 9 2024, 18:00)
    Tags: NIL
Nya-ow you have 6 tasks in the list.
```

### Delete task: `delete`
Delete task from the list.
Format: `delete <taskIndex>`
Example: `delete 4`
Expected outcome:
```
Noted. I've remeowved this task:
    [T][ ] wash dishes
    Tags: NIL
Nya-ow you have 5 tasks in the list.
```

### Mark task as done: `mark` 
Mark task as done.
Format: `mark <taskIndex>`
Example: `mark 1`
Expected outcome:
```
Ameowzing! I've marked this task as done:
    [T][X] clean room
    Tags: #chore
```

### Mark task as not done: `unmark` 
Mark task as not done.
Format: `unmark <taskIndex>`
Example: `unmark 1`
Expected outcome:
```
OK, I've marked this task as not done yet:
    [T][ ] clean room
    Tags: #chore
```

### Find task: `find` 
Find task by keyword.
Format: `find <keyword>`
Example: `find book`
Expected outcome:
```
Here are the meow-tching tasks in your list:
1. [D][X] book assignment (by: Feb 1 2024, 18:00)
    Tags: #hw, #english
```

### Tag task: `tag`
Tag task with tag word.
Format: `tag <taskIndex> <tagWord>`
Example: `tag 4 hw`
Expected outcome:
```
Ameowzing! I've tagged this task:
    [D][ ] math tutorial (by: Feb 5 2024, 23:59)
    Tags: #hw
```

### Untag task: `untag`
Remove tag word on task.
Format: `untag <taskIndex> <tagWord>`
Example: `untag 4 hw`
Expected outcome:
```
OK, I've removed the tag for this task:
    [D][ ] math tutorial (by: Feb 5 2024, 23:59)
    Tags: NIL
```

## Command Summary
* `list`
* `todo <description>`
* `deadline <description> /by <yyyy-mm-dd hh:mm>`
* `event <description> /from <yyyy-mm-dd hh:mm> /to <yyyy-mm-dd hh:mm>`
* `delete <taskIndex>`
* `mark <taskIndex>`
* `unmark <taskIndex>`
* `find <keyword>`
* `tag <taskIndex> <tagWord>`
* `untag <taskIndex> <tagWord>`