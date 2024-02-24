# Kai User Guide

Chatbot Kai is an application that manages your task via Command Line
Interface(CLI) or via Graphical User Interface(GUI) application.

- [Quick start](#quickstart)
- [Features](#features)
- [Description](#description)
- [FAQ](#faq)
- [Command summary](#summary)

## <a id="quickstart">Quick start</a> 
1. Make sure that Java 11 or above is installed on your device
2. Download the latest Duke.jar file
3. Place the file in folder of your preference
4. Double-click the file to run the application

## <a id="features">Features</a> 

[Viewing help](#help)

View all the commands available

Add a task

Add one specific tasks. The three types of tasks are
1. [Todo](#todo): A task that needs to be done, without a specific deadline
2. [Deadline](#deadline): A task that needs to be done, that requires a specific
   date as a deadline
3. [Event](#event): A task that will happen, with a specific date and time 

[Mark a task as done](#mark)

Update the specific task in the task list as done

[Mark a task as not done](#unmark)

Update a specific task in the task list as not done

[Delete a task](#delete)

Delete the specific task in the task list

[Find a specific task](#find)

Find the specific task in the task list using a keyword

[List all tasks](#list)

List down all the tasks in the task list in order

[Exit the program](#bye)

Exit the application completely

<a id="description">Description</a>

<a id="help">`help`</a> - Typing this will display a string that shows the list of commands that can be used

Example of usage:

`help`

Description of expected outcome:

```
"Here is the list of commands you can type in~"
"list: list out all the tasks you have keyed in!"
"todo [task]: adds a specific todo task!"
"deadline [task] | yyyy-MM-dd: adds a deadline task"
"event [task] | [date]: adds an event task!"
"mark [index]: marks a specific task at that index as done!"
"unmark [index]: unmarks a specific task at that index as not done!"
"delete [index]: deletes a specific task at that index!"
"find [keyword]: finds tasks containing the specific keywords!"
```

<a id="todo">`todo`</a> - Adding a todo

Example of usage:

`todo report submission`

Description of expected outcome:

```
Got it. I've added this task:
[T][X] report submission
Now you have 1 task in the list.
```

<a id="deadline">`deadline`</a> - Adding a deadline

Example of usage:

`deadline so many projects | 2024-10-19`

Description of expected outcome:

```
Got it. I've added this task:
[D][X] so many projects (by: Oct 19 2024)
Now you have 1 task in the list.
```

<a id="event">`event`</a> - Adding an event

Example of usage:

`event career fair | Aug 6th 2-4pm`

Description of expected outcome:

```
Got it. I've added this task:
[E][X] career fair (at: Aug 6th 2-4pm)
Now you have 1 task in the list.
```

<a id="mark">`mark`</a> - Marking a task as done

Example of usage:

`mark 1`

Description of expected outcome:

```
Nice! I've marked this task as done:
[T][0] report submission
```

<a id="unmark">`unmark`</a> - Marking a task as done

Example of usage:

`unmark 1`

Description of expected outcome:

```
OK, I've marked this task as not done yet:
[T][X] report submission
```

<a id="delete">`delete`</a> - Deleting a task from the task list

Example of usage:

`delete 1`

Description of expected outcome:

```
Noted. I've removed this task:
[T][X] report submission 
Now you have 3 tasks in the list
```

<a id="find">`find`</a> - Finding a task from the task list with a keyword

Example of usage:

`find report`

Description of expected outcome:

```
Here are the matching tasks in your list:
1. [T][X] report submission 
```

<a id="list">`list`</a> - Listing all tasks from the task list in order

Example of usage:

`list`

Description of expected outcome:

```
Here are the tasks in your list:
1. [T][X] report submission
2. [D][X] so many projects (by: OCT 19 2024)
3. [E][X] career fair (at: Aug 6th 2-4pm)
```

<a id="bye">`bye`</a> - Exiting the application

Example of usage:

`bye`

Description of expected outcome:

```
Bye Bye. Hope to see you again soon!
```

<a id="faq">FAQ</a>

Q: How do I transfer my data to another Computer?

A: Install the app in the other computer and overwrite the 
empty data file it creates with the file that contains the 
data of your previous AddressBook home folder.

<a id="summary">Command Summary</a>

| Action | Format                         |        
|--------|--------------------------------|
|  help  |`help`                          |
|  todo  |`todo`                          |
|deadline|`deadline`                      |
|  event |`event`                         |
|  mark  |`mark`                          |
| unmark |`unmark`                        |
| delete |`delete`                        |
|  find  |`find`                          |
|  list  |`list`                          |
|   bye  |`bye`                           |