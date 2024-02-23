# BotYue User Guide

Welcome to BotYue, your personal task manager! BotYue is a command-line application designed to help you keep track of your tasks efficiently.
Whether it's deadlines, events, or simple to-dos, BotYue has got you covered.

![GUI window](Ui.png)

## Quick Start
1. Ensure you have Java11 or above installed in your computer.
2. Download the `yue.jar` from from [here](https://github.com/MaYuehan/ip/releases).
3. Copy the file to the folder that you wish to use as the home folder.
4. In terminal, run the file by using command `java -jar yue.jar`

## Key Features
- [x] Adding different types of tasks into the list
- [x] Task tracking and management
- [X] Time management

## Table of Contents
1. [Adding Tasks](#adding-tasks)
2. [Managing Tasks](#managing-tasks)
3. [Greet and Exit](#greet-and-exit)
4. [Handling Exceptions](#handling-exceptions)

## Adding Tasks

### 1. Adding Todos
To add a todo task, use the following __command format__:

### `todo <description>`

Replace `<description>` with the description of your deadline task.

__Example__:

`todo return book`

__Expected Output__:
```
Got it. I've added this task:
 [T][] return book
Now you have 1 tasks in the list.
```

### 2. Adding Deadlines
To add a deadline task, use the following __command format__:

### `deadline <description> /by <deadline>`

Replace `<description>` with the description of your deadline task and `<deadline>` with the deadline date and time.

The deadline format: `yyyy-mm-dd` or `dd/mm/yyyy`.

__Example__: 

`deadline Submit report /by 2024-03-15`

__Expected Output__:
```
Got it. I've added this task:
 [D][] Submit report (by: Mar 15 2024)
Now you have 2 tasks in the list.
```

### 3. Adding Events
To add an event, use the following __command format__:

### `event <description> /from <start time> /to <end time>`

Replace `<description>` with the description of your deadline task, 
replace `<start time>` and `<end time>` with the date and time.

Time format: `yyyy-mm-dd`, `dd/mm/yyyy` or `dd/mm/yyyy HH:mm`.

__Example__:

`event project meeting /from 12/03/2024 1800 /to 21/03/2024 1830`

__Expected Output__:
```
Got it. I've added this task:
 [E][] project meeting (from: Mar 12 2024 18:00 to: Mar 12 2024 18:30)
Now you have 3 tasks in the list.
```


## Managing Tasks
### 1. Listing all tasks
Command format:

### `list`

__Example__:

`list`

__Expected Output__:
```
Here are the tasks in your list:
1. [T][] return book
2. [D][] Submit report (by: Mar 15 2024)
3. [E][] project meeting (from: Mar 12 2024 18:00 to: Mar 12 2024 18:30)
```

### 2. Delete Task
__Command format__:

### `delete <task index>`

__Example__:

`delete 1`

__Expected Output__:
```
Noted. I've removed this task:
 [T][] return book
Now you have 2 tasks in the list.
```

### 3. Mark Task as Done
__Command format__:

### `mark <task index>`

__Example__:

`mark 1`

__Expected Output__:
```
Nice! I've marked this task as done:
 [D][X] Submit report (by: Mar 15 2024)
```

### 4. Mark Task as Not Done
__Command format__:

### `unmark <task index>`

__Example__:

`unmark 1`

__Expected Output__:
```
OK, I've marked this task as not done yet:
 [D][] Submit report (by: Mar 15 2024)
```

### 5. Detect and Remove Duplicates
__Command format__:

### `detect`

__Example__:

`detect`

__Expected Output__:

If there are two exactly the same todo tasks of return book.
```
Duplicates removed successfully:
1. [T][] return book
```
If all the tasks are unique in the list.
```
Great! There is no duplicates in the task list.
```

### 5. Find Tasks
BotYue can help you find the tasks that contains the keyword you provided.

This command is __case sensitive__, and __not full-text search__.

__Command format__:

### `find <keyword>` 

__Example__:

`find return`

__Expected Output__:

If there are two tasks contain word 'return':
```
Here are the matching tasks in your list:
[T][] return book
[T][X] return books back to school
```
If there is no task matching the keyword:
```
There is no task matching the word: return
```

## Greet and Exit
### Greeting
To start communicating with BotYue, user can type a command:
### `hi`

Then, BotYue will response:
```
Hello!
How can I help you?
```
### Exiting
To end the conversation, input the command:
### `bye`

Then, BotYue will end the conversation:
```
Bye. Hope to see you again soon!
```


## Handling Exceptions
BotYue provides robust error handling to ensure smooth user interactions. 
When an error occurs during command processing, 
BotYue throws an exception along with an informative error message. 
Below are the common error scenarios and their corresponding error messages:

### 1. **Invalid Command Format**
If the user enters an __empty__ command,
BotYue displays the following error message:

`OOPS!!! Please enter a valid command.`

If the user enters an __invalid__ command,
BotYue displays the following error message:

`OOPS!!! I'm sorry, I don't know what that means :-(`

### 2. **Invalid Task Index** 
When the user attempts to perform an action (e.g., mark, unmark, delete) 
on a task __without an index__, BotYue throws the following error:

`OOPS!!! Please specify the task index to [action].`

When the user attempts to perform an action (e.g., mark, unmark, delete)
on a task with an __invalid index__, BotYue throws the following error:

`OOPS!!! Please enter a valid task index to [action].`

When the user attempts to perform an action (e.g., mark, unmark, delete)
on a task with an __index out of range__, BotYue throws the following error:

`OOPS!!! The index is out of range.`

### 4. Empty Task Description
When adding a todo task or a deadline/event task without a description, 
BotYue responds with:

__Todo__
```
OOPS!!! The description of a todo task cannot be empty.
The correct format of input should be: todo <your task>.
```
__Deadline__
```
OOPS!!! The description of a deadline task cannot be empty.
The correct format of input should be:
deadline <description> /by <deadline>
The time format should be: dd/mm/yyyy or yyyy-mm-dd.
```

__Event__
```
OOPS!!! The description of an event task cannot be empty.
The correct format of input should be:
event <description> /from <start time> /to <end time>
The time format should be: dd/mm/yyyy HH:mm or yyyy-mm-dd or dd/mm/yyyy
```
### 5. Empty Deadline/Event Time 
If the user fails to specify the deadline or event time, BotYue displays:

**Deadline**
```
OOPS!!! The deadline of a deadline task cannot be empty.
The correct format of input should be:
deadline <description> /by <deadline>
The time format should be: dd/mm/yyyy or yyyy-mm-dd.
```
__Event (missing start and end time)__
```
OOPS!!! The time of an event task cannot be empty.
The correct format of input should be: 
event <description> /from <start time> /to <end time>
The time format should be: dd/mm/yyyy HH:mm or yyyy-mm-dd or dd/mm/yyyy
```

__Event (missing end time)__
```
OOPS!!! The end time of an event task cannot be empty.
The correct format of input should be: 
event <description> /from <start time> /to <end time>
The time format should be: dd/mm/yyyy HH:mm or yyyy-mm-dd or dd/mm/yyyy
```

### 6. Invalid Deadline/Event Time Format
When the deadline or event time is provided in an incorrect format, 
BotYue throws an error:

Example:
```
deadline homework /by 12/03/21
```
Output:
```
Unable to parse date/time as the time might be missing or in a wrong format 12/03/21
```

These error messages help users understand the nature of the problem and guide them in providing the correct input to BotYue.

