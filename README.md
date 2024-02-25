# ChatterPal - A new way to manage tasks!

ChatterPal is a novel application for adding, viewing and deleting tasks using the Command Line Interface (CLI)

![Screenshot of application](docs/Ui.png)


ChatterPal allows you to add 3 types of tasks:
1. ToDo: Generic Task with no timings.
2. DeadLine: Tasks that has to be completed by set timing.
3. Event: Tasks that occurs at set timing.

## Quick Start
1. Ensure that Java 11 installed in your computer, and not any other version.
2. Download the jar file from [here](https://github.com/tanjiajiajun/ip/releases/tag/A-Release).
3. Open a terminal window, `cd` to the directory where the jar file is located.
4. Type `java -jar chatterpal.jar` in the terminal.
5. Start logging your tasks by using the commands provided below.


Apart from task logging, ChatterPal offers functionalities for toggling task completion status and viewing your schedules.

## Features
- Commands are case-sensitive.
- Words in UPPER_CASE are the parameters to be supplied by the user.
- DATE parameter only accepts date in this format: `M/D/YYYY HHMM`
## Add Todo Task: `todo`

Adds a new todo task to the task manager.

Format: `todo DESCRIPTION`

Example: `todo Submit CS2103 Quiz`


## Add Deadline Task: `deadline`

Adds a new Deadline task that has to be completed by a certain date and time.

Format: `deadline DESCRIPTION /by DATE`

Example: `deadline Submit Math Homework /by 1/1/2024 0000`

## Add Event Task: `event`

Adds a new Event task that occurs from a certain time to .

Format: `event DESCRIPTION /from DATE /to DATE`

Example: `event Submit Math Homework /from 1/1/2024 0000 /to 1/1/2024 2359`


## List Tasks: `list`

List all the tasks.

Format: `list`


## Mark Tasks: `mark`

Marks the task as done given the specified index in the list.

Format: `mark INDEX`

Example: `mark 2`


## Unmark Tasks: `unmark`
Unmarks the task given the specified index in the list.

Format: `unmark INDEX`

Example: `unmark 2`

// A description of the expected outcome goes here


## Delete Tasks: `delete`
Deletes the task given the specified index of the task in the list.

Format: `delete INDEX`

Example: `delete 2`

// A description of the expected outcome goes here


## Find Tasks: `find`
Find tasks given specific keywords in the task's description.

Format: `find KEYWORDS`

Example: `find CS2103`

## View Schedule: `view`
View your tasks due by specified date and events occuring in specified date.

Format: `view DATE`

Example: `view 11/11/2000`

## Exit Application
Exit the program.

Format: `bye`
