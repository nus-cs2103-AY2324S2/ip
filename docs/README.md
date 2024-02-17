# BotChat User Guide

BotChat is a **chatbot for managing user's tasks, optimised for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, BotChat manage your tasks faster than traditional GUI apps.

- Setting up in Intellij
- Features
  - Adding todos: `todo`
  - Adding deadlines: `deadline`
  - Adding events: `event`
  - Listing all tasks: `list`
  - Deleting a task : `delete`
  - Marking a task : `mark`
  - Unmarking a task: `unmark`
  - Finding tasks : `find`
  - Show all available commands: `help`

## Quick Start

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Go to GitHub Releases and download the zip file from the release "JAR-Release for A-Release".
2. Unzip the zip file.
3. Open up terminal and navigate to the directory the file is in.
4. Type in the command: `java -jar BotChatFinall.jar` to launch the chatbot. You should see a window popup with a message:
   ```
   Hello! I'm botChat :D
   What can I do for you?
   ```
   
## Features

### Adding todos: `todo`

Adds a todo to the tasklist.
Format: `todo DESCRIPTION`
Example:
- `todo CS2103T ip`

### Adding deadlines: `deadline`

Adds a deadlines to the tasklist.
Format: `deadline DESCRIPTION /by DATE&TIME`
Example:
- `deadline CS2103T ip /by 2024-02-23`

### Adding events: `event`

Adds a events to the tasklist.
Format: `event DESCRIPTION /from DATE&TIME /to DATE&TIME`
Example:
- `event CS2103T ip /from 2024-02-10 /to 2024-02-23`

### Listing all tasks: `list`

Shows a list of all tasks in the tasklist.
Format: `list`

### Deleting a task : `delete`

Deletes the specified task from the tasklist.
Format: `delete TASKINDEX`
- Deletes the task at the specified `TASKINDEX`.
- The index refers to the index number shown in the tasklist.
- The index must be a **positive integer** 1, 2, 3, …​
Example:
- `delete 2` deletes the 2nd person in the tasklist.

### Marking a task : `mark`

Marks the specified task from the tasklist.
Format: `mark TASKINDEX`
- Mark the task at the specified `TASKINDEX`.
- The index refers to the index number shown in the tasklist.
- The index must be a **positive integer** 1, 2, 3, …​
Example:
- `mark 2` marks the 2nd person in the tasklist.

### Unmarking a task: `unmark`

Unmarks the specified task from the tasklist.
Format: `unmark TASKINDEX`
- Unmark the task at the specified `TASKINDEX`.
- The index refers to the index number shown in the tasklist.
- The index must be a **positive integer** 1, 2, 3, …​
Example:
- `unmark 2` unmarks the 2nd person in the tasklist.

### Finding tasks : `find`

Finds tasks whose description contain the keyword.
Format: `find KEYWORD`
- The search is case-insensitive. e.g cs2013t will match CS2103T
- Only the description is searched.
- Tasks matching the keyword will be returned.
Example:
- `find cs`returns `cs2102 project` and `cs2103 ip`

### Show all available commands: `help`

Shows a list of all available commands used in BotChat.
Format: `help`
