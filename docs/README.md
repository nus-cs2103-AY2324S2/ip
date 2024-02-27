# ByteTalker User Guide

![Ui.png](Ui.png)

ByteTalker is a chatbot for managing todo tasks, deadlines and events. 

The feauters it supports are:
* Add
* Delete
* List
* Find
* Update
* Mark
* Unmark

# Quick Start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `bytetalker.jar` from [here](https://github.com/jskimdev/ip/tree/master).
3. Copy the file to the folder you want to use as the home folder for your ByteTalker chatbot.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the ```java -jar bytetalker.jar```
command to run the application.
5. Type the command you would like to execute and press Enter or send button next to the input field.

# Features

Notes about command format:

1. words in [] are parameters to be supplied by users and cannot be empty.
   
   eg. `todo [content]`, content is a parameter which can be used as `todo read book`
       
2. Parameters have to be in the specified order.
    
3. Extraneous parameters for commands that do not take in parameters (such as `bye`, and `list`) will be ignored.
   
   e.g. if the command specifies `list 123`, it will be interpreted as `help`.
4. Accepted DateTime format would be `yyyy-M-d Hmm` and `d/M/yyyy Hmm`.

   eg.`2024-2-25 1500` and `25/2/2024 1500` are accepted by the chatbot.
5. If the date passes the maximum date in that month, the largest date will be taken.
6. Index parameter follows the index in list. If you would like to know the index of the task
you would like operate on check using `list`


## Adding Tasks
In general, you can add tasks by using the type of tasks to be added as the command. Newly added task will be added to 
the back of the tasklist.
Upon successful execution, a confirmation message will appear. 

Note that tasks in general contain two parameters:
* _Status_ of the task to indicate whether the task has been done
* _Content/Description_ of the task

Some types of tasks such as events and deadlines will have more parameters such as time, but every task type contains these two parameters.

### Adding todos: `todo`
**Save a task you need to do but without set deadline.**

Command input format: `todo [content]`

Example input: `todo read book`

Successful execution output:
considering you've executed the above example input for the first time
```
Got it. I've added this task:
  [T][] read book
Now you have 1 tasks in the list.
```

### Adding deadlines: `deadline`
**Save a task you need to do with set deadline.**

Command input format: `deadline [content] /by [DateTime]`

*supported DateTime formats are `YYYY-M-D Hmm` and `d/M/yyyy Hmm`*

Example input: `deadline read book /by 2024-2-25 1525`

Successful execution output:
considering you've executed the above example input for the first time

```
Got it. I've added this task:
  [D][] read book (by:Feb 25 2024 3:25PM)
Now you have 1 tasks in the list.
```

### Adding Events: `event`
**Save an event with specific dateTimes of when it starts and ends.**

Command input format: `event [content] /from [DateTime] /to [DateTime]`

*supported DateTime formats are `YYYY-M-D Hmm` and `d/M/yyyy Hmm`*

Example input: `event midterm /from 2024-3-5 800 /to 8/3/2024 1500`

Successful execution output:
considering you've executed the above example input for the first time

```
Got it. I've added this task:
  [E][] midterm (from:Mar 05 2024 8:00AM to:Mar 08 2024 3:00PM)
Now you have 1 tasks in the list.
```


## List: `list`
**Lists out all the tasks added.**

_No additional parameter required_

```
Here are the tasks in your list:
 1. [T][] read book
 2. [D][] read book (by:Feb 25 2024 3:25PM)
 3. [E][] midterm (from:Mar 05 2024 8:00AM to:Mar 08 2024 3:00PM)
```

## Delete: `delete`
**Deletes specified task from the list.**

Command input format: `delete [index]`

_**Note that after deleting, index for the rest of tasks can change; hence, execute `list` to check 
the index of task you would like to remove.**_

Example input: delete 1

Successful execution output: considering you've executed the above input with the list in List section
```
Got it. I've removed this task:
  [T][] read book
Now you have 2 tasks in the list.
```

## Find: `find`
**Find tasks that contain the string user has input as a substring of content.**

Command input format: `find [string]`

Example input: `find book`

Successful execution output: considering you've executed the above input with the list in List section
```
Here are the found tasks:
1.[T][] read book
2.[D][] read book (by:Feb 25 2024 3:25PM)
```

## Update: `update`
**Updates one of the parameters of an added task.**

Successful execution will print out the original task with applied changes.

Command input format: `update [index] [parameter]`

### Update todo task
Command input format: `update [index] /content [new content]`

Example input: `update 1 /content finish homework`

Successful execution output: considering you've executed the above input with the list in List section
```
Updated the chosen task!
 [T][] finish homework
```

### Update deadline task
Command input format for updating content: `update [index] /content [new conent]`

Command input format for updating deadline: `update [index] /by [new deadline]`

Example input for updating deadline: `update 2 /by 28/2/2024 1800`

_For updating content, refer to update todo task section_

Successful execution output: considering you've executed the above input with the list in List section
```
Updated the chosen task!
 [D][] read book (by:Feb 28 2024 6:00PM)
```

### Update event task
Command input format for updating content: `update [index] /content [new content]`

Command input format for updating starting DateTime: `update [index] /from [new DateTime]`

Command input format for updating ending DateTime: `update [index] /to [new DateTime]`

Example input for updating starting DateTime: `update 3 /from 28/2/2024 1800`

Successful execution output: considering you've executed the above input with the list in List section
```
Updated the chosen task!
 [E][] midterm (from:Feb 28 2024 6:00PM to:Mar 08 2024 3:00PM)
```

Example input for updating ending DateTime: `update 3 /to 1/3/2024 2000`

Successful execution output: considering you've executed the above input with the list in List section
```
Updated the chosen task!
 [E][] midterm (from:Feb 28 2024 6:00PM to:Mar 01 2024 8:00PM)
```

_For updating content, refer to update todo task section_

## Mark: `mark`
**Mark the task as done.**

Marked tasks will have `X` in the second `[]` of the specified task

Command input format: `mark [index]`

Example input: `mark 1`

Successful execution output: considering you've executed the above input with the list in List section
```
Nice! I've marked this task as done:
  [T][X] read book
```

## Unmark: `unmark`
**Mark the task as not done.**

The specified task will not have `X` in the second `[]`.

Command input format: `unmark [index]`

Example input: `unmark 1`

Successful execution output: considering you've executed the above input with the list in List section
```
OK, I've marked this task as not done yet:
  [T][] read book
```

## Exit: `bye`
**Exits the program by closing the window of the application**

## Saving tasks in a folder
By default, when you run the application, it will create a `data` folder in the home page if it does not exist.
Inside the `data` folder, it will create a `Bytetalker.txt` file. In this text file, all your tasks will be saved.
It is recommended not to touch this file. When you exit the program and rerun, the saved tasks in this file will be
automatically loaded into the list.