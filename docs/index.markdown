---
# Feel free to add content and custom Front Matter to this file.
# To modify the layout, see https://jekyllrb.com/docs/themes/#overriding-theme-defaults

title: Jade User Guide
layout: home
---
Jade is a Personal Assistant Chatbot that helps a person to manage different types of tasks efficiently. Jade is __optimized for use via a Command Line Interface__ (CLI) while still having the benefits of a Graphical User Interface (GUI).

<p style="text-align: center;"><img src="Ui.png" width="400" alt="Jade Ui"></p>

# Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest `jade.jar` from [here](https://github.com/fy17ohhh/ip/releases).
3. Copy the file to the folder you want to use as the home folder for Jade.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar jade.jar` command to run the application.
5. Type the command in the command box and press Enter or click the Send Button to execute.
6. Refer to the Features below for details of each command.

# Features
## 📎 About the command format
- Words in `UPPER_CASE` are the parameters to be supplied by the user
- Words in curly brackets `{}` are enumerations, please check values of all enums under the description of that command
- Items in square brackets `[]` are optional
- Commands are case-sensitive

## A Quick Command Summary (Scroll down to see more details below)
| Action        | Format, Examples                                                                                                                                                                                            |
|---------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Todo          | `todo DESCRIPTION`<br>e.g., `todo read a book`                                                                                                                                                              |
| Deadline      | `deadline DESCRIPTION /by DEADLINEDATETIME`<br>e.g., `deadline Submit proposal /by 2024-01-01 12:00 pm`                                                                                                     |
| Event         | `event DESCRIPTION /from STARTDATETIME /to ENDDATETIME`<br>e.g., `event Team meeting /from 2024-01-01 02:00 pm /to 2024-02-01 04:00 pm`                                                                     |
| RecurringTask | `recur DESCRIPTION /dfrom STARTDATE /dto ENDDATE /tfrom STARTTIME /tto ENDTIME /freq {TASKFREQ}`<br>e.g., `recur consultation /dfrom 2024-01-01 /dto 2024-02-01 /tfrom 02:00 pm /tto 04:00 pm /freq Weekly` |
| List          | `list [DATE]`<br>e.g., `list` `list 2024-01-01`                                                                                                                                                             |
| Find          | `find KEYWORD(S)`<br>e.g., `find team meeting`                                                                                                                                                              |
| Delete        | `delete INDEX`<br>e.g., `delete 1`                                                                                                                                                                          |
| Mark          | `mark INDEX`<br>e.g., `mark 1`                                                                                                                                                                              |
| Unmark        | `unmark INDEX`<br>e.g., `unmark 1`                                                                                                                                                                          |
| Exit          | `bye`                                                                                                                                                                                                       |
| Help          | `help`                                                                                                                                                                                                      |

## Adding a todo task: `todo`
- Adds a new to-do task with the given description.
- Format: `todo DESCRIPTION`
- Examples:
    - todo read a book
    - todo homework 1

## Adding a deadline: `deadline`
- Adds a new task with a deadline and description.
- Format: `deadline DESCRIPTION /by DEADLINEDATETIME`, with `DEADLINEDATETIME` in format `uuuu-MM-dd hh:mm a`
- Examples:
    - deadline Submit proposal /by 2024-01-01 12:00 pm
    - deadline Project Proposal /by 2024-03-01 23:59 pm

## Adding an event: `event`
- Adds a new event with a description and time frame.
- Format: `event DESCRIPTION /from STARTDATETIME /to ENDDATETIME`, with `STARTDATETIME` and `ENDDATETIME` in format `uuuu-MM-dd hh:mm a`
- Examples:
    - event Team meeting /from 2024-01-01 02:00 pm /to 2024-02-01 04:00 pm
    - event Recess Week /from 2024-02-24 00:00 am /to 2024-03-03 23:59 pm

## Adding a recurring task: `recur`
- Adds a new recurring task with a description, date frame, time frame, and frequency.
    - Format: `recur DESCRIPTION /dfrom STARTDATE /dto ENDDATE /tfrom STARTTIME /tto ENDTIME /freq {TASKFREQ}`, with `STARTDATE` and `ENDDATE` in format `uuuu-MM-dd`, `STARTTIME` and `ENDTIME` in format `hh:mm a`, and `TASKFREQ` in values `Daily`, `Weekly`, or `Monthly`
- Examples:
    - recur consultation /dfrom 2024-01-01 /dto 2024-02-01 /tfrom 02:00 pm /tto 04:00 pm /freq Weekly
    - recur team bonding /dfrom 2024-01-01 /dto 2024-06-01 /tfrom 10:00 am /tto 05:00 pm /freq Monthly

## Listing tasks: `list`
- Lists all tasks scheduled for the specified date. If no date is provided, it lists all tasks.
- Format: `list [DATE]`, with `[DATE]` in format `uuuu-MM-dd`
- Examples:
    - list
    - list 2024-01-01

## Finding tasks: `find`
- Finds tasks containing the specified keyword(s) in their descriptions.
- Format: `find KEYWORD(S)`
- Examples:
    - find team meeting
    - find read

## Deleting a task: `delete`
- Deletes the task with the specified index from the list, using the **absolute index** of the task in all tasks.
- Format: `delete INDEX`
- Examples:
    - delete 1
    - delete 2

## Marking a task: `mark`
- Marks the task with the specified index as completed, using the **absolute index** of the task in all tasks.
- Format: `mark INDEX`
- Examples:
    - mark 1
    - mark 2

### Unmarking a task: `unmark`
- Marks the task with the specified index as uncompleted, using the **absolute index** of the task in all tasks.
- Format: `unmark INDEX`
- Examples:
    - unmark 1
    - unmark 2

### Exiting the program: `bye`
- Exits the program.
- Format: `exit`

### Viewing help: `help`
- Shows a message containing a summary of the usage all commands.
- Format: `help`

### Saving the data
The task data of user are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.