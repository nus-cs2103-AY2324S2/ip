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

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/BotChat/Launcher.java` file, right-click it, and choose `Run Launcher.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see a window popup with a message:
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
