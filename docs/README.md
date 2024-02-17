# Baron

## About
Baron Von Burger is of noble birth who has graciously let us immortalise him as a digital avatar named Baron.
You can have Baron role-play as your little servant and he'll follow your orders, although he won't be nice about it..

## Commands
1. [Quick Start](#quickstart)
2. [Features](#features)
    1. [Listing all tasks](#listing-tasks) - `tasks`
    2. [Creating todos](#creating-todos) - `todo`
    3. [Creating deadlines](#creating-deadlines) - `deadline`
    4. [Creating events](#creating-events) - `event`
    5. [Marking tasks](#marking-tasks) - `mark`
    6. [Unmarking tasks](#unmarking-tasks) - `unmark`
    7. [Deleting tasks](#deleting-tasks) - `delete'
    8. [Listing all clients](#listing-clients) - `client`
    9. [Deleting clients](#deleting-clients) - `discharge`
3. [Future Features](#future-features)

## Quickstart
1. Ensure you have Java `11` installed on your computer
2. Copy `baron.jar` to any folder
3. Type `java -jar baron.jar` in the same fodler as `baron.jar` to run the application

## Features
1. Create, mark and delete 3 different tasks: `Todo`, `Deadline` and `Event`
2. All data is saved locally in a text file automatically

## Listing Tasks
Lists all created tasks

Format: `tasks`

## Creating Todos
Creates a todo.

Format: `todo NAME`

Example: `todo Finish Homework`

## Creating Deadlines
Creates a deadline that requires a valid date time.

Format: `deadline NAME /by DATE_FORMAT`

Date Format: `d/M/yyyy HHmm`. E.g. `21/10/1999 1600`

Example: `deadline Homework Submission /by 1/5/2024`

## Creating Events
Creates an event.

Format: `event NAME /from FROM /to TO`

Example: `event House Party /from today /to tomorrow`

## Marking Tasks
Marks a task as done. Denoted by [x] when listing tasks

Format: `mark INDEX`. Marks the task at the specified index as done

Example: `mark 1`

Note: INDEX refers to the index in the array list and not the actual id value inside the data file.

## Unmarking Tasks
Marks a task as not done. Denoted by [ ] when listing tasks

Format: `unmark INDEX`. Marks the task at the specified index as not done

Example: `mark 1`

## Deleting Tasks
Deletes a task at the specified index

Format: `delete INDEX` - Deletes a task at the specified index

Example: `delete 1`

## Listing Clients
Lists all created clients. Clients have the following attributes
1. Student Number - `/sn`
2. Email -`/email`
3. Name -`/name`
4. Contact Number - `/cn`

Format: `client /sn STUDENT_NUMBER /email EMAIL /name NAME /cn CONTACT_NUMBER`

Example: `client /sn A01234567X /email jondoe@u.nus.edu /name John Doe /cn 98837093`

Note: Baron will not understand when the commands are not written in order. This will be improved in a future
release

## Cischarging Clients
Discharges a client at the specified index.  Performs the same function as `delete`

Format: `discharge INDEX`

Example: `discharge 1`

## Future Features
What's next for Baron v2.0?
1. Forcibly gain access to your file system and credit card information
2. Collect monthly taxes from you
3. Send you for execution

Now that you've seen what Baron can do, what can you do for Baron?