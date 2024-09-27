# Rick User Guide

![Screenshot of the Rick chatbot showing an image, added in the Markdown, 
of Rick responding to the requests of Morty, in the garage of their house.]
(./Ui.png)

## Installation
1. Make sure you have Java SDK 11 or higher installed on your device.
2. Download the Rick.jar file in the latest release.
3. Open your terminal, run `java -jar path-to-Rick.jar` (make sure you DO NOT go INTO the directory which contains the jar file).
4. Have fun with Rick!

## Task List
A list that holds a collection of to-do tasks, deadlines, and event which 
you want Rick to help you record.

To view or edit your task list, input commands in the textField and press 'send' button.

_For example, if you wish to see the whole list of tasks:_

### Command
list

Now let's look at what other amazing things you can do with the task list!

## Adding todos
Adds a new task to do, without specified time to do it.

### Command
todo <name of task>

### Outcome
Rick will add a new todo task in your task list, and show you the new task.

Example: `todo read book`

Expected output:
```
Fine, Morty, I'll add your stupid task to the list:
[T][ ] read book
There, you happy now? You've got 1 other pointless tasks in your stupid list.
```

## Adding deadlines

### Command 
deadline <name of task> /by <yyyy-MM-dd>\
or\
deadline <name of task> /by <yyyy-MM-ddTHH-MM-SS>

### Outcome
Rick will add a new deadline task in your task list, and show you the new task.

Example: `deadline assignment 1 /by 2024-09-07`

Expected output:
```
Fine, Morty, I'll add your stupid deadline to the list:
[D][ ] assignment 1 (by: Sep 07 2024, 00:00:00)
Now you've got 10 things to stress about.
```
## Adding events

### Command
event <name of task> /from <yyyy-MM-dd> /to <yyyy-MM-dd>\
or\
event <name of task> /from <yyyy-MM-ddTHH-MM-SS> /to <yyyy-MM-ddTHH-MM-SS>

### Outcome
Rick will add a new event in your task list, and show you the new event.

Example: `event meeting with group /from 2024-09-07T15:00:00 /to 2024-09-07T17:00:00`

Expected output:
```
Fine, Morty, I've added your pointless event to the list:
[E][ ] meeting with group (from: Sep 07 2024, 15:00:00 to: Sep 07 2024, 17:00:00)
Now you've got 10 things to stress about.
```

## Deleting Tasks

### Command
delete <index of task>

### Outcome
Rick will delete the task with the corresponding index in your task list, and show you the deleted task.
The index is allocated as shown when the 'list' command is run.

Example: `delete 1`

Expected output:
```
Fine, Morty, I'll removed this pointless task for you:
[E][ ] meeting with group (from: Sep 07 2024, 15:00:00 to: Sep 07 2024, 17:00:00)
Now you have 0 tasks in the list. Try not to waste my time with this stuff again, Morty.
```

## Marking Tasks

### Command
mark <index of task>

### Outcome
Rick will mark the task with the corresponding index in your task list as done, and show you the marked task.
The index is allocated as shown when the 'list' command is run.

Example: `mark 1`

Expected output:
```
Alright, Morty. I've marked this task as done for you:
[E][X] meeting with group (from: Sep 07 2024, 15:00:00 to: Sep 07 2024, 17:00:00)
You're making progress, *burp* Morty!
```

## Unmarking Tasks

### Command
unmark <index of task>

### Outcome
Rick will unmark the task with the corresponding index in your task list as not done yet, and show you the task.
The index is allocated as shown when the 'list' command is run.

Example: `unmark 1`

Expected output:
```
Fine, Morty, I've marked this task as not done yet:
[E][ ] meeting with group (from: Sep 07 2024, 15:00:00 to: Sep 07 2024, 17:00:00)
Morty, I can't believe you didn't complete the task! What were you doing, Morty? You need to get your act together and finish what you started. Don't let me down, Morty!
```

## Searching for Tasks

### Command
find <substring to search for>

### Outcome
Rick will search for the substring within the show list and show you tasks containing the substring.

Example: `find read book`

Expected output:
```
Fine, Morty, here's your list of tasks. Try not to mess it up:
[T][ ] read book
Don't make me regret this, Morty.
```

## Exiting

If you wish to exit the application, simply say `bye` to Rick.

### Command
bye

### Outcome
Rick will greet you and close the application.

Example: `bye`

Expected output:
```
See you later, Morty! Remember, don't do anything stupid while I'm gone.
```
Application closes.

Alternatively, you can also close the window directly.


