package haro;

import haro.command.Command;
/**
 * The main class for the Haro application.
 * Haro is a task management chatBot.
 */
public class Haro {
    private String haroLogo = " ___  ___  ________  ________  ________\n"
            + "|\\  \\|\\  \\|\\   __  \\|\\   __  \\|\\   __  \\\n"
            + "\\ \\  \\\\\\  \\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\|\\  \\\n"
            + " \\ \\   __  \\ \\   __  \\ \\   _  _\\ \\  \\\\\\  \\\n"
            + "  \\ \\  \\ \\  \\ \\  \\ \\  \\ \\  \\\\  \\\\ \\  \\\\\\  \\\n"
            + "   \\ \\__\\ \\__\\ \\__\\ \\__\\ \\__\\\\ _\\\\ \\_______\\\n"
            + "    \\|__|\\|__|\\|__|\\|__|\\|__|\\|__|\\|_______|\n";
    private String horizontalLine = "______________________________________________";
    private String openingMsg = "Heya! I'm Haro!\n" + "What can I do for you today?";
    private String closingMSg = "Bye. Hope to see you some time soon!";
    private TaskList taskList;
    private Storage haroStorage;
    private Ui haroUi;

    /**
     * Constructs a new Haro object.
     * Initializes the storage, task list and ui components.
     *
     * @param dataPath   Path to the data file, where task data will be stored
     * @param directory  directory that the data file will be in
     */
    public Haro(String dataPath, String directory) {
        this.taskList = new TaskList();
        this.haroUi = new Ui();
        this.haroStorage = new Storage(dataPath, directory);
        this.taskList = new TaskList(haroStorage.loadSave());
    }

    /**
     * Initialises the haro application.
     * Greets the user and prompts the user for input through the command line.
     */
    public void initialise() {
        haroUi.greet();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = haroUi.readCommand();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(taskList, haroUi, haroStorage);
                isExit = c.isExit();
            } catch (Exception e) {
                haroUi.showError(e.getMessage());
            }
        }

        haroUi.bye();
    }

    public String getResponse(String userInput) {
        try {
            Command c = Parser.parseCommand(userInput);
            return c.execute(taskList, haroUi, haroStorage);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Saves the current list to the save file.
     */
    public void saveList() {
        this.haroStorage.saveToDisk(this.taskList);
    }
}

