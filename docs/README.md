# Joy User Guide

<img width="300" alt="Ui" src="https://github.com/yashma-sonara/ip/assets/44407952/b8b4bee2-507b-4574-b678-8242abc0071b">











Joy is a task managing chatbot. Use Joy to store your daily tasks and manage your life!

To test out Joy, download her from [here](https://github.com/yashma-sonara/ip/releases/tag/A-Release)!


Running the file

1. Download the joy.jar
2. Open cmd, and navigate to the directory in which you have downloaded the joy.jar file.
3. Run the following command
 
```
java -jar joy.jar
```



# Features
 - [x] Adding tasks
 - [X] Viewing task list 
 - [X] Finding tasks
 - [X] Marking tasks
 - [X] Deleting tasks
 - [X] No duplicate tasks
    

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

## No duplicate tasks

Action: Not allowing a duplicate task to be added.

Usage Example: `event conference /from 3pm /to 5pm`

Expected Outcome: 
```
Duplicate task found. Task not added.
```
