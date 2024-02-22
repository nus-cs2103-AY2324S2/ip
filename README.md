# Toothless

Toothless is a chatbot that remembers your tasks with its singular braincell.
> Made of **100% void** 🐈‍⬛

![Screenshot of Toothless GUI](/docs/Ui.png)

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
   1. Click `Open`.
   2. Select the project directory, and click `OK`.
   3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/toothless/Launcher.java` file, right-click it, and choose `Run Launcher` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see a welcome message from Toothless:
   ```
    /\_/\
   ( o.o )
    > ^ <
   Nya-ice to meet you! I'm Toothless :D
   What can I do for you?
   ```
## User Guide
The User Guide can be found [here](https://getsquared.github.io/ip/).
