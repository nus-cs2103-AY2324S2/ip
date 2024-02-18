# Klee project

This is a chatbot modeled after a character Klee from a game Genshin Impact.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/klee/Launcher.java` file, right-click it, and choose `Run Launcer.main()` (if the code editor is showing compile errors, try restarting the IDE).
If the app starts running a GUi should appear and Klee should greet you with a message!
