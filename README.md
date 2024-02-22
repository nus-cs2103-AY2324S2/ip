# Stratify: Elevate Your Task Management :clipboard:

>_Stratify is more than just a task organizer; it's a seamless experience designed to amplify your productivity._

Here's why it stands out:

* :computer: **Command-Line Simplicity**: A user-friendly, command-line-input based interface ensures efficient task handling without unnecessary complexities.

* :watch: **Effective Task Management**: Stratify empowers you to effortlessly manage your tasks, providing a streamlined approach to boost your productivity.

* :bust_in_silhouette: **User-Friendly Design**: Easy to use, Stratify eliminates the learning curve. Dive in, follow a few simple steps, and let it transform how you manage your tasks.

## Get Started in Four Steps:
1. **Clone the Repository**: Fetch Stratify to your local machine effortlessly from [here](https://github.com/tituschewxj/ip).
1. **Compile and Run**: Quick setup with minimal effort.
1. **Add Your Tasks**: Seamlessly integrate your to-dos, deadlines, and events with just a few commands.
1. **Let it Manage for You**: Sit back as Stratify efficiently orchestrates your tasks, allowing you to focus on what truly matters.

## Features That Set Stratify Apart:
- [x] :memo: **Task Management**: Efficiently organize your to-dos.
- [x] :date: **Deadline Tracking**: Stay ahead by managing crucial deadlines.
- [x] :people_holding_hands: **Event Coordination**: Seamlessly integrate and manage your events.
- [x] :leftwards_arrow_with_hook: **Made a mistake?** Undo your changes!

## Calling All Java Enthusiasts:
Are you a Java programmer looking for a practical playground? Stratify has you covered. Enhance your Java skills by running it's simple `main` method:
```java
public static void main(String[] args) {
    Application.launch(ChatBot.class, args);
}
```

>Elevate your task management with Stratify — where simplicity meets productivity.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/chatbot/Launcher.java` file, right-click it, and choose `Run Launcher.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:

![Start window](/docs/StartWindow.png)