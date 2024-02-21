# Mamta: Your Event Management Chatbot 🤖

Welcome to the Mamta USER GUIDE, your go-to event management chatbot! Mamta helps you organize your tasks and events efficiently, ensuring you stay on top of your schedule with ease.

## Features

- **Versatile Task Management**: Add various types of tasks, from simple todos to detailed events.
- **Calendar Integration**: Seamlessly manages event dates and times using an integrated calendar API.
- **Enhanced Command Line Options**: Enjoy improved command line options for inputting tasks, making interaction smooth and intuitive.

## Future plans for Mamta

- **Smoother User Interface**: Enhance the UI to provide users with a seamless and enjoyable experience.
- **Stronger Test Coverage**: Add more test cases to ensure robust functionality and reliability.
- **Enhanced Error Handling**: Implement additional error checks to improve user experience and provide informative feedback.

## Error Handling Example

```java
public class MamtaException {
    public static MamtaException invalidTaskType() {
        return new MamtaException("Na manega! You entered an invalid task type. Sorry!");
    }
}
```
> "Na Mane" origination cames from Indian parents scolding their children for doing something wrong.


### Example Tasks with Mamta:
- T|X|read book
- D| |return book|OCTOBER 15 2019 1800
- E| |project meeting|OCTOBER 29 2004 400|2-4pm

For more details, check out the [Mamta's Source Code](https://github.com/YuvBindal/ip).

Mamta's main classes involve `Ui.java`, `Parser.java`, `Mamta.java` that handle the entry point interaction of Mamta with the user!

**Mamta's favourite line: Kaise?** DUM_DUM! ~~Mamta dum dum~~

---

This repository introduces Mamta, making event management easier and more intuitive for users.

## Setting up Mamta in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
    1. Click `Open`.
    1. Select the project directory, and click `OK`.
    1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/seedu.mamta/Mamta.java` file, right-click it, and choose `Run Launcher` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:

![Screenshot 2024-02-21 at 11.59.52 PM.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fk4%2F5t9w_3t17bx_h1kzys3r8tvh0000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_vExG50%2FScreenshot%202024-02-21%20at%2011.59.52%20PM.png)