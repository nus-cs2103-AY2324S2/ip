# ChipChat User Guide

Welcome to Chipchat user guide! ChipChat is a **chatbot to assist users in managing their tasks**. As of now it supports three task types: 1) ToDo 2) Deadline 3) Event. 

## Installing and Running Chipchat
Prerequisite: Java and JDK11 is downloaded on your computer.

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

Usage: `deadline submit maths quiz /by 2024-02-23`

Expected Output:
```
Got it. I've added this task:
[D][ ] submit maths quiz (by: 2024-02-23)
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

Format: `<TASK TYPE> <TASK NAME> /tag <tag1> <tag2> <...>`

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
1. [D][ ] submit maths quiz (by: 2024-02-23)
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

## Find task by keyword
Users can find tasks by their name using a keyword.

Format: `find <KEYWORD>`

Usage: `find maths`

Expected Output:
```
Here are the possible tasks that match your query:
0. [T][ ] revise maths
1. [D][ ] submit maths quiz (by: 2024-02-23)
```

## Delete task
Users can delete task by their index.

Format: `delete <INDEX>`

Usage: `delete 0`

Expected Output:
```
Noted. I've removed this task:
[T][ ] revise maths
```

## Save Data
The app will automatically save user data to local drive.

## Close app
Users can terminate the app program.

Format: `bye`

Expected Output: No output. App will close immediately.
