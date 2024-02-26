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

- ```todo <description>``` : add to do  
- ```event <description> /from <datetime> /to <datetime>```: add a task with deadline  
- ```deadline <description> /by <datetime>```: add an event  

`<datetime>` should be in format: day/month/year time(24hr) e.g. 23/2/2019 1800

### List all tasks
Lists all tasks in the task list.  

```list```: list all tasks using checkbox

### Mark task as done

```mark <task number>```: Marks a task as done.    

- Mark a task that is already checked does nothing
- Mark the task using TASK NUMBER shown in `list`.
- The index must be a positive integer 1, 2, 3, …

### Unmark task as not done 

```unmark <task number>```: Unmarks a task as not done.  

- Unmark an unchecked task does nothing
- Unmark the task using TASK NUMBER shown in `list`.
- The index must be a positive integer 1, 2, 3, …

### Delete tasks

```delete <task number>```: Deletes a task from the task list.  

- Deletes task one-by-one
- Deletes the task using TASK NUMBER shown in `list`.
- The index must be a positive integer 1, 2, 3, …

### Find task

```find <keyword>```: Finds tasks that match the given keyword.  

- The search is case-insensitive. e.g john will match John
- Only the task description is searched.
- Only full words will be matched e.g. read will not match reads
- Search for tasks using ONE keyword only

### Exit the program 

```bye```: Exits the program. 
