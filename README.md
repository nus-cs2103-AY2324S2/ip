# CatChat User Guide

_CatChat_ is my attempt at making my personalised version of _Duke_. Given below are instructions on how to use it.

## Helpful Commands

Here is a list of commands that you can input:
   1. **help** - _Displays this helpful list of commands_
   2. **list** - _Displays your task list_
   3. **todo** task - _Adds a Todo task to the list_
   4. **deadline** task **/by** <date> - _Adds a Deadline task to the list_
   5. **event** task **/from** <date> **/to** <date> - _Adds an Event task to the list_
   6. **mark done** index - _Marks the task at the given index as done_
   7. **mark undone** index - _Marks the task at the given index as undone_
   8. **delete** index - _Deletes the task at the given index_
   9. **find** keyword - _Finds tasks that contain the given keyword_
   10. **bye** - _Exits the program_

Hope you enjoy using it!

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Launcher.java` file, right-click it, and choose `Run Launcher.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
```
![image](https://github.com/LargeCrowd/ip/assets/102574520/10a5872a-e425-49fe-b28b-509c8c8ec361)

```

