package lumiere.lumiere;

import java.io.IOException;

public class Lumiere {

    private Storage storage;
    private TaskList tasks;
    @SuppressWarnings("unused")
    private Ui ui;
    public static String TASK_LIST_PATH = "./lumiere.txt";

    public Lumiere() throws IOException {
        ui = new Ui();
        storage = new Storage(TASK_LIST_PATH);
        tasks = new TaskList();
        storage.loadTasksFromFile(tasks);
    }

    public String getResponse(String input) {
        try {
            String response = "";
            if (input.startsWith("list")) {
                response += "Here are the tasks in your list:\n";
                response += tasks.printList();
            } else if (input.startsWith("bye")) {
                response += Ui.exit();
            } else {
                response += tasks.addTask(input, storage);
            }
            return response;
        } catch (IOException err) {
            return "Sorry! This is an invalid command.";
        }
    }

    /*
     * public void run() throws IOException {
     * Ui.greet();
     * Lumiere lumiere = new Lumiere();
     * lumiere.ui.run(lumiere.tasks, lumiere.storage);
     * }
     * 
     * public static void main(String[] args) throws IOException {
     * new Lumiere().run();
     * }
     */
}
