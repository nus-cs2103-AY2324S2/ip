# GuanGuan

![Screenshot of GuanGuan Bot.](docs/Ui.png)

GuanGuan is designed for efficient task management to streamline your daily tasks and enhance productivity.

# Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
    1. Click `Open`.
    2. Select the project directory, and click `OK`.
    3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/guanguan.Launcher.java` file, right-click it, and choose `Run Launcher.main()` (if the code editor is showing compile errors, try restarting the IDE).

# Features:
- Words in UPPER_CASE are the parameters to be supplied by the user.
- Input `DATE` format should be `YYYY-MM-DD`.
- Example, in `deadline DESCRIPTION /by DATE`, `DESCRIPTION` and `DATE` are parameters which can be used as `deadline complete CS2103T tutorial /by 2024-05-05`.
- Parameters can be in any order.

## List tasks: `list`
List all tasks in the task list.

Format: `list`

Output:
```
Here are the tasks in your list:
1. [T][] complete CS2103T tutorial
2. [D][] dance practice (by: May 05 2024)
```

## Add todo: `todo`
Add a todo task to the task list.

Format: `todo DESCRIPTION`

Examples:
- `todo complete CS2103T tutorial`

Output:
```
Got it. I've added this task:
[T][] complete CS2103T tutorial
Now you have 1 tasks in the list.
```

## Add deadline: `deadline`
Add a deadline task to the task list.

Format: `deadline DESCRIPTION /by DATE`

Examples:
- `deadline complete CS2103T tutorial /by 2024-05-05`

Output:
```
Got it. I've added this task:
[D][] complete CS2103T tutorial (by: May 05 2024)
Now you have 1 tasks in the list.
```

## Add event: `event`
Add an event task to the task list.

Format: `event DESCRIPTION /from DATE /to DATE`

Examples:
- `event complete CS2103T tutorial /from 2024-05-05 /to 2024-05-15`

Output:
```
Got it. I've added this task:
[E][] complete CS2103T tutorial (from: May 05 2024 to: May 15 2024)
Now you have 1 tasks in the list.
```

## Mark task: `mark`
Mark task as done.

Format: `mark INDEX`

Examples:
- `mark 1`

Output:
```
Nice! I've marked this task as done:
[T][X] complete CS2103T tutorial
```

## Unmark task: `unmark`
Mark task as undone.

Format: `unmark INDEX`

Examples:
- `unmark 1`

Output:
```
OK, I've marked this task as not done yet:
[T][] complete CS2103T tutorial
```

## Delete task: `delete`
Delete task from the task list.

Format: `delete INDEX`

Examples:
- `delete 1`

Output:
```
Noted. I've removed this task:
[T][] complete CS2103T tutorial
Now you have 0 tasks in the list.
```

## Find task: `find`
Find tasks that contain the keyword.

Format: `find KEYWORD`

Examples:
- `find tut`

Output:
```
Here are the tasks in your list:
1. [T][] complete CS2103T tutorial
```

## Exit: `bye`
Exit the program.

Format: `bye`

Output:
```
Bye. Hope to see you again soon!
```

## Saving the data
Data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Editing the data file
Data are saved automatically as a text file [JAR file location]/data/tasks.txt. Advanced users are welcome to update data directly by editing that data file.

## Command Summary

| Action       | Format                                  |
|--------------|-----------------------------------------|
| Add Todo     | `todo DESCRIPTION`                      |
| Add Deadline | `deadline DESCRIPTION /by DATE`         |
| Add Event    | `event DESCRIPTION /from DATE /to DATE` |
| List         | `list`                                  |
| Mark         | `mark INDEX`                            |
| Unmark       | `unmark INDEX`                          |
| Delete       | `delete INDEX`                          |
| Find         | `find KEYWORD`                          |
| Exit         | `bye`                                   |
