# DukeProject 

DukeProject is a task management application designed to simplify your life. It offers a range of features to help you stay organized and productive.

>“Your mind is for having ideas, not holding them.” – David Allen

**DukePro frees your mind of having to remember things you need to do. It's,**

- Text-based interface for quick task management
- Easy to learn and use
- Super fast performance



**All you need to do is:**

1. Download it from [here](https://github.com/MaYuehan/ip/releases/tag/A-JUnit).
2. Double-click it.
3. Add your tasks.
4. Let it manage your tasks for you 😉 

And it is __FREE__!

## Key Features
- [x] Task tracking and management
- [x] Deadline management


If you are a Java programmer, you can use it to practice Java too. Here's the main method:

```java
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Runs the chatbot, displaying welcome message and processing user commands.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/botYue.txt").run();
    }
}
