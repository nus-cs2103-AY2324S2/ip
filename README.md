# Kaipybara Bot

```
          /'  _/                                /'
        /' _/~                                /'
     ,/'_/~  ____     O   ____              /'__     ____      ____     ____
    /\/~   /'    )  /'  /'    )--/'    /  /'    )  /'    )   )'    )--/'    )
  /'  \  /'    /' /'  /'    /' /'    /' /'    /' /'    /'  /'       /'    /'
/'     \(___,/(__(__/(___,/'  (___,/(__(___,/(__(___,/(__/'        (___,/(__
                  /'             /'
                /'       /     /'
              /'        (___,/'
```
> Your Best Bot and Friend 

About Me
-
I am a bot that helps you organise your tasks and boost your productivity so that you can focus on the more important things in life, such as watching your favourite TV show. I can:
- Able to read your text inputs.
- Execute commands to help you organise your life.
- Keep it saved locally once you leave the program.

All you need to do is to [download](https://github.com/c-wenlong/ip) me! :shit: And your life will become so much __*better*__.
1. No longer worried about forgetting deadlines.
2. No longer missing your dates with your potential partners.
3. No longer missing your meds.

Features
-
- [X] Add, Delete, Mark & Unmark Tasks.
- [X] View all Tasks.
- [X] Search for Tasks.

If you are a Java programmer
-
You can use it to practice Java too. Here's the `main` method:

```java
public class Main {
    public static void main(String[] args) {
        Ui ui = new Ui();
        System.out.println(ui.getStartMessage()); // opening statement
        TaskManager.loadLocalSavedTasks(); // checking if there are local files to load
        KBot.simulate(); // simulate kaipybara chatbot
        System.out.println(ui.getEndMessage()); // closing statement
        System.out.println(ui.getFlower());
    }
}
```