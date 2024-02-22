# SAOPIG Bot User Guide
## Introduction
``` 
 ____    _    ___  ____ ___ ____   ____   ___ _____ 
/ ___|  / \  / _ \|  _ \_ _/ ___| | __ ) / _ \_   _|
\___ \ / _ \| | | | |_) | | |  _  |  _ \| | | || |  
 ___) / ___ \ |_| |  __/| | |_| | | |_) | |_| || |  
|____/_/   \_\___/|_|  |___\____| |____/ \___/ |_|
```
>Hi there! I'm a gentle and proud cartoon girl ChatBot, here to spread joy and help you with your tasks in the most delightful way! 🌸✨ – Simulated personality by ChatGPT

Saopig ChatBot is here to assist you with:
- providing cheerful encouragement along the way
- managing tasks
- make sure everything is as lovely and efficient as a well-tended garden! 📚🌼✨

>Product Screenshot
![Ui.png](Ui.png)

And it is **FREE & OPENSOURCE!**

## Quick start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest `saopig.jar` from [here](https://github.com/ZHANGTIANYAO1/ip/releases)
3. Copy the file to the folder you want to use as the home folder for your AddressBook.
4. Open a terminal and navigate to the folder where the `saopig.jar` is located.
5. Run the command `java -jar saopig.jar` to start the app.
6. Type the command in the command box and press Enter to execute it.
7. Refer to the Features below for details of each command.

## Types of tasks
```
| Type of Tasks | Description | Start Time | End Time |
|---------------|-------------|------------|----------|
| Todo          | yes         | no         | no       |
| DeadLine      | yes         | no         | yes      |
| Event         | yes         | yes        | yes      |
```

## Features
## Add a task
>Add a task to the task list.
### Add a Todo task: `todo`
Format: `todo <DESCRIPTION>`

Example: `todo read book`

### Add a Deadline task: `deadline`
Format: `deadline <DESCRIPTION> /by <DATE>`

Example: `deadline return book /by 2021-09-30 12:00`

### Add an Event task: `event`
Format: `event <DESCRIPTION> /from <DATE> /to <DATE>`

Example: `event project meeting /from 2021-09-30 12:00 /to 2021-09-30 14:00`

## List all tasks
>Display all tasks in the task list.

Format: `list`

## Mark a task as done
>Mark a task as done in the task list.

Format: `mark <INDEX>`

Example: `mark 1`

## Unmark a task as not done

>Unmark a task as not done in the task list.

Format: `unmark <INDEX>`

Example: `unmark 1`

## Delete a task
>Delete a task from the task list.

Format: `delete <INDEX>`

Example: `delete 1`

## Find tasks

>Find tasks in the task list by keyword.

Format: `find <KEYWORD>`

Example: `find book`

## Exit the program

>Exit the program.

Format: `bye`

## Save the data
>Save the data to the hard disk.

+ Each time the task list is updated, the program automatically saves the task list to the saopigTaskList.txt file
+ saopigTaskList.txt file is located in the relative path `/data/saopigTaskList.txt` 
from the current directory where the `saopig.jar` is located.

## Load the data
>Load the data from the hard disk.

+ Each time the program starts, the program automatically loads the task list from the saopigTaskList.txt file
+ saopigTaskList.txt file is located in the relative path `/data/saopigTaskList.txt`

## List Tasks On Date
>Display all tasks on a specific date.

Format: `listOnDate <DATE>`

Example: `listOnDate 2021-09-30`
