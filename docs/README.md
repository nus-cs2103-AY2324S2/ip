# Virtue Chatbot User Guide

![Screenshot of product test run.](./Ui.png)

Do you want to organize the chaos in your life? 
Do you want to find peace in a brutal world?
Introducing Virtue, your personal task management chatbot!
You can say goodbye to forgetting tasks, stressing over close deadlines, and much more!
Chaos is more beautiful when organized, so improve your productivity and start living a better life with Virtue!

User guide inspired by https://github.com/jmsandiegoo/ip/blob/master/docs/README.md
## Features

### Adding todos

Adds a simple todo task into the task list (no starting or ending times).

**Command Format**: ```todo <task description>```
* **task description** - the name of the task.

**Example**: Add a todo task "Study for midterms" into the list.
```todo Study for midterms```

### Adding deadlines

Adds a deadline task into the task list (with ending time and no starting time).

**Command Format**: ```deadline <task description> /by <yyyy-mm-dd>T< HH:mm:ss>```
* **task description** - the name of the task.
* **yyyy-mm-dd** - the due date of the task.
* **HH:mm:ss** - the due time of the task.

**Example**: Add a deadline task "Finish CS2103T iP" with deadline 26 Feb 2024 11:59:59 PM into the list.
```deadline Finish CS2103T iP /by 2024-02-26T23:59:59```

### Adding events

Adds an event task into the task list (with both starting and ending times).

**Command Format**: ```deadline <task description> /from <yyyy-mm-dd>T< HH:mm:ss> /to <yyyy-mm-dd>T< HH:mm:ss>```
* **task description** - the name of the task.
* **yyyy-mm-dd** - the start/end dates of the task.
* **HH:mm:ss** - the start/end times of the task.

**Example**: Add an event task "CS2109S Midterms" from 05 Mar 2024 4:00:00 PM to 05 Mar 2024 6:00:00 PM into the list.
```event CS2109S Midterms /from 2024-03-05T16:00:00 /to 2024-03-05T18:00:00```

### Mark tasks as done

Marks certain tasks as considered done.

**Command Format**: ```mark <task ids>```

* **task ids** - the ids of the tasks to be marked with respect to the task list, separated by spaces.
The ids can be seen using the `list` command, and each task id must be between 1 and the task list size inclusive.

**Example**: Mark the tasks with ids 1, 3, 5, 6 ```mark 1 3 5 6```

### Mark tasks as not done

Marks certain tasks as considered not done.

**Command Format**: ```unmark <task ids>```

* **task ids** - the ids of the tasks to be marked with respect to the task list, separated by spaces.
  The ids can be seen using the `list` command, and each task id must be between 1 and the task list size inclusive.

**Example**: Unmark the tasks with ids 2, 5, 7, 8 ```mark 2 5 7 8```

### View task list

Displays all tasks in the task list, including their descriptions, status, 
and if possible, starting and ending dates and times.

**Command Format**: ```list```

### Find tasks with keyword

Displays the tasks from the task list containing a specific keyword in their respective descriptions.

**Command Format**: ```find <keyword>```

* **keyword** - the keyword to look for in the task descriptions.

**Example**: Find all tasks with "study" in their task descriptions ```find study```

### Delete tasks

Deletes certain tasks in the task list.

**Command Format**: ```delete <task ids>```

* **task ids** - the ids of the tasks to be marked with respect to the task list, separated by spaces.
  The ids can be seen using the `list` command, and each task id must be between 1 and the task list size inclusive.

**Example**: Delete the tasks with ids 3, 4, 6 ```delete 3 4 6```

### Exit chatbot

Terminates the chatbot.

**Command Format**: ```bye```

### How do I run the chatbot?

1. Download the latest jar file release from this [repo](https://github.com/sarjinius/ip/releases).
2. Double-click the jar file to run the application.
3. Try out the commands listed above!

### How about running it locally on my computer?
1. Make sure you have JDK 11 on your computer. 
2. Update IntelliJ to the latest version.
3. Clone the repo locally.
4. Access the repo with chosen IDE with gradle.
5. Run gradlew command `./gradlew clean run`