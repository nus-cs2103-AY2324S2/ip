# ChatPal User Guide

Welcome to ChatPal, your friendly chatbot assistant! ChatPal is designed to help you manage tasks, set deadlines, and much more. This user guide will walk you through the various features and functionalities of ChatPal.

![ChatPal Screenshot](/Ui.png)

## Feature List

1. **Task Management**
    - ChatPal allows users to manage their tasks efficiently by adding, listing, marking as done, and deleting tasks.

2. **Multiple Task Types**
    - Users can add different types of tasks, including todo tasks, deadline tasks, and event tasks, each with its own set of properties.

3. **Date and Time Management**
    - ChatPal supports tasks with deadlines and events by allowing users to specify dates and times for these tasks.

4. **Task Filtering**
    - Users can filter tasks based on dates or specific keywords to quickly find relevant tasks.

5. **Undo Functionality**
    - ChatPal provides an undo feature that allows users to revert the last action performed, providing an extra layer of flexibility.

6. **Error Handling**
    - ChatPal handles errors gracefully, providing informative error messages to users when incorrect commands are entered or tasks cannot be processed.


## Available Commands:

1. **List Commands**
    - Lists all available commands.

2. **Add Todo Task**
    - Adds a todo task to the task list.

3. **Add Deadline Task**
    - Adds a deadline task with a specified deadline to the task list.

4. **Add Event Task**
    - Adds an event task with a specified start and end time to the task list.

5. **List All Tasks**
    - Lists all tasks currently in the task list.

6. **Mark Task Done**
    - Marks the specified task as done.

7. **Unmark Task**
    - Marks the specified task as not done.

8. **Delete a Task**
    - Deletes the specified task from the task list.

9. **Find Tasks Related to a Date**
    - Finds tasks related to the specified date.

10. **Find Tasks Related to a Matching Word**
    - Finds tasks containing the specified word in their description.

11. **Undo Last Action**
    - Reverts the last action performed.

12. **Close Chatbot**
    - Exits the chatbot application.

## Example Commands
1. Add Todo Task:
```dtd
todo [your task]
```
2. Add Deadline Task:
```dtd
deadline [your task] /by [yyyy-mm-dd hh:mm]
```
3. Add Event Task:
```dtd
event [your task] /from [yyyy-mm-dd hh:mm /to [yyyy-mm-dd hh:mm]
```
4. List All Tasks:
```dtd
list
```
5. List Commands:
```dtd
list commands
```
6. Mark Task Done:
```dtd
mark [task number (integer)]
```
7. Unmark Task:
```dtd
unmark [task number (integer)]
```
8. Delete Task:
```dtd
delete [task number (integer)]
```
9. Find Tasks Related to a Date:
```dtd
checkdate [yyyy-mm-dd]
```
10. Find Tasks Related to Matching Word:
```dtd
find [word]
```
11. Undo Last Action
```dtd
undo
```
12. Close ChatPal
```dtd
bye
```