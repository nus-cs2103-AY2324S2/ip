# ByteBuddy User Guide

![A representative screenshot of Bytebuddy GUI](Ui.png)

ByteBuddy frees your mind of having to remember things you need to do. It's,
* text-based
* Easy to learn
* _SUPER_ FAST to use

> "10/10 app - a loyal fan"

All you need to do is,
1. download it from [here](https://github.com/NeoHW/ip)
1. double-click it.
1. add your tasks.
1. let it manage your tasks for you :shushing_face:

And it is **FREE**!

Functionalities:
- [x] Managing tasks
- [x] Managing deadlines
- [ ] Reminders (coming soon)

---

## Quickstart

How to use:
1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into IntelliJ as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any more prompts, please feel free to accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialogue, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/gui/Launcher.java` file, right-click it, and choose `Run Launcher.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see a GUI that allows you to use ByteBuddy!

---

## Features

### Task Creation:

1. **Creating a Todo Task:**
    - **Format:** `todo [task]`
    - **Description:** Create a new todo task using this command.
    - **Example:** `todo Complete project proposal`

1. **Creating an Event Task:**
    - **Format:** `event [task] /from [date] /to [date]`
    - **Description:** This command allows you to create an event task with specified start and end dates.
    - **Example:** `event Team meeting /from 2024-02-15 /to 2024-02-15`

1. **Creating a Deadline Task:**
    - **Format:** `deadline [task] /by [date]`
    - **Description:** Use this command to create a task with a specific deadline.
    - **Example:** `deadline Submit report /by 2024-02-28`

### Task Management:

1. **Marking a Task as Done:**
    - **Format:** `mark [task index]`
    - **Description:** Marks the task at the specified index as done.
    - **Example:** `mark 1` (Marks the task at index 1 as done)

1. **Unmarking a Task:**
    - **Format:** `unmark [task index]`
    - **Description:** Reverts the completion status of the task at the specified index to undone.
    - **Example:** `unmark 1` (Marks the task at index 1 as not done)

1. **Deleting a Task:**
    - **Format:** `delete [task index]`
    - **Description:** Deletes the task at the specified index from the task list.
    - **Example:** `delete 1` (Deletes the task at index 1)

1. **Listing All Tasks:**
    - **Format:** `list`
    - **Description:** Displays all tasks in the task list.
    - **Example:** `list`

### Task Searching:

1. **Searching for Tasks:**
    - **Format:** `find [keyword1] OR find [keyword1], [keyword2] ...`
    - **Description:** Search for tasks containing the specified keyword(s).
    - **Example:** `find project` (Finds tasks related to "project")

### Exiting Bytebuddy Bot:

1. **Exiting Bytebuddy Bot:**
    - **Format:** `bye`
    - **Description:** Use this command to exit Bytebuddy Bot and end the session.
    - **Example:** `bye`