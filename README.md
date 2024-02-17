# Maltese Project Template

This is a project template for a greenfield Java project. It's named after a cute puppy _Maltese_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
   1. Click `Open`.
   2. Select the project directory, and click `OK`.
   3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/Launcher.java` file, right-click it, and choose `Run Launcher.main()`

## This are the features available in Maltese
### Add a new Todo task
> **code**
   ```
    todo <description>
   ```
> **example**
   ```
    todo drink coffee
   ```
### Add a new Deadline task
> **code**
   ```
    deadline <description> /by <due date>
   ```
> **example**
   ```
    deadline buy cake /by 2024-10-20
   ```
### Add a new Event task
> **code**
   ```
    event <description> /from <start date> /to <end date>
   ```
> **example**
   ```
    event attend career talk /from 2024-09-09 /to 2024-10-09
   ```
### View all tasks
> **code**
   ```
    list
   ```
### Mark task(s) as done
> **code**
   ```
    mark <indice(s)>
   ```
> **example**
   ```
    mark 2 3
   ```
### Unmark task(s) as undone
> **code**
   ```
    unmark <indice(s)>
   ```
> **example**
   ```
    unmark 1
   ```
### Delete task(s)
> **code**
   ```
    delete <indice(s)>
   ```
> **example**
   ```
    delete 2 4
   ```
### Find task(s) containing the keyword
> **code**
   ```
    find <keyword>
   ```
> **example**
   ```
    find coffee
   ```
### Exit the application
> **code**
   ```
    bye
   ```


