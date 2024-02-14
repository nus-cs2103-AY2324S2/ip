package cro;

/**
 * Represents the chatbot, Cro. Cro consists of a task list, a storage, and an
 * ui to help you track your tasks.
 */
public class Cro {

    public TaskList taskList;
    public Storage storage;
    private Ui ui;
    public Cro(String filePath) {
        ui = new Ui(this);
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (CroException e) {
            e.getMessage();
            taskList = new TaskList();
        }
    }

    /**
     * Returns nothing. Continuously reads the input from the user and parses it through the UI.
     */
    public void run() {
        while (ui.isReading()) {
        }
        storage.updateSave(taskList);
    }
    public static void main(String[] args){

        new Cro("saveFile.txt").run();


    }
}
