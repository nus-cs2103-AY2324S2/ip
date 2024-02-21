# Balom
<br>

> "*Procrastination is something best put off till tomorrow.*" - Gerald Vaughan [(source)](https://www.askideas.com/63-best-procrastination-quotes-and-sayings/procrastination-is-something-best-put-off-until-tomorrow-gerald-vaughan/)

<br><br>
Balom is a type of chatbot that helps you with managing tasks that you have to do!
<br><br>
## Pros
* Easy to learn 👍
* Neat and Tidy
* text-based
  <br><br>
## Quick Start
1. Ensure you have the latest Java SE-Runtime Environment installed on your device.
2. Download balom.jar from v0.2 found at [here](https://github.com/ChuaZenKhoon/ip/releases)
3. Double click to open balom.jar in your computer or run it in your command prompt with java -jar balom.jar
4. Troubleshoot the opening of the file if necessary
   <br><br>
## Features
1. [Add task `add`](#add-task)
2. [Delete task `delete`](#delete-task)
3. [View tasks `list`](#view-tasks)
3. [Mark and Unmark task `mark` `unmark`](#marking-and-Unmarking-task)
4. [**Find** tasks `find`](#find-tasks)
5. [**NEW:** Snooze task `snooze`](#snooze-task)


### Add Task
There are 3 types of tasks you can add:
+ *A Todo*, a basic task: **todo (task)**
<br> e.g `todo read book`
+ *A Deadline*, a task with a deadline: **deadline (task) /by (yyyy-mm-dd HH:MM)**
<br> e.g `deadline read book /by 2020-10-10 12:30`
+ *An Event*, a task with a start and end time: 
**event (task) /from (yyyy-mm-dd HH:MM) /to yyyy-mm-dd HH:MM**
<br> e.g `event read book /from 2020-10-10 12:30 /to 2020-10-10 14:30`


### Delete task
Deletes the task at the specified index of the tasks stored.

**delete (number)**
<br> e.g `delete 2`


### View tasks

`list`

Lists out all tasks currently stored.


### Marking and Unmarking task

Marks or unmarks the task at the specified index of the tasks stored.


**mark (number) / unmark (number)**
<br> e.g `mark 2` `unmark 6` 

### Find tasks

Finds all the tasks currently stored that contain the keyword.

**find (keyword)**
<br> e.g `find read`

### Snooze task

Snoozes the task at the specified index of the tasks stored by 5 minutes.

**snooze (number)**
<br> e.g `snooze 3`

### Changes are automatically saved to your hard disk and scanned when you reopen it!

