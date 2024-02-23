# Joy User Guide


<img width="295" alt="Ui" src="https://github.com/yashma-sonara/ip/assets/44407952/33c49227-94e3-461a-87f4-e49c8e1f75a3">









Joy is a task managing chatbot. Use Joy to store your daily tasks and manage your life!

# Features


## Adding deadlines

Action: Add a deadline task to your tasklist.

Usage Example: `deadline Submit Report /by 2024-02-01`


Expected Outcome:
```
 task added successfully
```

## Adding events

Action: Add an event task to your tasklist.

Usage Example: `event conference /from 3pm /to 5pm`

Expected Outcome: 
```
task added successfully
```

## Adding todos

Action: Add a todo task to your tasklist.

Usage Example: `todo cleaning`

Expected Outcome: 
```
task added successfully
```

## Viewing tasks

Action: Display all tasks currently in list.

Usage Example: `list`

Expeced Outcome: 
```
Here are the tasks in your list!
1. [T][ ] todo cleaning
```

## Finding tasks

Action: Find tasks containing a specific keyword.

Usage Example: `find cleaning`

Expected Outcome: 
```
Here are the matching tasks in your list: 1. [T][ ] cleaning 
```

## Marking tasks

Action: Mark a task as done/undone.

Usage Example: `mark 2`

Expected Outcome: 
```
Nice! I've marked this task as done: [E][X] conference (from: 3pm to: 5pm)
```

## Deleting tasks

Action: Delete a task from tasklist.

Usage Example: `delete 3`

Expected Outcome: 
```
I have deleted the below command: [T][ ] cleaning
```
