# ChipChat User Guide

Welcome to Chipchat user guide! ChipChat is a **chatbot to assist users in managing their tasks**. As of now it supports three task types: 1) ToDo 2) Deadline 3) Event. 

## Add Tasks
Add tasks of three types.
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

Usage: `deadline submit quiz /by 2024-01-01`

Expected Output:

```
Got it. I've added this task:
[D][ ] submit quiz (by: 2024-01-01)
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
