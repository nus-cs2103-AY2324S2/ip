# ByteBuddy project template

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/ByteBuddy.java` file, right-click it, and choose `Run ByteBuddy.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
   ____   __   __  _____  U _____ u   ____     _   _   ____     ____   __   __ 
U | __")u \ \ / / |_ " _| \| ___"|/U | __")uU |"|u| | |  _"\   |  _"\  \ \ / / 
 \|  _ \/  \ V /    | |    |  _|"   \|  _ \/ \| |\| |/| | | | /| | | |  \ V /  
  | |_) | U_|"|_u  /| |\   | |___    | |_) |  | |_| |U| |_| |\U| |_| |\U_|"|_u 
  |____/    |_|   u |_|U   |_____|   |____/  <<\___/  |____/ u |____/ u  |_|   
 _|| \\_.-,//|(_  _// \\_  <<   >>  _|| \\_ (__) )(    |||_     |||_ .-,//|(_  
(__) (__)\_) (__)(__) (__)(__) (__)(__) (__)    (__)  (__)_)   (__)_) \_) (__) 
   ```
