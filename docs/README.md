# AcademicWeapon User Guide
AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

- Quick start
- Features
    - Listing all Tasks: list
    - Adding a Todo task: Todo
    - Adding a Deadline task: Deadline
    - Adding an Event task: Event
    - Locating a keyword: find
    - Deleting a task: delete
    - Marking a task: Mark
    - Unmarking a task: Unmark
    - Exiting the program: bye
- FAQ
- Known issues
- Command summary

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `academicWeapon.jar` from [here](https://github.com/SherwynNg/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your AcademicWeapon.
4. Open a command terminal, cd into the folder you put the jar file in, and use the `java -jar academicWeapon.jar` command to run the application.
<br>
A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.
<br>
![Screenshot of the sample data in GUI.](/assets/images/sampleData.png)
5. Type the command in the command box and press Enter to execute it. e.g. typing 'list' and pressing Enter will list all outstanding tasks.
<br>
Some example commands you can try:
   - list: List all tasks
   - mark 1: Marks the 1st task shown in the current list.
   - unmark 1: Unmark the 1st task shown in the current list.
   - find book: Finds tasks with the keyword book.
   - todo return book: Adds a new todo task named return book.
   - event meeting /from later 2pm /to 4pm: Adds a new event task named meeting from 2pm-4pm.
   - deadline return book /by 2024-02-01: Adds a new deadline task named return book, by 1 Feb 2024.
   - bye: Exits the app
6. Refer to the Features below for details of each command.

---

## Features


// Describe the action and its outcome.

// Give examples of usage

Example: `keyword (optional arguments)`

// A description of the expected outcome goes here

```
expected output
```

## Feature ABC

// Feature details


## Feature XYZ

// Feature details
