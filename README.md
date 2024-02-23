# NotDuke

>"Your mind is for having ideas, not holding them." – David Aleen ([source](https://dansilvestre.com/productivity-quotes))

NotDuke frees your mind of having to remember things you need to do. It's,

- text-based
- easy to learn
- ~~FAST~~ *SUPER FAST* to use

## Start Up

All you need to do is,

1. download it from [here](https://github.com/yorklim/ip/releases).
1. open a terminal
2. navigate to the folder that the download file is in using the ```cd``` command
2. run ```java -jar NotDuke.jar```
1. add your tasks.
1. let it manage your tasks for you :wink:

And its **FREE**!

---
# Features

## Adding a Todo: ```todo```
Adds a todo task to the list for NotDuke to track.

Format: ```todo [Task Name]```

Examples:
- ```todo Apply Internships```
- ```todo Revise for CS2103T```

## Adding a Deadline: ```deadline```
Adds a deadline task to the list for NotDuke to track.

Format: ```deadline [Task Name] /by [Date & Time]```
- [Date & Time] format: dd/MM/yyyy hhmm

Examples:

- ```deadline CS2106 Lab Assignment 2 /by 02/02/2024 1600```
- ```deadline CS2103T iP Final submission /by 03/03/2024 2359```

## Adding an Event: ```event```
Adds an event task to the list for NotDuke to track.

Format: ```event [Task Name] /from [Date & Time] /to [Date & Time]```
- [Date & Time] format: dd/MM/yyyy hhmm

Examples
- ```event Recess Week /from 04/02/2024 0000 /to 10/02/2024 2359```
- ```event Mid Term Period /from 11/02/2024 0000 /to 17/02/2024 2359```

## Listing all tasks: ```list```
Shows a list of all tasks tracked by NotDuke.

Format: ```list```

## Deleting a task: ```delete```
Deletes the specified task tracked by NotDuke.

Format: ```delete [Index]```

- Deletes the task at the specified ```[Index]```.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...

Examples:
- ```list``` followed by ```delete 2``` deletes the 2nd task in the task list.

## Marking a task: ```mark```
Marks a specified task tracked by NotDuke as done.

Format: ```mark [Index]```

- Marks the task at the specified ```[Index]``` as done.
- The task at the specified ```[Index]``` has to be not marked for the task to be marked.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...

Examples:
- ```list``` followed by ```mark 2``` marks the 2nd task in the task list.

## Unmarking a task: ```unmark```
Unmarks a specified marked task tracked by NotDuke as not done.

Format: ```unmark [Index]```

- Unmarks the task at the specified ```[Index]``` as undone.
- The task at the specified ```[Index]``` has to be marked for the task to be unmarked.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...

Examples:
- ```list``` followed by ```unmark 2``` unmarks the 2nd task in the task list.

## Locating tasks by name: ```find```
List tasks whose task name contain any of the given keyword.

Format: ```find [Keyword]```

- The search is case-sensitive

Examples:
- ```find CS``` returns ```CS2103T``` and ```CS2106```

## Reminding of tasks: ```remind```
Remind user of tasks which are happening within the next 7 days.

Format: ```remind```
- The remind feature will also be shown at startup

## Saving and Exiting: ```bye```
Saves all the current tasks and exits the program.

Format: ```bye```


