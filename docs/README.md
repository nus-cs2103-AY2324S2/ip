# Cal User Guide

## Quick Start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest version of Cal from [here](https://github.com/ValenciaLim/ip/releases/tag/Level-10).
3. Open a command terminal, `cd` into the folder you put the jar file in, and use the following command to run the application.
   ```
   java -jar cal.jar
   ```
4. Refer to the [Features](#features) below for details of each command.

## <span style="color:#A366F9">Features</span>
Cal is a simple and easy-to-use chatbot that helps you keep track of your tasks, deadlines and events.

## Adding events
Adds an event to the task list.
```event <description> /from <datetime> /to <datetime>```
```deadline <description> /by <datetime>```
```todo <description>```

## Listing all tasks
Lists all tasks in the task list.
```list```

## Marking tasks as done
Marks a task as done.
```mark <task number>```

## Unmarking tasks as not done
Unmarks a task as not done.
```unmark <task number>```

## Deleting tasks
Deletes a task from the task list.
```delete <task number>```

## Finding tasks
Finds tasks that match the given keyword.
```find <keyword>```

## Exiting the program
Exits the program.
```bye```
