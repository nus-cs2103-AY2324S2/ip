# Detective User Guide

![Screenshot of Detective](/docs/Ui.png)

Detective is a notebook which can chat with you!

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start:

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `Detective.jar` from [here](https://github.com/NusMinato/ip/releases)
3. Copy the file to the folder you want to use as the **home folder** for your data.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar detective.jar` command to run the application.
5. After a few seconds if you see a GUI similar to the one above, congratulations on a successful run!
6. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window.

## Features:

Notes about the command format:

* Words in `UPPER_CASE` are the parameters to be supplied by the user.
e.g. in `todo TASK`, TASK is a parameter which can be used as `todo task`.
* Please enter the commands in **strict accordance** with the format of the commands, the commands are all **lowercase**, uppercase commands are _invalid_.

### Viewing help: `help`:

Shows all allowed commands.

Format: `help`

### Listing all tasks: `list`:

Shows a list of all recorded tasks.

Format: `list`

### Adding a task:

You can add _3_ types of task as followed~

#### Adding a Todo task: `todo`:

Adds a task with only a name.

Format: `todo TASK`

Examples:

* `todo read book`
* `todo shopping`

#### Adding a Deadline task: `deadline`:

Adds a task with a name and its deadline.

Format: `deadline TASK /by YYYY/MM/DD`

Examples:

* `deadline return book /by 2002/09/30`
* `deadline submit ip task /by 2024/02/23`

#### Adding a Event task: `event`:

Adds a task with a name, start time and end time.

Format: `event TASK /from TIME /to TIME`

Examples:

* `event read book /from 12am /to 12pm`
* `event project meeting /from Aug 6th 2pm /to 4pm`

### Marking the task: `mark`:

Marks a task as completed.

Format: `mark NUMBER`

Examples:

* `mark 1`
* `mark 9`

### Unmarking the task: `unmark`:

Marks a task as uncompleted.

Format: `unmark NUMBER`

Examples:

* `unmark 1`
* `unmark 9`

### Deleting the task: `delete`:

Deletes a task from the list.

Format: `delete NUMBER`

Examples:

* `delete 1`
* `delete 2`

### Find the task: `find`:

Finds all tasks containing the keyword.

Format: `find KEYWORD`

Examples:

* `find book`
* `find read`

### Saving then exiting the program: `bye`:

Says bye~

Format: `bye`

### Editing the data file:

Detective data are saved as a text file [JAR file location]/data/data.txt. Advanced users are welcome to update data directly by editing that data file.

## FAQ:

**Q**: How do I transfer my data to another Computer?
**A**: Install the program in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Detective home folder.

**Q**: Why the data do not save?
**A**: Detective only saves the data when you enter "bye".

## Acknowledgements:

This User Guide is adapted from the [AddressBook Level 3 (AB3) User Guide](https://se-education.org/addressbook-level3/UserGuide.html#features).