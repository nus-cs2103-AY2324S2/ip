# Mamta: Your Event Management Chatbot 🤖

![Ui.png](Ui.png)

Welcome to the Mamta USER GUIDE, your go-to event management chatbot! Mamta helps you organize your tasks and events efficiently, ensuring you stay on top of your schedule with ease.


## Adding deadlines


Add various types of tasks, from simple todos to detailed events such as deadlines. The examples below showcase how to add a deadline with outcomes.

Sample Command:
`deadline Finish Mamta's Interlude by 19/1/2024`

Outcome:

```
------------------------------------------
Got it. I've added this task:
D| |Finish Mamta's Interlude|JANUARY 1 2024
Now you have 1 tasks in the list
------------------------------------------
```

## Mamta Command Line Features
- **Versatile Task Management**: Add various types of tasks, from simple todos (todo {desc}), deadlines (deadline {desc} by {date}), and events (event {desc} from {startDate} to {endDate}).
- **Calendar Integration**: Seamlessly manages event dates and times using an integrated calendar API.
- **Enhanced Command Line Options**: Enjoy improved command line options for inputting tasks, making interaction smooth and intuitive.

## Mamta UI Features
- **Smooth UI**: Interact with Mamta on a more personalised level through the UI.
- **Background Support**: Interact with Mamta through a vibrant background that shows her Mahesh.

## Setting up Mamta in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
    1. Click `Open`.
    1. Select the project directory, and click `OK`.
    1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/seedu.mamta/Ma
