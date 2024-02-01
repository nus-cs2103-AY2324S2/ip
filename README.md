# Duke Ted

> I'm not writing it down to remember it _later_, I'm writing it down to remember it **now**. - [Field Notes](https://fieldnotesbrand.com/)

Duke Ted is a task management application based on [Project Duke](https://nus-cs2103-ay1920s1.github.io/website/se-book-adapted/projectDuke/index.html)🔥.

## Command List
- `todo <task-name>`: Adds a new `ToDo` item.
- `deadline <task-name> \by <dd-MM-yyyy HH:mm>`: Adds a new `Deadline` task.
- `event <task-name> \from <dd-MM-yyyy HH:mm> \to <dd-MM-yyyy HH:mm>`: Adds a new `Event` item.
-  `mark <task-number>`: Marks task at index `<task-number>` as done.
- `unmark <task-number>`: Marks task at index `<task-number>` as not done.
- `delete <task-number>`: Deletes task at index `<task-number>`.
- `find <task-name>`: Finds task with matching keywords of `<task-name>`.
- `bye`: Exits the program.

## Levels Implemented
- [x] Level-0: Renaming Bot
- [x] Level-1: Greet, Echo, Exit
- [x] Level-2: Add, List
- [x] Level-3: Mark as Done
- [x] Level-4: ToDos, Events, Deadlines
- [x] Level-5: Handle Errors
- [x] Level-6: Delete
- [x] Level-7: Save
- [x] Level-8: Dates and Times
- [x] Level-9: Find
- [ ] Level-10: GUI

## Running Duke Ted
1. Download the repository [here](https://github.com/KohGuanZeh/ip).
2. Run `./gradlew clean build` from root folder.
3. Locate `duke.jar` from `builds/lib`.
4. Run `java -jar duke.jar`.

## Code Snippet of Duke Ted
```java
public class Duke {
    // File directory of stored data.
    private static final String FILE_DIRECTORY = "data";
    // File name of stored data.
    private static final String FILE_NAME = "duke.txt";

    public static void main(String[] args) {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Storage storage = null;
        try {
            storage = new Storage(Duke.FILE_DIRECTORY, Duke.FILE_NAME);
            taskList.loadTasks(storage.load(), ui);
        } catch (IOException e) {
            ui.showError("Error in creating or accessing data file. Exiting...");
            return;
        }

        ui.showGreeting();

        boolean isQuit = false;
        while (!isQuit) {
            try {
                String input = ui.readline();
                ui.showLine();
                Command command = Parser.parseInput(input);
                command.run(taskList, ui, storage);
                isQuit = command.isExit();
            } catch (CommandException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError("An error has occurred with the save file. Exiting...");
                isQuit = true;
            } finally {
                ui.showLine();
            }
        }
    }
}
```