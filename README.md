# Banter project template

Banter is a greenfield Java project with the motivation of creating an **app for managing tasks**, optimized for use 
via a **Command Line Interface (CLI)**, while still having the benefits of a **chatbot Graphical User Interface 
(GUI)**. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project 
first)
2. Open the project into Intellij as follows:
   1. Click `Open`.
   2. Select the project directory, and click `OK`.
   3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in 
[here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/seedu.banterbanter.Launcher.java` file, right-click it, and choose 
`Run Launcher.main()` (if the code editor is showing compile banter.errors, try restarting the IDE). If the setup is 
correct, you should see a GUI window pop up:

![Screenshot of the Banter GUI at the start](docs/Start.png)
