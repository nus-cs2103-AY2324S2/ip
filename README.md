# GMO (GameMate Organizer) 👾 🚀
> "Who wants to play video games?" - GMO

**GMO's not a bad influence... he**
- helps you remember your tasks
- ~SULKS~ _reminds_ you when you are slaved by work
- tells you to hurry so you can play with him!

**All you need to do is**
1. Download GMO [here](https://github.com/huekoh/ip)
2. Double click it
3. Start telling GMO your dues
4. Let him keep track of them for you 🤩

And he is **FREE**

GMO's Features:
- [X] Keep track of tasks (todos, dues, events)
- [X] Manage task status
- [X] Sort tasks by date
- [ ] Tell you to skip lecture

And if you are a Java programmer, use him to practice your coding too! Here's GMO's `constructor` method:
``` ruby
public GMO() {
        ui = new Ui();
        storage = new Storage();

        try {
            tasks = new TaskList(storage.loadData(), ui, storage);
        } catch (IOException e) {
            System.out.println("Error: Unable to load data. " + e.getMessage());
            tasks = new TaskList(ui);
        }
    }
```
