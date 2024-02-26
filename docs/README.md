# Cal User Guide

## <span style="color:#A366F9">Quick Start</span>
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest version of Cal from [here](https://github.com/ValenciaLim/ip/releases/tag/Level-10).
3. Open a command terminal, `cd` into the folder you put the jar file in, and use the following command to run the application.
   ```
   java -jar cal.jar
   ```
4. Refer to the [Features](#features) below for details of each command.

## <span style="color:#A366F9">Features</span>
Cal is a simple and easy-to-use chatbot that helps you keep track of your tasks, deadlines and events.

### Add task
```todo <description>``` : add to do
```event <description> /from <datetime> /to <datetime>```: add a task with deadline
```deadline <description> /by <datetime>```: add an event

### List all tasks
Lists all tasks in the task list.
```list```: list all tasks using checkbox

### Mark task as done
Marks a task as done.
```mark <task number>```

### Unmark task as not done
Unmarks a task as not done.
```unmark <task number>```

### Delete tasks
Deletes a task from the task list.
```delete <task number>```

### Find tasks
Finds tasks that match the given keyword.
```find <keyword>```

### Exit the program
Exits the program.
```bye```
