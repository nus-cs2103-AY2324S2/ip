# Duke project template
This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Note
The project has been renamed to Felix. Refer to the User Guide in docs/README.MD for more information.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/felix/Felix.java` file, right-click it, and choose `Run Felix.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   ___________    .__  .__        
   \_   _____/___ |  | |__|__  ___
   |    __)/ __ \|  | |  \  \/  /
   |     \\  ___/|  |_|  |>    <  
   \___  / \___  >____/__/__/\_ \
   \/      \/              \/

   _______________________________________________________
   _______________________________________________________
   Hello! I'm Felix
   What can I do for you?
   _______________________________________________________
   ```
