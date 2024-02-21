# Mamta: Your Event Management Chatbot 🤖

Welcome to Mamta, your go-to event management chatbot! Mamta helps you organize your tasks and events efficiently, ensuring you stay on top of your schedule with ease.

## Features

- **Versatile Task Management**: Add various types of tasks, from simple todos to detailed events.
- **Calendar Integration**: Seamlessly manages event dates and times using an integrated calendar API.
- **Enhanced Command Line Options**: Enjoy improved command line options for inputting tasks, making interaction smooth and intuitive.

## Changes to Complete

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


### Example Tasks:
- T|X|read book
- D| |return book|OCTOBER 15 2019 1800
- E| |project meeting|OCTOBER 29 2004 400|2-4pm

For more details, check out the [Mamta's Source Code](https://github.com/YuvBindal/ip).

Mamta's main classes involve `Ui.java`, `Parser.java`, `Mamta.java` that handle the entry point interaction of Mamta with the user!

**Mamta's favourite line: Kaise?** DUM_DUM! ~~Mamta dum dum~~

---

This PR introduces significant enhancements to Mamta, making event management easier and more intuitive for users.
