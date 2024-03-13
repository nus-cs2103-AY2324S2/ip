# theGiantPeach User Guide

![theGiantPeach Product Screenshot](https://github.com/jamessinmaojun/ip/blob/master/docs/Ui.png)

**theGiantPeach** is a GIANT PEACH 🍑 that helps you remember the stuff that you don't want to remember! It is a CLI based, meaning that you never ever have to leave your keyboard! (how cool is that?)

## Setup and Installation

Prerequisites: Java 11 (download from [here](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html))

1. Download the [latest release](https://github.com/jamessinmaojun/ip/releases/tag/A-Release).
2. Open the command line, and navigate to the folder which you have saved the `theGiantPeach.jar` file.
3. Run the command `java -jar theGiantPeach.jar`.

## Adding todos `todo`

Adds a todo to theGiantPeach.

### Format:

`todo <description>`

### Examples

Input: 
```
todo call mum
```

Expected Output: 
```
Got it. I've added this task: [T][] call mum
```

Input: 
```
todo talk to theGiantPeach
```

Expected Output: 
```
Got it. I've added this task: [T][] talk to theGiantPeach
```

## Adding deadlines `deadline`

Adds a deadline to theGiantPeach.

### Format:

`deadline <description> /by <yyyy-mm-dd>`

### Examples

Input: 
```
deadline submit CS2103T Individual Project Final Release /by 2024-02-23
```

Expected Output: 
```
Got it. I've added this task: [D][] submit CS2103T Individual Project Final Release (by: Feb 23 2024)
```

Input: 
```
deadline finalise CS2103T Team Project User Stories /by 2024-01-19
```

Expected Output: 
```
Got it. I've added this task: [D][] finalise CS2103T Team Project User Stories (by: Jan 19 2024)
```

## Adding events `event`

Adds an event to theGiantPeach.

### Format:

`event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>`

### Examples

Input: 
```
event NUS Fintech Summit /from 2024-01-10 /to 2024-01-16
```

Expected Output: 
```
Got it. I've added this task: [E][] NUS Fintech Summit (from: Jan 10 2024 to: Jan 16 2024)
```

Input: 
```
event GIC Hackathon /from 2023-11-11 /to 2023-12-12
```

Expected Output: 
```
Got it. I've added this task: [E][] GIC Hackathon (from: Nov 11 2023 to: Dec 12 2023)
```

## Deleting tasks `delete`

Deletes a task from theGiantPeach.

### Format:

`delete <task number>`

### Examples

Current Task List:
```
1. [T][] finish CS2103T assignment
2. [T][] call mum
3. [E][] GIC Hackathon (from: Nov 11 2023 to: Dec 12 2023)
```

Input: 
```
delete 2
```

Expected Output: 
```
Noted. I've removed this task: [T][] call mum
```

Input: 
```
delete 1
```

Expected Output: 
```
Noted. I've removed this task: [T][] finish CS2103T assignment
```

## Marking Tasks as Completed `mark`

Marks a task from theGiantPeach as completed.

### Format:

`mark <task number>`

### Examples

Current Task List:
```
1. [T][] finish CS2103T assignment
2. [T][] call mum
3. [E][] GIC Hackathon (from: Nov 11 2023 to: Dec 12 2023)
```

Input: 
```
mark 2
```

Expected Output: 
```
Nice! I've marked this task as done: [T][X] call mum
```

Input: 
```
mark 1
```

Expected Output: 
```
Nice! I've marked this task as done: [T][X] finish CS2103T assignment
```

## Marking Tasks as Incomplete `unmark`

Marks a task from theGiantPeach as incomplete.

### Format:

`unmark <task number>`

### Examples

Current Task List:
```
1. [T][X] finish CS2103T assignment
2. [T][X] call mum
3. [E][X] GIC Hackathon (from: Nov 11 2023 to: Dec 12 2023)
```

Input: 
```
unmark 2
```

Expected Output: 
```
Nice! I've marked this task as done: [T][] call mum
```

Input: 
```
unmark 1
```

Expected Output: 
```
Nice! I've marked this task as done: [T][] finish CS2103T assignment
```

## Listing Tasks `list`

Lists all the tasks stored in theGiantPeach.

### Format:

`list`

### Example

Current Task List:
```
1. [T][X] finish CS2103T assignment
2. [T][X] call mum
3. [E][X] GIC Hackathon (from: Nov 11 2023 to: Dec 12 2023)
```

Input: 
```
list
```

Expected Output: 
```
Here are the tasks in your list:
1. [T][X] finish CS2103T assignment
2. [T][X] call mum
3. [E][X] GIC Hackathon (from: Nov 11 2023 to: Dec 12 2023)
```

## Searching for Tasks `find`

Searches for tasks based on their descriptions.

### Format:

`search <description>`

### Examples

Current Task List:
```
1. [T][X] finish CS2103T assignment
2. [T][X] call mum
3. [E][X] GIC Hackathon (from: Nov 11 2023 to: Dec 12 2023)
```

Input: 
```
find mum
```

Expected Output: 
```
Here are the matching tasks in your list:
2. [T][X] call mum
```

Input: 
```
find assignment
```

Expected Output: 
```
Here are the matching tasks in your list:
1. [T][X] finish CS2103T assignment
```

## View Help `help`

Lists all available commands in theGiantPeach.

### Format:

`help`

### Example

Input: 
```
help
```

Expected Output: 
```
Here are a list of commands and their respective syntaxes:

todo <description>
Adds a new todo to the list.

deadline <description> /by <deadline>
Adds a new deadline to the list.

event <description> /from <start> /to <end>
Adds a new event to the list.

delete <task number>
Deletes a task from the list.

mark <task number>
Marks a task from the list as complete.

unmark <task number>
Marks a task from the list as incomplete.

list
Displays all current tasks in the list.

find <search>
Returns all the tasks in the list with description matching the search field.

bye
Exits the application.
```

## Exit Application `bye`

Exits theGiantPeach.

### Format:

`bye`

### Example

Input: 
```
bye
```

Expected Output: 
```
Goodbye!
```
