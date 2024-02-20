# Duke update: Venus

> "Unlock the potential of chat bots." - by nobody

## Venus features

Venus allows you to do so more more, for example,
- track your event dates
- track your event time
- store or load your events ~~easily~~ super easily!

And, it's _free_! Yes, **free**! :v:

To start,
1. You have to [download](https://github.com/peterXGD/ip) it.
2. Double click it.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. Either run it from intellij from `./src/java/Venus/Venus.java` or `./gradlew run`

Remember, run with `SDK default` and Java 11!

Features:
- [x] Different class types.
- [ ] Save and load tasks whenever you start the program
- [ ] Add delight to the experience when all tasks are complete :tada:

If you are a java programmer, here is the program `main` method:

```java
    public static void main(String[] args) {
        Venus venus = new Venus("data" + File.separator + "venus.txt");
        venus.run();
    }
```
