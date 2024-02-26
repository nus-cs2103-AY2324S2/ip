# Linus User Guide

![Screenshot of Linus chatbot](Ui.png)

Linus is a chatbot application designed to be used via computers.
It allows you to manage your tasks conveniently.

## Quick Start
1. Ensure you have Java 11 or above properly installed in your computer.
2. Download the latest `linus.jar` from [here](https://github.com/jeong-jaeho/ip/releases/tag/A-Release).
3. Copy the file to the folder where you want to use as the _home folder_ for your Linus.
4. Open a command terminal and enter `cd` command to navigate to the home folder containing Linus.
5. Run `java -jar linus.jar` command to run the chatbot.

## Accessing help page: `help`
Opens up the help page dialog box.

Format: `help`

## Adding todos: `todo`
Adds a todo task.

Format: `todo DESCRIPTION_OF_TODO_TASK`

Example: `todo borrow book`

* This creates a todo task and adds into your task list.

## Adding deadlines: `deadline`
Adds a deadline task.

Format: `deadline DESCRIPTION_OF_DEADLINE_TASK /by BY_DATE`

Example: `deadline return book /by 2019-10-15`

* The `BY_DATE` must be in YEAR-MONTH-DAY format.

## Adding events: `event`
Adds an event task.

Format: `event DESCRIPTION_OF_EVENT_TASK /from FROM_DATE /to TO_DATE`

Example: `event team meeting /from 2019-10-15 /to 2019-10-16`

* The `FROM_DATE` and `TO_DATE` must be in YEAR-MONTH-DAY format.

## Listing all tasks: `list`
Lists out all the tasks, the first task having the index number of 0.

Format: `list`

## Finding tasks with keywords: `find`
Lists out all the tasks with a description that contains the keywords.

Format: `find KEYWORDS`

Example: `find books`
* This example finds all tasks with descriptions that contain `books`.

## Marking tasks as complete: `mark`
Marks a task as complete.

Format: `mark INDEX_OF_TASK`
* To see the index of the task you want to `mark`, input the `list` command.

Example: `mark 1`
* This marks the task that is in index of 1 within the task list as complete.

## Unmarking tasks as incomplete: `unmark`
Unmarks a task as incomplete.

Format: `unmark INDEX_OF_TASK`
* To see the index of the task you want to `unmark`, input the `list` command.

Example: `unmark 1`
* This unmarks the task that is in index of 1 within the task list as incomplete.

## Deleting tasks: `delete`
Deletes a task.

Format: `delete INDEX_OF_TASK`
* To see the index of the task you want to `delete`, input the `list` command.

Example: `delete 1`
* This deletes the task that is in index of 1 within the task list.

## Letting Linus know you want to save changes and exit: `bye`
Saves all the changes made so far and prepares for exit.

Format: `bye`

## Exiting Linus: `terminate`
Closes Linus chatbot and exits the program.

Format: `terminate`

## FAQ

**Q**: What to do if I want to transfer updated data to another computer?  
**A**: Download the app in the other computer and paste the content of your original data file into the 
data file in the new computer.