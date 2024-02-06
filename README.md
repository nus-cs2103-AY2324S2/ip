# UkeCat
```
                    /~(_)~\.          ~ Hello!
 /= ••\      /~(_)~\        \     /\
K=|=|=|=|=|=|=|=|=(  )===]  |    (`~ o7
 \= ••/      \_(~)_/        /   c\   c\
                    \_(~)_/`    U`U_, )=~~  
```

**UkeCat** :octocat: is:
* text-based
* easy to learn
* ~~FAST~~ SUPER FAST to use

All you need to do is:
1. Download it from [here](https://github.com/ziiqii/ip).
2. Double-click to run it!

Features:
- [x] Managing tasks
- [ ] Managing deadlines (coming soon)
- [ ] Reminders (coming soon)

> "*Don't think. Just do.*"

If you are a Java programmer, you can use it to practice Java too. Here's the `updateTasks()`
method from the `FileManager` class:

```java
public static void updateTasks() {
    try (FileWriter fileWriter = new FileWriter("data/taskData.txt")) {
        for (Task task : Storage.getTasks()) {
            fileWriter.write(Parser.parseTaskToCsv(task) + "\n");
        }
    } catch (IOException ioException) {
        System.out.println("IO error occurred while updating.");
    }
}
```