# Fluffy

This is a develper guide for Fluffy, a personal assistant chatbot that
helps a person to keep track of various tasks. It is optimized for use
via a Command Line Interface (CLI) while having the benefits of a
Graphical User Interface (GUI). If you are an avid keyboard user,
Fluffy can help you keep track of your tasks faster than traditional GUI apps.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
2. After that, locate the `src/main/java/fluffy/Fluffy.java` file, right-click it, and choose `Run Fluffy.main()`.

## Github Actions

We use Github Actions to automatically run tests and generate the JAR file.
Have a look at the Actions tab in the repository to see the status of the workflow.