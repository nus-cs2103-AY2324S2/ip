# ChipChat User Guide

Welcome to Chipchat user guide! ChipChat is a **chatbot to assist users in managing their tasks**. As of now it supports three task types: 1) ToDo 2) Deadline 3) Event. 

## Add Tasks
Allows users to add tasks of three types.
Format: `<TASK TYPE> <TASK NAME>`

### Add ToDo
Format: `todo <TASK NAME>`

Usage: `todo revise maths`

Expected Output:

```
Got it. I've added this task: 
[T][ ] revise maths
```

### Add Deadline
- Date must be in YYYY-MM-DD. 

Format: `deadline <TASK NAME> /by <DUE DATE>`

Usage: `deadline submit quiz /by 2024-02-23`

Expected Output:
```
Got it. I've added this task:
[D][ ] submit quiz (by: 2024-02-23)
```

### Add Event
- Date must be in YYYY-MM-DD.

Format: `event <TASK NAME> /from <START DATE> /to <END DATE>`

Usage: `event exam week /from 2024-04-01 /to 2024-04-07`

Expected Output:
```
Got it. I've added this task:
[E][ ] exam week (from: 2024-04-01 to: 2024-04-07)
```

### Adding tags
Tags can be attached to all three types of tasks at creation.
- Use of tags are optional.
- You can attach as many number of tags as you want.

Format: `<TASK TYPE> <TASK NAME> /tags <tag1> <tag2> <...>`

Format of tag: `#<tag name>`

Usage: `todo cs2101 ca1 script /tag #important #cs2101`

Expected Output:
```
Got it. I've added this task:
[T][ ] cs2101 ca1 script  #important #cs2101
```

## List Tasks
Displays a list of tasks in order of oldness. The tasks will be prefixed with an index.

Format: `list`

Usage: `list` (Assuming we have added all tasks outlined above)

Expected Output:
```
Here are the tasks in your list:
0. [T][ ] revise maths
1. [D][ ] submit quiz (by: 2024-02-23)
2. [E][ ] exam week (from: 2024-04-01 to: 2024-04-07)
3. [T][ ] cs2101 ca1 script  #important #cs2101
```


## Mark task as done
Allows users to set certain tasks as done. 
- Tasks are specified by their index in the `list`

Format: `mark <INDEX>`

Usage: `mark 0`

Expected Output: 
```
Nice! I've marked this task as done:
[T][X] revise maths
```

## Unmark task
Allows users to unmark certain tasks as not done. Mostly for correcting accidentally marked tasks.

Format: `unmark <INDEX>`

Usage: `unmark 0`

Expected Output:
```
Nice! I've unmarked this task as not done:
[T][ ] revise maths
```

##
