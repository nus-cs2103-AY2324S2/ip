# duck.Test project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.
<img width="395" alt="Screenshot 2024-02-17 at 11 30 18 PM" src="https://github.com/Jayne1010/ip/assets/122381620/499ff4d9-66d6-4686-88c8-cd16e3c2c184">


## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/duck.Test.java` file, right-click it, and choose `Run duck.Test.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Welcome to Duck Chatbot! How can I assist you today? Use help to see the list of commands available
   ```
## View all task
Enter 'List' and you will be able to see your list of tasks.

## Mark a task as done/undone
Enter 'mark (task number)' or 'unmark (task number)' to mark/unmark 
your task.

## Delete a task
Enter 'delete (task number)' to delete the corresponding task.

## Tag/Untag a task
Enter 'tag (task number) (tag)' or 'untag (task number)' to tag/untag the corresponding task.

## Find a task
Enter 'search (keyword)' to search a task.

## Adding todo
Enter 'todo (task)' to add a todo task.

## Adding deadlines
Enter 'deadline (task) /by (yyyy-mm-dd)' to add a deadline task.

## Adding event
Enter 'event (task) /from (yyyy-mm-dd) /to (yyyy-mm-dd)' to add an event task.

